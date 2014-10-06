package viidensuora.ai;

import java.util.Random;
import viidensuora.logiikka.Ristinolla;

/**
 * Tässä nyt ei ole mitään järkeä.
 *
 * @author juha
 */
public class RandomEvaluoija implements Evaluointimetodi {

    private final Random rnd = new Random();

    public int evaluoiPelitilanne(Ristinolla rn) {
        return rnd.nextInt();
    }

}
