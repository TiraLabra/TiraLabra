package Collections;

import java.util.Objects;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class VectorTest {

    private Vector<Integer> vector;

    @Before
    public void setUp() {
        vector = new Vector<>(Integer.class);
    }

    @Test
    public void testSizeWhenEmpty() {
        assertEquals(0, vector.size());
    }

    @Test
    public void testAdd() {
        vector.add(23);
        assertEquals(1, vector.size());
    }

    @Test
    public void testMany() {
        final int times = 1024;
        for (int i = 0; i < 1024; i++) {
            vector.add(i);
        }
        assertEquals(times, vector.size());
    }

    @Test
    public void addAndGet() {
        final Integer number = 254234;
        vector.add(number);
        assertEquals(number, vector.get(0));
    }

    @Test
    public void addHighNumberAndGet() {
        final Integer number = 4434;
        for (int i = 0; i < 100; i++) {
            if (i == 50) {
                vector.add(number);
            }
            vector.add(i);
        }
        assertEquals(number, vector.get(50));
    }

    @Test
    public void testAddNullError() {
        Vector<String> stringVector = new Vector<>(String.class);
        try {
            stringVector.add(null);
            fail("Added null to vector");
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testCreateCopyArray() {
        for (int i = 0; i < 100; i++) {
            vector.add(i);
        }
        Vector<Integer> copy = new Vector<>(vector);
        for (int i = 0; i < 100; i++) {
            if (!Objects.equals(vector.get(i), copy.get(i))) {
                fail("Array copy failed");
            }
        }
        assertTrue(true);
    }
}
