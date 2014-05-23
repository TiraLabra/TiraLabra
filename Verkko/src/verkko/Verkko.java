/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package verkko;

import java.util.Arrays;
import labyrintti.Labyrintti2D;
import labyrintti.RecursiveBacktracker;
import util.Taulukko;

/**
 *
 * @author Arvoitusmies
 */
public class Verkko {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

         Labyrintti2D l = new Labyrintti2D(10, 10);
         l.setLabyrintitin(new RecursiveBacktracker(l.getSolmut()));
         l.labyrintitaLabyrintti();
         System.out.println(l.toString());


    }

}
