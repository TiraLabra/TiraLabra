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
<<<<<<< HEAD
     * niin kauan kunnes sen parent solmu on sitä pienempi.
=======
     * niin kauan ylöspäin kunnes sen parent solmu on sitä pienempi.
>>>>>>> origin/master
     * 
    */
    private void SiirräYlös(){
        
        int lapsenpaikka = viimeinen-1;
        while (lapsenpaikka > 0) {
            
            int vanhemmanpaikka = (lapsenpaikka-1)/2;
            Solmu lapsi = solmut[lapsenpaikka];
            Solmu vanhempi = solmut[vanhemmanpaikka];
            
            if (lapsi.Vertaa(vanhempi) > 0) {

                solmut[lapsenpaikka]=vanhempi;
                solmut[vanhemmanpaikka]=lapsi;
                 
                lapsenpaikka = vanhemmanpaikka;
            } else {
                break;
            }
        }
    }
    
    /**
     * Siirtää puun ylintä solmua alaspäin
     * kunnes sen lapsi solmut ovat sitä suurempia.
     * 
    */  
     
    private void SiirräAlas() {
        int vanhemmanpaikka = 0;
        int vasenlapsi = 2*vanhemmanpaikka+1;
        
        while (vasenlapsi < viimeinen-1) {
            
            int pienempiilapsi=vasenlapsi;
            int oikealapsi=vasenlapsi+1;
            
            if (oikealapsi < viimeinen-1) {
                if (solmut[oikealapsi].Vertaa(solmut[vasenlapsi]) > 0) {
                    pienempiilapsi++; //eli oikealapsesta tulee suurempi
                }
            }
            if (solmut[vanhemmanpaikka].Vertaa(solmut[pienempiilapsi]) < 0) {

                    Solmu käsittelyssä = solmut[vanhemmanpaikka];
                    solmut[vanhemmanpaikka] = solmut[pienempiilapsi];
                    solmut[pienempiilapsi] = käsittelyssä;
                    vanhemmanpaikka = pienempiilapsi;
                    vasenlapsi = 2*vanhemmanpaikka+1;
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
        SiirräYlös();
    }    
    
     /**
     * Poistaa taulukon ensimmäisen ja mikäli taulukko ei ole tyhjä 
     * tämän jälkeen niin asettaa viimeisimmän solmun ensimmäiseksi ja 
     * kutsuu metodia siirräalas.
     * 
     * 
     * @return poistettava solmu
    */      

    public Solmu Poista() {
        
        if (viimeinen == 0) {
            return null;
        }
        

        Solmu palautus = solmut[0];
        solmut[0]=solmut[viimeinen-1];
        solmut[viimeinen-1]=null;
        viimeinen--;
        SiirräAlas();
        return palautus;
    }
    
    
    public Solmu haeEka() {
        return solmut[0];
    }
}
