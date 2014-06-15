package labyrinthsolver;

import org.junit.Before;

/**
 *
 * @author Juri Kuronen
 */
public class BFSTest extends LabyrinthSolverTest {

    @Before
    @Override
    public void setUp() {
        super.setUp();
        l.setLabyrinthSolver(new BFS());
    }

}
