
package GUI;

/**
 *
 * @author alpa
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;

/*
 * The implementation of ActionListener to be used with the project's
 * graphical user interface
 */
public class ButtonActionListener implements ActionListener {

    JFileChooser fileChooser = new JFileChooser();

    /*
     * The action events that occur when a JButton in GUIMain is pressed
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("BROWSE")) {
            System.out.println("Hello World?");

        } else {
            System.out.println("undefined");
        }
    }
}
