package com.mycompany.tiralabra_maven;

import com.sun.org.apache.xpath.internal.axes.NodeSequence;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

public final class Compressor {

    private final File pathToFile;
    private final PriorityQueue<AbstractNode> nodeQueue;
    private final HashMap<Character, WeightedNode> nodes;

    public Compressor(final String path) {
        pathToFile = new File(path);
        nodeQueue = new PriorityQueue<>(new NodeComparer());
        nodes = new HashMap<>();
    }

    public boolean canBeRead() {
        return pathToFile.canRead();
    }

    public void compressFile() {
        try (final FileReader fileReader = new FileReader(pathToFile); final BufferedReader bufferReader = new BufferedReader(fileReader)) {
            startReading(bufferReader);
            sortNodes();
            createTheHuffmanTree();
        } catch (final IOException ex) {
            System.err.println("Check readability before reading!");
        }
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
            nodes.put(read, new WeightedNode(read));
        }
        final WeightedNode readNode = nodes.get(read);
        nodes.put(read, new WeightedNode(readNode));
    }

    private void sortNodes() {
        for (final AbstractNode node : nodes.values()) {
            nodeQueue.add(node);
        }
    }

    private void createTheHuffmanTree() {
        while (nodeQueue.size() > 1) {
            final AbstractNode left = nodeQueue.poll();
            final AbstractNode right = nodeQueue.poll();
            final AbstractNode parent = new Branch(left, right);

            left.setParent(parent);
            right.setParent(parent);
            nodeQueue.add(parent);
        }
    }
}
