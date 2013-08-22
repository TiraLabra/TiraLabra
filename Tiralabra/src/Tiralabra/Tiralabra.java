package Tiralabra;

/**
 *
 * @author virta
 */
import Encoding.MultiByteDecoder;
import Encoding.MultiByteEncoder;
import Encoding.StatusEnum;
import UI.UIscreen;
import Utilities.FileIO;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.SwingUtilities;

public class Tiralabra {

    private static byte[] fileBytes;

    public static void main(String[] args) throws IOException {

        args = getArguments();

        String operation = args[0];
        String path;
        if (operation.equals("c")) {
            int bytewidth;
            
            try {
                bytewidth = Integer.parseInt(args[1]);
                if (bytewidth > 16){
                    printInstructions();
                    return;
                }
                
            } catch (NumberFormatException numberFormatException) {
                printInstructions();
                return;
            }
            
            path = args[2];
            compressFile(path, bytewidth);

        } else if (operation.equals("d")) {
            path = args[1];
            decompressFile(path);

        } else if (operation.equals("g")) {
            UIscreen screen = new UIscreen();
            SwingUtilities.invokeLater(screen);

        } else {
            printInstructions();
        }

    }

    /**
     * Makes a new encoder and starts a thread enveloping it. Waits for completion and writes data to file.
     * @param path
     * @param bytewidth 
     */
    private static void compressFile(String path, int bytewidth) {
        if (!readFile(path)) {
            System.out.println("Inaccessible file");
            return;
        }
        MultiByteEncoder encoder = new MultiByteEncoder(fileBytes, bytewidth);
        Thread encoderThread = new Thread(encoder);
        encoderThread.start();
        boolean completedSuccesfully = waitForEncoder(encoder);
        
        if (completedSuccesfully){
            path = path+".vZip";
            writeToFile(encoder.getCombinedEncodedKeysAndData(), path);
        }
    }

    /**
     * Makes a new decoder and starts a thread enveloping it. Waits for completion and writes data to file.
     * @param path 
     */
    private static void decompressFile(String path) {
        if (!readFile(path)) {
            System.out.println("Inaccessible file");
            return;
        }
        MultiByteDecoder decoder = new MultiByteDecoder(fileBytes);
        Thread decoderThread = new Thread(decoder);
        decoderThread.start();
        boolean completedSuccessully = waitForDecoder(decoder);
        
        if (completedSuccessully){
            path = path.substring(0, path.length()-5);
            writeToFile(decoder.getDecodedData(), path);
        }
    }

    /**
     * Reads any data from the file pointed to by the path and stores it into the global variable. Hanldes IOExeption.
     * @param path
     * @return 
     */
    private static boolean readFile(String path) {
        try {
            fileBytes = FileIO.readFromFile(path);
            return true;
        } catch (IOException ex) {
            System.out.println("Error reading file: "+ex.getMessage());
            return false;
        }
    }
    
    /**
     * Writes to file any supplied data at the location pointed to by the path, creates a new file but does not overwrite existing but fails. Hanldes IOExeption.
     * @param bytes
     * @param path 
     */
    private static void writeToFile(byte[] bytes, String path){
        try {
            FileIO.writeToFile(bytes, path);
        } catch (IOException ex) {
            System.out.println("Error writing to file: file "+path+" already exists");
        }
    }

    /**
     * Prints the usage instructions.
     */
    private static void printInstructions() {
        System.out.println("Usage:");
        System.out.println("    vZip cdg [path] [bytewidth]");
        System.out.println("    c   [absolute path] [3 - 16]    compress the file pointed to by the path with this bytewidth");
        System.out.println("    d   [absolute path]             decompress the file pointed to by the path");
        System.out.println("    g   (no path)                   start the graphical user interface, does not work properly yet");
    }

    /**
     * Reads the parameters given to the program from the user.
     * @return 
     */
    private static String[] getArguments() {
        String argsString = "";
        while (argsString.length() == 0) {
            printInstructions();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Operation: ");
            argsString = scanner.nextLine();
        }
        String[] args = argsString.split(" ");
        return args;
    }

    /**
     * Waits for encoder completing in a busy wait loop, in which the status of the encoder is printed with current running time.
     * @param encoder
     * @return 
     */
    private static boolean waitForEncoder(MultiByteEncoder encoder) {
        long time = System.currentTimeMillis();
        
        while (!encoder.getStatus().equals(StatusEnum.DONE)){
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Interrupted");
            }
            
            long timeNow = System.currentTimeMillis();
            System.out.println(encoder.getStatus()+": "+(timeNow-time)/1000+" sec");
            
            if (encoder.getStatus().equals(StatusEnum.DATAERROR) ||encoder.getStatus().equals(StatusEnum.INTERRUPTED)){
                System.out.println("Incompressible");
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Waits for decoder in a busy wait loop, in which the status of the decoder is printed with current running time.
     * @param decoder
     * @return 
     */
    private static boolean waitForDecoder(MultiByteDecoder decoder) {
        long time = System.currentTimeMillis();
        
        while (!decoder.getStatus().equals(StatusEnum.DONE)){
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Interrupted");
            }
            
            long timeNow = System.currentTimeMillis();
            System.out.println(decoder.getStatus()+": "+(timeNow-time)/1000+" sec");

            if (decoder.getStatus().equals(StatusEnum.DATAERROR) ||decoder.getStatus().equals(StatusEnum.INTERRUPTED)){
                System.out.println("Data does not match vZip format");
                return false;
            }
        }
        
        return true;
    }
}
