package PackerX;

import Compressors.HuffmanCompressor;
import Compressors.FileCompressionController;
import Compressors.HuffmanDecompressor;
import java.io.FileNotFoundException;

public final class Packer {

    private final FileCompressionController controller;
    private final FileStream fileStream;

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
        this.controller = packing ? new HuffmanCompressor(fs) : new HuffmanDecompressor(fs);
        this.fileStream = fs;
    }

    public void run() {
        if (checkFileAcces(fileStream)) {
            controller.processFile();
        }
    }

    private boolean checkFileAcces(final FileStream file) {
        if (!file.canBeRead()) {
            System.err.println("Can't access file...");
            return false;
        } else if (file.fileExists()) {
            System.err.println("Name for the new file already exists in the path");
            return false;
        } else if (!file.create()) {
            System.err.println("Could not create file");
            return false;
        } else if (!file.canWrite()) {
            System.err.println("Cannot write to the newly created file");
            return false;
        }
        return true;
    }

    private void doActions() {

    }
}
