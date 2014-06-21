package labyrinthgenerator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import util.SetElement;

public class KruskalsAlgorithmTest extends LabyrinthGeneratorTest {

    public KruskalsAlgorithm ka;
    public SetElement[] elements;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        elements = new SetElement[9];
        ka = new KruskalsAlgorithm();
        l.setLabyrinthGenerator(ka);
    }

    @Test
    public void dontKeepBadVertice() throws Exception {
        for (int i = 0; i < 9; i++) {
            if (i != 4 && i % 2 == 0) {
                elements[i] = new SetElement(i + 15);
            } else {
                elements[i] = new SetElement(1);
            }
        }
        l.updateLabyrinth(3, 3);
        int[] edges = new int[2];
        edges[0] = 4;
        edges[1] = 13;
        assertTrue(ka.removeUselessEdges(edges, elements[4], elements));
    }

    @Test
    public void keepGoodVertice() throws Exception {
        for (int i = 0; i < 9; i++) {
            elements[i] = new SetElement(i);
        }
        l.updateLabyrinth(3, 3);
        int[] edges = new int[2];
        edges[0] = 4;
        edges[1] = 13;
        assertFalse(ka.removeUselessEdges(edges, elements[4], elements));
    }

    @Test
    public void unionPerformedJoinsTwoSets() throws Exception {
        keepGoodVertice(); // To initialize labyrinth.
        ka.unionPerformed(elements, 0, (byte) 4);
        assertTrue(elements[0].getId() == elements[3].getId());
    }

    @Test
    public void unionPerformedAddsPassage() throws Exception {
        unionPerformedJoinsTwoSets();
        labyrinth = generateByteArray(l);
        assertTrue(labyrinth[0][0] == 4);
        assertTrue(labyrinth[1][0] == 1);
    }
}
