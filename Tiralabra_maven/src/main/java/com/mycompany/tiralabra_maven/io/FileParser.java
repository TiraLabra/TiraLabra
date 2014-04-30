package com.mycompany.tiralabra_maven.io;

import com.mycompany.tiralabra_maven.maze.Maze;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author yessergire
 */
public class FileParser {

    private final Parser parser;
    private String ascii;
    private FileInputStream fileReader;

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
        readFile(file);
    }

    /**
     *
     * @param filename
     * @throws IOException
     */
    public void readFile(File filename) throws IOException {
        fileReader = new FileInputStream(filename);
        byte[] bytes = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (fileReader.available() > 0) {
            int read = fileReader.read(bytes);
            if (read < 1024)
                sb.append(new String(bytes, 0, read));
            else 
                sb.append(new String(bytes));
        }
        fileReader.close();
        ascii = sb.toString();
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
        FileParser parser = new FileParser(new AsciiWithTabsParser(), file);
        return parser.parse();
    }
    /**
     * 
     * @param file
     * @param maze
     * @throws IOException 
     */
    public static void saveAsciiWithTabsFile(File file, Maze maze) throws IOException {
        MazePrinter mp = new MazePrinter(maze);
        PrintWriter pw = new PrintWriter(file);
        pw.print(mp);
        pw.flush();
        pw.close();
    }

}
