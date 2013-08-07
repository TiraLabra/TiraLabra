/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author albis
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    
    public Kayttoliittyma() {
    }
    
    @Override
    public void run() {
        frame = new JFrame("Kontinpurkaja 2000");
        frame.setPreferredSize(null);
        
        Dimension koko = Toolkit.getDefaultToolkit().getScreenSize();
        
        frame.setPreferredSize(new Dimension((int) koko.getWidth() / 2, (int) koko.getHeight() / 2));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        
    }
}
