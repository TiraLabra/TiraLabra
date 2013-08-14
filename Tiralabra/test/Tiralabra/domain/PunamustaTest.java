package Tiralabra.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PunamustaTest {

    Punamusta p = new Punamusta(10);
    
    public PunamustaTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void search(){
        assertEquals(true, p.search(10));
        p.insert(11);
        p.insert(5);
        p.insert(13);
        assertEquals(true, p.search(11));
        assertEquals(true, p.search(13));
        assertEquals(true, p.search(5));
    }
    
    @Test
    public void searchEiLoydy(){
        assertEquals(false, p.search(1));
        p.insert(1);
        assertEquals(false, p.search(999));
    }
    
    
    
}