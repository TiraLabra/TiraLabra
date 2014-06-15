package Hike.gamewindow;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;

/**
 * Game window, sets size etc.
 * @author petri
 */
public class MenuWindow implements Runnable {

    private JFrame frame;
    private MenuDraw menu;

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
        this.menu = new MenuDraw();
        container.add(menu);
    }



    public MenuWindow getGameWindow() {
        return this;
    }
}
