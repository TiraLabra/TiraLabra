/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Serafim
 */
public class DiskreettiVerkko implements Verkko {

    private double ruudunpituus;
    private HashMap<Kordinaatti, DiskreettiSolmu> kartta;

    public DiskreettiVerkko() {
        ruudunpituus = 1;
        this.kartta = new HashMap<Kordinaatti, DiskreettiSolmu>();
    }

    public void asetaRuudunpituus(double pituus) {
        this.ruudunpituus = pituus;
    }

    public double palautaPituus() {
        return ruudunpituus;
    }
    
    public void asetaKartta(HashMap<Kordinaatti, DiskreettiSolmu> kartta)
    {
    this.kartta = kartta;
    }
    

    @Override
    public List<Abstraktisolmu> Naapurit(Abstraktisolmu node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Olemassa(Abstraktisolmu node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double Heurestiikka(Abstraktisolmu alku, Abstraktisolmu loppu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double Etaisyys(Abstraktisolmu alku, Abstraktisolmu loppu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
