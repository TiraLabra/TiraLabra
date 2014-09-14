/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti;

import labyrintti.logiikka.LyhinReitti;
import labyrintti.logiikka.Maapala;
import labyrintti.logiikka.Maapalarekisteri;

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
        
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(4, 1, 1, 3, 3);
        maapalarekisteri.luoMaapalat();
        LyhinReitti lyhinReitti = new LyhinReitti(maapalarekisteri);
        
        Maapala maapala = lyhinReitti.getParasNaapuri(maapalarekisteri.getAlku());
        maapalarekisteri.tulostaMaapalat();
        System.out.println(maapala);
        
    }
}
