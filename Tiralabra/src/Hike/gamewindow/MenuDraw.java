package Hike.gamewindow;

import Hike.Values;
import Hike.controls.ClickListener;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.io.File;


import javax.swing.*;

/**
 * Shows the welcome screen
 *
 */
public class MenuDraw extends JPanel {

    private ActionListener listener;
    private MapWindow map;

    public MenuDraw() {

        setLayout(null);

        JTextArea teksti = new JTextArea("Finds the shortest route from top left to bottom right. Diagonal moves are only slightly more expensive, "
                + "costing 1.4 times the node's terrain difficulty! This leads to diagonal routes being shorter."
                + " For example, moving on grass or unset terrain diagonally costs 1.4*"
                + Values.UNSETGROUND + ", but moving into a mountain diagonally will cost 1.4*" + Values.MOUNTAIN
                + ".\n\nPlease note! When diagonal movement is enabled, the results often are longer than the best possible route."
                + "\n\n- Dijkstra search is slow but is guaranteed to find shortest route. "
                + "\n- Diagonal search is fast with diagonals enabled, but the route it finds might be slightly longer."
                + "\n- Manhattan search is always fast, but with diagonals enabled it might"
                + " find a route that is much longer."
                + "\n\n- Green or white are easy to pass. Yellow is desert and harder to pass. "
                + "Blue is water and it is very hard to pass. Black are mountains, and they are nearly impossible to pass,"
                + " the route search should use them only when no other routes exist.");

        teksti.setWrapStyleWord(true);
        teksti.setLineWrap(true);
        teksti.setBounds(50, 20, 220, 530);

        JButton Dijkstra = new JButton("Dijkstra");
        listener = new ClickListener(this);
        Dijkstra.setActionCommand(ClickListener.Actions.DIJKSTRA.name());
        Dijkstra.setBounds(600, 60, 100, 50);
        Dijkstra.addActionListener(listener);

        JButton Chebyshev = new JButton("Diagonal");
        Chebyshev.setActionCommand(ClickListener.Actions.DIAGONALSEARCH.name());
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

        JCheckBox diagonal = new JCheckBox("Enable diagonals");
        diagonal.addActionListener(listener);
        diagonal.setActionCommand(ClickListener.Actions.DIAGONAL.name());
        diagonal.setBounds(300, 100, 200, 50);
        add(diagonal);


    }

    /**
     * Open the map screen to show path.
     *
     */
    public void openMap() {
        map = new MapWindow();
        map.run();
    }
}
