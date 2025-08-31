package tictactoe.Strategy;

import tictactoe.model.Board;
import tictactoe.model.Player;

public class ColumnWinningStrategy implements Strategy {
    @Override
    public boolean checkWinner(Board board, Player player) {
        char symbol = player.getSymbol().getValue();
        char [][] grid = board.getGrid();
        int size = board.getSize();

        for(int c = 0; c<size; c++) {
            boolean colWin = true;
            for(int r = 0; r < size; r++) {
                if(grid[r][c] != symbol) {
                    colWin = false;
                    break;
                }
            }

            if(colWin) {
                return true;
            }
        }
        return false;
    }
}
