/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OhjelmaLogiikka.Purkaaja;

import TestiTiedostoLuokat.TestiLukija;
import Tietorakenteet.Koodi;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import Tietorakenteet.Pari;
import java.io.IOException;
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
public class HeaderLukijaTest {

    private TestiLukija tiedosto;
    private HeaderLukija headerLukija;

    public HeaderLukijaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tiedosto = new TestiLukija();
        headerLukija = new HeaderLukija();
    }

    @After
    public void tearDown() {
    }

    @Test(expected = IOException.class)
    public void tyhjaTiedostoAiheuttaaIOException() throws IOException {
        tiedosto.tavut = new byte[0];
        headerLukija.lueHeader(tiedosto);
    }

    @Test(expected = IOException.class)
    public void roskaDataAiheuttaaIOException() throws IOException {
        tiedosto.tavut = "Laitetaanhan vähän roskadataa tänne".getBytes();
        headerLukija.lueHeader(tiedosto);
    }

    @Test
    public void antaaOikeanMerkitsevienBittienMaaran() throws IOException {
        // 5 merkitsevää bittiä, - blokkikoko 1 - 8 bittiä koodissa, 'a' blokki, 8 bittiä koodissa, 'b' blokki. 128 offset
        tiedosto.tavut = new byte[]{(5 - 128), (1 - 128), (2 - 128), 'a', (2 - 128), 'b', (3 - 128), 'c'};
                
        Pari<Integer, OmaMap<Koodi, byte[]>> koodit = headerLukija.lueHeader(tiedosto);
        assertEquals("Väärä merkitsevien tavujen määrä", new Integer(5), koodit.ensimmainen);
    }

    @Test
    public void lukeeKooditOikein() throws IOException {
        // 5 merkitsevää bittiä, - blokkikoko 1 - 8 bittiä koodissa, 'a' blokki, 8 bittiä koodissa, 'b' blokki. 128 offset
        tiedosto.tavut = new byte[]{(5 - 128), (1 - 128), (2 - 128), 'a', (2 - 128), 'b', (3 - 128), 'c'};
        
        // kanonisoituna pitäisi lukea 00, 10 ja  001 eli 0, 2 ja 1 integereinä (tallennettu longiin)
        // ensiksi 00 a:lle, 0 + 1 = 01, bittijärjestys käännetään ennen palautusta -> 10 b
        // 01 + 1 = 10, koska pituus suurempi (3 vs 2) shifti oikealle -> 100, käännös -> 001 c
        Pari<Integer, OmaMap<Koodi, byte[]>> koodit = headerLukija.lueHeader(tiedosto);
        Koodi koodi = new Koodi();
        koodi.pituus = 2;
        koodi.koodi = 0;
        
        OmaList<Koodi> avaimet = koodit.toinen.avaimet();
    
        assertEquals("A:n koodi väärä", 'a', koodit.toinen.get(koodi)[0]);
        koodi.koodi = 2;
        assertEquals("B:n koodi väärä", 'b', koodit.toinen.get(koodi)[0]);   
        koodi.koodi = 1;
        koodi.pituus = 3;
        assertEquals("C:n koodi väärä", 'c', koodit.toinen.get(koodi)[0]);   
    }
}
