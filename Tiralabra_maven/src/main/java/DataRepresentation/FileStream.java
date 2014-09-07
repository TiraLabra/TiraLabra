package DataRepresentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Class which holds the input and output files for the compression and the
 * decompression. Provides information about the files.
 */
public final class FileStream {

    private final File inputFile;
    private final File outputFile;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    /**
     * Create a new instance of the FileStream. Creates streams to the input and
     * output file paths. Full paths are required if the filepaths are not in
     * the same folder as the program.
     *
     * @param inputFileName The path to the input file.
     * @param outputFileName The path to the output file.
     * @throws FileNotFoundException If the is an error creating streams to
     * the target files.
     */
    public FileStream(final String inputFileName, final String outputFileName) throws FileNotFoundException {
        inputFile = new File(inputFileName);
        outputFile = new File(outputFileName);
        inputStream = new FileInputStream(inputFile);
        outputStream = new FileOutputStream(outputFile);
    }

    /**
     * Can the file be read?
     *
     * @return Can the file be read?
     */
    public boolean canBeRead() {
        return inputFile.canRead();
    }

    /**
     * Does the file, that is going to be the name for the output file, exist?
     *
     * @return Does the file, that is going to be the name for the output file,
     * exist?
     */
    public boolean fileExists() {
        return outputFile.exists();
    }

    /**
     * Can we write to the newly created file?
     *
     * @return Can we write to the newly created file?
     */
    public boolean canWrite() {
        return outputFile.canWrite();
    }

    /**
     * Get the underlying InputStream in the FileStream.
     *
     * @return The underlying InputStream.
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Get the underlying OutputStream in the FileStream.
     *
     * @return The underlying OutputStream.
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }
}
