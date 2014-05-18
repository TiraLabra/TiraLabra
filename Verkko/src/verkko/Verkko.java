package verkko;

import java.util.Comparator;
import util.Keko;

/**
 *
 * @author Arvoitusmies
 */
public class Verkko {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer t, Integer t1) {
                if (t < t1) {
                    return -1;
                }
                if (t1 < t) {
                    return 1;
                }
                return 0;
            }
        };
        // TODO code application logic here
        Keko<Integer> k = new Keko<Integer>(comparator);
        Integer[] lisattavat = {4, 7, 1, 6,0,10,15,2,20,-1,-2};
        for (Integer integer : lisattavat) {
            k.lisaa(integer);
        }
        for (int i = 0; i < lisattavat.length; i++) {
            final Integer poista = k.poista();
            System.out.println(poista);
        }

    }
    private static Comparator<Integer> comparator;

}
