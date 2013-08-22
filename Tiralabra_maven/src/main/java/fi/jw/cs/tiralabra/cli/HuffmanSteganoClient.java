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

    static final String QUIT = "q";
    static Scanner scanner;
    static BinaryTreeMap options = new BinaryTreeMap();

    public static void main(String... args) {
        options.put("1", "Huffman encode");
        options.put("2", "Huffman decode");
        options.put("3", "Stegano encode");
        options.put("4", "Stegano decode");
        options.put("5", "Huffman-Stegano encode");
        options.put("6", "Stegano-Huffman decode");
        options.put(QUIT, "Quit");

        welcome();
        printOptions();
        scanner = new Scanner(System.in);
        String choice = "1";//scanner.nextLine();

        try {
            while (!choice.equals(QUIT)) {
                if (validChoice(choice)) {

                    switch (choice) {
                        case "1":
                            String filename = "/home/unfo/TiraLabra/Tiralabra_maven/lib/huffman.txt";//requestSourceFilename();
                            String[] results = huffmanEncode(filename);
                            break;
                        case "check":
                            checkMaxLength(args[1]);
                            break;
                        case "encode":
                            encode(args);
                            break;
                        case "decode":
                            decode(args[1], "");
                            break;
                    }

                } else {
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


    }

    static String requestSourceFilename() {
        System.out.print("File to read from > ");
        return scanner.nextLine().trim();
    }

    static boolean validChoice(String choice) {
        return options.containsKey(choice);
    }

    static void welcome() {
        System.out.println("Welcome to a Huffman/Steganographer encoder/decoder program");
        for (String key : options.keys()) {
            System.out.println(key + ") " + options.get(key));
        }
    }

    static void printOptions() {
        System.out.print("Would you like to :");
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
        System.out.println(map);

        return result;
    }

    static void checkMaxLength(String path) throws IOException {
        Steganographer s = new Steganographer(path);
        System.out.println("Maximum encoding length for file " + path + " = " + s.getMaximumMessageLength());
    }

    static String readFileContentsString(String path) throws IOException {
        return cs.decode(ByteBuffer.wrap(readFileContents(path))).toString();
    }

    static byte[] readFileContents(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }

    static void encode(String... args) throws IOException {

        String message = "";
        String src = args[1];
        String dest = args[2];

        if ("file".equals(args[3])) {
            System.out.println("Reading from file");
            byte[] bytes = Files.readAllBytes(Paths.get(args[4]));

            message = cs.decode(ByteBuffer.wrap(bytes)).toString();
        } else {
            System.out.println("Reading from cli [" + args[3] + "]");
            for (int i = 3; i < args.length; i++) {
                message += args[i] + " ";
            }
            message = message.trim();
        }
        System.out.println("Message length: " + message.length() + " bytes");
        Huffman encoder = new Huffman(message);
        encoder.encode();
        String encodedMessage = encoder.getEncodedMessage();
        String map = encoder.getStringifiedMap();
        System.out.println("Map:\n" + map);
        Steganographer s = new Steganographer(src, encodedMessage);

        s.encode();
        s.saveFile(dest);
        System.out.println(dest + " saved");

        // let's write the map to file so we can open it

        if ("file".equals(args[3])) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(dest + ".huff"));
            bw.write(map);
            bw.close();
        }

        s = null;
    }

    static void decode(String path, String map) throws IOException, IllegalHuffmanCodeException {
        System.out.println("Opening " + path);
        Steganographer dec = new Steganographer(path);
        dec.decode();
        String stegDecoded = dec.getMessage();

        Huffman decoder = new Huffman();

        String huffMap = map;
        File m = new File(path + ".huff");
        if (m.exists()) {
            System.out.println("Reading from file");
            byte[] bytes = Files.readAllBytes(Paths.get(m.toURI()));

            huffMap = cs.decode(ByteBuffer.wrap(bytes)).toString();
        }
        decoder.setMap(decoder.parseMap(huffMap));
        decoder.setEncodedMessage(stegDecoded);
        decoder.decode();
        System.out.println(decoder.getMessage());
    }
}
