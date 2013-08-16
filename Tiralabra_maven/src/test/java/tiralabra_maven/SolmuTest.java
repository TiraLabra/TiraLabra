/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra_maven;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author esaaksvu
 */
public class SolmuTest {

    public SolmuTest() {
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
     * Test of setVasen method, of class Solmu.
     */
    @Test
    public void testSetVasen() {
        System.out.println("setVasen");
        Solmu s1 = new Solmu(3);
        Solmu instance = new Solmu(5);
        instance.setVasen(s1);
        assertEquals("5{3{null,null},null}", instance.toString());
    }

    /**
     * Test of setOikea method, of class Solmu.
     */
    @Test
    public void testSetOikea() {
        System.out.println("setOikea");
        Solmu s1 = new Solmu(7);
        Solmu instance = new Solmu(2);
        instance.setOikea(s1);
        assertEquals("2{null,7{null,null}}", instance.toString());
    }

    /**
     * Test of lapseton method, of class Solmu.
     */
    @Test
    public void testLapseton() {
        System.out.println("lapseton");
        Solmu instance = new Solmu(8);
        boolean expResult = true;
        boolean result = instance.lapseton();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Solmu.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Solmu instance = new Solmu(2);
        String expResult = "2{null,null}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getArvo method, of class Solmu.
     */
    @Test
    public void testGetArvo() {
        System.out.println("getArvo");
        Solmu instance = new Solmu(120);
        int expResult = 120;
        int result = instance.getArvo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVasen method, of class Solmu.
     */
    @Test
    public void testGetVasen() {
        System.out.println("getVasen");
        Solmu instance = new Solmu(10);
        Solmu s = new Solmu(4);
        instance.setVasen(s);
        Solmu expResult = s;
        Solmu result = instance.getVasen();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOikea method, of class Solmu.
     */
    @Test
    public void testGetOikea() {
        System.out.println("getOikea");
        Solmu instance = new Solmu(8);
        Solmu s = new Solmu(2);
        instance.setOikea(s);
        Solmu expResult = s;
        Solmu result = instance.getOikea();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVanhem method, of class Solmu.
     */
    @Test
    public void testSetVanhem() {
        System.out.println("setVanhem");
        Solmu a = new Solmu(4);
        Solmu instance = new Solmu(1);
        instance.setVanhem(a);
        Solmu expResult = a;
        Solmu result = instance.getVanhem();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVanhem method, of class Solmu.
     */
    @Test
    public void testGetVanhem() {
        System.out.println("getVanhem");
        Solmu a = new Solmu(8);
        Solmu instance = new Solmu(10);
        instance.setVanhem(a);
        Solmu expResult = a;
        Solmu result = instance.getVanhem();
        assertEquals(expResult, result);
    }

    /**
     * Test of setArvo method, of class Solmu.
     */
    @Test
    public void testSetArvo() {
        System.out.println("setArvo");
        int arvo = 14;
        Solmu instance = new Solmu(16);
        instance.setArvo(arvo);
        assertEquals(14, instance.getArvo());
    }

    /**
     * Test of setKorkeus method, of class Solmu.
     */
    @Test
    public void testSetKorkeus() {
        System.out.println("setKorkeus");
        int h = 5;
        Solmu instance = new Solmu(6);
        instance.setKorkeus(h);
        assertEquals(5, instance.getKorkeus());
    }

    /**
     * Test of getKorkeus method, of class Solmu.
     */
    @Test
    public void testGetKorkeus() {
        System.out.println("getKorkeus");
        Solmu instance = new Solmu(10);
        instance.setKorkeus(3);
        int expResult = 3;
        int result = instance.getKorkeus();
        assertEquals(expResult, result);
    }
}