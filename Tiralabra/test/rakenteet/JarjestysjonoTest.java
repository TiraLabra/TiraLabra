/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rakenteet;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author maef
 */
public class JarjestysjonoTest {
    
    private Jarjestysjono<Integer> jono;
    
    public JarjestysjonoTest() {
    }
    
    @Before
    public void setUp() {
        jono = new Jarjestysjono();
        jono.add(1);
        jono.add(5);
        jono.add(2);
        jono.add(7);
    }
    
     /**
     * Test of toString method, of class Jarjestysjono.
     */
    @Test
    public void testToStringJaJarjesta() {
        assertEquals("[7, 5, 2, 1]", jono.toString());
    }
    
     /**
     * Test of poll method, of class Jarjestysjono.
     */
    @Test
    public void testPoll() {
        int apu = jono.poll();
        assertEquals(1, apu);
    }
    
    /**
     * Test of add method, of class Jarjestysjono.
     */
    @Test
    public void testAdd() {
        jono.add(8);
        
        assertEquals("[8, 7, 5, 2, 1]", jono.toString());

    }

    /**
     * Test of peek method, of class Jarjestysjono.
     */
    @Test
    public void testPeek() {
        int apu = jono.peek();
        
        assertEquals(1, apu);
    }

    /**
     * Test of clear method, of class Jarjestysjono.
     */
    @Test
    public void Tyhjentaako() {
        jono.clear();
        assertEquals(null, jono.peek());
    }
    
    /*
     * Testaa toimiiko isEmpty-metodi oikein.
     */
    @Test
    public void onkoTyhja() {
        assertEquals(false, jono.isEmpty());
        
        jono.clear();
        
        assertEquals(true, jono.isEmpty());
    }
    
    /*
     * Testaa näkyykö koko oikein.
     */
    @Test
    public void onkoKokoOikein(){
        assertEquals(4, jono.size());
    }
    
    /*
     * Testaa, palauttaako contains-metodi oikean boolean-arvon.
     */
    @Test
    public void toimiikoContains() {
        assertEquals(true, jono.contains(5));
        assertEquals(false, jono.contains(26));
    }

}
