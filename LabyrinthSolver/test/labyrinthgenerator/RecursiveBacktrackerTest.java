package labyrinthgenerator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RecursiveBacktrackerTest extends LabyrinthGeneratorTest {

    @Before
    @Override
    public void setUp() {
        super.setUp();
        lg = new RecursiveBacktracker(labyrinth);
    }

}
