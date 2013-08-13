package com.mycompany.tiralabra_maven.player;

import com.mycompany.tiralabra_maven.data_structures.Stack;
import com.mycompany.tiralabra_maven.data_structures.StackNode;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joel Nummelin
 */
public class FileHandler {
    private File file;
    private FileWriter fileWriter;
    

    public FileHandler(File file) throws IOException {
        this.file = file;
        this.fileWriter = new FileWriter(file);
    }
    
    public void saveLine(int move, int result) {
        if (file == null){
            return;
        }
        try {
            fileWriter.append(Integer.toString(move) + Integer.toString(result) + "\n");
            fileWriter.flush();
        } catch (IOException ex) {
            System.out.println("Could not write to: " + file.getPath());
            System.out.println(ex.getMessage());
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Stack getLines() throws IOException{
        if (file == null){
            return new Stack();
        }
        Scanner sc = new Scanner(file);
        Stack stack = new Stack();
        while (sc.hasNextLine()){
            String string = sc.nextLine();
            int move = Character.digit(string.charAt(0), 10);
            int result;
            if (string.charAt(1) == '-'){
                result = -1;
            } else {
                result = Character.digit(string.charAt(1), 10);
            }
            stack.put(new StackNode(move, result));
        }
        return stack;
    }
}
