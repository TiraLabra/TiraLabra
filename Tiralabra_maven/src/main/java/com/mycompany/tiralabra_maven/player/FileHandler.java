package com.mycompany.tiralabra_maven.player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Joel Nummelin
 */
public class FileHandler {
    private File file;

    public FileHandler(File file) {
        this.file = file;
    }
    
    public void saveLine(int rockPaperScissors, int result) throws IOException{
        try (FileWriter fw = new FileWriter(file)) {
            fw.append(Integer.toString(rockPaperScissors) + ":" + Integer.toString(result));
        }
    }
    
    public int[][] getLines(){
        return new int[1][1];
    }
}
