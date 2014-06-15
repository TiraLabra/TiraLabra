/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.domain.*;
import com.mycompany.tiralabra_maven.structures.List;
import com.mycompany.tiralabra_maven.tools.Console;
import com.mycompany.tiralabra_maven.tools.Io;
import java.util.Scanner;
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
public class PakkaajaTest {

    private Tilapalkki t;
    private List<Laatikkotyyppi> laatikot;
    private Pakkaaja pakkaaja;

    public PakkaajaTest() {
        t = new Tilapalkki(1000, 1000, 1000);
        laatikot = new List<Laatikkotyyppi>();
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
    public void testGeneroiLaatikoita() {
        Io io = new Console(new Scanner(System.in));
        this.pakkaaja = new Pakkaaja(io);
        List<Laatikkotyyppi> boksit = this.pakkaaja.generoiLaatikoita(50, 3);
        assertEquals(3, boksit.size());
        int i = 0;
        for (int j = 0; j < boksit.size(); j++) {
            Laatikkotyyppi tyyppi = boksit.get(j);
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

    public List<Laatikkotyyppi> teeLaatikoita(int x, int y, int z, int n) {
        laatikot = new List<Laatikkotyyppi>();
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
        Io io = new Console(new Scanner(System.in));
        this.pakkaaja = new Pakkaaja(io);
        
        laatikot = teeLaatikoita(80, 1, 100, 1);
        t = new Tilapalkki(100, 100, 100);
        Palkki p = this.pakkaaja.haeParasPalkkiLaatikoista(t, laatikot);
        assertEquals(80, p.getX());
        assertEquals(1, p.getY());
        assertEquals(100, p.getZ());

        t = new Tilapalkki(200, 100, 100);
        p = this.pakkaaja.haeParasPalkkiLaatikoista(t, laatikot);
        assertEquals(80, p.getX());
        assertEquals(1, p.getY());
        assertEquals(100, p.getZ());

        t = new Tilapalkki(100, 100, 100);
        laatikot = teeLaatikoita(10, 10, 10, 10000);
        p = this.pakkaaja.haeParasPalkkiLaatikoista(t, laatikot);
        assertEquals(100, p.getX());
        assertEquals(100, p.getY());
        assertEquals(100, p.getZ());
        assertEquals(10, p.getNx());
        assertEquals(10, p.getNy());
        assertEquals(10, p.getNz());

        laatikot = teeLaatikoita(10, 100, 100, 100);
        p = this.pakkaaja.haeParasPalkkiLaatikoista(t, laatikot);
        assertEquals(100, p.getX());
        assertEquals(100, p.getY());
        assertEquals(100, p.getZ());
        assertEquals(10, p.getNx());
        assertEquals(1, p.getNy());
        assertEquals(1, p.getNz());
    }
    
}
