package com.alice.adrian.rebollo.tictactoe.core.controller;

import com.alice.adrian.rebollo.tictactoe.model.*;
import com.alice.adrian.rebollo.tictactoe.core.validator.ValidatorProvider;
import com.alice.adrian.rebollo.tictactoe.model.exceptions.GameIllegalState;
import com.alice.adrian.rebollo.tictactoe.util.Constants;

import java.util.*;

class BoardControllerImpl implements BoardController {

    private BoardRepresentation board;

    private PlayerController playerController;

    private List<Move> allMovements;

    private int moveCount;
    private boolean gameOver;
    private boolean draw;

    BoardControllerImpl(final PlayerController playerController) {
        board = new BoardRepresentation();
        this.playerController = playerController;
        moveCount = 0;
        gameOver = false;
        draw = false;
        initializeBoard();
    }

    /**
     * Restart the game with a new blank board.
     */
    public void reset() {
        moveCount = 0;
        gameOver = false;
        draw = false;
        playerController = new PlayerController();
        initializeBoard();
    }

    @Override
    public Result<Move> move(final Move move) {

        final Result<Move> moveResult = ValidatorProvider.getMoveValidatorInstance().validate(board, move);

        return moveResult.ifValid(this::makeMove);
    }

    private void makeMove(final Move move) {
        draw = false;

        if(gameOver) {
            throw new GameIllegalState("tictactoe is over. No moves can be played.");
        }

        board.setSymbol(move.getY(), move.getX(), playerController.getPlayerInTurn().getSymbol());

        moveCount++;
        allMovements.remove(move);

        final Result<Move> playerIsWinner = ValidatorProvider.getWinnerValidatorInstance().validate(board, move);
        if (playerIsWinner.isValid()) {
            playerController.playerInTurnIsTheWinner();
            gameOver = true;
        } else if (moveCount == Constants.BOARD_WIDTH * Constants.BOARD_WIDTH) {
            draw = true;
            gameOver = true;
            playerController.noWinners();
        }

        playerController.nextTurn();
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public boolean isDraw() {
        return draw;
    }

    @Override
    public BoardRepresentation getBoard() {
        return board;
    }

    @Override
    public PlayerController getPlayerController() {
        return playerController;
    }

    @Override
    public void nextRound() {
        playerController.getPlayer().setInTurn(true);
        playerController.getConsole().setInTurn(false);
    }

    @Override
    public List<Move> getAvailableMoves() {
        return allMovements;
    }

    @Override
    public BoardControllerImpl getDeepCopy() {
        PlayerController playerController = this.playerController.cloneInstance();
        BoardControllerImpl boardController = new BoardControllerImpl(playerController);

        boardController.board = this.board.cloneInstance();
        boardController.allMovements = new LinkedList<>();
        boardController.allMovements.addAll(this.allMovements);
        boardController.moveCount = this.moveCount;
        boardController.gameOver = this.gameOver;
        boardController.draw = this.draw;

        return boardController;
    }

    /**
     * Set the cells to be blank and load the available moves (all the moves are
     * available at the start of the game).
     */
    private void initializeBoard() {
        board.initialize();
        allMovements = Movements.cloneMoves();
    }
}
