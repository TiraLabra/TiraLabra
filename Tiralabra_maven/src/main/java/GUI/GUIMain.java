package GUI;

/**
 *
 * @author alpa
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;

/**
 * The Graphical User Interface of the program for ease-of-use
 */
public class GUIMain extends JFrame {

    /*
     * Constructor, sets the specifications of the JFrame, as well
     * as initiates the JFileChooser
     */
    public GUIMain() {

        setTitle("GUI");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*
         * Creates the buttons required and assigns Action Listeners to them
         */
        ActionListener browseListener = new ButtonActionListener();

        JButton browseButton = new JButton("Browse");
        browseButton.setActionCommand("BROWSE");
        browseButton.addActionListener(browseListener);
        browseButton.setVisible(true);
        this.add(browseButton);

        JButton compressButton = new JButton("Compress");
        compressButton.setActionCommand("COMPRESS");
        compressButton.addActionListener(browseListener);
        compressButton.setVisible(true);
    }

    public void main() {
    }
}
