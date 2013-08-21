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
public class Compressor implements Runnable {
    
    private MultiByteEncoder encoder;
    private byte[] encodedData;
    private Path pathToDataToBeCompressed;
    private int byteWidth;
;    
    public Compressor(Path pathToFile, int byteWidth){
        this.pathToDataToBeCompressed = pathToFile;
    }
    
    private void compress() throws Exception{
        byte[] dataToCompress = FileIO.readFromFile(pathToDataToBeCompressed);
        encoder = new MultiByteEncoder(dataToCompress, byteWidth);
        Thread encoderThread = new Thread(encoder);
        encoderThread.start();
    }
    
    public StatusEnum queryStatus(){
        return encoder.getStatus();
    }

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
//            FileIO.writeToFile(encodedData, null, null);
        }
    }
    
    
    
}
