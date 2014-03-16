package pacman.peli;

import java.util.HashMap;
import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;

public class Haku {
    private HashMap<Peliruutu,Integer> alkuun;
    private HashMap<Peliruutu,Integer> loppuun;
    private HashMap<Peliruutu,Peliruutu> polku;
    
    public Haku() {
        alkuun = new HashMap<Peliruutu,Integer>();
        loppuun = new HashMap<Peliruutu, Integer>();
        polku = new HashMap<Peliruutu, Peliruutu>();
    }
    
    public Peliruutu aStar(Peliruutu lahto, Peliruutu maali, Pelialusta alusta) {
        
        for (int y = 0; y < alusta.getKorkeus(); y++) {
            for (int x = 0; x < alusta.getLeveys(); x++) {
                Peliruutu nykyinen = new Peliruutu(x, y);
                alkuun.put(nykyinen, Integer.MAX_VALUE);
                loppuun.put(nykyinen, etaisyys(maali, x, y));
                polku.put(nykyinen, null);
            }
        }
        
        
        return null;
    }
    
    public int etaisyys(Peliruutu maali, int x, int y) {
        int etaisyysarvio = (x-maali.getX())+(y-maali.getY());
        etaisyysarvio = Math.abs(etaisyysarvio);
        
        return etaisyysarvio;
    }
    
}
