package org.example;

public class User {
    private int userId;
    private String name;
    public User(int id,String name){
        this.userId=id;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }
}
