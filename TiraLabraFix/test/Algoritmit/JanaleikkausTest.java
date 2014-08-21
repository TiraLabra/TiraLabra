/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algoritmit;

import Tietorakenteet.Kordinaatti;
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
public class JanaleikkausTest {
    
    private Janaleikkaus testaaja;
    
    
    public JanaleikkausTest() {
    }
    
 
    
    @Before
    public void setUp() {
        this.testaaja = new Janaleikkaus();
    }
    
  

    /**
     * Test of leikkaako method, of class Janaleikkaus.
     */
    @Test
    public void testLeikkaakoyksinkertainenleikkaus() {
      Kordinaatti k1 = new Kordinaatti(0,0);
      Kordinaatti k2 = new Kordinaatti(1,1);
      Kordinaatti k3 = new Kordinaatti(0,1);
      Kordinaatti k4 = new Kordinaatti(1,0);
      assertEquals(testaaja.leikkaako(k1, k2, k3, k4), true);
    }
     @Test
    public void testeileikkaayksinkertainenleikkaus() {
      Kordinaatti k1 = new Kordinaatti(0,0);
      Kordinaatti k2 = new Kordinaatti(1,0);
      Kordinaatti k3 = new Kordinaatti(2,2);
      Kordinaatti k4 = new Kordinaatti(1,1);
      assertEquals(testaaja.leikkaako(k1, k2, k3, k4), false);
    }
     @Test
    public void Vaakasuorapystysuora() {
      Kordinaatti k1 = new Kordinaatti(0,1);
      Kordinaatti k2 = new Kordinaatti(2,1);
      Kordinaatti k3 = new Kordinaatti(1,0);
      Kordinaatti k4 = new Kordinaatti(1,2);
      assertEquals(testaaja.leikkaako(k1, k2, k3, k4), true);
    }
     @Test
    public void testaavaikeampitapaus() {
      Kordinaatti k1 = new Kordinaatti(0,0);
      Kordinaatti k2 = new Kordinaatti(2,0);
      Kordinaatti k3 = new Kordinaatti(0,0);
      Kordinaatti k4 = new Kordinaatti(2,0);
      assertEquals(testaaja.leikkaako(k1, k2, k3, k4), false);
    }
    
}
