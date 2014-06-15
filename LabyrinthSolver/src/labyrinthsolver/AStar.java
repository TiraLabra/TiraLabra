package labyrinthsolver;

import main.MyList;
import main.MyPriorityHeap;
import main.HeapElement;

/**
 * A*-hakualgoritmi labyrintin ratkaisemiseen.
 *
 * @author Juri Kuronen
 */
public class AStar extends LabyrinthSolver {

    private MyPriorityHeap ph;
    private int width;
    private int goalX;
    private int goalY;

    /**
     * Yliluokka alustaa random-olion.
     *
     * @see labyrinthsolver.LabyrinthSolver#LabyrinthSolver()
     */
    public AStar() {
        super();
        name = "A* search";
    }

    /**
     * Palauttaa kustannusarvion nykyisestä koordinaatista maaliin.
     * Kustannusarviota on skaalattu ylöspäin, jotta lähempänä maalia olevat
     * solut käsiteltäisiin ensin.
     *
     * @param coordinate Nykyinen koordinaatti.
     * @return Palauttaa pituusarvion nykyisestä koordinaatista maaliin.
     */
    private int heuristic(int coordinate) {
        return (int) (1.1 * ((goalX - coordinate % width) + (goalY - coordinate / width)));
    }

    /**
     * <u>Alustus:</u><br>
     * Alustaa visited-arrayn, prioriteettikeon ja koordinaattitiedot.
     * <br><br>
     * <u>Toiminta:</u><br>
     * Algoritmi kutsuu findGoal()-metodia, mikä käsittelee koordinaatteja
     * pienimmän kustannusarvion järjestyksessä. Käsitellyn koordinaatin
     * naapurit lisätään prioriteettilistaan. Kun käsittelyyn tulee
     * maalikoordinaatti, algoritmi ratkaisi labyrintin.
     *
     * @return Palauttaa true, jos labyrintti ratkaistiin.
     */
    @Override
    public boolean solveLabyrinth() {
        width = labyrinth.getWidth();
        goalY = labyrinth.getHeight() - 1;
        goalX = width - 1;
        visited = new int[goalY + 1][width];
        ph = new MyPriorityHeap((goalY + 1) * width / 4);
        findGoal();
        return true;
    }

    /**
     * Aloittaa käsittelyn lähtökoordinaatista. Käsitellyn koordinaatin naapurit
     * lisätään prioriteettilistaan. Käsittelee koordinaatteja pienimmän
     * kustannusarvion järjestyksessä. Kun käsittelyyn tulee maalikoordinaatti,
     * algoritmi ratkaisi labyrintin.
     *
     * @param coordinate Nykyinen koordinaatti.
     * @param currentDistance Kuljettu matka.
     * @see addNeighborsToPriorityHeap
     */
    private void findGoal() {
        int coordinate = 0;
        int currentDistance = 0;
        while (true) {
            visited[coordinate / width][coordinate % width] = 2;
            if (visited[goalY][goalX] == 2) {
                return;
            }
            addNeighborsToPriorityHeap(coordinate, currentDistance);
            HeapElement next = ph.removeMin();
            coordinate = next.getCoordinate();
            currentDistance = next.getDistance();
        }
    }

    /**
     * Lisää annetun koordinaatin naapurit prioriteettikekoon.
     *
     * @param coordinate Annettu koordinaatti.
     * @param currentDistance Kuljettu matka.
     * @see heuristic()
     */
    private void addNeighborsToPriorityHeap(int coordinate, int currentDistance) {
        MyList neighbors = labyrinth.getListOfConnectedNeighbors(coordinate, visited, 0);
        for (int i = 0; i < neighbors.size(); i++) {
            int neighbor = (int) neighbors.get(i);
            ph.insert(neighbor, currentDistance + 1, heuristic(neighbor));
        }
    }
}
