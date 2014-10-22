/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algoritmi;

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
public class KekoTest {
    
    static Solmu a;
    static Solmu b;
    static Solmu c;
    static Solmu d;
    static Solmu e;
    
    Keko testikeko; 
    
    
    public KekoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        a=new Solmu(1, 1, 1, 0);
        b=new Solmu(1, 1, 2, 0);
        c=new Solmu(1, 1, 3, 0);      
        d=new Solmu(1, 1, 4, 0);      
        e=new Solmu(1, 1, 5, 0);      
                
             
                
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        testikeko=new Keko(20); 
    }
    
    @After
    public void tearDown() {
    }
    

    /**
     * Test of lisää method, of class Keko.
     */
    @Test
    public void testLisää() {

        testikeko.lisää(b);
        testikeko.lisää(c);
        testikeko.lisää(a);
        
        assertEquals(testikeko.haeEka(), a);
        
    }

    /**
     * Test of Poista method, of class Keko.
     */
    @Test
    public void testPoista() {
 
        testikeko.lisää(b);
        testikeko.lisää(c);
        testikeko.lisää(a);
        
        assertEquals(testikeko.Poista(), a);
        assertEquals(testikeko.Poista(), b);
        assertEquals(testikeko.Poista(), c);
        assertEquals(testikeko.Poista(), null);
        
    }
    
}
