package com.alice.adrian.rebollo.tictactoe.model;

public class Player {

    private final String name;
    private final CellType symbol;

    private boolean isWinner;
    private boolean isInTurn;

    public Player(final String name, final CellType symbol, final boolean isInTurn) {
        this.name = name;
        this.symbol = symbol;
        this.isInTurn = isInTurn;
        this.isWinner = false;
    }

    public CellType getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public boolean isInTurn() {
        return isInTurn;
    }

    public void setInTurn(final boolean inTurn) {
        isInTurn = inTurn;
    }

    public void setWinner(final boolean winner) {
        this.isWinner = winner;
    }

    public boolean hasSameSymbol(Player player) {
        return symbol == player.symbol;
    }
}
