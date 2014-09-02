/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filehandling;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author joonaskylliainen
 */
public class Writer {
    
    private String input;
    private String outputFile;
    
    public Writer() {
    }
    
    public void write(String input, String aOutputFileName){
       
        byte[] aInput = stringToByteChar(divide(input));
    
        try {
            OutputStream output = null;
            try {
                output = new BufferedOutputStream(new FileOutputStream(aOutputFileName));
                output.write(aInput);
            }
            finally {
                output.close();
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("ei löydy");
        }
        catch(IOException ex){

        }
    }
    
    public byte[] stringToByteChar(ArrayList<String> list) {
        byte[] b = new byte[list.size()];
        for(int i = 0; i<list.size();i++) {
            b[i] = (byte) Integer.parseInt(list.get(i),2);
        }
        return b;
    }
    public ArrayList<String> divide(String s) {
        ArrayList<String> list = new ArrayList<String>();
        int j = 6;
        for (int i = 0; i< s.length(); i += 7) { 
            if(j < s.length()) {
                list.add(s.substring(i, j));
            }
            else {
                list.add(s.substring(i));
            }
            j += 7;
        }
        return list;
    }
    
    
    
}
