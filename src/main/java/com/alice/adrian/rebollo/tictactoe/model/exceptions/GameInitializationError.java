package com.alice.adrian.rebollo.tictactoe.model.exceptions;

public class GameInitializationError extends IllegalStateException {

    public GameInitializationError(final String message) {
        super(message);
    }
}
