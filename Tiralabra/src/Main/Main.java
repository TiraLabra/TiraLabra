package Main;

import Tietorakenteet.DiskreettiSolmu;
import Tietorakenteet.Keko.Iteroitava;
import Tietorakenteet.Keko.Keko;
import Tietorakenteet.Kordinaatti;
import java.util.HashMap;

/*
 * 
 * Toimii tällä hetkellä lähinnä debuggaus metodina
 * 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(Math.pow(2, 3));
      //  testaaHashMap();

    }

    public static void testaaHashMap() {
        
        HashMap<Kordinaatti, Integer> mappi = new HashMap<Kordinaatti, Integer>();
        Kordinaatti a = new Kordinaatti(1, 1);
        Kordinaatti b = new Kordinaatti(1, 2);
        Kordinaatti c = new Kordinaatti(1, 1);
        mappi.put(a, 12);
        
        System.out.println(mappi.get(c));
        mappi.put(c, 42);
        System.out.println(mappi.get(a));
    }

}
