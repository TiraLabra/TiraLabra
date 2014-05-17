package labyrinthgenerator;

import java.util.Random;
import main.Labyrinth;
import main.MyList;

/**
 * Labyrintin generointialgoritmien yliluokka.
 *
 * @author Juri Kuronen
 */
public abstract class LabyrinthGenerator {

    /**
     * Labyrintti, jolle algoritmit ajetaan.
     */
    public Labyrinth labyrinth;
    /**
     * Random-olio, jota käytetään satunnaisalgoritmeissa.
     */
    public Random random;

    /**
     * Ottaa syötteenä labyrintin ja alustaa Random-olion.
     *
     * @param l Labyrintti, jolle algoritmi ajetaan.
     */
    public LabyrinthGenerator(Labyrinth l) {
        labyrinth = l;
        //if(labyrinth is not empty) { abort and give warning}
        random = new Random();
    }

    /**
     * Labyrintin generoiva metodi.
     */
    public abstract void generateLabyrinth();

    /**
     * Tulostusrutiini.
     */
    public void routine() {
        long startTime = System.currentTimeMillis();
        generateLabyrinth();
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     * Annetun koordinaatin vierailemattomat naapurit.
     *
     * @param coordinate Koordinaatti, jossa solu on.
     * @param visited Array, jossa on tietoa labyrintin solujen tilasta.
     * @return Palauttaa listan annetun koordinaatin vierailemattomista
     * naapureista.
     *
     * @see main.MyList
     */
    public MyList getListOfUnvisitedNeighbors(int coordinate, int[][] visited) {
        MyList listOfNeighbours = new MyList(4);

        /*
         NORTH
         */
        if (coordinate / labyrinth.width - 1 >= 0) {
            if (visited[coordinate / labyrinth.width - 1][coordinate % labyrinth.width] == 0) {
                listOfNeighbours.add(coordinate - labyrinth.width);
            }
        }

        /*
         EAST
         */
        if (coordinate % labyrinth.width + 1 < labyrinth.width) {
            if (visited[coordinate / labyrinth.width][coordinate % labyrinth.width + 1] == 0) {
                listOfNeighbours.add(coordinate + 1);
            }
        }

        /*
         SOUTH
         */
        if (coordinate / labyrinth.width + 1 < labyrinth.height) {
            if (visited[coordinate / labyrinth.width + 1][coordinate % labyrinth.width] == 0) {
                listOfNeighbours.add(coordinate + labyrinth.width);
            }
        }

        /*
         WEST
         */
        if (coordinate % labyrinth.width - 1 >= 0) {
            if (visited[coordinate / labyrinth.width][coordinate % labyrinth.width - 1] == 0) {
                listOfNeighbours.add(coordinate - 1);
            }
        }

        return listOfNeighbours;
    }

}
