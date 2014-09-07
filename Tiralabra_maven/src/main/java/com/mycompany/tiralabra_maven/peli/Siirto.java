package com.mycompany.tiralabra_maven.peli;

/**
 * Luokka sisältää metodit siirto-olioiden käsittelyyn
 * @author noora
 */
public class Siirto {
    private final int alkuRivi;
    private final int alkuSarake;
    private final int loppuRivi;
    private final int loppuSarake;
    
    /**
     * Konstruktorissa luodaan uusi siirto-olio
     * @param alkuRivi Kertoo miltä riviltä siirto lähtee
     * @param alkuSarake Kertoo mistä sarakkeesta siirto lähtee
     * @param loppuRivi Kertoo mille riville siirto loppuu
     * @param loppuSarake Kertoo mihin sarakkeeseen siirto loppuu
     */
    public Siirto(int alkuRivi, int alkuSarake, int loppuRivi, int loppuSarake){
        this.alkuRivi = alkuRivi;
        this.alkuSarake = alkuSarake;
        this.loppuRivi = loppuRivi;
        this.loppuSarake = loppuSarake;
    }
    
    /**
     * Kertoo onko siirto hyppy
     * @return Palauttaa tiedon siitä, onko siirto hyppy
     */
    public boolean onkoSiirtoHyppy(){
        return (Math.abs(this.alkuRivi - this.loppuRivi) == 2);
    }

    public int getAlkuRivi() {
        return alkuRivi;
    }

    public int getAlkuSarake() {
        return alkuSarake;
    }

    public int getLoppuRivi() {
        return loppuRivi;
    }

    public int getLoppuSarake() {
        return loppuSarake;
    }
    
}
