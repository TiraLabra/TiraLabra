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


public class DiskreettiSolmuTest {
    
  
 
    @Test
    public void testPalautaX() {
     DiskreettiSolmu solmu = new DiskreettiSolmu(2,3);
     assertEquals(2, solmu.palautaX(), 0.01);
    }

    /**
     * Test of PalautaY method, of class Solmu.
     */
    @Test
    public void testPalautaY() {
       DiskreettiSolmu solmu = new DiskreettiSolmu(2,3);
     assertEquals(3, solmu.palautaY(), 0.01);
    }

    /**
     * Test of Arvo method, of class Solmu.
     */
    @Test
    public void testArvo() {
      DiskreettiSolmu solmu = new DiskreettiSolmu(2,3);
      solmu.asetaArvo(3.53);
      assertEquals(3.53, solmu.palautaSolmuMuisti().palautaFScore(), 0.01);


    }

    /**
     * Test of SijaintiKeossa method, of class Solmu.
     */

    
}
