package tira.main;

import tira.dijkstra.Dijkstra;
import tira.astar.Astar;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
        int algoritmi = 0;
        
        /**
         * Alustetaan HashMap karttatiedostosta.
         */
        
        grid.initialize();
        
        /**
         * Käyttäjä valitsee kumalla algoritmilla haluaa hakea reitin.
         */
        System.out.println("Valitse algoritmi reitin hakuun:\n 1 = A*\n 2 = Dijkstra.");
        algoritmi = Integer.parseInt(input.nextLine());
        
        if (algoritmi == 2) {
            System.out.println("Valitse lähtöpiste ja määränpää kirjoittamalla kohde.");
            grid.print();
            
            /**
             * Tarkistetaan, että käyttäjä ei sekoile vaan valitsee olemassaolevat kohteet.
             */
            
            while (!grid.validKeys(start, end)) {
                System.out.println("Lähtöpaikka:");
                start = input.nextLine();

                System.out.println("Päämäärä:");
                end = input.nextLine();
            }
            
            /**
             * Luodaan Dijsktra-olio, alustetaan verkko ja etsitään reitti.
             */
            Dijkstra d = new Dijkstra(start, end, grid);
        
            d.initialize();
            d.route();
            d.print();
        }
        
        /**
         * Astar valittu, dijkstran ja astarin valinnan jälkeiset tapahtumat ovat vielä liikaa copyapastea.
         * Täytyy pilkkoa omiksi private apumetodeiksi.
         */
        if (algoritmi == 1) {
            System.out.println("Valitse lähtöpiste ja määränpää kirjoittamalla kohde.");
            grid.print();
            
            /**
             * Tarkistetaan, että käyttäjä ei sekoile vaan valitsee olemassaolevat kohteet.
             */
            
            while (!grid.validKeys(start, end)) {
                System.out.println("Lähtöpaikka:");
                start = input.nextLine();

                System.out.println("Päämäärä:");
                end = input.nextLine();
            }
            
            /**
             * Etsitään reitti Astarilla, huom ei vielä valmis!
             */
            
            Astar a = new Astar(start, end, grid);
            a.initialize();
            a.test();
        }

        System.exit(0);
    }
    
}