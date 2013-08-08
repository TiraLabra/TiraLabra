package tyokalut;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author albis
 */
public class KasvavaListaTest {
    private KasvavaLista lista;
    
    public KasvavaListaTest() {
    }
    
    @Before
    public void setUp() {
        lista = new KasvavaLista();
    }
    
    @Test
    public void listastaHakuToimii() {
        lista.lisaa("vaaka");
        lista.lisaa("pysty");
        
        assertEquals(lista.getAsento(0), "vaaka");
        assertEquals(lista.getAsento(1), "pysty");
    }
    
    @Test
    public void listaAlussaTyhja() {
        boolean onkoMitaan = false;
        
        for (int i = 0; i < lista.length(); i++) {
            if (lista.getAsento(i) != null) {
                onkoMitaan = true;
            }
        }
        
        assertFalse(onkoMitaan);
    }
    
    @Test
    public void lisaysToimii() {
        boolean onkoOikein = true;
        
        for (int i = 0; i < 5; i++) {
            lista.lisaa("vaaka");
        }
        
        for (int i = 0; i < 5; i++) {
            if (!lista.getAsento(i).equals("vaaka")) {
                onkoOikein = false;
            }
        }
        
        assertTrue(onkoOikein);
    }
    
    @Test
    public void pituusPalautetaanOikein() {
        for (int i = 0; i < 12; i++) {
            lista.lisaa("pysty");
        }
        
        assertEquals(lista.length(), 12);
    }
    
    @Test
    public void kopioituuOikein() {
        boolean kopioituukoOikein = true;
        
        for (int i = 0; i < 5; i++) {
            lista.lisaa("vaaka");
        }
        lista.lisaa("pysty");
        lista.lisaa("pysty");
        
        KasvavaLista kopio = lista.kopioi();
        
        for (int i = 0; i < 7; i++) {
            if (!lista.getAsento(i).equals(kopio.getAsento(i))) {
                kopioituukoOikein = false;
            }
        }
        
        assertTrue(kopioituukoOikein);
        assertEquals(lista.length(), 7);
        assertEquals(kopio.length(), 7);
    }
}
