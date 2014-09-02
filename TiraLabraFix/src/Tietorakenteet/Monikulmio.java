/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Jono.Jonoiteroitava;
import java.util.ArrayList;

/**
 *
 * Monikulmio luokka
 */
public interface Monikulmio {


    /**
     *
     * Luo uuden monikulmion parametreinä kordinaattien lista.
     *
     * @param kordinaatit
     */
   

    /**
     *
     * Palauttaakulmat
     *
     * @return ArrayList<Kordinaatti> kordinaatit
     */
    public Jono palautaKulmat();

    /**
     *
     * Palauttaaa virittavannelion
     */
    public Kordinaatti[] palautaVirittavaNelio();

    /**
     *
     * Laskee virittavannelion
     */
    public void LaskeVirittavaNelio();

    /**
     *
     * Laskee janat
     */
 

    public void laskeJanat();
       

    public Kordinaatti[][] PalautaJanat();

}
