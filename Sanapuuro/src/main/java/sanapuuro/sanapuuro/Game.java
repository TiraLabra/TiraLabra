/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro;

import java.util.Random;
import sanapuuro.sanapuuro.grid.Grid;

import sanapuuro.sanapuuro.filereaders.LetterReader;
import sanapuuro.sanapuuro.letters.Letters;

/**
 * The main game logic class that is used to start a new game and
 * retrieve the grid cursor and letter pool for input.
 * @author skaipio
 */
public class Game implements GameTimerListener{
    private final Grid grid;    // Game grid for letters.
    private Letters letters;    // For random letters from the alphabet,
    private Player player;      // Player with score and control methods.
    private Evaluation evaluation;  // Evaluation class that keeps track of player score and time.
    private final int gridSize = 8; // Grid size n x n
    private final int timerStart = 2*60; // Count down starts from.

    public Game() {
        this.grid = new Grid(gridSize, gridSize);
    }

    /**
     * Starts a new game,loads in valid letters and words and starts the countdown timer.
     * @param timer A timer that notifies the game when it has counted down to zero.
     */
    public void newGame(GameTimer timer) {
        this.grid.clear();
        this.letters = new LetterReader(new Random());
        this.evaluation = new Evaluation();       
        this.player = new Player(grid, this.evaluation, letters);
        this.evaluation.registerPlayer(player, timer);
        timer.addListener(this.player);
        timer.startCountdownFrom(timerStart);
    }

    public Player getPlayer(){
        return this.player;
    }
    
    public Grid getGrid(){
        return this.grid;
    }

    /**
     * Disables player's controls.
     */
    @Override
    public void notifyTimeOut() {
        this.player.setControlsEnabled(false);
    }
}
