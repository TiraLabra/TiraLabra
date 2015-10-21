package CoreLogic;

import Terrain.CartesianMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

//@author Leevi
public class AStarPathfinderTest {

    public AStarPathfinderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of determineRoute method, of class AStarPathfinder.
     */
    @Test
    public void testDetermineRoute() {
        System.out.println("determineRoute");
        long startTime = System.currentTimeMillis();
        
        CartesianMap map = new CartesianMap();
        map.generateTerrain(true);
        AStarPathfinder finder = new AStarPathfinder(map);
        int[][] route = finder.determineRoute(0, 0, 18, 18);

        long total = 0;
        for (int i = 0; i < 10000000; i++) {
            total += i;
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Route finding took " + elapsedTime + " ms");
    }

}
