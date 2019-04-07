package com.alice.adrian.rebollo.tictactoe.core.ai;

import com.alice.adrian.rebollo.tictactoe.core.controller.BoardController;
import com.alice.adrian.rebollo.tictactoe.model.CellType;
import com.alice.adrian.rebollo.tictactoe.model.Move;
import com.alice.adrian.rebollo.tictactoe.model.Player;
import com.alice.adrian.rebollo.tictactoe.model.Result;
import com.alice.adrian.rebollo.tictactoe.model.exceptions.IllegalPlayerCell;

import java.util.Optional;

/**
 * Uses the Alpha-Beta Pruning algorithm for calculate best possible move.
 */
public class AlphaBetaPrunning implements Strategy {

    @Override
    public void apply(final BoardController boardController) {
        alphaBetaPruning(boardController.getPlayerController().getPlayerInTurn(), boardController, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    private int alphaBetaPruning(final Player player, final BoardController boardController, final double maximum, final double minimum) {
        if (boardController.isGameOver()) {
            return score(player, boardController);
        }

        if (boardController.getPlayerController().getPlayerInTurn().hasSameSymbol(player)) {
            return getMax(player, boardController, maximum, minimum);
        } else {
            return getMin(player, boardController, maximum, minimum);
        }
    }

    private int getMax(final Player player, final BoardController boardController, double maximum, final double minimum) {

        Optional<Move> bestMove = Optional.empty();

        for (final Move move : boardController.getAvailableMoves()) {

            final BoardController boardControllerCopy = boardController.getDeepCopy();
            final Result<Move> resultMove = boardControllerCopy.move(move);

            if (resultMove.isValid()) {
                int score = alphaBetaPruning(player, boardControllerCopy, maximum, minimum);
                if (score > maximum) {
                    maximum = score;
                    bestMove = Optional.of(move);
                }
                if (maximum >= minimum) {
                    break;
                }
            }
        }

        bestMove.ifPresent(boardController::move);

        return (int)maximum;
    }

    private int getMin(final Player player, final BoardController boardController, final double maximum, double minimum) {

        Optional<Move> bestMove = Optional.empty();

        for (final Move move: boardController.getAvailableMoves()) {

            final BoardController boardControllerCopy = boardController.getDeepCopy();

            final Result<Move> resultMove = boardControllerCopy.move(move);

            if (resultMove.isValid()) {

                int score = alphaBetaPruning(player, boardControllerCopy, maximum, minimum);
                if (score < minimum) {
                    minimum = score;
                    bestMove = Optional.of(move);
                }
                if (maximum >= minimum) {
                    break;
                }
            }
        }

        bestMove.ifPresent(boardController::move);

        return (int) minimum;
    }

    private int score(Player player, BoardController boardController) {

        if (player.getSymbol() == CellType.EMPTY) {
            throw new IllegalPlayerCell("Player must be X or O.");
        }

        final Player opponent = boardController.getPlayerController().getPlayerNotInTurn();
        final CellType winnerSymbol = boardController.getPlayerController().getWinner().map(Player::getSymbol).orElse(CellType.EMPTY);

        if (boardController.isGameOver() &&
                !boardController.isDraw() &&
                winnerSymbol == player.getSymbol()) {
            return 10;
        } else if (boardController.isGameOver() &&
                !boardController.isDraw() &&
                winnerSymbol == opponent.getSymbol()) {
            return -10;
        } else {
            return 0;
        }
    }

}
