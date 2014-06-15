package labyrinthsolver;

import main.MyList;
import main.MyQueue;

/**
 * Satunnaistettu leveyssuuntainen hakualgoritmi labyrintin ratkaisemiseen.
 *
 * @author Juri Kuronen
 */
public class BFS extends LabyrinthSolver {

    /**
     * Yliluokka alustaa random-olion.
     *
     * @see labyrinthsolver.LabyrinthSolver#LabyrinthSolver()
     */
    public BFS() {
        super();
        name = "Randomized breadth-first search";
    }

    /**
     * <u>Alustus:</u><br>
     * Alustaa visited-arrayn ja jonon. Lisää aluksi jonoon lähtökoordinaatin.
     * <br><br>
     * <u>Toiminta:</u><br>
     * Poista jonosta alkio. Jos alkio on maalikoordinaatti, labyrintin ratkaisu
     * löydettiin ja algoritmi päättyy. Muutoin, lisää tästä alkiosta lähtevät
     * kaaret (ei vierailtuihin soluihin) satunnaisessa järjestyksessä jonoon.
     * Jatka kunnes maalikoordinaatti löytyy.
     * <br><br>
     * Jonoa käyttämällä saavutetaan leveyssuuntainen haku.
     *
     * @return Palauttaa true, jos labyrintti ratkaistiin.
     * @see main.Labyrinth#getListOfEdges(int, int[][], int)
     * @see main.MyQueue
     * @see main.myList
     */
    @Override
    public boolean solveLabyrinth() {
        int width = labyrinth.getWidth();
        int height = labyrinth.getHeight();
        int targetCoordinate = width * height - 1;
        visited = new int[height][width];
        MyQueue queue = new MyQueue<>(width * height / 4);
        queue.enqueue(0);
        while (!queue.empty()) {
            int coordinate = (int) queue.dequeue();
            if (coordinate == targetCoordinate) {
                visited[coordinate / width][coordinate % width] = 2;
                return true;
            }
            if (visited[coordinate / width][coordinate % width] == 0) {
                visited[coordinate / width][coordinate % width] = 2;
                MyList neighbors = labyrinth.getListOfConnectedNeighbors(coordinate, visited, 0);
                while (!neighbors.empty()) {
                    queue.enqueue(neighbors.removeByIndex(random.nextInt(neighbors.size())));
                }
            }
        }
        return false;
    }

}
