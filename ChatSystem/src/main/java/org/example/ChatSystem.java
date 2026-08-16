package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChatSystem {
    Map<Integer,User> users;
    Map<Integer,Chat> chats;
    public ChatSystem(){
        users=new HashMap<>();
        chats=new HashMap<>();
    }
    public Chat createDirectChat(User user1,User user2){
        if (user1.getId() == user2.getId()) {
            return null;
        }
        Set<User> participants=new HashSet<>();
        participants.add(user1);
        participants.add(user2);
        Chat chat = new Chat(participants);
        chats.put(chat.getChatId(),chat);
        return chat;
    }
    public void addUser(User user) {
        users.put(user.getId(), user);
    }
    public Chat createGroupChat(Set<User> participants){
        if (participants.size()<=2){
            return null;
        }
        Chat chat = new Chat(participants);
        chats.put(chat.getChatId(), chat);
        return chat;
    }

    public boolean sendMessage(int chatId,User sender,String content){
        Chat chat=chats.get(chatId);
        if(chat==null){
            return false;
        }
        if (!chat.getParticipants().contains(sender)) {
            return false;
        }
        Message message = new Message(content, sender);
        for (User user : chat.getParticipants()) {
            if (user.getId() != sender.getId()) {
                message.getStatus().put(user.getId(), Status.SENT);
            }
        }
        chat.addMessage(message);
        return true;
    }
    public boolean updateMessageStatus(
            int chatId,
            int messageId,
            int userId,
            Status newStatus) {

        Chat chat = chats.get(chatId);

        if (chat == null) {
            return false;
        }

        if (!chat.hasParticipant(userId)) {
            return false;
        }

        for (Message message : chat.getMessages()) {

            if (message.getId() == messageId) {

                if (message.getSender().getId() == userId) {
                    return false;
                }

                Status currentStatus = message.getStatus().get(userId);

                if (currentStatus == null) {
                    return false;
                }

                if (!isValidStatusTransition(currentStatus, newStatus)) {
                    return false;
                }

                message.getStatus().put(userId, newStatus);

                return true;
            }
        }

        return false;
    }
    private boolean isValidStatusTransition(
            Status current,
            Status next) {

        if (current == Status.SENT) {
            return next == Status.DELIVERED ||
                    next == Status.READ;
        }

        if (current == Status.DELIVERED) {
            return next == Status.READ;
        }

        return false;
    }
}
