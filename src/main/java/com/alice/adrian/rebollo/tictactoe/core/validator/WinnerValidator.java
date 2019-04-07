package com.alice.adrian.rebollo.tictactoe.core.validator;

import com.alice.adrian.rebollo.tictactoe.model.BoardRepresentation;
import com.alice.adrian.rebollo.tictactoe.model.Move;
import com.alice.adrian.rebollo.tictactoe.model.Result;
import com.alice.adrian.rebollo.tictactoe.model.annotation.Singleton;
import com.alice.adrian.rebollo.tictactoe.util.Constants;

import java.util.function.Predicate;

@Singleton
class WinnerValidator implements Validator<Move> {

    private final Predicate<Integer> LIMIT_PREDICATE = (i) -> i < Constants.BOARD_WIDTH;
    private final Predicate<Move> LEFT_DIAGONAL_PREDICATE = (move) -> move.getX() == move.getY();;
    private final Predicate<Move> RIGHT_DIAGONAL_PREDICATE = (move) -> Constants.BOARD_WIDTH - 1 - move.getX() == move.getY();

    private static WinnerValidator instance;

    private WinnerValidator(){
    }

    /*
     * Apply Double Checked Locking principle.
     * MoveValidator should be under Singleton pattern.
     */
    static WinnerValidator getInstance() {
        if(instance == null){
            synchronized (WinnerValidator.class) {
                if(instance == null){
                    instance = new WinnerValidator();
                }
            }
        }
        return instance;
    }

    public Result<Move> validate(final BoardRepresentation board, final Move move) {

        final int x = move.getX();
        final int y = move.getY();

        boolean result = simpleCheck((i) -> board.getSymbolAt(y, i) == board.getSymbolAt(y, i-1)) ||
               simpleCheck((i) -> board.getSymbolAt(i, x) == board.getSymbolAt(i-1, x)) ||
               diagonalCheck(LEFT_DIAGONAL_PREDICATE.test(move), (i) -> board.getSymbolAt(i, i) == board.getSymbolAt(i-1, i-1)) ||
               diagonalCheck(RIGHT_DIAGONAL_PREDICATE.test(move), (i) ->  board.getSymbolAt(Constants.BOARD_WIDTH -1-i, i) == board.getSymbolAt(Constants.BOARD_WIDTH -i, i-1));

        return Result.of(result, move);
    }

    private boolean simpleCheck(final Predicate<Integer> conditionPredicate) {

        int i = 1;

        while(LIMIT_PREDICATE.test(i) && conditionPredicate.test(i)) {
            if (i == Constants.BOARD_WIDTH -1) {
                return true;
            }
            i++;
        }

        return false;
    }

    private boolean diagonalCheck(final boolean isDiagonal, final Predicate<Integer> conditionPredicate) {
        if (isDiagonal) {

            int i = 1;

            while(LIMIT_PREDICATE.test(i) && conditionPredicate.test(i)) {
                if (i == Constants.BOARD_WIDTH - 1) {
                    return true;
                }
                i++;
            }
        }

        return false;
    }
}
