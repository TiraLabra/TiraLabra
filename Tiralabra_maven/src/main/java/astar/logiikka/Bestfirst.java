/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.logiikka;

import astar.tietorakenteet.Lista;
import astar.tietorakenteet.PrioKeko;
import astar.verkko.Kartta;
import astar.verkko.Solmu;
import java.util.Comparator;

/**
 *
 * @author sasumaki
 */
public class Bestfirst {

    private final Lista<Solmu> lista;
    private final Kartta kartta;

    public Bestfirst(Lista lista, Kartta kartta) {
        this.lista = lista;
        this.kartta = kartta;
    }

    public Lista<Solmu> haku() {
        Lista<Solmu> polku = new Lista<>();
        if(lista.size() < 2){
            return null;
        }
        final int maaliX = lista.get(1).getX();
        final int maaliY = lista.get(1).getY();
        Integer[][] kayty = new Integer[kartta.getKorkeus()][kartta.getLeveys()];
        
        PrioKeko<Solmu> rintama = new PrioKeko<>(new Comparator<Solmu>() {

            @Override
            public int compare(Solmu t, Solmu t1) {
                return (t.getMatkaAlusta() + heuristinenMatka(t, maaliX, maaliY)) - (t1.getMatkaAlusta() + heuristinenMatka(t1, maaliX, maaliY));
            }
        });
        
        rintama.heapInsert(lista.get(0));
        
        while(!rintama.isEmpty()){
            Solmu nykyinen = rintama.pullDelete();
            
            if(nykyinen.getX() == maaliX && nykyinen.getY() == maaliY){
                
                polku.add(nykyinen);
                
                break;
            }
            
            for(Solmu s : kartta.naapurit(nykyinen.getX(), nykyinen.getY(), nykyinen, nykyinen.getMatkaAlusta())){
                
                if(kayty != null){
                    continue;
                }
                kayty[s.getY()][s.getX()] = s.getMatkaAlusta();
                rintama.heapInsert(s);
            }
            
        }
        int i= 0;
        while(polku.get(polku.size()).getEdellinen() != null){
            polku.add(polku.get(i).getEdellinen());
            i++;
        }
        
        
        return polku;

    }

    private int heuristinenMatka(Solmu s, int maaliX, int maaliY) {
        double heuristinen;
        heuristinen = Math.sqrt(Math.pow(s.getX() - maaliX, 2) + Math.pow(s.getY() - maaliY, 2));

        return (int) heuristinen;
    }
}
