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
    public static PrimsAlgorithm pa;
    public static KruskalsAlgorithm ka;
    public static RecursiveBacktracker rb;
    public static WallFollower wf;
    public static DFS dfs;
    public static BFS bfs;

    public static void testCase() throws Exception {
        LabyrinthGenerator[] generators = {pa, ka, rb};
        LabyrinthSolver[] solvers = {wf, dfs, bfs};
        int[] tests = {10, 50, 100, 250, 500, 1000, 2000, 3000};
        for (Integer t : tests) {
            l.updateLabyrinth(t, t);
            for (LabyrinthGenerator lg : generators) {
                System.out.println("- - - - - - - - - - - - - - - - - - - - ");
                lg.routine();
                System.out.println("");
                for (LabyrinthSolver ls : solvers) {
                    ls.routine();
                }
                System.out.println("");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        l = new Labyrinth(30, 30);
        pa = new PrimsAlgorithm(l);
        ka = new KruskalsAlgorithm(l);
        rb = new RecursiveBacktracker(l);
        wf = new WallFollower(l);
        dfs = new DFS(l);
        bfs = new BFS(l);
        //testCase();
        pa.generateLabyrinth();
        wf.routine();
        Gui gui = new Gui(l, wf);
        SwingUtilities.invokeLater(gui);
    }
}
