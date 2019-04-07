package com.alice.adrian.rebollo.tictactoe.ui;

import com.alice.adrian.rebollo.tictactoe.model.exceptions.GameInitializationError;
import com.alice.adrian.rebollo.tictactoe.util.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

class UIResources {

    static BufferedImage imageBackground, firstPlayer, consolePlayer;

    static {
        imageBackground = getImageResource(Constants.PANEL_IMAGE);
        firstPlayer = getImageResource(Constants.PLAYER);
        consolePlayer = getImageResource(Constants.CONSOLE);
    }

    private static BufferedImage getImageResource(String path) {

        try {
            return ImageIO.read(Objects.requireNonNull(BoardWrapper.class.getClassLoader().getResource(path + Constants.PNG_EXTENSION)));
        } catch (IOException ex) {
            throw new GameInitializationError(String.format("Given resource image %s can not be loaded.", path));
        }
    }
}
