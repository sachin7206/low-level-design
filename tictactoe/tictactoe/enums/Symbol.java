package tictactoe.enums;

public enum Symbol {
    X('X'),
    O('O');

    private char value;

    // Constructor
    Symbol(char value) {
        this.value = value;
    }

    // Getter method to access the char value
    public char getValue() {
        return value;
    }
}
