/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.osat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heidvill@cs
 */
public class RuutuTest {

    /**
     * Testattava ruutu.
     */
    private Ruutu ruutu;

    @Before
    public void setUp() {
        ruutu = new Ruutu(0, 0, 0);
    }

    @Test
    public void ruutuLuodaanOikein() {
        assertEquals(0, ruutu.getArvo());
        assertEquals(2000000000, ruutu.getEtaisyysAlkuun());
        assertEquals(2000000000, ruutu.getEtaisyysLoppuun());
    }

    @Test
    public void ruudunEtaisyysarvioMaaliinOikein() {
        ruutu.laskeEtaisyysMaaliin(1, 2);
        assertEquals(3, ruutu.getEtaisyysLoppuun());
    }

    @Test
    public void etaisyyksienSummaOikein() {
        ruutu.laskeEtaisyysMaaliin(1, 2);
        ruutu.setEtaisyysAlkuun(1);
        assertEquals(4, ruutu.getEtaisyyksienSumma());
    }

    @Test
    public void equalsToimii() {
        Object obj = null;
        assertEquals(false, ruutu.equals(obj));
        String mj = "asdf";
        assertEquals(false, ruutu.equals(mj));
        Ruutu verrattava1 = new Ruutu(0, 1, 0);
        assertEquals(false, ruutu.equals(verrattava1));
        Ruutu verrattava2 = new Ruutu(0, 0, 0);
        assertEquals(true, ruutu.equals(verrattava2));
    }
}
