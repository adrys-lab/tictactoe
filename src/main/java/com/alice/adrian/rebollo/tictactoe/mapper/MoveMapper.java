package com.alice.adrian.rebollo.tictactoe.mapper;

import com.alice.adrian.rebollo.tictactoe.model.Move;
import com.alice.adrian.rebollo.tictactoe.model.Movements;
import com.alice.adrian.rebollo.tictactoe.model.Pair;
import com.alice.adrian.rebollo.tictactoe.model.Result;
import com.alice.adrian.rebollo.tictactoe.model.annotation.Singleton;
import com.alice.adrian.rebollo.tictactoe.util.Constants;

import java.util.*;

@Singleton
public class MoveMapper implements Mapper {

    private static Mapper instance;

    private MoveMapper(){
    }

    /*
     * Apply Double Checked Locking principle.
     * Mapper should be under Singleton pattern.
     */
    public static Mapper getInstance() {
        if(instance == null){
            synchronized (MoveMapper.class) {
                if(instance == null){
                    instance = new MoveMapper();
                }
            }
        }
        return instance;
    }

    @Override
    public Result<Move> apply(final Double xPos, final Double yPos) {

        final Optional<Move> cellFound = BoardCellPositions.cells.entrySet().stream()
                .filter(entry -> entry.getValue().getA().isBetween(xPos))
                .filter(entry -> entry.getValue().getB().isBetween(yPos))
                .map(Map.Entry::getKey)
                .findFirst();

        return cellFound.map(Result::valid)
                .orElseGet(Result::failure);
    }

    private static class BoardCellPositions {

        static final Map<Move, Pair<Range, Range>> cells = new HashMap<>();

        private static final int FIRST_RANGE = 0;
        private static final int SECOND_RANGE = Constants.SCREEN_WIDTH / 3; //200
        private static final int THIRD_RANGE = (int) (Constants.SCREEN_WIDTH / 1.5); // 400
        private static final int FOURTH_RANGE = Constants.SCREEN_WIDTH; // 600

        static {
            cells.put(Movements.get(0), Pair.of(new Range(FIRST_RANGE, SECOND_RANGE), new Range(FIRST_RANGE, SECOND_RANGE)));
            cells.put(Movements.get(1), Pair.of(new Range(SECOND_RANGE, THIRD_RANGE), new Range(FIRST_RANGE, SECOND_RANGE)));
            cells.put(Movements.get(2), Pair.of(new Range(THIRD_RANGE, FOURTH_RANGE), new Range(FIRST_RANGE, SECOND_RANGE)));
            cells.put(Movements.get(3), Pair.of(new Range(FIRST_RANGE, SECOND_RANGE), new Range(SECOND_RANGE, THIRD_RANGE)));
            cells.put(Movements.get(4), Pair.of(new Range(SECOND_RANGE, THIRD_RANGE), new Range(SECOND_RANGE, THIRD_RANGE)));
            cells.put(Movements.get(5), Pair.of(new Range(THIRD_RANGE, FOURTH_RANGE), new Range(SECOND_RANGE, THIRD_RANGE)));
            cells.put(Movements.get(6), Pair.of(new Range(FIRST_RANGE, SECOND_RANGE), new Range(THIRD_RANGE, FOURTH_RANGE)));
            cells.put(Movements.get(7), Pair.of(new Range(SECOND_RANGE, THIRD_RANGE), new Range(THIRD_RANGE, FOURTH_RANGE)));
            cells.put(Movements.get(8), Pair.of(new Range(THIRD_RANGE, FOURTH_RANGE), new Range(THIRD_RANGE, FOURTH_RANGE)));
        }

        private static class Range {

            private final int start;
            private final int end;

            private Range(int start, int end) {
                this.start = start;
                this.end = end;
            }

            boolean isBetween(final Number value) {
                return value.intValue() >= start && value.intValue() <= end;
            }
        }
    }
}
