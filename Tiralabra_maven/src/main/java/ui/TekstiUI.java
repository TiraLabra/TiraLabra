package ui;

import apurakenteet.*;
import hakualgoritmit.AStar;
import heuristiikat.*;
import java.util.Scanner;
import tietorakenteet.*;

/**
 * Yksinkertainen tekstikäyttöliittymä.
 * Ei ole tarkoitettu kovin massiiviseen käyttöön, tai toteutettu käytettävyyttä miettien.
 * Ydintarkoitus on vain mahdollistaa AStar-hakujen tekeminen jollain käyttöliittymällä ilman koodin muokkaamista.
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
    
    private boolean hakuTehty;
    
    private static final String oletustiedosto = "100x100.bmp";
    
    private Kuvalukija kl;
    
    public TekstiUI() {
        // Oletusheuristiikaksi manhattan
        heuristiikka = new Manhattan();
        
        //Oletusalueeksi jotain pohjalle:
        kl = new Kuvalukija(oletustiedosto);
        alue = new Alue(kl.muodostaAlue(), kl.getKorkeus(), kl.getLeveys());
        alkurivi = alkusarake = 0;
        loppurivi = loppusarake = 88;
        hakuTehty = false;
    }
    
    public void suorita() {

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

    private void kartanValinta() {
        System.out.println("Anna halutun tiedoston nimi (oletuksena 100x100.bmp):");
        String syote = scanner.nextLine();
        if (syote.equals("")) {
            tiedostonimi = "100x100.bmp";
        } else {
            tiedostonimi = syote;       // Ei mitään tarkastuksia vielä...
            System.out.println(tiedostonimi);
        }
        kl = new Kuvalukija(tiedostonimi);
        this.alue = new Alue(kl.muodostaAlue(), kl.getKorkeus(), kl.getLeveys());
        System.out.println("Alueen mitat: " + kl.getKorkeus() + "x" + kl.getLeveys());
        
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

    private void suoritaHaku() {
        if (alue != null && heuristiikka != null) {
            astar = new AStar(heuristiikka);
            hakuTehty = astar.AStarHaku(alue, alue.getnode(alkurivi, alkusarake), alue.getnode(loppurivi,loppusarake));
        } else
            System.out.println("Hakua ei voida suorittaa, tarkista että hakualue ja heuristiikka on asetettu!");
        
    }

    private void tulostaHakutulos() {
        if (hakuTehty) {
            System.out.println("Yhteensä " + astar.getAskelia() + " laskenta-askelta.");

            ArrayListOma reitti = astar.kerroKuljettuReitti();
            System.out.println("\nKuljettu reitti: ("+ reitti.koko()+ " kpl)");
            //for (Node n : reitti) {
            for (int i = 0; i < reitti.koko(); i++) {
                Node n = (Node)reitti.palautaKohdasta(i);
                System.out.println(i + ": " + n.toString());
                n.toString();
            }
            System.out.println(alue.toString());
            System.out.println(astar.yhteenveto());

            System.out.println("-------");

            Kuvanayttaja kn = new Kuvanayttaja(kl.getKuva());
            kn.muodostaKuvaanPolku(reitti);
            kn.naytaKuva();
        } else {
            System.out.println("Suorita ensin haku ennen reitin tulostamista!");
        }
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
            System.out.println("  Aluetta ei vielä valittu.");
        
        if (!hakuTehty) {
            System.out.println("  Hakua ei ole vielä suoritettu.");
        }
        System.out.println("---");
    }

    private void tulostaAlueentiedot() {
        System.out.println("  Alue: " + alue.getKorkeus() + "x" + alue.getLeveys());
        System.out.println("    alku: " + alkurivi+"x"+alkusarake+", loppu: " + loppurivi+"x"+loppusarake);
    }

    /**
     * Metodirunko jos myöhemmin halutaan tarkastaa koordinaatit.
     * Ei ole toteutettu vielä varsinaista tarkastusta, palauttaa aina true.
     * @return 
     */
    private boolean tarkastaKoordinaatit() {
        return true;
    }
    
}
