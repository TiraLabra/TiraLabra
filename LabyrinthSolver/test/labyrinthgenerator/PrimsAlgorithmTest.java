package labyrinthgenerator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PrimsAlgorithmTest extends LabyrinthGeneratorTest {

    @Before
    @Override
    public void setUp() {
        super.setUp();
        try {
            l.setLabyrinthGenerator(new PrimsAlgorithm());
        } catch (Exception ex) {
        }
    }

    @Test
    public void listingVisitedNeighbors() {
        width = l.getWidth();
        height = l.getHeight();
        int[][] visited = new int[height][width];
        assertEquals(0, l.getListOfNeighbors(0, visited, 2).size());
        assertEquals(0, l.getListOfNeighbors(width / 2, visited, 2).size());
        assertEquals(0, l.getListOfNeighbors(width + 5, visited, 2).size());
    }
}
