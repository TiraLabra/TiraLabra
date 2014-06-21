package labyrinthgenerator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import util.SetElement;

public class KruskalsAlgorithmTest extends LabyrinthGeneratorTest {

    public KruskalsAlgorithm ka;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        ka = new KruskalsAlgorithm();
        try {
            l.setLabyrinthGenerator(ka);
        } catch (Exception ex) {
        }
    }

    @Test
    public void dontSaveBadVertice() throws Exception {
        SetElement[] elements = new SetElement[9];
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
        edges[1] = 15;
        assertFalse(ka.saveVertice(edges, elements[4], elements));
    }

    @Test
    public void saveGoodVertice() throws Exception {
        SetElement[] elements = new SetElement[9];
        for (int i = 0; i < 9; i++) {
            elements[i] = new SetElement(i);
        }
        l.updateLabyrinth(3, 3);
        int[] edges = new int[2];
        edges[0] = 4;
        edges[1] = 15;
        assertTrue(ka.saveVertice(edges, elements[4], elements));
    }
}
