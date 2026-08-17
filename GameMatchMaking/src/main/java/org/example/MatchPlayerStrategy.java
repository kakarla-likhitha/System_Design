package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface MatchPlayerStrategy {
    List<Player> findMatch(List<Player> players);
}
class EloStrategy implements MatchPlayerStrategy{
    private final int threshold;
    public EloStrategy(int threshold){
        this.threshold=threshold;
    }
    @Override
    public List<Player> findMatch(List<Player> players){
        List<Player> eligiblePlayers= new ArrayList<>();
        for (Player player : players) {
            if (player.getState() == State.ONLINE) {
                eligiblePlayers.add(player);
            }
        }
        eligiblePlayers.sort(
                Comparator.comparingInt(Player::getElo)
        );
        for (int i = 0; i < eligiblePlayers.size() - 1; i++) {

            Player p1 = eligiblePlayers.get(i);
            Player p2 = eligiblePlayers.get(i + 1);

            if (Math.abs(p1.getElo() - p2.getElo()) <= threshold) {
                return List.of(p1, p2);
            }
        }

        return new ArrayList<>();


    }
}