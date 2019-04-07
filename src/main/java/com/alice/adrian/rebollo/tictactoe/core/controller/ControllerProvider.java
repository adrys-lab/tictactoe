package com.alice.adrian.rebollo.tictactoe.core.controller;

public class ControllerProvider {

    // no construct allowed
    private ControllerProvider() {
    }

    private static PlayerController buildPlayerControllerInstance() {
        return new PlayerController();
    }

    public static BoardController buildBoardControllerInstance() {
        return new BoardControllerImpl(buildPlayerControllerInstance());
    }
}
