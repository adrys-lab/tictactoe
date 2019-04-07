package com.alice.adrian.rebollo.tictactoe.core.ai;

import com.alice.adrian.rebollo.tictactoe.core.controller.BoardController;

/**
 * Strategy Pattern
 */
@FunctionalInterface
public interface Strategy {

    void apply(BoardController boardController);
}
