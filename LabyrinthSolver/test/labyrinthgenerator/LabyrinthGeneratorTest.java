package labyrinthgenerator;

import main.Labyrinth;
import static org.junit.Assert.*;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

public class LabyrinthGeneratorTest {

    Labyrinth l;
    int width;
    int height;

    @Before
    public void setUp() {
        l = new Labyrinth(20, 20);
        width = l.getWidth();
        height = l.getHeight();
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
        visited[coordinate / width][coordinate % width] = true;

        if ((lab[coordinate / width][coordinate % width] & 2) > 0
                && !visited[coordinate / width][coordinate % width + 1]) {
            testLab[coordinate / width][coordinate % width] |= 2;
            testLab[coordinate / width][coordinate % width + 1] |= 8;
            traverseLabyrinthAndSaveEdges(lab, testLab, visited, coordinate + 1);
        }
        if ((lab[coordinate / width][coordinate % width] & 4) > 0
                && !visited[coordinate / width + 1][coordinate % width]) {
            testLab[coordinate / width][coordinate % width] |= 4;
            testLab[coordinate / width + 1][coordinate % width] |= 1;
            traverseLabyrinthAndSaveEdges(lab, testLab, visited, coordinate + width);
        }
        if ((lab[coordinate / width][coordinate % width] & 1) > 0
                && !visited[coordinate / width - 1][coordinate % width]) {
            testLab[coordinate / width][coordinate % width] |= 1;
            testLab[coordinate / width - 1][coordinate % width] |= 4;
            traverseLabyrinthAndSaveEdges(lab, testLab, visited, coordinate - width);
        }
        if ((lab[coordinate / width][coordinate % width] & 8) > 0
                && !visited[coordinate / width][coordinate % width - 1]) {
            testLab[coordinate / width][coordinate % width] |= 8;
            testLab[coordinate / width][coordinate % width - 1] |= 2;
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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
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
        boolean[][] visited = new boolean[height][width];
        traverseLabyrinth(l.labyrinth, visited, 0, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                assertTrue(visited[i][j]);
            }
        }
    }

    @Test
    public void regeneratingLabyrinthCreatesANewLabyrinth() throws Exception {
        Assume.assumeNotNull(l.lg);
        AlgorithmAddsAllCellsToLabyrinth();
        int checksum = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                checksum = checksum + (l.labyrinth[i][j] + i) * j;
            }
        }
        l.lg.createEmptyLabyrinthIfNeeded();
        AlgorithmAddsAllCellsToLabyrinth();
        int checksum2 = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                checksum2 = checksum + (l.labyrinth[i][j] + i) * j;
            }
        }
        assertTrue(checksum != checksum2);
    }

    @Test
    public void listingUnvisitedNeighbors() {
        Assume.assumeNotNull(l.lg);
        int[][] visited = new int[height][width];
        assertEquals(2, l.getListOfNeighbors(0, visited, 0).size());
        assertEquals(3, l.getListOfNeighbors(width / 2, visited, 0).size());
        assertEquals(4, l.getListOfNeighbors(width + 5, visited, 0).size());
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
        int coordinate = width * height / 2 + width / 2;
        Labyrinth testLabyrinth = new Labyrinth(width, height);
        traverseLabyrinthAndSaveEdges(l.labyrinth, testLabyrinth.labyrinth, new boolean[width][height], coordinate);
        int checksum1 = 0, checksum2 = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                checksum1 += l.labyrinth[i][j];
                checksum2 += testLabyrinth.labyrinth[i][j];
            }
        }
        assertTrue(checksum1 == checksum2);
    }
}
