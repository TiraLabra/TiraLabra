package com.mycompany.tiralabra_maven.ui;

import com.mycompany.tiralabra_maven.Game;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 *
 * @author Joel Nummelin
 */
public class Ui implements Runnable{
    private JFrame frame;
    private Game game;

    @Override
    public void run() {
        frame = new JFrame("Rock, Paper, Scissors");
        frame.setPreferredSize(new Dimension(500 , 200));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void createComponents(Container container){
        GridLayout layout = new GridLayout(2, 1);
        container.setLayout(layout);
        
        this.game = new Game();
        JTextArea textArea = new JTextArea();
        Controls controls = new Controls(game, textArea);
        
        JButton rockButton = new JButton("Rock");
        rockButton.addActionListener(controls);
        JButton paperButton = new JButton("Paper");
        paperButton.addActionListener(controls);
        JButton scissorsButton = new JButton("Scissors");
        scissorsButton.addActionListener(controls);
        JButton optionsButton = new JButton("...");
        optionsButton.addActionListener(controls);
        
        container.add(textArea);
        
        JLabel label = new JLabel();
        label.setLayout(new GridLayout(1, 4));
        
        label.add(rockButton);
        label.add(paperButton);
        label.add(scissorsButton);
        label.add(optionsButton);
        
        container.add(label);
        
        
        
    }
}
