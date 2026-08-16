package org.example;

import java.util.HashSet;
import java.util.Set;

public class Post {
    private int id;
    private int author;
    private String content;
    private Set<Integer> likes;
    private long createdAt;

    public Post(int author, String content, int id) {
        this.author = author;
        this.content = content;
        this.id = id;
        this.likes = new HashSet<>();
        this.createdAt= System.currentTimeMillis();
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public int getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public Set<Integer> getLikes() {
        return new HashSet<>(likes);
    }
    public boolean like(int userId){
        return likes.add(userId);
    }
    public boolean unlike(int userId){
        return likes.remove(userId);
    }
    public int getLikeCount(){
        return likes.size();
    }

}
class PostEntry {

    Post post;
    int authorId;
    int index;

    public PostEntry(Post post, int authorId, int index) {
        this.post = post;
        this.authorId = authorId;
        this.index = index;
    }
}
