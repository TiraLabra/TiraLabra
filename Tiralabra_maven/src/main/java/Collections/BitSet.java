package Collections;

public final class BitSet {

    private final Vector<Boolean> bools;
    private int count;

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
            bools.add(getBoolFromByte(from, i));
        }
    }

    public BitSet() {
        this.bools = new Vector<>(Boolean.class);
        this.count = 0;
    }

    public void add(final boolean bool) {
        bools.add(bool);
        count++;
    }

    public boolean get(final int index) {
        return bools.get(index);
    }

    public int lenght() {
        return count;
    }

    public void set(final int index, final boolean bool) {
        bools.setAtt(index, bool);
    }

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

    private static byte setIndexTo(final int toSet, final int index, final boolean value) {
        final int mask = mask(index);
        final int maskedByte = value ? toSet | mask : toSet;
        return (byte) maskedByte;
    }

    private static boolean getBoolFromByte(final int b, final int index) {
        final int mask = mask(index);
        final int result = b & mask;
        return result != 0;
    }

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
