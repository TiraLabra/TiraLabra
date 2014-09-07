package Compressors;

import Collections.BitSet;
import DataRepresentation.FileStream;
import DataRepresentation.Node;
import DataRepresentation.TreeMember;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Decompresses the files created by the huffman compressor.
 */
public final class HuffmanDecompressor extends FileCompressionController {

    private int readBits = 0;

    /**
     * Decompresses the file from the stream.
     *
     * @param file The filestream containing the huffman compressed file.
     *
     * @param STDOUT Output stream to write info about the compression.
     */
    public HuffmanDecompressor(final FileStream file, final PrintStream STDOUT) {
        super(file, STDOUT);
    }

    @Override
    protected void process() throws IOException {
        try (final ObjectInputStream objectReader = new ObjectInputStream(getFile().getInputStream());
                final DataInputStream bitReader = new DataInputStream(getFile().getInputStream())) {
            final String deCompressed = readFile(objectReader, bitReader);
            writeFile(deCompressed);
        } catch (final ClassNotFoundException ex) {
            throw new IOException("Cannot find the huffman tree");
        }
    }

    /**
     * Returns the decompressed file. Reads the huffman tree first, then the
     * amount of bits in the file and then the bits.
     *
     * @param objectReader Reader for the huffman tree.
     * @param bitReader Reader for the bits.
     * @return The decompressed file.
     * @throws IOException If reading fails.
     * @throws ClassNotFoundException If huffmantree can't be read.
     */
    private String readFile(final ObjectInputStream objectReader, final DataInputStream bitReader) throws IOException, ClassNotFoundException {
        final TreeMember tree = (TreeMember) objectReader.readObject();
        final int bitsInArray = bitReader.readInt();
        final int arrayLenghtInBytes = readArrayLenghts(bitsInArray);
        final byte[] bits = new byte[arrayLenghtInBytes];
        bitReader.read(bits);
        final BitSet bitsInSet = new BitSet(bits, bitsInArray);
        final String decoded = decode(bitsInSet, tree);
        final int bytesInDecompressedFile = decoded.length();
        print("Decompressed file size: " + bytesInDecompressedFile + " (" + bytesInDecompressedFile / 1000 + "kB).");
        return decoded;
    }

    /**
     * Get the array lenght for the byte array.
     *
     * @param arrayLenghtInBits The amount of bits in the file.
     * @return The amount of bytes in the file.
     */
    private static int readArrayLenghts(final int arrayLenghtInBits) {
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
    private String decode(final BitSet bits, final TreeMember tree) {
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
    private char decodeChar(final BitSet bits, final TreeMember node) {
        if (node.isLeaf()) {
            return ((Node) node).getSymbol();
        }
        final boolean turnRight = bits.get(readBits);
        readBits++;
        final TreeMember nodeToTurn = turnRight ? node.getRight() : node.getLeft();
        return decodeChar(bits, nodeToTurn);
    }

    private void writeFile(final String text) {
        final PrintWriter writer = new PrintWriter(getFile().getOutputStream());
        writer.write(text);
        writer.flush();
    }
}
