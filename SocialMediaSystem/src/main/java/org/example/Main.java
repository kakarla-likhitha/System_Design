package org.example;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        SocialMediaSystem system = new SocialMediaSystem();

        // -------------------------
        // 1. Create users
        // -------------------------
        User user1 = new User(1, "Likhi");
        User user2 = new User(2, "Megh");
        User user3 = new User(3, "Rahul");
        User user4 = new User(4, "Priya");

        system.createUser(user1);
        system.createUser(user2);
        system.createUser(user3);
        system.createUser(user4);

        // -------------------------
        // 2. Follow users
        // User1 follows User2 and User3
        // -------------------------
        System.out.println(
                "User1 follows User2: "
                        + system.follow(1, 2)
        );

        System.out.println(
                "User1 follows User3: "
                        + system.follow(1, 3)
        );

        // -------------------------
        // 3. Create posts
        // -------------------------
        Post post1 = new Post(
                2,
                "Hello from Megh!",
                101
        );

        Post post2 = new Post(
                3,
                "Hello from Rahul!",
                102
        );

        Post post3 = new Post(
                1,
                "My own post!",
                103
        );

        Post post4 = new Post(
                2,
                "Another post from Megh!",
                104
        );

        system.createPost(post1);
        system.createPost(post2);
        system.createPost(post3);
        system.createPost(post4);

        // -------------------------
        // 4. Like posts
        // -------------------------
        System.out.println(
                "User1 likes post101: "
                        + system.likePost(1, 101)
        );

        System.out.println(
                "User3 likes post101: "
                        + system.likePost(3, 101)
        );

        System.out.println(
                "Post101 likes: "
                        + post1.getLikeCount()
        );

        // -------------------------
        // 5. Get User1's feed
        // -------------------------
        System.out.println("\nUser1 Feed:");

        List<Post> feed = system.getFeed(1, 10);

        for (Post post : feed) {
            System.out.println(
                    "Post ID: " + post.getId()
                            + " | Author: " + post.getAuthor()
                            + " | Content: " + post.getContent()
            );
        }

        // -------------------------
        // 6. Unlike
        // -------------------------
        System.out.println(
                "\nUser1 unlikes post101: "
                        + system.unlikePost(1, 101)
        );

        System.out.println(
                "Post101 likes: "
                        + post1.getLikeCount()
        );

        // -------------------------
        // 7. Unfollow
        // -------------------------
        System.out.println(
                "\nUser1 unfollows User3: "
                        + system.unfollow(1, 3)
        );

        // -------------------------
        // 8. Feed after unfollow
        // -------------------------
        System.out.println("\nUser1 Feed after unfollowing User3:");

        feed = system.getFeed(1, 10);

        for (Post post : feed) {
            System.out.println(
                    "Post ID: " + post.getId()
                            + " | Author: " + post.getAuthor()
                            + " | Content: " + post.getContent()
            );
        }
    }
}