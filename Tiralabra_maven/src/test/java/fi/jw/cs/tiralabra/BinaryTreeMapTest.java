package fi.jw.cs.tiralabra;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-13
 */
public class BinaryTreeMapTest {
    @Test
    public void testKeySet() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {
        BinaryTreeMap map = new BinaryTreeMap();
        BinaryTreeMap map2 = new BinaryTreeMap();


        String foo = "a";
        map.put(foo, foo);
        map2.put(foo, foo);

        assertEquals(map, map2);

    }
}
