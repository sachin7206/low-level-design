package tictactoe.Strategy;

import tictactoe.model.Board;
import tictactoe.model.Player;

public interface Strategy {
    boolean checkWinner(Board board, Player player);
}
