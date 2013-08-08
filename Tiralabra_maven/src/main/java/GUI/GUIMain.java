package GUI;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Graphical User Interface of the program for ease-of-use
 */
public class GUIMain extends JFrame {

    JPanel panel;

    /**
     * Constructor for the class, sets up the JFrame and calls for
     * prepareUI() method
     */
    public GUIMain() {

        /**
         * Sets up the JFrame
         */
        setTitle("GUI");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);

        /**
         * Initiates the user interface by calling prepareUI()
         * method
         */
        prepareUI();

        /**
         * Calls for the method which initialises the buttons 
         */
        createButtons();
    }

    /**
     * Creates the JPanel which hosts the rest of the GUI components
     */
    private void prepareUI() {

        /**
         *Creates and sets up the JPanel that holds the components for the UI
         */
        panel = new JPanel();
        getContentPane().add(panel);

        panel.setLayout(null);
    }

    /**
     * Creates the buttons required, as well as the action
     */
    private void createButtons() {
        JButton browseButton = new JButton("Browse");
        browseButton.setActionCommand("BROWSE");
        browseButton.setBounds(60, 50, 120, 60);
        browseButton.setVisible(true);

        JButton compressButton = new JButton("Compress");
        compressButton.setActionCommand("COMPRESS");
        compressButton.setBounds(200, 50, 120, 60);
        compressButton.setVisible(true);

        JButton exitButton = new JButton("Exit");
        exitButton.setActionCommand("KILL");
        exitButton.setBounds(60, 270, 120, 60);
        exitButton.setVisible(true);
        /**
         * Creates the ActionListener and adds buttons to it
         */
        ActionListener buttonListener = new ButtonActionListener();
        browseButton.addActionListener(buttonListener);
        compressButton.addActionListener(buttonListener);
        exitButton.addActionListener(buttonListener);

        /**
         * adds all buttons to JPanel panel
         */
        panel.add(browseButton);
        panel.add(compressButton);
        panel.add(exitButton);
    }

    /**
     * Redraws the screen when an event that requires it occurs
     */
    private void redrawScreen() {
    }
}
