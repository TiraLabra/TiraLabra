package com.mycompany.tiralabra_maven.player;

import com.mycompany.tiralabra_maven.data_structures.Node;
import com.mycompany.tiralabra_maven.data_structures.Stack;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Takes care of saving and loading. 
 * @author Joel Nummelin
 */
public class FileHandler {
    private FileWriter fileWriter;
    private Scanner scanner;

    
    public FileHandler(File file) throws IOException {
        this.fileWriter = new FileWriter(file, true);
        this.scanner = new Scanner(file);
    }
    
    /**
     * Saves one action. 
     * @param move
     * @param result 
     */
    public void saveLine(int move, int result) {
        try {
            fileWriter.append(Integer.toString(move) + Integer.toString(result) + "\n");
            fileWriter.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Returns all content of the file in a form of stack. 
     * @return stack
     * @throws IOException 
     */
    public Stack getLines() throws IOException{
        Stack stack = new Stack();
        while (scanner.hasNextLine()){
            String string = scanner.nextLine();
            int move = Character.digit(string.charAt(0), 10);
            int result;
            if (string.charAt(1) == '-'){
                result = -1;
            } else {
                result = Character.digit(string.charAt(1), 10);
            }
            stack.put(new Node(move, result));
        }
        return stack;
    }
}
