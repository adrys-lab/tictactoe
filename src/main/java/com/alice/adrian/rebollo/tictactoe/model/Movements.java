package com.alice.adrian.rebollo.tictactoe.model;

import java.util.*;
import java.util.stream.Collectors;

public class Movements {

    private static final List<Move> MOVES = new LinkedList<>(Arrays.asList(new Move.Builder().of(0, 0).build(),
            new Move.Builder().of(1, 0).build(),
            new Move.Builder().of(2, 0).build(),
            new Move.Builder().of(0, 1).build(),
            new Move.Builder().of(1, 1).build(),
            new Move.Builder().of(2, 1).build(),
            new Move.Builder().of(0, 2).build(),
            new Move.Builder().of(1, 2).build(),
            new Move.Builder().of(2, 2).build()));

    private Movements(){
    }

    public static Move get(int idx) {
        return MOVES.get(idx);
    }

    public static List<Move> cloneMoves() {
        return MOVES.stream()
                .map(Move::cloneInstance)
                .collect(Collectors.toList());
    }
}
