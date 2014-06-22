package suorituskyky;

import tietorakenteet.ArrayListOma;

/**
 * Luokka jolla voi testailla hieman ArrayListOma-luokan suorituskykyä.
 * Tarkoitettu muokattavaksi kooditasolla ja Netbeansista ajettavaksi.
 */
public class ArrayListOmaSuorituskyky {
    
    private ArrayListOma lista;

    private static int maara = 10000000;
    private static int alkukoko = 1000;
    
    public ArrayListOmaSuorituskyky() {
    }
    
    public void suorita() {
        
        lista = new ArrayListOma(alkukoko);

        long alkuaika = System.currentTimeMillis();

        for (int i = 0; i < maara; i++) {
            lista.lisaa(i);
        }
        long kesto = System.currentTimeMillis() - alkuaika;
        System.out.println(maara + " alkion lisäys kesti " + kesto + ".");
        
    }
    
}
