package Tiralabra.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AListaTest {

    ALista l;
    
    public AListaTest() {
    }
    
    @Before
    public void setUp() {
        l = new ALista(1);
    }
    
    @Test
    public void listaLuotu(){
        assertEquals(1 , l.getLista().getArvo());
        assertEquals(null , l.getLista().getNext());
        assertEquals(null , l.getLista().getPrev());
    }
    
    @Test
    public void lisaa(){
        l.lisaa(11);;
        assertEquals(11, l.getLista().getNext().getArvo());
        assertEquals(null, l.getLista().getNext().getNext());
    }
    
    @Test
    public void poista(){
        l.lisaa(11);
        l.lisaa(12);
        l.lisaa(13);
        l.poista(12);
        assertEquals(11, l.getLista().getNext().getArvo());
        assertEquals(11, l.getLista().getNext().getNext().getPrev().getArvo());
    }
    
    @Test
    public void listanKoko(){
        assertEquals(1, l.getKoko());
        l.lisaa(11);
        l.lisaa(67);
        assertEquals(3, l.getKoko());
        l.poista(11);
        assertEquals(2, l.getKoko());
    }
    
    @Test
    public void listaJarjLisays(){
        l.lisaa(4);
        l.lisaa(65);
        l.lisaa(2);
        assertEquals("1\n2\n4\n65\n", l.toString());
    }
    
    @Test
    public void listaJarjPoisto(){
        l.lisaa(2);
        l.lisaa(7);
        l.poista(2);
        assertEquals("1\n7\n", l.toString());
    }
}