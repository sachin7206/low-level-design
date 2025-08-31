package tictactoe.model;

import tictactoe.enums.Symbol;

public class Board {
    private final int size;
    private char grid[][];
    public Board(int size) {
        this.size = size;
        this.grid = new char[size][size];
        initializeBoard(size, grid);
    }

    public char[][] getGrid() {
        return grid;
    }

    public int getSize() {
        return size;
    }
    private void initializeBoard(int size, char[][] grid) {
        for(int r = 0; r<size; r++) {
            for(int c = 0; c<size; c++) {
                grid[r][c] = '-'; // initialize with empty char
            }
        }
    }

    public void placeSymbol(int row, int col, Symbol symbol) {
        if(row > size || col > size) {
            throw new IllegalArgumentException("Row or Column is out of bounds");
        }

        if(grid[row][col] != '-') {
            throw new IllegalArgumentException("Cell is already occupied");
        }

        grid[row][col] = symbol.getValue();
    }

    public boolean isFull() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if(grid[r][c] == '-') {
                    return false;
                }
            }
        }

        return true;
    }

    public void printBoard() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }
    }

}
