package org.example;

public class Board {
    private Symbol[][] board;
    public Board(){
        board =new Symbol[3][3];
    }

    public Symbol getCell(int row,int col){
        return board[row][col];
    }


    public boolean isValidMove(int row,int col){
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return false;
        }
        return board[row][col] == null;
    }
    public boolean isFull(){
        for(int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (board[i][j]==null){
                    return false;
                }
            }

        }
        return true;
    }
    public boolean place(Player player,int row,int col){
        Symbol sym= player.getSymbol();
        if (isFull()){
            return false;
        }
        if(isValidMove(row,col)){
            board[row][col]= sym;
            return true;
        }
        return false;
    }

}
