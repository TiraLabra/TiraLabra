/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti;

import labyrintti.logiikka.LyhinReitti;
import labyrintti.logiikka.Maapala;
import labyrintti.logiikka.Maapalarekisteri;
import labyrintti.tietorakenteet.LinkitettyLista;

/**
 *
 * @author User
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(4, 0, 0, 3, 3);
        maapalarekisteri.luoMaapalat();
        Maapala[][] labyrintti = maapalarekisteri.getLabyrintti();
        labyrintti[2][1].asetaSeinaksi();
        labyrintti[1][0].asetaSeinaksi();

        LyhinReitti lyhinReitti = new LyhinReitti(maapalarekisteri);
        LinkitettyLista linkitettyLista = new LinkitettyLista();
        
        Maapala maapala = maapalarekisteri.getAlku();
        
        linkitettyLista.lisaaListaan(maapala);
        
        Maapala maapala1 = maapalarekisteri.getLoppu();
        
        linkitettyLista.poistaListasta(maapala1);
        
        maapalarekisteri.tulostaMaapalat();

        lyhinReitti.tulostaLyhinReitti();
        
        
    }
}
