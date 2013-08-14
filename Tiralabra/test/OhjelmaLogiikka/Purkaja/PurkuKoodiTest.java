/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OhjelmaLogiikka.Purkaja;

import OhjelmaLogiikka.Purkaja.PurkuKoodi;
import TestiTiedostoLuokat.TestiKirjoittaja;
import TestiTiedostoLuokat.TestiLukija;
import Tiedostokasittely.ITiedostoKirjoittaja;
import Tiedostokasittely.ITiedostoLukija;
import Tiedostokasittely.TiedostoKirjoittaja;
import Tietorakenteet.Koodi;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Omistaja
 */
public class PurkuKoodiTest {
    private PurkuKoodi purkaja;
    private TestiKirjoittaja kirjoittaja;
    private TestiLukija lukija;
    private OmaMap<Koodi, byte[]> koodit;
    private int viimeisessaTavussaMerkitseviaBitteja;
    
    public PurkuKoodiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            purkaja = new PurkuKoodi();
            kirjoittaja = new TestiKirjoittaja();
            lukija = new TestiLukija();
            
            // pakattuna teksti: abcadeabed - 8 tavun rypäissä koodit kirjaimille: abca | dea | bed
            // tallennettu tavuun väärinpäin ->  acba - aed - deb
            // kirjainten koodit tavuihin:
            // ensimmäisen tavun koodi: 00|01|10|00 = 24
            // toisen tavun koodi: 00|111|011= 59
            // kolmannen tavun koodi 011|111|10 = 126
            lukija.tavut = new byte[]{24, 59, 126};
            
            koodit = new OmaHashMap<Koodi, byte[]>();
            viimeisessaTavussaMerkitseviaBitteja = 8;
            luoKoodit();
            kirjoittaja.avaaTiedosto();
            lukija.avaaTiedosto();
        } catch (FileNotFoundException ex) {
            // Ei pitäisi ikinä tapahtua, Testiobjektit eivät heitä poikkeuksia
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void heittaaIllegalArgumentExceptionJosMerkitsevienBittienMaaraLiianPieni() {
        try {
            purkaja.kasitteleTiedosto(lukija, kirjoittaja, 0, koodit);
            
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void heittaaIllegalArgumentExceptionJosMerkitsevienBittienMaaraNegatiivinen() {
        try {
            purkaja.kasitteleTiedosto(lukija, kirjoittaja, -1, koodit);
            
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void heittaaIllegalArgumentExceptionJosMerkitsevienBittienMaaraLiianSuuri() {
        try {
            purkaja.kasitteleTiedosto(lukija, kirjoittaja, 9, koodit);
            
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }
    
    @Test
    public void purkaaOikein() {
        try {
            purkaja.kasitteleTiedosto(lukija, kirjoittaja, viimeisessaTavussaMerkitseviaBitteja, koodit);
            
            String teksti = new String(kirjoittaja.haeTavut());
            assertEquals("Purkaja purki väärin: ", "abcadeabed", teksti);
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    private void luoKoodit() {
      
        Koodi koodi;

     
        koodi = new Koodi();
      
        koodi.pituus = 2;
        koodi.koodi = 0;
        koodit.put(koodi, new byte[]{'a'});

      
        koodi = new Koodi();
        koodi.pituus = 2;
        koodi.koodi = 2;
        koodit.put(koodi, new byte[]{'b'});

    
        koodi = new Koodi();
        koodi.pituus = 2;
        koodi.koodi = 1;
        koodit.put(koodi, new byte[]{'c'});

      
        koodi = new Koodi();
        koodi.pituus = 3;
        koodi.koodi = 3;
        koodit.put(koodi, new byte[]{'d'});


        koodi = new Koodi();
        koodi.pituus = 3;
        koodi.koodi = 7;
        koodit.put(koodi, new byte[]{'e'});
    }
}
