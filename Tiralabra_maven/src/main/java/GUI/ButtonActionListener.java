package GUI;

/**
 *
 * @author alpa
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;

import ProgramLogic.AssistingFunctions.FileManager;
/*
 * The implementation of ActionListener used with the project's
 * graphical user interface
 */

public class ButtonActionListener implements ActionListener {
    
    FileManager manager;
    
    public ButtonActionListener() {
        this.manager = new FileManager();
    }

    /*
     * The action events that occur when a JButton in GUIMain is pressed
     */
    public void actionPerformed(ActionEvent e) {
        
        String cmd = e.getActionCommand();
        
        if (cmd.equals("BROWSE")) {
            manager.setFile();
        } else if (cmd.equals("KILL")) {
            System.exit(0);
        } else {
            System.out.println("undefined");
        }
    }
}
