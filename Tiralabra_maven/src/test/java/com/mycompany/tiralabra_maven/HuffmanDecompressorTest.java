package com.mycompany.tiralabra_maven;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public final class HuffmanDecompressorTest {

    private File out;
    private static File inputFile;
    private static File outputFile;
    private static final String testString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque elit enim, blandit a quam a, consequat rutrum eros. Pellentesque suscipit consectetur scelerisque. Curabitur at quam fermentum, tristique ligula scelerisque, accumsan augue. In arcu nulla, consectetur in tempor gravida, luctus vitae enim. Aliquam nec neque gravida, pharetra enim sed, sodales leo. Integer interdum metus quis nulla pharetra placerat. Proin et vehicula dolor. Donec gravida dolor neque, nec pretium nisl suscipit ut. Nam hendrerit augue orci, sed auctor felis tempus et. Etiam interdum tincidunt libero, ac condimentum neque volutpat accumsan.\n"
            + "\n"
            + "Vivamus fringilla lorem sit amet vestibulum porta. Donec et purus pulvinar, cursus erat sed, consectetur sapien. Praesent rhoncus nisi ut diam aliquet, a gravida mauris fringilla. Nullam adipiscing elementum lorem quis sodales. Aliquam non facilisis eros. Nullam dictum erat nec bibendum fringilla. Vivamus dapibus mattis turpis, vitae aliquam sapien luctus quis. In in massa consequat orci pharetra aliquam sit amet at quam. Donec aliquam ut nunc non sagittis. Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

    @BeforeClass
    public static void setUpStatic() {
        try {
            inputFile = File.createTempFile("testHuffman", ".txt");
            try (final PrintWriter writer = new PrintWriter(inputFile)) {
                writer.write(testString);
            } catch (final IOException ex) {
                System.err.println("Error writing decompressed file");
            }
            FileStream fs = new FileStream(inputFile.getAbsolutePath(), inputFile.getAbsolutePath() + ".pkx");
            FileCompressionController controller = new HuffmanCompressor(fs);
            controller.processFile();
            outputFile = new File(inputFile.getAbsoluteFile() + ".pkx");
        } catch (IOException ex) {
            System.err.println("Error in test: Creating temp file");
        }
    }

    private String read() {
        try (BufferedReader br = new BufferedReader(new FileReader(out))) {
            StringBuilder sb = new StringBuilder();
            while (true) {
                int line = br.read();
                if (line == -1) {
                    break;
                }
                sb.append((char) line);
            }
            return sb.toString();
        } catch (IOException ex) {
            return null;
        }
    }

    @Test
    public void testDecompressionWorks() throws FileNotFoundException, IOException {
        out = new File(inputFile.getAbsolutePath() + ".decompressed");
        out.createNewFile();
        FileStream fs = new FileStream(outputFile.getAbsolutePath(), out.getAbsolutePath());
        HuffmanDecompressor decompressor = new HuffmanDecompressor(fs);
        decompressor.processFile();
        System.out.println("Read " + read());
        System.out.println("Expected " + testString);
        assertEquals(testString, read());
    }
}
