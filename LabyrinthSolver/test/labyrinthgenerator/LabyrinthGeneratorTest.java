package labyrinthgenerator;

import main.Labyrinth;
import static org.junit.Assert.*;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

public class LabyrinthGeneratorTest {

    Labyrinth l;

    @Before
    public void setUp() {
        l = new Labyrinth(20, 20);
    }

    public void traverseLabyrinth(byte[][] l, boolean[][] visited, int coordinate, int width) {
        visited[coordinate / width][coordinate % width] = true;

        if ((l[coordinate / width][coordinate % width] & 2) > 0
                && !visited[coordinate / width][coordinate % width + 1]) {
            traverseLabyrinth(l, visited, coordinate + 1, width);
        }
        if ((l[coordinate / width][coordinate % width] & 4) > 0
                && !visited[coordinate / width + 1][coordinate % width]) {
            traverseLabyrinth(l, visited, coordinate + width, width);
        }
        if ((l[coordinate / width][coordinate % width] & 1) > 0
                && !visited[coordinate / width - 1][coordinate % width]) {
            traverseLabyrinth(l, visited, coordinate - width, width);
        }
        if ((l[coordinate / width][coordinate % width] & 8) > 0
                && !visited[coordinate / width][coordinate % width - 1]) {
            traverseLabyrinth(l, visited, coordinate - 1, width);
        }
    }

    public void traverseLabyrinthAndSaveEdges(byte[][] lab, byte[][] testLab, boolean[][] visited, int coordinate) {
        visited[coordinate / l.width][coordinate % l.width] = true;

        if ((lab[coordinate / l.width][coordinate % l.width] & 2) > 0
                && !visited[coordinate / l.width][coordinate % l.width + 1]) {
            testLab[coordinate / l.width][coordinate % l.width] |= 2;
            testLab[coordinate / l.width][coordinate % l.width + 1] |= 8;
            traverseLabyrinthAndSaveEdges(lab, testLab, visited, coordinate + 1);
        }
        if ((lab[coordinate / l.width][coordinate % l.width] & 4) > 0
                && !visited[coordinate / l.width + 1][coordinate % l.width]) {
            testLab[coordinate / l.width][coordinate % l.width] |= 4;
            testLab[coordinate / l.width + 1][coordinate % l.width] |= 1;
            traverseLabyrinthAndSaveEdges(lab, testLab, visited, coordinate + l.width);
        }
        if ((lab[coordinate / l.width][coordinate % l.width] & 1) > 0
                && !visited[coordinate / l.width - 1][coordinate % l.width]) {
            testLab[coordinate / l.width][coordinate % l.width] |= 1;
            testLab[coordinate / l.width - 1][coordinate % l.width] |= 4;
            traverseLabyrinthAndSaveEdges(lab, testLab, visited, coordinate - l.width);
        }
        if ((lab[coordinate / l.width][coordinate % l.width] & 8) > 0
                && !visited[coordinate / l.width][coordinate % l.width - 1]) {
            testLab[coordinate / l.width][coordinate % l.width] |= 8;
            testLab[coordinate / l.width][coordinate % l.width - 1] |= 2;
            traverseLabyrinthAndSaveEdges(lab, testLab, visited, coordinate - 1);
        }
    }

    @Test
    public void creatingNewLabyrinth() throws Exception {
        Assume.assumeNotNull(l.lg);
        l.lg.generateLabyrinth();
        l.lg.createEmptyLabyrinthIfNeeded();
        assertTrue(l.labyrinth[0][0] == 0);
    }

    @Test
    public void AlgorithmAddsAllCellsToLabyrinth() throws Exception {
        Assume.assumeNotNull(l.lg);
        l.lg.generateLabyrinth();
        for (int i = 0; i < l.height; i++) {
            for (int j = 0; j < l.width; j++) {
                assertTrue(l.labyrinth[i][j] > 0);
            }
        }
    }

    @Test
    public void allCellsAreVisitableInGeneratedLabyrinth() throws Exception {
        Assume.assumeNotNull(l.lg);
        if (l.labyrinth[0][0] == 0) {
            AlgorithmAddsAllCellsToLabyrinth();
        }
        boolean[][] visited = new boolean[l.height][l.width];
        traverseLabyrinth(l.labyrinth, visited, 0, l.width);
        for (int i = 0; i < l.height; i++) {
            for (int j = 0; j < l.width; j++) {
                assertTrue(visited[i][j]);
            }
        }
    }

    @Test
    public void regeneratingLabyrinthCreatesANewLabyrinth() throws Exception {
        Assume.assumeNotNull(l.lg);
        AlgorithmAddsAllCellsToLabyrinth();
        int checksum = 0;
        for (int i = 0; i < l.height; i++) {
            for (int j = 0; j < l.width; j++) {
                checksum = checksum + (l.labyrinth[i][j] + i) * j;
            }
        }
        l.lg.createEmptyLabyrinthIfNeeded();
        AlgorithmAddsAllCellsToLabyrinth();
        int checksum2 = 0;
        for (int i = 0; i < l.height; i++) {
            for (int j = 0; j < l.width; j++) {
                checksum2 = checksum + (l.labyrinth[i][j] + i) * j;
            }
        }
        assertTrue(checksum != checksum2);
    }

    @Test
    public void listingUnvisitedNeighbors() {
        Assume.assumeNotNull(l.lg);
        int[][] visited = new int[l.height][l.width];
        assertEquals(2, l.getListOfUnvisitedNeighbors(0, visited).size());
        assertEquals(3, l.getListOfUnvisitedNeighbors(l.width / 2, visited).size());
        assertEquals(4, l.getListOfUnvisitedNeighbors(l.width + 5, visited).size());
    }

    @Test
    public void creatingBigLabyrinth() throws Exception {
        Assume.assumeNotNull(l.lg);
        l.updateLabyrinth(100, 100);
        long startTime = System.currentTimeMillis();
        AlgorithmAddsAllCellsToLabyrinth();
        assertTrue(System.currentTimeMillis() - startTime < 1000);
        allCellsAreVisitableInGeneratedLabyrinth();
    }

    @Test
    public void labyrinthIsSpanningTree() throws Exception {
        Assume.assumeNotNull(l.lg);
        l.lg.generateLabyrinth();
        /*
         Start somewhere in the middle.
         */
        int coordinate = l.width * l.height / 2 + l.width / 2;
        Labyrinth testLabyrinth = new Labyrinth(l.width, l.height);
        traverseLabyrinthAndSaveEdges(l.labyrinth, testLabyrinth.labyrinth, new boolean[l.width][l.height], coordinate);
        int checksum1 = 0, checksum2 = 0;
        for (int i = 0; i < l.height; i++) {
            for (int j = 0; j < l.width; j++) {
                checksum1 += l.labyrinth[i][j];
                checksum2 += testLabyrinth.labyrinth[i][j];
            }
        }
        assertTrue(checksum1 == checksum2);
    }
}
