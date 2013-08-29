/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OhjelmaLogiikka.Pakkaaja;

import TestiTiedostoLuokat.TestiLukija;
import Tietorakenteet.HuffmanKoodi;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import Tietorakenteet.OmaQueue;
import Tietorakenteet.OmaTreeNode;
import Tietorakenteet.Pari;
import Tietorakenteet.TiedostoBlokki;
import java.io.IOException;
import java.util.Arrays;
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
public class HuffmanKoodiMuodostajaTest {

    private HuffmanKoodiMuodostaja muodostaja;
    private EsiintymisTiheysLaskija laskija;
    private TestiLukija lukija;

    public HuffmanKoodiMuodostajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        muodostaja = new HuffmanKoodiMuodostaja();
        laskija = new EsiintymisTiheysLaskija();
        lukija = new TestiLukija();
        lukija.tavut = "aaaabbbccd".getBytes();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void muodostaaOikeatKooditKunBlokinKoko1() {
        try {
            // pitäisi muodostua koodit a - 0, b - 01, c - 111, d - 011
            OmaMap<Integer, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>>> koodit = muodostaja.muodostaHuffmanKoodit(laskija.laskeEsiintymisTiheydet(lukija, 1));
            Pari<TiedostoBlokki, HuffmanKoodi> pari;
            OmaList<Integer> avaimet = koodit.avaimet();

            for (int i = 0; i < avaimet.size(); ++i) {
                OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> lista = koodit.get(avaimet.get(i));
                for (int j = 0; j < lista.size(); ++j) {
                    pari = lista.get(j);


                    vertaileKoodi(pari, "a", 0);
                    vertaileKoodi(pari, "b", 1);
                    vertaileKoodi(pari, "c", 7);
                    vertaileKoodi(pari, "d", 3);
                }
            }
        } catch (IOException ex) {
            assertTrue("Ei pitäisi, tapahtua...", false);
        }
    }

    @Test
    public void muodostaaOikeatKooditKunBlokinKoko2() {
        try {
            // pitäisi muodostua koodit aa - 0, bb - 01, bc - 111, cd - 011
            OmaMap<Integer, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>>> koodit = muodostaja.muodostaHuffmanKoodit(laskija.laskeEsiintymisTiheydet(lukija, 2));
            Pari<TiedostoBlokki, HuffmanKoodi> pari;
            OmaList<Integer> avaimet = koodit.avaimet();

            for (int i = 0; i < avaimet.size(); ++i) {
                OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> lista = koodit.get(avaimet.get(i));
                for (int j = 0; j < lista.size(); ++j) {
                    pari = lista.get(j);

                    vertaileKoodi(pari, "aa", 0);
                    vertaileKoodi(pari, "bb", 1);
                    vertaileKoodi(pari, "bc", 7);
                    vertaileKoodi(pari, "cd", 3);
                }
            }
        } catch (IOException ex) {
            assertTrue("Ei pitäisi, tapahtua...", false);
        }
    }

    private void vertaileKoodi(Pari<TiedostoBlokki, HuffmanKoodi> koodiBlokki, String teksti, int koodi) {
        if (koodiBlokki.ensimmainen.size() != teksti.length()) {
            return;
        }

        for (int i = 0; i < koodiBlokki.ensimmainen.size(); ++i) {
            if (koodiBlokki.ensimmainen.byteTaulukko[i] != teksti.getBytes()[i]) {
                return;
            }
        }

        assertEquals("väärä koodi: ", koodi, koodiBlokki.toinen.koodi);
    }

    @Test
    public void alkiotOikeinPrioriteettiJonossaKunBlokinKoko1() {
        try {
            OmaQueue<OmaTreeNode<Integer, TiedostoBlokki>> jono = muodostaja.muodostaPrioriteettiJono(laskija.laskeEsiintymisTiheydet(lukija, 1));
            assertEquals("Väärä alkio jonossa", 'd', jono.pop().haeArvo().byteTaulukko[0]);
            assertEquals("Väärä alkio jonossa", 'c', jono.pop().haeArvo().byteTaulukko[0]);
            assertEquals("Väärä alkio jonossa", 'b', jono.pop().haeArvo().byteTaulukko[0]);
            assertEquals("Väärä alkio jonossa", 'a', jono.pop().haeArvo().byteTaulukko[0]);

        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test
    public void alkiodenMaaraOikeinPrioriteettiJonossaKunBlokinKoko1() {
        try {
            OmaQueue<OmaTreeNode<Integer, TiedostoBlokki>> jono = muodostaja.muodostaPrioriteettiJono(laskija.laskeEsiintymisTiheydet(lukija, 1));
            assertEquals("Väärä alkio jonossa", new Integer(1), jono.pop().haeAvain());
            assertEquals("Väärä alkio jonossa", new Integer(2), jono.pop().haeAvain());
            assertEquals("Väärä alkio jonossa", new Integer(3), jono.pop().haeAvain());
            assertEquals("Väärä alkio jonossa", new Integer(4), jono.pop().haeAvain());

        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test
    public void alkiotOikeinPrioriteettiJonossaKunBlokinKoko2() {
        try {
            OmaQueue<OmaTreeNode<Integer, TiedostoBlokki>> jono = muodostaja.muodostaPrioriteettiJono(laskija.laskeEsiintymisTiheydet(lukija, 2));
            assertTrue("Väärä alkio jonossa", Arrays.equals("cd".getBytes(), jono.pop().haeArvo().byteTaulukko));
            assertTrue("Väärä alkio jonossa", Arrays.equals("bc".getBytes(), jono.pop().haeArvo().byteTaulukko));
            assertTrue("Väärä alkio jonossa", Arrays.equals("bb".getBytes(), jono.pop().haeArvo().byteTaulukko));
            assertTrue("Väärä alkio jonossa", Arrays.equals("aa".getBytes(), jono.pop().haeArvo().byteTaulukko));

        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test
    public void alkiodenMaaraOikeinPrioriteettiJonossaKunBlokinKoko2() {
        try {
            OmaQueue<OmaTreeNode<Integer, TiedostoBlokki>> jono = muodostaja.muodostaPrioriteettiJono(laskija.laskeEsiintymisTiheydet(lukija, 2));
            assertEquals("Väärä alkio jonossa", new Integer(1), jono.pop().haeAvain());
            assertEquals("Väärä alkio jonossa", new Integer(1), jono.pop().haeAvain());
            assertEquals("Väärä alkio jonossa", new Integer(1), jono.pop().haeAvain());
            assertEquals("Väärä alkio jonossa", new Integer(2), jono.pop().haeAvain());

        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    /*
     * pitäisi olla: 
     *                  *
     *                 / \
     *                a   *
     *                   / \
     *                  b   *
     *                     / \
     *                    d   c   
     */
    @Test
    public void puuMuodostuuOikeinKunBlokinKoko1() {
        try {
            OmaTreeNode<Integer, TiedostoBlokki> juuri =muodostaja.muodostaHuffmanPuu(muodostaja.muodostaPrioriteettiJono(laskija.laskeEsiintymisTiheydet(lukija, 1)));
            assertTrue("Väärä arvo", Arrays.equals(((TiedostoBlokki)juuri.vasenLapsi().haeArvo()).byteTaulukko, "a".getBytes()));
            
            juuri = juuri.oikeaLapsi();
            assertTrue("Väärä arvo", Arrays.equals(((TiedostoBlokki)juuri.vasenLapsi().haeArvo()).byteTaulukko, "b".getBytes()));
            
            juuri = juuri.oikeaLapsi();
            assertTrue("Väärä arvo", Arrays.equals(((TiedostoBlokki)juuri.vasenLapsi().haeArvo()).byteTaulukko, "d".getBytes()));
            assertTrue("Väärä arvo", Arrays.equals(((TiedostoBlokki)juuri.oikeaLapsi().haeArvo()).byteTaulukko, "c".getBytes()));
            
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }
    
    /*
     * pitäisi olla: 
     *                  *
     *                 / \
     *               aa   *
     *                   / \
     *                 bb   *
     *                     / \
     *                   cd   bc   
     */
    @Test
    public void puuMuodostuuOikeinKunBlokinKoko2() {
        try {
            OmaTreeNode<Integer, TiedostoBlokki> juuri =muodostaja.muodostaHuffmanPuu(muodostaja.muodostaPrioriteettiJono(laskija.laskeEsiintymisTiheydet(lukija, 2)));
            assertTrue("Väärä arvo", Arrays.equals(((TiedostoBlokki)juuri.vasenLapsi().haeArvo()).byteTaulukko, "aa".getBytes()));
            
            juuri = juuri.oikeaLapsi();
            assertTrue("Väärä arvo", Arrays.equals(((TiedostoBlokki)juuri.vasenLapsi().haeArvo()).byteTaulukko, "bb".getBytes()));
            
            juuri = juuri.oikeaLapsi();
            assertTrue("Väärä arvo", Arrays.equals(((TiedostoBlokki)juuri.vasenLapsi().haeArvo()).byteTaulukko, "cd".getBytes()));
            assertTrue("Väärä arvo", Arrays.equals(((TiedostoBlokki)juuri.oikeaLapsi().haeArvo()).byteTaulukko, "bc".getBytes()));
            
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }
}
