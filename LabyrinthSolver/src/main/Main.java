package main;

import labyrinthgenerator.KruskalsAlgorithm;
import labyrinthgenerator.PrimsAlgorithm;
import labyrinthgenerator.RecursiveBacktracker;

/**
 * Tietorakenteet ja algoritmit harjoitustyö. Kesä 2014.
 *
 * @author Juri Kuronen
 */
public class Main {

    public static void main(String[] args) {
        Labyrinth labyrinth = new Labyrinth(100, 100);
        RecursiveBacktracker rb = new RecursiveBacktracker(labyrinth);
        rb.routine();

        PrimsAlgorithm pa = new PrimsAlgorithm(labyrinth);
        pa.routine();

        KruskalsAlgorithm ka = new KruskalsAlgorithm(labyrinth);
        ka.routine();
    }
}
