/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Serafim
 */
public class JatkuvaVerkko implements Verkko {

    HashMap<Kordinaatti, ArrayList<Abstraktisolmu>> solmukartta;
    HashMap<Kordinaatti, Abstraktisolmu> tavkartta;

    public JatkuvaVerkko() {
        this.solmukartta = new HashMap<Kordinaatti, ArrayList<Abstraktisolmu>>();

    }

    @Override
    public ArrayList<Abstraktisolmu> naapurit(Abstraktisolmu node) {
        JatkuvaSolmu n = (JatkuvaSolmu) node;
        return this.solmukartta.get(n.palautaKordinaatti());
    }

    @Override
    public boolean olemassa(Abstraktisolmu node) {
        return solmukartta.containsKey(node);
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
        for (Kordinaatti k : this.tavkartta.keySet()) {
            SolmuMuisti a = this.tavkartta.get(k).palautaSolmuMuisti();
            a.tyhjenna();

        }
    }

}
