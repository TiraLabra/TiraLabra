/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import java.util.ArrayList;

/**
 *
 * @author ilkkaporna
 */

public class Taytto {
    private Paketti[] paketit;
    private Kontti kontti;
    private ArrayList[] jonot;
    private int[] summat;
    
    private int tayttolaskuri = 0;
    
    /**
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
        
        //Väliaikainen ratkaisu asettaa koko etukäteen, lopullisessa ei tarpeellinen ja vaatii ekpotentiaalisen paljon muistia kun pakettien määrät kasvavat
        int koko = (int)Math.pow((double)2, (double)paketit.length);
        this.jonot = new ArrayList[koko];
        this.summat = new int[koko];
    }

    /**
     *
     * @param jono
     * @param kohta
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
     *
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
    public String annaSummatJaJonotMerkkijonona() {
        String vastaus="";
        for (int i=0; i<jonot.length; i++) {
            vastaus = vastaus + "Summa: " + this.summat[i] + ", paketit: " + this.jonot[i].toString() + "\n";            
        }
        return vastaus;
    }

}
