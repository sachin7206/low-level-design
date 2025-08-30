package tictactoe.Strategy;

import tictactoe.model.Board;
import tictactoe.model.Player;

public class RowWinningStrategy implements Strategy {

    @Override
    public boolean checkWinner(Board board, Player player) {
        char symbol = player.getSymbol().getValue();
        char [][] grid = board.getGrid();
        int size = board.getSize();
        for(int r = 0; r<size; r++) {
            boolean rowWin = true;
            for(int c = 0; c<size; c++) {
                if(grid[r][c] != symbol) {
                    rowWin = false;
                    break;
                }
            }

            if(rowWin) {
                return true;
            }
        }


        return false;
    }
}
