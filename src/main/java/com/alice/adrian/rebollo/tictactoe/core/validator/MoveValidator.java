package com.alice.adrian.rebollo.tictactoe.core.validator;

import com.alice.adrian.rebollo.tictactoe.model.BoardRepresentation;
import com.alice.adrian.rebollo.tictactoe.model.CellType;
import com.alice.adrian.rebollo.tictactoe.model.Move;
import com.alice.adrian.rebollo.tictactoe.model.Result;
import com.alice.adrian.rebollo.tictactoe.model.annotation.Singleton;

@Singleton
class MoveValidator implements Validator<Move> {

    private static MoveValidator instance;

    private MoveValidator(){
    }

    /*
     * Apply Double Checked Locking principle.
     * MoveValidator should be under Singleton pattern.
     */
    static MoveValidator getInstance() {
        if(instance == null){
            synchronized (MoveValidator.class) {
                if(instance == null){
                    instance = new MoveValidator();
                }
            }
        }
        return instance;
    }

    @Override
    public Result<Move> validate(final BoardRepresentation boardRepresentation, final Move move) {

        return boardRepresentation.getSymbolAt(move.getY(), move.getX()) != CellType.EMPTY ? Result.failure(move) : Result.valid(move);
    }
}
