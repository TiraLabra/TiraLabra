/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HikeTests.LinkyList;

import Hike.graph.Edge;
import Hike.graph.Node;
import Hike.structures.LinkyList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author petri
 */
public class LinkyListTest {

    private LinkyList list;

    public LinkyListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        list = new LinkyList();

    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void emptyList() {
        assertEquals(0, list.size());

    }

    @Test
    public void OneElementList() {
        list.add(new Edge(new Node(2, 2, 2), new Node(2, 2, 2), 2));
        assertEquals(1, list.size());
        assertEquals(null, list.getTopElement().next);
    }

    @Test
    public void OneElementListNextIsNull() {
        list.add(new Edge(new Node(2, 2, 2), new Node(2, 2, 2), 2));
        assertNull(list.getTopElement().next);
    }

    @Test
    public void TwoElementList() {
        list.add(new Edge(new Node(2, 2, 2), new Node(2, 2, 2), 2));
        list.add(new Edge(new Node(2, 2, 2), new Node(2, 2, 2), 2));
        assertEquals(2, list.size());
    }

    @Test
    public void TwoElementListNextIsNotNull() {
        list.add(new Edge(new Node(2, 2, 2), new Node(2, 2, 2), 2));
        list.add(new Edge(new Node(2, 2, 2), new Node(2, 2, 2), 2));
        assertNotNull(list.getTopElement().next);
    }
}