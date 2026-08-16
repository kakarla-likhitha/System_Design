package org.example;

import java.util.HashMap;
import java.util.Map;

enum Status{
    SENT,
    DELIVERED,
    READ
}
public class Message {
    private static int nextId = 1;
    private int id;
    private User sender;
    private String content;
    private Map<Integer,Status> status;

    public User getSender() {
        return sender;
    }

    public Message(String content, User sender) {
        this.id=nextId++;
        this.content = content;
        this.sender = sender;
        this.status = new HashMap<>();
    }

    public Map<Integer, Status> getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
