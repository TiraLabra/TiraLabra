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
        t = new Tila(k, teeLaatikkotyyppeja(100, 100, 100, 10));
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

    public ArrayList<Laatikko> teeLaatikoita(int x, int y, int z, int n) {
        ArrayList<Laatikko> laatikot = new ArrayList<Laatikko>();
        Laatikkotyyppi l = new Laatikkotyyppi(x, y, z);
        for (int i = 0; i < n; i++) {
            Laatikko laatikko = new Laatikko(l, new Sijainti(), 0);
            l.getLaatikot().add(laatikko);
            laatikot.add(laatikko);
        }
        return laatikot;
    }

    public ArrayList<Laatikkotyyppi> teeLaatikkotyyppeja(int x, int y, int z, int n){
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
        Palkki p = new Palkki(50, 50, 50, teeLaatikoita(50, 50, 50, 10));
        assertEquals(t.getVapaatTilapalkit().size(), 1);
        t.lisaaTilapalkit(k, p, s);
        assertEquals(t.getVapaatTilapalkit().size(), 4);
    }
    
    @Test
    public void testLisaaOikeatTilapalkit() {
        Palkki p = new Palkki(50, 50, 50, teeLaatikoita(50, 50, 50, 10));
        t.lisaaTilapalkit(k, p, s);
        assertEquals(t.getVapaatTilapalkit().pop().getX(), k.getX());
        assertEquals(t.getVapaatTilapalkit().pop().getX(), k.getX() - 50);
        
    }
}
