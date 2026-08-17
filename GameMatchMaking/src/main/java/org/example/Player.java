package org.example;
enum State{
    ONLINE,
    OFFLINE,
    INGAME
}
public class Player {
    int id;
    String name;
    int elo;
    String location;

    public void setState(State state) {
        this.state = state;
    }

    State state;

    public Player(int id,String location, String name,int elo) {
        this.id = id;
        this.elo = elo;
        this.location = location;
        this.name = name;
        this.state=State.OFFLINE;
    }

    public State getState() {
        return state;
    }

    public int getElo() {
        return elo;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}
