package tictactoe.Main;

import tictactoe.Observer.GameSubject;
import tictactoe.Strategy.ColumnWinningStrategy;
import tictactoe.Strategy.DiagonalWinningStrategy;
import tictactoe.Strategy.RowWinningStrategy;
import tictactoe.Strategy.Strategy;
import tictactoe.enums.GameStatus;
import tictactoe.model.Board;
import tictactoe.model.Player;
import tictactoe.state.InProgressState;
import tictactoe.state.State;

import java.util.List;

public class Game extends GameSubject {
    private Player p1;
    private Player p2;
    private final int size;
    private Board board;
    private State state;
    private Player winner;
    private GameStatus status;
    List<Strategy> strategyList;
    private Player currentPlayer;
    private GameSubject gameSubject;
    public Game(Player p1, Player p2, int size) {
        this.p1 = p1;
        this.p2 = p2;
        this.size =  size;
        this.board = new Board(size);
        this.state = new InProgressState();
        this.currentPlayer = p1;
        this.status = GameStatus.IN_PROGRESS;
        strategyList = List.of(new RowWinningStrategy(),
                 new ColumnWinningStrategy(),
                 new DiagonalWinningStrategy()
        );
    }

    public Board getBoard() {
        return board;
    }

    public void makeMove(Player player, int row, int col) {
        state.handleMove(this, player, row, col);
    }

    public boolean checkWinner(Player player) {
        for(Strategy strategy : strategyList) {
            if(strategy.checkWinner(board, player)){
                return true;
            }
        }
        return false;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer == p1 ? p2 : p1;
    }

    public Player getCurrentPlayer() { return currentPlayer; }
    public Player getWinner() { return winner; }
    public void setWinner(Player winner) { this.winner = winner; }
    public GameStatus getStatus() { return status; }
    public void setState(State state) { this.state = state; }
    public void setStatus(GameStatus status) {
        this.status = status;
        // Notify observers when the status changes to a finished state
        if (status != GameStatus.IN_PROGRESS) {
            setGame(this);
        }
    }

}
