/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Encoding;

import Utilities.FileIO;
import java.nio.file.Path;

/**
 *
 * @author virta
 */
public class Decompressor implements Runnable {
    
    private Path pathToCompressedData;
    private byte[] decompressedData;
    private MultiByteDecoder decoder;
    
    public Decompressor(Path path){
        this.pathToCompressedData = path;
    }
    
    private void decompress() throws Exception {
        byte[] compressedData = FileIO.readFromFile(pathToCompressedData);
        decoder = new MultiByteDecoder(compressedData);
        Thread decoderThread = new Thread(decoder);
        decoderThread.start();
    }
    
    public StatusEnum queryStatus(){
        return decoder.getStatus();
    }

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
//            FileIO.writeToFile(decompressedData, null, null);
        }
        
    }
    
}