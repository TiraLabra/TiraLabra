package tira.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import tira.astar.Astar;
import tira.dijkstra.Dijkstra;

/**
 *
 * @author joonaslaakkonen
 * Pääohjelman runko, joka ohjailee ohjelma toimintaa käyttäjän syötteen perusteella. Luokka myös luo suurimman osan
 * ohjelman muista olioista.
 */
public class Run {  
    
    /**
     * 
     * @param args
     * @throws FileNotFoundException
     * Pääohjelma, jossa ensin luetaan karttatiedosto ja sen jälkeen käyttäjä ohjaa toimintaa.
     */
    public static void main( String[] args ) throws FileNotFoundException{

        File map = new File("map.txt");
        Scanner reader = new Scanner(map);
        Scanner input = new Scanner(System.in);
        Mapper grid = new Mapper(reader);
        String start = "";
        String end = "";
        int algoritmi;
        
        /**
         * Alustetaan HashMap karttatiedostosta.
         */        
        grid.initialize();
        HashMap<String, ArrayList<Target>> graph = grid.getGrid();
        
        /**
         * Käyttäjä valitsee kumalla algoritmilla haluaa hakea reitin.
         */
        System.out.println("Valitse algoritmi reitin hakuun:\n 1 = A*\n 2 = Dijkstra.");
        algoritmi = Integer.parseInt(input.nextLine());
        
        /**
         * Käyttäjä valitsee alkupisteen ja loppupisteen.
         * Tarkistetaan, että käyttäjä ei sekoile vaan valitsee olemassaolevat kohteet.
         */       
        System.out.println("Valitse lähtöpiste ja määränpää kirjoittamalla kohde.");
        grid.print();
        while (!grid.validKeys(start, end)) {
           System.out.println("Lähtöpaikka:");
           start = input.nextLine();

           System.out.println("Päämäärä:");
           end = input.nextLine();
        }
        
        if (algoritmi == 2) {
            doDijkstra(start, end, graph);
        }
        if (algoritmi == 1) {
            doAstar(start, end, graph);
        }    
        
        System.exit(0);
    }
    
    /**
     * Luodaan Astar-olio, alustetaa verkko ja etsitään reitti.
     */
    private static void doAstar(String s, String f, HashMap m) {
        Astar a = new Astar(s, f, m);
        a.initialize();
        a.route();
        a.print();
    }
     
    /**
     * Luodaan Dijsktra-olio, alustetaan verkko ja etsitään reitti.
     */
    private static void doDijkstra(String s, String f, HashMap m) {
        Dijkstra d = new Dijkstra(s, f, m);
        d.initialize();
        d.route();
        d.print();
    }    
}