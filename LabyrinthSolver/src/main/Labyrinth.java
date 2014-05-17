package main;

import labyrinthgenerator.*;

/**
 * Labyrintti-olio.
 *
 * @author Juri Kuronen
 */
public class Labyrinth {

    /**
     * Labyrintti on tallennettu height * width -kokoiseen arrayhun.
     */
    public byte[][] labyrinth;
    /**
     * Labyrintin leveys.
     */
    public int width;
    /**
     * Labyrintin korkeus.
     */
    public int height;

    /**
     * Maski pohjoissuunnalle.
     */
    public final byte NORTH = 1;
    /**
     * Maski itäsuunnalle.
     */
    public final byte EAST = 2;
    /**
     * Maski eteläsuunnalle.
     */
    public final byte SOUTH = 4;
    /**
     * Maski länsisuunnalle.
     */
    public final byte WEST = 8;

    /**
     *
     * @param w Labyrintin leveys.
     * @param h Labyrintin korkeus.
     */
    public Labyrinth(int w, int h) {
        width = w;
        height = h;
        labyrinth = new byte[height][width];
        RecursiveBacktracker rb = new RecursiveBacktracker(this);
        rb.routine();
        //print();

        labyrinth = new byte[height][width];
        PrimsAlgorithm pa = new PrimsAlgorithm(this);
        pa.routine();
        //print();

        labyrinth = new byte[height][width];
        KruskalsAlgorithm ka = new KruskalsAlgorithm(this);
        ka.routine();
        //print();
    }

    /**
     * (TEMP) Tulostusrutiini.
     */
    public void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.printf("%d%d%d%d ", labyrinth[i][j] & NORTH, labyrinth[i][j] & EAST, labyrinth[i][j] & SOUTH, labyrinth[i][j] & WEST);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * Palauttaa koordinaatin kohteeseen.
     *
     * @param coordinateOrig Solun koordinaatti.
     * @param mask Suunnan maski.
     * @return
     */
    public int getTargetCoordinate(int coordinateOrig, byte mask) {
        if (mask == 1) {
            return coordinateOrig - width;
        }
        if (mask == 2) {
            return coordinateOrig + 1;
        }
        if (mask == 4) {
            return coordinateOrig + width;
        }
        return coordinateOrig - 1;
    }

    /**
     * Lisää labyrinttiin reitin kahden koordinaatin välille.
     *
     * @param coordinateOrig Solun koordinaatti.
     * @param coordinateTarget Kohteen koordinaatti.
     */
    public void addPassage(int coordinateOrig, int coordinateTarget) {
        if (coordinateOrig - width == coordinateTarget) {
            labyrinth[coordinateOrig / width][coordinateOrig % width] |= NORTH;
            labyrinth[coordinateTarget / width][coordinateTarget % width] |= SOUTH;
        } else if (coordinateOrig + 1 == coordinateTarget) {
            labyrinth[coordinateOrig / width][coordinateOrig % width] |= EAST;
            labyrinth[coordinateTarget / width][coordinateTarget % width] |= WEST;
        } else if (coordinateOrig + width == coordinateTarget) {
            labyrinth[coordinateOrig / width][coordinateOrig % width] |= SOUTH;
            labyrinth[coordinateTarget / width][coordinateTarget % width] |= NORTH;
        } else {
            labyrinth[coordinateOrig / width][coordinateOrig % width] |= WEST;
            labyrinth[coordinateTarget / width][coordinateTarget % width] |= EAST;
        }
    }

}
