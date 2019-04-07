package com.alice.adrian.rebollo.tictactoe.ui;

import com.alice.adrian.rebollo.tictactoe.core.controller.BoardController;
import com.alice.adrian.rebollo.tictactoe.model.BoardRepresentation;
import com.alice.adrian.rebollo.tictactoe.model.CellType;
import com.alice.adrian.rebollo.tictactoe.model.Player;
import com.alice.adrian.rebollo.tictactoe.model.exceptions.GameFinishedIllegalState;
import com.alice.adrian.rebollo.tictactoe.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BoardPanel extends JPanel {

    private final BoardController boardController;

    public BoardPanel(final BoardController boardController, final MouseEventHandler mouseEventHandler) {
        this.boardController = boardController;
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        addMouseEventHandler(mouseEventHandler);
    }

    private void addMouseEventHandler(MouseEventHandler mouseEventHandler) {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mouseEventHandler.handleMouse(e.getPoint());
                BoardPanel.this.repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawPanel(graphics);
    }

    /**
     * The main painting method that paints everything.
     * @param graphics     the Graphics object that will perform the panting
     */
    private void drawPanel(Graphics graphics) {
        setProperties(graphics);
        drawBoard(graphics);
        if (boardController.isGameOver()) {
            drawResult(graphics);
        }
    }

    /**
     * Set the rendering hints of the Graphics object.
     * @param graphics     the Graphics object to set the rendering hints on
     */
    private void setProperties (Graphics graphics) {
        graphics.drawImage(UIResources.imageBackground, 0, 0, null);
        graphics.drawString("", 0, 0);
    }

    private void drawBoard(Graphics graphics) {
        final BoardRepresentation boardRepresentation = boardController.getBoard();

        for (int y = 0; y < Constants.BOARD_WIDTH; y++) {
            for (int x = 0; x < Constants.BOARD_WIDTH; x++) {
                if (boardRepresentation.getSymbolAt(y, x) == CellType.X) {
                    graphics.drawImage(UIResources.firstPlayer, Constants.CELL_OFFSET + Constants.CELL_WIDTH * x, Constants.CELL_OFFSET + Constants.CELL_WIDTH * y, null);
                } else if (boardRepresentation.getSymbolAt(y, x) == CellType.O) {
                    graphics.drawImage(UIResources.consolePlayer, Constants.CELL_OFFSET + Constants.CELL_WIDTH * x, Constants.CELL_OFFSET + Constants.CELL_WIDTH * y, null);
                }

            }
        }
    }

    private void drawResult(final Graphics graphics) {

        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 50));

        String message;

        if (boardController.isDraw()) {
            message = Constants.DRAW_RESULT_MESSAGE;
        } else {
            if(!boardController.getPlayerController().getWinner().isPresent()) {
                throw new GameFinishedIllegalState("Game finished either without draw nor winenr !");
            }
            final Player winner = boardController.getPlayerController().getWinner().get();
            message = winner.getName() + Constants.WIN_RESULT_MESSAGE;
        }

        graphics.drawString(message, (Constants.SCREEN_WIDTH / 3), 35);

    }
}
