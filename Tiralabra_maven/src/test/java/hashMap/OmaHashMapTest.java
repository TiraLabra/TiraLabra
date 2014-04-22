package hashMap;

import junit.framework.TestCase;
import linkitettyLista.Lista;

/**
 *
 * @author Ilkimys
 */
public class OmaHashMapTest extends TestCase {

    private OmaHashMap<Integer, Object> h;
    private Object d;

    @Override
    protected void setUp() throws Exception {
        h = new OmaHashMap<Integer, Object>();
        d = new Object();
    }

    /**
     * Test of get method, of class OmaHashMap.
     */
    public void testGet() {
        taytaMap();
        for (int i = 0; i < 500; i++) {
            assertEquals(d, h.get(i));
        }

    }

    /**
     * Test of put method, of class OmaHashMap.
     */
    public void testPut() {
        taytaMap();
        assertEquals(500, h.getKoko());
    }

    /**
     * Test of remove method, of class OmaHashMap.
     */
    public void testRemove() {
        taytaMap();
        for (int i = 0; i < 500; i++) {
            h.remove(i);
        }
        assertEquals(0, h.getKoko());
    }

    /**
     * Test of getValues method, of class OmaHashMap.
     */
    public void testGetValues() {
        Lista l = h.getDatas();
        assertEquals(0, l.getListanKoko());
        taytaMap();
        l = h.getDatas();
        assertEquals(500, l.getListanKoko());
    }

    /**
     * Test of tyhjenna method, of class OmaHashMap.
     */
    public void testTyhjenna() {
        taytaMap();
        assertEquals(500, h.getKoko());
        h.tyhjenna();
        assertEquals(0, h.getKoko());
    }

    /**
     * Test of getKoko method, of class OmaHashMap.
     */
    public void testGetKoko() {
        for (int i = 0; i < 500; i++) {
            h.put(i, d);
            assertEquals(i+1, h.getKoko());
        }
        for (int i = 499; i >= 0; i--) {
            h.remove(i);
            assertEquals(i, h.getKoko());
        }
    }

    /**
     * Test of isEmpty method, of class OmaHashMap.
     */
    public void testIsEmpty() {
        assertTrue(h.isEmpty());
    }

    public void taytaMap() {
        for (int i = 0; i < 500; i++) {
            h.put(i, d);
        }
    }


    /**
     * Test of sisaltaaAvaimen method, of class OmaHashMap.
     */
    public void testSisaltaaAvaimen() {
        h.put(5, d);
        assertTrue(h.sisaltaaAvaimen(5));
        assertTrue(!h.sisaltaaAvaimen(4));
    }
}
