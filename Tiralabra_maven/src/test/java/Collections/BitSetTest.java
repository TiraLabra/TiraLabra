package Collections;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class BitSetTest {

    private BitSet bits;

    @Before
    public void setUp() {
        bits = new BitSet();
    }

    @Test
    public void testAddTrue() {
        bits.add(true);
        assertTrue(bits.get(0));
    }

    @Test
    public void testAddFalse() {
        bits.add(false);
        assertFalse(bits.get(0));
    }

    @Test
    public void testGetMany() {
        Vector<Boolean> bools = new Vector<>(Boolean.class);
        Random rand = new Random();
        final int iters = 150;
        for (int i = 0; i < iters; i++) {
            boolean bool = rand.nextBoolean();
            bools.add(bool);
            bits.add(bool);
        }
        boolean allCorrect = true;
        for (int i = 0; i < iters; i++) {
            if (bools.get(i) != bits.get(i)) {
                allCorrect = false;
                break;
            }
        }
        assertTrue(allCorrect);
    }

    @Test
    public void testLenghtEmpty() {
        assertEquals(0, bits.length());
    }

    @Test
    public void testLenghtMany() {
        final int count = 12;
        for (int i = 0; i < count; i++) {
            bits.add(true);
        }
        assertEquals(count, bits.length());
    }

    @Test
    public void testSet() {
        Vector<Boolean> bools = new Vector<>(Boolean.class);
        Random rand = new Random();
        final int iters = 150;
        for (int i = 0; i < iters; i++) {
            bools.add(rand.nextBoolean());
            bits.add(true);
        }
        for (int i = 0; i < iters; i++) {
            bits.set(i, bools.get(i));
        }
        boolean allCorrect = true;
        for (int i = 0; i < iters; i++) {
            if (bools.get(i) != bits.get(i)) {
                allCorrect = false;
                break;
            }
        }
        assertTrue(allCorrect);
    }

    private void testArrayWith(int byteCount, int bitOffSet) {
        final int max = byteCount * Byte.SIZE + bitOffSet;
        final int expected = byteCount + (bitOffSet > 0 ? 1 : 0);
        for (int i = 0; i < max; i++) {
            bits.add(true);
        }
        assertEquals(expected, bits.toByteArray().length);
    }

    @Test
    public void testToByteArraySizeCorrectZero() {
        testArrayWith(10, 0);
    }

    @Test
    public void testToByteArraySizeCorrectOne() {
        testArrayWith(10, 1);
    }

    @Test
    public void testToByteArraySizeCorrectTwo() {
        testArrayWith(10, 2);
    }

    @Test
    public void testToByteArraySizeCorrectThree() {
        testArrayWith(10, 3);
    }

    @Test
    public void testToByteArraySizeCorrectFour() {
        testArrayWith(10, 4);
    }

    @Test
    public void testToByteArraySizeCorrectFive() {
        testArrayWith(10, 5);
    }

    @Test
    public void testToByteArraySizeCorrectSix() {
        testArrayWith(10, 6);
    }

    @Test
    public void testToByteArraySizeCorrectSeven() {
        testArrayWith(10, 7);
    }

    @Test
    public void testToByteArraySizeCorrectEight() {
        testArrayWith(10, 8);
    }

    @Test
    public void testToByteArrayHoldingCorrect() {
        final byte expected = (byte) 0b00111111;
        bits.add(true);
        bits.add(true);
        bits.add(true);
        bits.add(true);
        bits.add(true);
        bits.add(true);
        bits.add(false);
        bits.add(false);
        assertEquals(expected, bits.toByteArray()[0]);
    }

    @Test
    public void testToByteArrayHoldingCorrectTakeTwo() {
        final byte expected = (byte) 0b00100011;
        bits.add(true);
        bits.add(true);
        bits.add(false);
        bits.add(false);
        bits.add(false);
        bits.add(true);
        bits.add(false);
        bits.add(false);
        assertEquals(expected, bits.toByteArray()[0]);
    }

    @Test
    public void testToByteArrayHoldingCorrectTakeThree() {
        final byte expected = (byte) 0b01101001;
        bits.add(true);
        bits.add(false);
        bits.add(false);
        bits.add(true);
        bits.add(false);
        bits.add(true);
        bits.add(true);
        bits.add(false);
        assertEquals(expected, bits.toByteArray()[0]);
    }

    @Test
    public void testToByteArrayHoldingCorrectOverByte() {
        final byte expected = (byte) 0b00000001;
        bits.add(true);
        bits.add(false);
        bits.add(false);
        bits.add(true);
        bits.add(false);
        bits.add(true);
        bits.add(true);
        bits.add(false);
        bits.add(true);
        assertEquals(expected, bits.toByteArray()[1]);
    }

    @Test
    public void testArrayCreationFromBytes() {
        Random rand = new Random();
        final int iters = 135;
        for (int i = 0; i < iters; i++) {
            bits.add(rand.nextBoolean());
        }
        BitSet fromBytes = new BitSet(bits.toByteArray(), bits.length());
        boolean allCorrect = true;
        for (int i = 0; i < iters; i++) {
            if (bits.get(i) != fromBytes.get(i)) {
                allCorrect = false;
            }
        }
        assertTrue("Bits were from when created from array", allCorrect);
    }
}
