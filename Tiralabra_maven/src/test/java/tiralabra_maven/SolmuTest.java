package tiralabra_maven;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolmuTest {

    public SolmuTest() {
    }

    @Test
    public void testSetVasen() {
        System.out.println("setVasen");
        Solmu s1 = new Solmu(3);
        Solmu instance = new Solmu(5);
        instance.setVasen(s1);
        assertEquals("5{3,[]}", instance.toString());
    }

    @Test
    public void testSetOikea() {
        System.out.println("setOikea");
        Solmu s1 = new Solmu(7);
        Solmu instance = new Solmu(2);
        instance.setOikea(s1);
        assertEquals("2{[],7}", instance.toString());
    }

    @Test
    public void testLapseton() {
        System.out.println("lapseton");
        Solmu instance = new Solmu(8);
        boolean expResult = true;
        boolean result = instance.lapseton();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testToString() {
        System.out.println("toString");
        Solmu instance = new Solmu(2);
        String expResult = "2";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetArvo() {
        System.out.println("getArvo");
        Solmu instance = new Solmu(120);
        int expResult = 120;
        int result = instance.getArvo();
        assertEquals(expResult, result);
    }

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

    @Test
    public void testSetArvo() {
        System.out.println("setArvo");
        int arvo = 14;
        Solmu instance = new Solmu(16);
        instance.setArvo(arvo);
        assertEquals(14, instance.getArvo());
    }

    @Test
    public void testSetKorkeus() {
        System.out.println("setKorkeus");
        int h = 5;
        Solmu instance = new Solmu(6);
        instance.setKorkeus(h);
        assertEquals(5, instance.getKorkeus());
    }

    @Test
    public void testGetKorkeus() {
        System.out.println("getKorkeus");
        Solmu instance = new Solmu(10);
        instance.setKorkeus(3);
        int expResult = 3;
        int result = instance.getKorkeus();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetVari() {
        System.out.println("setVari");
        Boolean b = true;
        Solmu instance = new Solmu(3);
        instance.setVari(b);
        assertEquals(b,instance.getVari());
    }

    @Test
    public void testGetVari() {
        System.out.println("getVari");
        Boolean b = true;
        Solmu instance = new Solmu(7);
        instance.setVari(b);
        assertEquals(b,instance.getVari());
    }
}