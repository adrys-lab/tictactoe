package com.alice.adrian.rebollo.tictactoe.model;

import com.alice.adrian.rebollo.tictactoe.util.Constants;

import java.util.Arrays;

public class BoardRepresentation {

    private CellType[][] board;

    public BoardRepresentation() {
        board = new CellType[Constants.BOARD_WIDTH][Constants.BOARD_WIDTH];
    }

    public BoardRepresentation cloneInstance() {

        final BoardRepresentation board = new BoardRepresentation();
        for (int i = 0; i < this.board.length; i++) {
            board.board[i] = Arrays.copyOf(this.board[i], this.board[i].length);
        }

        return board;
    }

    public void initialize() {
        for(int row = 0; row < Constants.BOARD_WIDTH; row++) {
            for(int col = 0; col < Constants.BOARD_WIDTH; col++) {
                board[row][col] = CellType.EMPTY;
            }
        }
    }

    public void setSymbol(int y, int x, CellType symbol) {
        board[y][x] = symbol;
    }

    public CellType getSymbolAt(int y, int x) {
        return board[y][x];
    }
}
