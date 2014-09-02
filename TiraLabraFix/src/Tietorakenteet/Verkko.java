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
 * Abstrakti Verkkorajapinta jonka tarkoituksena on luetella mitkä asiat siitä
 * pitäisi löytyä, jotta tässä verkossa voitaisiin löytää lyhimmän polun
 * käyttäen a-star algoritmia.
 */
public interface Verkko {

    public Jono naapurit(Abstraktisolmu node);

    public boolean olemassa(Abstraktisolmu node);

    public double heurestiikka(Abstraktisolmu alku, Abstraktisolmu loppu);

    public double etaisyys(Abstraktisolmu alku, Abstraktisolmu loppu);
    
    public void tyhjenna();
}