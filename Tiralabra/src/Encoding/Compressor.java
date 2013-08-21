/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Encoding;

import Utilities.FileIO;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class works as the middleware between the encoder, fileIO and userinterface.
 * @author virta
 */
public class Compressor implements Runnable {
    
    /**
     * Encoder to use in encoding data.
     */
    private MultiByteEncoder encoder;
    
    /**
     * The data that is produced by the encoder.
     */
    private byte[] encodedData;
    
    /**
     * The Path to the file that is to be compressed.
     */
    private String pathToDataToBeCompressed;
    
    /**
     * The bytewidth to use in compression.
     */
    private int byteWidth;
    
    private Thread encoderThread;
;    
    public Compressor(String pathToFile, int byteWidth){
        this.pathToDataToBeCompressed = pathToFile;
        this.byteWidth = byteWidth;
    }
    
    /**
     * Creates a new encoder thread with the data read from the file and starts the thread.
     * @throws Exception 
     */
    private void compress() throws Exception{
        byte[] dataToCompress = FileIO.readFromFile(pathToDataToBeCompressed);
        encoder = new MultiByteEncoder(dataToCompress, byteWidth);
        encoderThread = new Thread(encoder);
        encoderThread.start();
    }
    
    public void interrupt(){
        encoder.interrupt();
        encoderThread.stop();
    }
    
    /**
     * Useful for querying the status of the encoder and subsequently this instances' status.
     * @return 
     */
    public StatusEnum queryStatus(){
        return encoder.getStatus();
    }

    /**
     * Calls the internal compress method to start compressing, then waits in a loop for its completion.
     * After completing succesfully puts the encoded data into the internal array and writes it to file.
     */
    @Override
    public void run() {
        try {
            compress();
        } catch (Exception ex) {
            return;
        }
        
        boolean compressedSuccesfuly = true;
        while (!queryStatus().equals(StatusEnum.DONE)){
            if (queryStatus().equals(StatusEnum.DATAERROR) || queryStatus().equals(StatusEnum.INTERRUPTED)){
                compressedSuccesfuly = false;
                break;
            }
        }
        
        if (compressedSuccesfuly){
            encodedData = encoder.getCombinedEncodedKeysAndData();
            writeToFile();
        }
    }
    
    private void writeToFile(){
        try {
            FileIO.writeToFile(encodedData, pathToDataToBeCompressed);
        } catch (IOException ex) {
            
        }
    }
    
}
