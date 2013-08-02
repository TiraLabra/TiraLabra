package fi.jw.cs.tiralabra;

import junit.framework.TestCase;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-02
 */
public class SymbolTest extends TestCase {

    public void testCompareTo() throws Exception {
        Symbol s1 = new Symbol('a', 0);
        Symbol s2 = new Symbol('a', 1);

        assertTrue(s1.compareTo(s2) < 0);
        assertTrue(s1.compareTo(s1) == 0);
        assertTrue(s2.compareTo(s1) > 0);
    }

    public void testIncreaseWeight() throws Exception {
        Symbol s1 = new Symbol('a', 0);
        s1.increaseWeight();
        assertEquals(s1.getWeight(), 1);
    }
}
