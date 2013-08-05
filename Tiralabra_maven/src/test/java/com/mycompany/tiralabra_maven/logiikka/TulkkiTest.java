
package com.mycompany.tiralabra_maven.logiikka;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TulkkiTest {
    
    public TulkkiTest() {
    }
    
    private static final Random ARPOJA = new Random();
    
    private static Tulkki   tulkki;
    private Queue<String>   odotusarvo, saatuArvo;
    
    @BeforeClass
    public static void setUpClass() {
        tulkki = new Tulkki();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        odotusarvo  = new ArrayDeque<String>();
    }
    
    @After
    public void tearDown() {
    }
    
        
    @Test(expected = IllegalArgumentException.class)
    public void testEpakelpoKaava1() {
        tulkki.tulkitseMerkkijono("7 ^ 3 * 5");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testEpakelpoKaava2() {
        tulkki.tulkitseMerkkijono("(7 + 3)) + 5");
    }

    @Test
    public void testKelvollinenKaava1() {
        odotusarvo.add("7");
        odotusarvo.add("3");
        odotusarvo.add("+");
        odotusarvo.add("5");
        odotusarvo.add("*");
        
        saatuArvo = tulkki.tulkitseMerkkijono("(7 + 3) * 5");
        
        vertaileJonoja();
    }
    
    @Test
    public void testKelvollinenKaava2() {
        odotusarvo.add("7");
        odotusarvo.add("3");
        odotusarvo.add("5");
        odotusarvo.add("*");
        odotusarvo.add("+");
        
        saatuArvo = tulkki.tulkitseMerkkijono("7 + 3 * 5");
        
        vertaileJonoja();
    }
    
//    @Test
//    public void testKelvollinenKaava3() {
//        odotusarvo.add("7");
//        odotusarvo.add("3");
//        odotusarvo.add("/");
//        odotusarvo.add("5");
//        odotusarvo.add("*");
//        
//        saatuArvo = tulkki.tulkitseMerkkijono("7 / 3 * 5");
//        
//        vertaileJonoja();
//    }
    
    @Test
    public void testKelvollinenKaava4() {
        // Jostain syystä jälkimmäisestä luvusta tulee kaavaan mukaan vain
        // ensimmäinen merkki.
        int n = ARPOJA.nextInt(Integer.MAX_VALUE / 2),
//                m = ARPOJA.nextInt(Integer.MAX_VALUE / 2);
                m = 9;
        String a = String.valueOf(n), b = String.valueOf(m);
        
        odotusarvo.add(a);
        odotusarvo.add(b);
        odotusarvo.add("+");
        
        saatuArvo = tulkki.tulkitseMerkkijono(a + " + " + b);
        
        vertaileJonoja();
    }
    
    private void vertaileJonoja() {
        String a, b;
        while (!odotusarvo.isEmpty()) {
            a = odotusarvo.poll();
            b = saatuArvo.poll();
            if (!a.equals(b)) {
                fail("Tulkki palautti virheellisen RPN-kaavan!");
            }
        }
    }
}