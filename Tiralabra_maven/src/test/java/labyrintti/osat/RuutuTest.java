/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.osat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heidvill@cs
 */
public class RuutuTest {

    private Ruutu ruutu;

    public RuutuTest() {
    }

    @Before
    public void setUp() {
        ruutu = new Ruutu(0, 0, 0);
    }

    @After
    public void tearDown() {
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
    public void etaisyyksienSummaOikein(){
        ruutu.laskeEtaisyysMaaliin(1, 2);
        ruutu.setEtaisyysAlkuun(1);
        assertEquals(4, ruutu.getEtaisyyksienSumma());
    }
    
    @Test
    public void equalsToimii(){
        Object obj = null;
        assertEquals(false, ruutu.equals(obj));
        String mj = (String) obj;
        assertEquals(false, ruutu.equals(mj));
        Ruutu verrattava1 = new Ruutu(0, 1, 0);
        assertEquals(false, ruutu.equals(verrattava1));
        Ruutu verrattava2 = new Ruutu(0, 0, 0);
        assertEquals(true, ruutu.equals(verrattava2));
    }
    
    @Test
    public void compareToimii(){
        Ruutu verrattava = new Ruutu(0, 0, 0);
        int palautus = ruutu.compareTo(verrattava);
        assertEquals(true, palautus==0);
        
        verrattava.setEtaisyysAlkuun(4);
        palautus = ruutu.compareTo(verrattava);
        assertEquals(true, palautus>0);
        
//        verrattava.setEtaisyysAlkuun(-5);
//        palautus = ruutu.compareTo(verrattava);
//        assertEquals(true, palautus>0);
    }
}
