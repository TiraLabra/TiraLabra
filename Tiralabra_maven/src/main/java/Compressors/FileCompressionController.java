package Compressors;

import DataRepresentation.FileStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Superclass for the compressors and decompressors. Holds the FileStream.
 */
public abstract class FileCompressionController {

    private final FileStream file;
    private final PrintStream STDOUT;

    /**
     * Create an abstract file compression class. Holds the FileStream which can
     * be either a compressed byte stream or an uncompressed text file.
     *
     * @param file The FileStream containing the data.
     * @param STDOUT The PrintStream where information about the process can be
     * printed.
     */
    protected FileCompressionController(final FileStream file, final PrintStream STDOUT) {
        this.file = file;
        this.STDOUT = STDOUT;
        print("Running version 1.0 of the PackerX compressor.");
    }

    /**
     * Get the data stream.
     *
     * @return The data stream.
     */
    protected final FileStream getFile() {
        return file;
    }

    /**
     * Start reading and processing the file. First you should check the file
     * permissions.
     *
     * @throws java.io.IOException Should there be any problem with reading or
     * writing the compressed or decompressed file.
     */
    public final void processFile() throws IOException {
        print("Starting to process the file.");
        process();
        print("Process finished.");
    }

    /**
     * The actual procesing method.
     *
     * @throws java.io.IOException Should there be any problem with reading or
     * writing the compressed or decompressed file.
     */
    protected abstract void process() throws IOException;

    /**
     * Write an object to the given output stream. May be a null-stream.
     *
     * @param obj The object to write to the stream.
     */
    protected final void print(final Object obj) {
        STDOUT.println(obj);
    }
}
