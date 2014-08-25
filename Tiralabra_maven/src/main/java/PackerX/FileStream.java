package PackerX;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public final class FileStream {

    private final File inputFile;
    private final File outputFile;
    private final InputStream inputStream;
    private final OutputStream outputStream;

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

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
