package com.mycompany.logiikka;

import com.mycompany.domain.Kasi;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogiikkaTest {
    
    private Logiikka l;
    
    public LogiikkaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.l = new Logiikka(new Heuristiikka());
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void alussaPelaajanKasiOnNull() {
        assertEquals(null, this.l.pelaajanViimeisinKasi());
    }
    
    @Test
    public void kiviVoittaaSakset() {
        this.l.setPelaajanKasi(new Kasi("KIVI"));
        this.l.setTekoalynKasi(new Kasi("SAKSET"));
        assertEquals(1, this.l.pelaajaVoittaaKierroksen());
    }
    
    @Test
    public void saksetHaviaaKivelle() {
        this.l.setPelaajanKasi(new Kasi("SAKSET"));
        this.l.setTekoalynKasi(new Kasi("KIVI"));
        assertEquals(-1, this.l.pelaajaVoittaaKierroksen());
    }
    
    @Test
    public void tasapeliToimii() {
        Kasi k = new Kasi("SPOCK");
        this.l.setPelaajanKasi(k);
        this.l.setTekoalynKasi(k);
        assertEquals(0, this.l.pelaajaVoittaaKierroksen());
    }
}
