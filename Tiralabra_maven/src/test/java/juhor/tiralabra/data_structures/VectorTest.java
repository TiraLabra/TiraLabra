package juhor.tiralabra.data_structures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhorim
 */
public class VectorTest {

    public VectorTest() {
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

    @Test
    public void testAdd() {
        double[] a1 = {1, 1, 1};
        double[] a2 = {2, 2, 2};
        double[] a3 = {-1, -1, -1};
        double[] a4 = {0, 0, 0};

        Vector v1 = new Vector(a1);
        Vector v2 = new Vector(a2);
        Vector v3 = new Vector(a3);
        Vector v4 = new Vector(a4);

        v1.add(v2);
        assertTrue(v1.getValue(0) == 3);
        assertTrue(v1.getValue(1) == 3);
        assertTrue(v1.getValue(2) == 3);

        v1.add(v3);
        assertTrue(v1.getValue(0) == 2);
        assertTrue(v1.getValue(1) == 2);
        assertTrue(v1.getValue(2) == 2);

        v1.add(v4);
        assertTrue(v1.getValue(0) == 2);
        assertTrue(v1.getValue(0) == 2);
        assertTrue(v1.getValue(0) == 2);

    }

    @Test
    public void testSubstract() {
        double[] a1 = {1, 1, 1};
        double[] a2 = {2, 2, 2};
        double[] a3 = {-1, -1, -1};
        double[] a4 = {0, 0, 0};

        Vector v1 = new Vector(a1);
        Vector v2 = new Vector(a2);
        Vector v3 = new Vector(a3);
        Vector v4 = new Vector(a4);
        
        v1.substract(v2);
        assertTrue(v1.getValue(0) == -1);
        assertTrue(v1.getValue(1) == -1);
        assertTrue(v1.getValue(2) == -1);
        
        v1.substract(v3);
        assertTrue(v1.getValue(0) == 0);
        assertTrue(v1.getValue(1) == 0);
        assertTrue(v1.getValue(2) == 0);
        
        v3.substract(v2);
        assertTrue(v3.getValue(0) == -3);
        assertTrue(v3.getValue(1) == -3);
        assertTrue(v3.getValue(2) == -3);
    }

    @Test
    public void testDotProduct() {
        double[] a1 = {0, 0, 0};
        double[] a2 = {1, 2, 3};
        double[] a3 = {-3, 4, -5};

        Vector v1 = new Vector(a1);
        Vector v2 = new Vector(a2);
        Vector v3 = new Vector(a3);

        double d1 = v1.dotProduct(v2);
        assertTrue(d1 == 0);

        double d2 = v2.dotProduct(v3);
        assertTrue(d2 == -10);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
