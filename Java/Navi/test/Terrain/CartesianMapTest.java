package Terrain;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

//@author Leevi
public class CartesianMapTest {

    public CartesianMapTest() {
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
     * Test of generateTerrain method, of class CartesianMap.
     */
    @Test
    public void testGenerateTerrain() {
        System.out.println("generateTerrain");
        boolean hasRoughTerrain = false;
        CartesianMap instance = null;
        instance.generateTerrain(hasRoughTerrain);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateTerrainPreset1 method, of class CartesianMap.
     */
    @Test
    public void testGenerateTerrainPreset1() {
        System.out.println("generateTerrainPreset1");
        CartesianMap instance = null;
        instance.generateTerrainPreset1();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSingleTile method, of class CartesianMap.
     */
    @Test
    public void testGetSingleTile() {
        System.out.println("getSingleTile");
        int xPos = 0;
        int yPos = 0;
        CartesianMap instance = null;
        int expResult = 0;
        int result = instance.getSingleTile(xPos, yPos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMap method, of class CartesianMap.
     */
    @Test
    public void testGetMap() {
        System.out.println("getMap");
        CartesianMap instance = null;
        int[][] expResult = null;
        int[][] result = instance.getMap();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAdjacentNodes method, of class CartesianMap.
     */
    @Test
    public void testGetAdjacentNodes() {
        System.out.println("getAdjacentNodes");
        int xPos = 0;
        int yPos = 0;
        CartesianMap instance = null;
        List<Integer> expResult = null;
        List<Integer> result = instance.getAdjacentNodes(xPos, yPos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayMap method, of class CartesianMap.
     */
    @Test
    public void testDisplayMap() {
        System.out.println("displayMap");
        CartesianMap instance = null;
        instance.displayMap();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayMapWithRoute method, of class CartesianMap.
     */
    @Test
    public void testDisplayMapWithRoute() {
        System.out.println("displayMapWithRoute");
        int[][] route = null;
        CartesianMap instance = null;
        instance.displayMapWithRoute(route);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}