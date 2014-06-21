package main;

import gui.Gui;
import javax.swing.SwingUtilities;

/**
 * Tietorakenteet ja algoritmit harjoitustyö. Kesä 2014.
 *
 * @author Juri Kuronen
 */
public class Main {

    /**
     * Ajaa suoritusaikatestejä labyrintin ratkoja- ja generointialgoritmeille.
     *
     * @throws java.lang.Exception Labyrintti-luokka heittää poikkeuksen, jos
     * algoritmi yrittää käsitellä labyrintin ulkopuolista koordinaattia.
     */
    public static void runTests() throws Exception {
        RunTimeTesting rtt = new RunTimeTesting();
        rtt.runTests();
    }

    /**
     * Luo alustavan labyrintin ja käynnistää guin
     *
     * @param args Komentorivin argumentit.
     * @throws java.lang.Exception Labyrintti-luokka heittää poikkeuksen, jos
     * algoritmi yrittää käsitellä labyrintin ulkopuolista koordinaattia.
     */
    public static void main(String[] args) throws Exception {
        Labyrinth l = new Labyrinth(50, 50);
        Gui gui = new Gui(l);
        SwingUtilities.invokeLater(gui);
    }
}
