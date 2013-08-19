package tyokalut;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import osat.Laatikko;
import osat.Lava;

/**
 * 
 *
 * @author albis
 */
public class HistorianKasittelijaTest {
    private AVLkasittelija historia;
    
    public HistorianKasittelijaTest() {
    }

    @Before
    public void setUp() {
        historia = new AVLkasittelija();
        
        historia.AVLlisays(new Laatikko(20, 20, 20, 111111111111L),
                new KasvavaLista(), new Lava(80, 120, 120));

        historia.AVLlisays(new Laatikko(40, 40, 40, 222222222222L),
                new KasvavaLista(), new Lava(100, 120, 120));
        
        historia.AVLlisays(new Laatikko(60, 60, 60, 333333333333L),
                new KasvavaLista(), new Lava(80, 120, 200));
        
        historia.AVLlisays(new Laatikko(15, 15, 15, 100000000000L),
                new KasvavaLista(), new Lava(100, 120, 200));
    }
    
    @Test
    public void lapsetOikein() {
        assertEquals(historia.getJuuri().getKey(), 222222222222L);
        assertEquals(historia.getJuuri().getVasen().getKey(), 111111111111L);
        assertEquals(historia.getJuuri().getVasen().getVasen().getKey(), 100000000000L);
        assertEquals(historia.getJuuri().getOikea().getKey(), 333333333333L);
    }
    
    @Test
    public void vanhemmatOikein() {
        assertEquals(historia.getJuuri().getVanhempi(), null);
        assertEquals(historia.getJuuri().getVasen().getVanhempi(), historia.getJuuri());
        assertEquals(historia.getJuuri().getVasen().getVasen().getVanhempi(), historia.getJuuri().getVasen());
        assertEquals(historia.getJuuri().getOikea().getVanhempi(), historia.getJuuri());
    }

    @Test
    public void hakeeKorkeudenOikein() {
        assertEquals(historia.haeKorkeus(historia.getJuuri()), 2);
        assertEquals(historia.haeKorkeus(historia.getJuuri().getVasen()), 1);
        assertEquals(historia.haeKorkeus(historia.getJuuri().getVasen().getVasen()), 0);
        assertEquals(historia.haeKorkeus(historia.getJuuri().getVasen().getVasen().getVasen()), -1);
        assertEquals(historia.haeKorkeus(historia.getJuuri().getOikea()), 0);
    }
}
