/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.testipakkaus;

import astar.tietorakenteet.Lista;
import astar.verkko.Solmu;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sasumaki
 */
public class Listatesti {

    private int size;
    private Object taulu[];
    private Lista<String> lista;
    private Lista<Integer> intlista;
    private ArrayList<String> list;

    public Listatesti() {

        this.lista = new Lista<>();
        this.intlista = new Lista<>();
        this.list = new ArrayList<>();
    }

    @Before
    public void setUp() {
    }

    @Test
    public void lisaystesti() {

        String s = "a";
        lista.add(s);

        assertEquals(lista.get(0), s);

    }

    @Test
    public void lisaystesti2() {
        String s = "a";
        String s1 = "b";
        lista.add(s);
        lista.add(s1);

        assertEquals(lista.size(), 2);
    }

    @Test
    public void fortesti() {
        String s = "a";
        String s1 = "b";
        String s2 = "c";
        lista.add(s);
        lista.add(s1);
        lista.add(s2);

        Lista<String> toinenlista = new Lista<>();

        for (String z : lista) {
            toinenlista.add(z);
        }
        assertEquals(toinenlista.get(0), s);
        assertEquals(toinenlista.get(1), s1);
        assertEquals(toinenlista.get(2), s2);
    }

    @Test
    public void isolisays() {
        String s = "";
        for (int i = 0; i < 10000; i++) {
            lista.add(s);
        }
        assertEquals(lista.size(), 10000);
    }

    @Test
    public void isEmptytesti() {
        assertEquals(lista.isEmpty(), true);
    }

    @Test
    public void removetesti() {
        String s = "a";
        String s1 = "b";
        lista.add(s);
        lista.add(s1);

        lista.remove(1);
        assertEquals(lista.size(), 1);
        assertEquals(lista.get(0), s);
    }

    @Test
    public void isEmptytesti2() {
        String s = "a";
        String s1 = "b";
        lista.add(s);
        lista.add(s1);

        lista.remove(1);
        lista.remove(0);

        assertEquals(lista.isEmpty(), true);
    }

    @Test
    public void isEmptytesti3() {
        int i = 1;
        intlista.add(i);

        assertEquals(intlista.isEmpty(), false);
    }

    @Test
    public void removeIndeksinSiirtotesti() {
        String s = "a";
        String s1 = "b";
        lista.add(s);
        lista.add(s1);

        lista.remove(0);

        assertEquals(lista.get(0), s1);
    }

    @Test
    public void satunnainenTesti() {
        int i = 1;
        int i1 = 1;
        int i2 = 1;
        int i3 = 1;
        int i4 = 2;

        intlista.add(i);
        intlista.add(i1);
        intlista.add(i2);
        intlista.add(i3);
        intlista.add(i4);

        int i5 = 0;

        for (int a = 0; a < 5; a++) {
            i5 += intlista.get(a);

        }
        assertEquals(i5, 6);
    }
}
