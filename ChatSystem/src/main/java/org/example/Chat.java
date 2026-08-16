package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

enum ChatType{
    DIRECT,GROUP
}
public class Chat {
    private static int nextId=1;
    private int chatId;
    private ChatType chatType;
    private Set<User> participants;
    private List<Message> messages;

    public Chat(Set<User> participants) {
        if(participants.size()<2){
            throw new IllegalArgumentException("Chat needs at least 2 participants");
        }
        this.chatId = nextId++;
        this.messages = new ArrayList<>();
        this.participants = new HashSet<>(participants);
        if (participants.size()==2){
            this.chatType=ChatType.DIRECT;
        }
        else{
            this.chatType=ChatType.GROUP;
        }
    }
    public void addMessage(Message message){
        messages.add(message);
    }
    public List<Message> getMessages(){
        return new ArrayList<>(messages);
    }
    public Set<User> getParticipants() {
        return new HashSet<>(participants);
    }

    public int getChatId() {
        return chatId;
    }
    public boolean hasParticipant(int userId) {

        for (User user : participants) {
            if (user.getId() == userId) {
                return true;
            }
        }
        return false;
    }
}
