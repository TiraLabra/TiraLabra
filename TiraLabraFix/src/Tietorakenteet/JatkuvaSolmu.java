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
 *
 */
public class JatkuvaSolmu implements Iteroitava, Abstraktisolmu {

    private Kordinaatti k;
    private SolmuMuisti muisti;
    private int sijaintikeossa;
    private Verkko verkko;
    private Jono naapurit;
   

    public JatkuvaSolmu(Kordinaatti k) {
        this.k = k;
        this.muisti = new SolmuMuisti();
        this.naapurit = new Jono();
    }

    @Override
    public void asetaArvo(double d) {
        this.muisti.asetaFScore(d);

    }

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

    @Override
    public int sijaintiKeossa() {
        return this.sijaintikeossa;
    }

    @Override
    public void asetaSijainti(int i) {
        this.sijaintikeossa = i;
    }

    @Override
    public void asteaVerkko(Verkko verkko) {
        this.verkko = verkko;
    }

    @Override
    public Verkko palautaVerkko() {
        return this.verkko;
    }

    @Override
    public SolmuMuisti palautaSolmuMuisti() {
        return this.muisti;
    }
    
    @Override
    public Kordinaatti palautaKordinaatti()
    {
    return this.k;
    }
    
    public Jono palautaNaapurit()
    {
    return this.naapurit;
    
    }
    public void asetaNaapurit(Jono jono)
    {
    this.naapurit = jono;
    }

    public void lisaaNaapuri(JatkuvaSolmu loppu) {
        this.naapurit.lisaa(loppu);
    }
    
    


    

}
