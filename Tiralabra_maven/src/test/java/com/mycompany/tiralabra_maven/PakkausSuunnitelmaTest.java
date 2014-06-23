/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.domain.Laatikko;
import com.mycompany.tiralabra_maven.domain.Laatikkotyyppi;
import com.mycompany.tiralabra_maven.domain.PakkausSuunnitelma;
import com.mycompany.tiralabra_maven.domain.Palkki;
import com.mycompany.tiralabra_maven.domain.Sijainti;
import com.mycompany.tiralabra_maven.structures.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sami
 */
public class PakkausSuunnitelmaTest {
    
    private PakkausSuunnitelma ps;
    
    public PakkausSuunnitelmaTest() {
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
    public void testToString(){
        Laatikko laatikko = new Laatikko(new Laatikkotyyppi(19, 96, 86), new Sijainti(), 2);
        Palkki p = new Palkki(new Sijainti(), laatikko, 2, 1, 1);
        p.lisaaLaatikot();
        List<Palkki> list = new List<Palkki>();
        list.add(p);
        this.ps = new PakkausSuunnitelma(list);
        
        assertEquals("Laatikko: x = 19, y = 96, z = 86, orientaatio: 2, sijainti: x = 0, y = 0, z = 0\nLaatikko: x = 19, y = 96, z = 86, orientaatio: 2, sijainti: x = 96, y = 0, z = 0\n", this.ps.toString());        
    }
}
