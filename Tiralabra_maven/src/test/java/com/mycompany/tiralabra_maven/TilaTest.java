/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.domain.*;
import com.mycompany.tiralabra_maven.structures.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author szetk
 */
public class TilaTest {

    private Kontti k;
    private Tila t;
    private Sijainti s;

    public TilaTest() {
        s = new Sijainti();
        k = new Kontti(100, 100, 100);
        t = new Tila(k, teeLaatikoita(100, 100, 100, 10));
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

    public List<Laatikkotyyppi> teeLaatikoita(int x, int y, int z, int n) {
        List<Laatikkotyyppi> laatikot = new List<Laatikkotyyppi>();
        Laatikkotyyppi l = new Laatikkotyyppi(x, y, z);
        l.setLaatikoita(n);
        laatikot.add(l);
        return laatikot;
    }

    @Test
    public void testLisataanTilapalkkeja() {
        Laatikko laatikko = new Laatikko(new Laatikkotyyppi(50, 50, 50), new Sijainti(), 0);
        Palkki p = new Palkki(new Sijainti(), laatikko, 3, 1, 1);
        assertEquals(t.getTilapalkit().size(), 1);
        Tilapalkki tila = new Tilapalkki(100, 100, 100);
        t.lisaaTilapalkit(tila, p);
        assertEquals(t.getTilapalkit().size(), 4);
    }

    @Test
    public void testLisaaOikeatTilapalkit() {
        Laatikko laatikko = new Laatikko(new Laatikkotyyppi(50, 50, 50), new Sijainti(), 0);
        Palkki p = new Palkki(new Sijainti(), laatikko, 3, 1, 1);
        Tilapalkki tila = new Tilapalkki(100, 100, 100);
        t.lisaaTilapalkit(tila, p);
        Tilapalkki tp = t.getTilapalkit().pop();

        assertEquals(p.getX(), tp.getX());
        assertEquals(p.getY(), tp.getY());
        assertEquals(k.getZ() - p.getZ() - s.getZ(), tp.getZ());

        tp = t.getTilapalkit().pop();
        assertEquals(k.getX() - s.getX(), tp.getX());
        assertEquals(k.getY() - p.getY() - s.getY(), tp.getY());
        assertEquals(k.getZ() - s.getZ(), tp.getZ());

        tp = t.getTilapalkit().pop();
        assertEquals(k.getX() - p.getX() - s.getX(), tp.getX());
        assertEquals(p.getY(), tp.getY());
        assertEquals(k.getZ() - s.getX(), tp.getZ());

    }

    @Test
    public void testLisaaOikeatTilapalkitKunEiOrigossa() {
        Laatikko laatikko = new Laatikko(new Laatikkotyyppi(19, 96, 86), new Sijainti(), 2);
        Palkki p = new Palkki(new Sijainti(), laatikko, 53, 1, 30);
        // palkin koko: 5088, 19, 2580
        Tilapalkki tila = new Tilapalkki(5100, 20, 2591);
        tila.setSijainti(new Sijainti(1, 2, 3));
        t.lisaaTilapalkit(tila, p);
        Tilapalkki tp = t.getTilapalkit().pop();

        assertEquals(5088, tp.getX());
        assertEquals(19, tp.getY());
        assertEquals(11, tp.getZ());

        tp = t.getTilapalkit().pop();
        assertEquals(5100, tp.getX());
        assertEquals(1, tp.getY());
        assertEquals(2591, tp.getZ());

        tp = t.getTilapalkit().pop();
        assertEquals(12, tp.getX());
        assertEquals(19, tp.getY());
        assertEquals(2591, tp.getZ());

    }

    @Test
    public void testLisaaTilapalkinOikeaanSijaintiin() {
        Laatikko laatikko = new Laatikko(new Laatikkotyyppi(19, 96, 86), new Sijainti(), 2);
        Palkki p = new Palkki(new Sijainti(), laatikko, 53, 1, 30);
        // palkin koko: 5088, 19, 2580
        Tilapalkki tila = new Tilapalkki(5100, 20, 2591);
        tila.setSijainti(new Sijainti(1, 2, 3));
        t.lisaaTilapalkit(tila, p);
        Tilapalkki tp = t.getTilapalkit().pop();

        Sijainti sij = tp.getSijainti();
        assertEquals(1, sij.getX());
        assertEquals(2, sij.getY());
        assertEquals(2580 + 3, sij.getZ());

        tp = t.getTilapalkit().pop();
        sij = tp.getSijainti();
        assertEquals(1, sij.getX());
        assertEquals(2 + 19, sij.getY());
        assertEquals(3, sij.getZ());

        tp = t.getTilapalkit().pop();
        sij = tp.getSijainti();
        assertEquals(1 + 5088, sij.getX());
        assertEquals(2, sij.getY());
        assertEquals(3, sij.getZ());

    }

    @Test
    public void testVapaitaLaatikoita() {
        Laatikkotyyppi laatikkotyyppi = new Laatikkotyyppi(50, 50, 50);
        laatikkotyyppi.setLaatikoita(100);
        Laatikko laatikko = new Laatikko(laatikkotyyppi, new Sijainti(), 0);
        Palkki p = new Palkki(new Sijainti(), laatikko, 3, 1, 1);
        p.lisaaLaatikot();
        List<Laatikkotyyppi> list = new List<Laatikkotyyppi>();
        t.setVapaatLaatikot(list);
        assertEquals(0, t.vapaitaLaatikoita());

        list.add(laatikkotyyppi);
        t.setVapaatLaatikot(list);
        assertEquals(100, t.vapaitaLaatikoita());
        t.poistaVapaistaLaatikoista(p);

//        assertEquals(97, );
    }
    
    @Test
    public void testVapaitaLaatikoita2() {
        Laatikkotyyppi laatikkotyyppi = new Laatikkotyyppi(50, 50, 50);
        laatikkotyyppi.setLaatikoita(100);
        Laatikko laatikko = new Laatikko(laatikkotyyppi, new Sijainti(), 0);
        Palkki p = new Palkki(new Sijainti(), laatikko, 3, 1, 1);
        p.lisaaLaatikot();
        List<Laatikkotyyppi> list = new List<Laatikkotyyppi>();
        t.setVapaatLaatikot(list);
        assertEquals(0, t.vapaitaLaatikoita());

        list.add(laatikkotyyppi);
        t.setVapaatLaatikot(list);
        assertEquals(100, t.vapaitaLaatikoita());
        t.poistaVapaistaLaatikoista(p);

//        assertEquals(97, );
    }

    @Test
    public void testPoistaVapaistaLaatikoista() {
        Laatikkotyyppi laatikkotyyppi = new Laatikkotyyppi(50, 50, 50);
        laatikkotyyppi.setLaatikoita(100);
        Laatikko laatikko = new Laatikko(laatikkotyyppi, new Sijainti(), 0);
        Palkki p = new Palkki(new Sijainti(), laatikko, 3, 1, 1);
        p.lisaaLaatikot();
        List<Laatikkotyyppi> list = new List<Laatikkotyyppi>();
        list.add(laatikkotyyppi);
        t.setVapaatLaatikot(list);
        assertEquals(100, t.vapaitaLaatikoita());
        t.poistaVapaistaLaatikoista(p);
        assertEquals(97, t.vapaitaLaatikoita());

//        assertEquals(97, );
    }
}
