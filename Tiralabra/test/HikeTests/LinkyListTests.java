/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HikeTests;

import Hike.Graph.Node;
import Hike.Structures.LinkyList;
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
public class LinkyListTests {

    private LinkyList list;

    public LinkyListTests() {
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
        list.add(new Node(2, 2, 2));
        assertEquals(1, list.size());
        assertEquals(null, list.getTopElement().next);
    }

    @Test
    public void OneElementListNextIsNull() {
        list.add(new Node(2, 2, 2));
        assertNull(list.getTopElement().next);
    }

    @Test
    public void TwoElementList() {
        list.add(new Node(2, 2, 2));
        list.add(new Node(3, 3, 2));
        assertEquals(2, list.size());
    }

    @Test
    public void TwoElementListNextIsNotNull() {
        list.add(new Node(2, 2, 2));
        list.add(new Node(3, 3, 2));
        assertNotNull(list.getTopElement().next);
    }
}