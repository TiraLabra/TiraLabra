/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algoritmi;

/**
 *
 *Luokka on minimikeko. Keossa olevat solmut on tallenettu taulukkoon. 
 * 
 */
public class Keko {
//    node at k has kids in 2k+1 and 2k+2
//    node at k has parent at (k-1)/2
    private Solmu[] solmut;
    private int viimeinen;
    
    /**
     *
     * @param laajuus taulukon maksimikoko
    */ 
    
    public Keko(int laajuus){
        solmut=new Solmu[laajuus];
        viimeinen=0;
    }

    /**
     * Siirtää vimeisintä lisättyä solmua ylöspäin puussa
     * niin kauan ylöspäin kunnes sen parent solmu on sitä suurempi.
     * 
    */     
    private void siirräylös(){
        
        int lapsi = viimeinen-1;
        while (lapsi > 0) {
            int vanhem = (lapsi-1)/2;
            Solmu käsittelyssä = solmut[lapsi];
            Solmu vanhempi = solmut[vanhem];
            if (käsittelyssä.vertaa(vanhempi) > 0) {

                solmut[lapsi]=vanhempi;
                solmut[vanhem]=käsittelyssä;
                 
                lapsi = vanhem;
            } else {
                break;
            }
        }
    
    }
    
    /**
     * Siirtää puun ylintä solmua alaspäin
     * kunnes sen lapsi solmut ovat sitä pienempiä.
     * 
    */  
     
    private void siirräalas() {
        int vanhem = 0;
        int vasenlapsi = 2*vanhem+1;
        while (vasenlapsi < viimeinen-1) {
            int suurempi=vasenlapsi;
            int oikealapsi=vasenlapsi+1;
            if (oikealapsi < viimeinen-1) {
                if (solmut[oikealapsi].vertaa(solmut[vasenlapsi]) > 0) {
                    suurempi++;
                }
            }
            if (solmut[vanhem].vertaa(solmut[suurempi]) < 0) {

                    Solmu käsittelyssä = solmut[vanhem];
                    solmut[vanhem] = solmut[suurempi];
                    solmut[suurempi] = käsittelyssä;
                    vanhem = suurempi;
                    vasenlapsi = 2*vanhem+1;
            } else {
                break;
            }
        }
    }

     /**
     * Lisää solmun taulukon loppuun ja kutsuu metodia siirräylös.
     * 
     * @param lisättävä kekoon lisättävä solmu
    */  
    
    public void lisää(Solmu lisättävä) {
        
        solmut[viimeinen]=lisättävä;
        viimeinen++;
        siirräylös();
    }    
    
     /**
     * Poistaa taulukon ensimmäisen ja mikäli taulukko ei ole tyhjä 
     * tämän jälkeen niin asettaa viimeisimmän solmun ensimmäiseksi ja 
     * kutsuu metodia siirräalas.
     * 
     * 
     * @return poistettava solmu
    */      

    public Solmu poista() {
        
        if (viimeinen == 0) {
        }
        
        if (viimeinen == 1) {
            viimeinen--;
            Solmu palautus = solmut[0];
            solmut[0]=null;
            return palautus;
        }
        Solmu palautus = solmut[0];
        solmut[0]=solmut[viimeinen-1];
        solmut[viimeinen-1]=null;
        viimeinen--;
        siirräalas();
        return palautus;
    }
    
}
