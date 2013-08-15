package GUI;

import ProgramLogic.AssistingFunctions.FileManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The implementation of ActionListener used with the project's
 * graphical user interface
 */
public class ButtonActionListener implements ActionListener {

    FileManager manager;

    /**
     * Constructor, initialises FileManager that handles the
     * file compression and decompression commands
     */
    public ButtonActionListener() {
        this.manager = new FileManager();
    }

    /**
     * The action events that occur when a JButton in GUIMain is pressed
     */
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        if (cmd.equals("BROWSE")) {
            System.out.println("not implemented yet!");
        } else if (cmd.equals("COMPRESS")) {
            System.out.println("work in progress!");
        } else if (cmd.equals("KILL")) {
            System.exit(0);
        } else {
            System.out.println("undefined");
        }
    }
}
