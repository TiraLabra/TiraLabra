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
        Labyrinth labyrinth = new Labyrinth(1000, 1000);
        PrimsAlgorithm pa = new PrimsAlgorithm(labyrinth);
        KruskalsAlgorithm ka = new KruskalsAlgorithm(labyrinth);
        RecursiveBacktracker rb = new RecursiveBacktracker(labyrinth);
        WallFollower wf = new WallFollower(labyrinth);
        DFS dfs = new DFS(labyrinth);
        LabyrinthGenerator[] generators = {pa, ka, rb};
        LabyrinthSolver[] solvers = {wf, dfs};
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
