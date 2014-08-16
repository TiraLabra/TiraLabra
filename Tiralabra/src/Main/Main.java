package Main;

import java.util.HashMap;
import tietorakenteet.keko.Iteroitava;

import tietorakenteett.DiskreettiSolmu;
import tietorakenteett.Kordinaatti;

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
