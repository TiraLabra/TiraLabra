package labyrinthsolver;

import java.util.Random;
import main.Labyrinth;
import main.MyList;
import main.MyStack;

/**
 * Labyrintin ratkoja-algoritmien yliluokka.
 *
 * @author Juri Kuronen
 */
public abstract class LabyrinthSolver {

    /**
     * Labyrintti, jolle algoritmit ajetaan.
     */
    public Labyrinth labyrinth;
    /**
     * Random-olio, jota käytetään satunnaisalgoritmeissa.
     */
    protected Random random;
    /**
     * Tutkittujen solujen määrä.
     */
    protected int exploredCells;
    /**
     * Reitti maaliin.
     */
    protected MyList<Integer> path;
    /**
     * Array, jossa on tietoa labyrintin solujen tiloista.
     */
    protected int[][] visited;

    /**
     * Ottaa syötteenä labyrintin ja alustaa Random-olion..
     * 
     */
    public LabyrinthSolver() {
        random = new Random();
    }

    /**
     * Labyrintin ratkova metodi.
     *
     * @return Palauttaa true, jos labyrintti ratkaistiin.
     */
    public abstract boolean solveLabyrinth();

    /**
     * Tulostusrutiini.
     */
    public void printRoutine() {
        long startTime = System.nanoTime() / 1000;
        boolean solved = solveLabyrinth();
        long finishTime = System.nanoTime() / 1000;
        String timeFormat = labyrinth.formatTime(finishTime - startTime);
        if (solved) {
            System.out.print("  ::  Solution found in " + timeFormat);
            getExploredCells();
            String exploredCellsFormat = labyrinth.formatNumber(exploredCells);
            System.out.println("  ::  Cells explored: " + exploredCellsFormat);
        } else {
            System.out.println("TIMEOUT LIMIT EXCEEDED (" + timeFormat + ")");
        }
    }

    /**
     * Laskee visited-solujen määrän.
     */
    protected void getExploredCells() {
        exploredCells = 0;
        for (int i = 0; i < labyrinth.height * labyrinth.width; i++) {
            if (visited[i / labyrinth.width][i % labyrinth.width] == 2) {
                exploredCells++;
            }
        }
    }

    /**
     * Palauttaa vierailtujen solujen arrayn.
     *
     * @return Palauttaa vierailtujen solujen arrayn.
     */
    public int[][] getVisitedCells() {
        return visited;
    }

    /**
     * Palauttaa listan reitistä maaliin.
     *
     * @return Palauttaa listan reitistä maaliin.
     */
    public MyList<Integer> getPath() {
        if (path == null) {
            if (visited == null) {
                return null;
            }
            int[][] vstd = new int[labyrinth.height][labyrinth.width];
            for (int i = 0; i < labyrinth.height; i++) {
                System.arraycopy(visited[i], 0, vstd[i], 0, labyrinth.width);
            }
            findPath(vstd);
        }
        return path;
    }

    /**
     * Polunetsijän käyttämä treenode-apuluokka. (Vähän purkkaa...)
     */
    static class TreeNode {

        /**
         * Parent-alkio.
         */
        TreeNode parent;
        /**
         * Tämän noden koordinaatti.
         */
        int coordinate;

        /**
         * Alustaa parentilla ja koordinaatilla.
         *
         * @param p Parent node.
         * @param c Koordinaatti.
         */
        TreeNode(TreeNode p, int c) {
            parent = p;
            coordinate = c;
        }
    }

    /**
     * Etsii polun maaliin kulkemalla visited-arraytä pitkin.
     *
     * @param vsted Array, jossa on tietoa labyrintin solujen tilasta.
     */
    protected void findPath(int[][] vsted) {
        path = new MyList<>();
        MyStack<TreeNode> stack = new MyStack<>();
        stack.push(new TreeNode(null, 0));
        int target = labyrinth.height * labyrinth.width - 1;
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (vsted[node.coordinate / labyrinth.width][node.coordinate % labyrinth.width] == 0) {
                continue;
            }
            vsted[node.coordinate / labyrinth.width][node.coordinate % labyrinth.width] = 0;
            if (node.coordinate == target) {
                while (node != null) {
                    path.add(node.coordinate);
                    node = node.parent;
                }
                path.reverseList();
                return;
            }
            MyList neighbors = labyrinth.getListOfEdgesToVisitedNeighbors(node.coordinate, vsted);
            for (int i = 0; i < neighbors.size(); i++) {
                stack.push(new TreeNode(node, (int) neighbors.get(i)));
            }
        }
    }
}
