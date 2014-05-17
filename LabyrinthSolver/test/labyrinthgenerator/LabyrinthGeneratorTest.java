package labyrinthgenerator;

import main.Labyrinth;
import static org.junit.Assert.*;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

public class LabyrinthGeneratorTest {

    /**
     * 1 = NORTH, 2 = EAST, 4 = SOUTH, 8 = WEST.
     */
    public final byte[] masks = {1, 2, 4, 8};

    LabyrinthGenerator lg;
    public Labyrinth labyrinth;

    @Before
    public void setUp() {
        labyrinth = new Labyrinth(10, 10);
    }

    public void traverseLabyrinth(byte[][] labyrinth, boolean[][] visited, int coordinate, int width) {
        visited[coordinate / width][coordinate % width] = true;

        if ((labyrinth[coordinate / width][coordinate % width] & masks[0]) > 0
                && !visited[coordinate / width - 1][coordinate % width]) {
            traverseLabyrinth(labyrinth, visited, coordinate - width, width);
        }
        if ((labyrinth[coordinate / width][coordinate % width] & masks[1]) > 0
                && !visited[coordinate / width][coordinate % width + 1]) {
            traverseLabyrinth(labyrinth, visited, coordinate + 1, width);
        }
        if ((labyrinth[coordinate / width][coordinate % width] & masks[2]) > 0
                && !visited[coordinate / width + 1][coordinate % width]) {
            traverseLabyrinth(labyrinth, visited, coordinate + width, width);
        }
        if ((labyrinth[coordinate / width][coordinate % width] & masks[3]) > 0
                && !visited[coordinate / width][coordinate % width - 1]) {
            traverseLabyrinth(labyrinth, visited, coordinate - 1, width);
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
        assertEquals(2, lg.getListOfUnvisitedNeighbors(0, visited).size());
        assertEquals(3, lg.getListOfUnvisitedNeighbors(labyrinth.width / 2, visited).size());
        assertEquals(4, lg.getListOfUnvisitedNeighbors(labyrinth.width + 5, visited).size());
    }

    @Test
    public void creatingBigLabyrinth() {
        Assume.assumeNotNull(lg);
        labyrinth = new Labyrinth(100, 100);
        lg.newLabyrinth(labyrinth);
        long startTime = System.currentTimeMillis();
        algorithmAddsAllCellsToLabyrinth();
        assertTrue(System.currentTimeMillis() - startTime < 1000);
        allCellsAreVisitableInGeneratedLabyrinth();
    }
}
