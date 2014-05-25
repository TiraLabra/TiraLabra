/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HikeTests;

import Hike.Graph.Node;
import Hike.Values;
import java.util.List;
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
public class NodeTests {

    private Node[][] table;

    public NodeTests() {
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void neighboursTest() {
        table = new Node[Values.IMAGEHEIGHT][Values.IMAGEWIDTH];
        for (int i = 0; i < Values.IMAGEHEIGHT; i++) {
            for (int z = 0; z < Values.IMAGEWIDTH; z++) {
                table[i][z] = new Node(i, z, 1);

            }
        }
        table[0][0].setNeighbours(table);
        table = table[0][0].getTable();

        List neighbours = table[0][0].getNeighbours();
        assertEquals(3, neighbours.size());
        
        neighbours = table[10][10].getNeighbours();
        assertEquals(8, neighbours.size());
        
    }
}