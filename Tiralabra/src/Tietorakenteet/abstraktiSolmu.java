/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import Tietorakenteet.Keko.Iteroitava;

/**
 *
 * @author Serafim
 */
public interface abstraktiSolmu extends Iteroitava{

    public void asteaVerkko(Verkko verkko);

    public Verkko palautaVerkko();

    public void Varita(int i);

    public int palautaVari();
    
    public void asetaEdellinen(abstraktiSolmu solmu);
    
    public abstraktiSolmu palautaEdellinen();
    
    

}
