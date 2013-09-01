package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.player.Bot;
import com.mycompany.tiralabra_maven.player.FileHandler;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
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
    //main bot
    private Bot primaryBot;
    private Bot secondaryBot;
    private int[][] results;
    private Statistics statistics;
    private int lastRound;
    private FileHandler fileHandler;
    //filehandler2 is for saving all games
    private FileHandler fileHandler2;
    private File file;
    //file2 is for filehandler2
    private File file2;

    /**
     * Sets up the game in right game mode.
     */
    public Game() throws IOException {
        file2 = new File("profiles/all");
        fileHandler2 = new FileHandler(file2);
        setUpResultTable();
        this.primaryBot = new Bot(0);
        gameModeWindow();
        if (gameMode != GameMode.PLAYER_VS_BOT){
            return;
        }
        fileHandler = new FileHandler(file);
        primaryBot.loadProfile(fileHandler);
    }

    /**
     * Sets up human vs ai game with saving.
     */
    private void playVsBot() throws IOException {
        showNewOrExistingPlayerWindow();
        showFileBrowser();
        this.statistics = new Statistics(file);
    }

    /**
     * Sets up human vs ai game without saving.
     */
    private void asGuest() {
        this.gameMode = GameMode.GUEST_VS_BOT;
        try {
            this.statistics = new Statistics(file2);
            primaryBot.loadProfile(fileHandler2);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sets up ai vs ai game.
     */
    private void botVsBot() throws FileNotFoundException {
        this.gameMode = GameMode.BOT_VS_BOT;
        this.secondaryBot = new Bot(1);
        this.statistics = new Statistics(null);
    }

    /**
     * Creates new player profile file.
     *
     * @return file
     * @throws IOException
     */
    private void newPlayer() throws IOException {
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
        file = f;
    }

    /**
     * Play one round. parameter move is used only when it's human vs ai.
     *
     * @param move
     * @return result
     */
    public int playRound(int move) throws IOException {
        if (gameMode == GameMode.BOT_VS_BOT) {
            return playBotVsBotRound();
        }
        return playHumanVsBotRound(move);
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
     * @return gameMode
     */
    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     * @return statistics
     */
    public Statistics getStatistics() {
        return statistics;
    }
    
    
    /**
     * Shows pop-up window about ai's decision. 
     */
    public void showData(){
        primaryBot.showData();
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

    /**
     * Shows pop-up option window asking game mode. 
     * @throws HeadlessException
     * @throws IOException 
     */
    private void gameModeWindow() throws HeadlessException, IOException {
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
                break;
            case 2:
                botVsBot();
                break;
        }
    }

    /**
     * Executes one bot vs bot round
     * @return result
     */
    private int playBotVsBotRound() {
        int primary = primaryBot.makeAMove();
        int secondary = secondaryBot.makeAMove();
        primaryBot.updateAi(results[primary][secondary]);
        secondaryBot.updateAi(results[secondary][primary]);
        lastRound = results[primary][secondary];
        updateStatistics(lastRound);
        return lastRound;
    }

    /**
     * Executes one human vs bot round. 
     * @param move
     * @return result
     */
    private int playHumanVsBotRound(int move) {
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
     * Shows pop-up window asking to create new profile or not. 
     * @throws HeadlessException
     * @throws IOException 
     */
    private void showNewOrExistingPlayerWindow() throws HeadlessException, IOException {
        int n = -1;
        Object[] options = {"New player", "Existing player"};
        while (n == -1) {
            n = JOptionPane.showOptionDialog(new Frame(), "Please select", "Rock, Paper, Scissors",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options);
        }

        if (n == 0) {
            newPlayer();
        }
    }

    /**
     * Shows pop-up file browser. This method is for choosing a player profile. 
     * @throws HeadlessException 
     */
    private void showFileBrowser() throws HeadlessException {
        this.gameMode = GameMode.PLAYER_VS_BOT;
        JFileChooser fc = new JFileChooser("profiles/");
        fc.setFileFilter(new FileNameExtensionFilter("Player profile", "player"));
        fc.showOpenDialog(null);
        file = fc.getSelectedFile();
    }
}