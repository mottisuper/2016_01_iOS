package example.com.whatsappclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {

    EditText txtMessage, txtRecipient;
    TextView txtStatus;
    Button btnSend;
    static final String sender = "elad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMessage = (EditText)findViewById(R.id.txtMessage);
        txtRecipient = (EditText)findViewById(R.id.txtRecipient);
        txtStatus = (TextView)findViewById(R.id.txtStatus);
        btnSend = (Button)findViewById(R.id.btnSend);
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
                        jsonObject.put("userName", "elad");
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

                return null;
            }
        }.execute(message, recipient);
    }
}
