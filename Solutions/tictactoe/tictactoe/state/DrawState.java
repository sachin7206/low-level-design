package tictactoe.state;

import exceptions.InvalidMoveException;
import tictactoe.Main.Game;
import tictactoe.model.Player;

public class DrawState implements State {
    @Override
    public void handleMove(Game game, Player player, int row, int col) {
        throw new InvalidMoveException("Game is already over. It was a draw.");
    }
}
