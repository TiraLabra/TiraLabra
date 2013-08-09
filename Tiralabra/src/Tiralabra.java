
/**
 *
 * @author virta
 */
import Dictionary.MultiByteHashedTable;
import Dictionary.MultiByteTable;
import MultiByteEntities.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;

public class Tiralabra {

    public static void main(String[] args) throws IOException {

        
        Path path = Paths.get("joku");
        byte[] randomData = Files.readAllBytes(path);
        
        
        MultiByteHashedTable table = new MultiByteHashedTable(randomData.length);
        
        int width = 16;
        
        fillTable(randomData, width, table);

        int[] stats = table.getStats();
        
        System.out.println("Keys: "+stats[0]+" total: "+stats[1]+" avg coll: "+stats[1]/stats[0]+ " max coll: "+stats[2]);
        
    }
    
    private static void fillTable(byte[] randomData, int width, MultiByteHashedTable table) {

        for (int i = 0; i < randomData.length; i++) {
            
            if (i+width-1<randomData.length){
                MultiByte mb = new MultiByte(width);
                for (int j = 0; j < width; j++) {
                    mb.addData(randomData[i+j]);
                }
                table.put(mb);
            }
            
        }
    }

}
