package com.alice.adrian.rebollo.tictactoe.core.validator;

import com.alice.adrian.rebollo.tictactoe.model.Move;

public class ValidatorProvider {

    // no construct allowed
    private ValidatorProvider() {
    }

    public static Validator<Move> getWinnerValidatorInstance() {
        return WinnerValidator.getInstance();
    }

    public static Validator<Move> getMoveValidatorInstance() {
        return MoveValidator.getInstance();
    }
}
