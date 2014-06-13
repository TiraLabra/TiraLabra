package Hike.controls;

import Hike.Values;
import Hike.gameWindow.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listens to mouse clicks.
 *
 */
public class ClickListener implements ActionListener {

    private GameScreen screen;

    public ClickListener(GameScreen a) {
        screen = a;

    }

    public enum Actions {

        STARTGAME,
        SAVESCORE,
        DIJKSTRA,
        CHEBYSHEV,
        MANHATTAN,
        DRAWSEARCHED, DIAGONAL
    }

    public ClickListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == Actions.DIJKSTRA.name()) {
            Values.setHeuristic("Dijkstra");
            screen.openMap();

        }
        if (e.getActionCommand() == Actions.CHEBYSHEV.name()) {
            Values.setHeuristic("Chebyshev");
            screen.openMap();

        }
        if (e.getActionCommand() == Actions.MANHATTAN.name()) {
            Values.setHeuristic("Manhattan");
            screen.openMap();

        }
        if (e.getActionCommand() == Actions.DRAWSEARCHED.name()) {
            Values.setShowSearched();

        }

        if (e.getActionCommand() == Actions.DIAGONAL.name()) {
            Values.setDiagonal();

        }
    }
}