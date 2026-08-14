package org.example;
enum Symbol{
    X,O
}
public class Player {
    private int id;
    private String name;
    private Symbol symbol;
    public Player(int id, String name, Symbol symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }


    public int getId() {
        return id;
    }

    public Symbol getSymbol() {
        return symbol;
    }



    public String getName() {
        return name;
    }



}
