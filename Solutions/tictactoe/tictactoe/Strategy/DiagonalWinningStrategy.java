package tictactoe.Strategy;

import tictactoe.model.Board;
import tictactoe.model.Player;

public class DiagonalWinningStrategy implements Strategy {
    @Override
    public boolean checkWinner(Board board, Player player) {
        char symbol = player.getSymbol().getValue();
        char [][] grid = board.getGrid();
        int size = board.getSize();
        int col = 0;
        int row = 0;
        boolean diagonal1Win = true;
        while(row < size && col < size) {
            if(grid[row][col] != symbol) {
                diagonal1Win = false;
                break;
            }
            row++;
            col++;
        }

        if(diagonal1Win) {
            return true;
        } else {
            row = 0;
            col = size - 1;
            while(row < size && col >= 0) {
                if(grid[row][col] != symbol) {
                    diagonal1Win = false;
                    break;
                }
                row++;
                col--;
            }
        }
        return diagonal1Win;
    }
}
