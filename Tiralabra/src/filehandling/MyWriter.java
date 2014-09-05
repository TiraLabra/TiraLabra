/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filehandling;

import datastructures.Paketti;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author joonaskylliainen
 */
public class MyWriter {
    
    private String input;
    private String outputFile;
    
    public MyWriter() {
    }
    
    public void write(Paketti paketti, String aOutputFileName){
        
        System.out.println("Writing...");
        
        byte[] tree = paketti.getByteTree();
        byte[] text = stringToByteChar(divide(paketti.getText()));
        byte[] aInput = combine(tree,text);
    
        try {
            OutputStream output = null;
            try {
                output = new BufferedOutputStream(new FileOutputStream(aOutputFileName));
                output.write(aInput);
            }
            finally {
                System.out.println("Closing...");
                output.close();
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("Ei löydy");
        }
        catch(IOException ex){

        }
    }
    
    public void writeAnswerToFile(String text) {
        Scanner in = new Scanner(System.in);
        System.out.println("Anna tiedostolle nimi");
        String file = in.nextLine();
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                  new FileOutputStream(file)));
            writer.write(text);
        } catch (IOException ex) {
            System.out.println("Something went wrong");
        } finally {
           try {writer.close();} catch (Exception ex) {}
        }
    }
    
    public byte[] stringToByteChar(ArrayList<String> list) {
        byte[] b = new byte[list.size()];
        for(int i = 0; i<list.size();i++) {
            String temp = "1";
            temp += list.get(i);
            b[i] = (byte) Integer.parseInt(temp,2);
        }
        return b;
    }
    public ArrayList<String> divide(String s) {
        ArrayList<String> list = new ArrayList<String>();
        int j = 6;
        for (int i = 0; i< s.length(); i += 6) { 
            if(j < s.length()) {
                list.add(s.substring(i, j));
            }
            else {
                list.add(s.substring(i));
            }
            j += 6;
        }
        return list;
    }
    
    public byte[] combine(byte[] tree, byte[] text) {
        byte[] aInput = new byte[tree.length + text.length];
        System.arraycopy(tree, 0, aInput, 0, tree.length);
        System.arraycopy(text, 0, aInput, tree.length, text.length);
        return aInput;
    }
    
    
}
