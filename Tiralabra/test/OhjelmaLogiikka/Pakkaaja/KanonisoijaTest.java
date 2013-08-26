
package OhjelmaLogiikka.Pakkaaja;

import Tietorakenteet.TiedostoBlokki;
import Tietorakenteet.HuffmanKoodi;
import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import Tietorakenteet.Pari;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class KanonisoijaTest {

    private Kanonisoija kanonisoija;
    private OmaMap<Integer, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>>> blokkiKoodiLista;

    public KanonisoijaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kanonisoija = new Kanonisoija();
        blokkiKoodiLista = new OmaHashMap<Integer, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>>>();

    }

    @After
    public void tearDown() {
    }
  

    @Test
    public void kanonisoitujenKoodienMaaraOikea() {
        OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> lista = taytaLista();
        blokkiKoodiLista.put(6, lista);

        OmaMap<TiedostoBlokki, HuffmanKoodi> taulu = kanonisoija.kanonisoi(blokkiKoodiLista);
        assertEquals("Koodien määrä väärä", 8, taulu.size());
    }

    @Test
    public void kanonisoitujenKoodienPituudetOikein() {
        OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> lista = taytaLista();
        blokkiKoodiLista.put(6, lista);

        OmaMap<TiedostoBlokki, HuffmanKoodi> taulu = kanonisoija.kanonisoi(blokkiKoodiLista);

        TiedostoBlokki wrapper = new TiedostoBlokki();
        wrapper.byteTaulukko = new byte[1];

        wrapper.byteTaulukko[0] = 'a';
        assertEquals("Pituus väärä", 4, taulu.get(wrapper).pituus);

        wrapper.byteTaulukko[0] = 'b';
        assertEquals("Pituus väärä", 4, taulu.get(wrapper).pituus);

        wrapper.byteTaulukko[0] = 'c';
        assertEquals("Pituus väärä", 4, taulu.get(wrapper).pituus);

        wrapper.byteTaulukko[0] = 'd';
        assertEquals("Pituus väärä", 5, taulu.get(wrapper).pituus);

        wrapper.byteTaulukko[0] = 'e';
        assertEquals("Pituus väärä", 5, taulu.get(wrapper).pituus);

        wrapper.byteTaulukko[0] = 'f';
        assertEquals("Pituus väärä", 5, taulu.get(wrapper).pituus);

        wrapper.byteTaulukko[0] = 'g';
        assertEquals("Pituus väärä", 6, taulu.get(wrapper).pituus);

        wrapper.byteTaulukko[0] = 'h';
        assertEquals("Pituus väärä", 6, taulu.get(wrapper).pituus);
    }
    
    @Test
    public void kanonisoitujenKoodienArvotOikein() {
        OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> lista = taytaLista();
        blokkiKoodiLista.put(6, lista);

        OmaMap<TiedostoBlokki, HuffmanKoodi> taulu = kanonisoija.kanonisoi(blokkiKoodiLista);

        TiedostoBlokki wrapper = new TiedostoBlokki();
        wrapper.byteTaulukko = new byte[1];
        
        // käännön jälkeen koodien pitäisi olla:
        // 0000 a, 1000 b, 0100 c
        // 01100 d, 11100 e, 00010 f
        // 010010 g, 110010 h
        
        wrapper.byteTaulukko[0] = 'a';
        assertEquals("A:n koodi väärä", 0, taulu.get(wrapper).koodi);

        wrapper.byteTaulukko[0] = 'b';
        assertEquals("C:n koodi väärä", 8, taulu.get(wrapper).koodi);

        wrapper.byteTaulukko[0] = 'c';
        assertEquals("C:n koodi väärä", 4, taulu.get(wrapper).koodi);

        wrapper.byteTaulukko[0] = 'd';
        assertEquals("D:n koodi väärä", 12, taulu.get(wrapper).koodi);

        wrapper.byteTaulukko[0] = 'e';
        assertEquals("E:n koodi väärä", 28, taulu.get(wrapper).koodi);

        wrapper.byteTaulukko[0] = 'f';
        assertEquals("F:n koodi väärä", 2, taulu.get(wrapper).koodi);

        wrapper.byteTaulukko[0] = 'g';
        assertEquals("G:n koodi väärä", 18, taulu.get(wrapper).koodi);

        wrapper.byteTaulukko[0] = 'h';
        assertEquals("H:n koodi väärä", 50, taulu.get(wrapper).koodi);
    }
    
    
    @Test
    public void jarjestettyjenKoodienJarjestysOikea() {
        OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> lista = taytaLista();
        blokkiKoodiLista.put(6, lista);

        kanonisoija.kanonisoi(blokkiKoodiLista);
        OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> jarjestetyt = kanonisoija.haeKooditJarjestettyna();
        int nykyinenPituus = Integer.MIN_VALUE;
        
        for (int i  = 0; i < jarjestetyt.size(); ++i) {
            assertTrue("Koodit eivät järjestyksessä!", nykyinenPituus <= jarjestetyt.get(i).toinen.pituus);
            nykyinenPituus = jarjestetyt.get(i).toinen.pituus;
        }   
    }
    
    private OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> taytaLista() {
        OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> lista = new OmaArrayList<Pari<TiedostoBlokki, HuffmanKoodi>>();

        alusta(4, 1, new byte[]{'a'}, lista);
        alusta(4, 0, new byte[]{'b'}, lista);
        alusta(4, 2, new byte[]{'c'}, lista);
        blokkiKoodiLista.put(4, lista);

        lista = new OmaArrayList<Pari<TiedostoBlokki, HuffmanKoodi>>();
        alusta(5, 3, new byte[]{'d'}, lista);
        alusta(5, 4, new byte[]{'e'}, lista);
        alusta(5, 5, new byte[]{'f'}, lista);
        blokkiKoodiLista.put(5, lista);

        lista = new OmaArrayList<Pari<TiedostoBlokki, HuffmanKoodi>>();
        alusta(6, 3, new byte[]{'g'}, lista);
        alusta(6, 4, new byte[]{'h'}, lista);
        blokkiKoodiLista.put(6, lista);
        return lista;
    }

    private void alusta(int pituus, long koodi, byte[] blokki, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> lista) {
        HuffmanKoodi k = new HuffmanKoodi();

        TiedostoBlokki wrapper = new TiedostoBlokki();
        Pari<TiedostoBlokki, HuffmanKoodi> pari = new Pari<TiedostoBlokki, HuffmanKoodi>();


        k.pituus = pituus;
        k.koodi = koodi;
        wrapper.byteTaulukko = blokki;

        pari.ensimmainen = wrapper;
        pari.toinen = k;
        lista.add(pari);
    }
}
