package blokus.logiikka;

import junit.framework.TestCase;


public class BlokusTest extends TestCase {

    Blokus blokus;

    public BlokusTest() {
    }

    @Override
    public void setUp() {
        blokus = new Blokus(false, false, false, false);
        blokus.tarkastaAI();
    }

    @Override
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    
    public void testpelaajiaOikeaMaaraJaYksiVuorossa() {
        assertEquals(3, blokus.getPelaajat().getListanKoko());
        assertTrue(blokus.getVuorossa() != null);
    }

    public void testvuoronLoppumisenJalkeenSamanverranPelaajiaJonossa() {
        blokus.lopetaVuoro(false, false);
        assertEquals(4, blokus.getPelaajat().getListanKoko());
        assertTrue(blokus.getVuorossa() != null);
    }

    public void testpelaajiaVastaaOikeaMaaraTarkastusLautoja() {

        assertEquals(4, blokus.getPeliLauta().getTarkastusLaudat().getKoko());

    }

    
    public void testvarilleLoytyyTekstit() {

        assertEquals("Sininen", blokus.getIDVariTekstina(1));
        assertEquals("Oranssi", blokus.getIDVariTekstina(2));
        assertEquals("Punainen", blokus.getIDVariTekstina(3));
        assertEquals("Vihreä", blokus.getIDVariTekstina(4));

    }
}
