package Tiralabra.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ThreadedTest {
    
    Threaded p;
    
    public ThreadedTest() {
    }
    
    @Before
    public void setUp() {
        p = new Threaded(10);
    }
    
    @Test
    public void insertSuurempiTest() {
        p.insert(11);
        assertEquals(11, p.getJuuri().getOikea().getKey());
    }
    
    @Test
    public void insertPienempiTest(){
        p.insert(5);
        assertEquals(5, p.getJuuri().getVasen().getKey());
    }
    
    @Test
    public void insertLisaaVanhemmanVasemmalle(){
        p.insert(8);
        assertEquals(p.getJuuri(), p.getJuuri().getVasen().getParent());
    }
    
    @Test
    public void insertLisaaVanhemmanOikealle(){
        p.insert(81);
        assertEquals(p.getJuuri(), p.getJuuri().getOikea().getParent());
    }
    
    @Test
    public void insertAsettaaStatuksen(){
        assertEquals(false, p.getJuuri().oikeaStatusGet());
        assertEquals(false, p.getJuuri().vasenStatusGet());
        p.insert(99);
        p.insert(1);
        assertEquals(true, p.getJuuri().oikeaStatusGet());
        assertEquals(true, p.getJuuri().vasenStatusGet());
    }
    
    @Test
    public void insertUseampiOikealle(){
        p.insert(5);
        p.insert(4);
        p.insert(7);
        assertEquals(5, p.getJuuri().getVasen().getVasen().getParent().getKey());
        assertEquals(7, p.getJuuri().getVasen().getOikea().getKey());
    }
    
    @Test
    public void insertUseampiVasemmalle(){
        p.insert(15);
        p.insert(14);
        p.insert(17);
        assertEquals(15, p.getJuuri().getOikea().getVasen().getParent().getKey());
        assertEquals(17, p.getJuuri().getOikea().getOikea().getKey());
    }
    
    @Test
    public void osoitinSeuraajaan(){
        p.insert(9);
        assertEquals(p.getJuuri(), p.getJuuri().getVasen().getOikea());
    }
    
    @Test
    public void osoitinEdeltajaan(){
        p.insert(999);
        assertEquals(p.getJuuri(), p.getJuuri().getOikea().getVasen());
    }
    
    @Test
    public void searchLoytyy(){
        assertEquals(true, p.search(10));
        p.insert(3);
        assertEquals(true, p.search(3));
        p.insert(78);
        p.insert(5);
        p.insert(1);
        assertEquals(true, p.search(3));
        assertEquals(true, p.search(5));
    }
    
    @Test
    public void searchEiLoyda(){
        assertEquals(false, p.search(1));
    }
    
    @Test
    public void deleteEiLapsia(){
        p.insert(6);
        p.delete(6);
        assertEquals(null, p.getJuuri().getVasen());
    }
    
    @Test
    public void deleteOikLapsi(){
        p.insert(101);
        p.insert(211);
        p.delete(101);
        assertEquals(211, p.getJuuri().getOikea().getKey());
        assertEquals(p.getJuuri(), p.getJuuri().getOikea().getParent());
    }
    
    @Test
    public void deleteVasLapsi(){
        p.insert(1);
        p.insert(2);
        p.delete(1);
        assertEquals(2, p.getJuuri().getVasen().getKey());
        assertEquals(p.getJuuri(), p.getJuuri().getVasen().getParent());
    }
    
    @Test
    public void deleteKaksLasta(){
        p.insert(3);
        p.insert(1);
        p.insert(6);
        p.delete(3);
        assertEquals(6, p.getJuuri().getVasen().getKey());
        assertEquals(1, p.getJuuri().getVasen().getVasen().getKey());
    }
    
    @Test 
    public void deleteJuuri(){
        p.insert(101);
        p.delete(10);
        assertEquals(101, p.getJuuri().getKey());
        assertEquals(null, p.getJuuri().getParent());
    }
    
    @Test 
    public void edeltajaAsettuu(){
        p.insert(11);
        p.insert(14);
        assertEquals(p.getJuuri(), p.getJuuri().getOikea().getVasen());
        assertEquals(p.getJuuri().getOikea(), p.getJuuri().getOikea().getOikea().getVasen());
        p.delete(11);
        assertEquals(p.getJuuri(), p.getJuuri().getOikea().getVasen());
    }
    
    @Test 
    public void seuraajaAsettuu(){
        p.insert(4);
        p.insert(1);
        assertEquals(p.getJuuri(), p.getJuuri().getVasen().getOikea());
        assertEquals(p.getJuuri().getVasen(), p.getJuuri().getVasen().getVasen().getOikea());
        p.delete(4);
        assertEquals(p.getJuuri(), p.getJuuri().getVasen().getOikea());
    }
    
    @Test
    public void tulostus(){
        p.insert(4);
        p.insert(234);
        p.insert(8);
        p.insert(1);
        assertEquals("1\n4\n8\n10\n234\n", p.tulostaArvot());
    }
    
    
}