package blokus.logiikka;

import blokus.logiikka.Pelaaja;
import junit.framework.TestCase;


public class PelaajaTest extends TestCase  {

    Pelaaja pelaaja1;

    public PelaajaTest() {
    }


    @Override
    public void setUp() {
        pelaaja1 = new Pelaaja(1, false);
    }

 
    @Override
    public void tearDown() {
    }


    public void testpisteetOikeinAlussa() {
        assertEquals(84, pelaaja1.getPisteet());
    }


    public void testvaihtaminenToimii() {
        pelaaja1.vaihdaValittuaLaattaa(0, 3);    
        assertEquals(1, pelaaja1.getValittuna().getId());
        pelaaja1.vaihdaValittuaLaattaa(0, 0);    
        assertEquals(1, pelaaja1.getValittuna().getId());
    }
}
