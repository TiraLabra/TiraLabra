package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.player.Bot;
import com.mycompany.tiralabra_maven.player.FileHandler;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Contains game logic.
 *
 * @author joel Nummelin
 */
public class Game {

    private GameMode gameMode;
    private Bot primaryBot;
    private Bot secondaryBot;
    private int[][] results;
    private Statistics statistics;
    private int lastRound;
    private FileHandler fileHandler;
    private FileHandler fileHandler2;
    private File file;

    /**
     * Sets up the game in right game mode.
     */
    public Game() throws IOException {
        fileHandler2 = new FileHandler(new File("profiles/all"));
        setUpResultTable();
        this.primaryBot = new Bot(0);
        int n = -1;
        Object[] options = {"Play vs bot", "Play vs bot as guest", "Bot vs bot"};
        while (n == -1) {
            n = JOptionPane.showOptionDialog(new Frame(), "Please select", "Rock, Paper, Scissors",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options);
        }
        switch (n) {
            case 0:
                playVsBot();
                break;
            case 1:
                asGuest();
                return;
            case 2:
                botVsBot();
                return;
        }
        this.statistics = new Statistics(file);
        fileHandler = new FileHandler(file);
        primaryBot.loadProfile(fileHandler);
    }

    /**
     * Sets up human vs ai game with saving.
     */
    private void playVsBot() throws IOException {
        int n = -1;
        Object[] options = {"New player", "Existing player"};
        while (n == -1) {
            n = JOptionPane.showOptionDialog(new Frame(), "Please select", "Rock, Paper, Scissors",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options);
        }

        if (n == 0) {
            try {
                file = newPlayer();
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.gameMode = GameMode.PLAYER_VS_BOT;
            JFileChooser fc = new JFileChooser("profiles/");
            fc.setFileFilter(new FileNameExtensionFilter("Player profile", "player"));
            fc.showOpenDialog(null);
            file = fc.getSelectedFile();
        }
    }

    /**
     * Sets up human vs ai game without saving.
     */
    private void asGuest() {
        this.gameMode = GameMode.GUEST_VS_BOT;
        try {
            primaryBot.loadProfile(fileHandler2);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sets up ai vs ai game.
     */
    private void botVsBot() {
        this.gameMode = GameMode.BOT_VS_BOT;
        this.secondaryBot = new Bot(1);
    }

    /**
     * Creates new player profile file.
     *
     * @return file
     * @throws IOException
     */
    private File newPlayer() throws IOException {
        String playerName = null;

        while (true) {
            playerName = JOptionPane.showInputDialog("Name");
            if (playerName != null) {
                File f = new File("profiles/" + playerName + ".player");
                if (f.exists()) {
                    JOptionPane.showMessageDialog(null, "Profile already exists");
                    continue;
                }
                break;
            }
        }
        File f = new File("profiles/" + playerName + ".player");
        f.createNewFile();
        return f;
    }

    /**
     * Play one round. parameter move is used only when it's human vs ai.
     *
     * @param move
     * @return result
     */
    public int playRound(int move) throws IOException {
        if (gameMode == GameMode.BOT_VS_BOT) {
            int primary = primaryBot.makeAMove();
            int secondary = secondaryBot.makeAMove();
            primaryBot.updateAi(results[primary][secondary]);
            secondaryBot.updateAi(results[secondary][primary]);
            lastRound = results[primary][secondary];
            updateStatistics(lastRound);
            return lastRound;
        }
        int primary = primaryBot.makeAMove();
        lastRound = results[primary][move];
        primaryBot.updateAi(lastRound);
        updateStatistics(lastRound);
        if (gameMode == GameMode.PLAYER_VS_BOT) {
            fileHandler.saveLine(primary, lastRound);
        }
        fileHandler2.saveLine(primary, lastRound);
        return results[primary][move];
    }

    /**
     * Sets up table that gives game results.
     */
    private void setUpResultTable() {
        this.results = new int[3][3];
        results[0][0] = 0;
        results[0][1] = -1;
        results[0][2] = 1;
        results[1][0] = 1;
        results[1][1] = 0;
        results[1][2] = -1;
        results[2][0] = -1;
        results[2][1] = 1;
        results[2][2] = 0;
    }

    /**
     *
     * @return gameMode
     */
    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     *
     * @return statistics
     */
    public Statistics getStatistics() {
        return statistics;
    }

    /**
     * Updates general game statistics.
     *
     * @param result
     */
    private void updateStatistics(int result) {
        if (result == 0) {
            statistics.draw();
        } else if (result == 1) {
            statistics.win();
        } else if (result == -1) {
            statistics.lose();
        }
    }
}