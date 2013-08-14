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

    public static void main(String... args) {


        try {
            char cmd = args[0].charAt(0);
            Steganographer s;
            switch (cmd) {
                case 'c':
                    checkMaxLength(args[1]);
                    break;
                case 'e':
                    encode(args);
                    break;
                case 'd':
                    decode(args[1], "");
                    break;
                case 'b':
                    Huffman h = new Huffman("");
                    BinaryTreeMap m = new BinaryTreeMap();
                    m.put("\t", "0");
                    m.put(" ", "10");
                    m.put("l", "110");
                    m.put("p", "111");
                    h.setMap(m);
                    System.out.println(h.getStringifiedMap());
                    break;
            }

        } catch (IOException ioe) {
            System.out.println("Oh poop.");
            ioe.printStackTrace();
        } catch (IllegalHuffmanCodeException huff) {
            System.out.println("Huffman decoding failed");
            huff.printStackTrace();
        }


    }

    static void checkMaxLength(String path) throws IOException {
        Steganographer s = new Steganographer(path);
        System.out.println("Maximum encoding length for file " + path + " = " + s.getMaximumMessageLength());
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
