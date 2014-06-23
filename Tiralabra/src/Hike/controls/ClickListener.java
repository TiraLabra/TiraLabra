package Hike.controls;

import Hike.gamewindow.MenuDraw;
import Hike.Values;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listens to mouse clicks.
 *
 */
public class ClickListener implements ActionListener {

    private MenuDraw screen;

    public ClickListener(MenuDraw a) {
        screen = a;

    }

    public enum Actions {

        STARTGAME,
        SAVESCORE,
        DIJKSTRA,
        DIAGONALSEARCH,
        MANHATTAN,
        DRAWSEARCHED,
        DIAGONAL,
    }

    public ClickListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(Actions.DIJKSTRA.name())) {
            Values.setHeuristic("Dijkstra");
            screen.openMap();

        }
        if (e.getActionCommand().equals(Actions.DIAGONALSEARCH.name())) {
            Values.setHeuristic("Diagonalsearch");
            screen.openMap();

        }
        if (e.getActionCommand().equals(Actions.MANHATTAN.name())) {
            Values.setHeuristic("Manhattan");
            screen.openMap();

        }
        if (e.getActionCommand().equals(Actions.DRAWSEARCHED.name())) {
            Values.setShowSearched();

        }

        if (e.getActionCommand().equals(Actions.DIAGONAL.name())) {
            Values.setDiagonal();

        }

    }
}