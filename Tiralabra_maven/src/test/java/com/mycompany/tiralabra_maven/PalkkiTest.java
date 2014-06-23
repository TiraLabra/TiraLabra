/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.domain.Laatikko;
import com.mycompany.tiralabra_maven.domain.Laatikkotyyppi;
import com.mycompany.tiralabra_maven.domain.Palkki;
import com.mycompany.tiralabra_maven.domain.Sijainti;
import com.mycompany.tiralabra_maven.domain.Tilapalkki;
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
public class PalkkiTest {

    private Palkki p;

    public PalkkiTest() {
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
    public void testKonstruktori() {
        Laatikkotyyppi lt = new Laatikkotyyppi(59, 54, 86);
        Laatikko l = new Laatikko(lt, new Sijainti(), 0);
        Tilapalkki tp = new Tilapalkki(6058, 2438, 2591);
        this.p = new Palkki(tp.getSijainti(), l, 102, 45, 30);

        assertEquals(102, p.getNx());
        assertEquals(45, p.getNy());
        assertEquals(30, p.getNz());
    }

}
