
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
//        
        int width = 3;
//        
        fillTable(randomData, width, table);
        
////        for (int i = 0; i < table.size(); i++) {
////            System.out.println(table.fetch(i).hashCode());
////            
////        }
//        
//        int[][] hashes = new int[table.size()][2];
//        hashes = introduceHashes(table, hashes);
//        System.out.println(table.size());
//        
//        int hashCount = 0;
//        
//        for (int i = 0; i < hashes.length; i++) {
//            System.out.println(hashes[i][0]+" "+hashes[i][1]);
//            if (hashes[i][0]==0){
//                break;
//            }
//        }
//        System.out.println("Count: "+hashCount);
    }
    
    public static void initializeTable(int[][] table){
        for (int i = 0; i < table.length; i++) {
            table[i][0] = Integer.MIN_VALUE;
        }
    }
    
    private static int tableContains(int hash, int[][] table){
        for (int i = 0; i < table.length; i++) {
            if (table[i][0] == hash){
                return i;
            }
        }
        return Integer.MIN_VALUE;
    }
    
    private static int getIndex(int[][] table){
        for (int i = 0; i < table.length; i++) {
            if (table[i][0] == Integer.MIN_VALUE){
                return i;
            }
        }
        return 0;
    }

    private static void fillTable(byte[] randomData, int width, MultiByteHashedTable table) {
        for (int i = 0; i < randomData.length; i+=width) {
            
            if (i+width<randomData.length){
                MultiByte mb = new MultiByte(width);
                for (int j = 0; j < width; j++) {
                    mb.addData(randomData[i+j]);
                }
                table.put(mb);
            }
            
        }
    }

    private static int[][] introduceHashes(MultiByteTable table, int[][] hashes) {
        int tableIndex = 0;
        
        for (int i = 0; i < table.size(); i++) {
            int hashCode = table.fetch(i).hashCode();
            int tablePosition = tableContains(hashCode, hashes);
            if (tablePosition != Integer.MIN_VALUE){
                hashes[tablePosition][1]++;
            } else {
                hashes[tableIndex][0] = hashCode;
                hashes[tableIndex][1] = 1;
                tableIndex++;
            }
            
        }
        return hashes;
    }
}
