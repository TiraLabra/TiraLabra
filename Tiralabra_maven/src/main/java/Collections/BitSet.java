package Collections;

/**
 * A mutable collection of bits. Can be converted to a byte array.
 */
public final class BitSet {

    private final Vector<Boolean> bools;
    private int count;

    /**
     * Create a new BitSet from a byte array.
     *
     * @param byteForm The byte array to create from.
     * @param bitCount The amount of bits the array has.
     */
    public BitSet(final byte[] byteForm, final int bitCount) {
        this();
        final int extraBits = bitCount % 8;
        final int lastIter = byteForm.length - 1;
        for (int i = 0; i <= lastIter; i++) {
            final byte current = byteForm[i];
            if (i == lastIter) {
                addBools(current, extraBits);
            } else {
                addBools(current, 8);
            }
        }
    }

    private void addBools(final byte from, final int endAt) {
        for (int i = 0; i < endAt; i++) {
            add(getBoolFromByte(from, i));
        }
    }

    /**
     * Create a new, empty BitSet.
     */
    public BitSet() {
        this.bools = new Vector<>(Boolean.class);
        this.count = 0;
    }

    /**
     * Add a boolean value to the set.
     *
     * @param bool Boolean to add.
     */
    public void add(final boolean bool) {
        bools.add(bool);
        count++;
    }

    /**
     * Get bit value at boolean.
     *
     * @param index The index where to get.
     * @return The value at the index.
     */
    public boolean get(final int index) {
        return bools.get(index);
    }

    /**
     * The amount of bits in the set.
     *
     * @return The amount of bits in the set.
     */
    public int length() {
        return count;
    }

    /**
     * Set a bit at specified index to value.
     *
     * @param index The bit index.
     * @param bool The new value.
     */
    public void set(final int index, final boolean bool) {
        bools.setAtt(index, bool);
    }

    /**
     * Copy the BitSet to a byte array, with each byte holding 8 boolean values.
     *
     * @return The byte array.
     */
    public byte[] toByteArray() {
        final Vector<Byte> bytes = new Vector<>(Byte.class);
        int currentByteIndex = 0;
        int i = 0;
        do {
            final boolean modIsZero = i % 8 == 0;
            final byte current = modIsZero ? 0 : bytes.get(currentByteIndex);
            final byte toSet = setIndexTo(current, i % 8, bools.get(i));
            if (modIsZero) {
                bytes.add(toSet);
            } else {
                bytes.setAtt(currentByteIndex, toSet);
            }
            i++;
            if (i % 8 == 0) {
                currentByteIndex++;
            }
        } while (i < bools.size());

        //Primitives...
        byte[] primitives = new byte[bytes.size()];
        for (int j = 0; j < bytes.size(); j++) {
            primitives[j] = bytes.get(j);
        }
        return primitives;
    }

    /**
     * Set position n in the byte to the value.
     *
     * @param toSet The current byte.
     * @param index Position n.
     * @param value The value to set the position, 1 or 0.
     * @return The byte where the bit is set.
     */
    private static byte setIndexTo(final int toSet, final int index, final boolean value) {
        final int mask = mask(index);
        final int maskedByte = value ? toSet | mask : toSet;
        return (byte) maskedByte;
    }

    /**
     * Get the boolean representation of the n:th bit in the byte.
     *
     * @param boolsInByte The byte where to get the boolean value.
     * @param index The index of the boolean value in the bit.
     * @return The boolean looked for.
     */
    private static boolean getBoolFromByte(final int boolsInByte, final int index) {
        final int mask = mask(index);
        final int result = boolsInByte & mask;
        return result != 0;
    }

    /**
     * Get the correct mask for an index in a byte.
     *
     * @param index Which mask to get.
     * @return The mask for the index.
     */
    private static int mask(final int index) {
        switch (index) {
            case 0:
                return 0b00000001;
            case 1:
                return 0b00000010;
            case 2:
                return 0b00000100;
            case 3:
                return 0b000001000;
            case 4:
                return 0b00010000;
            case 5:
                return 0b00100000;
            case 6:
                return 0b01000000;
            case 7:
                return 0b10000000;
            default:
                throw new ArithmeticException("Invalid mask");
        }
    }
}
