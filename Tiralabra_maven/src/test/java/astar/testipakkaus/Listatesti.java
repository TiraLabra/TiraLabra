/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.testipakkaus;

import astar.tietorakenteet.Lista;
import astar.verkko.Solmu;
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
public class Listatesti {

    private int size;
    private Object taulu[];
    private Lista lista;
    private ArrayList<Solmu> list;

    public Listatesti() {

        this.lista = new Lista();
        this.list = new ArrayList<>();
    }

    @Before
    public void setUp() {
    }

    @Test
    public void lisaystestiSolmu() {

        Solmu s = new Solmu(0, 0, null, 0);
        list.add(s);
        lista.add(s);

        assertEquals(lista.get(0), s);

    // TODO add test methods here.
        // The methods must be annotated with annotation @Test. For example:
        //
        // @Test
        // public void hello() {}
    }
}
