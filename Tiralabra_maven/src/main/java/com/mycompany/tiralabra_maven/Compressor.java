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

public final class Compressor {

    private final File pathToFile;
    private final File pathToCompressedFile;
    private final PriorityQueue<WeightedNode> nodeQueue;
    private final HashMap<Character, WeightedNode> nodes;
    private final HashMap<Character, BitImmutableCollection> characterEncoding;
    private final StringBuilder readText;

    public Compressor(final String path) {
        pathToFile = new File(path);
        pathToCompressedFile = new File(path + ".pkx");
        nodeQueue = new PriorityQueue<>(new NodeComparer());
        nodes = new HashMap<>();
        characterEncoding = new HashMap<>();
        readText = new StringBuilder(100);
    }

    public boolean fileExists() {
        return pathToCompressedFile.exists();
    }

    public boolean create() {
        try {
            return pathToCompressedFile.createNewFile();
        } catch (IOException ex) {
            return false;
        }
    }

    public boolean canBeRead() {
        return pathToFile.canRead();
    }

    public boolean canWrite() {
        return pathToCompressedFile.canWrite();
    }

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

    private void startReading(final BufferedReader bufferReader) throws IOException {
        while (true) {
            final int readChar = bufferReader.read();
            if (readChar == -1) {
                break;
            }
            readChar((char) readChar);
        }
        System.out.println("\n\n<-- End of File -->\n\n");
    }

    private void readChar(final char read) {
        if (!nodes.containsKey(read)) {
            nodes.put(read, new WeightedNode(read, 0, null, null));
        }
        final WeightedNode readNode = nodes.get(read);
        nodes.put(read, new WeightedNode(readNode.getSymbol(), readNode.getWeight() + 1, null, null));
        readText.append(read);
    }

    private void sortNodes() {
        for (final WeightedNode node : nodes.values()) {
            nodeQueue.add(node);
        }
    }

    private void createTheHuffmanTree() {
        while (nodeQueue.size() > 1) {
            final WeightedNode left = nodeQueue.poll();
            final WeightedNode right = nodeQueue.poll();
            final WeightedNode parent = new WeightedNode('c', 0, left, right);

            left.setParent(parent);
            right.setParent(parent);
            nodeQueue.add(parent);
        }
    }

    private void writeHuffmanTree() {
        try (final FileOutputStream output = new FileOutputStream(pathToCompressedFile);
                final DataOutputStream writer = new DataOutputStream(output);
                final ObjectOutputStream treeWriter = new ObjectOutputStream(output)) {
            treeWriter.writeObject(nodeQueue.peek());
            writeCharacterBits(new BitImmutableCollection(), nodeQueue.peek());
            final BitSet bits = new BitSet();
            writeDataToBitSet(readText.toString(), bits);
            final byte[] bitsToBytes = bits.toByteArray();
            writer.writeInt(bitsToBytes.length);
            writer.write(bitsToBytes);
        } catch (final IOException ex) {
            System.err.println("Check write access before writing...\n" + ex.toString());
        }
    }

    private void writeCharacterBits(final BitImmutableCollection path, final WeightedNode node) {
        if (node == null) {
            return;
        }
        final WeightedNode leftNode = node.getLeft();
        final WeightedNode rightNode = node.getRight();
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

    private void writeDataToBitSet(final String text, final BitSet bits) {
        int bitsWritten = 0;
        for (int i = 0; i < text.length(); i++) {
            final BitImmutableCollection bitsCollection = characterEncoding.get(text.charAt(i));
            final int collectionSize = bitsCollection.size();
            for (int j = 0; j < collectionSize; j++) {
                bits.set(j + bitsWritten, bitsCollection.at(j));
            }
            bitsWritten += collectionSize;
        }
    }
}
