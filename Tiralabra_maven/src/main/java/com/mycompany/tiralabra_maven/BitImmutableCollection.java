package com.mycompany.tiralabra_maven;

public final class BitImmutableCollection {

    private static final boolean[] start = new boolean[0];

    private final boolean[] internal;

    public BitImmutableCollection() {
        this.internal = start;
    }

    private BitImmutableCollection(BitImmutableCollection previous, boolean added) {
        this.internal = new boolean[previous.internal.length + 1];
        System.arraycopy(previous.internal, 0, internal, 0, previous.internal.length);
        internal[internal.length - 1] = added;
    }

    public BitImmutableCollection add(boolean bit) {
        return new BitImmutableCollection(this, bit);
    }

    public int size() {
        return internal.length;
    }

    public boolean at(int index) {
        return internal[index];
    }
}
