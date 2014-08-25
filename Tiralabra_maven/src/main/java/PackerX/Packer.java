package PackerX;

import Compressors.HuffmanCompressor;
import Compressors.FileCompressionController;
import Compressors.HuffmanDecompressor;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main application.
 */
public final class Packer {

    private final FileCompressionController controller;
    private final FileStream fileStream;

    /**
     * Creates the main application.
     *
     * @param arguments The commandline arguments.
     */
    public Packer(final String[] arguments) {
        FileStream fs = null;
        boolean packing = false;
        for (final String arg : arguments) {
            switch (arg) {
                case "pack":
                    packing = true;
                    break;
                case "unpack":
                    break;
                default:
                    try {
                        if (arg.endsWith(".txt")) {
                            fs = new FileStream(arg, arg + ".pkx");
                            break;
                        } else if (arg.endsWith(".pkx")) {
                            fs = new FileStream(arg, arg.substring(0, arg.length() - 4));
                            break;
                        }
                    } catch (final FileNotFoundException ex) {
                        System.err.println("Cannot find file");
                    }
                    System.err.println("Invalid argument " + arg);
                    throw new IllegalArgumentException();
            }
        }
        this.controller = packing ? new HuffmanCompressor(fs, System.out) : new HuffmanDecompressor(fs, System.out);
        this.fileStream = fs;
    }

    /**
     * Start the program.
     */
    public void run() {
        if (checkFileAcces(fileStream)) {
            try {
                controller.processFile();
            } catch (final IOException ex) {
                System.err.println("Error processing the file: " + ex.getMessage());
            }
        }
    }

    private boolean checkFileAcces(final FileStream file) {
        if (!file.canBeRead()) {
            System.err.println("Can't access file...");
            return false;
        } else if (!file.fileExists()) {
            System.err.println("Cannot create new file for output");
            return false;
        } else if (!file.canWrite()) {
            System.err.println("Cannot write to the newly created file");
            return false;
        }
        return true;
    }
}
