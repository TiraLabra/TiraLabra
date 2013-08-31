package Tiralabra.kayttoliittyma;

import Tiralabra.domain.*;
import Tiralabra.vertailija.Vertailija;
import java.util.Scanner;

/**
 * Tekstikäyttöliittymä testausta ja perustoimivuutta varten.
 *
 * @author Pia Pakarinen
 */
public class TekstiUI {

    /**
     * Lukee käyttäjän antamat komennot.
     *
     */
    private Scanner luk;
    /**
     * Syötteen koko.
     */
    private int syote;
    
    /**
     * Lisäysoperaatiossa käytettävien alkioiden järjestys. True, jos
     * alkiot halutaan lisätä suuruujärjestyksessä.
     */
    private boolean jarjestys;

    /**
     * Käynnistää tekstikäyttöliittymän.
     *
     */
    public void kaynnista() {
        syote = 100;
        jarjestys = false;
        Puu puu;
        luk = new Scanner(System.in);
        System.out.print("Puuvertailija!");
        while (true) {
            System.out.println("\nValitse toiminto: \n"
                    + "1. Ohje \n"
                    + "2. Valitse syöte\n"
                    + "3. Katso kunkin puun suoritusajat\n"
                    + "4: Vertaa kaikkia\n"
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
                    Vertailija v = new Vertailija(syote, jarjestys);
                    String tulos = v.vertaile(puu);
                    System.out.println(tulos + "+\n\nSyötä jokin merkki palataksesi. ");
                    luk.next();
                }
            } else if (komento == 4) {
                Vertailija v = new Vertailija(syote, jarjestys);
                String t = v.vertaileKaikki(new Punamusta(), new Threaded(), new KaksiKolme(), new Treap());
                System.out.println(t + "\n\nSyötä jokin merkki palataksesi.");
                luk.next();
            } else {
                break;
            }
        }

    }

    /**
     * Valitaan haluttu puu testattavaksi.
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
        System.out.println("\n Ohjelmassa on mahdollista testata neljän erilaisen puu-tietorakenteen "
                + "toimintanopeuksia. \n"
                + " Punamustapuut ovat itsestään tasapainottuvia binäärihakupuita. \n"
                + " Treapit ovat kekojen ja puiden risteytys. Toimivat kuin normaalit binäärihakupuut, "
                + "mutta solmuilla on lisäksi toiset arvot, joidenka mukaan rakenne on samaan aikaa "
                + "kekojärjestyksessä, ja näin keskimäärin tasapainoinen.\n"
                + " Kaksi-kolme hakupuut ovat hakupuita, joissa solmulla on kaksi mahdollista arvoa, "
                + "ja tällöin mahdollisesti kolme lasta.\n"
                + " Threaded puut ovat binäärihakupuita, joiden tyhjät lapsi-pointterit osoittavat "
                + "ylöspäin puussa.\n"
                + " Tässä ohjelmassa on mahdollista valita syötteen koko joko valitsemalla määrä itse "
                + "tai käyttämällä yhtä kolmesta valmiista kokoluokasta.\n"
                + " Testattavat puut valitaan yksi kerrallaan, jonka jälkeen tulokset annetaan "
                + "millisekunnin tarkuudella.\n"
                + " Lisäksi voidaan testata kaikki puut yhtäaikaa, ja nähdä tulokset nopeimmiten ja hitaimmiten "
                + "suoritetuista operaatioista.\n"
                + "\nSyötä jokin merkki palataksesi\n");
        luk.next();
    }

    /**
     * Valitse syötteen koon ja tyypin.
     */
    private void valitseSyote() {
        System.out.println("\n1: Pieni syöte (noin 100 alkiota)\n"
                + "2: Keskikokoinen syöte(noin 1000 alkiota)\n"
                + "3: Suuri syöte (noin 5000 alkiota)\n"
                + "4: Oma syöte\n");
        int komento;
        try {
            komento = luk.nextInt();
            if (komento == 1) {
                syote = 100;
            } else if (komento == 2) {
                syote = 1000;
            } else if (komento == 3) {
                syote = 5000;
            } else if (komento == 4) {
                System.out.print("\nYli 5000 alkion syötteitä ei suositella hitauden takia. \nAnna syötteen koko: ");
                syote = luk.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Epäkäypä komento.\n");
        }


        System.out.println("\nValitse syötteen tyyppi: \n"
                + "1: Luvut lisätään satunnaisjärjestyksessä\n"
                + "2: Luvut lisätään suuruusjärjestyksessä\n");
        try{
            komento = luk.nextInt();
            if (komento == 1) {
                jarjestys = false;
            } else if (komento == 2) {
                jarjestys = true;
            }
        } catch (Exception e) {
            System.out.println("Epäkäypä komento\n");
        }
    }
}