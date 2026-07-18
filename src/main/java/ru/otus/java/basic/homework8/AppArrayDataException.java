package ru.otus.java.basic.homework8;

public class AppArrayDataException extends RuntimeException {
    private final int row;
    private final int col;

    public AppArrayDataException(String message, int row, int col) {
        super(message);
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
