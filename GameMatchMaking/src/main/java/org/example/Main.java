package org.example;

public class Main {

    public static void main(String[] args) {

        MatchMaking matchmaking =
                new MatchMaking(new EloStrategy(50));

        // -------------------------
        // Create players
        // -------------------------

        Player p1 = new Player(1, "India", "Alice", 1500);
        Player p2 = new Player(2, "India", "Bob", 1520);
        Player p3 = new Player(3, "US", "Charlie", 1800);
        Player p4 = new Player(4, "India", "David", 1510);
        Player p5 = new Player(5, "India", "Eve", 2000);

        // -------------------------
        // Make players ONLINE
        // -------------------------

        p1.setState(State.ONLINE);
        p2.setState(State.ONLINE);
        p3.setState(State.ONLINE);
        p4.setState(State.ONLINE);

        // p5 remains OFFLINE

        // -------------------------
        // Add players
        // -------------------------

        matchmaking.addPlayer(p1);
        matchmaking.addPlayer(p2);
        matchmaking.addPlayer(p3);
        matchmaking.addPlayer(p4);
        matchmaking.addPlayer(p5);

        // -------------------------
        // Match
        // -------------------------

        Game game = matchmaking.match();

        if (game == null) {
            System.out.println("No match found");
        } else {
            System.out.println("Match found!");
            System.out.println(
                    game.getPlayer1().getName()
                            + " vs "
                            + game.getPlayer2().getName()
            );

            System.out.println(
                    game.getPlayer1().getName()
                            + " state: "
                            + game.getPlayer1().getState()
            );

            System.out.println(
                    game.getPlayer2().getName()
                            + " state: "
                            + game.getPlayer2().getState()
            );
        }

        // -------------------------
        // Check remaining players
        // -------------------------

        System.out.println("\nPlayer states:");

        System.out.println(
                p1.getName() + " : " + p1.getState()
        );

        System.out.println(
                p2.getName() + " : " + p2.getState()
        );

        System.out.println(
                p3.getName() + " : " + p3.getState()
        );

        System.out.println(
                p4.getName() + " : " + p4.getState()
        );

        System.out.println(
                p5.getName() + " : " + p5.getState()
        );
    }
}