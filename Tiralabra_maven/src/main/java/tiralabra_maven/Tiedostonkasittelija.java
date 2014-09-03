/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tiralabra_maven;
import java.io.*;
import omamatriisipaketti.*;
import java.util.Scanner;


/**
 * Tiedoston kasittelija. Luokan tarkoitus on lukea tietoja tiedostosta matriisiin
 * näin mahdollistaen isompien matriisien siirtämisen ohjelmaan.
 * @author risto
 */
public class Tiedostonkasittelija {
    
    /**
     * Lue tiedosto. Metodi lukee tiedostosta luvut ja palauttaa matriisin. Tiedoston
     * tulee olla csv-formaatissa, tarkemmin ottaen yksittäiset arvot on eroteltu
     * pilkulla ja rivit rivinvaihdolla. Tiedostossa ei saa olla mitään ylimääräisiä merkkejä.
     * Esim. libreoffice calc:n txt(.csv)-tiedostoformaatti on oletuksena sopiva.
     * @param tiedostopolku On halutun tiedoston tiedostopolku merkkijonona, tai mikäli tiedosto sijaitsee
     * projektikansion juuressa pelkästään tiedoston nimi.
     * @return metodi palauttaa Matrix-olion.
     * @throws Exception Metodi heittää kaikki tiedoston käsittelyyn liittyvät 
     * poikkeukset kutsujansa huoleksi.
     */
    
    public BasicMatrix lueTiedosto(String tiedostopolku) throws Exception {
        File tiedosto;
        String[] rivi;
        double[][] matriisi;
        Scanner scan;
        int m;
        int n;
        BasicMatrix palautettava = null;

            tiedosto = new File(tiedostopolku);
            scan = new Scanner(tiedosto);
            m = this.selvitaRivit(tiedosto);           
            n = this.selvitaSarakkeet(tiedosto);
            matriisi = new double[m][n];
            int j = 0;
            while(scan.hasNextLine()) {
                rivi = scan.nextLine().split(",");
                for (int i = 0; i < n; i++) {
                    matriisi[j][i] = Double.parseDouble(rivi[i]);
                }
                j++;
            }
            scan.close();
            palautettava = new BasicMatrix(matriisi);
        
        
       
        
        return palautettava;
    }
    
    /**
     * Selvitä rivit. Metodi palauttaa tiedostossa olevien rivien määrän.
     * @param tiedosto On joki File-olio.
     * @return rivien määrä int-muodossa
     * @throws Exception metodi heittää tiedoston käsittelyyn liittyvät poikkeukset.
     */
    private int selvitaRivit(File tiedosto) throws Exception {
        Scanner scan = new Scanner(tiedosto);
        int rivit=0;
        
        while(scan.hasNextLine()) {
            rivit++;
            scan.nextLine();
        }
        scan.close();
        return rivit;
        
        
    }
    
    /**
     * Selvitä sarakkeet. Metodi selvittää sarakkeiden määrän. Koska hyvässä tiedostossa
     * on sama määrä alkioita joka rivillä, riittää laskea ensimmäisen rivin sarakkeiden
     * määrä. Jos jollain rivillä onkin enemmän alkioita se johtaa poikkeukseen 
     * matriisia muodostettaessa, mutta epälukuisesta määrästä alkioita jollain rivillä
     * ei muutenkaan selvittäisi ilman poikkeusta.
     * @param tiedosto On .csv-muodossa oleva matriisi.
     * @return metodi palauttaa rivien määrän kokonaislukuna.
     * @throws Exception Tiedoston käsittelyyn liittyvät poikkeuksen heitetään kutsujalle. 
     */
    private int selvitaSarakkeet(File tiedosto) throws Exception {
        String[] rivi1;
        Scanner scan = new Scanner(tiedosto);
        rivi1=scan.nextLine().split(",");
        scan.close();
        return rivi1.length;
    }

}
