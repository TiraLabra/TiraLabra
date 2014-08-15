package com.mycompany.tiralabra_maven;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.BitSet;

/**
 * Decompresses the files created by the huffman compressor.
 */
public final class HuffmanDecompressor {

    private final File path;
    private final File decompressed;
    private int readBits = 0;

    /**
     * Decompresses the file from the path.
     *
     * @param path The compressed file.
     */
    public HuffmanDecompressor(final String path) {
        this.path = new File(path);
        final String pathToDecompressed = removeFileEnding(path);
        this.decompressed = new File(pathToDecompressed);
    }

    private String removeFileEnding(final String toRemoveFrom) {
        return toRemoveFrom.substring(0, toRemoveFrom.length() - 4);
    }

    /**
     * Has the file correct file ending?
     *
     * @return Has the file correct file ending?
     */
    public boolean fileIsValid() {
        return path.getName().endsWith(".pkx");
    }

    /**
     * Can the file be read?
     *
     * @return Can the file be read?
     */
    public boolean fileCanBeRead() {
        return path.canRead();
    }

    /**
     * Is the name for the decompressed file valid?
     *
     * @return Is the name for the decompressed file valid?
     */
    public boolean fileExists() {
        return decompressed.exists();
    }

    /**
     * Tries to create a file for the decompressed data.
     *
     * @return Was the file created.
     */
    public boolean create() {
        try {
            return decompressed.createNewFile();
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Starts the decompression process.
     *
     * @return The decompressed file.
     */
    public String decompress() {
        try (final FileInputStream file = new FileInputStream(path);
                final ObjectInputStream objectReader = new ObjectInputStream(file);
                final DataInputStream bitReader = new DataInputStream(file)) {
            return readFile(objectReader, bitReader);
        } catch (final IOException ex) {
            System.err.println("Error decompressing files");
        } catch (final ClassNotFoundException ex) {
            System.err.println("Could not read file, it may be corrupted (Missing huffman tree)");
        }
        return null;
    }

    /**
     * Returns the decompressed file. Reads the huffman tree first, then the
     * amount of bits in the file and then the bits.s
     *
     * @param objectReader Reader for the huffman tree.
     * @param bitReader Reader for the bits.
     * @return The decompressed file
     * @throws IOException If reading fails.
     * @throws ClassNotFoundException If huffman tree can't be read.
     */
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

    /**
     * Get the array lenght for the byte array.
     *
     * @param arrayLenghtInBits The amount of bits in the file.
     * @return The amount of bytes in the file.
     */
    private int readArrayLenghts(final int arrayLenghtInBits) {
        final int arrayLenghtInBytes = arrayLenghtInBits / 8;
        return arrayLenghtInBytes + 1;
    }

    /**
     * Decompress the file using bits from the given BitSet and the given
     * huffman tree.
     *
     * @param bits The compressed bits.
     * @param tree The huffman tree used for decoding.
     * @return The decompressed text.
     */
    private String decode(final BitSet bits, final Node tree) {
        final StringBuilder text = new StringBuilder();
        while (readBits < bits.length()) {
            text.append(decodeChar(bits, tree));
        }
        return text.toString();
    }

    /**
     * Get a single character from the bitset. Travels recursively through the
     * tree, turning left on every false from the bitset and right on every
     * true. When hitted a leaf, the symbol is found. Increases readBits for
     * every bit read from the bitset.
     *
     * @param bits The compressed data.
     * @param node The current node in the recursion.
     * @return The character from the leaf.
     */
    private char decodeChar(final BitSet bits, final Node node) {
        if (node.isLeaf()) {
            return node.getSymbol();
        }
        final boolean turnRight = bits.get(readBits);
        readBits++;
        final Node nodeToTurn = turnRight ? node.getRight() : node.getLeft();
        return decodeChar(bits, nodeToTurn);
    }
}
