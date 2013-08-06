package ohjelma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kkivikat
 */
public class Main {
//

    public static void main(String[] args) {
        HashMap<Integer, Solmu> solmut = new HashMap<Integer, Solmu>();
        HashSet<Kaari> kaaret = new HashSet<Kaari>();
        
        //*****************************************//
        
        Solmu eka = new Solmu(1);
        Solmu toka = new Solmu(2);
        Solmu kolmas = new Solmu(3);
        Solmu neljas = new Solmu(4);
        Solmu viides = new Solmu(5);
        Solmu kuudes = new Solmu(6);
        

        solmut.put(eka.getSolmuNumero(), eka);
        solmut.put(toka.getSolmuNumero(), toka);
        solmut.put(kolmas.getSolmuNumero(), kolmas);
        solmut.put(neljas.getSolmuNumero(), neljas);
        solmut.put(viides.getSolmuNumero(), viides);
        solmut.put(kuudes.getSolmuNumero(), kuudes);
        
        //*****************************************//
        
        Kaari a = new Kaari(eka, kolmas, 5);
        Kaari b = new Kaari(eka, toka, 3);
        Kaari c = new Kaari(toka, kolmas, 1);
        Kaari d = new Kaari(kolmas, viides, 3);
        Kaari e = new Kaari(toka, neljas, 4);
        Kaari f = new Kaari(viides, kuudes, 6);
        Kaari g = new Kaari(neljas, kuudes, 2);

        kaaret.add(a);kaaret.add(b);kaaret.add(c);kaaret.add(d);
        kaaret.add(e);kaaret.add(f);kaaret.add(g);
        
        //*****************************************//

        Verkko verkko = new Verkko(solmut, kaaret);

        BellmanFord aloita = new BellmanFord(verkko, solmut, kaaret);
        aloita.BellmanFord(verkko);


    }
}