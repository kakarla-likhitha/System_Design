package org.example;

import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Board board;
    private Player currentPlayer;
    private boolean gameOver;
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board();
        this.currentPlayer = player1;
        this.gameOver = false;
    }
    public void switchPlayer(){
        if (currentPlayer.equals(player1)){
            currentPlayer=player2;
        }
        else{
            currentPlayer=player1;
        }
    }
    public boolean isGameOver() {
        return gameOver;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public boolean makeMove(int row,int col){
        if (gameOver) {
            return false;
        }
        boolean placed=board.place(currentPlayer,row,col);
        if (!placed){
            return false;
        }
        if(checkDraw()){
            System.out.println("Draw");
            gameOver=true;
            return true;
        }
        if(checkWin(currentPlayer.getSymbol())){
            System.out.println(currentPlayer+" Wins");
            gameOver=true;
            return true;
        }
        switchPlayer();
        return true;
    }
    public boolean checkWin(Symbol symbol){
        for (int i = 0; i < 3; i++) {
            if (board.getCell(i,0) == symbol &&
                    board.getCell(i,1) == symbol &&
                    board.getCell(i,2) == symbol) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if ( board.getCell(0,j)== symbol &&
                    board.getCell(1,j)== symbol &&
                    board.getCell(2,j) == symbol) {
                return true;
            }
        }

        if (board.getCell(0, 0) == symbol &&
                board.getCell(1, 1) == symbol &&
                board.getCell(2, 2) == symbol) {
            return true;
        }

        if (board.getCell(0, 2) == symbol &&
                board.getCell(1, 1) == symbol &&
                board.getCell(2, 0) == symbol) {
            return true;
        }
        return false;
    }
    public boolean checkDraw() {
        return board.isFull();
    }
}
