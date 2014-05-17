package labyrinthgenerator;

import main.Labyrinth;
import main.MyList;

/**
 * Modifioitu satunnaistettu Primin algoritmi labyrintin generoimiseen.
 *
 * @author Juri Kuronen
 */

public class PrimsAlgorithm extends LabyrinthGenerator {

    /**
     * @param l Labyrintti, jolle algoritmi ajetaan.
     * @see labyrinthgenerator.LabyrinthGenerator
     */
    public PrimsAlgorithm(Labyrinth l) {
        super(l);
    }

    /**
     * @see labyrinthgenerator.LabyrinthGenerator
     */
    @Override
    public void routine() {
        System.out.println("Prim's Algorithm");
        super.routine();
    }

    /**
     * Lähtee liikkeelle lähtösolusta. Lisää solun ne naapurit, jotka eivät ole
     * osana labyrinttia, listaan soluista, jotka voidaan seuraavaksi lisätä
     * osaksi labyrinttia. Tämän jälkeen valitsee yhden solun tästä listasta,
     * liittää sen labyrinttiin, ja hakee jälleen tämän solun naapurit samalla
     * tavalla. Toistaa tätä niin kauan, kun listassa on soluja. Listan
     * tyhjetessä labyrintti on generoitu.
     *
     * Labyrintin toiminnasta löytyy tietoa myös määrittelydokumentista.
     *
     * @see main.MyList
     * @see main.Labyrinth#addPassage(int, int)
     */
    @Override
    public void generateLabyrinth() {
        int[][] visited = new int[labyrinth.height][labyrinth.width];
        visited[0][0] = 2;  // Start at (0, 0)
        /*
         List could be initialized with labyrinth size / 2.
         */
        MyList list = getListOfUnvisitedNeighbors(0, visited);
        while (!list.empty()) {
            int key = random.nextInt(list.size());
            int coordinate = list.get(key);
            list.remove(key);
            visited[coordinate / labyrinth.width][coordinate % labyrinth.width] = 2;
            MyList possibleConnectionsToLabyrinth = getListOfVisitedNeighbors(coordinate, visited);
            labyrinth.addPassage(coordinate, possibleConnectionsToLabyrinth.get(random.nextInt(possibleConnectionsToLabyrinth.size())));
            MyList newAdjacentUnvisitedCells = getListOfUnvisitedNeighbors(coordinate, visited);
            list.join(newAdjacentUnvisitedCells);
        }
    }

    /**
     * Modifioitu versio yliluokan algoritmista. Tässä versiossa on 3
     * 'visited'-tilaa:
     * <br>
     * 0 - Solu ei ole labyrintin polkujen lähettyvillä.<br>
     * 1 - Solu voitaisiin seuraavaksi liittää labyrinttiin.<br>
     * 2 - Solu on osana labyrinttia.
     *
     * @param coordinate Koordinaatti, jossa solu on.
     * @param visited Array, jossa on tietoa labyrintin solujen tiloista.
     * @return Palauttaa listan annetun koordinaatin naapureista, jotka ovat
     * tilassa 0.
     *
     * @see main.MyList
     * @see
     * labyrinthgenerator.LabyrinthGenerator#getListOfUnvisitedNeighbors(int,
     * int[][])
     */
    @Override
    public MyList getListOfUnvisitedNeighbors(int coordinate, int[][] visited) {
        MyList listOfNeighbours = new MyList(4);

        /*
         NORTH
         */
        if (coordinate / labyrinth.width - 1 >= 0) {
            if (visited[coordinate / labyrinth.width - 1][coordinate % labyrinth.width] == 0) {
                visited[coordinate / labyrinth.width - 1][coordinate % labyrinth.width] = 1;
                listOfNeighbours.add(coordinate - labyrinth.width);
            }
        }

        /*
         EAST
         */
        if (coordinate % labyrinth.width + 1 < labyrinth.width) {
            if (visited[coordinate / labyrinth.width][coordinate % labyrinth.width + 1] == 0) {
                visited[coordinate / labyrinth.width][coordinate % labyrinth.width + 1] = 1;
                listOfNeighbours.add(coordinate + 1);
            }
        }

        /*
         SOUTH
         */
        if (coordinate / labyrinth.width + 1 < labyrinth.height) {
            if (visited[coordinate / labyrinth.width + 1][coordinate % labyrinth.width] == 0) {
                visited[coordinate / labyrinth.width + 1][coordinate % labyrinth.width] = 1;
                listOfNeighbours.add(coordinate + labyrinth.width);
            }
        }

        /*
         WEST
         */
        if (coordinate % labyrinth.width - 1 >= 0) {
            if (visited[coordinate / labyrinth.width][coordinate % labyrinth.width - 1] == 0) {
                visited[coordinate / labyrinth.width][coordinate % labyrinth.width - 1] = 1;
                listOfNeighbours.add(coordinate - 1);
            }
        }

        return listOfNeighbours;
    }

    /**
     * Hakee annetun solun naapurit, jotka ovat osana labyrinttia.
     *
     * @param coordinate Koordinaatti, jossa solu on.
     * @param visited Array, jossa on tietoa labyrintin solujen tiloista.
     * @return Palauttaa listan annetun koordinaatin naapureista, jotka ovat
     * osana labyrinttia.
     *
     * @see main.MyList
     */
    public MyList getListOfVisitedNeighbors(int coordinate, int[][] visited) {
        MyList listOfNeighbours = new MyList(4);

        /*
         NORTH
         */
        if (coordinate / labyrinth.width - 1 >= 0) {
            if (visited[coordinate / labyrinth.width - 1][coordinate % labyrinth.width] == 2) {
                listOfNeighbours.add(coordinate - labyrinth.width);
            }
        }

        /*
         EAST
         */
        if (coordinate % labyrinth.width + 1 < labyrinth.width) {
            if (visited[coordinate / labyrinth.width][coordinate % labyrinth.width + 1] == 2) {
                listOfNeighbours.add(coordinate + 1);
            }
        }

        /*
         SOUTH
         */
        if (coordinate / labyrinth.width + 1 < labyrinth.height) {
            if (visited[coordinate / labyrinth.width + 1][coordinate % labyrinth.width] == 2) {
                listOfNeighbours.add(coordinate + labyrinth.width);
            }
        }

        /*
         WEST
         */
        if (coordinate % labyrinth.width - 1 >= 0) {
            if (visited[coordinate / labyrinth.width][coordinate % labyrinth.width - 1] == 2) {
                listOfNeighbours.add(coordinate - 1);
            }
        }

        return listOfNeighbours;
    }

}
