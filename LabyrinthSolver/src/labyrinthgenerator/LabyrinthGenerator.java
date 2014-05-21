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
    protected Labyrinth labyrinth;
    /**
     * Random-olio, jota käytetään satunnaisalgoritmeissa.
     */
    protected Random random;

    /**
     * Ottaa syötteenä labyrintin ja alustaa Random-olion.
     *
     * @param l Labyrintti, jolle algoritmi ajetaan.
     */
    public LabyrinthGenerator(Labyrinth l) {
        labyrinth = l;
        random = new Random();
    }

    /**
     * Labyrintin generoiva metodi.
     */
    abstract void generateLabyrinth();

    /**
     * Tyhjentää labyrintin, jos se ei ole tyhjä.
     *
     * @see main.Labyrinth
     */
    private void createEmptyLabyrinthIfNeeded() {
        if (labyrinth.labyrinth[0][0] != 0) {
            labyrinth.labyrinth = new byte[labyrinth.height][labyrinth.width];
        }
    }

    /**
     * Tulostusrutiini.
     */
    public void routine() {
        System.out.println(" (" + labyrinth.width + "x" + labyrinth.height + ")");
        createEmptyLabyrinthIfNeeded();
        long startTime = System.nanoTime() / 1000;
        generateLabyrinth();
        long microTime = System.nanoTime() / 1000 - startTime;
        String timeFormat = "," + (microTime % 1000) + " ms";
        if (microTime / 1000 >= 1000) {
            timeFormat = (microTime / 1000000) + " " + ((microTime / 1000) % 1000) + timeFormat;
        } else {
            timeFormat = (microTime / 1000) + timeFormat;
        }
        System.out.println("Generation time: " + timeFormat);
    }

    
}
