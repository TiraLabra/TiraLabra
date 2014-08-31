/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

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
public class KordinaattiTest {

    public KordinaattiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of palautaX method, of class Kordinaatti.
     */
    /**
     * Test of equals method, of class Kordinaatti.
     */
    @Test
    public void yksinkertainenEqualsTestaus() {
      Kordinaatti k1 = new Kordinaatti(0,0);
      Kordinaatti k2 = new Kordinaatti(0,0);
      assertEquals(k1, k2);
    }
    @Test
    public void vahanvaikeempi() {
      Kordinaatti k1 = new Kordinaatti(1,2);
      Kordinaatti k2 = new Kordinaatti(1,2);
      assertEquals(k1, k2);
    }
    @Test
    public void vielavaikeempi() {
      Kordinaatti k1 = new Kordinaatti(1.09,2.01);
      Kordinaatti k2 = new Kordinaatti(1.09,2.01);
      assertEquals(k1, k2);
    }
    @Test
    public void vaikeempieii() {
      Kordinaatti k1 = new Kordinaatti(1.0901,2.01);
      Kordinaatti k2 = new Kordinaatti(1.09,2.01);
      boolean d = (k1 == k2);
      assertEquals(d, false);
    }
     @Test
    public void ehkvaikeampi() {
      Kordinaatti k1 = new Kordinaatti(1.09,2.01);
      Kordinaatti k2 = new Kordinaatti(1.09,2.010);
      assertEquals(k1, k2);
    }
    

    /**
     * Test of asetaArvo method, of class Kordinaatti.
     */
}
