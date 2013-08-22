/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author virta
 */
public class FileIO {
    
    /**
     * Writes the bytes into a new file with the catenated string as filename.
     * @param data
     * @param uriString
     * @param catenationString
     * @throws IOException 
     */
    public static void writeToFile(byte[] data, String String) throws IOException{
        File file = new File(String);
        Path path = file.toPath();
        Files.write(path, data, StandardOpenOption.CREATE_NEW);
    }
    
    /**
     * Reads bytes from the specified by the uri-string.
     * @param uriString
     * @return
     * @throws IOException 
     */
    public static byte[] readFromFile(String pathString) throws IOException{
        File file = new File(pathString);
        Path path = file.toPath();
        byte[] data = Files.readAllBytes(path);
        return data;
    }
    
}
