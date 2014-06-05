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
        int[][] visited = new int[l.height][l.width];
        assertEquals(0, l.getListOfVisitedNeighbors(0, visited).size());
        assertEquals(0, l.getListOfVisitedNeighbors(l.width / 2, visited).size());
        assertEquals(0, l.getListOfVisitedNeighbors(l.width + 5, visited).size());
    }
}
