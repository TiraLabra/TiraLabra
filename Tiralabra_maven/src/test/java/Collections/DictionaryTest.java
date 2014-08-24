package Collections;

import java.util.Objects;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public final class DictionaryTest {

    private Dictionary<Integer, String> map;

    @Before
    public void setUp() {
        map = new Dictionary<>();
    }

    @Test
    public void testSizeEmpty() {
        assertEquals(0, map.size());
    }

    @Test
    public void testAdd() {
        final int k = 23;
        final String v = "SADFÄSÅZFÅSDÅFS";
        map.add(k, v);
        assertEquals(1, map.size());
    }

    @Test
    public void testContainsKey() {
        final Integer k = 23;
        final String v = "SADFÄSÅZFÅSDÅFS";
        map.add(k, v);
        assertTrue(map.containsKey(k));
    }

    @Test
    public void testContainsKeyMany() {
        Vector<Integer> keys = new Vector<>(Integer.class);
        Vector<Float> values = new Vector<>(Float.class);
        Dictionary<Integer, Float> map = new Dictionary<>();
        Random rand = new Random();
        final int iters = 1000;
        for (int i = 0; i < iters; i++) {
            float v = rand.nextFloat();
            int k = rand.nextInt();
            keys.add(k);
            values.add(v);
            map.add(k, v);
        }
        boolean allCorrect = true;
        for (int i = 0; i < iters; i++) {
            if (map.containsKey(keys.get(i))) {
                if (Objects.equals(map.get(keys.get(i)), values.get(i))) {
                    continue;
                }
            }
            allCorrect = false;
            break;
        }
        assertTrue("All members of map were not correct", allCorrect);
    }

    @Test
    public void testGet() {
        final int k = 23;
        final String v = "SADFÄSÅZFÅSDÅFS";
        map.add(k, v);
        assertEquals(v, map.get(k));
    }

    @Test
    public void testGetNull() {
        try {
            map.get(null);
            fail("Getting null from map");
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testGetKeyThatIsntInMap() {
        String value = map.get(1);
        assertNull(value);
    }

    @Test
    public void testAddNull() {
        try {
            map.add(null, "");
            fail("Adding null to map");
        } catch (NullPointerException ex) {
        }
    }
}
