package main;

import labyrinthgenerator.*;
import labyrinthsolver.*;

/**
 * Tietorakenteet ja algoritmit harjoitustyö. Kesä 2014.
 *
 * @author Juri Kuronen
 */
public class Main {

    public static void main(String[] args) {
        Labyrinth labyrinth = new Labyrinth(10, 10);
        PrimsAlgorithm pa = new PrimsAlgorithm(labyrinth);
        KruskalsAlgorithm ka = new KruskalsAlgorithm(labyrinth);
        RecursiveBacktracker rb = new RecursiveBacktracker(labyrinth);
        WallFollower wf = new WallFollower(labyrinth);
        DFS dfs = new DFS(labyrinth);
        BFS bfs = new BFS(labyrinth);
        LabyrinthGenerator[] generators = {pa, ka, rb};
        LabyrinthSolver[] solvers = {wf, dfs, bfs};
        int[] tests = {10, 50, 100, 250, 500, 1000, 2000, 3000};
        for (Integer t : tests) {
            labyrinth.updateLabyrinth(t, t);
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
}
