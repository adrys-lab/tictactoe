package com.alice.adrian.rebollo.tictactoe.model;

import com.alice.adrian.rebollo.tictactoe.model.annotation.Immutable;

import java.util.Objects;

@Immutable
public class Move {

    private final int xPos;
    private final int yPos;

    private Move(final int xPos, final int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public Move cloneInstance() {
        return new Move(xPos, yPos);
    }

    public static class Builder {

        private Integer xPos;
        private Integer yPos;

        public Builder of(int xPos, int yPos) {
            this.xPos = xPos;
            this.yPos = yPos;
            return this;
        }

        Move build() {
            Objects.requireNonNull(xPos);
            Objects.requireNonNull(yPos);
            return new Move(xPos, yPos);
        }

    }


}
