/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tietorakenteett;

import Tietorakenteet.DiskreettiSolmu;
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
public class DiskreettiSolmutest {
    
    public DiskreettiSolmutest() {
    }
    
 
    @Test
    public void testPalautaX() {
     DiskreettiSolmu solmu = new DiskreettiSolmu(2,3);
     assertEquals(2, solmu.PalautaX(), 0.01);
    }

    /**
     * Test of PalautaY method, of class Solmu.
     */
    @Test
    public void testPalautaY() {
       DiskreettiSolmu solmu = new DiskreettiSolmu(2,3);
     assertEquals(3, solmu.PalautaY(), 0.01);
    }

    /**
     * Test of Arvo method, of class Solmu.
     */
    @Test
    public void testArvo() {
      DiskreettiSolmu solmu = new DiskreettiSolmu(2,3);
      solmu.asetaArvo(3.53);
      assertEquals(3.53, solmu.KekoArvo(), 0.01);


    }

    /**
     * Test of SijaintiKeossa method, of class Solmu.
     */

    
}
