/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Encoding;

import Dictionary.MultiByteHashedTable;
import MultiByteEntities.MultiByte;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author virta
 */
public class MultiByteEncoder implements Runnable {
    
    /**
     * Path to the file.
     */
    private String filePath;
    
    /**
     * A Hashed table for multibyte data.
     */
    private MultiByteHashedTable hashTable;
    
    /**
     * The data read from file.
     */
    private byte[] data;
    
    /**
     * Current bytewidth to use in constructing multibytes.
     */
    private int byteWidth;
    
    /**
     * Given the filepath, extracts the data if file is found and creates a new hashed table for encoding.
     * @param fileP
     * @throws IOException if the file is not found.
     */
    public MultiByteEncoder(String fileP) throws IOException{
        this.filePath = fileP;
        byteWidth = 4;
        readData();
    }
    
    /**
     * Reads data from the specified path and forms a hashed table from files' size data.
     * @throws IOException if the file is not found.
     */
    private void readData() throws IOException{
        Path path = Paths.get(filePath);
        data = Files.readAllBytes(path);
        this.hashTable = new MultiByteHashedTable(data.length / byteWidth);
    }
    
    /**
     * Use with caution, sets the current bytewidth to use and creates a new hashed table matching the width, erases previous hashed table.
     * @param width 
     */
    public void setByteWidth(int width) throws IOException{
        this.byteWidth = width;
        readData();
    }

    /**
     * Since this is a class implementing runnable, one can run several instances of different bytewidths on the same data.
     * The method reads the data from the array and creates multibyte entries for the hashed table accordingly.
     * 
     * UNFINISHED
     */
    @Override
    public void run() {
        
        for (int i = 0; i < data.length; i+=byteWidth) {
            if (i+byteWidth < data.length){
                MultiByte mb = new MultiByte(byteWidth);
                for (int j = 0; j < byteWidth; j++) {
                    mb.addData(data[i+j]);
                }
                hashTable.put(mb);
            }
        }
        
    }
    
}
