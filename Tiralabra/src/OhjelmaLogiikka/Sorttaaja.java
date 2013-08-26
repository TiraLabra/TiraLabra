package OhjelmaLogiikka;

import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMinimiPriorityQueue;
import java.util.Comparator;

/**
 * Apuluokka. Sorttaa annetun listan annetulla komparaattorilla pienimmästä
 * suurimpaan arvoon
 */
public class Sorttaaja {

    /**
     * Sorttaa annetun listan annetulla komparaattorilla pienimmästä suurimpaan
     * arvoon
     *
     * @param <T> Sortattavan tiedon tyyppi (esim. Integer, Double)
     * @param lista lista jossa on arvot jotka sortataan
     * @param comparator Comparator-rajapinnan toteuttava objekti joka vastaa vertailusta
     * @return Lista jossa arvot järjestettyna pienimmästä suurimpaan
     */
    public <T> OmaList<T> sorttaaKasvavaanJarjestykseen(OmaList<T> lista, Comparator<T> comparator) {
        OmaList<T> paluu = new OmaArrayList<T>();

        OmaMinimiPriorityQueue<T> heapsort = new OmaMinimiPriorityQueue<T>(comparator);

        for (int i = 0; i < lista.size(); ++i) {
            heapsort.push(lista.get(i));
        }

        while (!heapsort.isEmpty()) {
            paluu.add(heapsort.pop());
        }

        return paluu;
    }
}
