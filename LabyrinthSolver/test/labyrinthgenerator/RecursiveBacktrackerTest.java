package labyrinthgenerator;

import org.junit.Before;

public class RecursiveBacktrackerTest extends LabyrinthGeneratorTest {

    @Before
    @Override
    public void setUp() {
        super.setUp();
        try {
            l.setLabyrinthGenerator(new RecursiveBacktracker());
        } catch (Exception ex) {
        }
    }

}
