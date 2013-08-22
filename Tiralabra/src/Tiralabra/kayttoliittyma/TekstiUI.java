package Tiralabra.kayttoliittyma;

import Tiralabra.domain.*;
import Tiralabra.vertailija.Vertailija;
import java.util.Scanner;

/** Tekstikäyttöliittymä testausta ja perustoimivuutta varten.
 *
 * @author Pia Pakarinen
 */

public class TekstiUI {
    
    /** Lukee käyttäjän antamat komennot.
     * 
     */
    private Scanner luk;
    
    /**
     * Syötteen koko.
     */
    private int syote;
    
    /** Käynnistää tekstikäyttöliittymän.
     * 
     */
    public void kaynnista(){
       syote = 100;
       Puu puu;
       luk = new Scanner(System.in);
       System.out.print("Puuvertailija!");
        while (true) {
            System.out.println("\nValitse toiminto: \n"
                    + "1. Ohje \n"
                    + "2. Valitse syötteen koko\n"
                    + "3. Vertaile\n"
                    + "Muu: Lopeta\n");
            
            int komento = 0;
            try {
                komento = luk.nextInt();
            } catch (Exception e) {
                break;
            }
            
            if (komento == 1) {
                tulostaOhje();
            } else if (komento == 2) {
                valitseSyote();
            } else if (komento == 3) {
                puu = valitsePuu();
                if (puu == null) {
                    System.out.println("\nEt valinnut tietorakennetta.");
                } else {
                    Vertailija v = new Vertailija(syote);
                    Puu pJarjestyksessa = puu;
                    String tulos = v.vertaile(puu, pJarjestyksessa);
                    System.out.println(tulos + "+\n\nSyötä jokin merkki palataksesi. ");
                    luk.next();
                }
            } else {
                break;
            }
        }
        
    }   

    /** Valitaan haluttu puu testattavaksi.
     * 
     * @return uusi puu valittua tyyppiä
     */
    private Puu valitsePuu() {
     System.out.println("\nValitse tietorakenne jonka operaatioiden keston haluat nähdä: \n"
                + "1: Punamustapuu\n"
                + "2: 2-3-hakupuu\n"
                + "3: Treap\n"
                + "4: Threaded-puu\n"
                + "Muu: Palaa\n");
     int komento = luk.nextInt();
     if (komento == 1) {
         return new Punamusta();
     } else if (komento == 2) {
         return new KaksiKolme();
     } else if (komento == 3) {
         return new Treap();
     } else if (komento == 4) {
         return new Threaded();
     }
         return null;
    }

    /**
     * Tulostaa ohjeen ohjelman käyttöä varten.
     */
    private void tulostaOhje() {
        System.out.println("\n Ohjelmassa on mahdollista testata neljän erilaisen puu-tietorakenteen\n"
                + "toimintanopeuksia. \n"
                + " Punamustapuut ovat itsestään tasapainottuvia binäärihakupuita. \n"
                + " Treapit ovat ???\n"
                + " Kaksi-kolme hakupuut ovat hakupuita, joissa solmulla on kaksi mahdollista arvoa, \n"
                + "ja tällöin mahdollisesti kolme lasta.\n"
                + " Threaded puut ovat binäärihakupuita, joiden tyhjät lapsi-pointterit osoittavat \n"
                + "ylöspäin puussa.\n"
                + " Tässä ohjelmassa on mahdollista valita syötteen koko joko valitsemalla määrä itse\n"
                + "tai käyttämällä yhtä kolmesta valmiista kokoluokasta.\n"
                + " Testattavat puut valitaan yksi kerrallaan, jonka jälkeen tulokset annetaan \n"
                + "millisekunnin tarkuudella.\n"
                + "\nSyötä jokin merkki palataksesi\n");
        luk.next();
    }

    /**
     * Valitse syötteen koon.
     */
    private void valitseSyote() {
        System.out.println("\n1: Pieni syöte (noin 100 alkiota)\n"
                + "2: Keskikokoinen syöte(noin 10 000 alkiota)\n"
                + "3: Suuri syöte (noin miljoona alkiota)\n"
                + "4: Oma syöte\n");
        int komento = luk.nextInt();
        
        if (komento == 1) {
            syote = 100;
        } else if (komento == 2) {
            syote = 1000;
        } else if (komento == 3) {
            syote = 10000;
        } else if (komento == 4){
            System.out.print("\nYli 10 000 alkion syötteitä ei suositella. \nAnna syötteen koko: ");
            syote = luk.nextInt();
        } 
    }
}