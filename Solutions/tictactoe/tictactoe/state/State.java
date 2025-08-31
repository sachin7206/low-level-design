package tictactoe.state;

import tictactoe.Main.Game;
import tictactoe.model.Player;

public interface State {
    void handleMove(Game game, Player player, int row, int col);
}
