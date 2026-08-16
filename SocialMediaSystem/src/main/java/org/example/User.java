package org.example;

import java.util.HashSet;
import java.util.Set;

public class User {
    private int id;
    private String name;
    private Set<Integer> following;

    public User(int id, String name) {
        this.following = new HashSet<>();
        this.id = id;
        this.name = name;
    }
    public boolean follow(int userId){
        return following.add(userId);
    }
    public boolean unfollow(int userId){
        return following.remove(userId);
    }
    public boolean isFollowing(int userId){
        return following.contains(userId);
    }
    public int getId() {
        return id;

    }

    public Set<Integer> getFollowing() {
        return new HashSet<>(following);
    }

    public String getName() {
        return name;
    }
}
