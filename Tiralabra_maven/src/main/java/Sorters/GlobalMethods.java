package Sorters;

/**
 *
 * @author nkostiai
 * 
 * Luokkaan GlobalMethods kootaan simppeleitä ja yleiskäyttöisiä metodeita, joita käytetään eri järjestämisalgoritmeissä.
 * 
 */
public class GlobalMethods {

    /**
     *
     *Vaihtaa kahden taulukon alkion paikkaa.
     * 
     * @param array Taulukko josta vaihdetaan.
     * @param a Ensimmäinen alkio jota vaihdetaan.
     * @param b Toinen alkio jota vaihdetaan.
     */
    public static void exchange(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

}
