/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import Tietorakenteet.Abstraktisolmu;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * Abstrakti Verkkorajapinta jonka tarkoituksena on luetella mitkä asiat siitä
 * pitäisi löytyä, jotta tässä verkossa voitaisiin löytää lyhimmän polun
 * käyttäen a-star algoritmia.
 */
public interface Verkko {

    public ArrayList<Abstraktisolmu> Naapurit(Abstraktisolmu node);

    public boolean Olemassa(Abstraktisolmu node);

    public double Heurestiikka(Abstraktisolmu alku, Abstraktisolmu loppu);

    public double Etaisyys(Abstraktisolmu alku, Abstraktisolmu loppu);
    
    
}
