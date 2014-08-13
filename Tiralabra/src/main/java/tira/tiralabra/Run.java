package tira.tiralabra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author joonaslaakkonen
 */
public class Run {
    
    
    public static void main( String[] args ) throws FileNotFoundException{

        File map = new File("map.txt");
        Scanner reader = new Scanner(map);
        Scanner input = new Scanner(System.in);
        Mapper grid = new Mapper(reader);
 
        
        System.out.println("Valitse lähtöpiste ja määränpää antamalla kohteen numero.");
        grid.initialize();
        grid.print();
        
        System.out.println("Lähtöpaikka:");
        int start = input.nextInt();
        
        System.out.println("Päämäärä:");
        int end = input.nextInt();
    }
    
}