/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OhjelmaLogiikka;

import Tietorakenteet.HuffmanKoodi;
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
public class BittiUtilityTest {

    public BittiUtilityTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testaaEttaHaeBitinArvoPaikastaKoodilleHakeeOikeanArvon1() {
        HuffmanKoodi koodi = new HuffmanKoodi();
        koodi.koodi = 16; // 0000...00010000
        assertEquals("Bitin arvo väärä", 1, BittiUtility.haeBitinArvoPaikasta(koodi, 4));
    }

    @Test
    public void testaaEttaHaeBitinArvoPaikastaKoodilleHakeeOikeanArvon2() {
        HuffmanKoodi koodi = new HuffmanKoodi();
        koodi.koodi = 16; // 0000...00010000
        assertEquals("Bitin arvo väärä", 0, BittiUtility.haeBitinArvoPaikasta(koodi, 3));
    }

    @Test
    public void testaaEttaHaeBitinArvoPaikastaKoodilleHakeeOikeanArvon3() {
        HuffmanKoodi koodi = new HuffmanKoodi();
        koodi.koodi = 23; // 0000...00010111
        assertEquals("Bitin arvo väärä", 1, BittiUtility.haeBitinArvoPaikasta(koodi, 1));
    }

    @Test
    public void testaaEttaHaeBitinArvoPaikastaKoodilleHakeeOikeanArvon4() {
        HuffmanKoodi koodi = new HuffmanKoodi();
        koodi.koodi = 23; // 0000...00010111
        assertEquals("Bitin arvo väärä", 0, BittiUtility.haeBitinArvoPaikasta(koodi, 3));
    }

    @Test
    public void testaaEttaHaeBitinArvoPaikastaTavulleHakeeOikeanArvon1() {
        assertEquals("Bitin arvo väärä", 1, BittiUtility.haeBitinArvoPaikasta((byte) 10, 3));
    }

    @Test
    public void testaaEttaHaeBitinArvoPaikastaTavulleHakeeOikeanArvon2() {
        assertEquals("Bitin arvo väärä", 0, BittiUtility.haeBitinArvoPaikasta((byte) 10, 4));
    }

    @Test
    public void testaaEttaHaeBitinArvoPaikastaTavulleHakeeOikeanArvon3() {
        assertEquals("Bitin arvo väärä", 1, BittiUtility.haeBitinArvoPaikasta((byte) 10, 1));
    }

    @Test
    public void testaaEttaHaeBitinArvoPaikastaTavulleHakeeOikeanArvon4() {
        assertEquals("Bitin arvo väärä", 0, BittiUtility.haeBitinArvoPaikasta((byte) 10, 25));
    }

    @Test
    public void testaaEttaHaeBitinArvoPaikastaLongilleHakeeOikeanArvon1() {
        assertEquals("Bitin arvo väärä", 1, BittiUtility.haeBitinArvoPaikasta(10L, 3));
    }

    @Test
    public void testaaEttaHaeBitinArvoPaikastaLongilleHakeeOikeanArvon2() {
        assertEquals("Bitin arvo väärä", 1, BittiUtility.haeBitinArvoPaikasta(8589934592L, 33));
    }

    @Test
    public void testaaEttaHaeBitinArvoPaikastaLongilleHakeeOikeanArvon3() {
        assertEquals("Bitin arvo väärä", 0, BittiUtility.haeBitinArvoPaikasta((long) 381384729134L, 0));
    }

    @Test
    public void testaaEttaHaeBitinArvoPaikastaLongilleHakeeOikeanArvon4() {
        assertEquals("Bitin arvo väärä", 0, BittiUtility.haeBitinArvoPaikasta((long) 9663709185L, 25));
    }

    @Test
    public void testaaEttaTallennaBitinArvoPaikalleKoodilleToimiiOikein() {
        HuffmanKoodi koodi = new HuffmanKoodi();
        koodi.koodi = 0;
        assertEquals("Bitin arvo väärä", 32, BittiUtility.tallennaBitinArvoPaikalle(koodi, 1, 5)); // 2^5 = 32
    }

    @Test
    public void testaaEttaTallennaBitinArvoPaikalleKoodilleToimiiOikein2() {
        HuffmanKoodi koodi = new HuffmanKoodi();
        koodi.koodi = 16;
        assertEquals("Bitin arvo väärä", 48, BittiUtility.tallennaBitinArvoPaikalle(koodi, 1, 5)); // 32 + 16 = 48
    }

    @Test
    public void testaaEttaTallennaBitinArvoPaikalleKoodilleToimiiOikeinKunBitinArvoJoYksi() {
        HuffmanKoodi koodi = new HuffmanKoodi();
        koodi.koodi = 32;
        assertEquals("Bitin arvo väärä", 32, BittiUtility.tallennaBitinArvoPaikalle(koodi, 1, 5)); // 2^5 = 1
    }

    @Test
    public void testaaEttaTallennaBitinArvoPaikalleKoodilleToimiiOikeinKunBitinArvoJoYksi2() {
        HuffmanKoodi koodi = new HuffmanKoodi();
        koodi.koodi = 32;
        assertEquals("Bitin arvo väärä", 32, BittiUtility.tallennaBitinArvoPaikalle(koodi, 0, 5)); // 2^5 = 1
    }

    @Test
    public void testaaEttaTallennaBitinArvoPaikalleTavulleToimiiOikein() {
        assertEquals("Bitin arvo väärä", -128, BittiUtility.tallennaBitinArvoPaikalle((byte) 0, 1, 7));
    }

    @Test
    public void testaaEttaTallennaBitinArvoPaikalleTavulleToimiiOikein2() {
        assertEquals("Bitin arvo väärä", 91, BittiUtility.tallennaBitinArvoPaikalle((byte) 27, 1, 6)); // 27 = 00011011
    }

    @Test
    public void testaaEttaTallennaBitinArvoPaikalleTavulleToimiiOikeinKunBitinArvoJoYksi() {
        assertEquals("Bitin arvo väärä", 65, BittiUtility.tallennaBitinArvoPaikalle((byte) 1, 1, 6));
    }

    @Test
    public void testaaEttaTallennaBitinArvoPaikalleTavulleToimiiOikeinKunBitinArvoJoYksi2() {
        assertEquals("Bitin arvo väärä", -50, BittiUtility.tallennaBitinArvoPaikalle((byte) -50, 0, 7));
    }

    @Test
    public void testaaEttaTallennaBitinArvoPaikalleLongilleToimiiOikein() {
        assertEquals("Bitin arvo väärä", (long) Math.pow(2, 30) + 1, (long) BittiUtility.tallennaBitinArvoPaikalle(1L, 1, 30));
    }

    @Test
    public void testaaEttaTallennaBitinArvoPaikalleLongilleToimiiOikein2() {
        assertEquals("Bitin arvo väärä", 1074921473L, (long) BittiUtility.tallennaBitinArvoPaikalle(1179649L, 1, 30));
    }

    @Test
    public void testaaEttaTallennaBitinArvoPaikalleLongilleToimiiOikeinKunBitinArvoJoYksi() {
        assertEquals("Bitin arvo väärä", 1179649L, BittiUtility.tallennaBitinArvoPaikalle(1179649L, 1, 20));
    }

    @Test
    public void testaaEttaTallennaBitinArvoPaikalleLongilleToimiiOikeinKunBitinArvoJoYksi2() {
        assertEquals("Bitin arvo väärä", 528416L, BittiUtility.tallennaBitinArvoPaikalle(528416L, 0, 19));
    }

    @Test
    public void testaaEttaHaeBitinArvoPaikaltaToimiiAivanAarilaidastaByte() {
        assertEquals("Bitin arvo väärä", 1, BittiUtility.haeBitinArvoPaikasta(-128, 8));
    }   

    @Test
    public void testaaEttaHaeBitinArvoPaikaltaToimiiAivanAarilaidastaLong() {
        assertEquals("Bitin arvo väärä", 1, BittiUtility.haeBitinArvoPaikasta(Long.MIN_VALUE, 63));
        
    }
}
