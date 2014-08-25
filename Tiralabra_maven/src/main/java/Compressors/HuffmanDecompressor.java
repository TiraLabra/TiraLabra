package Compressors;

import Collections.BitSet;
import PackerX.FileStream;
import PackerX.Node;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
     */
    public HuffmanDecompressor(final FileStream file) {
        super(file);
    }

    /**
     * Starts the decompression process.
     */
    @Override
    public void processFile() {
        try (final ObjectInputStream objectReader = new ObjectInputStream(getFile().getInputStream());
                final DataInputStream bitReader = new DataInputStream(getFile().getInputStream())) {
            final String deCompressed = readFile(objectReader, bitReader);
            writeFile(deCompressed);
        } catch (final IOException ex) {
            System.err.println("Error decompressing files");
        } catch (final ClassNotFoundException ex) {
            System.err.println("Could not read file, it may be corrupted (Missing huffman tree)");
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
        final Node tree = (Node) objectReader.readObject();
        final int bitsInArray = bitReader.readInt();
        System.out.println("bits in file decompression " + bitsInArray);
        final int arrayLenghtInBytes = readArrayLenghts(bitsInArray);
        System.out.println("bytes in file decompression " + arrayLenghtInBytes);
        final byte[] bits = new byte[arrayLenghtInBytes];
        bitReader.read(bits);
        final BitSet bitsInSet = new BitSet(bits, bitsInArray);
        return decode(bitsInSet, tree);
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

    private void writeFile(final String text) {
        final PrintWriter writer = new PrintWriter(getFile().getOutputStream());
        writer.write(text);
        writer.flush();
    }
}
