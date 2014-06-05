package main;

import gui.Gui;
import javax.swing.SwingUtilities;
import labyrinthgenerator.*;
import labyrinthsolver.*;

/**
 * Tietorakenteet ja algoritmit harjoitustyö. Kesä 2014.
 *
 * @author Juri Kuronen
 */
public class Main {

    public static Labyrinth l;

    public static void testCase() throws Exception {
        LabyrinthGenerator[] generators = {new PrimsAlgorithm(),
            new KruskalsAlgorithm(),
            new RecursiveBacktracker()};
        LabyrinthSolver[] solvers = {new WallFollower(),
            new DFS(),
            new BFS()};
        int[] tests = {10, 50, 100, 250, 500, 1000, 2000, 3000};
        for (Integer t : tests) {
            l.updateLabyrinth(t, t);
            for (LabyrinthGenerator lg : generators) {
                System.out.println("- - - - - - - - - - - - - - - - - - - - ");
                l.setLabyrinthGenerator(lg);
                l.lg.printRoutine();
                System.out.println("");
                for (LabyrinthSolver ls : solvers) {
                    l.setLabyrinthSolver(ls);
                    l.ls.printRoutine();
                }
                System.out.println("");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        l = new Labyrinth(50, 50);
        //testCase();
        Gui gui = new Gui(l);
        SwingUtilities.invokeLater(gui);
    }
}
