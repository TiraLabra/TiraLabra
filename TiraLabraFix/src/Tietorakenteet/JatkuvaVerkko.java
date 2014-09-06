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
 * @author Serafim
 */
public class JatkuvaVerkko implements Verkko {

    private Jono kaikkialkiot;

    public JatkuvaVerkko() {
        this.kaikkialkiot = new Jono();
    }
    
    public void lisaaAlkio(JatkuvaSolmu s)
    {
    this.kaikkialkiot.lisaa(s);
    s.asteaVerkko(this);
    
    }

    @Override
    public boolean olemassa(Abstraktisolmu node) {
        JatkuvaSolmu s = (JatkuvaSolmu) node;
        if (s.palautaVerkko() == this) {
            return true;
        }
        return false;
    }

    @Override
    public double heurestiikka(Abstraktisolmu alku, Abstraktisolmu loppu) {
        return etaisyys(alku, loppu);
    }

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

    @Override

    public void tyhjenna() {
        Jonoiteroitava iter = kaikkialkiot.palautaEnsimmainen();
        while (iter != null) {
            JatkuvaSolmu s = (JatkuvaSolmu) iter.palautaObjekti();
            s.palautaSolmuMuisti().tyhjenna();

            iter = iter.palautaSeuraava();
        }
    }

    @Override
    public Jono naapurit(Abstraktisolmu node) {
        JatkuvaSolmu s = (JatkuvaSolmu) node;
        return s.palautaNaapurit();
    }

}
