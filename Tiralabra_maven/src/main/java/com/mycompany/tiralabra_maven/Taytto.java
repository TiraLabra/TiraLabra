/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import java.util.ArrayList;

/**
 * Etsii mahdollisia tapoja täyttää annettu kontti annetuilla pakettivaihtoehdoilla
 * Tulostaa vaihtoehtoja
 * @author ilkkaporna
 */

public class Taytto {
    private Paketti[] paketit;
    private Kontti kontti;
    private ArrayList[] jonot;
    private int[] summat;
    
    //Väliaikainen ratkaisu: käytetään löydettyjen kombinaatioiden indeksoimisessa

    private int tayttolaskuri = 0;
    
    /**
     * ALustaa Luokkamuuttujat täyttövaihtoehtojen ajamiselle
     * 
     * @param paketit
     * @param kontti
     */
    public Taytto (Paketti[] paketit, Kontti kontti) {
        //luodaan paketteja kokoineen
        this.kontti = kontti;
        this.paketit = paketit;

        //kombinaatioiden määrä on 2 potenssiin pakettien määrä
        //http://en.wikipedia.org/wiki/Combination#Number_of_k-combinations_for_all_k
        
        //Väliaikainen ratkaisu: asettaa kombinaatioiden määrän mukaisen koon etukäteen, lopullisessa ei tarpeellinen ja vaatii ekpotentiaalisen paljon muistia kun pakettien määrät kasvavat.
        int koko = (int)Math.pow((double)2, (double)paketit.length);
        this.jonot = new ArrayList[koko];
        this.summat = new int[koko];
    }

    /**
     * Muodostaa annetuille paketeille kaikki mahdolliset kombinaatiot. Toimii rekursiivisesti, lähtöarvot tyhjiä.
     * @param jono Kombinaatioon kuuluvien pakettien indeksit 
     * @param kohta osoittaa missäkohtaa iteraatio kulkee eli mikä paketti (indeksi) viimeiseksi ollut käsittelyssä
     */
    public void muodostaKombinaatioRek(ArrayList jono, int kohta) {
        if (this.paketit.length == kohta) {
            this.jonot[this.tayttolaskuri] = jono;
            this.tayttolaskuri = this.tayttolaskuri + 1;
        } 
        else {
            ArrayList jonoUusi = new ArrayList(jono);
            jonoUusi.add(kohta);
            kohta = kohta + 1;
            muodostaKombinaatioRek(jonoUusi, kohta);
            muodostaKombinaatioRek(jono, kohta);
        }
    }

    /**
     * Sama kuin muodostaKombinaatioRek, mutta laskee pakettien kokojen summat kullekin jonolle
     * @param jono
     * @param kohta
     * @param summa
     */
    public void etsiSummatKombinaatioistaRek(ArrayList jono, int kohta, int summa) {
        // Tähän kohtaan tullaan kun kombinaatiopuun lehti on saavutettu. Kyseisen kombinaation jäsenet ja summa tallennetaan.
        if (this.paketit.length == kohta) {
            this.jonot[this.tayttolaskuri] = jono;
            this.summat[this.tayttolaskuri] = summa;
            this.tayttolaskuri = this.tayttolaskuri + 1;
            
        } 
        // Tässä siirrytään uuteen haaraan kombinaatioden muodostamisessa ja pidetään tieto haaraumakohhdan tilanteesta tallessa.
        else {
            ArrayList uusiJono = new ArrayList(jono);
            uusiJono.add(kohta);
            int uusiSumma = summa + this.paketit[kohta].annaKoko();
            kohta = kohta + 1;
            etsiSummatKombinaatioistaRek(uusiJono, kohta, uusiSumma);
            etsiSummatKombinaatioistaRek(jono, kohta, summa);
        }
    }

    /**
     *
     * @return
     */
    public Paketti[] annaPaketit() {
        return this.paketit;        
    }

    /**
     *
     * @return
     */
    public Kontti annaKontti() {
        return this.kontti;        
    }

    /**
     *
     * @return
     */
    public ArrayList[] annaJonot() {
        return this.jonot;        
    }

    /**
     *
     */
    public void tulostaJonot() {

        for (int i=0; i<jonot.length; i++) {
            System.out.println(jonot[i].toString());
            
        }
    }

    /**
     *
     * @param i
     */
    public void tulostaJono(int i) {
        System.out.println(jonot[i].toString());
    }

    
    /**
     *
     */
    public void tulostaSummatJaJonot() {

        for (int i=0; i<jonot.length; i++) {
            System.out.println("Summa: " + summat[i] + ", paketit: " + jonot[i].toString());            
        }
    }

    /**
     *
     * @return
     */
    public String annaSummatJaJonotMerkkijonona() {
        String vastaus="";
        for (int i=0; i<jonot.length; i++) {
            vastaus = vastaus + "Summa: " + this.summat[i] + ", paketit: " + this.jonot[i].toString() + "\n";            
        }
        return vastaus;
    }

}
