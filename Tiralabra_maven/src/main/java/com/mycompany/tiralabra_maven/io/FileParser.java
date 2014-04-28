package com.mycompany.tiralabra_maven.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author yessergire
 */
public class FileParser {

    private final Parser parser;
    private String ascii;

    /**
     *
     * @param parser
     */
    public FileParser(Parser parser) {
        this.parser = parser;
    }

    /**
     *
     * @param parser
     * @param file
     * @throws IOException
     */
    public FileParser(Parser parser, File file) throws IOException {
        this.parser = parser;
        ascii = new String(Files.readAllBytes(file.toPath()));
    }
    
    /**
     *
     * @param filename
     * @throws IOException
     */
    public void readFile(File filename) throws IOException{
        ascii = new String(Files.readAllBytes(filename.toPath()));
    }

    /**
     *
     * @return
     */
    public int[][] parse() {
        return parser.parse(ascii);
    }
    
    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static int[][] parseAsciiWithTabsFile(File file) throws IOException {
        return new AsciiWithTabsParser().parse(new String(Files.readAllBytes(file.toPath())));
    }

}
