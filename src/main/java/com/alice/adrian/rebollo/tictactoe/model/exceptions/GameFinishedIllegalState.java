package com.alice.adrian.rebollo.tictactoe.model.exceptions;

public class GameFinishedIllegalState extends IllegalStateException {

    public GameFinishedIllegalState(final String message) {
        super(message);
    }
}
