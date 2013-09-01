
package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.tietorakenteet.Jono;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LaskinTest {
    
    private static final Random ARPOJA = new Random();
    
    private Laskin          laskin;
    private Jono<String>   syote;
    
    @Before
    public void setUp() {
        laskin  = new Laskin();
        syote   = new Jono<>();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testLaskeVirheellinenSyote1() {        
        syote.lisaa("3");
        syote.lisaa("+");
        
        laskin.laske(syote);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testLaskeVirheellinenSyote2() {        
        syote.lisaa("3");
        syote.lisaa("2");
        syote.lisaa("5");
        syote.lisaa("+");
        
        laskin.laske(syote);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testLaskeVirheellinenSyote3() {        
        syote.lisaa("3");
        syote.lisaa("2");
        syote.lisaa("^");
        
        laskin.laske(syote);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testLaskeVirheellinenSyote4() {        
        syote.lisaa("3");
        syote.lisaa("2");
        syote.lisaa("8");
        
        laskin.laske(syote);
    }
    
    @Test(expected = ArithmeticException.class)
    public void testAritmeettinenYlivuoto() {
        syote.lisaa("50000");
        syote.lisaa("50000");
        syote.lisaa("*");
        
        laskin.laske(syote);
    }
    
    @Test(expected = ArithmeticException.class)
    public void testNollallaJakaminen() {
        syote.lisaa("57");
        syote.lisaa("0");
        syote.lisaa("/");
        
        laskin.laske(syote);
    }
    
    @Test
    public void testSumma() {        
        int n = ARPOJA.nextInt(Integer.MAX_VALUE / 2),
                m = ARPOJA.nextInt(Integer.MAX_VALUE / 2),
                summa = n + m;
        
        syote.lisaa(String.valueOf(n));
        syote.lisaa(String.valueOf(m));
        syote.lisaa(" ");
        syote.lisaa("+");
        syote.lisaa(" ");
        syote.lisaa(" ");
        
        assertEquals(summa, laskin.laske(syote));
    }
    
    @Test
    public void testErotus() {        
        int n = ARPOJA.nextInt(Integer.MAX_VALUE),
                m = ARPOJA.nextInt(Integer.MAX_VALUE),
                erotus = n - m;
        
        syote.lisaa(String.valueOf(n));
        syote.lisaa(String.valueOf(m));
        syote.lisaa("-");
        
        assertEquals(erotus, laskin.laske(syote));
    }
    
    @Test
    public void testTulo() {
        int n = ARPOJA.nextInt(46340),
                m = ARPOJA.nextInt(46340),
                tulo = n * m;
        
        syote.lisaa(String.valueOf(n));
        syote.lisaa(String.valueOf(m));
        syote.lisaa("*");
        
        assertEquals(tulo, laskin.laske(syote));
    }
    
    @Test
    public void testOsamaara() {
        int n = ARPOJA.nextInt(Integer.MAX_VALUE),
                m = ARPOJA.nextInt(Integer.MAX_VALUE - 1) + 1,
                osamaara = n / m;
        
        syote.lisaa(String.valueOf(n));
        syote.lisaa(String.valueOf(m));
        syote.lisaa("/");
        
        assertEquals(osamaara, laskin.laske(syote));
    }
    
    @Test
    public void testJakojaannos() {
        int n = ARPOJA.nextInt(Integer.MAX_VALUE),
                m = ARPOJA.nextInt(Integer.MAX_VALUE - 1) + 1,
                jakojaannos = n % m;
        
        syote.lisaa(String.valueOf(n));
        syote.lisaa(String.valueOf(m));
        syote.lisaa("%");
        
        assertEquals(jakojaannos, laskin.laske(syote));
    }
}
