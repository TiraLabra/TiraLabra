package Hike.gameWindow;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Game window, sets size etc.
 * @author petri
 */
public class GameWindow implements Runnable {

    private JFrame frame;
    private GameScreen menu;

    @Override
    public void run() {
        frame = new JFrame("Hike");
        frame.setPreferredSize(new Dimension(900, 600));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());



        frame.pack();
        frame.setVisible(true);




    }


    private void createComponents(Container container) {
        this.menu = new GameScreen();
        container.add(menu);
    }



    public GameWindow getGameWindow() {
        return this;
    }
}
