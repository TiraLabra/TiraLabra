package labyrinthgenerator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PrimsAlgorithmTest extends LabyrinthGeneratorTest {

    @Before
    @Override
    public void setUp() {
        super.setUp();
        lg = new PrimsAlgorithm(labyrinth);
    }

    @Test
    public void listingVisitedNeighbors() {
        PrimsAlgorithm pa = new PrimsAlgorithm(labyrinth);
        int[][] visited = new int[labyrinth.height][labyrinth.width];
        assertEquals(0, pa.getListOfVisitedNeighbors(0, visited).size());
        assertEquals(0, pa.getListOfVisitedNeighbors(labyrinth.width / 2, visited).size());
        assertEquals(0, pa.getListOfVisitedNeighbors(labyrinth.width + 5, visited).size());
    }
}
