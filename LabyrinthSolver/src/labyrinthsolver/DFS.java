package labyrinthsolver;

import util.MyList;
import util.MyStack;

/**
 * Satunnaistettu syvyyssuuntainen hakualgoritmi labyrintin ratkaisemiseen.
 *
 * @author Juri Kuronen
 */
public class DFS extends LabyrinthSolver {

    /**
     * Yliluokka alustaa random-olion.
     *
     * @see labyrinthsolver.LabyrinthSolver#LabyrinthSolver()
     */
    public DFS() {
        super();
        name = "Randomized depth-first search";
    }

    /**
     * <u>Alustus:</u><br>
     * Alustaa visited-arrayn ja pinon. Lisää aluksi pinoon lähtökoordinaatin.
     * <br><br>
     * <u>Toiminta:</u><br>
     * Poista pinosta alkio. Jos alkio on maalikoordinaatti, labyrintin ratkaisu
     * löydettiin ja algoritmi päättyy. Muutoin, lisää tästä alkiosta lähtevät
     * kaaret (ei vierailtuihin soluihin) satunnaisessa järjestyksessä pinoon.
     * Jatka kunnes maalikoordinaatti löytyy.
     * <br><br>
     * Pinoa käyttämällä saavutetaan syvyyssuuntainen haku.
     *
     * @return Palauttaa true, jos labyrintti ratkaistiin.
     * @see main.Labyrinth#getListOfConnectedNeighbors(int, int[][], int)
     * @see util.MyStack
     * @see util.MyList
     */
    @Override
    public boolean solveLabyrinth() {
        int width = labyrinth.getWidth();
        int height = labyrinth.getHeight();
        int targetCoordinate = width * height - 1;
        visited = new int[height][width];
        MyStack stack = new MyStack<>();
        stack.push(0);
        while (!stack.empty()) {
            int coordinate = (int) stack.pop();
            if (coordinate == targetCoordinate) {
                visited[coordinate / width][coordinate % width] = 2;
                return true;
            }
            visited[coordinate / width][coordinate % width] = 2;
            MyList neighbors = labyrinth.getListOfConnectedNeighbors(coordinate, visited, 0);
            while (!neighbors.empty()) {
                stack.push(neighbors.removeByIndex(random.nextInt(neighbors.size())));
            }
        }
        return false;
    }
}
