package com.alice.adrian.rebollo.tictactoe.core.controller;

import com.alice.adrian.rebollo.tictactoe.model.CellType;
import com.alice.adrian.rebollo.tictactoe.model.Player;
import com.alice.adrian.rebollo.tictactoe.util.Constants;

import java.util.Optional;

public class PlayerController {

    private Player player;
    private Player console;

    PlayerController() {
        player = new Player(Constants.PLAYER, CellType.X, true);
        console = new Player(Constants.CONSOLE, CellType.O, false);
    }

    private PlayerController(final Player player, final Player console) {
        this.player = player;
        this.console = console;
    }

    public Player getPlayerInTurn() {
        return player.isInTurn() ? player : console;
    }

    public Player getPlayerNotInTurn() {
        return player.isInTurn() ? console : player;
    }

    public Optional<Player> getWinner() {
        return player.isWinner() ? Optional.of(player) : console.isWinner() ? Optional.of(console) : Optional.empty();
    }

    Player getPlayer() {
        return player;
    }

    Player getConsole() {
        return console;
    }

    void nextTurn() {
        player.setInTurn(!player.isInTurn());
        console.setInTurn(!console.isInTurn());
    }

    void playerInTurnIsTheWinner() {
        player.setWinner(player.isInTurn());
        console.setWinner(console.isInTurn());
    }

    void noWinners() {
        player.setWinner(false);
        console.setWinner(false);
    }

    PlayerController cloneInstance() {
        return new PlayerController(new Player(player.getName(), player.getSymbol(), player.isInTurn()), new Player(console.getName(), console.getSymbol(), console.isInTurn()));
    }
}
