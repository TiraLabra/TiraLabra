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

    @Test
    public void testArraySwitch() {
        final int first = 255;
        final int second = 56345;
        vector.add(first);
        vector.add(second);
        vector.switchAtIndex(0, 1);
        boolean correctOrder = vector.get(0) == second && vector.get(1) == first;
        assertTrue("Vector switch is incorrect", correctOrder);
    }

    @Test
    public void testSetAt() {
        for (int i = 0; i < 100; i++) {
            vector.add(i);
        }
        final int index = 24;
        final Integer toSet = 549753;
        vector.setAtt(index, toSet);
        assertEquals(toSet, vector.get(index));
    }

    @Test
    public void testRemoveLastSizeCorrect() {
        vector.add(23);
        vector.removeLast();
        assertEquals(0, vector.size());
    }

    private int removeAndRealSize() {
        final int toAdd = 10;
        final int toRemove = 3;
        for (int i = 0; i < toAdd; i++) {
            vector.add(i);
        }
        for (int i = 0; i < toRemove; i++) {
            vector.removeLast();
        }
        vector.add(Integer.MAX_VALUE);
        return toAdd - toRemove + 1;
    }

    @Test
    public void testRemoveAndRealSize() {
        assertEquals(removeAndRealSize(), vector.size());
    }

    @Test
    public void testRemoveAndRealMember() {
        removeAndRealSize();
        final Integer expected = Integer.MAX_VALUE;
        assertEquals(expected, vector.get(vector.size() - 1));
    }

    @Test
    public void testRemoveArrayOutOfIndex() {
        final int toAdd = 10;
        for (int i = 0; i < toAdd; i++) {
            vector.add(i);
        }
        vector.removeLast();
        try {
            vector.get(toAdd - 1);
            fail("Real size is ignored in vector");
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
    }

    @Test
    public void testToArray() {
        Vector<String> v = new Vector<>(String.class);
        v.add("ASDSWERWUOU(");
        v.add("r46666666666666(");
        v.add("*GHF^Ä&*N(");
        v.add("#`ÄFD G*DÅV^RÅPLZ^D(");
        String[] arrayForm = v.toArray();
        for (int i = 0; i < v.size(); i++) {
            String arrayString = arrayForm[i];
            String vectorString = v.get(i);
            if (!arrayString.equals(vectorString)) {
                fail("Expected " + vectorString + " got " + arrayString);
            }
        }
    }
}
