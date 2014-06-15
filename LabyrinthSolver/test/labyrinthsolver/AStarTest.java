package labyrinthsolver;

import org.junit.Before;

/**
 *
 * @author Juri Kuronen
 */
public class AStarTest extends LabyrinthSolverTest {

    @Before
    @Override
    public void setUp() {
        super.setUp();
        l.setLabyrinthSolver(new AStar());
    }

}
