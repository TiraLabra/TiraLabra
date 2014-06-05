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
     * @see labyrinthgenerator.LabyrinthGenerator#LabyrinthGenerator()
     */
    public PrimsAlgorithm() {
        super();
    }

    /**
     * @throws java.lang.Exception Heittää poikkeuksen, jos labyrinttia ei ole
     * asetettu tai käsiteltiin jotain labyrintin ulkopuolista koordinaattia.
     * @see labyrinthgenerator.LabyrinthGenerator#routine()
     */
    @Override
    public void printRoutine() throws Exception {
        System.out.print("Prim's Algorithm");
        super.printRoutine();
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
     * @throws java.lang.Exception Heittää poikkeuksen, jos labyrinttia ei ole
     * asetettu tai käsiteltiin jotain labyrintin ulkopuolista koordinaattia.
     * @see main.MyList
     * @see main.Labyrinth#addPassage(int, int)
     */
    @Override
    public void generateLabyrinth() throws Exception {
        createEmptyLabyrinthIfNeeded();
        int[][] visited = new int[labyrinth.height][labyrinth.width];
        visited[0][0] = 2;  // Start at (0, 0)
        /*
         List could be initialized with labyrinth size / 2.
         */
        MyList<Integer> list = getListOfUnvisitedNeighbors(0, visited);
        while (!list.empty()) {
            int key = random.nextInt(list.size());
            int coordinate = list.get(key);
            list.removeByIndex(key);
            visited[coordinate / labyrinth.width][coordinate % labyrinth.width] = 2;
            MyList<Integer> possibleConnectionsToLabyrinth = labyrinth.getListOfVisitedNeighbors(coordinate, visited);
            labyrinth.addPassage(coordinate, possibleConnectionsToLabyrinth.get(random.nextInt(possibleConnectionsToLabyrinth.size())));
            MyList<Integer> newAdjacentUnvisitedCells = getListOfUnvisitedNeighbors(coordinate, visited);
            list.join(newAdjacentUnvisitedCells);
        }
    }

    /**
     * Modifioitu versio labyrintti-luokan algoritmista. Tässä versiossa on 3
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
     * @see main.Labyrinth#getListOfUnvisitedNeighbors(int, int[][])
     */
    MyList getListOfUnvisitedNeighbors(int coordinate, int[][] visited) {
        MyList<Integer> listOfNeighbours = new MyList(4);

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
         NORTH
         */
        if (coordinate / labyrinth.width - 1 >= 0) {
            if (visited[coordinate / labyrinth.width - 1][coordinate % labyrinth.width] == 0) {
                visited[coordinate / labyrinth.width - 1][coordinate % labyrinth.width] = 1;
                listOfNeighbours.add(coordinate - labyrinth.width);
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

}
