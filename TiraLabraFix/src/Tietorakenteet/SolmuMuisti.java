/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tietorakenteet;

/**
 *
 * Tämä luokka on luotu tukemaan abstraktia solmurakennetta. Tämä solmu sisältää tarvittavat muuttujat Atähti algoritmin
 */
public class SolmuMuisti {

    private int vari;
    private Abstraktisolmu edellinen;
    private double fscore;
    private double gscore;
    private boolean keossa;

    /**
 *
 * Luo uuden SolmuMuistin ja asettaa alkuarvot
 */
    
    public SolmuMuisti() {
        this.keossa = false;
        this.vari = 0;
        this.edellinen = null;
        this.fscore = 0;
        this.gscore = 0;
        this.keossa = false;
    }
    
      /**
 *
 * Värittää solmun i:ksi
 */

    public void Varita(int i) {
        this.vari = i;
    }
    
      /**
 *
 * Palauttaa värityksen
 */

    public int palautaVari() {
        return this.vari;
    }

    /**
 *
 * asettaa solmun mistä tähän päädyttiin
 */
    
    public void asetaEdellinen(Abstraktisolmu solmu) {
        this.edellinen = solmu;
    }

     /**
 *
 * palauttaa edellsien
 */
    public Abstraktisolmu palautaEdellinen() {
        return this.edellinen;
    }
    
    /**
 *
 * asettaa F scoren
 */

    public void asetaFScore(double fscore) {
        this.fscore = fscore;
    }

      /**
 *
 * palauttaa F scoren
 */
    
    public double palautaFScore() {
        return this.fscore;
    }
    /**
 *
 * asettaa G scoren
 */
    
    
    public void asetaGScore(double gscore) {
        this.gscore = gscore;
    }

      /**
 *
 * palauttaa G scoren
 */
    
    
    public double palautaGScore() {
        return this.gscore;
    }

     /**
 *
 * Asettaa että kyseinen alkio on keossa
 */
    
    
    public void asetaKekoon(boolean k) {
        this.keossa = k;
    }
    
     /**
 *
 * Tarkistaa onko kyseinen alkio keossa
 */

    public boolean Keossa() {
        return this.keossa;
    }

}
