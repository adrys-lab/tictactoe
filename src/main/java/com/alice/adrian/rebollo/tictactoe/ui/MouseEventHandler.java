package com.alice.adrian.rebollo.tictactoe.ui;

import com.alice.adrian.rebollo.tictactoe.core.ai.Strategy;
import com.alice.adrian.rebollo.tictactoe.core.controller.BoardController;
import com.alice.adrian.rebollo.tictactoe.mapper.MoveMapper;
import com.alice.adrian.rebollo.tictactoe.model.Move;
import com.alice.adrian.rebollo.tictactoe.model.Result;

import java.awt.*;

public class MouseEventHandler {

    private final BoardController boardController;
    private final Strategy strategy;

    public MouseEventHandler(final BoardController boardController, final Strategy strategy) {
        this.boardController = boardController;
        this.strategy = strategy;
    }

    void handleMouse(final Point point) {

        if (boardController.isGameOver()) {
            boardController.reset();
        } else {
            final Result<Move> resultMove = MoveMapper.getInstance().apply(point.getX(), point.getY());
            if (!boardController.isGameOver()) {

                final Result<Move> validMove = resultMove.map(boardController::move).orElse(Result.failure());

                if(validMove.isValid()) {
                    strategy.apply(boardController);
                    boardController.nextRound();
                }
            }
        }
    }
}
