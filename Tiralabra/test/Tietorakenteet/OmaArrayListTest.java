package Tietorakenteet;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OmaArrayListTest {

    private OmaArrayList<Integer> lista;

    public OmaArrayListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        lista = new OmaArrayList<Integer>();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void uusiListaOnTyhja() {
        assertEquals("Listan koko ei ole nolla", lista.size(), 0);
    }

    @Test
    public void isEmptyTosiTyhjalle() {
        assertTrue("isEmpty antaa väärän tuloksen", lista.isEmpty());
    }

    @Test
    public void isEmptyFalseEiTyhjalle() {
        lista.add(23);
        assertFalse("isEmpty antaa väärän tuloksen", lista.isEmpty());
    }

    @Test
    public void kokoOikeaKunLisataanAlkio() {
        lista.add(1);
        assertEquals("Listan koko ei ole oikea", lista.size(), 1);
        lista.add(2);
        assertEquals("Listan koko ei ole oikea", lista.size(), 2);
    }

    @Test
    public void kokoOikeaKunLisataanSatunnainenMaaraAlkioita() {
        Random random = new Random();
        // max 30 
        int lisattavaMaara = Math.abs(random.nextInt()) % 30;

        for (int i = 0; i < lisattavaMaara; ++i) {
            lista.add(i);
        }
        assertEquals("Listan koko ei ole oikea", lista.size(), lisattavaMaara);
    }

    @Test
    public void addAllMuuttaaKokoaOikein() {
        lista.add(1);
        lista.add(2);

        OmaList<Integer> lisattava = new OmaArrayList<Integer>();
        lisattava.add(5);
        lisattava.add(4);

        lista.addAll(lisattava);

        assertEquals("Listan koko ei ole oikea", lista.size(), 4);
    }

    @Test
    public void addAllOikeatAlkiotTaulukossa() {
        lista.add(1);
        lista.add(2);

        OmaList<Integer> lisattava = new OmaArrayList<Integer>();
        lisattava.add(5);
        lisattava.add(4);

        lista.addAll(lisattava);

        assertTrue("Väärä alkion arvo", lista.get(0) == 1);
        assertTrue("Väärä alkion arvo", lista.get(1) == 2);
        assertTrue("Väärä alkion arvo", lista.get(2) == 5);
        assertTrue("Väärä alkion arvo", lista.get(3) == 4);
    }

    @Test
    public void addAllTyhjanLisausEiMuutaKokoa() {
        lista.add(1);
        lista.add(2);

        OmaList<Integer> lisattava = new OmaArrayList<Integer>();
        lista.addAll(lisattava);

        assertEquals("Väärä alkion arvo", lista.size(), 2);
    }

    @Test
    public void addAllNullLisaysEiMuutaKokoa() {
        lista.add(1);
        lista.add(2);

        lista.addAll(null);
        assertEquals("Väärä alkion arvo", lista.size(), 2);
    }

    @Test
    public void alkioOnTallennettuOikein() {
        lista.add(1);
        lista.add(4);
        lista.add(25);
        lista.add(19);
        Random random = new Random();

        Integer lisattava = random.nextInt();
        lista.add(lisattava);

        assertTrue("Lista ei tallentanut oikeaa numeroa", 1 == lista.get(0));
        assertTrue("Lista ei tallentanut oikeaa numeroa", 4 == lista.get(1));
        assertTrue("Lista ei tallentanut oikeaa numeroa", 25 == lista.get(2));
        assertTrue("Lista ei tallentanut oikeaa numeroa", 19 == lista.get(3));
        assertTrue("Lista ei tallentanut oikeaa numeroa", lisattava == lista.get(4));
    }

    @Test
    public void setMuuttaaOikein() {
        lista.add(1);
        lista.add(4);
        lista.add(25);
        lista.add(19);

        lista.set(2, 40);
        assertTrue("Lista ei tallentanut oikeaa numeroa", 1 == lista.get(0));
        assertTrue("Lista ei tallentanut oikeaa numeroa", 4 == lista.get(1));
        assertTrue("Lista ei tallentanut oikeaa numeroa", 40 == lista.get(2));
        assertTrue("Lista ei tallentanut oikeaa numeroa", 19 == lista.get(3));
    }

    @Test
    public void setListanKokoPysyyOikeana() {
        lista.add(1);
        lista.add(4);
        lista.add(25);
        lista.add(19);

        assertEquals("Listan koko on väärä", 4, lista.size());
    }

    @Test
    public void containsLoytaaObjektin() {
        Integer yksi = new Integer(1);
        Integer kaksi = new Integer(2);
        Integer kolme = new Integer(3);
        Integer nelja = new Integer(4);

        lista.add(yksi);
        lista.add(kaksi);
        lista.add(kolme);
        lista.add(nelja);

        assertTrue("contains() ei löytänyt objektia", lista.contains(yksi));
        assertTrue("contains() ei löytänyt objektia", lista.contains(kaksi));
        assertTrue("contains() ei löytänyt objektia", lista.contains(kolme));
        assertTrue("contains() ei löytänyt objektia", lista.contains(nelja));
    }

    @Test
    public void containsEiLoydaOlematonta() {
        Integer yksi = new Integer(1);
        Integer kaksi = new Integer(2);
        Integer kolme = new Integer(3);
        Integer nelja = new Integer(4);

        lista.add(yksi);
        lista.add(kaksi);
        lista.add(kolme);
        lista.add(nelja);

        assertFalse("contains() löysi olemattoman objektin", lista.contains(new Integer(1)));
        assertFalse("contains() löysi olemattoman objektin", lista.contains(new Integer(2)));
        assertFalse("contains() löysi olemattoman objektin", lista.contains(new Integer(3)));
        assertFalse("contains() löysi olemattoman objektin", lista.contains(new Integer(4)));
    }

    @Test
    public void listanKokoOikeaRemoveObjektinJaljilta() {
        Integer yksi = new Integer(1);
        Integer kaksi = new Integer(2);
        Integer kolme = new Integer(3);
        Integer nelja = new Integer(4);

        lista.add(yksi);
        lista.add(kaksi);
        lista.add(kolme);
        lista.add(nelja);

        lista.remove(kolme);
        assertEquals("Listan koko väärä removen jäljiltä", lista.size(), 3);
    }

    @Test
    public void listanKokoOikeaRemoveObjektinJaljiltaKunPoistetaanVaara() {
        Integer yksi = new Integer(1);
        Integer kaksi = new Integer(2);
        Integer kolme = new Integer(3);
        Integer nelja = new Integer(4);
        Integer viisi = new Integer(5);

        lista.add(yksi);
        lista.add(kaksi);
        lista.add(kolme);
        lista.add(nelja);

        lista.remove(viisi);
        assertEquals("Listan koko väärä removen jäljiltä", 4, lista.size());
    }

    @Test
    public void removeObjectPalauttFalseKunEioleListalla() {
        Integer yksi = new Integer(1);
        Integer kaksi = new Integer(2);
        Integer kolme = new Integer(3);
        Integer nelja = new Integer(4);
        Integer viisi = new Integer(5);

        lista.add(yksi);
        lista.add(kaksi);
        lista.add(kolme);
        lista.add(nelja);

        assertFalse("Lista väärän arvon kun poistettiin olematonta", lista.remove(viisi));
    }

    @Test
    public void removeObjectPalauttaaOikean() {
        Integer yksi = new Integer(1);
        Integer kaksi = new Integer(2);
        Integer kolme = new Integer(3);
        Integer nelja = new Integer(4);

        lista.add(yksi);
        lista.add(kaksi);
        lista.add(kolme);
        lista.add(nelja);

        assertTrue("Lista palautti väärän arvon kun poistetaan arvoa", lista.remove(kolme));
    }

    @Test
    public void listanKokoOikeaRemoveIndeksinJaljilta() {
        Integer yksi = new Integer(1);
        Integer kaksi = new Integer(2);
        Integer kolme = new Integer(3);
        Integer nelja = new Integer(4);

        lista.add(yksi);
        lista.add(kaksi);
        lista.add(kolme);
        lista.add(nelja);

        lista.remove(2);
        assertEquals("Listan koko väärä removen jäljiltä", lista.size(), 3);
    }

    @Test
    public void RemoveIndeksiPalauttaaOikean() {
        Integer yksi = new Integer(1);
        Integer kaksi = new Integer(2);
        Integer kolme = new Integer(3);
        Integer nelja = new Integer(4);

        lista.add(yksi);
        lista.add(kaksi);
        lista.add(kolme);
        lista.add(nelja);

        assertEquals("Palautti väärän objektin", kolme, lista.remove(2));
    }

    @Test
    public void removePoistaaObjektin() {
        Integer yksi = new Integer(1);
        Integer kaksi = new Integer(2);
        Integer kolme = new Integer(3);
        Integer nelja = new Integer(4);

        lista.add(yksi);
        lista.add(kaksi);
        lista.add(kolme);
        lista.add(nelja);

        lista.remove(kolme);

        assertTrue("contains() ei löytänyt objektia removen jäljiltä", lista.contains(yksi));
        assertTrue("contains() ei löytänyt objektia removen jäljiltä", lista.contains(kaksi));
        assertFalse("contains() löysi poistetun objektin removen jäljiljä", lista.contains(kolme));
        assertTrue("contains() ei löytänyt objektia removen jäljiltä", lista.contains(nelja));

        assertTrue("Väärä arvo indeksillä", 1 == lista.get(0));
        assertTrue("Väärä arvo indeksillä", 2 == lista.get(1));
        assertTrue("Väärä arvo indeksillä", 4 == lista.get(2));
    }

    @Test
    public void removePoistaaIndeksin() {
        Integer yksi = new Integer(1);
        Integer kaksi = new Integer(2);
        Integer kolme = new Integer(3);
        Integer nelja = new Integer(4);

        lista.add(yksi);
        lista.add(kaksi);
        lista.add(kolme);
        lista.add(nelja);

        lista.remove(2);

        assertTrue("contains() ei löytänyt objektia removen jäljiltä", lista.contains(yksi));
        assertTrue("contains() ei löytänyt objektia removen jäljiltä", lista.contains(kaksi));
        assertFalse("contains() löysi poistetun objektin removen jäljiljä", lista.contains(kolme));
        assertTrue("contains() ei löytänyt objektia removen jäljiltä", lista.contains(nelja));

        assertTrue("Väärä arvo indeksillä", 1 == lista.get(0));
        assertTrue("Väärä arvo indeksillä", 2 == lista.get(1));
        assertTrue("Väärä arvo indeksillä", 4 == lista.get(2));
    }

    @Test
    public void removePoistaaOikeinViimeisenIndeksin() {
        Integer yksi = new Integer(1);
        Integer kaksi = new Integer(2);
        Integer kolme = new Integer(3);
        Integer nelja = new Integer(4);

        lista.add(yksi);
        lista.add(kaksi);
        lista.add(kolme);
        lista.add(nelja);

        lista.remove(3);

        assertTrue("contains() ei löytänyt objektia removen jäljiltä", lista.contains(yksi));
        assertTrue("contains() ei löytänyt objektia removen jäljiltä", lista.contains(kaksi));
        assertTrue("contains() löysi poistetun objektin removen jäljiljä", lista.contains(kolme));
        assertFalse("contains() ei löytänyt objektia removen jäljiltä", lista.contains(nelja));

        assertTrue("Väärä arvo indeksillä", 1 == lista.get(0));
        assertTrue("Väärä arvo indeksillä", 2 == lista.get(1));
        assertTrue("Väärä arvo indeksillä", 3 == lista.get(2));
    }

    @Test
    public void kokoOikeinKunremovePoistaaViimeisenIndeksin() {
        Integer yksi = new Integer(1);
        Integer kaksi = new Integer(2);
        Integer kolme = new Integer(3);
        Integer nelja = new Integer(4);

        lista.add(yksi);
        lista.add(kaksi);
        lista.add(kolme);
        lista.add(nelja);

        lista.remove(3);

        assertTrue("Väärä koko listalla", lista.size() == 3);
    }

    public void kokoOikeinKunremovePoistaaAinoanIndeksin() {


        lista.add(1);
        lista.remove(0);

        assertTrue("Väärä koko listalla", lista.isEmpty());
    }

    @Test
    public void listanKokoNollaClearinJalkeen() {
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);

        lista.clear();
        assertEquals("Listan koko väärä clear()-kutsun jälkeen", 0, lista.size());
    }

    @Test
    public void listanKokoOikeaClearinJalkeenKunLisataan() {
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);

        lista.clear();
        lista.add(1);
        lista.add(2);
        assertEquals("Listan koko väärä clear()-kutsun jälkeen", 2, lista.size());
    }

    @Test
    public void listaAntaaOikeanArvonKunLisataanClearataanJaLisataan() {
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);

        lista.clear();
        lista.add(1);
        lista.add(2);
        assertTrue("Väärä arvo clear()-kutsun jälkeen", 2 == lista.get(1));
    }

    @Test
    public void isEmptyClearinJalkeen() {
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);

        lista.clear();
        assertTrue("isEmpty väärin clear()-kutsun jälkeen", lista.isEmpty());
    }

    @Test
    public void eiOleIsEmptyClearinJalkeenKunLisattyTavaraa() {
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);

        lista.clear();
        lista.add(1);

        assertFalse("isEmpty väärin clear()-kutsun jälkeen", lista.isEmpty());
    }

    @Test
    public void toArrayAntaaTyhjanArraynTyhjalleListalle() {
        Object[] array = lista.toArray();
        int pituus = array.length;
        assertEquals("Taulukon koko väärä", 0, pituus);
    }

    @Test
    public void toArrayAntaaOikeanKoonKunListassaTavaraa() {
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        Object[] array = lista.toArray();
        int pituus = array.length;
        assertEquals("Taulukon koko väärä", 4, pituus);
    }

    @Test
    public void toArrayAntaaOikeatArvot() {
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        Object[] array = lista.toArray();
        assertTrue("Taulukon arvo väärä", (Integer) array[0] == 1);
        assertTrue("Taulukon arvo väärä", (Integer) array[1] == 2);
        assertTrue("Taulukon arvo väärä", (Integer) array[2] == 3);
        assertTrue("Taulukon arvo väärä", (Integer) array[3] == 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void lukuYliKoonHeittääPoikkeuksen() {
        lista.add(1);
        lista.add(2);

        lista.get(5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void lukuYliKoonJuuriRajallaHeittääPoikkeuksen() {
        lista.add(1);
        lista.add(2);

        lista.get(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setYliKoonHeittääPoikkeuksen() {
        lista.add(1);
        lista.add(2);

        lista.set(5, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setYliKoonJuuriRajallaHeittääPoikkeuksen() {
        lista.add(1);
        lista.add(2);

        lista.set(2, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeYliKoonHeittääPoikkeuksen() {
        lista.add(1);
        lista.add(2);

        lista.remove(5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeYliKoonJuuriRajallaHeittääPoikkeuksen() {
        lista.add(1);
        lista.add(2);

        lista.remove(2);
    }

    @Test
    public void hashCodeSamaKunArraytSamat() {
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(15);

        OmaArrayList<Integer> lista2 = new OmaArrayList<Integer>();
        lista2.add(1);
        lista2.add(2);
        lista2.add(3);
        lista2.add(4);
        lista2.add(15);

        assertTrue("Hash codet eivät ole samat", lista.hashCode() == lista2.hashCode());
    }

    @Test
    public void arraytSamaKunSisaltoSama() {
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(15);

        OmaArrayList<Integer> lista2 = new OmaArrayList<Integer>();
        lista2.add(1);
        lista2.add(2);
        lista2.add(3);
        lista2.add(4);
        lista2.add(15);

        assertTrue("Listat eivät ole samat", lista.equals(lista2));
    }

    @Test
    public void arraytEriKunSisaltoEri() {
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(15);

        OmaArrayList<Integer> lista2 = new OmaArrayList<Integer>();
        lista2.add(5);
        lista2.add(2);
        lista2.add(3);
        lista2.add(4);
        lista2.add(15);

        assertFalse("Equals antaa virheellisesti true", lista.equals(lista2));
    }

    @Test
    public void arraytEriKunSisaltoSamaPaitsiViimeinen() {
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(15);

        OmaArrayList<Integer> lista2 = new OmaArrayList<Integer>();
        lista2.add(1);
        lista2.add(2);
        lista2.add(3);
        lista2.add(4);
        lista2.add(16);

        assertFalse("Equals antaa virheellisesti true", lista.equals(lista2));
    }

    @Test
    public void hashCodeSamaKunArraytSamatSatunnainen() {
        Random random = new Random();
        OmaArrayList<Integer> lista2 = new OmaArrayList<Integer>();

        for (int i = 0; i < 20; ++i) {
            int luku = random.nextInt();
            lista.add(luku);
            lista2.add(luku);
        }

        assertEquals("Hash codet eivät ole samat", lista.hashCode(), lista2.hashCode());
    }

    @Test
    public void arraytSamaKunSisaltoSamaSatunnaiset() {

        Random random = new Random();
        OmaArrayList<Integer> lista2 = new OmaArrayList<Integer>();

        for (int i = 0; i < 20; ++i) {
            int luku = random.nextInt();
            lista.add(luku);
            lista2.add(luku);
        }

        assertTrue("Listat eivät ole samat", lista.equals(lista2));
    }

    @Test
    public void arraytEriKunSisaltoEriSatunnainen() {
        Random random = new Random();
        OmaArrayList<Integer> lista2 = new OmaArrayList<Integer>();

        for (int i = 0; i < 20; ++i) {
            int luku = random.nextInt();
            lista.add(luku);
            lista2.add(luku + 1);
        }

        assertFalse("Equals antaa virheellisesti true", lista.equals(lista2));
    }
}
