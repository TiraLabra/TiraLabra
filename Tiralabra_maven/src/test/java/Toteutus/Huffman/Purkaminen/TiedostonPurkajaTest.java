package Toteutus.Huffman.Purkaminen;

import Apuvalineet.Kirjoittaja;
import Tietorakenteet.HajautusTaulu;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    public void testinLukeminenOnnistuu() throws IOException, UnsupportedEncodingException, Exception  {
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
    public void kirjoitettavaTeksti() throws Exception {
        HajautusTaulu bittijonotJaMerkit = bittijonotJaMerkit();

        assertTrue(purkaja.kirjoitettavaTeksti("", bittijonotJaMerkit).isEmpty());
        assertEquals("bcadabc", purkaja.kirjoitettavaTeksti(y+n+y+y+n+n+y+y+y+n+y+n+y+y+n, bittijonotJaMerkit));
    }
    
    private HajautusTaulu bittijonotJaMerkit() throws Exception {
        HajautusTaulu bittijonotJaMerkit = new HajautusTaulu();
        bittijonotJaMerkit.lisaa(n, "a");
        bittijonotJaMerkit.lisaa(y+n, "b");
        bittijonotJaMerkit.lisaa(y+y+n, "c");
        bittijonotJaMerkit.lisaa(y+y+y, "d");
        
        return bittijonotJaMerkit;
    }
    
    @Test
    public void kayPuuLapi() throws Exception {
        puunLapiKayntiTyhjallaTekstilla();
        puunLapiKayntiTekstillaJossaTavallisiaAsciiMerkkeja();
        
        josBittiEsitysEpaTyhjaLisataanSeHajautusTauluun();
        lisaaMerkkiJosSeOn0Tai1();
        puuOnKelattuLoppuun();
    }
    
    private void puunLapiKayntiTyhjallaTekstilla() throws Exception {
        HajautusTaulu bittijonotJaMerkit = new HajautusTaulu();
        String teksti = (char) 127 + "" + (char) 127;
        
        assertEquals(2, purkaja.kayPuuLapi(teksti, bittijonotJaMerkit));
        assertTrue(bittijonotJaMerkit.onTyhja());
    }
    
    private void puunLapiKayntiTekstillaJossaTavallisiaAsciiMerkkeja() throws Exception {
        HajautusTaulu bittijonotJaMerkit = new HajautusTaulu();
        
        String teksti = "c" + n + n + n + "b" + n + n + y + "a" + n + y + n + "d" + n + y + y + "f" + y + y + "e" + y + n + 
                        (char) 127 + "" + (char) 127 + "_abcdef";
        
        assertEquals(teksti.length() - 7, purkaja.kayPuuLapi(teksti, bittijonotJaMerkit));
        
        HajautusTaulu verrattava = puunLapiKayntiaVerrattavaHajTaulu();
        
        assertTrue(bittijonotJaMerkit.getKoko() == verrattava.getKoko());
        
        for (String bittijono : bittijonotJaMerkit.getAvaimet()) {
            assertTrue(bittijonotJaMerkit.getArvo(bittijono).equals(verrattava.getArvo(bittijono)));
        }
    }
    
    private HajautusTaulu puunLapiKayntiaVerrattavaHajTaulu() throws Exception {
        HajautusTaulu verrattava = new HajautusTaulu();
        
        verrattava.lisaa(n+y+n, "a");
        verrattava.lisaa(n+n+y, "b");
        verrattava.lisaa(n+n+n, "c");
        verrattava.lisaa(n+y+y, "d");
        verrattava.lisaa(y+n, "e");
        verrattava.lisaa(y+y, "f");
        
        return verrattava;
    }

    private void josBittiEsitysEpaTyhjaLisataanSeHajautusTauluun() throws Exception {
        HajautusTaulu bittijonotJaMerkit = new HajautusTaulu();
        String sata = y+n+n;
        
        purkaja.josBittiEsitysEpaTyhjaLisataanSeHajautusTauluun('a', sata, bittijonotJaMerkit);
        
        assertTrue(bittijonotJaMerkit.sisaltaaAvaimen(sata));
        assertTrue(bittijonotJaMerkit.getArvo(sata).equals("a"));
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
