/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Jono.Jonoiteroitava;

/**
 *
 * Tämä on jatkuvasolmu luokka, eli jatkuvan verkon alkio
 */
public class JatkuvaSolmu implements Iteroitava, Abstraktisolmu {

    private Kordinaatti k;
    private SolmuMuisti muisti;
    private int sijaintikeossa;
    private Verkko verkko;
    private Jono naapurit;
   
    /**
 *
 * Luo uuden Jatkuvasolmun
     * @param k Kordinaatti kordinaatti parametri
 */

    public JatkuvaSolmu(Kordinaatti k) {
        this.k = k;
        this.muisti = new SolmuMuisti();
        this.naapurit = new Jono();
    }

    /**
 *
 * Asettaa FScoren (lähinnä testausta varten)
 */
    @Override
    public void asetaArvo(double d) {
        this.muisti.asetaFScore(d);

    }
    
     /**
 *
 * Vertaa tätä toiseen jatkuvasolmuun
     * @return palauttaa 1 jos on suurempi, 0 jos on sama alkio, muuten -1
 */

    @Override
    public int vertausoperaatio(Iteroitava toinen) {
        JatkuvaSolmu d = (JatkuvaSolmu) toinen;
        if (this.palautaSolmuMuisti().palautaFScore() - d.palautaSolmuMuisti().palautaFScore() > 0) {
            return 1;
        }
        if (this.palautaSolmuMuisti().palautaFScore() == d.palautaSolmuMuisti().palautaFScore()) {
            return 0;
        } else {
            return -1;
        }
    }
    
   

    /**
     * Kertoo oman sijainnin keossa
     * @return int sijainti
     */
    @Override
    public int sijaintiKeossa() {
        return this.sijaintikeossa;
    }
    
     /**
 *
 * Asettaa sijainnin keossa
     * @param i sijainti
 */

    @Override
    public void asetaSijainti(int i) {
        this.sijaintikeossa = i;
    }
    
     /**
 *
 * Asettaa verkon
 */

    @Override
    public void asteaVerkko(Verkko verkko) {
        this.verkko = verkko;
    }
    
      /**
 *
 * Palauttaa verkon
 */

    @Override
    public Verkko palautaVerkko() {
        return this.verkko;
    }
    
     /**
 *
 * Palauttaa solmumuistin
 */

    @Override
    public SolmuMuisti palautaSolmuMuisti() {
        return this.muisti;
    }
    
     /**
 *
 * Palauttaa kordiinatit
 */
    
    @Override
    public Kordinaatti palautaKordinaatti()
    {
    return this.k;
    }
    
    /**
 *
 * Palauttaa naapurit
     * @return Jono naapureita JatkuvaSolmu muodossa
 */
    
    public Jono palautaNaapurit()
    {
    return this.naapurit;
    
    }
    
    /**
 *
 * Asettaa naapurit jono muodossa
     * @param jono
 */
    public void asetaNaapurit(Jono jono)
    {
    this.naapurit = jono;
    }
    
    /**
 *
 * Lisaa naapurin joukkoon
     * @param loppu toinen solmu
 */

    public void lisaaNaapuri(JatkuvaSolmu loppu) {
        this.naapurit.lisaa(loppu);
    }
    
    


    

}
