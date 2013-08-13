package com.mycompany.tiralabra_maven.player;

import com.mycompany.tiralabra_maven.data_structures.Stack;
import com.mycompany.tiralabra_maven.data_structures.StackNode;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Joel Nummelin
 */
public class FileHandler {
    private File file;

    public FileHandler(File file) {
        this.file = file;
    }
    
    public void saveLine(int move, int result) throws IOException{
        try (FileWriter fw = new FileWriter(file)) {
            fw.append(Integer.toString(move) + "" + Integer.toString(result));
        }
    }
    
    public Stack getLines() throws IOException{
        Scanner sc = new Scanner(file);
        Stack stack = new Stack();
        while (sc.hasNextLine()){
            String string = sc.nextLine();
            int move = Character.digit(string.charAt(0), 10);
            int result = Character.digit(string.charAt(1), 10);
            stack.put(new StackNode(move, result));
        }
        return stack;
    }
}
