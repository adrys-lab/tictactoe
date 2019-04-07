package com.alice.adrian.rebollo.tictactoe.model.exceptions;

public class IllegalPlayerCell extends IllegalArgumentException {

    public IllegalPlayerCell(final String message) {
        super(message);
    }
}
