package Collections;

import Collections.Vector;

/**
 * An immutable collection of bits. Once created, it cannot be modified.
 */
public final class BitImmutableCollection {

    private final Vector<Boolean> internal;

    /**
     * Create an empty collection of bits.
     */
    public BitImmutableCollection() {
        this.internal = new Vector<>(Boolean.class);
    }

    private BitImmutableCollection(final BitImmutableCollection previous, final boolean added) {
        internal = new Vector<>(previous.internal);
        internal.add(added);
    }

    /**
     * Adds a new bit to the collection.
     *
     * @param bit Bit to add.
     * @return The new collection containing previous bits and the new bit.
     */
    public BitImmutableCollection add(final boolean bit) {
        return new BitImmutableCollection(this, bit);
    }

    /**
     * Get the amount of bits in the collection.
     *
     * @return The amount of bits in the collection
     */
    public int size() {
        return internal.size();
    }

    /**
     * Get the bit at certain location.
     *
     * @param index Index of the bit. Must be at least 0 and under size().
     * @return The bit at the index.
     */
    public boolean at(final int index) {
        return internal.get(index);
    }
}
