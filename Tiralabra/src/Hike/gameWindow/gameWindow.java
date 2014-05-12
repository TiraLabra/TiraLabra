

package Hike.gameWindow;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;


public class gameWindow extends JPanel {
    
    private JFrame frame;
    private startMenu menu;

    
        public void run() {

        frame = new JFrame("Hike");
        frame.setPreferredSize(new Dimension(900,600));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        System.out.println("");
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);


    }

    private void createComponents(Container container) {
        this.menu = new startMenu();
        
        container.add(menu);
        
        
    }
    
}
