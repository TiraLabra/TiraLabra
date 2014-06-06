package main;

import java.text.DecimalFormat;
import labyrinthgenerator.*;
import labyrinthsolver.*;

/**
 * Suorityskykytestailua.
 *
 * @author Juri Kuronen
 */
public class RunTimeTesting {

    /**
     * Labyrintti, jolle testit ajetaan.
     */
    public Labyrinth labyrinth;

    /**
     * Luo alustavan labyrintti-olion.
     */
    public RunTimeTesting() {
        labyrinth = new Labyrinth(10, 10);
    }

    /**
     * Generoi labyrintin eri ko'oilla, ja ratkaisee jokaisen generoidun
     * labyrintin eri ratkaisualgoritmilla. Kunkin tulostetaan.
     *
     * @throws java.lang.Exception Labyrintti-luokka heittää poikkeuksen, jos
     * algoritmi yrittää käsitellä labyrintin ulkopuolista koordinaattia.
     */
    public void runTests() throws Exception {
        LabyrinthGenerator[] generators = getGenerators();
        LabyrinthSolver[] solvers = getSolvers();
        int[] tests = getTestSizes();
        for (Integer t : tests) {
            labyrinth.updateLabyrinth(t, t);
            for (LabyrinthGenerator lg : generators) {
                System.out.println("- - - - - - - - - - - - - - - - - - - - ");
                labyrinth.setLabyrinthGenerator(lg);
                System.out.print(lg.getName());
                lgPrintRoutine();
                System.out.println("");
                for (LabyrinthSolver ls : solvers) {
                    labyrinth.setLabyrinthSolver(ls);
                    System.out.print(ls.getName());
                    lsPrintRoutine();
                }
                System.out.println("");
            }
        }
    }

    LabyrinthGenerator[] getGenerators() {
        LabyrinthGenerator[] lgs = new LabyrinthGenerator[3];
        lgs[0] = new PrimsAlgorithm();
        lgs[1] = new KruskalsAlgorithm();
        lgs[2] = new RecursiveBacktracker();
        return lgs;
    }

    LabyrinthSolver[] getSolvers() {
        LabyrinthSolver[] lss = new LabyrinthSolver[3];
        lss[0] = new DFS();
        lss[1] = new BFS();
        lss[2] = new WallFollower();
        return lss;
    }

    int[] getTestSizes() {
        int[] sizes = {10, 25, 50, 100, 250, 500, 1000, 2000};
        return sizes;
    }

    /**
     * Tulostusrutiini labyrintingeneroimistesteille.
     *
     * @throws java.lang.Exception Labyrintti-luokka heittää poikkeuksen, jos
     * algoritmi yrittää käsitellä labyrintin ulkopuolista koordinaattia.
     */
    void lgPrintRoutine() throws Exception {
        System.out.println(" (" + labyrinth.width + "x" + labyrinth.height + ")");
        labyrinth.lg.createEmptyLabyrinthIfNeeded();
        long startTime = System.nanoTime() / 1000;
        labyrinth.lg.generateLabyrinth();
        long finishTime = System.nanoTime() / 1000;
        String timeFormat = formatTime(finishTime - startTime);
        System.out.println("Generation time: " + timeFormat);
    }

    /**
     * Tulostusrutiini labyrintinratkomistesteille.
     */
    void lsPrintRoutine() {
        long startTime = System.nanoTime() / 1000;
        boolean solved = labyrinth.ls.solveLabyrinth();
        long finishTime = System.nanoTime() / 1000;
        String timeFormat = formatTime(finishTime - startTime);
        if (solved) {
            System.out.print("  ::  Solution found in " + timeFormat);
            int exploredCells = labyrinth.ls.getExploredCells();
            String exploredCellsFormat = formatNumber(exploredCells);
            System.out.println("  ::  Cells explored: " + exploredCellsFormat);
        } else {
            System.out.println("TIMEOUT LIMIT EXCEEDED (" + timeFormat + ")");
        }
    }

    /**
     * Formatoi ajan. Millisekuntiosa formatoidaan muotoon "### " + ... "###",
     * ja mikrosekuntiosa muotoon ",### ms".
     *
     * @param time Aika joka halutaan formatoida.
     * @return Palauttaa formatoidun ajan.
     * @see formatNumber
     */
    String formatTime(long time) {
        DecimalFormat df = new DecimalFormat("000");
        return formatNumber(time / 1000) + "," + df.format(time % 1000) + " ms";
    }

    /**
     * Formatoi luvun muotoon "### " + "### " + ... + "###".
     *
     * @param number Luku joka halutaan formatoida.
     * @return Palauttaa formatoidun luvun.
     */
    String formatNumber(long number) {
        DecimalFormat df = new DecimalFormat("000");
        if (number / 1000 == 0) {
            return number + "";
        }
        String numberFormat = df.format(number % 1000);
        number /= 1000;
        while (number / 1000 != 0) {
            numberFormat = df.format(number % 1000) + " " + numberFormat;
            number /= 1000;
        }
        return number + " " + numberFormat;
    }

}
