/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencealgorithms;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author riha
 */
public class AlignmentMatrixTest {

    AlignmentMatrix m = new AlignmentMatrix(5, 5);

    public AlignmentMatrixTest() {
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
    public void setUpAlignmentMatrixTest() {
        assertEquals(0.0, m.get(0, 0), 0.00001);
        assertEquals(0.0, m.get(5, 5), 0.00001);
    }

    @Test
    public void getBestScoreAtSetUpTest() {
        assertEquals(-1E100, m.getBestScore(), 0.00001);
    }

    @Test
    public void getBestScoreCoordsAtSetUpTest() {
        assertEquals(-1, m.getBestScoreX());
        assertEquals(-1, m.getBestScoreY());
    }

    @Test
    public void setScoreTest() {
        m.setScore(3, 3, 5);
        assertEquals(5.0, m.get(3, 3), 0.00001);
    }

    @Test
    public void setScoreEdgeTest() {
        m.setScore(5, 5, 10);
        assertEquals(10, m.get(5, 5), 0.00001);
    }

    @Test
    public void bestScoreTest() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                m.setScore(i, j, (6 * i + j) % 14);
            }
        }
        assertEquals(13, m.getBestScore(), 0.00001);
    }

    @Test
    public void bestScoreCoordTest() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                m.setScore(i, j, 10-(i-3)*(i-3) + 10-(j-2)*(j-2));
            }
        }
        assertEquals(3, m.getBestScoreX());
        assertEquals(2, m.getBestScoreY());
    }

}
