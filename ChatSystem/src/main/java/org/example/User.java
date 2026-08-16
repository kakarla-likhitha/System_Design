package org.example;

public class User {
    private static int nextId = 1;
    private int id;
    private String name;
    private String mobile;

    public User(String mobile, String name) {
        this.id = nextId++;
        this.mobile = mobile;
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
