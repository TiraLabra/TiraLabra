package tyokalut;

import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import osat.Laatikko;
import osat.Nelikulmio;

/**
 * 
 *
 * @author albis
 */
public class AVLkasittelijaTest {
    private AVLkasittelija historia;
    private int monesko;
    
    public AVLkasittelijaTest() {
    }

    @Before
    public void setUp() {
        historia = new AVLkasittelija();
        
        historia.AVLlisays(new Laatikko(20, 20, 20, 111111111111L),
                new int[1][1], new Nelikulmio(80, 120, 120));

        historia.AVLlisays(new Laatikko(40, 40, 40, 222222222222L),
                new int[1][1], new Nelikulmio(100, 120, 120));
        
        historia.AVLlisays(new Laatikko(60, 60, 60, 333333333333L),
                new int[1][1], new Nelikulmio(80, 120, 200));
        
        historia.AVLlisays(new Laatikko(15, 15, 15, 100000000000L),
                new int[1][1], new Nelikulmio(100, 120, 200));
    }
    
    @Test
    public void lapsetOikein() {
        assertEquals(historia.getJuuri().getKey(), 222222222222L);
        assertEquals(historia.getJuuri().getVasen().getKey(), 111111111111L);
        assertEquals(historia.getJuuri().getVasen().getVasen().getKey(), 100000000000L);
        assertEquals(historia.getJuuri().getOikea().getKey(), 333333333333L);
    }
    
    @Test
    public void vanhemmatOikein() {
        assertEquals(historia.getJuuri().getVanhempi(), null);
        assertEquals(historia.getJuuri().getVasen().getVanhempi(), historia.getJuuri());
        assertEquals(historia.getJuuri().getVasen().getVasen().getVanhempi(), historia.getJuuri().getVasen());
        assertEquals(historia.getJuuri().getOikea().getVanhempi(), historia.getJuuri());
    }

    @Test
    public void hakeeKorkeudenOikein() {
        assertEquals(historia.haeKorkeus(historia.getJuuri()), 2);
        assertEquals(historia.haeKorkeus(historia.getJuuri().getVasen()), 1);
        assertEquals(historia.haeKorkeus(historia.getJuuri().getVasen().getVasen()), 0);
        assertEquals(historia.haeKorkeus(historia.getJuuri().getVasen().getVasen().getVasen()), -1);
        assertEquals(historia.haeKorkeus(historia.getJuuri().getOikea()), 0);
    }
    
    @Test
    public void jarjestysOikein() {
        Random arpoja = new Random();
        
        for (int i = 0; i < 25; i++) {
            historia.AVLlisays(new Laatikko(1, 1, 1, arpoja.nextLong()), new int[1][1],
                    new Nelikulmio(1, 1, 1));
        }
        
        long[] avaimet = haeAvaimet();
        
        for (int i = 1; i < avaimet.length; i++) {
            if (avaimet[i] < avaimet[i-1]) {
                throw new AssertionError();
            }
        }
    }
    
    private long[] haeAvaimet() {
        long[] avaimet = new long[29];
        monesko = 0;
        
        lisaaAvain(historia.getJuuri(), avaimet);
        
        return avaimet;
    }
    
    private void lisaaAvain(AVLsolmu solmu, long[] avaimet) {
        if (solmu == null) {
            return;
        }
        
        lisaaAvain(solmu.getVasen(), avaimet);
        
        avaimet[monesko] = solmu.getKey();
        monesko++;
        
        lisaaAvain(solmu.getOikea(), avaimet);
    }
    
    @Test
    public void oikeaMaaraSolmuja() {
        Random arpoja = new Random();
        
        for (int i = 0; i < 25; i++) {
            historia.AVLlisays(new Laatikko(1, 1, 1, arpoja.nextLong()), new int[1][1],
                    new Nelikulmio(1, 1, 1));
        }
        
        assertEquals(29, laskeSolmut(historia.getJuuri()));
    }
    
    private int laskeSolmut(AVLsolmu solmu) {
        if (solmu == null) {
            return 0;
        }
        
        if (solmu.getVasen() == null && solmu.getOikea() == null) {
            return 1;
        }
        
        return laskeSolmut(solmu.getVasen()) + laskeSolmut(solmu.getOikea()) + 1;
    }
}
