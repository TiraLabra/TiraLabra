
package com.mycompany.tiralabra_maven.logiikka;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaskinTest {
    
    private static final Random ARPOJA = new Random();
    
    private Laskin      laskin;
    private String[]    syote;
    
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
        laskin = new Laskin();
    }
    
    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLaskeVirheellinenSyote1() {
        syote = new String[2];
        
        syote[0] = "3";
        syote[1] = "+";
        
        laskin.laske(syote);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testLaskeVirheellinenSyote2() {
        syote = new String[4];
        
        syote[0] = "3";
        syote[1] = "2";
        syote[2] = "5";
        syote[3] = "+";
        
        laskin.laske(syote);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testLaskeVirheellinenSyote3() {
        syote = new String[3];
        
        syote[0] = "3";
        syote[1] = "2";
        syote[2] = "^";
        
        laskin.laske(syote);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testLaskeVirheellinenSyote4() {
        syote = new String[3];
        
        syote[0] = "3";
        syote[1] = "2";
        syote[2] = "8";
        
        laskin.laske(syote);
    }
    
    @Test(expected = ArithmeticException.class)
    public void testAritmeettinenYlivuoto() {
        syote = new String[3];
        
        int n = 50000, m = 50000;
        
        syote[0] = String.valueOf(n);
        syote[1] = String.valueOf(m);
        syote[2] = "*";
        
        laskin.laske(syote);
    }
    
    @Test(expected = ArithmeticException.class)
    public void testNollallaJakaminen() {
        syote = new String[3];
        
        int n = 57,
                m = 0;
        
        syote[0] = String.valueOf(n);
        syote[1] = String.valueOf(m);
        syote[2] = "/";
        
        laskin.laske(syote);
    }
    
    @Test
    public void testSumma() {
        syote = new String[6];
        
        int n = ARPOJA.nextInt(Integer.MAX_VALUE / 2),
                m = ARPOJA.nextInt(Integer.MAX_VALUE / 2),
                summa = n + m;
        
        syote[0] = String.valueOf(n);
        syote[1] = String.valueOf(m);
        syote[2] = " ";
        syote[3] = "+";
        syote[4] = " ";
        syote[5] = " ";
        
        assertEquals(summa, laskin.laske(syote));
    }
    
    @Test
    public void testErotus() {
        syote = new String[3];
        
        int n = ARPOJA.nextInt(Integer.MAX_VALUE),
                m = ARPOJA.nextInt(Integer.MAX_VALUE),
                erotus = n - m;
        
        syote[0] = String.valueOf(n);
        syote[1] = String.valueOf(m);
        syote[2] = "-";
        
        assertEquals(erotus, laskin.laske(syote));
    }
    
    @Test
    public void testTulo() {
        syote = new String[3];
        
        int n = ARPOJA.nextInt(46340),
                m = ARPOJA.nextInt(46340),
                tulo = n * m;
        
        syote[0] = String.valueOf(n);
        syote[1] = String.valueOf(m);
        syote[2] = "*";
        
        assertEquals(tulo, laskin.laske(syote));
    }
    
    @Test
    public void testOsamaara() {
        syote = new String[3];
        
        int n = ARPOJA.nextInt(Integer.MAX_VALUE),
                m = ARPOJA.nextInt(Integer.MAX_VALUE - 1) + 1,
                osamaara = n / m;
        
        syote[0] = String.valueOf(n);
        syote[1] = String.valueOf(m);
        syote[2] = "/";
        
        assertEquals(osamaara, laskin.laske(syote));
    }
}
