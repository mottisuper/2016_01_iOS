package example.com.whatsappclient;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtMessage, txtRecipient;
    TextView txtStatus;
    Button btnSend;
    ListView lstMessages;
    LoginActivity.User user;
    List<String> messages;
    Thread checkForMessagesThread;
    boolean checkForMessages = true;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMessage = (EditText)findViewById(R.id.txtMessage);
        txtRecipient = (EditText)findViewById(R.id.txtRecipient);
        txtStatus = (TextView)findViewById(R.id.txtStatus);
        btnSend = (Button)findViewById(R.id.btnSend);
        lstMessages = (ListView)findViewById(R.id.lstMessages);
        String userName = getIntent().getStringExtra(LoginActivity.USER_NAME);
        String password = getIntent().getStringExtra(LoginActivity.PASSWORD);
        if(userName != null){
            user = new LoginActivity.User();
            user.userName = userName;
            user.password = password;
        }
        messages = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, messages);
        lstMessages.setAdapter(arrayAdapter);


    }

    public void btnSend(View view) {
        String message = txtMessage.getText().toString();
        String recipient = txtRecipient.getText().toString();
        txtStatus.setText("please wait...");
        btnSend.setEnabled(false);
        txtMessage.setEnabled(false);
        txtRecipient.setEnabled(false);
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                HttpURLConnection urlConnection = null;
                OutputStream outputStream = null;
                InputStream inputStream = null;
                boolean success = false;
                try {
                    URL url = new URL("http://10.0.2.2:8080/MainServlet");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setUseCaches(false);
                    urlConnection.setDoOutput(true);
                    urlConnection.setDoInput(true);
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.connect();
                    outputStream = urlConnection.getOutputStream();
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("action", "SendMessage");
                        jsonObject.put("userName", user.userName);
                        jsonObject.put("password", user.password);
                        jsonObject.put("recipient", params[1]);//please look at the execute line
                        jsonObject.put("content", params[0]);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    outputStream.write(jsonObject.toString().getBytes());
                    //outputStream.flush();
                    outputStream.close();
                    inputStream = urlConnection.getInputStream();
                    byte[] buffer = new byte[1024];
                    int actuallyRead;
                    StringBuilder stringBuilder = new StringBuilder();
                    while((actuallyRead = inputStream.read(buffer)) > 0){
                        String s = new String(buffer, 0, actuallyRead);
                        stringBuilder.append(s);
                    }

                    String requestBody = stringBuilder.toString();
                    try {
                        JSONObject jsonResponse = new JSONObject(requestBody);
                        if(jsonResponse.getString("result").equals("message sent"))
                            success = true;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("Elad", requestBody);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(outputStream != null)
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    if(inputStream != null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(urlConnection != null)
                        urlConnection.disconnect();
                }

                return success;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                if(success) {
                    txtStatus.setText("message sent");
                }else{
                    txtStatus.setText("message was NOT sent");
                }
                btnSend.setEnabled(true);
                txtMessage.setEnabled(true);
                txtRecipient.setEnabled(true);
            }
        }.execute(message, recipient);
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkForMessagesThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(checkForMessages){
                    HttpURLConnection urlConnection = null;
                    OutputStream outputStream = null;
                    InputStream inputStream = null;
                    try {
                        URL url = new URL("http://10.0.2.2:8080/MainServlet");
                        urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setUseCaches(false);
                        urlConnection.setDoOutput(true);
                        urlConnection.setDoInput(true);
                        urlConnection.setRequestMethod("POST");
                        urlConnection.setRequestProperty("Content-Type", "application/json");
                        urlConnection.connect();
                        outputStream = urlConnection.getOutputStream();
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("action", "CheckForMessages");
                            jsonObject.put("userName", user.userName);
                            jsonObject.put("password", user.password);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        outputStream.write(jsonObject.toString().getBytes());
                        //outputStream.flush();
                        outputStream.close();
                        inputStream = urlConnection.getInputStream();
                        byte[] buffer = new byte[1024];
                        int actuallyRead;
                        StringBuilder stringBuilder = new StringBuilder();
                        while((actuallyRead = inputStream.read(buffer)) > 0){
                            String s = new String(buffer, 0, actuallyRead);
                            stringBuilder.append(s);
                        }

                        String requestBody = stringBuilder.toString();
                        try {
                            JSONObject jsonResponse = new JSONObject(requestBody);
                            if(jsonResponse.getString("result").equals("done")) {
                                JSONArray jsonMessages = jsonResponse.getJSONArray("messages");
                                for (int i = 0; i < jsonMessages.length(); i++) {
                                    JSONObject jsonMessage = jsonMessages.getJSONObject(i);
                                    String content = jsonMessage.getString("content");
                                    messages.add(content);
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((ArrayAdapter<String>) lstMessages.getAdapter()).notifyDataSetChanged();
                                        }
                                    });

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("Elad", requestBody);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        if(outputStream != null)
                            try {
                                outputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        if(inputStream != null){
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(urlConnection != null)
                            urlConnection.disconnect();
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        });
        checkForMessagesThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        checkForMessages = false;
        checkForMessagesThread.interrupt();
        checkForMessagesThread = null;
    }
}
