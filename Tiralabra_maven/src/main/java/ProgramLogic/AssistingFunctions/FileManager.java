package ProgramLogic.AssistingFunctions;

/**
 *
 * @author alpa
 */
import java.io.File;
import javax.swing.JFileChooser;

/**
 * This class issues commands for file compression and decompression
 */
public class FileManager {

    File managedFile;
    JFileChooser chooser;

    /*
     * Class constructor
     */
    public FileManager() {
        this.chooser = new JFileChooser();
    }

    /*
     * Calls for the file compression algorithm 
     */
    public File compressFile(File f) {
        return null;
    }

    /*
     * Calls for the file decompression algorithm
     */
    public File decompressFile(File f) {
        return null;
    }

    /*
     * Browses for a file and sets it as the file currently managed
     */
    public void setFile() {
    }

    /*
     * Returns the name of the file currently managed
     */
    public String getFileName() {
        if (managedFile.getName() == null) {
            return "No file selected";
        }
        return managedFile.getName();
    }
}
