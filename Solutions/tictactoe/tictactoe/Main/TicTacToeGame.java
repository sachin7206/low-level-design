package tictactoe.Main;

import tictactoe.Observer.GameObserver;
import tictactoe.Observer.GameSubject;
import tictactoe.Observer.Scoreboard;
import tictactoe.model.Player;

public class TicTacToeGame {

    private static TicTacToeGame instance;
    private Game game;
    private final Scoreboard scoreboard; // The system now manages a scoreboard

    private TicTacToeGame() {
        this.scoreboard = new Scoreboard();
    }

    public static synchronized TicTacToeGame getInstance() {
        if(instance == null) {
            return new TicTacToeGame();
        }
        return instance;
    }

    public void createNewGame(Player p1, Player p2, int sizeOfGrid) {
        System.out.println("New game created between " + p1.getName() + " and " + p2.getName() + " with grid size " + sizeOfGrid);
        this.game = new Game(p1, p2, sizeOfGrid);
        this.game.addObserver(this.scoreboard);
    }

    public void makeMove(Player player, int row, int col) {
        System.out.println(player.getName() + " made move at (" + row + ", " + col + ")");
        game.makeMove(player, row, col);

    }

    public void printBoard() {
        game.getBoard().printBoard();
    }

    public void printScoreBoard() {
        scoreboard.displayScores();
    }

}
