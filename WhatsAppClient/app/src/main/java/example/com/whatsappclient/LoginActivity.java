package example.com.whatsappclient;

import android.content.Intent;
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    EditText txtUserName, txtPassword;
    TextView lblStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUserName = (EditText)findViewById(R.id.txtUserName);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        lblStatus = (TextView)findViewById(R.id.lblStatus);
    }

    static class User{
        String userName,password;
    }

    public void btnLogin(View view) {
        lblStatus.setText("please wait");
        //((Button)view).setEnabled(false);
        String userName = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();
        //TODO: check userName and password length;
        new AsyncTask<String, Void, User>() {
            @Override
            protected User doInBackground(String... params) {
                HttpURLConnection urlConnection = null;
                OutputStream outputStream = null;
                InputStream inputStream = null;
                boolean inputStreamClosed = false, outputStreamClosed = false, urlConnectionClosed = false;
                User user = null;
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
                        jsonObject.put("action", "SignUp");
                        jsonObject.put("userName", params[0]);
                        jsonObject.put("password", params[1]);
                        outputStream.write(jsonObject.toString().getBytes());
                        outputStream.close();
                        outputStreamClosed = true;
                        inputStream = urlConnection.getInputStream();
                        byte[] buffer = new byte[1024];
                        int actuallyRead;
                        StringBuilder stringBuilder = new StringBuilder();
                        while((actuallyRead = inputStream.read(buffer)) > 0){
                            String s = new String(buffer, 0, actuallyRead);
                            stringBuilder.append(s);
                        }
                        inputStream.close();
                        inputStreamClosed = true;
                        urlConnection.disconnect();
                        urlConnectionClosed = true;

                        String response = stringBuilder.toString();
                        JSONObject jsonResponse = new JSONObject(response);
                        String result = jsonResponse.getString("result");
                        if(result.equals("user created")){

                        }else if(result.equals("user name already exists")){

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(!outputStreamClosed && outputStream != null)
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    if(!inputStreamClosed && inputStream != null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(!urlConnectionClosed && urlConnection != null)
                        urlConnection.disconnect();
                }
                return success;
            }

            @Override
            protected void onPostExecute(Boolean success) {

                if(success){
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.putExtra("userName")
                }else{
                    lblStatus.setText("user name taken");
                }
            }
        }.execute(userName, password);
    }

    public void btnSignup(View view) {
    }
}
