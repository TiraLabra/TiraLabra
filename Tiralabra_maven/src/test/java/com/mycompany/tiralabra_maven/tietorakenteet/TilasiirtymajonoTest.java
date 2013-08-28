
package com.mycompany.tiralabra_maven.tietorakenteet;

import com.mycompany.tiralabra_maven.automaatit.Tila;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public class TilasiirtymajonoTest {
    
    private Tilasiirtymajono tsj;
    
    public TilasiirtymajonoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.tsj = new Tilasiirtymajono();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAvainjono() {
        assertNull(tsj.avainjono());
        
        Tila t = new Tila(false), u = new Tila(true), v = new Tila(false);
        
        Jono<Character> odotettuAvainjono
                = new Jono<>('a', 'b', 'c', 'b', null, null, null);
        
        tsj.lisaa('a', t);
        tsj.lisaa('b', t);
        tsj.lisaa('c', v);
        tsj.lisaa('b', u);
        tsj.lisaa(null, null);
        tsj.lisaa(null, null);
        tsj.lisaa(null, v);
        Jono saatuAvainjono = tsj.avainjono(); 
        
        AvainArvoJonoTest.vertaaJonoja(odotettuAvainjono, saatuAvainjono);
    }

    @Test
    public void testArvojono() {
        assertNull(tsj.arvojono());
        
        Tila t = new Tila(false), u = new Tila(true), v = new Tila(false);
        
        Jono<Tila> odotettuTilajono
                = new Jono<>(t, t, v, u, null, null, v);
        
        tsj.lisaa('a', t);
        tsj.lisaa('b', t);
        tsj.lisaa('c', v);
        tsj.lisaa('b', u);
        tsj.lisaa(null, null);
        tsj.lisaa(null, null);
        tsj.lisaa(null, v);
        Jono saatuTilajono = tsj.arvojono(); 
        
        AvainArvoJonoTest.vertaaJonoja(odotettuTilajono, saatuTilajono);
    }
}