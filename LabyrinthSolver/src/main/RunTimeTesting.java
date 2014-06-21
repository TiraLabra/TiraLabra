package main;

import java.text.DecimalFormat;
import labyrinthgenerator.*;
import labyrinthsolver.*;

/**
 * Luokka suorituskykytesteille.
 *
 * @author Juri Kuronen
 */
public class RunTimeTesting {

    /**
     * Labyrintti-olio.
     */
    private final Labyrinth labyrinth;
    /**
     * Kuinka monta kertaa testit ajetaan kullekin koolle.
     */
    private final int n;
    /**
     * Labyrintin generoijat.
     */
    private final LabyrinthGenerator[] generators;
    /**
     * Labyrintin ratkojat.
     */
    private final LabyrinthSolver[] solvers;
    /**
     * Testikoot.
     */
    private final int[] tests;

    /**
     * Luo alustavan labyrintti-olion ja asettaa asetukset.
     */
    public RunTimeTesting() {
        labyrinth = new Labyrinth(10, 10);
        n = 50;
        generators = getGenerators();
        solvers = getSolvers();
        tests = getTestSizes();
    }

    /**
     * Generoi labyrintin eri ko'oilla, ja ratkaisee jokaisen generoidun
     * labyrintin eri ratkaisualgoritmilla. Kunkin tulostetaan.
     *
     * @throws java.lang.Exception Labyrintti-luokka heittää poikkeuksen, jos
     * algoritmi yrittää käsitellä labyrintin ulkopuolista koordinaattia.
     */
    public void runTests() throws Exception {
        WallFollower wf = new WallFollower();
        int sLength = solvers.length;
        /*
         Testit ajetaan kaikille testiko'oille.
         */
        for (Integer t : tests) {
            labyrinth.updateLabyrinth(t, t);
            /*
             Joka testi ajetaan kaikille generoijille.
             */
            for (LabyrinthGenerator lg : generators) {
                System.out.println("- - - - - - - - - - - - - - - - - - - - ");
                labyrinth.setLabyrinthGenerator(lg);
                System.out.print(lg.getName());
                long generationTime = 0;
                long[] solvingTimes = new long[sLength + 1];
                long[] exploredCells = new long[sLength + 1];
                /*
                 Testejä ajetaan n kappaletta.
                 */
                for (int i = 0; i < n; i++) {
                    generationTime += generateAndGetTime();
                    /*
                     Joka generoitu labyrintti ratkotaan joka ratkojalla.
                     */
                    for (int j = 0; j < sLength; j++) {
                        labyrinth.setLabyrinthSolver(solvers[j]);
                        solvingTimes[j] += solveAndGetTime();
                        exploredCells[j] += labyrinth.ls.getExploredCells();
                    }
                    labyrinth.setLabyrinthSolver(wf);
                    solvingTimes[sLength] += solveAndGetTime(wf);
                }
                /*
                 Tulostetaan tämän testiko'on tulokset tälle generointialgoritmille.
                 */
                printResults(generationTime, solvingTimes, exploredCells, sLength);
            }
        }
    }

    /**
     * Tulostaa yhden testikoon tulokset yhdelle generointialgoritmille.
     *
     * @param generationTime Generointeihin kulunut aika.
     * @param solvingTimes Ratkomisiin kulunut aika.
     * @param exploredCells Vieraillut solut.
     * @param sLength Ratkoja-algoritmien määrä.
     * @throws java.lang.Exception Labyrintti-luokka heittää poikkeuksen, jos
     * algoritmi yrittää käsitellä labyrintin ulkopuolista koordinaattia.
     */
    private void printResults(long generationTime, long[] solvingTimes, long[] exploredCells, int sLength) throws Exception {
        lgPrintRoutine(generationTime);
        System.out.println("");
        for (int j = 0; j < sLength; j++) {
            System.out.print(solvers[j].getName());
            lsPrintRoutine(solvingTimes[j], exploredCells[j]);
        }
        System.out.print("Wall follower (Optimized)");
        lsPrintRoutine(solvingTimes[sLength], exploredCells[sLength - 1]);
    }

    /**
     * Labyrintin generoijat arrayssä.
     *
     * @return Palauttaa labyrintin generoijat arrayssä.
     */
    private LabyrinthGenerator[] getGenerators() {
        LabyrinthGenerator[] lgs = new LabyrinthGenerator[3];
        lgs[0] = new PrimsAlgorithm();
        lgs[1] = new KruskalsAlgorithm();
        lgs[2] = new RecursiveBacktracker();
        return lgs;
    }

    /**
     * Labyrintin ratkojat arrayssä.
     *
     * @return Palauttaa labyrintin ratkojat arrayssä.
     */
    private LabyrinthSolver[] getSolvers() {
        LabyrinthSolver[] lss = new LabyrinthSolver[4];
        lss[0] = new DFS();
        lss[1] = new BFS();
        lss[2] = new AStar();
        lss[3] = new WallFollower();
        return lss;
    }

    /**
     * Testikoot arrayssa.
     *
     * @return Palauttaa testikoot arrayssa.
     */
    private int[] getTestSizes() {
        int[] sizes = {10, 25, 50, 100, 250, 500};
        return sizes;
    }

    /**
     * Tulostusrutiini labyrintingeneroimistesteille.
     *
     * @throws java.lang.Exception Labyrintti-luokka heittää poikkeuksen, jos
     * algoritmi yrittää käsitellä labyrintin ulkopuolista koordinaattia.
     */
    private void lgPrintRoutine(long time) throws Exception {
        System.out.print(" (" + labyrinth.getWidth() + "x" + labyrinth.getHeight() + ")");
        time /= n; // Keskiarvo.
        time /= 1000; // Muunnos mikrosekunneiksi.
        String timeFormat = formatTime(time);
        System.out.println("  ::  Average generation time (n=" + n + "): " + timeFormat);
    }

    /**
     * Tulostusrutiini labyrintinratkomistesteille.
     */
    private void lsPrintRoutine(long time, long exploredCells) {
        exploredCells /= n; // Keskiarvo.
        time /= n; // Keskiarvo.
        time /= 1000; // Muunnos mikrosekunneiksi.
        String timeFormat = formatTime(time);
        String exploredCellsFormat = formatNumber(exploredCells);
        System.out.print("  ::  Solution found on average in " + timeFormat);
        System.out.println("  ::  Cells explored on average: " + exploredCellsFormat);
    }

    /**
     * Generoi labyrintin ja palauttaa kestäneen ajan.
     *
     * @return Palauttaa labyrintin generointiin kestäneen ajan.
     * @throws java.lang.Exception Labyrintti-luokka heittää poikkeuksen, jos
     * algoritmi yrittää käsitellä labyrintin ulkopuolista koordinaattia.
     */
    private long generateAndGetTime() throws Exception {
        labyrinth.lg.createEmptyLabyrinthIfNeeded();
        long startTime = System.nanoTime();
        labyrinth.lg.generateLabyrinth();
        return System.nanoTime() - startTime;
    }

    /**
     * Ratkoo labyrintin ja palauttaa kestäneen ajan.
     *
     * @return Palauttaa labyrintin ratkaisemiseen kestäneen ajan.
     */
    private long solveAndGetTime() {
        labyrinth.ls.reset();
        long startTime = System.nanoTime();
        labyrinth.ls.solveLabyrinth();
        return System.nanoTime() - startTime;
    }

    /**
     * Ratkoo labyrintin ja palauttaa kestäneen ajan.
     *
     * @return Palauttaa labyrintin ratkaisemiseen kestäneen ajan.
     */
    private long solveAndGetTime(WallFollower wf) {
        labyrinth.ls.reset();
        long startTime = System.nanoTime();
        wf.minorlySpedUpSolver();
        return System.nanoTime() - startTime;
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
