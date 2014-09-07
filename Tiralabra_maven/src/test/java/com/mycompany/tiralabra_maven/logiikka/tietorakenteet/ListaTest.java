package com.mycompany.tiralabra_maven.logiikka.tietorakenteet;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static junit.framework.Assert.*;
import org.junit.*;
import org.junit.rules.ExpectedException;

/**
 *
 * @author mikko
 */
public class ListaTest {

    private Lista<String> lista;

    @Before
    public void setUp() {
        lista = new Lista<>();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void listanKokoOnAlussaNollaJaListaOnTyhja() {
        assertEquals(0, lista.size());
        assertTrue(lista.isEmpty());
    }

    @Test
    public void alkioidenLisaaminenKasvattaaListanKokoa() {
        lista.add("Mikko");
        assertEquals(1, lista.size());
        lista.add("Matti");
        assertEquals(2, lista.size());
        lista.add("Topias");
        assertEquals(3, lista.size());
        lista.add("Einari");
        assertEquals(4, lista.size());
        lista.add("Kalevi");
        assertEquals(5, lista.size());
        lista.add("Henri");
        assertEquals(6, lista.size());
        lista.add("Santeri");
        assertEquals(7, lista.size());
        lista.add("Samuli");
        assertEquals(8, lista.size());
        lista.add("Antero");
        assertEquals(9, lista.size());
        lista.add("Pauli");
        assertEquals(10, lista.size());
        lista.add("Roope");
        assertEquals(11, lista.size());
        lista.add("Viljami");
        assertEquals(12, lista.size());
        lista.add("Veeti");
        assertEquals(13, lista.size());
        lista.add("Vadim");
        assertEquals(14, lista.size());

    }

    @Test
    public void listastaSaadaanSinneLisatytAlkiot() {
        lista.add("Juhani");
        lista.add("Petteri");
        lista.add("Tommi1");
        lista.add("Tommi2");
        lista.add("Tommi3");
        lista.add("Tommi4");
        lista.add("Tommi5");
        lista.add("Tommi6");
        lista.add("Tommi7");
        lista.add("Tommi8");
        lista.add("Tommi9");
        lista.add("Tommi10");
        lista.add("Tommi11");
        lista.add("Tommi12");
        lista.add("Tommi13");
        lista.add("Tommi14");
        lista.add("Tommi15");
        lista.add("Tommi16");
        lista.add("Tommi17");
        lista.add("Tommi18");
        lista.add("Tommi19");
        lista.add("Tommi20");
        lista.add("Tommi21");
        lista.add("Tommi22");

        assertEquals("Juhani", lista.get(0));
        assertEquals("Petteri", lista.get(1));
        assertEquals("Tommi1", lista.get(2));
        assertEquals("Tommi22", lista.get(lista.size() - 1));
    }

    @Test
    public void poistaminenIndeksinMukaanPoistaaOikeanAlkion() {
        lista.add("Anna");
        lista.add("Johanna");
        lista.add("Iida");
        lista.add("Roosa-Maria");
        lista.remove(1);
        assertEquals(3, lista.size());
        assertEquals("Anna", lista.get(0));
        assertEquals("Iida", lista.get(1));
        assertEquals("Roosa-Maria", lista.get(2));

    }

    @Test
    public void containsPalauttaaTrueJosJaVainJosListastaLoytyyLisattyAlkio() {
        lista.add("Satu");
        assertTrue(lista.contains("Satu"));
        assertFalse(lista.contains("Suvi"));
    }

    @Test
    public void listanAlkiotIteroidaanOikeassaJarjestyksessa() {
        String[] oikeaJarjestys = {"Tuomas", "Kosti", "Kalevi", "Matias"};
        for (int i = 0; i < oikeaJarjestys.length; i++) {
            lista.add(oikeaJarjestys[i]);
        }

        int i = 0;
        for (String nimi : lista) {
            assertEquals(oikeaJarjestys[i], nimi);
            i++;
        }
    }

    @Test
    public void listaEiSisallaErityyppistaAlkiota() {
        Lista<Integer> numerolista = new Lista<>();
        numerolista.add(3);
        assertFalse(numerolista.contains("Petteri"));
    }

    @Test
    public void iteraattoriOsaaPoistaaAlkion() {
        lista.add("Noora");
        lista.add("Kristiina");
        lista.add("Julia");
        lista.add("Katariina");
        Iterator<String> iteraattori = lista.iterator();

        while (iteraattori.hasNext()) {
            String nimi = iteraattori.next();
            if (nimi.equals("Julia")) {
                iteraattori.remove();
                break;
            }
        }
        assertEquals("Katariina", iteraattori.next());
        assertEquals(3, lista.size());
        assertEquals("Noora", lista.get(0));
        assertEquals("Kristiina", lista.get(1));
        assertEquals("Katariina", lista.get(2));

    }

    @Test
    public void iteraattoriPalauttaaNoSuchElementExceptioninJosKaikkiOnJoKaytyLapiJaYritetaanSaadaSeuraava() {
        lista.add("Teuvo");
        lista.add("Mika");
        Iterator<String> iteraattori = lista.iterator();

        iteraattori.next();
        iteraattori.next();
        exception.expect(NoSuchElementException.class);
        iteraattori.next();
        

    }

}
