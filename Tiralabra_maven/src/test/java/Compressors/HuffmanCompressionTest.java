package Compressors;

import DataRepresentation.FileStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import static org.junit.Assert.*;
import org.junit.Test;

public final class HuffmanCompressionTest {

    private File out;
    private File inputFile;
    private File outputFile;
    private static final String testString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque elit enim, blandit a quam a, consequat rutrum eros. Pellentesque suscipit consectetur scelerisque. Curabitur at quam fermentum, tristique ligula scelerisque, accumsan augue. In arcu nulla, consectetur in tempor gravida, luctus vitae enim. Aliquam nec neque gravida, pharetra enim sed, sodales leo. Integer interdum metus quis nulla pharetra placerat. Proin et vehicula dolor. Donec gravida dolor neque, nec pretium nisl suscipit ut. Nam hendrerit augue orci, sed auctor felis tempus et. Etiam interdum tincidunt libero, ac condimentum neque volutpat accumsan.\n"
            + "\n"
            + "Vivamus fringilla lorem sit amet vestibulum porta. Donec et purus pulvinar, cursus erat sed, consectetur sapien. Praesent rhoncus nisi ut diam aliquet, a gravida mauris fringilla. Nullam adipiscing elementum lorem quis sodales. Aliquam non facilisis eros. Nullam dictum erat nec bibendum fringilla. Vivamus dapibus mattis turpis, vitae aliquam sapien luctus quis. In in massa consequat orci pharetra aliquam sit amet at quam. Donec aliquam ut nunc non sagittis. Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
    private static final String testStringTakeTwo = "fäs4åaVA¤&?¤&lkfådak\n\n\n\n\r\nokatgopääääääääääääääääääRT^^T^5^\n6^&^&5%^&#¤*VÄ*AÖZX:BVC;ZXAWETRTYRfdjkgnzoiu+8571fd4vd546v65xcv46zf045+6´313241idsfk½½½d,lask,,<,<xzcmczn<cnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceur\n\n\nh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoafjdpsfjoacnmzxcnbceurh74783jhfg8sdfiuhöohruosdOSFJdiashgpudsuhpafjdpsfjoa";

    private void writeFile(String text) throws IOException {
        inputFile = File.createTempFile("testHuffman", ".txt");
        try (final PrintWriter writer = new PrintWriter(inputFile)) {
            writer.write(text);
        }
        FileStream fs = new FileStream(inputFile.getAbsolutePath(), inputFile.getAbsolutePath() + ".pkx");
        FileCompressionController controller = new HuffmanCompressor(fs, System.out);
        controller.processFile();
        outputFile = new File(inputFile.getAbsoluteFile() + ".pkx");
    }

    private String read() throws FileNotFoundException, IOException {
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
        }
    }

    private void compressAndDecompress(String text) throws IOException {
        writeFile(text);
        out = new File(inputFile.getAbsolutePath() + ".decompressed");
        out.createNewFile();
        FileStream fs = new FileStream(outputFile.getAbsolutePath(), out.getAbsolutePath());
        HuffmanDecompressor decompressor = new HuffmanDecompressor(fs, System.out);
        decompressor.processFile();
        assertEquals(text, read());
    }

    @Test
    public void testDecompressionWorks() throws FileNotFoundException, IOException {
        compressAndDecompress(testString);
    }

    @Test
    public void testDecompressionWorksTakeTwo() throws FileNotFoundException, IOException {
        compressAndDecompress(testStringTakeTwo);
    }
}
