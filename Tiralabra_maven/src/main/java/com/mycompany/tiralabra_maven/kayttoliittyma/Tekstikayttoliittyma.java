
package com.mycompany.tiralabra_maven.kayttoliittyma;

import com.mycompany.tiralabra_maven.tyokalut.PuunTutkija;

/**
 *  Ohjelman käyttöliittymä ja tekstin tulostuksesta vastaavat metodit
 * @author Markus
 */
public class Tekstikayttoliittyma {

    private PuunTutkija tutkija;
    String[] kuvaukset;
    int[][] datajoukot;
    
    public Tekstikayttoliittyma(PuunTutkija tutkija) {
        this.tutkija = tutkija;
        
        kuvaukset = new String[1];
        kuvaukset[0] = "Testivertailu";
        
        datajoukot = new int[1][100];
        for (int i = 0; i < 100; i++) {
            datajoukot[0][i] = i+1;
        }
    }
    
    /**
     *  Käynnistää tekstipohjaisen käyttöliittymän
     */
    public void kaynnista(){
        System.out.println("Testi");
        System.out.println("Keskimääräinen mittausvirhe: " + tutkija.virhemarginaali() + " ns");
        System.out.println(tutkija.lisaysVertailu("Lukujen 1-100 lisäys", datajoukot[0]));
    }
    
}
