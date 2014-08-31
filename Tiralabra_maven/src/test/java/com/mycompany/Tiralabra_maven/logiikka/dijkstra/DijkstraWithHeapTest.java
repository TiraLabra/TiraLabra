package com.mycompany.Tiralabra_maven.logiikka.dijkstra;

import com.mycompany.Tiralabra_maven.logiikka.dijkstra.DijkstraWithHeap;
import com.mycompany.Tiralabra_maven.logiikka.Piste;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hannu
 */
public class DijkstraWithHeapTest {

    Piste lahtoPiste;
    Piste maaliPiste;

    public DijkstraWithHeapTest() {
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
    public void toimiikoDijkstra() {

        int e = Integer.MAX_VALUE / 10;
        int[][] kartta = new int[][]{
            {1, 1, 1, 1, 1},
            {1, e, e, e, 1},
            {1, 1, 1, e, 1},
            {1, 1, 1, 1, 1}
        };

        lahtoPiste = new Piste(2, 1);
//        Piste maaliPiste = new Piste(1, 4);
//        Piste maaliPiste = new Piste(2, 2);
        maaliPiste = new Piste(0, 2);
//        Piste maaliPiste = new Piste(2, 1);

        boolean minunKeko = true;

        DijkstraWithHeap dijkstraWithHeap = new DijkstraWithHeap(kartta, lahtoPiste, maaliPiste, minunKeko);

        assertEquals(5, dijkstraWithHeap.ratkaise());
    }
}
