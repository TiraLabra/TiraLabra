package suorituskyky;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SuorituskykyTestausTest {
    
    long[] lista;
    SuorituskykyTestaus suo;
    
    @Before
    public void setUp() {
        suo = new SuorituskykyTestaus();
        lista = new long[] {Long.MIN_VALUE, 10L, 9L, 8L, 7L, 6L, 5L, 4L, 3L, 2L, 1L};
    }
    
    @Test
    public void jarjestysToimii() {
        suo.jarjesta(lista);
        long[] jarjestetty = new long[] {Long.MIN_VALUE, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L};
        assertTrue(Arrays.equals(lista, jarjestetty));
    }
    
    @Test
    public void keskiarvoToimii() {
        assertEquals(5.5, suo.laskeKeskiarvo(lista), 0);
    }
    
    @Test
    public void kertolaskuToimii() {
        assertTrue(suo.laskutoimitus(5, "Kertolasku", 1));
    }
    
    @Test
    public void strassenToimii() {
        assertTrue(suo.laskutoimitus(5, "Strassen", 1));
    }
    
    @Test
    public void determinanttiToimii() {
        assertTrue(suo.laskutoimitus(5, "Determinantti", 1));
    }
    
    @Test
    public void kaanteismatriisiToimii() {
        assertTrue(suo.laskutoimitus(5, "Kaanteismatriisi", 1));
    }
    
    @Test
    public void potenssiToimii() {
        assertTrue(suo.laskutoimitus(5, "Potenssi", 5));
    }
}