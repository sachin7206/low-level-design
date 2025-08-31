package tictactoe.Observer;

import tictactoe.Main.Game;

import java.util.ArrayList;
import java.util.List;

public abstract class GameSubject {
    private Game game;
    private final List<GameObserver> observers = new ArrayList<>();

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    public void setGame(Game game) {
        this.game = game;
        notifyObservers();
    }

    private void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.update(game);
        }
    }
}
