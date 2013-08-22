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
 * This class works as the middleman between decoder, fileIO and userinterface.
 * @author virta
 */
public class Decompressor implements Runnable {
    
    /**
     * The path to the file to be decompressed.
     */
    private String pathToCompressedData;
    
    /**
     * The resulting decompressed data.
     */
    private byte[] decompressedData;
    
    /**
     * The decoder to use.
     */
    private MultiByteDecoder decoder;
    
    /**
     * The name of the file.
     */
    private String fileName;
    
    /**
     * Thread for the decoder.
     */
    private Thread decoderThread;
    
    public Decompressor(String path){
        this.pathToCompressedData = path;
    }
    
    /**
     * Reads from the path the files' contents and starts a new decoder thread.
     * @throws Exception 
     */
    private void decompress() throws Exception {
        byte[] compressedData = FileIO.readFromFile(pathToCompressedData);
        decoder = new MultiByteDecoder(compressedData);
        decoderThread = new Thread(decoder);
        decoderThread.start();
    }
    
    /**
     * Used to interrupt the decoding process. Does not work yet.
     */
    public void interrupt(){
        decoder.interrupt();
//        decoderThread.stop();
    }
    
    /**
     * Useful for querying the status of the encoder and this class' instance.
     * @return 
     */
    public StatusEnum queryStatus(){
        return decoder.getStatus();
    }

    /**
     * Calls the internal decompress method then waits in a loop for decompression to finish.
     * After succesful decompression, queries the decompressed data from the encoder and writes it to file.
     */
    @Override
    public void run() {
        try {
            decompress();
        } catch (Exception ex) {
            return;
        }
        boolean completedSuccesfully = true;
        while (!queryStatus().equals(StatusEnum.DONE)){
            if (queryStatus().equals(StatusEnum.DATAERROR) || queryStatus().equals(StatusEnum.INTERRUPTED)){
                completedSuccesfully = false;
                break;
            }
        }
        
        if (completedSuccesfully){
            decompressedData = decoder.getDecodedData();
            writeToFile();
        }
        
    }
    
    /**
     * Writes the data to file, handles IOException.
     */
    private void writeToFile(){
        try {
            fileName = fileName.substring(0, fileName.length()-5);
            FileIO.writeToFile(decompressedData, fileName);
        } catch (IOException ex) {
            
        }
    }
    
}