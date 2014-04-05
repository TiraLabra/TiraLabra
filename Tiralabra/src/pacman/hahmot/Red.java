package pacman.hahmot;

import pacman.alusta.Pelialusta;
/**
 * Luokka Red on yksi haamu tyypeistä, joka jahtaa mania. Haamun liikkumisesta huolehtii haamujenkäsittelijä.
 * @author Hanna
 */
public class Red extends Haamu{
    
    /**
     * Konstruktori, joka peritään yläluokalta.
     * @param x
     * @param y
     * @param suunta
     * @param nimi
     * @param alusta 
     */
    public Red(int x, int y,  Suunta suunta, String nimi, Pelialusta alusta) {
        super(x, y, suunta, nimi, alusta);
    }
}
