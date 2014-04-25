package com.mycompany.tiralabra_maven.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileParser {

    private final Parser parser;
    private String ascii;

    public FileParser(Parser parser) {
        this.parser = parser;
    }

    public FileParser(Parser parser, File filename) throws IOException {
        this.parser = parser;
        ascii = new String(Files.readAllBytes(filename.toPath()));
    }
    
    public void readFile(File filename) throws IOException{
        ascii = new String(Files.readAllBytes(filename.toPath()));
    }

    public int[][] parse() {
        return parser.parse(ascii);
    }

}
