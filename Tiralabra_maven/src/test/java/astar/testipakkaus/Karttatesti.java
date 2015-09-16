/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.testipakkaus;

import astar.astar.Kartta;
import astar.astar.Solmu;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sasumaki
 */
public class Karttatesti {

    Kartta kartta;
    private ArrayList<ArrayList<Solmu>> karttanen;

    public Karttatesti() {
    }

    @Before
    public void setUp() {
        this.kartta = new Kartta(10, 10,1,1,9,9);
    }

    @Test
    public void luoKarttaTesti() {
        ArrayList<ArrayList<Solmu>> testikartta = new ArrayList<ArrayList<Solmu>>();
        Solmu solmu;
        for (int x = 0; x < 10; x++) {
            karttanen.add(new ArrayList<Solmu>());
            for (int y = 0; y < 10; y++) {
                solmu = new Solmu(x, y, false, false, false);
                karttanen.get(x).add(solmu);
            }
        }
        assertEquals(testikartta, karttanen);
    }
}
