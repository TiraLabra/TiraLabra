/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import haku.Reitti;
import java.util.Comparator;
import java.util.PriorityQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author E
 */
public class PrioriteettiJonoListallaTest {
    
    private static DynaaminenLista<Reitti> reitit;
    private static int n;
    private static Comparator<Reitti> comparator;
    
    private PriorityQueue<Reitti> pq;
    
    
    @BeforeClass
    public static void setUpClass() {
        n = 100;
        reitit = new DynaaminenLista();
        for ( int i = 0; i<n; i++ ) {
            Reitti r = new Reitti();
            r.setAika(i);
            r.setMatka(0.5*i);
            r.setKustannus( r.getAika()+r.getMatka() );
            double d = (n-i)*1;
            r.setArvioituKustannus(d);
            r.setKuljettuKaari(null);
            r.setPrevious(null);
            r.setSolmu(null);
            
            reitit.add(r);
        }
        
        int   aika         = 40; 
        final int tarkkuus = 1;
        comparator = new Comparator<Reitti>() {

            public int compare(Reitti t1, Reitti t2) {
                return (int) ( tarkkuus*(t1.getArvioituKustannus() + t1.getKustannus() - t2.getArvioituKustannus() - t2.getKustannus()));
            }
        };
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
     * Test of add method, of class PrioriteettiJonoListalla.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Reitti e = reitit.get(0);
        PrioriteettiJonoListalla<Reitti> instance = new PrioriteettiJonoListalla( comparator );

        instance.add(e);
        
        Reitti expResult = e;
        Reitti result    = instance.peek();
        assertEquals(expResult, result);
        
        
        instance.add(reitit.get(1));
        
        expResult = e;
        result    = instance.peek();
        assertEquals(expResult, result);    

    }

    /**
     * Test of poll method, of class PrioriteettiJonoListalla.
     */
    @Test
    public void testPoll() {
        System.out.println("poll");
        PrioriteettiJonoListalla<Reitti> instance = new PrioriteettiJonoListalla( comparator );
        pq = new PriorityQueue( comparator );
        for ( Reitti r : reitit ) {
            instance.add(r);
            pq.add(r);
        }
        
        int i = 0;
        while ( !instance.isEmpty() && !pq.isEmpty() && i<n ) {
            Reitti result = instance.poll();
            Reitti result_pq = pq.poll();
            Reitti expResult = reitit.get(i);
            i++;
            assertEquals( result, result_pq );
            assertEquals( result, expResult );
        } 
        
       
    }

    /**
     * Test of isEmpty method, of class PrioriteettiJonoListalla.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        PrioriteettiJonoListalla<Reitti> instance = new PrioriteettiJonoListalla();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        
        instance.add(reitit.get(4));
        expResult = false;
        result = instance.isEmpty();
        assertEquals(expResult, result);
        
        instance.poll();
        expResult = true;
        result = instance.isEmpty();
        assertEquals(expResult, result);        

    }

    /**
     * Test of size method, of class PrioriteettiJonoListalla.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        PrioriteettiJonoListalla instance = new PrioriteettiJonoListalla();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        
        for ( Reitti r : reitit ) {
            instance.add(r);
        } 
        expResult = n;
        result = instance.size();
        assertEquals(expResult, result);

        instance.poll();
        instance.peek();
        
        expResult = n-1;
        result = instance.size();
        assertEquals(expResult, result);            
        
        while ( !instance.isEmpty() ) {
            instance.poll();
        }
        expResult = 0;
        result = instance.size();
        assertEquals(expResult, result);       
    }

    
}
