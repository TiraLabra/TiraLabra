/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package juhor.tiralabra.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author JuhoRim
 */
public class FileManager {
    private FileWriter writer;
    private Scanner fileReader;
    
    public FileManager(){
        
    }
    
    public double[][] readSamplesFromFile(File f) throws FileNotFoundException{
        double[][] newArray;
        fileReader = new Scanner(f);
        
        while(fileReader.hasNextLine()){
            
        }
        return null;
    }
    
    private int countLines(Scanner fileReader){
        
        return 0;
    }
    
    private double[] getLineAsArray(String s){
        return null;
    }
    
    private String getArrayAsString(double[] array){
        String s = "";
        for(int i =0; i< array.length; i++){
            s = s + " " + array[i];
        }
        return s + "\n";
    }
    
    public void writeToFile(double[][] samples, double[][] expected, File f) throws IOException{
        writer = new FileWriter(f);
        
        for(int i = 0; i < samples.length; i++){
            writeArrayToFile(samples[i], writer);
            writeArrayToFile(expected[i], writer);
        }
    }
    
    public void writeArrayToFile(double[] array, FileWriter writer) throws IOException{
        writer.write(getArrayAsString(array));
    }
}
