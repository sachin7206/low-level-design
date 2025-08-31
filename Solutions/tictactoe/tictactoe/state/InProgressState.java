package tictactoe.state;

import tictactoe.Main.Game;
import tictactoe.enums.GameStatus;
import tictactoe.model.Player;

public class InProgressState implements State {
    @Override
    public void handleMove(Game game, Player player, int row, int col) {

        game.getBoard().placeSymbol(row, col, player.getSymbol());

        // Check for a winner or a draw
        if (game.checkWinner(player)) {
            game.setWinner(player);
            game.setStatus(player.getSymbol().getValue() == 'X' ? GameStatus.WINNER_X : GameStatus.WINNER_O);
            game.setState(new WinnerState());
        } else if (game.getBoard().isFull()) {
            game.setState(new DrawState());
            game.setStatus(GameStatus.DRAW);
        } else {
            game.switchPlayer();
        }
    }
}
