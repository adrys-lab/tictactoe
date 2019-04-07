package com.alice.adrian.rebollo.tictactoe.core.controller;

import com.alice.adrian.rebollo.tictactoe.model.BoardRepresentation;
import com.alice.adrian.rebollo.tictactoe.model.Move;
import com.alice.adrian.rebollo.tictactoe.model.Result;

import java.util.List;

public interface BoardController {

    Result<Move> move(Move move);

    void reset();

    boolean isGameOver();

    boolean isDraw();

    BoardRepresentation getBoard();

    PlayerController getPlayerController();

    void nextRound();

    List<Move> getAvailableMoves();

    BoardControllerImpl getDeepCopy();
}
