package com.example;

/**
 * Created by eladlavi on 13/01/2016.
 */
public class Message implements Comparable<Message> {
    String content, sender, recipient;
    long timeStamp;

    public Message(String content, String sender, String recipient, long timeStamp) {
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.timeStamp = timeStamp;
    }

    @Override
    public int compareTo(Message o) {
        long result = this.timeStamp - o.timeStamp;
        return (int)result;
    }
}
