package labyrinthsolver;

import labyrinthgenerator.*;
import labyrinthsolver.LabyrinthSolver.TreeNode;
import main.Labyrinth;
import main.MyList;
import static org.junit.Assert.*;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

public class LabyrinthSolverTest {

    Labyrinth l;
    LabyrinthGenerator[] lg;
    int lgSize;

    @Before
    public void setUp() {
        l = new Labyrinth(20, 20);
        setLabyrinthGenerators();
    }

    public void setLabyrinthGenerators() {
        lgSize = 3;
        lg = new LabyrinthGenerator[lgSize];
        lg[0] = new KruskalsAlgorithm();
        lg[1] = new PrimsAlgorithm();
        lg[2] = new RecursiveBacktracker();
    }

    public void runLabyrinthSolver() {
        l.ls.solveLabyrinth();
        l.ls.getExploredCells();
        l.ls.getPath();
    }

    @Test
    public void algorithmFindsPath() throws Exception {
        if (l.ls == null) {
            return;
        }
        for (int j = 0; j < lgSize; j++) {
            l.setLabyrinthGenerator(lg[j]);
            l.generateLabyrinth();
            runLabyrinthSolver();
            MyList path = l.ls.getPath();
            assertTrue(path != null);
        }
    }

    @Test
    public void algorithmFindsValidPath() throws Exception {
        if (l.ls == null) {
            return;
        }
        for (int j = 0; j < lgSize; j++) {
            l.setLabyrinthGenerator(lg[j]);
            l.generateLabyrinth();
            runLabyrinthSolver();
            MyList path = l.ls.getPath();
            assertTrue(path != null);
            int target = l.getHeight() * l.getWidth() - 1;
            for (int i = 1; i < path.size() - 1; i++) {
                assertTrue((int) path.get(i) != target);
                assertEquals(l.getListOfConnectedNeighbors((int) path.get(i - 1), l.ls.visited, 2).removeByValue((int) path.get(i)), (int) path.get(i));
            }
            assertTrue((int) path.get(path.size() - 1) == target);
        }
    }

    @Test
    public void gettingExploredCells() throws Exception {
        if (l.ls == null) {
            return;
        }
        for (int j = 0; j < lgSize; j++) {
            l.setLabyrinthGenerator(lg[j]);
            l.generateLabyrinth();
            runLabyrinthSolver();
            assertTrue(l.ls.exploredCells > 0);
        }
    }

    @Test
    public void gettingExploredCellsIsEqualOrLargerThanPathSize() throws Exception {
        if (l.ls == null) {
            return;
        }
        for (int j = 0; j < lgSize; j++) {
            l.setLabyrinthGenerator(lg[j]);
            l.generateLabyrinth();
            runLabyrinthSolver();
            assertTrue(l.ls.exploredCells >= l.ls.getPath().size());
        }
    }

    @Test
    public void treeNodeProperlyLinksNodes() {
        TreeNode tn = new TreeNode(null, 0);
        TreeNode tn2 = new TreeNode(tn, 0);
        TreeNode tn3 = new TreeNode(tn2, 0);
        TreeNode tn4 = new TreeNode(tn, 0);
        assertEquals(null, tn.parent);
        assertEquals(tn, tn2.parent);
        assertEquals(tn2, tn3.parent);
        assertEquals(tn, tn4.parent);
    }

    @Test
    public void findPathFindsCorrectPath() throws Exception {
        if (l.ls == null) {
            return;
        }
        l.updateLabyrinth(2, 4);
        l.addPassage(0, 2);
        l.addPassage(2, 4);
        l.addPassage(4, 6);
        l.addPassage(6, 7);
        int[][] visited = new int[4][2];
        visited[0][0] = 2;
        visited[1][0] = 2;
        visited[2][0] = 2;
        visited[3][0] = 2;
        visited[3][1] = 2;
        runLabyrinthSolver();
        MyList path = l.ls.getPath();
        assertTrue(!path.empty());
        assertEquals(0, path.get(0));
        assertEquals(2, path.get(1));
        assertEquals(4, path.get(2));
        assertEquals(6, path.get(3));
        assertEquals(7, path.get(4));
    }

}
