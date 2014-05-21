package labyrinthgenerator;

import main.Labyrinth;
import static org.junit.Assert.*;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

public class LabyrinthGeneratorTest {

    LabyrinthGenerator lg;
    public Labyrinth labyrinth;

    @Before
    public void setUp() {
        labyrinth = new Labyrinth(20, 20);
    }

    public void traverseLabyrinth(byte[][] labyrinth, boolean[][] visited, int coordinate, int width) {
        visited[coordinate / width][coordinate % width] = true;

        if ((labyrinth[coordinate / width][coordinate % width] & 2) > 0
                && !visited[coordinate / width][coordinate % width + 1]) {
            traverseLabyrinth(labyrinth, visited, coordinate + 1, width);
        }
        if ((labyrinth[coordinate / width][coordinate % width] & 4) > 0
                && !visited[coordinate / width + 1][coordinate % width]) {
            traverseLabyrinth(labyrinth, visited, coordinate + width, width);
        }
        if ((labyrinth[coordinate / width][coordinate % width] & 1) > 0
                && !visited[coordinate / width - 1][coordinate % width]) {
            traverseLabyrinth(labyrinth, visited, coordinate - width, width);
        }
        if ((labyrinth[coordinate / width][coordinate % width] & 8) > 0
                && !visited[coordinate / width][coordinate % width - 1]) {
            traverseLabyrinth(labyrinth, visited, coordinate - 1, width);
        }
    }

    public void traverseLabyrinthAndSaveEdges(byte[][] lab, byte[][] testLab, boolean[][] visited, int coordinate) {
        visited[coordinate / labyrinth.width][coordinate % labyrinth.width] = true;

        if ((lab[coordinate / labyrinth.width][coordinate % labyrinth.width] & 2) > 0
                && !visited[coordinate / labyrinth.width][coordinate % labyrinth.width + 1]) {
            testLab[coordinate / labyrinth.width][coordinate % labyrinth.width] |= 2;
            testLab[coordinate / labyrinth.width][coordinate % labyrinth.width + 1] |= 8;
            traverseLabyrinthAndSaveEdges(lab, testLab, visited, coordinate + 1);
        }
        if ((lab[coordinate / labyrinth.width][coordinate % labyrinth.width] & 4) > 0
                && !visited[coordinate / labyrinth.width + 1][coordinate % labyrinth.width]) {
            testLab[coordinate / labyrinth.width][coordinate % labyrinth.width] |= 4;
            testLab[coordinate / labyrinth.width + 1][coordinate % labyrinth.width] |= 1;
            traverseLabyrinthAndSaveEdges(lab, testLab, visited, coordinate + labyrinth.width);
        }
        if ((lab[coordinate / labyrinth.width][coordinate % labyrinth.width] & 1) > 0
                && !visited[coordinate / labyrinth.width - 1][coordinate % labyrinth.width]) {
            testLab[coordinate / labyrinth.width][coordinate % labyrinth.width] |= 1;
            testLab[coordinate / labyrinth.width - 1][coordinate % labyrinth.width] |= 4;
            traverseLabyrinthAndSaveEdges(lab, testLab, visited, coordinate - labyrinth.width);
        }
        if ((lab[coordinate / labyrinth.width][coordinate % labyrinth.width] & 8) > 0
                && !visited[coordinate / labyrinth.width][coordinate % labyrinth.width - 1]) {
            testLab[coordinate / labyrinth.width][coordinate % labyrinth.width] |= 8;
            testLab[coordinate / labyrinth.width][coordinate % labyrinth.width - 1] |= 2;
            traverseLabyrinthAndSaveEdges(lab, testLab, visited, coordinate - 1);
        }
    }

    @Test
    public void algorithmAddsAllCellsToLabyrinth() {
        Assume.assumeNotNull(lg);
        lg.generateLabyrinth();
        for (int i = 0; i < labyrinth.height; i++) {
            for (int j = 0; j < labyrinth.width; j++) {
                assertTrue(labyrinth.labyrinth[i][j] > 0);
            }
        }
    }

    @Test
    public void allCellsAreVisitableInGeneratedLabyrinth() {
        Assume.assumeNotNull(lg);
        if (labyrinth.labyrinth[0][0] == 0) {
            algorithmAddsAllCellsToLabyrinth();
        }
        boolean[][] visited = new boolean[labyrinth.height][labyrinth.width];
        traverseLabyrinth(labyrinth.labyrinth, visited, 0, labyrinth.width);
        for (int i = 0; i < labyrinth.height; i++) {
            for (int j = 0; j < labyrinth.width; j++) {
                assertTrue(visited[i][j]);
            }
        }
    }

    @Test
    public void regeneratingLabyrinthCreatesANewLabyrinth() {
        Assume.assumeNotNull(lg);
        algorithmAddsAllCellsToLabyrinth();
        int checksum = 0;
        for (int i = 0; i < labyrinth.height; i++) {
            for (int j = 0; j < labyrinth.width; j++) {
                checksum = checksum + (labyrinth.labyrinth[i][j] + i) * j;
            }
        }
        algorithmAddsAllCellsToLabyrinth();
        int checksum2 = 0;
        for (int i = 0; i < labyrinth.height; i++) {
            for (int j = 0; j < labyrinth.width; j++) {
                checksum2 = checksum + (labyrinth.labyrinth[i][j] + i) * j;
            }
        }
        assertTrue(checksum != checksum2);
    }

    @Test
    public void listingUnvisitedNeighbors() {
        Assume.assumeNotNull(lg);
        int[][] visited = new int[labyrinth.height][labyrinth.width];
        assertEquals(2, lg.labyrinth.getListOfUnvisitedNeighbors(0, visited).size());
        assertEquals(3, lg.labyrinth.getListOfUnvisitedNeighbors(labyrinth.width / 2, visited).size());
        assertEquals(4, lg.labyrinth.getListOfUnvisitedNeighbors(labyrinth.width + 5, visited).size());
    }

    @Test
    public void creatingBigLabyrinth() {
        Assume.assumeNotNull(lg);
        labyrinth.updateLabyrinth(100, 100);
        long startTime = System.currentTimeMillis();
        algorithmAddsAllCellsToLabyrinth();
        assertTrue(System.currentTimeMillis() - startTime < 1000);
        allCellsAreVisitableInGeneratedLabyrinth();
    }

    @Test
    public void labyrinthIsSpanningTree() {
        Assume.assumeNotNull(lg);
        lg.generateLabyrinth();
        /*
         Start somewhere in the middle.
         */
        int coordinate = labyrinth.width * labyrinth.height / 2 + labyrinth.width / 2;
        Labyrinth testLabyrinth = new Labyrinth(labyrinth.width, labyrinth.height);
        traverseLabyrinthAndSaveEdges(labyrinth.labyrinth, testLabyrinth.labyrinth, new boolean[labyrinth.width][labyrinth.height], coordinate);
        int checksum1 = 0, checksum2 = 0;
        for (int i = 0; i < labyrinth.height; i++) {
            for (int j = 0; j < labyrinth.width; j++) {
                checksum1 += labyrinth.labyrinth[i][j];
                checksum2 += testLabyrinth.labyrinth[i][j];
            }
        }
        assertTrue(checksum1 == checksum2);
    }
}
