package com.mycompany.tiralabra_maven;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.BitSet;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Compressor that uses huffman encoding to compress text files.
 */
public final class HuffmanCompressor {

    private final File pathToFile;
    private final File pathToCompressedFile;
    private final PriorityQueue<Node> nodeQueue;
    private final HashMap<Character, Node> nodes;
    private final HashMap<Character, BitImmutableCollection> characterEncoding;
    private final StringBuilder readText;

    /**
     * Creates a new compressor to compress the file at given path.
     *
     * @param path File to compress.
     */
    public HuffmanCompressor(final String path) {
        pathToFile = new File(path);
        pathToCompressedFile = new File(path + ".pkx");
        nodeQueue = new PriorityQueue<>();
        nodes = new HashMap<>();
        characterEncoding = new HashMap<>();
        readText = new StringBuilder(100);
    }

    /**
     * Does the file, that is going to be the name for the compressed file,
     * exist?
     *
     * @return Does the file, that is going to be the name for the compressed
     * file, exist?
     */
    public boolean fileExists() {
        return pathToCompressedFile.exists();
    }

    /**
     * Try to create the file for the compression.
     *
     * @return Was the file created?
     */
    public boolean create() {
        try {
            return pathToCompressedFile.createNewFile();
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Can the file, which is going to be compressed, be read?
     *
     * @return Can the file, which is going to be compressed, be read?
     */
    public boolean canBeRead() {
        return pathToFile.canRead();
    }

    /**
     * Can we write to the newly created file the compressed data?
     *
     * @return Can we write to the newly created file the compressed data?
     */
    public boolean canWrite() {
        return pathToCompressedFile.canWrite();
    }

    /**
     * Start reading and compressing the file. First you should check the file
     * permissions.
     */
    public void compressFile() {
        try (final FileReader fileReader = new FileReader(pathToFile); final BufferedReader bufferReader = new BufferedReader(fileReader)) {
            startReading(bufferReader);
            sortNodes();
            createTheHuffmanTree();
        } catch (final IOException ex) {
            System.err.println("Check readability before reading!");
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
            //System.out.println((char) readChar);
            readChar((char) readChar);
        }
        System.out.println("\n\n<-- End of File -->\n\n");
    }

    /**
     * Process a single char.
     *
     * @param read The read char.
     */
    private void readChar(final char read) {
        if (!nodes.containsKey(read)) {
            nodes.put(read, new Node(read, 0, null, null));
        }
        final Node readNode = nodes.get(read);
        nodes.put(read, new Node(readNode.getSymbol(), readNode.getWeight() + 1, null, null));
        readText.append(read);
    }

    /**
     * Puts the nodes in to a priority queue.
     */
    private void sortNodes() {
        for (final Node node : nodes.values()) {
            nodeQueue.add(node);
        }
    }

    /**
     * Creates the huffman tree from the nodes.
     */
    private void createTheHuffmanTree() {
        while (nodeQueue.size() > 1) {
            final Node left = nodeQueue.poll();
            final Node right = nodeQueue.poll();
            final Node parent = new Node('c', 0, left, right);
            
            nodeQueue.add(parent);
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
    private void writeHuffmanTree() {
        try (final FileOutputStream output = new FileOutputStream(pathToCompressedFile);
                final DataOutputStream writer = new DataOutputStream(output);
                final ObjectOutputStream treeWriter = new ObjectOutputStream(output)) {
            treeWriter.writeObject(nodeQueue.peek());
            writeCharacterBits(new BitImmutableCollection(), nodeQueue.peek());
            final BitSet bits = new BitSet();
            writeDataToBitSet(readText.toString(), bits);
            final int bitCount = bits.length();
            writer.writeInt(bitCount);
            System.out.println("bits in file compression " + bitCount);
            final byte[] bitsAsBytes = bits.toByteArray();
            writer.write(bitsAsBytes);
            System.out.println("bytes in file compression " + bitsAsBytes.length);
        } catch (final IOException ex) {
            System.err.println("Check write access before writing...\n" + ex.toString());
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
            characterEncoding.put(node.getSymbol(), path);
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
        int bitsWritten = 0;
        for (int i = 0; i < text.length(); i++) {
            final BitImmutableCollection bitsCollection = characterEncoding.get(text.charAt(i));
            final int collectionSize = bitsCollection.size();
            for (int j = 0; j < collectionSize; j++) {
                bits.set(j + bitsWritten, bitsCollection.at(j));
            }
            bitsWritten += collectionSize;
            if (i == text.length() - 1) {
                System.out.println(text.charAt(i));
            }
        }
    }
}
