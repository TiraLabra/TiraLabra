/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Jono.Jonoiteroitava;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Tämä toimii JatkuvaVerkko luokkana
 */
public class JatkuvaVerkko implements Verkko {

    private Jono kaikkialkiot;

    
    /**
 *
 * Generoi uuden JatkuvaVerkon ja alustaa kaikkialkiot jonon
 */
    public JatkuvaVerkko() {
        this.kaikkialkiot = new Jono();
    }
    
    public void lisaaAlkio(JatkuvaSolmu s)
    {
    this.kaikkialkiot.lisaa(s);
    s.asteaVerkko(this);
    
    }
    
    /**
 *
 * Tarkistaa onko kyseinen solmu tässä verkossa
     * @param node
     * @return true jos on, false muuten
 */

    @Override
    public boolean olemassa(Abstraktisolmu node) {
        JatkuvaSolmu s = (JatkuvaSolmu) node;
        if (s.palautaVerkko() == this) {
            return true;
        }
        return false;
    }
    
     /**
 *
 * Laskee heurestiikan
     * @param alku
     * @param loppu
     * @return 
 */

    @Override
    public double heurestiikka(Abstraktisolmu alku, Abstraktisolmu loppu) {
        return etaisyys(alku, loppu);
    }
       /**
 *
 * Laskee solmujen välisen eukliidisen etäisyyden (R^2 tavallinen metriikka)
     * @param alku
     * @param loppu
     * @return double etäisyys
 */

    @Override
    public double etaisyys(Abstraktisolmu alku, Abstraktisolmu loppu) {
        JatkuvaSolmu a = (JatkuvaSolmu) alku;
        JatkuvaSolmu b = (JatkuvaSolmu) loppu;
        double x1 = a.palautaKordinaatti().palautaX();
        double y1 = a.palautaKordinaatti().palautaY();
        double x2 = b.palautaKordinaatti().palautaX();
        double y2 = b.palautaKordinaatti().palautaY();
        double sisa = Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2);
        double d = Math.sqrt(sisa);

        return d;
    }
        /**
 *
 *  Tyhjentää Verkon ja sen solmujen sisältämän informaation
 */

    @Override

    public void tyhjenna() {
        Jonoiteroitava iter = kaikkialkiot.palautaEnsimmainen();
        while (iter != null) {
            JatkuvaSolmu s = (JatkuvaSolmu) iter.palautaObjekti();
            s.palautaSolmuMuisti().tyhjenna();

            iter = iter.palautaSeuraava();
        }
    }
    
     /**
 *
 *  Palauttaa kyseisen solmun kaikki naapurit Jono muodossa
     * @param node
     * @return Jono naapureita
 */

    @Override
    public Jono naapurit(Abstraktisolmu node) {
        JatkuvaSolmu s = (JatkuvaSolmu) node;
        return s.palautaNaapurit();
    }

}
