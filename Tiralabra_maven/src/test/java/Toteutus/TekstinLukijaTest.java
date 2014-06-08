package Toteutus;

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
    public void alussaTekstiOnTyhja() {
        lukija2 = new TekstinLukija();
        assertTrue(lukija2.getTeksti().isEmpty());
    }
    
    @Test (expected = FileNotFoundException.class) 
    public void lukeminenHeittaaPoikkeuksenJosTiedostoaEiLoydy() throws FileNotFoundException {
        lukija2 = new TekstinLukija();
        lukija2.lueTiedosto("");
    }
    
    
    @Test
    public void lopussaTekstiOnTiedostonSisaltamaTeksti() {
        assertTrue(this.lukija.getTeksti().equals("Tämä on testi -teksti, mikä luetaan\n" +
                                                  "TekstinLukijaTestin\n" +
                                                  "kautta."));
    }
    
    @Test
    public void merkkienEsiintymienMaaraTasmaa() {
        HashMap<String, Integer> esiintymat = lukija.getEsiintymat();
        
        int luku = esiintymat.get("T");
        assertEquals(3, luku);
        
        luku = esiintymat.get(" ");
        assertEquals(5, luku);        
        
        luku = esiintymat.get("ä");
        assertEquals(3, luku);
        
        luku = esiintymat.get("\n");
        assertEquals(2, luku);
        
        luku = esiintymat.get("n");
        assertEquals(4, luku); 
    }
    
    @Test
    public void rivinLisaaminenOnnistuu() {
        lukija2 = new TekstinLukija();
        String rivi = "abcdefg";
        lukija2.lisaaRivi(rivi);
        assertEquals(lukija2.getTeksti(), rivi);
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
