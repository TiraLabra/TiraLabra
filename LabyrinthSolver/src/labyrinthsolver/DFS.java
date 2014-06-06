package labyrinthsolver;

import main.MyList;
import main.MyStack;

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
     * @see main.Labyrinth#getListOfEdges(int, int[][], int)
     * @see main.MyStack
     * @see main.myList
     */
    @Override
    public boolean solveLabyrinth() {
        int width = labyrinth.width;
        int height = labyrinth.height;
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
            if (visited[coordinate / width][coordinate % width] == 0) {
                visited[coordinate / width][coordinate % width] = 2;
                MyList neighbors = labyrinth.getListOfEdges(coordinate, visited, 0);
                while (!neighbors.empty()) {
                    stack.push(neighbors.removeByIndex(random.nextInt(neighbors.size())));
                }
            }
        }
        return false;
    }

}
