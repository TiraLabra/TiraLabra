package Apuvalineet;

import java.io.FileNotFoundException;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TekstinLukijaTest {
    private TekstinLukija lukija;
    private TekstinLukija lukija2;
    
    @Before
    public void setUp() {
        this.lukija = new TekstinLukija();
        try {
            lukija.lueTiedosto("TekstinLukijaTest.txt");
        }
        catch (FileNotFoundException e) {
        }
    }

    @Test
    public void merkinLisaaminenKasvattaaSenEsiintymia() {
        lukija2 = new TekstinLukija();

        lukija2.lisaaMerkki("a");
        int maara = lukija2.getEsiintymat().get("a");
        assertEquals(maara, 1);
        
        lukija2.lisaaMerkki("a");
        maara = lukija2.getEsiintymat().get("a");
        assertEquals(maara, 2);
    }
}
