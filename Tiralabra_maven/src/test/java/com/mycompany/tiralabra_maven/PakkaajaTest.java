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
        Io io = new Console(new Scanner(System.in));
        this.pakkaaja = new Pakkaaja(io);
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

    public List<Laatikkotyyppi> teeLaatikoita(int x, int y, int z, int n) {
        laatikot = new List<Laatikkotyyppi>();
        Laatikkotyyppi l = new Laatikkotyyppi(x, y, z);
        l.setLaatikoita(n);
        laatikot.add(l);
        return laatikot;
    }

    @Test
    public void testHaeParasPalkkiLaatikoista() {
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

    @Test
    public void testOrientaationMaaratAkseleittainPysyyRajoissa() {
        Laatikkotyyppi lt = new Laatikkotyyppi(59, 54, 86);
        Laatikko l = new Laatikko(lt, new Sijainti(), 0);
        Tilapalkki tp = new Tilapalkki(6058, 2438, 2591);
        long[] taulu = this.pakkaaja.orientaationMaaratAkseleittain(tp, l, 1000000);

        assertTrue(tp.getX() >= taulu[0] * 59);
        assertTrue(tp.getY() >= taulu[1] * 54);
        assertTrue(tp.getZ() >= taulu[2] * 86);

    }

    @Test
    public void testOrientaationMaaratOikeinKunPaljonLaatikoita() {
        Laatikkotyyppi lt = new Laatikkotyyppi(59, 54, 86);
        Laatikko l = new Laatikko(lt, new Sijainti(), 0);
        Tilapalkki tp = new Tilapalkki(6058, 2438, 2591);
        long[] taulu = this.pakkaaja.orientaationMaaratAkseleittain(tp, l, 1000000);

        assertEquals(102, taulu[0]);
        assertEquals(45, taulu[1]);
        assertEquals(30, taulu[2]);

    }

    @Test
    public void testOrientaationMaaratOikeinKunVahemmanLaatikoita() {
        Laatikkotyyppi lt = new Laatikkotyyppi(59, 54, 86);
        Laatikko l = new Laatikko(lt, new Sijainti(), 0);
        Tilapalkki tp = new Tilapalkki(6058, 2438, 2591);
        long[] taulu = this.pakkaaja.orientaationMaaratAkseleittain(tp, l, 100);

        assertEquals(3, taulu[0]);
        assertEquals(1, taulu[1]);
        assertEquals(30, taulu[2]);

    }

    @Test
    public void testValitseOrientaatioPysyyRajoissa() {
        Laatikkotyyppi lt = new Laatikkotyyppi(59, 54, 86);
        Laatikko l = new Laatikko(lt, new Sijainti(), 0);
        Tilapalkki tp = new Tilapalkki(6058, 2438, 2591);
        Palkki p = this.pakkaaja.valitseOrientaatio(tp, l, 1000000);

        assertTrue(tp.getX() >= p.getX());
        assertTrue(tp.getY() >= p.getY());
        assertTrue(tp.getZ() >= p.getZ());
    }

    @Test
    public void testValitseOrientaationMaaratOikeinKunPaljonLaatikoita() {
        Laatikkotyyppi lt = new Laatikkotyyppi(59, 54, 86);
        Laatikko l = new Laatikko(lt, new Sijainti(), 0);
        Tilapalkki tp = new Tilapalkki(6058, 2438, 2591);
        Palkki p = this.pakkaaja.valitseOrientaatio(tp, l, 1000000);

        assertEquals(112, p.getNx());
        assertEquals(41, p.getNy());
        assertEquals(30, p.getNz());

    }

    @Test
    public void testValitseOrientaationMaaratOikeinKunPaljonLaatikoita2() {
        Laatikkotyyppi lt = new Laatikkotyyppi(19, 96, 86);
        Laatikko l = new Laatikko(lt, new Sijainti(), 0);
        Tilapalkki tp = new Tilapalkki(5100, 19, 2591);
        Palkki p = this.pakkaaja.valitseOrientaatio(tp, l, 1000000);

        // 2(yxz) tai 5(zxy)
        assertEquals(2, p.getOrientaatio());

        assertEquals(53, p.getNx()); // 5100/96 = 53
        assertEquals(1, p.getNy()); // 19/19 = 1
        assertEquals(30, p.getNz()); // 2591/86 = 30

    }

    @Test
    public void testValitseOrientaatioNullKunEiMahdu() {
        Laatikkotyyppi lt = new Laatikkotyyppi(19, 96, 86);
        Laatikko l = new Laatikko(lt, new Sijainti(), 0);
        Tilapalkki tp = new Tilapalkki(5, 19, 25);
        Palkki p = this.pakkaaja.valitseOrientaatio(tp, l, 1000000);
        
        assertEquals(null, p);

    }

}
