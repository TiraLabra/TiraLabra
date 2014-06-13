package Hike.gameWindow;

import Hike.controls.ClickListener;
import java.awt.Color;

import java.awt.event.ActionListener;


import javax.swing.*;

/**
 * Shows the welcome screen
 *
 */
public class GameScreen extends JPanel {

    private ActionListener listener;
    private MapGraphic map;

    public GameScreen() {

        setLayout(null);

        JTextArea teksti = new JTextArea("Finds the shortest route in a drawn image. Diagonal routes are not penalized, "
                + "for example going right cost the same amount as going diagonally down and right. Just like in a chess board.\n "
                + "\n- Dijkstra is slow but is guaranteed to find shortest route. "
                + "\n- Chebyshev is fast with diagonals allowed."
                + "\n- Manhattan is fast, but with diagonals allowed it might"
                + " find a route that is too long."
                + "\n\n- Green or white are easy to pass. Yellow is desert and harder to pass. "
                + "Blue is water and it is very hard to pass. Black are mountains, and they are nearly impossible to pass,"
                + " the route search should use them only when no other routes exist.");

        teksti.setWrapStyleWord(true);
        teksti.setLineWrap(true);
        teksti.setBounds(50, 50, 200, 400);

        JButton Dijkstra = new JButton("Dijkstra");
        listener = new ClickListener(this);
        Dijkstra.setActionCommand(ClickListener.Actions.DIJKSTRA.name());
        Dijkstra.setBounds(600, 60, 100, 50);
        Dijkstra.addActionListener(listener);

        JButton Chebyshev = new JButton("Chebyshev");
        Chebyshev.setActionCommand(ClickListener.Actions.CHEBYSHEV.name());
        Chebyshev.setBounds(600, 120, 100, 50);
        Chebyshev.addActionListener(listener);

        JButton Manhattan = new JButton("Manhattan");
        Manhattan.setActionCommand(ClickListener.Actions.MANHATTAN.name());
        Manhattan.setBounds(600, 180, 100, 50);
        Manhattan.addActionListener(listener);

        add(teksti);
        add(Dijkstra);
        add(Chebyshev);
        add(Manhattan);

        JCheckBox checkedAreas = new JCheckBox("Show searched area");
        checkedAreas.addActionListener(listener);
        checkedAreas.setActionCommand(ClickListener.Actions.DRAWSEARCHED.name());
        checkedAreas.setBounds(300, 50, 200, 50);
        add(checkedAreas);

        JCheckBox diagonal = new JCheckBox("Disable diagonals");
        diagonal.addActionListener(listener);
        diagonal.setActionCommand(ClickListener.Actions.DIAGONAL.name());
        diagonal.setBounds(300, 100, 200, 50);
        add(diagonal);

// Image is out of date.
//        helpfulImage = new ShowPicture("../help.png");
//        picture.setBounds(200, 200, 500, 300);
//        add(helpfulImage);

    }

    /**
     * Open the map screen to show path.
     *
     */
    public void openMap() {
        map = new MapGraphic();
        map.run();
    }
}
