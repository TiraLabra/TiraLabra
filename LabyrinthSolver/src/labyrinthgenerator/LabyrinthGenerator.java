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
     * Labyrintti, jolle algoritmit ajetaan.
     */
    public Labyrinth labyrinth;
    /**
     * Random-olio, jota käytetään satunnaisalgoritmeissa.
     */
    protected Random random;

    /**
     * Ottaa syötteenä labyrintin ja alustaa Random-olion.
     */
    public LabyrinthGenerator() {
        random = new Random();
    }

    /**
     * Labyrintin generoiva metodi.
     *
     * @throws java.lang.Exception Palauttaa poikkeuksen, jos labyrintin
     * käsittelyssä käytettiin labyrintin ulkopuolista koordinaattia. (Näin ei
     * pitäisi koskaan käydä.
     */
    public abstract void generateLabyrinth() throws Exception;

    /**
     * Tyhjentää labyrintin, jos se ei ole tyhjä.
     *
     * @see main.Labyrinth
     */
    public void createEmptyLabyrinthIfNeeded()  {
        if (labyrinth.labyrinth[0][0] != 0) {
            labyrinth.labyrinth = new byte[labyrinth.height][labyrinth.width];
        }
    }

}
