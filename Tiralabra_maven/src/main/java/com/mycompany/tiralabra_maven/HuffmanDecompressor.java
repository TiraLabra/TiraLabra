package com.mycompany.tiralabra_maven;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.BitSet;

public final class HuffmanDecompressor {

    private final File path;
    private final File decompressed;
    private int readBits = 0;

    public HuffmanDecompressor(final String path) {
        this.path = new File(path);
        final String pathToDecompressed = removeFileEnding(path);
        this.decompressed = new File(pathToDecompressed);
    }

    private String removeFileEnding(final String toRemoveFrom) {
        return toRemoveFrom.substring(0, toRemoveFrom.length() - 4);
    }

    public boolean fileIsValid() {
        return path.getName().endsWith(".pkx");
    }

    public boolean fileCanBeRead() {
        return path.canRead();
    }

    public boolean validDecompressionName() {
        return !decompressed.exists();
    }

    public boolean create() {
        try {
            return decompressed.createNewFile();
        } catch (IOException ex) {
            return false;
        }
    }

    public void decompress() {
        try (final FileInputStream file = new FileInputStream(path);
                final ObjectInputStream objectReader = new ObjectInputStream(file);
                final DataInputStream bitReader = new DataInputStream(file)) {
            final String decoded = readFile(objectReader, bitReader);
            writeFile(decoded);
        } catch (final IOException ex) {
            System.err.println("Error decompressing files");
        } catch (final ClassNotFoundException ex) {
            System.err.println("Could not read file, it may be corrupted (Missing huffman tree)");
        }
    }

    private String readFile(final ObjectInputStream objectReader, final DataInputStream bitReader) throws IOException, ClassNotFoundException {
        final Node tree = (Node) objectReader.readObject();
        final int bitsInArray = bitReader.readInt();
        System.out.println("bits in file decompression " + bitsInArray);
        final int arrayLenghtInBytes = readArrayLenghts(bitsInArray);
        System.out.println("bytes in file decompression " + arrayLenghtInBytes);
        final byte[] bits = new byte[arrayLenghtInBytes];
        bitReader.read(bits);
        final BitSet bitsInSet = BitSet.valueOf(bits);
        return decode(bitsInSet, tree);
    }

    private int readArrayLenghts(final int arrayLenghtInBits) {
        final int arrayLenghtInBytes = arrayLenghtInBits / 8;
        return arrayLenghtInBytes + 1;
    }

    private String decode(final BitSet bits, final Node tree) {
        final StringBuilder text = new StringBuilder();
        while (readBits < bits.length()) {
            text.append(decodeChar(bits, tree));
        }
        return text.toString();
    }

    private char decodeChar(final BitSet bits, final Node node) {
        if (node.isLeaf()) {
            return node.getSymbol();
        }
        final boolean turnRight = bits.get(readBits);
        readBits++;
        final Node nodeToTurn = turnRight ? node.getRight() : node.getLeft();
        return decodeChar(bits, nodeToTurn);
    }

    private void writeFile(final String text) {
        try (final PrintWriter writer = new PrintWriter(decompressed)) {
            writer.write(text);
        } catch (final IOException ex) {
            System.err.println("Error writing decompressed file");
        }
    }
}
