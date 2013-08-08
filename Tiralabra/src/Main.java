
import sovelluslogiikka.Laskin;

/**
 * Ohjelman suoritusluokka
 *
 * @author jukkapit
 */
public class Main {

    /**
     * Suorittaa ohjelman annetuilla parametreilla
     *
     * @param args Merkkijonot
     */
    public static void main(String[] args) {
        Laskin laskin = new Laskin();
        laskin.kaynnista();
    }
}