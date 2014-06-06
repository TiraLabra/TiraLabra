package labyrinthgenerator;

import main.MyList;

/**
 * Modifioitu satunnaistettu Primin algoritmi labyrintin generoimiseen.
 *
 * @author Juri Kuronen
 */
public class PrimsAlgorithm extends LabyrinthGenerator {

    /**
     * Yliluokka alustaa random-olion.
     *
     * @see labyrinthgenerator.LabyrinthGenerator#LabyrinthGenerator()
     */
    public PrimsAlgorithm() {
        super();
        name = "Prim's algorithm";
    }

    /**
     * <u>Alustus:</u><br>
     * Luodaan visited-array sekä alustetaan lista soluista, jotka voidaan
     * seuraavaksi lisätä osaksi labyrinttia.
     * <br><br>
     * <u>Toiminta:</u><br>
     * Lähtee liikkeelle lähtösolusta. Lisää solun ne naapurit, jotka eivät ole
     * osana labyrinttia, listaan soluista, jotka voidaan seuraavaksi lisätä
     * osaksi labyrinttia. Tämän jälkeen valitsee yhden solun tästä listasta,
     * liittää sen labyrinttiin, ja hakee jälleen tämän solun naapurit samalla
     * tavalla. Toistaa tätä niin kauan, kun listassa on soluja. Listan
     * tyhjetessä labyrintti on generoitu.
     * <br><br>
     * Labyrintin toiminnasta löytyy tietoa myös määrittelydokumentista.
     *
     * @throws java.lang.Exception Labyrintti-luokka heittää poikkeuksen, jos
     * algoritmi yrittää käsitellä labyrintin ulkopuolista koordinaattia.
     * @see main.MyList
     * @see main.Labyrinth#addPassage(int, int)
     */
    @Override
    public void generateLabyrinth() throws Exception {
        int width = labyrinth.width;
        int height = labyrinth.height;
        int[][] visited = new int[height][width];
        visited[0][0] = 2;
        /*
         * Alustetaan lista vähän isompana.
         */
        MyList<Integer> list = new MyList<>(height * width / 4);
        list.join(getListOfUnvisitedNeighbors(0, visited));
        while (!list.empty()) {
            int key = random.nextInt(list.size());
            int coordinate = list.get(key);
            list.removeByIndex(key);
            visited[coordinate / width][coordinate % width] = 2;
            MyList<Integer> possibleConnectionsToLabyrinth = labyrinth.getListOfNeighbors(coordinate, visited, 2);
            labyrinth.addPassage(coordinate, possibleConnectionsToLabyrinth.get(random.nextInt(possibleConnectionsToLabyrinth.size())));
            MyList<Integer> newAdjacentUnvisitedCells = getListOfUnvisitedNeighbors(coordinate, visited);
            list.join(newAdjacentUnvisitedCells);
        }
    }

    /**
     * Modifioitu versio labyrintti-luokan algoritmista. Algoritmi myös muuttaa
     * visited-tiloja. Tässä versiossa on 3 'visited'-tilaa:
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
     * @see main.Labyrinth#getListOfNeighbors(int, int[][], int)
     * @see main.MyList
     */
    MyList getListOfUnvisitedNeighbors(int coordinate, int[][] visited) {
        int width = labyrinth.width;
        MyList<Integer> listOfNeighbours = new MyList(4);
        int x = coordinate % width;
        int y = coordinate / width;
        /*
         EAST
         */
        if (labyrinth.validCoordinate(x + 1, y, visited, 0)) {
            visited[y][x + 1] = 1;
            listOfNeighbours.add(coordinate + 1);
        }
        /*
         SOUTH
         */
        if (labyrinth.validCoordinate(x, y + 1, visited, 0)) {
            visited[y + 1][x] = 1;
            listOfNeighbours.add(coordinate + width);
        }
        /*
         NORTH
         */
        if (labyrinth.validCoordinate(x, y - 1, visited, 0)) {
            visited[y - 1][x] = 1;
            listOfNeighbours.add(coordinate - width);
        }
        /*
         WEST
         */
        if (labyrinth.validCoordinate(x - 1, y, visited, 0)) {
            visited[y][x - 1] = 1;
            listOfNeighbours.add(coordinate - 1);
        }
        return listOfNeighbours;
    }
}
