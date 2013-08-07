
package com.mycompany.tiralabra_maven.logiikka;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaskinTest {
    
    private static final Random ARPOJA = new Random();
    
    private Laskin          laskin;
    private Queue<String>   syote;
    
    public LaskinTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        laskin  = new Laskin();
        syote   = new ArrayDeque<String>();
    }
    
    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLaskeVirheellinenSyote1() {        
        syote.add("3");
        syote.add("+");
        
        laskin.laske(syote);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testLaskeVirheellinenSyote2() {        
        syote.add("3");
        syote.add("2");
        syote.add("5");
        syote.add("+");
        
        laskin.laske(syote);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testLaskeVirheellinenSyote3() {        
        syote.add("3");
        syote.add("2");
        syote.add("^");
        
        laskin.laske(syote);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testLaskeVirheellinenSyote4() {        
        syote.add("3");
        syote.add("2");
        syote.add("8");
        
        laskin.laske(syote);
    }
    
    @Test(expected = ArithmeticException.class)
    public void testAritmeettinenYlivuoto() {
        syote.add("50000");
        syote.add("50000");
        syote.add("*");
        
        laskin.laske(syote);
    }
    
    @Test(expected = ArithmeticException.class)
    public void testNollallaJakaminen() {
        syote.add("57");
        syote.add("0");
        syote.add("/");
        
        laskin.laske(syote);
    }
    
    @Test
    public void testSumma() {        
        int n = ARPOJA.nextInt(Integer.MAX_VALUE / 2),
                m = ARPOJA.nextInt(Integer.MAX_VALUE / 2),
                summa = n + m;
        
        syote.add(String.valueOf(n));
        syote.add(String.valueOf(m));
        syote.add(" ");
        syote.add("+");
        syote.add(" ");
        syote.add(" ");
        
        assertEquals(summa, laskin.laske(syote));
    }
    
    @Test
    public void testErotus() {        
        int n = ARPOJA.nextInt(Integer.MAX_VALUE),
                m = ARPOJA.nextInt(Integer.MAX_VALUE),
                erotus = n - m;
        
        syote.add(String.valueOf(n));
        syote.add(String.valueOf(m));
        syote.add("-");
        
        assertEquals(erotus, laskin.laske(syote));
    }
    
    @Test
    public void testTulo() {
        int n = ARPOJA.nextInt(46340),
                m = ARPOJA.nextInt(46340),
                tulo = n * m;
        
        syote.add(String.valueOf(n));
        syote.add(String.valueOf(m));
        syote.add("*");
        
        assertEquals(tulo, laskin.laske(syote));
    }
    
    @Test
    public void testOsamaara() {
        int n = ARPOJA.nextInt(Integer.MAX_VALUE),
                m = ARPOJA.nextInt(Integer.MAX_VALUE - 1) + 1,
                osamaara = n / m;
        
        syote.add(String.valueOf(n));
        syote.add(String.valueOf(m));
        syote.add("/");
        
        assertEquals(osamaara, laskin.laske(syote));
    }
    
    @Test
    public void testJakojaannos() {
        int n = ARPOJA.nextInt(Integer.MAX_VALUE),
                m = ARPOJA.nextInt(Integer.MAX_VALUE - 1) + 1,
                jakojaannos = n % m;
        
        syote.add(String.valueOf(n));
        syote.add(String.valueOf(m));
        syote.add("%");
        
        assertEquals(jakojaannos, laskin.laske(syote));
    }
}
