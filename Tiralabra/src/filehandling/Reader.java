/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filehandling;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author joonaskylliainen
 */
public class Reader {
    
    public String read(String aInputFileName){
        System.out.println("Reading in binary file named : " + aInputFileName);
        File file = new File(aInputFileName);
        System.out.println("File size: " + file.length());
        byte[] result = new byte[(int)file.length()];
        try {
          InputStream input = null;
          try {
            int totalBytesRead = 0;
            input = new BufferedInputStream(new FileInputStream(file));
            while(totalBytesRead < result.length){
              int bytesRemaining = result.length - totalBytesRead;
              //input.read() returns -1, 0, or more :
              int bytesRead = input.read(result, totalBytesRead, bytesRemaining); 
              if (bytesRead > 0){
                totalBytesRead = totalBytesRead + bytesRead;
              }
            }
            /*
             the above style is a bit tricky: it places bytes into the 'result' array; 
             'result' is an output parameter;
             the while loop usually has a single iteration only.
            */
            System.out.println("Num bytes read: " + totalBytesRead);
          }
          finally {
            System.out.println("Closing input stream.");
            input.close();
          }
        }
        catch (FileNotFoundException ex) {
          System.out.println("File not found.");
        }
        catch (IOException ex) {
          System.out.println(ex);
        }
        
        return byteToString(result);
    }
    
    public String readInput() {
        String lause = "";
        try {
            Scanner lukija = new Scanner(System.in);
            File file = new File(lukija.nextLine());
            Scanner in = new Scanner(file);

            while(in.hasNext()) {
                lause += in.next(lause);
            }           
            in.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return lause;
    }
    
    public String byteToString(byte[] list) {
        String lause = "";
        for (byte b : list) {
            lause += Integer.toBinaryString(b);
        }
        return lause;
    }
    
}
