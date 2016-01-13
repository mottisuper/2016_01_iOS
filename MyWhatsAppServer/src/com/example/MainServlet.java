package com.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by eladlavi on 10/01/2016.
 */
public class MainServlet extends javax.servlet.http.HttpServlet {

    int counter = 0;
    HashMap<User, User> users;

    @Override
    public void init() throws ServletException {
        users = new HashMap<>();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        byte[] buffer = new byte[1024];
        int actuallyRead;
        InputStream inputStream = request.getInputStream();
        StringBuilder stringBuilder = new StringBuilder();
        while((actuallyRead = inputStream.read(buffer)) > 0){
            String s = new String(buffer, 0, actuallyRead);
            stringBuilder.append(s);
        }
        String requestBody = stringBuilder.toString();
        try {
            JSONObject jsonRequest = new JSONObject(requestBody);
            String action = jsonRequest.getString("action");
            JSONObject jsonResponse = new JSONObject();
            switch (action){
                case "SendMessage":
                    sendMessage(jsonRequest, jsonResponse);
                    break;
                case "CheckForMessages":
                    checkForMessages(jsonRequest, jsonResponse);
                    break;
                case "SignUp":
                    signUp(jsonRequest, jsonResponse);
                    break;
                case "Login":
                    login(jsonRequest, jsonResponse);
                    break;
            }
            response.getWriter().write(jsonResponse.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void sendMessage(JSONObject jsonRequest, JSONObject jsonResponse) throws JSONException {
        String userName = jsonRequest.getString("userName");
        String password = jsonRequest.getString("password");
        if(validateUser(userName, password)) {
            String recipient = jsonRequest.getString("recipient");
            if(users.containsKey(recipient)){
                Message message = new Message(jsonRequest.getString("content"), userName,
                        recipient, System.currentTimeMillis());
                users.get(recipient).messages.add(message);
                jsonResponse.put("result", "message sent");
            }

        }
    }

    private void checkForMessages(JSONObject jsonRequest, JSONObject jsonResponse) throws JSONException {
        String userName = jsonRequest.getString("userName");
        String password = jsonRequest.getString("password");
        if(validateUser(userName, password)) {
            JSONArray jsonMessages = new JSONArray();
            Message message;
            PriorityQueue<Message> messages = users.get(userName).messages;
            while ((message = messages.poll()) != null){
                JSONObject jsonMessage = new JSONObject();
                jsonMessage.put("content", message.content);
                jsonMessage.put("sender", message.sender);
                jsonMessage.put("timeStamp", message.timeStamp);
                jsonMessages.put(jsonMessage);
            }
            jsonResponse.put("messages", jsonMessages);
            jsonResponse.put("result", "done");
        }else {
            jsonResponse.put("result", "wrong username or password");
        }
    }

    private void signUp(JSONObject jsonRequest, JSONObject jsonResponse) throws JSONException {
        String userName = jsonRequest.getString("userName");
        if (!users.containsKey(userName)) {
            String password = jsonRequest.getString("password");
            User user = new User(userName, password);
            users.put(user, user);
            jsonResponse.put("result", "user created");

        } else {
            jsonResponse.put("result", "user name already exists");
        }
    }

    private void login(JSONObject jsonRequest, JSONObject jsonResponse) throws JSONException {
        String userName = jsonRequest.getString("userName");
        String password = jsonRequest.getString("password");
        if(validateUser(userName, password)){
            jsonResponse.put("result", "login ok");
        }else{
            jsonResponse.put("result", "wrong user or password");
        }
    }

    private boolean validateUser(String userName, String password){
        User existingUser = users.get(userName);
        return existingUser != null && existingUser.password.equals(password);
    }


    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("in doGet " + request.getQueryString());
        response.getWriter().write("<h1>welcome to my servlet " + (counter++) + "</h1>");

    }
}
