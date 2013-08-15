/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author albis
 */
public class LavaTest {
    private Lava lava;
    
    public LavaTest() {
    }
    
    @Before
    public void setUp() {
        lava = new Lava(80, 120, 130);
    }
    
    @Test
    public void leveysAsettuuOikein() {
        assertEquals(80, lava.getLeveys());
    }
    
    @Test
    public void pituusAsettuuOikein() {
        assertEquals(120, lava.getPituus());
    }
    
    @Test
    public void korkeusAsettuuOikein() {
        assertEquals(130, lava.getKorkeus());
    }
    
    @Test
    public void ruudutAluksiTyhjia() {
        boolean ovatkoTyhjia = true;
        
        for (int i = 0; i < lava.getLeveys(); i++) {
            for (int j = 0; j < lava.getPituus(); j++) {
                if (lava.onkoTyhja(i, j) != false) {
                    ovatkoTyhjia = true;
                }
            }
        }
        
        assertTrue(ovatkoTyhjia);
    }
    
    @Test
    public void ruudunTayttoOnnistuu() {
        boolean onkoOikein = true;
        
        lava.taytaRuutu(8, 4);
        
        for (int i = 0; i < lava.getLeveys(); i++) {
            for (int j = 0; j < lava.getPituus(); j++) {
                if (lava.onkoTyhja(i, j) != false) {
                    if (i != 8 && j != 4) {
                        if (lava.onkoTyhja(i, j) == true) {
                            onkoOikein = false;
                        }
                    } else {
                        if (lava.onkoTyhja(i, j) == false) {
                            onkoOikein = false;
                        }
                    }
                }
            }
            
            assertTrue(onkoOikein);
        }
    }
    
    public void laatikonMerkintaOnnistuu() {
        boolean onkoOikein = true;
        
        lava.merkitseLaatikko(20, 20, 40, 40);
        
        for (int i = 0; i < lava.getLeveys(); i++) {
            for (int j = 0; j < lava.getPituus(); j++) {
                if (lava.onkoTyhja(i, j) != false) {
                    if (i < 60 && j < 60 && i > 19 && j > 19) {
                        if (lava.onkoTyhja(i, j) == true) {
                            onkoOikein = false;
                        }
                    } else {
                        if (lava.onkoTyhja(i, j) == false) {
                            onkoOikein = false;
                        }
                    }
                }
            }
        }
            
            assertTrue(onkoOikein);
    }
}
