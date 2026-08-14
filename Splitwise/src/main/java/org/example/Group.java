package org.example;

import java.util.HashSet;
import java.util.Set;

public class Group {
    public int getGrpId() {
        return grpId;
    }

    private int grpId;
    private String name;
    private Set<Integer> members;
    public Group(int id,String name,Set<Integer> members) {
        if (members == null || members.size() < 2) {
            throw new IllegalArgumentException(
                    "Group must have at least 2 members");
        }
        this.grpId = id;
        this.name = name;
        this.members = new HashSet<>(members);
    }
    public boolean addMember(int userId){
        return members.add(userId);
    }
    public boolean removeMember(int userId){
        if (!members.contains(userId)) {
            return false;
        }


        return members.remove(userId);
    }
    public boolean hasMember(int userId){
        return members.contains(userId);
    }
    public Set<Integer> getMembers(){
        return new HashSet<>(members);
    }

}
