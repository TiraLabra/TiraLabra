
package com.mycompany.tiralabra_maven;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author noora
 */
public class ListaTest {
    
    public ListaTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of lisaa method, of class Lista.
     */
    @Test
    public void listalleVoiLisata() {
        Lista lista = new Lista();
        lista.lisaa(5);
        lista.lisaa(6);
        assertEquals(5, lista.getAlkio(0));
        assertEquals(6, lista.getAlkio(1));
    }
    
    @Test
    public void taulukonKasvatusToimii() {
        Lista lista = new Lista();
        for (int i = 0; i < 110; i++){
            lista.lisaa(i);
        }
        assertEquals(105, lista.getAlkio(105));
    }
    
    @Test
    public void taulukostaSaaSuurimmanArvon() {
        Lista lista = new Lista();
        lista.lisaa(6);
        lista.lisaa(100);
        lista.lisaa(34);
        assertEquals(100, lista.getSuurin());
    }
}
