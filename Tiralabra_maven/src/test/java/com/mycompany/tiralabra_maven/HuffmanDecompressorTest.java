package com.mycompany.tiralabra_maven;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public final class HuffmanDecompressorTest {

    private HuffmanDecompressor decompressor;
    private static File testFile;
    private static final String testString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque elit enim, blandit a quam a, consequat rutrum eros. Pellentesque suscipit consectetur scelerisque. Curabitur at quam fermentum, tristique ligula scelerisque, accumsan augue. In arcu nulla, consectetur in tempor gravida, luctus vitae enim. Aliquam nec neque gravida, pharetra enim sed, sodales leo. Integer interdum metus quis nulla pharetra placerat. Proin et vehicula dolor. Donec gravida dolor neque, nec pretium nisl suscipit ut. Nam hendrerit augue orci, sed auctor felis tempus et. Etiam interdum tincidunt libero, ac condimentum neque volutpat accumsan.\n"
            + "\n"
            + "Vivamus fringilla lorem sit amet vestibulum porta. Donec et purus pulvinar, cursus erat sed, consectetur sapien. Praesent rhoncus nisi ut diam aliquet, a gravida mauris fringilla. Nullam adipiscing elementum lorem quis sodales. Aliquam non facilisis eros. Nullam dictum erat nec bibendum fringilla. Vivamus dapibus mattis turpis, vitae aliquam sapien luctus quis. In in massa consequat orci pharetra aliquam sit amet at quam. Donec aliquam ut nunc non sagittis. Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

    @BeforeClass
    public static void setUpStatic() {
        try {
            testFile = File.createTempFile("testHuffman", ".txt");
            try (final PrintWriter writer = new PrintWriter(testFile)) {
                writer.write(testString);
            } catch (final IOException ex) {
                System.err.println("Error writing decompressed file");
            }
            HuffmanCompressor compressor = new HuffmanCompressor(testFile.getAbsolutePath());
            compressor.compressFile();
            testFile.deleteOnExit();
        } catch (IOException ex) {
            System.err.println("Error in test: Creating temp file");
        }
    }

    @AfterClass
    public static void downStatic() {
    }

    @Before
    public void setUp() {
        decompressor = new HuffmanDecompressor("Ö://ASFOBRMIOMFKSDMFKSDFMKDS//asdas");
    }

    @Test
    public void testFileIsNotValid() {
        assertFalse(decompressor.fileIsValid());
    }

    @Test
    public void testFileCannotBeRead() {
        assertFalse(decompressor.fileCanBeRead());
    }

    @Test
    public void testInvalidDecompressionName() {
        assertFalse(decompressor.fileExists());
    }

    @Test
    public void testCantCreate() {
        assertFalse(decompressor.create());
    }

    @Test
    public void testDecompressionNotNull() {
        decompressor = new HuffmanDecompressor(testFile.getAbsolutePath() + ".pkx");
        String decompressed = decompressor.decompress();
        assertNotNull(decompressed);
    }

    @Test
    public void testDecompressionWorks() {
        decompressor = new HuffmanDecompressor(testFile.getAbsolutePath() + ".pkx");
        String decompressed = decompressor.decompress();
        System.out.println(testFile.getAbsolutePath() + ".pkx" + " :  " + decompressed);
        assertEquals(testString, decompressed);
    }
}
