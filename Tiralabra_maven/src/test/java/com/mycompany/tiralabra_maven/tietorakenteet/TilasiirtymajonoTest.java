
package com.mycompany.tiralabra_maven.tietorakenteet;

import com.mycompany.tiralabra_maven.automaatit.Tila;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TilasiirtymajonoTest {
    
    private Tilasiirtymajono tsj;
    
    @Before
    public void setUp() {
        this.tsj = new Tilasiirtymajono();
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
        
        JonoTest.vertaaJonoja(odotettuAvainjono, saatuAvainjono);
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
        
        JonoTest.vertaaJonoja(odotettuTilajono, saatuTilajono);
    }
}