

package com.mycompany.tiralabra_maven;
import java.util.Scanner;
/**
 *
 * @author risto
 */
public class Kayttoliittyma {
    
    private Scanner scan = new Scanner(System.in);
    private String kasky;
    private Komento komento;
    private KomennonKasittelija komennonkasittelija = new KomennonKasittelija(scan, this);
    private boolean kaynnissa = true;
    
    public void run() {
        tulostaAloitusTeksti();
        tulostaValikko();
        while (kaynnissa) {
            try {
                kasky = scan.nextLine();
            }
            catch (Exception e) {
                System.out.println("Anna kunnollinen komento");
            }
            
            try {
                komennonkasittelija.suoritaKomento(kasky);
            }
            
            catch (Exception e) {
                System.out.println("Komentoa ei löytynyt");
            }
            
        }
    }
    
    public void tulostaAloitusTeksti() {
        System.out.println("Tervetuloa matriisilaskuriohjelmaan");
     
    }
    
    public void quit() {
        kaynnissa = false;
    }
    
    public void tulostaValikko() {
        System.out.println("Kirjoita komennot nähdäksesi kaikki komennot");

    }
    
    public void tulostaKomennot() {
        
        System.out.println("matriisi [ulottuvuudet muodossa nxm] [matriisin nimi]");
        System.out.println("determinantti [matriisin nimi]");
        System.out.println("kerro [matriisin nimi] [matriisin nimi] [tulosmatriisin nimi]");
        System.out.println("kaanna [matriisin nimi]");
        System.out.println("lista");
        System.out.println("komennot");
        System.out.println("quit");
    }
    
    public Komento tutkiKasky(String kasky) throws Exception {
        if (kasky.equals("matriisi")) {
            return Komento.MATRIISI;
        }
        else throw new IllegalArgumentException();
    }
   
    
    
    
    
}
