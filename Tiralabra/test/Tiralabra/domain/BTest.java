package Tiralabra.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BTest {

    B p;
    
    public BTest() {
    }
    
    @Before
    public void setUp() {
        p = new B(10);
        
        p.insert(20);
        
        p.insert(25);
        
        p.insert(15);
        
        p.insert(5);
        
        p.insert(7);
        p.insert(4);
        p.insert(8);
        p.insert(6);
        
        p.insert(18);
        p.insert(19);
        p.insert(17);
        p.insert(14);
        
        p.insert(30);
        p.insert(27);
        p.insert(22);
        p.insert(31);
    }
    
    @Test
    public void insertTaso1(){
        assertEquals(20, p.getJuuri().getToinenArvo());
        assertEquals(25, p.getJuuri().getOikea().getEnsimmainenArvo());
        assertEquals(15, p.getJuuri().getKeski().getEnsimmainenArvo());
        assertEquals(5, p.getJuuri().getVasen().getEnsimmainenArvo());
    }
    
    @Test
    public void insertTaso2(){
        assertEquals(7, p.getJuuri().getVasen().getToinenArvo());
        assertEquals(8, p.getJuuri().getVasen().getOikea().getEnsimmainenArvo());
        assertEquals(6, p.getJuuri().getVasen().getKeski().getEnsimmainenArvo());
        
        assertEquals(18, p.getJuuri().getKeski().getToinenArvo());
        assertEquals(19, p.getJuuri().getKeski().getOikea().getEnsimmainenArvo());
        assertEquals(17, p.getJuuri().getKeski().getKeski().getEnsimmainenArvo());
        assertEquals(14, p.getJuuri().getKeski().getVasen().getEnsimmainenArvo());
        
        assertEquals(30, p.getJuuri().getOikea().getToinenArvo());
        assertEquals(27, p.getJuuri().getOikea().getKeski().getEnsimmainenArvo());
        assertEquals(31, p.getJuuri().getOikea().getOikea().getEnsimmainenArvo());
        assertEquals(22, p.getJuuri().getOikea().getVasen().getEnsimmainenArvo());
        
    }
    
    @Test
    public void insertSamaArvo(){
        p = new B(8);
        p.insert(8);
        assertEquals(1, p.getJuuri().solmunKoko());
        assertEquals(8, p.getJuuri().getEnsimmainenArvo());
        assertEquals(null, p.getJuuri().getKeski());
        assertEquals(null, p.getJuuri().getOikea());
        assertEquals(null, p.getJuuri().getVasen());
    }
    
    @Test
    public void poistoLehti(){
        p.delete(31);
        assertEquals(null, p.getJuuri().getOikea().getOikea());
        assertEquals(30, p.getJuuri().getOikea().getToinenArvo());
        assertEquals(25, p.getJuuri().getOikea().getEnsimmainenArvo());
    }
    
    @Test
    public void poistoArvo1(){
        p.delete(15);
        assertEquals(17, p.getJuuri().getKeski().getEnsimmainenArvo());
    }
}
