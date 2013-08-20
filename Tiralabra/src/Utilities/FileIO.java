/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

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
    
    public static void writeToFile(byte[] data, URI uriString, String catenationString) throws IOException{
        String newFileName = uriString+catenationString;
        Path pathToNewFile = Paths.get(newFileName);
        Files.write(pathToNewFile, data, StandardOpenOption.CREATE);
    }
    
    public static byte[] readFromFile(URI uriString) throws IOException{
        Path pathToFile = Paths.get(uriString);
        byte[] data = Files.readAllBytes(pathToFile);
        return data;
    }
    
}
