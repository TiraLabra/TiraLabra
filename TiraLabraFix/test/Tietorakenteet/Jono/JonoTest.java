/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet.Jono;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Serafim
 */
public class JonoTest {

    private Jono jono;

    public JonoTest() {
    }

    @Before
    public void setUp() {
        this.jono = new Jono();

    }

    /**
     * Test of palautaKoko method, of class Jono.
     */
    @Test
    public void testPalautaKokoAlussanolla() {
        assertEquals(jono.palautaKoko(), 0);
    }

    /**
     * Test of lisaa method, of class Jono.
     */
    @Test
    public void testYhdenalkionlisaaminenkoko() {
        jono.lisaa(2);
        assertEquals(jono.palautaKoko(), 1);

    }

    /**
     * Test of lisaa method, of class Jono.
     */
    @Test
    public void testKolmentoistaalkionlisaaminentoimii() {
        for (int i = 1; i < 14; i++) {
            jono.lisaa(i);

        }
        assertEquals(jono.palautaKoko(), 13);
    }

    @Test
    public void testEnsimmainenalkioonNolla() {
        assertEquals(jono.palautaEnsimmainen(), null);
    }

    @Test
    public void testLisaataanYksialkio() {
        jono.lisaa(1);
        assertEquals(jono.palautaEnsimmainen().palautaObjekti(), 1);
    }

    @Test
    public void testLisaataanYksialkioViimienoikein() {
        jono.lisaa(1);
        assertEquals(jono.palautaViimeinen().palautaObjekti(), 1);
    }

    @Test
    public void testLisaataanKaksiialkiotaViimienoikein() {
        jono.lisaa(1);
        jono.lisaa(2);
        assertEquals(jono.palautaViimeinen().palautaObjekti(), 2);
    }

    @Test
    public void testLisaataanKaksialkiotaPalautaEnsimmaisenoikein() {
        jono.lisaa(1);
        jono.lisaa(2);
        assertEquals(jono.palautaViimeinen().palautaObjekti(), 2);
    }

    @Test
    public void testLisaataanKaksialkiotaPalauttaaEnsimmaisenalkionseuraajanoikein() {
        Jonoiteroitava eka = new Jonoiteroitava(1);
        Jonoiteroitava toka = new Jonoiteroitava(2);
        jono.lisaa(eka);
        jono.lisaa(toka);
        assertEquals(eka.palautaSeuraava(), toka);
    }
    @Test
    public void massiivineniterointitoimii() {
        for (int i = 1; i < 11; i++)
        {
        jono.lisaa(i);
        
        }
        Jonoiteroitava iter = jono.palautaEnsimmainen();
        int sum = 0;
        while(iter != null)
        {
        int j = (int) iter.palautaObjekti();
        sum = sum + j;
        iter = iter.palautaSeuraava();
        }
        
        
        
        assertEquals(sum, 55);
    }

    /**
     * Test of poista method, of class Jono.
     */
    @Test
    public void testPoista() {

    }

    /**
     * Test of palautaEnsimmainen method, of class Jono.
     */
    @Test
    public void testPalautaEnsimmainen() {

    }

}
