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
    
    @Test
    public void uusiSolmuPunainen(){
        p.insert(6);
        assertEquals(true, p.getJuuri().getVasen().getVari());
    }
    
    @Test
    public void juuriOnMusta(){
        assertEquals(false, p.getJuuri().getVari());
        p.insert(11);
        p.insert(34);
        p.insert(5);
        assertEquals(false, p.getJuuri().getVari());
        p.delete(34);
        assertEquals(false, p.getJuuri().getVari());
    }
    
    @Test
    public void tulostus(){
        p.insert(1);
        p.insert(897);
        p.insert(3);
        assertEquals("1\n3\n10\n897\n", p.tulostaArvot());
    }
    
    @Test
    public void olematonPoisEiRäjähdä(){
        p.delete(101);
    }
}