package com.mycompany.tiralabra_maven;

/**
 * An immutable collection of bits. Once created, it cannot be modified.
 */
public final class BitImmutableCollection {

    /**
     * Empty array at start.
     */
    private static final boolean[] start = new boolean[0];

    private final boolean[] internal;

    /**
     * Create an empty collection of bits.
     */
    public BitImmutableCollection() {
        this.internal = start;
    }

    private BitImmutableCollection(BitImmutableCollection previous, boolean added) {
        this.internal = new boolean[previous.internal.length + 1];
        System.arraycopy(previous.internal, 0, internal, 0, previous.internal.length);
        internal[internal.length - 1] = added;
    }

    /**
     * Adds a new bit to the collection.
     *
     * @param bit Bit to add.
     * @return The new collection containing previous bits and the new bit.
     */
    public BitImmutableCollection add(boolean bit) {
        return new BitImmutableCollection(this, bit);
    }

    /**
     * Get the amount of bits in the collection.
     *
     * @return The amount of bits in the collection
     */
    public int size() {
        return internal.length;
    }

    /**
     * Get the bit at certain location.
     *
     * @param index Index of the bit. Must be 0 <= index < size().
     * @return The bit at the index.
     */
    public boolean at(int index) {
        return internal[index];
    }
}
