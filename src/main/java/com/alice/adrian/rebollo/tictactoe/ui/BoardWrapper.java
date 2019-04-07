package com.alice.adrian.rebollo.tictactoe.ui;

import javax.swing.*;
import java.awt.*;

public class BoardWrapper extends JFrame {

    public BoardWrapper(final BoardPanel boardPanel) {
        Container cp = getContentPane();
        cp.add(boardPanel);
        setResizable(false);
        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ALICE - Adrian Rebollo - TicTacToe");
    }
}
