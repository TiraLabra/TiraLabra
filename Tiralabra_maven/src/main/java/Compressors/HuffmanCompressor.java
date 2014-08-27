package Compressors;

import Collections.BitImmutableCollection;
import Collections.BitSet;
import Collections.Dictionary;
import Collections.Dictionary.KeyValuePair;
import Collections.PriorityQueue;
import PackerX.FileStream;
import PackerX.Node;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Reader;

/**
 * Compressor that uses huffman encoding to compress text files.
 */
public final class HuffmanCompressor extends FileCompressionController {

    private final PriorityQueue<Node> nodeQueue;
    private final Dictionary<Character, Node> nodes;
    private final Dictionary<Character, BitImmutableCollection> characterEncoding;
    private final StringBuilder readText;

    /**
     * Creates a new compressor to compress the stream.
     *
     * @param file The filestream containing text file.
     * @param STDOUT Output stream to write info about the compression.
     */
    public HuffmanCompressor(final FileStream file, final PrintStream STDOUT) {
        super(file, STDOUT);
        nodeQueue = new PriorityQueue<>(Node.class);
        nodes = new Dictionary<>();
        characterEncoding = new Dictionary<>();
        readText = new StringBuilder(100);
    }

    @Override
    public void processFile() throws IOException {
        try (final Reader reader = new InputStreamReader(getFile().getInputStream()); final BufferedReader bufferReader = new BufferedReader(reader)) {
            startReading(bufferReader);
            sortNodes();
            createTheHuffmanTree();
        }
        writeHuffmanTree();
    }

    /**
     * Read the data
     *
     * @param bufferReader The reader to the file.
     * @throws IOException If reading failed.
     */
    private void startReading(final BufferedReader bufferReader) throws IOException {
        while (true) {
            final int readChar = bufferReader.read();
            if (readChar == -1) {
                break;
            }
            readChar((char) readChar);
        }
    }

    /**
     * Process a single char.
     *
     * @param read The read char.
     */
    private void readChar(final char read) {
        if (!nodes.containsKey(read)) {
            nodes.add(read, new Node(read, 0, null, null));
        }
        final Node readNode = nodes.get(read);
        nodes.add(read, new Node(readNode.getSymbol(), readNode.getWeight() + 1, null, null));
        readText.append(read);
    }

    /**
     * Puts the nodes in to a priority queue.
     */
    private void sortNodes() {
        for (final KeyValuePair<Character, Node> kvp : nodes.pairs()) {
            nodeQueue.enqueue(kvp.getValue());
        }
    }

    /**
     * Creates the huffman tree from the nodes.
     */
    private void createTheHuffmanTree() {
        while (nodeQueue.size() > 1) {
            final Node left = nodeQueue.dequeue();
            final Node right = nodeQueue.dequeue();
            final Node parent = new Node('c', 0, left, right);

            nodeQueue.enqueue(parent);
        }
    }

    /**
     * Writes the huffman tree to the compressed file. At the moment it just
     * serializes the entire node object to the compressed file, which causes
     * small files to become a lot bigger that they need to become.
     *
     * The compressed file structure is following:
     *
     * 1. The serialized Node class objetc, which is the huffman tree.
     *
     * 2. The amount of bits written in the compressed file (an integer).
     *
     * 3. The bits written in little-endian. They are handled as a byte array in
     * the decompressor.
     */
    private void writeHuffmanTree() throws IOException {
        try (final DataOutputStream writer = new DataOutputStream(getFile().getOutputStream());
                final ObjectOutputStream treeWriter = new ObjectOutputStream(getFile().getOutputStream())) {
            final Node huffmanTree = nodeQueue.dequeue();
            treeWriter.writeObject(huffmanTree);
            writeCharacterBits(new BitImmutableCollection(), huffmanTree);
            final BitSet bits = new BitSet();
            writeDataToBitSet(readText.toString(), bits);
            final int bitCount = bits.length();
            writer.writeInt(bitCount);
            final byte[] bitsAsBytes = bits.toByteArray();
            writer.write(bitsAsBytes);
            print("Compressed file size: " + bitsAsBytes.length + " bytes (" + bitsAsBytes.length / 1000 + " kB)");
        }
    }

    /**
     * Creates the symbol table for all the characters. Travels recursively
     * through the tree, until it hits a leaf. The immutable collection of bits
     * contains the path to the leaf. The path is represented by bits, value of
     * 0 means left and 1 means right. Bits are represented by booleans, true
     * (1) and false(0).
     *
     * @param path The current path in the tree.
     * @param node The current node in the recursion.
     */
    private void writeCharacterBits(final BitImmutableCollection path, final Node node) {
        if (node == null) {
            return;
        }
        final Node leftNode = node.getLeft();
        final Node rightNode = node.getRight();
        if (leftNode != null) {
            writeCharacterBits(path.add(false), leftNode);
        }
        if (rightNode != null) {
            writeCharacterBits(path.add(true), rightNode);
        }
        if (node.isLeaf()) {
            characterEncoding.add(node.getSymbol(), path);
        }
    }

    /**
     * Converts a text file to a series of bits. Each character is represented
     * by a bits series, held in the bit collection.
     *
     * @param text The text to convert.
     * @param bits The bitset where to write the bits.
     */
    private void writeDataToBitSet(final String text, final BitSet bits) {
        for (int i = 0; i < text.length(); i++) {
            final BitImmutableCollection bitsCollection = characterEncoding.get(text.charAt(i));
            final int collectionSize = bitsCollection.size();
            for (int j = 0; j < collectionSize; j++) {
                bits.add(bitsCollection.at(j));
            }
        }
    }
}
