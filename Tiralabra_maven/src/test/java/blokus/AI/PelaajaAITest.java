package blokus.AI;

import blokus.logiikka.Pelaaja;
import blokus.logiikka.PeliLauta;
import blokus.logiikka.PeliLautaTest;
import junit.framework.TestCase;

/**
 *
 * @author Ilkimys
 */
public class PelaajaAITest extends TestCase {
    PeliLauta l;
    Pelaaja p;
    PelaajaAI ai;
    
    public PelaajaAITest() {
        
    }
    
    @Override
    protected void setUp() throws Exception {
        l = new PeliLauta();
        p = new Pelaaja(1, true);
        l.lisaaTarkastusLauta(1, p.getTarkastusLauta());
        ai = new PelaajaAI(p);
       
    }

    /**
     * Test of aloitaVuoro method, of class PelaajaAI.
     */
    public void testAloitaVuoro() {
        while (!p.getLaatat().getJaljellaLaatat().isEmpty()) {
            ai.aloitaVuoro(l);
        }
        assertEquals(0, p.getPisteet());
    }
}
