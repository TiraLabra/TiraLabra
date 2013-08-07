package GUI;

/**
 *
 * @author alpa
 */
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

/**
 * The Graphical User Interface of the program for ease-of-use
 */
public class GUIMain extends JFrame {

    /*
     * Constructor for the class, sets up the JFrame and calls for
     * prepareUI() method
     */
    public GUIMain() {

        /*
         * Sets up the JFrame
         */
        setTitle("GUI");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);

        /*
         * Initiates the user interface by calling prepareUI()
         * method
         */
        prepareUI();
    }

    /*
     * 
     */
    public void prepareUI() {

        /*
         *Creates and sets up the JPanel that holds the components for the UI
         */
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        panel.setLayout(null);

        /*
         * Creates the buttons required
         */
        JButton browseButton = new JButton("Browse");
        browseButton.setActionCommand("BROWSE");
        browseButton.setBounds(60, 50, 100, 60);
        browseButton.setVisible(true);

        JButton compressButton = new JButton("Compress");
        compressButton.setActionCommand("COMPRESS");
        compressButton.setVisible(false);

        JButton exitButton = new JButton("Exit");
        exitButton.setActionCommand("KILL");
        exitButton.setBounds(60, 270, 100, 60);
        exitButton.setVisible(true);

        /*
         * Creates the ActionListener and adds buttons to it
         */
        ActionListener buttonListener = new ButtonActionListener();
        browseButton.addActionListener(buttonListener);
        compressButton.addActionListener(buttonListener);
        exitButton.addActionListener(buttonListener);

        /*
         * adds all buttons to JPanel panel
         */
        panel.add(browseButton);
        panel.add(compressButton);
        panel.add(exitButton);
    }
}
