package com.alice.adrian.rebollo.tictactoe.core.validator;

import com.alice.adrian.rebollo.tictactoe.model.BoardRepresentation;
import com.alice.adrian.rebollo.tictactoe.model.Result;

@FunctionalInterface
public interface Validator<T> {

    Result<T> validate(final BoardRepresentation boardRepresentation, T t);
}
