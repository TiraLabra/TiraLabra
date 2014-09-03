

package tiralabra_maven;
import java.util.Scanner;
/**
* Käyttöliittymä-luokka.
* 
* <P> Luokka vastaa käyttäjän syötteiden käsittelystä ja ohjelman toiminnan hallinnoinnista.
* 
* 
 */
public class Kayttoliittyma {

    /**
     * Scanner käyttäjän tekstien lukemista varten.
     */
    private Scanner scan = new Scanner(System.in);
    /**
     * Käyttäjän kirjoittama käsky tekstinä.
     */
    private String kasky;
    /**
     * Komennonkäsittelijä analysoi käyttäjän syötteen ja ohjaa ohjelman toimintaa.
     */
    private KomennonKasittelija komennonkasittelija = new KomennonKasittelija(scan, this);
    
    /**
     * Boolean kaynnissa-muuttuja kertoo onko ohjelma käynnissä.
     */
    private boolean kaynnissa = true;
    
    
    /** Run-metodi.
     * 
     * <P> Metodi kuuntelee käyttäjän komentopaneeliin antamia käskyjä, ja toimittaa ne 
     * KomennonKäsittelijä-luokan analysoitaviksi. Metodin suorituksen päättyessä myös ohjleman
     * suoritus päättyy.
     */
    public void run() {
        tulostaAloitusTeksti();
        tulostaOhje();
        while (kaynnissa) {
            System.out.print(">> ");
            try {
                kasky = scan.nextLine();
            }
            catch (Exception e) {
                if (e.getMessage()!=null) {
                    System.out.println(e.getMessage());
                }
                else System.out.println("Anna kunnollinen komento");
            }
            
            try {
                komennonkasittelija.suoritaKomento(kasky);
            }
            
            catch (Exception e) {
                System.out.println("Komento ei ollut kelvollinen");
            }
            
        }
    }
    
    /** tulostaAloitusTeksti.
     * <P> Metodi tulostaa ohjelman esittelytekstin ruudulle.
     */
    public void tulostaAloitusTeksti() {
        System.out.println("Tervetuloa matriisilaskuriohjelmaan");
     
    }
    
    /** Quit.
     * Metodi lopettaa ohjelman suorituksen.
     * 
     */
    public void quit() {
        kaynnissa = false;
    }
    
    /**
     * Tulostaohje.
     * Metodi tulostaa ruudulle ohjeen, joka kertoo miten komentolistan saa näkyville.
     */
    public void tulostaOhje() {
        System.out.println("Kirjoita komennot nähdäksesi kaikki komennot");

    }
    
    /**
     * Tulostakomennot.
     * Metodi tulostaa kaikki ohjelmassa toimivat komennot ruudulle.
     */
    public void tulostaKomennot() {
        
        System.out.println("matriisi [ulottuvuudet muodossa nxm] [matriisin nimi]");
        System.out.println("determinantti [matriisin nimi]");
        System.out.println("kerro [matriisin nimi] [matriisin nimi] [tulosmatriisin nimi]");
        System.out.println("kaanna [matriisin nimi] [tulosmatriisin nimi]");
        System.out.println("vahennyslasku [matriisin nimi] [matriisin nimi] [tulosmatriisin nimi]");
        System.out.println("yhteenlasku [matriisin nimi] [matriisin nimi] [tulosmatriisin nimi]");
        System.out.println("skalaarimonikerta [skalaari] [matriisi]");
        System.out.println("tiedosto [tulosmatriisin nimi] [tiedostopolku]");
        System.out.println("sparse [matriisinnimi]");
        System.out.println("rref [matriisinnimi]");
        System.out.println("lista");
        
        System.out.println("komennot");
        System.out.println("quit");
    }
    
    
    /**
     * Kerää matriisin alkiot. Metodi kerää käyttäjältä matriisin luvut.
     * @param m Matriisin rivien määrä
     * @param n Matriisin sarakkeiden määrä
     * @return Metodi palauttaa taulukon, jonka arvot ovat double-tyyppisiä
     * 
     */
    public double[][] keraaMatriisinLuvut(int m, int n) {
        String[] arvot;
        double[][] palautettava = new double[m][n];
        for (int i = 0; i < n; i++) {
               
               while (true) {
                   try {
                        System.out.println("Syötä sarakkeen " + i + " alkiot välimerkillä eroteltuna");
                        kasky = scan.nextLine();
                        if (kasky.equals("quit")) {
                            this.quit();
                            return null;
                        }
                        arvot = kasky.split(" ");
               
               
                        for (int k = 0; k < m; k++) {
                            palautettava[k][i] = Double.parseDouble(arvot[k]);
                        }
                        break;
                    }
                   
                   catch (Exception e) {
                       System.out.println("Antamasi syöte ei ollut kelvollinen");
                   }
               }
        
        }
    return palautettava;
    
    }
    
    
}
