
package viidensuora.ai;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import viidensuora.logiikka.Koordinaatti;

/**
 *
 * @author juha
 */
public class HakutulosTest {

    private Hakutulos ht;

    @Before
    public void setUp() {
        ht = new Hakutulos(new Koordinaatti(1, 2), 4, 666, 777, 2, 10);
    }

    @Test
    public void konstruktoriOikein() {
        assertTrue(ht.parasSiirto.x == 1 && ht.parasSiirto.y == 2);
        assertEquals(4, ht.siirronArvo);
        assertEquals(666, ht.hakuaika);
        assertEquals(777, ht.evaluoitujaTilanteita);
        assertEquals(2, ht.hakusyvyys);
        assertEquals(90, ht.hakupuunKoko.intValue());
        assertEquals(3628800, ht.pelipuunKoko.intValue());
    }

    @Test
    public void konstruktoriOikeinKunVapaitaRuutujaVahan() {
        ht = new Hakutulos(new Koordinaatti(1, 2), 4, 666, 777, 2, 1);
        assertTrue(ht.parasSiirto.x == 1 && ht.parasSiirto.y == 2);
        assertEquals(4, ht.siirronArvo);
        assertEquals(666, ht.hakuaika);
        assertEquals(777, ht.evaluoitujaTilanteita);
        assertEquals(1, ht.hakusyvyys);
        assertEquals(1, ht.hakupuunKoko.intValue());
        assertEquals(1, ht.pelipuunKoko.intValue());
    }

}
