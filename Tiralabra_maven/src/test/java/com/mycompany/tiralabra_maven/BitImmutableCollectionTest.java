package com.mycompany.tiralabra_maven;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class BitImmutableCollectionTest {

    private static final int MANY_TEST_COUNT = 100;
    private BitImmutableCollection bits;

    @Before
    public void setUp() {
        bits = new BitImmutableCollection();
    }

    @Test
    public void testCollectionEmptyAtStart() {
        assertEquals(0, bits.size());
    }

    @Test
    public void testAddImmutable() {
        bits.add(true);
        assertEquals(0, bits.size());
    }

    @Test
    public void testAddReturnCorrect() {
        BitImmutableCollection newBits = bits.add(true);
        assertEquals(1, newBits.size());
    }

    @Test
    public void testAddCorrectTrue() {
        BitImmutableCollection newBits = bits.add(true);
        assertTrue(newBits.at(0));
    }

    @Test
    public void testAddCorrectFalse() {
        BitImmutableCollection newBits = bits.add(false);
        assertFalse(newBits.at(0));
    }

    @Test
    public void testAddMany() {
        BitImmutableCollection newBits = bits;
        for (int i = 0; i < MANY_TEST_COUNT; i++) {
            newBits = newBits.add(false);
        }
        assertEquals(MANY_TEST_COUNT, newBits.size());
    }

    @Test
    public void testAddManyBitsCorrect() {
        BitImmutableCollection newBits = bits;
        Random rand = new Random();
        boolean[] bools = new boolean[MANY_TEST_COUNT];
        for (int i = 0; i < MANY_TEST_COUNT; i++) {
            boolean value = rand.nextBoolean();
            newBits = newBits.add(value);
            bools[i] = value;
        }
        boolean valuesAreCorrect = true;
        for (int i = 0; i < MANY_TEST_COUNT; i++) {
            if (bools[i] != newBits.at(i)) {
                valuesAreCorrect = false;
                break;
            }
        }
        assertTrue(valuesAreCorrect);
    }
}
