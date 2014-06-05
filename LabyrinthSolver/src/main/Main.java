package main;

import gui.Gui;
import javax.swing.SwingUtilities;

/**
 * Tietorakenteet ja algoritmit harjoitustyö. Kesä 2014.
 *
 * @author Juri Kuronen
 */
public class Main {

    public static Labyrinth l;


    public static void main(String[] args) throws Exception {
        l = new Labyrinth(85, 95);
        Gui gui = new Gui(l);
        SwingUtilities.invokeLater(gui);
    }
}
