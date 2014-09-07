
package com.mycompany.tiralabra_maven.tietorakenteet;

import com.mycompany.tiralabra_maven.peli.Siirto;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author noora
 */
public class SolmujenVertailijaTest {
    private SolmujenVertailija vertailija;
    private Solmu eka;
    private Solmu toka;
    private Solmu sama;
    
    public SolmujenVertailijaTest() {
    }
    
    @Before
    public void setUp() {
        vertailija = new SolmujenVertailija();
        eka = new Solmu(10, new Siirto(1,1,1,1));
        toka = new Solmu(5, new Siirto(2,2,2,2));
        sama = new Solmu(10, new Siirto(3,3,3,3));
        
    }

    @Test
    public void vertailuPalauttaaOikeinJosEkaSolmuSuurempi() {
        assertEquals(1, vertailija.compare(eka, toka));
    }
    
    @Test
    public void vertailuPalauttaaOikeinJosTokaSolmuSuurempi() {
        assertEquals(-1, vertailija.compare(toka, eka));
    }
    
    @Test
    public void vertailuPalauttaaOikeinJosSolmutSamanArvoiset(){
        assertEquals(0, vertailija.compare(eka, sama));
    }
    
}
