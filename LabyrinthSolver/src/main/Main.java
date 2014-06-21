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
     * @param n Joka testi ko'olle ajettavien testien lukumäärä.
     * @throws java.lang.Exception Labyrintti-luokka heittää poikkeuksen, jos
     * algoritmi yrittää käsitellä labyrintin ulkopuolista koordinaattia.
     */
    public static void runTests(int n) throws Exception {
        RunTimeTesting rtt = new RunTimeTesting(n);
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
        if (args.length > 0) {
            if (args.length == 2) {
                if (args[0].equals("runtests")) {
                    try {
                        int n = Integer.parseInt(args[1]);
                        if (n > 0 && n <= 100) {
                            runTests(n);
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
            return;
        }
        Labyrinth l = new Labyrinth(50, 50);
        Gui gui = new Gui(l);
        SwingUtilities.invokeLater(gui);
    }
}
