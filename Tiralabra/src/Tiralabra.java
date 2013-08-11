
/**
 *
 * @author virta
 */
import Dictionary.MultiByteHashedTable;
import Dictionary.SinlgeMultiByteIndexing;
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


        

        int width = 6;
        
        MultiByteHashedTable table = new MultiByteHashedTable(randomData.length / width);

        fillTable(randomData, width, table);

        int[] stats = table.getStats();

        System.out.println("Keys: " + stats[0] + " total: " + stats[1] + " avg coll: " + stats[1] / stats[0] + " max coll: " + stats[2]);

        int[][] refs = table.getReferences();
        int refcount = 0;
        int primary =0;
        int secondary = 0;
        for (int i = 0; i < refs.length; i++) {
            if (refs[i][0] != 0) {
                for (int j = 0; j < refs[i].length; j++) {
                    if (refs[i][j]>3) {
                        secondary++;
                        refcount += refs[i][j];
                    }
                    if (refs[i][j] == 0) {
                        break;
                    }
                    primary++;
                }

            }
        }
        
        System.out.println("avg refs: "+refcount/secondary);
        System.out.println(secondary);
        System.out.println(primary);
    }

    private static void fillTable(byte[] randomData, int width, MultiByteHashedTable table) {

        for (int i = 0; i < randomData.length; i+=width) {

            if (i + width< randomData.length) {
                MultiByte mb = new MultiByte(width);
                for (int j = 0; j < width; j++) {
                    mb.addData(randomData[i + j]);
                }
                table.put(mb);
            }

        }
    }
}
