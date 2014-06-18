package ui;

import apurakenteet.Kuvalukija;
import apurakenteet.Kuvanayttaja;
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
    
    private String tiedostonimi;
    private int alkurivi;
    private int alkusarake;
    private int loppurivi;
    private int loppusarake;
    
    private Kuvalukija kl;
    
    public TekstiUI() {
    }
    
    public void suorita() {
//        alkuvalikko();
//        
//        String syote = scanner.nextLine();
        String syote;
        
        boolean jatketaan = true;
        while (jatketaan) {
            alkuvalikko();
            syote = scanner.nextLine();
            if (syote.equalsIgnoreCase("Q")) {
                lopetus();
                jatketaan = false;
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
        astar = new AStar(heuristiikka);
        astar.AStarHaku(alue, alue.getnode(alkurivi, alkusarake), alue.getnode(loppurivi,loppusarake));
    }

    private void kartanValinta() {
        System.out.println("Anna halutun tiedoston nimi (oletuksena 100x100.bmp):");
        String syote = scanner.nextLine();
        if (syote.equals("")) {
            tiedostonimi = "100x100.bmp";
            System.out.println("tänne");
        } else {
            tiedostonimi = syote;       // Ei mitään tarkastuksia vielä...
            System.out.println(tiedostonimi);
        }
        kl = new Kuvalukija(tiedostonimi);
        this.alue = new Alue(kl.muodostaAlue(), kl.getKorkeus(), kl.getLeveys());
        
        while (true) {
            System.out.println("Anna alkusolmun rivi:");
            alkurivi = scanner.nextInt();
            System.out.println("Anna alkusolmun sarake:");
            alkusarake = scanner.nextInt();
            System.out.println("Anna loppusolmun rivi:");
            loppurivi = scanner.nextInt();
            System.out.println("Anna loppusolmun sarake:");
            loppusarake = scanner.nextInt();
            if (tarkastaKoordinaatit() ) {
                break;
            }
        }
            
    }

    private void heuristiikanValinta() {
        System.out.println("Valitse käytettävä heuristiikka:");
        System.out.println("1. Dijkstra");
        System.out.println("2. Manhattan");
        System.out.println("3. Euklidinen");
        String syote = scanner.nextLine();
        while (true) {
            if (syote.equalsIgnoreCase("1")) {
                heuristiikka = new Dijkstra();
                System.out.println("Asetettu heuristiikaksi Dijkstra");
                break;
            } else if (syote.equalsIgnoreCase("2")) {
                heuristiikka = new Manhattan();
                System.out.println("Asetettu heuristiikaksi Manhattan.");
                break;
            } else if (syote.equalsIgnoreCase("3")) {
                heuristiikka = new Euklidinen();
                System.out.println("Asetettu heuristiikaksi Euklidinen.");
                break;
            } else
                System.out.println("Virheellinen valinta");
        }
        
        
    }

    private void tulostaHakutulos() {
        System.out.println("Yhteensä " + astar.getAskelia() + " laskenta-askelta.");
        
        ArrayListOma reitti = astar.kerroKuljettuReitti();
        System.out.println("\nKuljettu reitti: ("+ reitti.koko()+ " kpl)");
        //for (Node n : reitti) {
        for (int i = 0; i < reitti.koko(); i++) {
            Node n = (Node)reitti.palautaKohdasta(i);
            System.out.println(n.toString());
            n.toString();
        }
        System.out.println(alue.toString());
        System.out.println(astar.yhteenveto());
        
        System.out.println("-------");
        
        Kuvanayttaja kn = new Kuvanayttaja(kl.getKuva());
        kn.muodostaKuvaanPolku(reitti);
        kn.naytaKuva();
        
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

    //TODO:
    private boolean tarkastaKoordinaatit() {
        return true;
    }
    
}
