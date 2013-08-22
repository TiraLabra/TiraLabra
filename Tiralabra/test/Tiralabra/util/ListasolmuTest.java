package Tiralabra.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListasolmuTest {
    
    Listasolmu s;
    
    public ListasolmuTest() {
    }
    
    @Before
    public void setUp() {
        s = new Listasolmu(1, null, null);
    }
    
    @Test
    public void konstruktori(){
        assertEquals(null, s.getNext());
        assertEquals(null, s.getPrev());
    }
    
    @Test
    public void setgetNext(){
        s.setNext(new Listasolmu(9, s, null));
        assertEquals(9 , s.getNext().getArvo());
        assertEquals(1, s.getNext().getPrev().getArvo());
        assertEquals(null, s.getNext().getNext());
    }
    
    @Test
    public void setgetPrev(){
        s.setPrev(new Listasolmu(7, null, s));
        assertEquals(7, s.getPrev().getArvo());
        assertEquals(1, s.getPrev().getNext().getArvo());
        assertEquals(null, s.getPrev().getPrev());
    }
    
}
