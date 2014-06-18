package ui;

import hakualgoritmit.AStar;
import heuristiikat.Dijkstra;
import heuristiikat.Euklidinen;
import heuristiikat.Heuristiikka;
import heuristiikat.Manhattan;
import java.util.Scanner;
import tietorakenteet.*;

/**
 *
 */
public class TekstiUI {
    
    private Scanner scanner = new Scanner(System.in);

    private AStar astar;
    
    private Heuristiikka heuristiikka;
    
    private Alue alue;
    
    public TekstiUI() {
    }
    
    public void suorita() {
//        alkuvalikko();
//        
//        String syote = scanner.nextLine();
        String syote;
        
        while (true) {
            alkuvalikko();
            syote = scanner.nextLine();
            if (syote.equalsIgnoreCase("Q")) {
                lopetus();
                break;
            } else if (syote.equalsIgnoreCase("1")) {
                kartanValinta();
            } else if (syote.equalsIgnoreCase("2")) {
                heuristiikanValinta();
            } else if (syote.equalsIgnoreCase("3")) {
                suoritaHaku();
            } else if (syote.equalsIgnoreCase("4")) {
                tulostaHakutulos();
            } else {
                System.out.println("Virheellinen komento! Q lopettaa.");
            }
            
    
        }
    }
    
    
    
    
 
    

    
    
    
    private void alkuvalikko() {
        tulostaValitut();
        System.out.println("1 = Valitse kartta-alue");
        System.out.println("2 = Valitse heuristiikka");
        System.out.println("3 = Aja haku");
        System.out.println("4 = Tulosta haun tulos");
        System.out.println("Q = Lopetus");
    }

    private void lopetus() {
        System.out.println("Lopetetaan.");
    }

    private void suoritaHaku() {
        System.out.println("Tähän tulee varsinaisen haun suoritus...");
    }

    private void kartanValinta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void heuristiikanValinta() {
        System.out.println("Valitse käytettävä heuristiikka:");
        System.out.println("1. Dijkstra");
        System.out.println("2. Manhattan");
        System.out.println("3. Euklidinen");
        String syote = scanner.nextLine();
        System.out.println("mennään looppiin...");
        boolean loop = true;
        while (loop) {
            if (syote.equalsIgnoreCase("1")) {
                heuristiikka = new Dijkstra();
                System.out.println("Asetettu heuristiikaksi Dijkstra");
                loop = false;
            } else if (syote.equalsIgnoreCase("2")) {
                heuristiikka = new Manhattan();
                System.out.println("Asetettu heuristiikaksi Manhattan.");
                loop = false;
                return;
            } else if (syote.equalsIgnoreCase("3")) {
                heuristiikka = new Euklidinen();
                System.out.println("Asetettu heuristiikaksi Euklidinen.");
                loop = false;
            } else
                System.out.println("Virheellinen valinta");
        }
        
        
    }

    private void tulostaHakutulos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void tulostaValitut() {
        System.out.println("Valittuna tällä hetkellä:");
        if (heuristiikka != null)
            System.out.println("  Heuristiikka: " + heuristiikka.getClass().getName());
        else
            System.out.println("  Heuristiikkaa ei valittu vielä.");
        
        if (alue != null)
            tulostaAlueentiedot();
        else
            System.out.println("  Aluetta ei vielä valittu");
        
        System.out.println("---");
    }

    private void tulostaAlueentiedot() {
        System.out.println("  Alue: Alue on valittu, TODO: parempi muotoilu");
    }
    
}
