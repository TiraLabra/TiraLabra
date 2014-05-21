package labyrinthsolver;

import main.Labyrinth;
import main.MyList;
import main.MyStack;

/**
 * Satunnaistettu syvyyssuuntainen hakualgoritmi labyrintin ratkaisemiseen.
 *
 * @author Juri Kuronen
 */
public class DFS extends LabyrinthSolver {

    /**
     * DFS käyttää pinoa naapureiden tallentamiseen.
     */
    MyStack<Integer> stack;

    /**
     * @param l Labyrintti, jolle algoritmi ajetaan.
     * @see labyrinthsolver.LabyrinthSolver#LabyrinthSolver(main.Labyrinth)
     */
    public DFS(Labyrinth l) {
        super(l);
        stack = new MyStack<>();
    }

    /**
     * @see labyrinthsolver.LabyrinthSolver#routine()
     */
    @Override
    public void routine() {
        System.out.print("Randomized depth-first search");
        super.routine();
    }

    /**
     * Etsii maalia syvyyssuuntaisella haulla siten, että kunkin solun naapurit
     * tallennetaan pinoon satunnaisessa järjetyksessä.
     *
     * @return Palauttaa true, jos labyrintti ratkaistiin.
     */
    @Override
    public boolean solveLabyrinth() {
        int targetCoordinate = labyrinth.width * labyrinth.height - 1;
        visited = new int[labyrinth.height][labyrinth.width];
        stack.push(0);
        while (!stack.empty()) {
            int coordinate = stack.pop();
            if (coordinate == targetCoordinate) {
                visited[coordinate / labyrinth.width][coordinate % labyrinth.width] = 2;
                return true;
            }
            if (visited[coordinate / labyrinth.width][coordinate % labyrinth.width] == 0) {
                visited[coordinate / labyrinth.width][coordinate % labyrinth.width] = 2;
                MyList neighbors = labyrinth.getListOfEdgesToUnvisitedNeighbors(coordinate, visited);
                while (!neighbors.empty()) {
                    stack.push(neighbors.removeByIndex(random.nextInt(neighbors.size())));
                }
            }
        }
        return false;
    }

}
