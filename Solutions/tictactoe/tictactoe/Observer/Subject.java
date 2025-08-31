package tictactoe.Observer;

public interface Subject {
    void registerObserver(GameObserver observer);
    void removeObserver(GameObserver observer);
    void notifyObservers();
}
