package labyrinthgenerator;

import main.Labyrinth;
import static org.junit.Assert.*;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

public class LabyrinthGeneratorTest {

    Labyrinth l;
    byte[][] labyrinth;
    int width;
    int height;

    @Before
    public void setUp() {
        l = new Labyrinth(20, 20);
        width = l.getWidth();
        height = l.getHeight();
    }

    byte[][] generateByteArray(Labyrinth lab) {
        byte[][] array = new byte[lab.getHeight()][lab.getWidth()];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array[i][j] = lab.getEdges(i * width + j);
            }
        }
        return array;
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
        if (l.lg == null) {
            return;
        }
        l.lg.generateLabyrinth();
        l.lg.createEmptyLabyrinthIfNeeded();
        labyrinth = generateByteArray(l);
        assertTrue(labyrinth[0][0] == 0);
    }

    @Test
    public void AlgorithmAddsAllCellsToLabyrinth() throws Exception {
        if (l.lg == null) {
            return;
        }
        l.lg.generateLabyrinth();
        labyrinth = generateByteArray(l);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                assertTrue(labyrinth[i][j] > 0);
            }
        }
    }

    @Test
    public void allCellsAreVisitableInGeneratedLabyrinth() throws Exception {
        if (l.lg == null) {
            return;
        }
        AlgorithmAddsAllCellsToLabyrinth();
        boolean[][] visited = new boolean[height][width];
        traverseLabyrinth(labyrinth, visited, 0, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                assertTrue(visited[i][j]);
            }
        }
    }

    @Test
    public void regeneratingLabyrinthCreatesANewLabyrinth() throws Exception {
        if (l.lg == null) {
            return;
        }
        AlgorithmAddsAllCellsToLabyrinth();
        int checksum = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                checksum = checksum + (labyrinth[i][j] + i) * j;
            }
        }
        l.lg.createEmptyLabyrinthIfNeeded();
        AlgorithmAddsAllCellsToLabyrinth();
        int checksum2 = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                checksum2 = checksum + (labyrinth[i][j] + i) * j;
            }
        }
        assertTrue(checksum != checksum2);
    }

    @Test
    public void creatingBigLabyrinth() throws Exception {
        if (l.lg == null) {
            return;
        }
        l.updateLabyrinth(100, 100);
        width = l.getWidth();
        height = l.getHeight();
        long startTime = System.currentTimeMillis();
        AlgorithmAddsAllCellsToLabyrinth();
        assertTrue(System.currentTimeMillis() - startTime < 1000);
        allCellsAreVisitableInGeneratedLabyrinth();
    }

    @Test
    public void labyrinthIsSpanningTree() throws Exception {
        if (l.lg == null) {
            return;
        }
        l.lg.generateLabyrinth();
        labyrinth = generateByteArray(l);
        /*
         Start somewhere in the middle.
         */
        int coordinate = width * height / 2 + width / 2;
        Labyrinth testL = new Labyrinth(width, height);
        byte[][] testLabyrinth = generateByteArray(testL);
        traverseLabyrinthAndSaveEdges(labyrinth, testLabyrinth, new boolean[width][height], coordinate);
        int checksum1 = 0, checksum2 = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                checksum1 += labyrinth[i][j];
                checksum2 += testLabyrinth[i][j];
            }
        }
        assertTrue(checksum1 == checksum2);
    }
}
