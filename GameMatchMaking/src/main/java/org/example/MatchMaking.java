package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchMaking {

    private Map<Integer, Player> players;
    private MatchPlayerStrategy strategy;

    public MatchMaking(MatchPlayerStrategy strategy) {
        this.players = new HashMap<>();
        this.strategy = strategy;
    }

    public void addPlayer(Player player) {
        players.put(player.getId(), player);
    }

    public Game match() {

        List<Player> playerList =
                new ArrayList<>(players.values());

        List<Player> matched =
                strategy.findMatch(playerList);

        if (matched.size() != 2) {
            return null;
        }

        Player p1 = matched.get(0);
        Player p2 = matched.get(1);

        p1.setState(State.INGAME);
        p2.setState(State.INGAME);

        return new Game(p1, p2);
    }
}
