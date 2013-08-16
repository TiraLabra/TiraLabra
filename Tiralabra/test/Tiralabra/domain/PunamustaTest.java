package Tiralabra.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PunamustaTest {

    Punamusta p;
    
    public PunamustaTest() {
    }
    
    @Before
    public void setUp() {
        p = new Punamusta(10);
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
    
    @Test
    public void perusInsert(){
        p.insert(11);
        p.insert(5);
        assertEquals(11, p.getJuuri().getOikea().getArvo());
        assertEquals(5, p.getJuuri().getVasen().getArvo());
    }
    
    @Test
    public void perusDelete(){
        p.insert(23);
        p.insert(6);
        p.delete(23);
        assertEquals(null, p.getJuuri().getOikea());
        p.delete(6);
        assertEquals(null, p.getJuuri().getVasen());
    }
    
    @Test
    public void deleteJuuri(){
        p.delete(10);
        assertEquals(null, p.getJuuri());
    }
    
}