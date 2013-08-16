package Tiralabra.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListaTest {

    Lista l;
    
    public ListaTest() {
    }
    
    @Before
    public void setUp() {
        l = new Lista(1, null, null);
    }
    
    @Test
    public void listaLuotu(){
        assertEquals(1 , l.getArvo());
        assertEquals(null , l.getNext());
        assertEquals(null , l.getPrev());
    }
    
    @Test
    public void lisaa(){
        l.lisaa(9);
        l.lisaa(11);
        assertEquals(9, l.getNext().getArvo());
        assertEquals(11, l.getNext().getNext().getArvo());
        assertEquals(null, l.getNext().getNext().getNext());
    }
    
    @Test
    public void poista(){
        l.lisaa(11);
        l.lisaa(12);
        l.lisaa(13);
        l.poista(12);
        assertEquals(13, l.getNext().getNext().getArvo());
        assertEquals(12, l.getNext().getNext().getPrev().getArvo());
    }
}
