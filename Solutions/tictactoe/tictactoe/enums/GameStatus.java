package tictactoe.enums;

public enum GameStatus {
    DRAW("DRAW"),
    WINNER_X("WINNER_X"),
    WINNER_O("WINNER_O"),
    IN_PROGRESS("IN_PROGRESS");

    private String value;

    // Constructor
    GameStatus(String value) {
        this.value = value;
    }

    // Getter method to access the char value
    public String getValue() {
        return value;
    }
}
