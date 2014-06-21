package labyrinthgenerator;

import java.util.Random;
import main.Labyrinth;

/**
 * Labyrintin generointialgoritmien yliluokka.
 *
 * @author Juri Kuronen
 */
public abstract class LabyrinthGenerator {

    /**
     * Labyrintti-olio.
     */
    public Labyrinth labyrinth;
    /**
     * Random-olio, jota käytetään satunnaisalgoritmeissa.
     */
    protected Random random;
    /**
     * Algoritmin nimi.
     */
    public String name;

    /**
     * Alustaa Random-olion.
     */
    public LabyrinthGenerator() {
        random = new Random();
    }

    /**
     * Palauttaa algoritmin nimen.
     *
     * @return Palauttaa algoritmin nimen.
     */
    public String getName() {
        return name;
    }

    /**
     * Labyrintin generoiva metodi, jonka kaikkien aliluokkien on toteutettava.
     *
     * @throws java.lang.Exception Labyrintti-luokka heittää poikkeuksen, jos
     * algoritmi yrittää käsitellä labyrintin ulkopuolista koordinaattia.
     */
    public abstract void generateLabyrinth() throws Exception;

    /**
     * Tyhjentää labyrintin, jos se ei ole tyhjä.
     *
     * @see main.Labyrinth#updateLabyrinth(int, int)
     */
    public void createEmptyLabyrinthIfNeeded() {
        if (labyrinth.isGenerated()) {
            labyrinth.updateLabyrinth(labyrinth.getWidth(), labyrinth.getHeight());
        }
    }

}
