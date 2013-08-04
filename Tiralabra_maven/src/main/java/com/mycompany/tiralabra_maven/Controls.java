package com.mycompany.tiralabra_maven;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;

/**
 *
 * @author Joel Nummelin
 */
public class Controls implements ActionListener {

    private Game game;
    private JTextArea textArea;

    public Controls(Game game, JTextArea textArea) {
        this.game = game;
        this.textArea = textArea;
        this.textArea.setEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Rock":
                rock();
                break;
            case "Paper":
                paper();
                break;
            case "Scissors":
                scissors();
                break;
            case "...":
                options();
        }
    }

    private void rock() {
        report(game.playRound(0));
    }

    private void paper() {
        report(game.playRound(1));
    }

    private void scissors() {
        report(game.playRound(2));
    }

    private void options() {
    }

    private void report(int result) {
        String text = "";
        if (result == 0){
            text += "Draw \nTotal draw percent: " + game.getStatistics().getTotalDrawPerCent()
                    + " \nSession draw percent: " + game.getStatistics().getSessionDrawPerCent();
        } else if (result == 1){
            text += "Bot1 won \nTotal bot1 win percent: " + game.getStatistics().getTotalWinPerCent()
                    + " \nSession win percent: " + game.getStatistics().getSessionWinPerCent();
        } else if (result == -1){
            text += "Bot1 lost \nTotal bot1 lose percent: " + (100.0 - (game.getStatistics().getTotalDrawPerCent() + game.getStatistics().getTotalWinPerCent()))
                    + " \nSession lose percent: " + (100.0 - (game.getStatistics().getSessionDrawPerCent() + game.getStatistics().getSessionWinPerCent()));
        }
        text += "\n\n";
        textArea.insert(text, 0);
    }
}