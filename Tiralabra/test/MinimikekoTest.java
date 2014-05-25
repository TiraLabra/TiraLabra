
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MinimikekoTest {
    Minimikeko keko;

    @Before
    public void setUp() {
        keko = new Minimikeko();
        keko.lisaa(new HuffmanSolmu(0, 1, null, null));
        keko.lisaa(new HuffmanSolmu(1, 2, null, null));
        keko.lisaa(new HuffmanSolmu(2, 2, null, null));
        keko.lisaa(new HuffmanSolmu(3, 4, null, null));
    }

    @Test
    public void keonKokoPalautetaanOikein() {
        assertEquals(4, keko.koko());
    }
    
    @Test
    public void palauttaaPienimman() {
        assertEquals(0, keko.pienin().getMerkki());
    }
    
    @Test
    public void lisaaLisattavanOikeaanKohtaan() {
        keko.lisaa(new HuffmanSolmu(4, 3, null, null));
        keko.pienin();
        keko.pienin();
        keko.pienin();
        
        assertEquals(4, keko.pienin().getMerkki());
    }
}
