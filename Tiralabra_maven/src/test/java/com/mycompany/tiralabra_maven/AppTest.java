/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.domain.*;
import java.util.ArrayList;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
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
public class AppTest {

    private Tilapalkki t;
    private ArrayList<Laatikkotyyppi> laatikot;

    public AppTest() {
        t = new Tilapalkki(1000, 1000, 1000);
        laatikot = new ArrayList<Laatikkotyyppi>();
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testMahtuuko() {
        t = new Tilapalkki(10, 10, 10);
        Palkki p = new Palkki(10, 10, 10);
        Palkki p2 = new Palkki(2, 2, 2);
        assertTrue(App.mahtuu(t, p));
        assertTrue(App.mahtuu(t, p2));

        p.setX(15);
        assertFalse(App.mahtuu(t, p));

        t.setX(20);
        assertTrue(App.mahtuu(t, p));

        t.setY(8);
        assertFalse(App.mahtuu(t, p));

        p.setY(5);
        assertTrue(App.mahtuu(t, p));

        t.setZ(8);
        assertFalse(App.mahtuu(t, p));

        p.setZ(5);
        assertTrue(App.mahtuu(t, p));
    }

    @Test
    public void testGeneroiLaatikoita() {
        ArrayList<Laatikkotyyppi> boksit = App.generoiLaatikoita(50, 3);
        assertEquals(3, boksit.size());
        int i = 0;
        for (Laatikkotyyppi tyyppi : boksit) {
            i += tyyppi.getLaatikot().size();
            assertTrue(tyyppi.getX() <= 100);
            assertTrue(tyyppi.getX() > 0);
            assertTrue(tyyppi.getY() <= 100);
            assertTrue(tyyppi.getY() > 0);
            assertTrue(tyyppi.getZ() <= 100);
            assertTrue(tyyppi.getZ() > 0);
        }
        assertEquals(50, i);
    }

    public ArrayList<Laatikkotyyppi> teeLaatikoita(int x, int y, int z, int n) {
        laatikot = new ArrayList<Laatikkotyyppi>();
        Laatikkotyyppi l = new Laatikkotyyppi(x, y, z);
        for (int i = 0; i < n; i++) {
            Laatikko laatikko = new Laatikko(l, new Sijainti(), 0);
            l.getLaatikot().add(laatikko);
        }
        laatikot.add(l);
        return laatikot;
    }

    @Test
    public void testHaeParasPalkkiLaatikoista() {
        laatikot = teeLaatikoita(80, 1, 100, 1);
        t = new Tilapalkki(100, 100, 100);
        Palkki p = App.haeParasPalkkiLaatikoista(t, laatikot);
        assertEquals(80, p.getX());
        assertEquals(1, p.getY());
        assertEquals(100, p.getZ());

        t = new Tilapalkki(200, 100, 100);
        p = App.haeParasPalkkiLaatikoista(t, laatikot);
        assertEquals(80, p.getX());
        assertEquals(1, p.getY());
        assertEquals(100, p.getZ());

        t = new Tilapalkki(100, 100, 100);
        laatikot = teeLaatikoita(10, 10, 10, 10000);
        p = App.haeParasPalkkiLaatikoista(t, laatikot);
        assertEquals(100, p.getX());
        assertEquals(100, p.getY());
        assertEquals(100, p.getZ());
        assertEquals(10, p.getNx());
        assertEquals(10, p.getNy());
        assertEquals(10, p.getNz());

        laatikot = teeLaatikoita(10, 100, 100, 100);
        p = App.haeParasPalkkiLaatikoista(t, laatikot);
        assertEquals(100, p.getX());
        assertEquals(100, p.getY());
        assertEquals(100, p.getZ());
        assertEquals(10, p.getNx());
        assertEquals(1, p.getNy());
        assertEquals(1, p.getNz());
    }

// kesken
    @Test
    public void testParempi() {
        PakkausSuunnitelma p1 = new PakkausSuunnitelma();
        PakkausSuunnitelma p2 = new PakkausSuunnitelma();

    }
}
