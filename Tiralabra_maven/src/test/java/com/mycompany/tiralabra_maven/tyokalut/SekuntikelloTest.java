
package com.mycompany.tiralabra_maven.tyokalut;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class SekuntikelloTest {
    
    Sekuntikello kello;
    
    public SekuntikelloTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kello = new Sekuntikello();
    }
    
    @After
    public void tearDown() {
    }

    /**
     *  Testaa että antaa jonkin ajan kanssa kasvavan tulosteen
     */
    @Test
    public void testToimii() {
        kello.aloita();
        long aika1 = kello.lopeta();
        assertEquals(true, kello.lopeta()>0);
        long aika2 = kello.lopeta();
        assertEquals(true, aika2>aika1);
    }
    
}
