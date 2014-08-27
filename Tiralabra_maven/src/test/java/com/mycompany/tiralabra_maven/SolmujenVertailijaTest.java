
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.AI.Solmu;
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
    
    public SolmujenVertailijaTest() {
    }
    
    @Before
    public void setUp() {
        vertailija = new SolmujenVertailija();
        eka = new Solmu(10, new Siirto(1,1,1,1));
        toka = new Solmu(5, new Siirto(2,2,2,2));
        
    }

    @Test
    public void vertailuPalauttaaOikeinJosEkaSolmuSuurempi() {
        assertEquals(1, vertailija.compare(eka, toka));
    }
    
    @Test
    public void vertailuPalauttaaOikeinJosTokaSolmuSuurempi() {
        assertEquals(-1, vertailija.compare(toka, eka));
    }
    
}
