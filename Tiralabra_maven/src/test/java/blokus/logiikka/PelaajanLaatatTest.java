
package blokus.logiikka;

import junit.framework.TestCase;



public class PelaajanLaatatTest extends TestCase  {
    
    PelaajanLaatat laatat;
    
    public PelaajanLaatatTest() {
    }

    @Override
    public void setUp() {
        laatat = new PelaajanLaatat(1);
    }
    

    @Override
    public void tearDown() {
    }


    public void testalustusToimii() {
        
        assertEquals(21, laatat.getJaljellaLaatat().getKoko());
        assertEquals(0, laatat.getPelatutLaatat().getKoko());
    }
}
