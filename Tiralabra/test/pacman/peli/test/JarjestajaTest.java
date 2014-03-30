/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.peli.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.alusta.Peliruutu;
import pacman.peli.Jarjestaja;

/**
 *
 * @author Hanna
 */
public class JarjestajaTest {

    private Jarjestaja jar;
    private Peliruutu[]solmut;

    public JarjestajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        solmut = new Peliruutu[4];
        
        for (int i = 0; i < 4; i++) {
            solmut[i] = new Peliruutu(i, i);
        }
        solmut[0].setEtaisyysAlkuun(3);
        solmut[0].setEtaisyysMaaliin(7);
        solmut[1].setEtaisyysAlkuun(1);
        solmut[1].setEtaisyysMaaliin(2);
        solmut[2].setEtaisyysAlkuun(10);
        solmut[2].setEtaisyysMaaliin(2);
        solmut[3].setEtaisyysAlkuun(3);
        solmut[3].setEtaisyysMaaliin(3);
        jar = new Jarjestaja(solmut);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void jarjestaaOikein() {
        jar.mergeSort(0, solmut.length-1);
        assertEquals(3, solmut[0].getEtaisyysAlkuun()+solmut[0].getEtaisyysMaaliin());
        assertEquals(6, solmut[1].getEtaisyysAlkuun()+solmut[1].getEtaisyysMaaliin());
        assertEquals(10, solmut[2].getEtaisyysAlkuun()+solmut[2].getEtaisyysMaaliin());
        assertEquals(12, solmut[3].getEtaisyysAlkuun()+solmut[3].getEtaisyysMaaliin());
    }
    
    @Test
    public void jarjestaaOikeinMyosNegatiivisia() {
        solmut[0].setEtaisyysAlkuun(-3);
        solmut[0].setEtaisyysMaaliin(-10);
        jar.mergeSort(0, solmut.length-1);
        assertEquals(-13, solmut[0].getEtaisyysAlkuun()+solmut[0].getEtaisyysMaaliin());
        assertEquals(3, solmut[1].getEtaisyysAlkuun()+solmut[1].getEtaisyysMaaliin());
        assertEquals(6, solmut[2].getEtaisyysAlkuun()+solmut[2].getEtaisyysMaaliin());
        assertEquals(12, solmut[3].getEtaisyysAlkuun()+solmut[3].getEtaisyysMaaliin());
    }
    
    @Test
    public void eiTeeMitaanJosKaikkiSamanArvoisia() {
        solmut[0].setEtaisyysAlkuun(0);
        solmut[0].setEtaisyysMaaliin(0);
        solmut[1].setEtaisyysAlkuun(0);
        solmut[1].setEtaisyysMaaliin(0);
        jar.setLista(solmut);
        assertEquals("(0,0)", solmut[0].toString());
        assertEquals("(1,1)", solmut[1].toString());
    }
}
