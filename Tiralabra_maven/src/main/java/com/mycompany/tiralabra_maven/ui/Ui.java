package com.mycompany.tiralabra_maven.ui;

import com.mycompany.tiralabra_maven.Game;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        frame.setPreferredSize(new Dimension(500 , 300));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            createComponents(frame);
        } catch (IOException ex) {
            Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.pack();
        frame.setVisible(true);
    }
    
    public void createComponents(Container container) throws IOException{
        BorderLayout layout = new BorderLayout();
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
        
        container.add(textArea, BorderLayout.CENTER);
        
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(200, 100));
        label.setLayout(new GridLayout(1, 4));
        
        label.add(rockButton);
        label.add(paperButton);
        label.add(scissorsButton);
        label.add(optionsButton);
        
        container.add(label, BorderLayout.SOUTH);
        
        
        
    }
}
