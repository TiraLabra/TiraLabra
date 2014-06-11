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
    public void setUp() throws Exception {
        this.lukija = new TekstinLukija();
        try {
            lukija.lueTiedosto("TekstinLukijaTest.txt");
        }
        catch (FileNotFoundException e) {
        }
    }

    @Test
    public void merkinLisaaminenKasvattaaSenEsiintymia() throws Exception {
        lukija2 = new TekstinLukija();

        lukija2.lisaaMerkki("a");
        String maara = lukija2.getEsiintymat().getArvo("a");
        assertEquals(1, Integer.parseInt(maara));
        
        lukija2.lisaaMerkki("a");
        maara = lukija2.getEsiintymat().getArvo("a");
        assertEquals(2, Integer.parseInt(maara));
    }
}
