package Compressors;

import PackerX.FileStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Superclass for the compressors and decompressors. Holds the FileStream.
 */
public abstract class FileCompressionController {

    private final FileStream file;
    private final PrintStream STDOUT;

    protected FileCompressionController(final FileStream file, final PrintStream STDOUT) {
        this.file = file;
        this.STDOUT = STDOUT;
    }

    protected FileStream getFile() {
        return file;
    }

    /**
     * Start reading and processing the file. First you should check the file
     * permissions.
     *
     * @throws java.io.IOException Should there be any problem with reading or
     * writing the compressed or decompressed file.
     */
    public abstract void processFile() throws IOException;

    protected void print(final Object obj) {
        STDOUT.println(obj);
    }
}
