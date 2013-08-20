/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author virta
 */
public class UIscreen implements Runnable {
    
    private JFrame frame;
    
    public UIscreen(){
        
    }
    
    @Override
    public void run(){
        this.frame = new JFrame("vZipper");
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(500, 500));
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void createComponents(Container container){
        
    }
    
}
