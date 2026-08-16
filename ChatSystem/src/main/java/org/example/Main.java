package org.example;

import java.util.HashSet;
import java.util.Set;
public class Main{
    public static void main(String[] args){
        User user1=new User("9381973256","likhi");
        User user2=new User("9381973257","likhi2");
        User user3=new User("9381973258","likhi3");
        User user4=new User("9381973259","likhi4");
        ChatSystem system= new ChatSystem();
        system.addUser(user1);
        system.addUser(user2);
        system.addUser(user3);
        system.addUser(user4);
        //Direct chat
        Chat directChat=system.createDirectChat(user1,user2);
        System.out.println("Direct chat created"+(directChat!=null));
        boolean sent= system.sendMessage(directChat.getChatId(),user1,"Hello");
        System.out.println("message sent"+sent);
        Message message =directChat.getMessages().get(0);
        System.out.println("Message ID: " + message.getId());
        System.out.println("Content: " + message.getContent());
        //status check
        System.out.println("initial status"+message.getStatus().get(user2.getId()));
        //update status
        boolean delivered = system.updateMessageStatus(directChat.getChatId(), message.getId(), user2.getId(), Status.DELIVERED);
        System.out.println("delivered update"+delivered);
        System.out.println("Status"+ message.getStatus().get(user2.getId()));
        boolean read = system.updateMessageStatus(
                directChat.getChatId(),
                message.getId(),
                user2.getId(),
                Status.READ
        );

        System.out.println("Read update: " + read);

        System.out.println(
                "Status: " +
                        message.getStatus().get(user2.getId())
        );
        //Group chat
        Set<User> members = new HashSet<>();

        members.add(user1);
        members.add(user2);
        members.add(user3);
        members.add(user4);

        Chat groupChat = system.createGroupChat(members);

        System.out.println(
                "Group chat created: " +
                        (groupChat != null)
        );
        boolean groupMessageSent = system.sendMessage(
                groupChat.getChatId(),
                user1,
                "Hello everyone!"
        );

        System.out.println(
                "Group message sent: " +
                        groupMessageSent
        );
        Message groupMessage =
                groupChat.getMessages().get(0);

        System.out.println(
                "User2 status: " +
                        groupMessage.getStatus().get(user2.getId())
        );

        System.out.println(
                "User3 status: " +
                        groupMessage.getStatus().get(user3.getId())
        );

        System.out.println(
                "User4 status: " +
                        groupMessage.getStatus().get(user4.getId())
        );
        system.updateMessageStatus(
                groupChat.getChatId(),
                groupMessage.getId(),
                user2.getId(),
                Status.READ
        );
        system.updateMessageStatus(
                groupChat.getChatId(),
                groupMessage.getId(),
                user3.getId(),
                Status.DELIVERED
        );
        System.out.println("\nAfter status updates:");

        System.out.println(
                "User2: " +
                        groupMessage.getStatus().get(user2.getId())
        );

        System.out.println(
                "User3: " +
                        groupMessage.getStatus().get(user3.getId())
        );

        System.out.println(
                "User4: " +
                        groupMessage.getStatus().get(user4.getId())
        );
    }
}