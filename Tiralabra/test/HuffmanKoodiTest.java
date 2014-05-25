
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HuffmanKoodiTest {

    HuffmanKoodi huff;

    @Before
    public void setUp() {
        huff = new HuffmanKoodi();
    }

    @Test
    public void puunMuodostusOnnistuu() {
        int[] merkit = {6, 1, 3, 5};

        HuffmanSolmu juuri = huff.muodostaPuu(merkit);

        assertEquals(15, juuri.getMaara());
        assertEquals(6, juuri.getVasen().getMaara());
        assertEquals(9, juuri.getOikea().getMaara());

        juuri = juuri.getOikea();
        assertEquals(5, juuri.getOikea().getMaara());
        assertEquals(4, juuri.getVasen().getMaara());

        juuri = juuri.getVasen();
        assertEquals(1, juuri.getVasen().getMaara());
        assertEquals(3, juuri.getOikea().getMaara());
    }

    @Test
    public void koodisanatMuodostuvatOikein() {
        int[] merkit = new int[256];
        
        //#
        merkit[35] = 5;
        //2
        merkit[50] = 2;
        //A
        merkit[65] = 1;
        //d
        merkit[100] = 4;        
        
        huff.muodostaKoodit(huff.muodostaPuu(merkit));
        String odotettu = "A - 100\n2 - 101\n# - 0\nd - 11\n";

        assertEquals(odotettu, huff.palautaKoodisanat());

    }
}
