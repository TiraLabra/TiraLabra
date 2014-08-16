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
        String start = "";
        String end = "";
 
        
        System.out.println("Valitse lähtöpiste ja määränpää antamalla kohteen numero.");
        grid.initialize();
        grid.print();
        
        while (!grid.validKeys(start, end)) {
            System.out.println("Lähtöpaikka:");
            start = input.nextLine();

            System.out.println("Päämäärä:");
            end = input.nextLine();
        }
        
        Dijkstra d = new Dijkstra(start, end, grid);
        
        d.initialize();
        d.route();
        
        System.exit(0);
    }
    
}