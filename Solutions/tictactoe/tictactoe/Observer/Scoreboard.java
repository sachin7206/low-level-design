package tictactoe.Observer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Scoreboard implements GameObserver {
    private final Map<String, Integer> scores;
    public Scoreboard() {
        this.scores = new ConcurrentHashMap<>();
    }
    @Override
    public void update(tictactoe.Main.Game game) {
        if (game.getWinner() != null) {
            String winnerName = game.getWinner().getName();
            scores.put(winnerName, scores.getOrDefault(winnerName, 0) + 1);
            System.out.printf("[Scoreboard] %s wins! Their new score is %d.%n", winnerName, scores.get(winnerName));
        }
    }

    public void displayScores() {
        System.out.println("Current Scores:");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
        }
    }
}
