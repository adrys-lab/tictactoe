package com.alice.adrian.rebollo.tictactoe.mapper;

import com.alice.adrian.rebollo.tictactoe.model.Move;
import com.alice.adrian.rebollo.tictactoe.model.Result;

import java.awt.Point;
import java.util.function.BiFunction;

@FunctionalInterface
public interface Mapper extends BiFunction<Double, Double, Result<Move>> {

    @Override
    Result<Move> apply(Double xPos, Double yPos);
}
