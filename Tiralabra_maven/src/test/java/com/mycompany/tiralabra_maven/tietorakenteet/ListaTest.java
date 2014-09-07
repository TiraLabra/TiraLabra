package com.mycompany.tiralabra_maven.tietorakenteet;

import java.util.Comparator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author noora
 */
public class ListaTest {

    public ListaTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void listalleVoiLisata() {
        Lista lista = new Lista();
        lista.lisaa(5);
        lista.lisaa(6);
        assertEquals(5, lista.getAlkio(0));
        assertEquals(6, lista.getAlkio(1));
    }

    @Test
    public void taulukonKasvatusToimii() {
        Lista lista = new Lista();
        for (int i = 0; i < 110; i++) {
            lista.lisaa(i);
        }
        assertEquals(105, lista.getAlkio(105));
        assertEquals(101, lista.getAlkio(101));
        assertEquals(99, lista.getAlkio(99));
        assertEquals(0, lista.getAlkio(0));
    }

    @Test
    public void taulukostaSaaSuurimmanArvon() {
        Lista lista = new Lista();
        lista.lisaa(6);
        lista.lisaa(100);
        lista.lisaa(34);
        assertEquals(100, lista.getSuurin());
    }

    @Test
    public void listaToimiiOmallaVertailijalla() {
        Comparator<String> vertailija = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.compareTo(s1);
            }
        };
        Lista<String> lista = new Lista<>(vertailija);
        lista.lisaa("dee");
        lista.lisaa("bee");
        lista.lisaa("cee");
        lista.lisaa("aa");
        
        assertEquals("aa", lista.getSuurin());
        assertEquals("cee", lista.getAlkio(2));
    }
    
    @Test
    public void listaToimiiOmallaVertailijallaJosUseampiaSamoja() {
        Comparator<String> vertailija = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.compareTo(s1);
            }
        };
        Lista<String> lista = new Lista<>(vertailija);
        lista.lisaa("dee");
        lista.lisaa("aa");
        lista.lisaa("bee");
        lista.lisaa("bee");
        lista.lisaa("cee");
        lista.lisaa("aa");
        
        assertEquals("aa", lista.getSuurin());
        assertEquals("bee", lista.getAlkio(2));
    }
    
    @Test
    public void listaToimiiToisellaOmallaVertailijalla() {
        Comparator<String> vertailija = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        };
        Lista<String> lista = new Lista<>(vertailija);
        lista.lisaa("dee");
        lista.lisaa("bee");
        lista.lisaa("cee");
        lista.lisaa("aa");
        
        assertEquals("dee", lista.getSuurin());
        assertEquals("cee", lista.getAlkio(2));
    }
    
    @Test
    public void listaToimiiToisellaOmallaVertailijallaJosUseampiaSamoja() {
        Comparator<String> vertailija = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        };
        Lista<String> lista = new Lista<>(vertailija);
        lista.lisaa("dee");
        lista.lisaa("bee");
        lista.lisaa("bee");
        lista.lisaa("cee");
        lista.lisaa("dee");
        lista.lisaa("aa");
        
        assertEquals("dee", lista.getSuurin());
        assertEquals("bee", lista.getAlkio(2));
    }
}
