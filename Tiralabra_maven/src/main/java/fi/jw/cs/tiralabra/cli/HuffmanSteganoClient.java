package fi.jw.cs.tiralabra.cli;

import fi.jw.cs.tiralabra.BinaryTreeMap;
import fi.jw.cs.tiralabra.Huffman;
import fi.jw.cs.tiralabra.IllegalHuffmanCodeException;
import fi.jw.cs.tiralabra.Steganographer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This is a command-line utility to invoke the Huffman-Steganography encoding and decoding of files.
 * <p/>
 * <p>
 * Author's note: Here be dragons. This cli could be cleaned up :)
 * </p>
 *
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-02
 */

public class HuffmanSteganoClient {
    static Charset cs = Charset.defaultCharset();

    static final String ENCODE = "1";
    static final String DECODE = "2";
    static final String QUIT = "q";
    static Scanner scanner;
    static BinaryTreeMap options = new BinaryTreeMap();

    public static void main(String... args) {
        options.put(ENCODE, "Huffman-Stegano encode");
        options.put(DECODE, "Stegano-Huffman decode");
        options.put(QUIT, "Quit");

        welcome();
        printOptions();
        scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        try {
            while (!choice.equals(QUIT)) {

                switch (choice) {
                    case ENCODE:
                        encode();
                        break;
                    case DECODE:
                        decode();
                        break;
                    default:
                        System.out.println("[" + choice + "] is not a valid option. Please retry.");
                }

                printOptions();
                choice = scanner.nextLine();

            }

        } catch (IOException ioe) {
            System.out.println("IO Failed");
            ioe.printStackTrace();
        } catch (IllegalHuffmanCodeException huff) {
            System.out.println("Huffman decoding failed");
            huff.printStackTrace();
        }

        System.out.println("Goodbye.");
    }

    static String requestSourceFilename(String _prompt) {
        String prompt = (_prompt == null || _prompt.length() == 0) ? "File to read from" : _prompt;
        System.out.print(prompt + "> ");
        return scanner.nextLine().trim();
    }

    static boolean validChoice(String choice) {
        return options.containsKey(choice);
    }

    static void welcome() {
        System.out.println("Welcome to a Huffman/Steganographer encoder/decoder program");

    }

    static void printOptions() {
        System.out.println("Choose next action:");
        for (String key : options.keys()) {
            System.out.println(key + ") " + options.get(key));
        }
        System.out.print(">> ");
    }

    static String[] huffmanEncode(String source) throws IOException {
        String message = readFileContentsString(source);
        if (message == null || message.length() == 0) {
            throw new IllegalArgumentException();
        }

        Huffman encoder = new Huffman(message);
        encoder.encode();
        String encodedMessage = encoder.getEncodedMessage();
        String map = encoder.getStringifiedMap();
        String[] result = new String[2];

        int originalBits = encoder.getMessage().length() * Huffman.BITS_PER_CHAR;
        int encodedBits = encodedMessage.length();
        double compressionRatio = 100 * ((double) encodedBits / (double) originalBits);
        System.out.println("Original message length: " + originalBits + " bits");
        System.out.println("Compressed message length: " + encodedBits + " bits");
        System.out.println("Compressed size: " + (int) compressionRatio + " %");

        result[0] = encodedMessage;
        result[1] = map;
        return result;
    }

    static void checkMaxLength(String path) throws IOException {
        Steganographer s = new Steganographer(path);
        int[] max = s.getMaximumMessageLength();
        System.out.println("Maximum encoding length for file " + path + " = " + max[0] + " bits or roughly " + max[1] + " chars");
    }

    static String readFileContentsString(String path) throws IOException {
        return cs.decode(ByteBuffer.wrap(readFileContents(path))).toString();
    }

    static byte[] readFileContents(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }

    static void encode() throws IOException {
        String filename = requestSourceFilename("Give path to plain text file");
        String[] results = huffmanEncode(filename);
        String encodedMessage = results[0];
        String encodedMap = results[1];

        String image = requestSourceFilename("Give path to image to inject");

        steganoEncode(image, encodedMessage, encodedMap);

    }

    static void steganoEncode(String path, String msg, String map) throws IOException {
        Steganographer s = new Steganographer(path, msg);
        s.encode();
        String baseName = new File(path).getName();
        String dest = "encoded-" + baseName + ".png";
        s.saveFile(dest);


        String huffmanMapFilename = mapFilename(dest);
        writeHuffmanMap(huffmanMapFilename, map);

        System.out.println("Encoded image saved as " + dest);
        System.out.println("Huffman map saved as " + huffmanMapFilename);
        s = null;
    }

    static String mapFilename(String path) {
        return path + ".huff";
    }

    private static void writeHuffmanMap(String path, String map) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        bw.write(map);
        bw.close();
    }

    private static String readHuffmanMap(String path) throws IOException {
        File m = new File(path);
        if (m.exists()) {
            byte[] bytes = Files.readAllBytes(Paths.get(m.toURI()));

            return cs.decode(ByteBuffer.wrap(bytes)).toString();
        } else {
            throw new IOException("Huffman map file " + path + " missing.");
        }
    }

    static void decode() throws IOException, IllegalHuffmanCodeException {
        String image = requestSourceFilename("Give path to image to decode");
        String stegDecoded = steganoDecode(image);
        String huffmanMapFilename = mapFilename(image);
        String huffMap = readHuffmanMap(huffmanMapFilename);

        Huffman decoder = new Huffman();
        decoder.setMap(decoder.parseMap(huffMap));
        decoder.setEncodedMessage(stegDecoded);
        decoder.decode();
        System.out.println(decoder.getMessage());
    }

    private static String steganoDecode(String image) throws IOException {

        Steganographer dec = new Steganographer(image);
        dec.decode();
        return dec.getMessage();
    }
}
