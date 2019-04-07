package com.alice.adrian.rebollo.tictactoe;

import com.alice.adrian.rebollo.tictactoe.core.ai.AlphaBetaPrunning;
import com.alice.adrian.rebollo.tictactoe.core.ai.Strategy;
import com.alice.adrian.rebollo.tictactoe.core.controller.BoardController;
import com.alice.adrian.rebollo.tictactoe.core.controller.ControllerProvider;
import com.alice.adrian.rebollo.tictactoe.ui.BoardPanel;
import com.alice.adrian.rebollo.tictactoe.ui.BoardWrapper;
import com.alice.adrian.rebollo.tictactoe.ui.MouseEventHandler;

import javax.swing.*;

public class Run {

    public static void main(String[] args) {

        final BoardController boardController = ControllerProvider.buildBoardControllerInstance();
        final Strategy strategy = new AlphaBetaPrunning();
        final MouseEventHandler mouseEventHandler = new MouseEventHandler(boardController, strategy);
        final BoardPanel boardPanel = new BoardPanel(boardController, mouseEventHandler);

        if (args.length == 1) {
            System.out.println("Game Mode: Player vs. Player");
            SwingUtilities.invokeLater(() -> new BoardWrapper(boardPanel));
        } else {
            System.out.println("Game Mode: Player vs. Console");
            SwingUtilities.invokeLater(() -> new BoardWrapper(boardPanel));
        }

    }
}
