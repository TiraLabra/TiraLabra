/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.domain.*;
import java.util.ArrayList;
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

   public ArrayList<Laatikkotyyppi> teeLaatikoita(int x, int y, int z, int n){
        ArrayList<Laatikkotyyppi> laatikot = new ArrayList<Laatikkotyyppi>();
        Laatikkotyyppi l = new Laatikkotyyppi(x, y, z);
        for (int i = 0; i < n; i++){
            Laatikko laatikko = new Laatikko(l, new Sijainti(), 0);
            l.getLaatikot().add(laatikko);
        }
        laatikot.add(l);
        return laatikot;
    }

    @Test
    public void testLisataanTilapalkkeja() {
        Palkki p = new Palkki(new Laatikkotyyppi(50, 50, 50), 3, 1, 1);
        assertEquals(t.getTilapalkit().size(), 1);
        t.lisaaTilapalkit(k, p, s);
        assertEquals(t.getTilapalkit().size(), 4);
    }

    @Test
    public void testLisaaOikeatTilapalkit() {
        Palkki p = new Palkki(new Laatikkotyyppi(50, 50, 50), 1, 1, 1);
        t.lisaaTilapalkit(k, p, s);
        Tilapalkki tp = t.getTilapalkit().pop();

        assertEquals(p.getX(), tp.getX());
        assertEquals(p.getY(), tp.getY());
        assertEquals(k.getZ() - p.getZ() - s.getZ(), tp.getZ());

        tp = t.getTilapalkit().pop();
        assertEquals(k.getX() - p.getX() - s.getX(), tp.getX());
        assertEquals(p.getY(), tp.getY());
        assertEquals(k.getZ() - s.getX(), tp.getZ());
 

        tp = t.getTilapalkit().pop();
        assertEquals(k.getX() - s.getX(), tp.getX());
        assertEquals(k.getY() - p.getX() - s.getX(), tp.getY());
        assertEquals(k.getZ() - s.getX(), tp.getZ());

    }

    @Test
    public void testPoistaVapaistaLaatikoista() {
        Palkki p = new Palkki(new Laatikkotyyppi(50, 50, 50), 3, 1, 1);
//	t.setVapaatTilapalkit();
    }
}
