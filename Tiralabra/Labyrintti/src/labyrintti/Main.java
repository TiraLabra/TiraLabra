/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti;

import labyrintti.gui.LabyrinthGUI;
import labyrintti.logiikka.LyhinReitti;
import labyrintti.logiikka.Maapala;
import labyrintti.logiikka.Maapalarekisteri;
import labyrintti.tietorakenteet.LinkitettyLista;

/**
 *
 * @author Mikael Parvamo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LabyrinthGUI kayttis = new LabyrinthGUI(20);
        kayttis.run();
    }
}
