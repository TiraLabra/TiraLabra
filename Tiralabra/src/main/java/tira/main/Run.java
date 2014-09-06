package tira.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import tira.astar.Astar;
import tira.dijkstra.Dijkstra;
import tira.list.LinkedList;
import tira.utils.Location;
import tira.utils.Mapper;

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

        File map = new File("kartta.csv");
        Scanner reader = new Scanner(map);
        Scanner input = new Scanner(System.in);
        Mapper grid = new Mapper(reader);
        String start = "";
        String end = "";
        int algoritmi = 0;
        
        /**
         * Alustetaan kartta.
         */        
        grid.initialize();
        LinkedList<Location> mappi = grid.getMap();
        
        /**
         * Käyttäjä valitsee kumalla algoritmilla haluaa hakea reitin.
         */
        try {
            System.out.println("Valitse algoritmi reitin hakuun:\n 1 = A*\n 2 = Dijkstra.");
            algoritmi = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Paina 1 tai 2..");
            System.exit(0);
        }
        
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
            doDijkstra(start, end, mappi);
        }
        if (algoritmi == 1) {
            doAstar(start, end, mappi);
        }    
        
        System.exit(0);
    }
    
    /**
     * Luodaan Astar-olio, alustetaa verkko ja etsitään reitti.
     */
    private static void doAstar(String s, String f, LinkedList m) {
        Astar a = new Astar(s, f, m);
        a.initialize();
        long start = System.nanoTime();
        a.route();
        long end = System.nanoTime();
        long micros = (end - start) / 1000;
        System.out.println("Operaatioon kului aikaa: \n" + micros + "mikrosekuntia");     
        a.print();
    }
     
    /**
     * Luodaan Dijsktra-olio, alustetaan verkko ja etsitään reitti.
     */
    private static void doDijkstra(String s, String f, LinkedList l) {
        Dijkstra d = new Dijkstra(s, f, l);
        d.initialize();
        long start = System.nanoTime();
        d.route();
        long end = System.nanoTime();
        long micros = (end - start) / 1000;
        System.out.println("Operaatioon kului aikaa: \n" + micros + "mikrosekuntia");
        d.print();
    }    
}