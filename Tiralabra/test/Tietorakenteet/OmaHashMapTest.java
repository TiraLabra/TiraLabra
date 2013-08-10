/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

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
public class OmaHashMapTest {

    OmaHashMap<Integer, String> hashMap;

    public OmaHashMapTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        hashMap = new OmaHashMap<Integer, String>();
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void uusiHashMapToimiiKunKokoOnKahdenPotenssi() {
        OmaMap foo = new OmaHashMap(16);
        assertNotNull("Jotakin meni pieleen", foo);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void uusiHashMapHeittaaPoikkeuksenJosKokoEiOleKahdenPotenssi() {
        OmaMap foo = new OmaHashMap(27);
    }
    

    @Test
    public void uusiHashMapKokoNolla() {
        assertEquals("Hashmapin koko väärä luomisen jälkeen", 0, hashMap.size());
    }

    @Test
    public void uusiHashMapIsEmpty() {
        assertTrue("Hashmap ei ole tyhjä luomisen jälkeen", hashMap.isEmpty());
    }

    @Test
    public void hashMapKokoOikeaKunLisattyTavaraa() {
        hashMap.put(1, "Yksi");
        hashMap.put(2, "Kaksi");
        hashMap.put(3, "Kolme");
        hashMap.put(4, "Neljä");
        hashMap.put(5, "Viisi");

        assertEquals("Hashmapin koko väärä", 5, hashMap.size());
    }

    @Test
    public void hashMapKokoOikeaKunLisattyTavaraaJaTyhjennetaan() {
        hashMap.put(1, "Yksi");
        hashMap.put(2, "Kaksi");
        hashMap.put(3, "Kolme");
        hashMap.put(4, "Neljä");
        hashMap.put(5, "Viisi");

        hashMap.clear();

        assertEquals("Hashmapin koko väärä", 0, hashMap.size());
    }

    @Test
    public void hashMapIsEmptyKunLisattyTavaraaJaTyhjennetaan() {
        hashMap.put(1, "Yksi");
        hashMap.put(2, "Kaksi");
        hashMap.put(3, "Kolme");
        hashMap.put(4, "Neljä");
        hashMap.put(5, "Viisi");

        hashMap.clear();

        assertTrue("Hashmapin koko väärä", hashMap.isEmpty());
    }

    @Test
    public void hashMapKokoOikeaKunLisattyTavaraaJaTyhjennetaanJaLisataanLisaa() {
        hashMap.put(1, "Yksi");
        hashMap.put(2, "Kaksi");
        hashMap.put(3, "Kolme");
        hashMap.put(4, "Neljä");
        hashMap.put(5, "Viisi");

        hashMap.clear();

        hashMap.put(1, "Banaani");
        hashMap.put(2, "hur");

        assertEquals("Hashmapin koko väärä", 2, hashMap.size());
    }

    @Test
    public void hashMapEiIsEmptyKunLisattyTavaraaJaTyhjennetaanJaLisataanLisaa() {
        hashMap.put(1, "Yksi");
        hashMap.put(2, "Kaksi");
        hashMap.put(3, "Kolme");
        hashMap.put(4, "Neljä");
        hashMap.put(5, "Viisi");

        hashMap.clear();

        hashMap.put(1, "asda");
        hashMap.put(2, "DASJD");

        assertFalse("Hashmapin isEmpty() vaikka lisätty tavaraa", hashMap.isEmpty());
    }

    @Test
    public void hashMapKokoOikeaKunLisattyTavaraaJaOsallaSamaAvain() {
        hashMap.put(1, "Yksi");
        hashMap.put(2, "Kaksi");
        hashMap.put(3, "Kolme");
        hashMap.put(4, "Neljä");
        hashMap.put(5, "Viisi");

        hashMap.put(5, "Five");
        hashMap.put(3, "Tre");

        assertEquals("Hashmapin koko väärä", 5, hashMap.size());
    }

    @Test
    public void hashMapPalauttaaOikeanArvon() {
        hashMap.put(1, "Yksi");
        hashMap.put(2, "Kaksi");
        hashMap.put(3, "Kolme");
        hashMap.put(4, "Neljä");
        hashMap.put(5, "Viisi");

        assertEquals("Hashmap antoi väärän arvon", "Kolme", hashMap.get(3));
    }

    @Test
    public void hashMapPalauttaaOikeanArvonKunNegatiivinenHashCode() {
        hashMap.put(-1, "MiinusYksi");
        hashMap.put(2, "Kaksi");
        hashMap.put(3, "Kolme");
        hashMap.put(4, "Neljä");
        hashMap.put(5, "Viisi");

        assertEquals("Hashmap antoi väärän arvon", "MiinusYksi", hashMap.get(-1));
    }

    @Test
    public void hashMapPalauttaaNullKunHaetaanOlematonta() {
        hashMap.put(1, "Yksi");
        hashMap.put(2, "Kaksi");
        hashMap.put(3, "Kolme");
        hashMap.put(4, "Neljä");
        hashMap.put(5, "Viisi");

        assertNull("Hashmap antoi väärän arvon", hashMap.get(25));
    }

    @Test
    public void hashMapPalauttaaOikeanKunPaljonAvaimia() {
        hashMap.put(1, "Yksi");
        hashMap.put(2, "Kaksi");
        hashMap.put(3, "Kolme");
        hashMap.put(4, "Neljä");
        for (int i = 5; i < 100; ++i) {
            hashMap.put(i, "Viisi");
        }

        assertEquals("Hashmap antoi väärän arvon", "Yksi", hashMap.get(1));
        assertEquals("Hashmap antoi väärän arvon", "Viisi", hashMap.get(6));
    }

    @Test
    public void hashMapArvoOikeaKunLisattyTavaraaJaTyhjennetaanJaLisataanLisaa() {
        hashMap.put(1, "Yksi");
        hashMap.put(2, "Kaksi");
        hashMap.put(3, "Kolme");
        hashMap.put(4, "Neljä");
        hashMap.put(5, "Viisi");

        hashMap.clear();

        hashMap.put(1, "Banaani");
        hashMap.put(2, "hur");

        assertEquals("Hashmap antoi väärän arvon", "hur", hashMap.get(2));
    }

    @Test
    public void hashMapArvoOikeaKunReHashataan() {

         for (int i = 0; i < 1500; i += 7) {
            hashMap.put(i, "yarr" + i);         
        }

        assertEquals("Hashmap antoi väärän arvon", "yarr105", hashMap.get(105));
    }
    
    @Test
    public void hashMapArvoOikeaKunReHashataan2() {

         for (int i = 0; i < 2500; i += 13) {
            hashMap.put(i, "yarr" + i);
        }

        assertEquals("Hashmap antoi väärän arvon", "yarr130", hashMap.get(130));
    }
    
    @Test
    public void hashMapKokoOikeaKunReHashataan() {

        for (int i = 0; i < 1500; i += 3) {
            hashMap.put(i, "yarr" + i);
        }

        assertEquals("Hashmap antoi väärän arvon", 500, hashMap.size());
    }

  


    @Test
    public void avaimetKokoNollaTyhjalleKartalle() {
        assertEquals("avaimien määrä väärä tyhjälle kartalle", 0, hashMap.avaimet().size());
    }

    @Test
    public void avaimetKokoOikea() {
        hashMap.put(1, "Yksi");
        hashMap.put(2, "Kaksi");
        hashMap.put(3, "Kolme");
        hashMap.put(4, "Neljä");
        hashMap.put(5, "Viisi");

        assertEquals("avaimien määrä väärä tyhjälle kartalle", 5, hashMap.avaimet().size());
    }

    @Test
    public void avaimetOikeat() {
        OmaList<Integer> avaimet = new OmaArrayList<Integer>();
        avaimet.add(new Integer(1));
        avaimet.add(new Integer(2));
        avaimet.add(new Integer(3));
        avaimet.add(new Integer(4));
        avaimet.add(new Integer(5));

        hashMap.put(avaimet.get(0), "Yksi");
        hashMap.put(avaimet.get(1), "Kaksi");
        hashMap.put(avaimet.get(2), "Kolme");
        hashMap.put(avaimet.get(3), "Neljä");
        hashMap.put(avaimet.get(4), "Viisi");

        OmaList<Integer> lista = hashMap.avaimet();

        for (int i = 0; i < lista.size(); ++i) {
            boolean loytyi = false;
            for (int j = 0; j < avaimet.size(); ++j) {
                if (lista.get(i) == avaimet.get(j)) {
                    loytyi = true;
                    break;
                }
            }
            assertTrue("Avainta ei palautettu oikein", loytyi);
        }
    }

    @Test
    public void kokoOikeaKunAvaimetHashaaSamaanIndeksiin() {

        hashMap.put(1, "1");
        hashMap.put(2, "2");
        hashMap.put(3, "3");
        hashMap.put(33, "5");

        assertEquals("Hash map ei toiminut oikein kun avaimilla samoja hashcodeja", 4, hashMap.size());
    }

    @Test
    public void arvoOikeaKunAvaimetHashaaSamaanIndeksiin() {

        hashMap.put(1, "1");
        hashMap.put(2, "2");
        hashMap.put(3, "3");
        hashMap.put(33, "33");
        
        assertEquals("Hash map ei toiminut oikein kun avaimilla samoja hashcodeja", "1", hashMap.get(1));
        assertEquals("Hash map ei toiminut oikein kun avaimilla samoja hashcodeja", "33", hashMap.get(33));
        
    }
}
