package logiikka;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import osat.Laatikko;
import osat.Nelikulmio;

/**
 *
 * @author albis
 */
public class LaskuriTest {
    private Laskuri laskuri;
    
    public LaskuriTest() {
    }
    
    @Before
    public void setUp() {
        laskuri = new Laskuri();
    }
    
    @Test
    public void laskeeOikeinIsoLaatikko() {
        int[][] asettelu = laskuri.laske(new Laatikko(40, 60, 40, 0L), new Nelikulmio(80, 120, 120));
        
        int virheellinenLaatikko = 0;
        
        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < 40; j++) {
                if (asettelu[i][j] != 1) {
                    virheellinenLaatikko = 1;
                }
            }
        }
        
        for (int i = 0; i < 60; i++) {
            for (int j = 40; j < 80; j++) {
                if (asettelu[i][j] != 2) {
                    virheellinenLaatikko = 2;
                }
            }
        }   
        
        for (int i = 60; i < 120; i++) {
            for (int j = 0; j < 40; j++) {
                if (asettelu[i][j] != 3) {
                    virheellinenLaatikko = 3;
                }
            }
        }
        
        for (int i = 60; i < 120; i++) {
            for (int j = 40; j < 80; j++) {
                if (asettelu[i][j] != 4) {
                    virheellinenLaatikko = 4;
                }
            }
        }
        
        assertEquals(0, virheellinenLaatikko);
    }
    
    @Test
    public void laskeeOikeinPienempiLaatikko() {
        int[][] asettelu = laskuri.laske(new Laatikko(40, 40, 40, 0L), new Nelikulmio(80, 120, 120));
        
        assertEquals(6, laskeLaatikoidenMaara(asettelu));
    }
    
    @Test
    public void laskeeOikeinIsoLaatikkoKunLavalleJaaTyhjaaTilaa() {
        int[][] asettelu = laskuri.laske(new Laatikko(40, 70, 40, 0L), new Nelikulmio(80, 120, 120));
        
        assertEquals(3, laskeLaatikoidenMaara(asettelu));
    }
    
    @Test
    public void laskeeOikeinPienempiLaatikkoKunLavalleJaaTyhjaaTilaa() {
        int[][] asettelu = laskuri.laske(new Laatikko(30, 50, 40, 0L), new Nelikulmio(80, 120, 120));
        
        assertEquals(6, laskeLaatikoidenMaara(asettelu));
    }
    
    @Test
    public void laskeeOikeinKorkeuden() {
        int kerroksia = laskuri.laskeKerrokset(new Laatikko(40, 40, 35, 0L), new Nelikulmio(80, 120, 120));
        
        assertEquals(3, kerroksia);
    }
    
    private int laskeLaatikoidenMaara(int[][] asettelu) {
        int maara = 0;
        
        for (int i = 0; i < asettelu.length; i++) {
            for (int j = 0; j < asettelu[0].length; j++) {
                if (asettelu[i][j] > maara) {
                    maara = asettelu[i][j];
                }
            }
        }
        
        return maara;
    }
}
