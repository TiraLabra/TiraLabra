package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.player.Bot;
import com.mycompany.tiralabra_maven.player.Player;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author joel Nummelin
 */
public class Game {

    private GameMode gameMode;
    private Player player;
    private Bot primaryBot;
    private Bot secondaryBot;
    private int[][] results;
    private Statistics statistics;
    private int lastRound;

    public Game() {
        setUpResultTable();
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
        this.primaryBot = new Bot();
        this.statistics = new Statistics(0, 0, 0);
    }

    private void playVsBot() {
        int n = -1;
        Object[] options = {"New player", "Existing player"};
        while (n == -1) {
            n = JOptionPane.showOptionDialog(new Frame(), "Please select", "Rock, Paper, Scissors",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options);
        }

        File file = null;

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
        this.player = new Player(file);
    }

    private void asGuest() {
        this.gameMode = GameMode.GUEST_VS_BOT;
        this.player = new Player();
    }

    private void botVsBot() {
        this.gameMode = GameMode.BOT_VS_BOT;
        this.secondaryBot = new Bot();
    }

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

    public int playRound(int rockPaperScissors) {
        if (gameMode == GameMode.BOT_VS_BOT) {
            int primary = primaryBot.makeAMove();
            int secondary = secondaryBot.makeAMove();
            primaryBot.upDateAi(results[primary][secondary]);
            secondaryBot.upDateAi(results[secondary][primary]);
            lastRound = results[primary][secondary];
            return lastRound;
        }
        int primary = primaryBot.makeAMove();
        lastRound = results[primary][rockPaperScissors];
        primaryBot.upDateAi(lastRound);
        return results[primary][rockPaperScissors];
    }

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

    public GameMode getGameMode() {
        return gameMode;
    }

    public Statistics getStatistics() {
        return statistics;
    }
    
    
}