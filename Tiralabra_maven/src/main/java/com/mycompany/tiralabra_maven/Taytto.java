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
    private int summaTotal;
    
    private int ratkaisuSumma;
    private ArrayList ratkaisuLisaysJono;
    private ArrayList ratkaisuPoistoJono;
    
    private ArrayList[] jonot;
    private int[] summat;
    
    //Väliaikainen ratkaisu: käytetään löydettyjen kombinaatioiden indeksoimisessa

    private int tayttolaskuri = 0;
    
    /**
     * ALustaa Luokkamuuttujat täyttövaihtoehtojen ajamiselle
     * 
     * @param summa
     * @param paketit
     * @param kontti
     */
    public Taytto (Paketti[] paketit, int summa, Kontti kontti) {
        //luodaan paketteja kokoineen
        this.kontti = kontti;
        this.paketit = paketit;
        this.summaTotal=summa;
        //kombinaatioiden määrä on 2 potenssiin pakettien määrä
        //http://en.wikipedia.org/wiki/Combination#Number_of_k-combinations_for_all_k
        
        //Väliaikainen ratkaisu: asettaa kombinaatioiden määrän mukaisen koon etukäteen, lopullisessa ei tarpeellinen ja vaatii ekpotentiaalisen paljon muistia kun pakettien määrät kasvavat.
        //int koko = (int)Math.pow((double)2, (double)paketit.length);
        //this.jonot = new ArrayList[koko];
        //this.summat = new int[koko];
        
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
     * Etsii rekursiivisesti kombinaatioista täyttöasteeseen sopivaa ratkaisua. Kun sopiva löytyy, niin kirjoittaa luokkamuuttujiin ratkaisun ja lopettaa suorituksen. Käy mobinaatioita läpi tarkistaen aina ko. lehden symmetrisen vastinparin.
     * @param lisaysJono
     * @param poistoJono
     * @param kohta
     * @param lisaysSumma
     * @param poistoSumma
     * @return 
     */
    public boolean etsiMahtuvaSummaKombinaatioistaRek(ArrayList jono, int kohta, int lisaysSumma, int poistoSumma) {
        // Tähän kohtaan tullaan kun kombinaatiopuun lehti on saavutettu. Kyseisen kombinaation jäsenet ja summa tallennetaan.
        if (this.paketit.length == kohta) {
            
            //Tulostetaan trakkaysta varten jokainen lehti
            //System.out.println(jono.toString() + ", summa lisäyksillä:  " + lisaysSumma + ", summa poistoilla " + " " + poistoSumma);
            
            //Tarkastetaan onko jompikumpi summista tayttotavoittteessa
            if (lisaysSumma>=this.kontti.annaTayttoTavoite() && lisaysSumma<=this.kontti.annaKoko()) {
                    this.ratkaisuSumma=lisaysSumma;
                    this.ratkaisuLisaysJono=jono;
                    return true;
            }
            if (poistoSumma>=this.kontti.annaTayttoTavoite() && poistoSumma<=this.kontti.annaKoko()) {
                    this.ratkaisuSumma=poistoSumma;
                    this.ratkaisuPoistoJono=jono;
                    return true;
            }
           
            //monitorointia varten pidetään jono ja summa tauluja pystyssä
            //this.jonot[this.tayttolaskuri] = lisaysJono;
            //this.summat[this.tayttolaskuri] = lisaysSumma;
            //this.tayttolaskuri = this.tayttolaskuri + 1;
            
        } 
        // Tässä siirrytään uuteen haaraan kombinaatioden muodostamisessa ja pidetään tieto haaraumakohhdan tilanteesta tallessa.
        else {
            ArrayList uusiJono = new ArrayList(jono);
            uusiJono.add(kohta);

            int uusiLisaysSumma = lisaysSumma + this.paketit[kohta].annaKoko();
            int uusiPoistoSumma = poistoSumma - this.paketit[kohta].annaKoko();

            kohta = kohta + 1;
            
            if (etsiMahtuvaSummaKombinaatioistaRek(uusiJono, kohta, uusiLisaysSumma, uusiPoistoSumma)==true) 
                return true;
            if (etsiMahtuvaSummaKombinaatioistaRek(jono, kohta, lisaysSumma, poistoSumma)==true) 
                return true;
        }
        return false;
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

    public void tulostaRatkaisut() {

        System.out.println("Vetoisuus: " + this.kontti.annaKoko());
        System.out.println("Täyttötavoite: " + this.kontti.annaTayttoTavoite());
        System.out.println("Summa: " + this.ratkaisuSumma);
        System.out.println("LisaysJono: " + this.ratkaisuLisaysJono);
        System.out.println("PoistoJono: " + this.ratkaisuPoistoJono);
    }
    public String annaRatkaisutMerkkijonona() {
        return " Vetoisuus: " + this.kontti.annaKoko() + " Täyttötavoite: " + this.kontti.annaTayttoTavoite() + " Summa: " + this.ratkaisuSumma + " LisaysJono: " + this.ratkaisuLisaysJono + " PoistoJono: " + this.ratkaisuPoistoJono;
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
