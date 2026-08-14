package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {
        Player player1=new Player(1,"likhi",Symbol.O);
        Player player2=new Player(2,"megh",Symbol.X);
        Game game = new Game(player1,player2);
        Scanner sc = new Scanner(System.in);
        while (!game.isGameOver()) {
            System.out.println(
                    game.getCurrentPlayer().getName() + "'s turn"
            );

            System.out.println("Enter row:");
            int row = sc.nextInt();

            System.out.println("Enter col:");
            int col = sc.nextInt();

            boolean res = game.makeMove(row, col);

            if (!res) {
                System.out.println("Invalid move. Try again.");
            }
        }

        sc.close();

    }
}
