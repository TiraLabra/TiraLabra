package superpakkaussofta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class for reading and writing files byte by byte
 *
 * @author Jouko
 */
public class FileIO {
    
    public byte[] read(String path) throws IOException{
        Path p = Paths.get(path);
        
        return Files.readAllBytes(p);
    }
    public void write() throws IOException{
        
    }
}
