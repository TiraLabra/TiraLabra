package ohjelma;

import java.util.ArrayList;

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
        Solmu eka = new Solmu();
        Solmu toka = new Solmu();
        
        Kaari a = new Kaari(eka, toka);
        
        Solmu kol = new Solmu();
        Solmu nel = new Solmu();
        
        Kaari b = new Kaari(kol, nel);
        
        Solmu viis = new Solmu();
        Solmu kuus = new Solmu();
        
        Kaari c = new Kaari(viis, kuus);
        
        Solmu seitm = new Solmu();
        Solmu kaheks = new Solmu();
        
        Kaari d = new Kaari(seitm, kaheks);
        
        Solmu yheks = new Solmu();
        Solmu kymm = new Solmu();
        
        Kaari e = new Kaari(yheks, kymm);
        
        ArrayList<Kaari> kaaret = new ArrayList<Kaari>();
        ArrayList<Solmu> solmut = new ArrayList<Solmu>();
        solmut.add(eka);
        solmut.add(toka);
        solmut.add(kol);
        solmut.add(nel);
        solmut.add(viis);
        solmut.add(kuus);
        solmut.add(seitm);
        solmut.add(kaheks);
        solmut.add(yheks);
        solmut.add(kymm);
        
        Verkko verkko = new Verkko(kaaret, solmut);
        
       
        BellmanFord aloita = new BellmanFord();
        aloita.BellmanFord(verkko);
        
        
    }
}