package Toteutus.Huffman.Purkaminen;

import Apuvalineet.Kirjoittaja;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TiedostonPurkajaTest {
    private TiedostonPurkaja purkaja;
    private final String n = (char) 0 + "";
    private final String y = (char) 1 + "";
    
    @Before
    public void setUp() throws IOException {
        this.purkaja = new TiedostonPurkaja();
        luoTestiTiedostoJosTarpeen();
    }
    
    @After
    public void tuhoaTestiTiedosto() {
        File testi = new File("TiedostonPurkajaTest.hemi");
        testi.delete();
    }
    
    private void luoTestiTiedostoJosTarpeen() throws IOException {
        File testi = new File("TiedostonPurkajaTest.hemi");
        if (! testi.exists()) {
            testi.createNewFile();
            
            Kirjoittaja kirjoittaja = new Kirjoittaja(testi.getPath());
            kirjoittaja.kirjoita("a"+n+n + "b"+n+y + "c"+y+n + "d"+y+y + (char) 127 + (char) 127 + n + (char) 135) ;
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
    public void testinLukeminenOnnistuu() throws IOException  {
        File tiedosto = new File("TiedostonPurkajaTest.hemi");
        assertEquals("a"+n+n + "b"+n+y + "c"+y+n + "d"+y+y + (char) 127 + (char) 127 + n + (char) 135,
                     purkaja.lueTeksti(tiedosto));
    }
    
    @Test
    public void luotavanTiedostonPolkuPalauttaaPolunOikein() {
        String polku = "a.hemi";
        assertEquals("a", purkaja.luotavanTiedostonPolku(polku));
        
        polku = ".hemi";
        assertEquals("", purkaja.luotavanTiedostonPolku(polku));
    }
    
    @Test
    public void tekstiBinaarinaTavallisillaAsciiMerkeilla() {
        String teksti = "8a¤" + (char) 2 + ".?,*)";
        assertEquals(y+n+y+y+y+n+n+n+y+y+y+y+y+y+n+n+y+n+y+y+n+n+n+n+y+n+y+n+y+n+n+n+y+n+y+n+n+y, 
                     purkaja.tekstiBinaarina(teksti, 3));
    }
    
    @Test
    public void tavuIlmanEtuNolliaTavallisillaAsciiMerkeilla() {
        String teksti = (char) 0 + "+/";
        assertEquals(n+n+y+n+y+n+y+y, purkaja.tavuIlmanEtuNollia(teksti, 0));
        
        teksti = "abc" + (char) 5 + "" + (char) 7 + "!D";
        assertEquals(y+y+y, purkaja.tavuIlmanEtuNollia(teksti, 3));
    }
    
    @Test
    public void lisaaMuuTekstiTavallisillaAsciiMerkeilla() {
        String teksti = "tty56B4";
        assertTrue(purkaja.lisaaMuuTeksti(teksti, 5).isEmpty());
        assertEquals(n+y+n+n+n+n+y+n+n+n+y+y+n+y+n+n, purkaja.lisaaMuuTeksti(teksti, 3));
    }
    
    @Test
    public void kirjoitettavaTeksti() {
        HashMap<String, String> bittijonotJaMerkit = bittijonotJaMerkit();

        assertTrue(purkaja.kirjoitettavaTeksti("", bittijonotJaMerkit).isEmpty());
        assertEquals("bcadabc", purkaja.kirjoitettavaTeksti(y+n+y+y+n+n+y+y+y+n+y+n+y+y+n, bittijonotJaMerkit));
    }
    
    private HashMap<String, String> bittijonotJaMerkit() {
        HashMap<String, String> bittijonotJaMerkit = new HashMap<>();
        bittijonotJaMerkit.put(n, "a");
        bittijonotJaMerkit.put(y+n, "b");
        bittijonotJaMerkit.put(y+y+n, "c");
        bittijonotJaMerkit.put(y+y+y, "d");
        
        return bittijonotJaMerkit;
    }
    
    @Test
    public void kayPuuLapi() {
        puunLapiKayntiTyhjallaTekstilla();
        puunLapiKayntiTekstillaJossaTavallisiaAsciiMerkkeja();
        
        josBittiEsitysEpaTyhjaLisataanSeHajautusTauluun();
        lisaaMerkkiJosSeOn0Tai1();
        puuOnKelattuLoppuun();
    }
    
    private void puunLapiKayntiTyhjallaTekstilla() {
        HashMap<String, String> bittijonotJaMerkit = new HashMap<>();
        String teksti = (char) 127 + "" + (char) 127;
        
        assertEquals(2, purkaja.kayPuuLapi(teksti, bittijonotJaMerkit));
        assertTrue(bittijonotJaMerkit.isEmpty());
    }
    
    private void puunLapiKayntiTekstillaJossaTavallisiaAsciiMerkkeja() {
        HashMap<String, String> bittijonotJaMerkit = new HashMap<>();
        
        String teksti = "c" + n + n + n + "b" + n + n + y + "a" + n + y + n + "d" + n + y + y + "f" + y + y + "e" + y + n + 
                        (char) 127 + "" + (char) 127 + "_abcdef";
        
        assertEquals(teksti.length() - 7, purkaja.kayPuuLapi(teksti, bittijonotJaMerkit));
        
        HashMap<String, String> verrattava = puunLapiKayntiaVerrattavaHajTaulu();
        
        assertTrue(bittijonotJaMerkit.size() == verrattava.size());
        
        for (String bittijono : bittijonotJaMerkit.keySet()) {
            assertTrue(bittijonotJaMerkit.get(bittijono).equals(verrattava.get(bittijono)));
        }
    }
    
    private HashMap<String, String> puunLapiKayntiaVerrattavaHajTaulu() {
        HashMap<String, String> verrattava = new HashMap<>();
        
        verrattava.put(n+y+n, "a");
        verrattava.put(n+n+y, "b");
        verrattava.put(n+n+n, "c");
        verrattava.put(n+y+y, "d");
        verrattava.put(y+n, "e");
        verrattava.put(y+y, "f");
        
        return verrattava;
    }

    private void josBittiEsitysEpaTyhjaLisataanSeHajautusTauluun() {
        HashMap<String, String> bittijonotJaMerkit = new HashMap<>();
        String sata = y+n+n;
        
        purkaja.josBittiEsitysEpaTyhjaLisataanSeHajautusTauluun('a', sata, bittijonotJaMerkit);
        
        assertTrue(bittijonotJaMerkit.containsKey(sata));
        assertTrue(bittijonotJaMerkit.get(sata).equals("a"));
        assertFalse(purkaja.josBittiEsitysEpaTyhjaLisataanSeHajautusTauluun('b', "", bittijonotJaMerkit));
    }
    
    private void lisaaMerkkiJosSeOn0Tai1() {
        StringBuilder bittiEsitys = new StringBuilder();
        assertTrue(purkaja.lisaaMerkkiJosSeOn0Tai1((char) 0, bittiEsitys));
        assertTrue(purkaja.lisaaMerkkiJosSeOn0Tai1((char) 1, bittiEsitys));
        assertEquals(n+y, bittiEsitys.toString());
        
        assertFalse(purkaja.lisaaMerkkiJosSeOn0Tai1('d', bittiEsitys));
    }
    
    private void puuOnKelattuLoppuun() {
        String teksti = "abcd" + (char) 127 + "" + (char) 127;
        assertFalse(purkaja.puuOnKelattuLoppuun(teksti, 2));
        assertFalse(purkaja.puuOnKelattuLoppuun(teksti, 3));
        assertTrue(purkaja.puuOnKelattuLoppuun(teksti, 4));
    }
}
