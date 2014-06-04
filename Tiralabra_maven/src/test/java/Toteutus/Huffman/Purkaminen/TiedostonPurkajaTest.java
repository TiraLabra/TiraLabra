package Toteutus.Huffman.Purkaminen;

import Apuvalineet.BinaariMuuntaja;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TiedostonPurkajaTest {
    private TiedostonPurkaja purkaja;
    
    @Before
    public void setUp() throws IOException {
        this.purkaja = new TiedostonPurkaja();
        luoTestiTiedostoJosTarpeen();
    }
    
    private void luoTestiTiedostoJosTarpeen() throws IOException {
        File testi = new File("TiedostonPurkajaTest.hemi");
        if (! testi.exists()) {
            testi.createNewFile();
            
            FileWriter kirjoittaja = new FileWriter(testi);
            kirjoittaja.write("jaflsdjvöjsd gjfgdf");
            kirjoittaja.close();
        }
    }
    
    @Test
    public void polkuOnValidi() throws IOException {
        purkaja.tarkistaOnkoPolkuValidi("abc.hemi");
        purkaja.tarkistaOnkoPolkuValidi("Tiedosto.HEMI");
    }
    
    @Test (expected = IOException.class)
    public void eiValidiPolkuHeittaaPoikkeuksen() throws IOException {
        purkaja.tarkistaOnkoPolkuValidi("abc.hemi2");
    }
    
    @Test
    public void pakkauksenHakuOnnistuu() throws IOException {
        File pakkaus = purkaja.haePakkaus("TiedostonPurkajaTest.hemi");
        assertTrue(pakkaus.isFile());
    }
    
    @Test (expected = IOException.class)
    public void pakkauksenHaussaHeitetaanPoikkeusJosTiedostoEiOlemassa() throws IOException {
        purkaja.haePakkaus("lol");
    }
    
    @Test
    public void tiedostonMuodostusOnnistuu() throws IOException {
        File tiedosto = purkaja.muodostaTiedosto("TiedostonPurkajaTest.hemi");
        assertFalse(tiedosto.exists());
        assertEquals("TiedostonPurkajaTest", tiedosto.getPath());
    }
    
    @Test (expected = IOException.class) 
    public void tiedostonMuodostusHeittaaPoikkeuksenKunPurettuTiedostoJoOlemassa() throws IOException {
        purkaja.muodostaTiedosto("TiedostonPurkajaTest.hemi.hemi");
    }
    
    @Test
    public void testinLukeminenOnnistuu() throws FileNotFoundException {
        File tiedosto = new File("TiedostonPurkajaTest.hemi");
        assertEquals("jaflsdjvöjsd gjfgdf", purkaja.lueTeksti(tiedosto));
    }
    
    @Test
    public void puunOsoitePalauttaaOsoitteen() {
        char nul = (char) 0;
        assertEquals(100, purkaja.puunOsoite(nul + "" + nul + "" + nul + "" + "d" + "fjsdlkfjlsd"));
    }
    
    @Test
    public void luotavanTiedostonPolkuPalauttaaPolunOikein() {
        String polku = "a.hemi";
        assertEquals("a", purkaja.luotavanTiedostonPolku(polku));
        
        polku = ".hemi";
        assertEquals("", purkaja.luotavanTiedostonPolku(polku));
    }
    
    @Test
    public void seuraavaOsoitePalauttaaOsoitteenOikein() {
        String tekstiBinaarina = "01011010";
        int puunOsoite = 13;
        assertEquals(14, purkaja.seuraavaOsoite(13, puunOsoite, tekstiBinaarina, 0));
        assertEquals(15, purkaja.seuraavaOsoite(13, puunOsoite, tekstiBinaarina, 1));
        
        puunOsoite = 46;
        
        assertEquals(65, purkaja.seuraavaOsoite(55, puunOsoite, tekstiBinaarina, 2));
        assertEquals(74, purkaja.seuraavaOsoite(59, puunOsoite, tekstiBinaarina, 3));
    }
    
    @Test
    public void tekstiBinaarinaToimii() {
        assertEquals("101111" + "0110111001110011", purkaja.tekstiBinaarina(kuudesTavu(), 8));
    }
    
    @Test
    public void kuudesTavuIlmanEtuNollia() {
        assertEquals("101111", purkaja.kuudesTavuIlmanEtuNollia(kuudesTavu()));
    }
    
    private String kuudesTavu() {
        char nul = (char) 0;
        String pointer = nul + "" + nul + (char) 2 + "" + "b";
        char etuNollia = (char) 2;
        
        return pointer + etuNollia + "/ns438fd54fgä'ä";
    }
    
    @Test
    public void muunTekstinLisaysOnnistuu() {
        String teksti = "012345ö5¤$!";
        assertEquals("", purkaja.lisaaMuuTeksti(teksti, 6));
        assertEquals("11110110", purkaja.lisaaMuuTeksti(teksti, 7));        // "ö:n pitäisi olla dec 148, ei dec 246..."
        assertEquals("1111011000110101", purkaja.lisaaMuuTeksti(teksti, 8));
    }
    
    @Test
    public void tiedostoonKirjoitusOnnistuu() {
        char nul = (char) 0;
        String puu = nul + "" + nul + "o" + "M" + nul + "" + nul + "" + nul + "" + nul + "" + nul + "r!";
        
        String binaariEsitys = "0000000010101011";
        
        BinaariMuuntaja muuntaja = new BinaariMuuntaja();
        String pakattuna = muuntaja.pakatuksiTekstiksi(binaariEsitys);
        
        String teksti = nul + "" + nul + "" + nul + "" + (char) 7 + (char) 6 + pakattuna + puu;

        int puunOsoite = purkaja.puunOsoite(teksti);
        
        String kirjoitettava = purkaja.kirjoitettavaTeksti(teksti, puunOsoite, purkaja.tekstiBinaarina(teksti, puunOsoite));
        assertEquals("Moro!", kirjoitettava);
    }
}
