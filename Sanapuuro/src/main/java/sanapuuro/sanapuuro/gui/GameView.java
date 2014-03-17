/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * The main game view presenting the grid, selected letters, score,
 * time and letter pool.
 * @author skaipio
 */
public class GameView extends JPanel {

    public final TimeLabel time;
    public final ScoreLabel score;
    public final SelectedLettersLabel selectedLetters;
    public final LetterGridPanel letterGrid;
    public final LetterPoolPanel letterPool;
    public final JLabel state;
    public final JLabel gameOverMessage;

    private final JPanel mainGameView = new JPanel();
    private final JPanel gameOverView = new JPanel();

    public GameView() {
        this.setBackground(Color.black);
        this.setFocusable(true);

        // Init main game view
        mainGameView.setLayout(new GridBagLayout());
        mainGameView.setBackground(GUISettings.getColorBackground());

        GridBagConstraints constraints = new GridBagConstraints();

        JPanel statusPanel = new JPanel(new GridLayout(1, 2));
        statusPanel.setBackground(GUISettings.getColorBackground());
        statusPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.time = new TimeLabel();
        statusPanel.add(this.time);

        this.score = new ScoreLabel();
        statusPanel.add(this.score);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainGameView.add(statusPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        mainGameView.add(Box.createVerticalStrut(5), constraints);

        JPanel submissionPanel = new JPanel();
        submissionPanel.setLayout(new BoxLayout(submissionPanel, BoxLayout.LINE_AXIS));
        submissionPanel.setBackground(GUISettings.getColorBackground());

        this.selectedLetters = new SelectedLettersLabel();
        JPanel selectedLettersPanel = new JPanel();
        selectedLettersPanel.setBorder(BorderFactory.createLineBorder(GUISettings.getColorBorder()));
        selectedLettersPanel.setBackground(GUISettings.getColorLetterPoolCell());
        int width = 200, height = 40;
        selectedLettersPanel.setPreferredSize(new Dimension(width, height));
        selectedLettersPanel.add(this.selectedLetters);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        mainGameView.add(selectedLettersPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        mainGameView.add(Box.createVerticalStrut(5), constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        int fillerWidth = 50;
        mainGameView.add(Box.createHorizontalStrut(fillerWidth), constraints);

        this.letterGrid = new LetterGridPanel(8, 8);
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        mainGameView.add(this.letterGrid, constraints);

        constraints.gridx = 2;
        mainGameView.add(Box.createHorizontalStrut(fillerWidth), constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.NONE;
        mainGameView.add(Box.createVerticalStrut(5), constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        this.letterPool = new LetterPoolPanel();
        mainGameView.add(this.letterPool, constraints);

        JPanel infoPanel = new JPanel(new BorderLayout(0, 0));
        infoPanel.setBackground(GUISettings.getColorBackground());
        this.state = new JLabel("Add letters to grid to form a word.");
        this.state.setFont(GUISettings.getMediumFont());
        infoPanel.add(this.state, BorderLayout.NORTH);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 3;
        mainGameView.add(infoPanel, constraints);

        this.add(mainGameView);

        // init game over view
        this.gameOverView.setBackground(Color.black);
        this.gameOverView.setPreferredSize(this.getPreferredSize());
        //this.gameOverView.setLayout(new GridBagLayout());
        
        JPanel messageContainer = new JPanel();
        messageContainer.setLayout(new GridBagLayout());
        messageContainer.setBackground(Color.black);
        
        JLabel message = new JLabel();
        message.setForeground(Color.WHITE);
        message.setFont(GUISettings.getLargeFont());
        message.setText("Game Over");
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        messageContainer.add(message, constraints);

        this.gameOverMessage = new JLabel();
        this.gameOverMessage.setForeground(Color.WHITE);
        this.gameOverMessage.setFont(GUISettings.getMediumFont());
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridy = 1;
        messageContainer.add(gameOverMessage, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        this.gameOverView.add(messageContainer);

        this.add(gameOverView);
        this.gameOverView.setVisible(false);
    }

    public void showGameOverView() {
        this.mainGameView.setVisible(false);
        this.gameOverView.setVisible(true);
    }
}
