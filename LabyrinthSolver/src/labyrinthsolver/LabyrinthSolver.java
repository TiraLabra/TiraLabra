package labyrinthsolver;

import java.util.Random;
import main.Labyrinth;
import util.MyList;
import util.MyStack;

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
     * Algoritmin nimi.
     */
    public String name;

    /**
     * Alustaa Random-olion..
     */
    public LabyrinthSolver() {
        random = new Random();
    }

    /**
     * Palauttaa algoritmin nimen.
     *
     * @return Palauttaa algoritmin nimen.
     */
    public String getName() {
        return name;
    }

    /**
     * Resetoi ratkaisun.
     */
    public void reset() {
        visited = null;
        exploredCells = 0;
        path = null;
    }

    /**
     * Labyrintin ratkova metodi.
     *
     * @return Palauttaa true, jos labyrintti ratkaistiin.
     */
    public abstract boolean solveLabyrinth();

    /**
     * Laskee visited-solujen määrän.
     *
     * @return Palauttaa visited-solujen määrän.
     */
    public int getExploredCells() {
        int width = labyrinth.getWidth();
        int height = labyrinth.getHeight();
        exploredCells = 0;
        for (int i = 0; i < height * width; i++) {
            if (visited[i / width][i % width] == 2) {
                exploredCells++;
            }
        }
        return exploredCells;
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
     * Palauttaa listan reitistä maaliin. Jos listaa ei ole vielä luotu, hakee
     * reitin mikäli labyrintti on ratkaistu.
     *
     * @return Palauttaa listan reitistä maaliin.
     * @see findPath(int[][])
     */
    public MyList<Integer> getPath() {
        if (path == null) {
            if (visited == null) {
                return null;
            }
            int width = labyrinth.getWidth();
            int height = labyrinth.getHeight();
            int[][] vstd = new int[height][width];
            for (int i = 0; i < height; i++) {
                System.arraycopy(visited[i], 0, vstd[i], 0, width);
            }
            findPath(vstd);
        }
        return path;
    }

    /**
     * Polunetsijän käyttämä TreeNode-apuluokka.
     */
    static class TreeNode {

        /**
         * Tämän alkion vanhempi.
         */
        TreeNode parent;
        /**
         * Tämän solmun koordinaatti.
         */
        int coordinate;

        /**
         * Alustaa vanhemmalla ja solmun koordinaatilla.
         *
         * @param p Vanhempi solmu.
         * @param c Koordinaatti.
         */
        TreeNode(TreeNode p, int c) {
            parent = p;
            coordinate = c;
        }
    }

    /**
     * Etsii polun maaliin kulkemalla visited-arraytä pitkin.
     * <br><br>
     * <u>Toiminta:</u><br>
     * Lisää lähtökoordinaatin, TreeNodena, pinoon. Pinossa on aina
     * täsmällisesti juuri nyt kuljettu polku, sillä peruuttaessa pinosta
     * poistuu peruutetut alkiot. Kun löydetään maali, pinon sisältö
     * tyhjennetään polun listaan.
     *
     * @param vsted Array, jossa on tietoa labyrintin solujen tilasta.
     * @see main.MyList
     * @see TreeNode
     */
    void findPath(int[][] vsted) {
        path = new MyList<>();
        MyStack<TreeNode> stack = new MyStack<>();
        stack.push(new TreeNode(null, 0));
        int width = labyrinth.getWidth();
        int height = labyrinth.getHeight();
        int target = height * width - 1;
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (vsted[node.coordinate / width][node.coordinate % width] == 0) {
                continue;
            }
            vsted[node.coordinate / width][node.coordinate % width] = 0;
            if (node.coordinate == target) {
                generatePath(node, target);
                return;
            }
            pushNewEdgesToStack(node, stack, vsted);
        }
    }

    /**
     * Lisää annetusta TreeNodesta lähtevien kaarien yhdistämät koordinaatit
     * pinoon.
     *
     * @param node Annettu TreeNode.
     * @param stack Annettu pino.
     * @param visited Array, jossa on tietoa labyrintin solujen tilasta.
     */
    void pushNewEdgesToStack(TreeNode node, MyStack stack, int[][] visited) {
        if (node == null || stack == null) {
            return;
        }
        MyList edges = labyrinth.getListOfConnectedNeighbors(node.coordinate, visited, 2);
        for (int i = 0; i < edges.size(); i++) {
            stack.push(new TreeNode(node, (int) edges.get(i)));
        }
    }

    /**
     * Kun annetaan maalisolmussa oleva TreeNode, luodaan polku maaliin
     * kulkemalla TreeNodesta juureen asti.
     *
     * @param node Annettu TreeNode.
     */
    void generatePath(TreeNode node, int target) {
        if (node == null || node.coordinate != target) {
            return;
        }
        while (node != null) {
            path.add(node.coordinate);
            node = node.parent;
        }
        path.reverseList();
    }
}
