/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HikeTests;

import Hike.Graph.Node;
import Hike.Structures.LinkyList;
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
public class ImageTableTests {

    private Node[][] table;

    public ImageTableTests() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        table = new Node[Values.IMAGEHEIGHT][Values.IMAGEWIDTH];
        for (int i = 0; i < Values.IMAGEHEIGHT; i++) {
            for (int z = 0; z < Values.IMAGEWIDTH; z++) {
                table[i][z] = new Node(i, z, 1);

            }
        }
        table[0][0].setNeighbours(table, Values.IMAGEHEIGHT, Values.IMAGEWIDTH);
        table = table[0][0].getTable();

    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void neighboursTestLeftTopCorner() {

        LinkyList neighbours = table[0][0].getNeighbours();

        assertEquals(3, neighbours.size());


    }

    @Test
    public void neighboursTestNonEdge() {

        LinkyList neighbours = table[10][10].getNeighbours();

        assertEquals(8, neighbours.size());

    }

    @Test
    public void neighboursTestRightBottomCorner() {

        LinkyList neighbours = table[Values.IMAGEHEIGHT - 1][Values.IMAGEWIDTH - 1].getNeighbours();

        assertEquals(3, neighbours.size());

    }

    @Test
    public void neighboursTestLeftBottomCorner() {

        LinkyList neighbours = table[Values.IMAGEHEIGHT - 1][0].getNeighbours();

        assertEquals(3, neighbours.size());

    }

    @Test
    public void neighboursTestRightTopCorner() {

        LinkyList neighbours = table[0][Values.IMAGEWIDTH - 1].getNeighbours();

        assertEquals(3, neighbours.size());

    }
}