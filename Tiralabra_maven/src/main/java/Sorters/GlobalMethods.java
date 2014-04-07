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
    
    /**
     * Kopioi taulukon annetulta alueelta.
     * 
     * @param arrayToCopy Taulukko, joka kopioidaan.
     * @param first Kopioitava alueen ensimmäinen indeksi (inclusive)
     * @param last Kopioitavan alueen viimeinen indeksi (exclusive)
     * @return Kopioitu taulukko
     */
    public static int[] copyRange(int[] arrayToCopy, int first, int last){
        int[] newArray = new int[last - first];
        int i = 0;
        for(int j = first; j < last; j++){
            newArray[i] = arrayToCopy[j];
            i++;
        }
        return newArray;
    }

}
