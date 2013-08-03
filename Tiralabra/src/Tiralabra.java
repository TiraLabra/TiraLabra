
import com.sun.org.apache.xalan.internal.xsltc.dom.BitArray;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
//import sun.security.util.BitArray;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author virta
 */
public class Tiralabra {
    
    public static void main(String[] args) throws IOException{
        
        
        Path path = Paths.get("joku");
        byte[] data = Files.readAllBytes(path);
        
        BitArray bitarray = new BitArray((int) Files.size(path));
        
        for (int i = 0; i < data.length; i++) {
            byte b = data[i];
            
        }
        
        System.out.println(bitarray);
        
        
        
    }
    
}
