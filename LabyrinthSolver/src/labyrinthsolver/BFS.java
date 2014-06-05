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
     * BFS käyttää jonoa naapureiden tallentamiseen.
     */
    MyQueue<Integer> queue;

    /**
     * @see labyrinthsolver.LabyrinthSolver#LabyrinthSolver()
     */
    public BFS() {
        super();
    }

    /**
     * Etsii maalia leveyssuuntaisella haulla siten, että kunkin solun naapurit
     * tallennetaan jonoon satunnaisessa järjetyksessä.
     *
     * @return Palauttaa true, jos labyrintti ratkaistiin.
     */
    @Override
    public boolean solveLabyrinth() {
        int targetCoordinate = labyrinth.width * labyrinth.height - 1;
        visited = new int[labyrinth.height][labyrinth.width];
        queue = new MyQueue<>();
        queue.enqueue(0);
        while (!queue.empty()) {
            int coordinate = queue.dequeue();
            if (coordinate == targetCoordinate) {
                visited[coordinate / labyrinth.width][coordinate % labyrinth.width] = 2;
                return true;
            }
            if (visited[coordinate / labyrinth.width][coordinate % labyrinth.width] == 0) {
                visited[coordinate / labyrinth.width][coordinate % labyrinth.width] = 2;
                MyList neighbors = labyrinth.getListOfEdges(coordinate, visited, 0);
                while (!neighbors.empty()) {
                    queue.enqueue(neighbors.removeByIndex(random.nextInt(neighbors.size())));
                }
            }
        }
        return false;
    }

}
