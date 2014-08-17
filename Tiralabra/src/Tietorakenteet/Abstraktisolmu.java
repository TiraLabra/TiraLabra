/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import Tietorakenteet.keko.Iteroitava;




/**
 *
 * Abstraktisolmu rajapinta
 */
public interface Abstraktisolmu extends Iteroitava {

    public void asteaVerkko(Verkko verkko);

    public Verkko palautaVerkko();

    public SolmuMuisti palautaSolmuMuisti();
    
    

}
