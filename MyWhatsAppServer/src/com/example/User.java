package com.example;

import java.util.PriorityQueue;

/**
 * Created by eladlavi on 13/01/2016.
 */
public class User {
    String userName, password;
    PriorityQueue<Message> messages;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        messages = new PriorityQueue<>();
    }



    @Override
    public int hashCode() {
        return userName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(obj instanceof User){
            User other = (User)obj;
            return this.userName.equals(other.userName);
        }
        return false;
    }
}
