package org.example;

import java.util.*;

public class SocialMediaSystem {
    private Map<Integer,User> users;
    private Map<Integer,Post> posts;
    private Map<Integer, List<Post>> userPosts;

    public SocialMediaSystem() {
        this.posts = new HashMap<>();
        this.users = new HashMap<>();
        userPosts = new HashMap<>();
    }
    public boolean createUser(User user) {
        if (users.containsKey(user.getId())) {
            return false;
        }

        users.put(user.getId(), user);
        return true;
    }

    public boolean createPost(Post post) {
        if (posts.containsKey(post.getId())) {
            return false;
        }
        if (!users.containsKey(post.getAuthor())) {
            return false;
        }

        posts.put(post.getId(), post);
        userPosts.computeIfAbsent(post.getAuthor(),k->new ArrayList<>()).add(post);
        return true;
    }
    public boolean follow(int followerId,int followingId){
        if(!users.containsKey(followerId) || !users.containsKey(followingId)){
            return false;
        }
        if (followerId == followingId) {
            return false;
        }

        User follower = users.get(followerId);

        return follower.follow(followingId);
    }
    public boolean unfollow(int followerId,int followingId){
        if(!users.containsKey(followerId) || !users.containsKey(followingId)){
            return false;
        }
        if (followerId == followingId) {
            return false;
        }

        User follower = users.get(followerId);

        return follower.unfollow(followingId);
    }
    public boolean likePost(int userId,int postId){
        if(!users.containsKey(userId) || !posts.containsKey(postId)){
            return false;
        }
        Post post=posts.get(postId);
        return post.like(userId);
    }
    public boolean unlikePost(int userId,int postId){
        if(!users.containsKey(userId) || !posts.containsKey(postId)){
            return false;
        }
        Post post =posts.get(postId);
        return post.unlike(userId);
    }
    private void addLatestPost(PriorityQueue<PostEntry> pq,int userId){
        List<Post> posts=userPosts.get(userId);
        if(posts==null || posts.isEmpty()){
            return;
        }
        int lastIndex=posts.size()-1;
        pq.offer(new PostEntry(posts.get(lastIndex),userId,lastIndex));
    }
    public List<Post> getFeed(int userId,int limit){
        if(!users.containsKey(userId)){
            return new ArrayList<>();
        }
        User user=users.get(userId);
        PriorityQueue<PostEntry> pq=new PriorityQueue<>((a,b)->Long.compare(b.post.getCreatedAt(),a.post.getCreatedAt()));
        addLatestPost(pq,userId);
        for(int followeeId:user.getFollowing()){
            addLatestPost(pq,followeeId);
        }
        List <Post> feed =new ArrayList<>();
        while(!pq.isEmpty() &&feed.size()<limit){
            PostEntry entry=pq.poll();
            feed.add(entry.post);
            int next=entry.index-1;
            List<Post> authorPosts=userPosts.get(entry.authorId);
            if(next>=0){
                pq.offer(new PostEntry(authorPosts.get(next), entry.authorId, next));
            }
        }
        return feed;
    }
}
