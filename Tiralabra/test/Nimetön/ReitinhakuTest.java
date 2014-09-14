/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nimetön;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaakko
 */
public class ReitinhakuTest {
    
    public ReitinhakuTest() {
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

    /**
     * Test of Haku method, of class Reitinhaku.
     */
    @Test
    public void testHaku() {
        System.out.println("Haku");
        Reitinhaku instance = new Reitinhaku();
        instance.Haku();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of TarkistaViereiset method, of class Reitinhaku.
     */
    @Test
    public void testTarkistaViereiset() {
        System.out.println("TarkistaViereiset");
        Solmu Käsittelyssä = null;
        Reitinhaku instance = new Reitinhaku();
        instance.TarkistaViereiset(Käsittelyssä);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Lisää method, of class Reitinhaku.
     */
    @Test
    public void testLisää() {
        System.out.println("Lis\u00e4\u00e4");
        int x = 0;
        int y = 0;
        Solmu Käsittelyssä = null;
        Reitinhaku instance = new Reitinhaku();
        instance.Lisää(x, y, Käsittelyssä);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tulosta method, of class Reitinhaku.
     */
    @Test
    public void testTulosta() {
        System.out.println("tulosta");
        Solmu[][] asd = null;
        Reitinhaku instance = new Reitinhaku();
        instance.tulosta(asd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
