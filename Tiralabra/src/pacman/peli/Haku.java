package pacman.peli;

import java.util.ArrayList;
import java.util.PriorityQueue;
import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;
import pacman.hahmot.Suunta;

public class Haku {

    private PriorityQueue<Peliruutu> solmulista;
    private ArrayList<Peliruutu> kaydyt;
    private PriorityQueue<Peliruutu> polku;
    
    public Haku(){

        kaydyt = new ArrayList<Peliruutu>();
        solmulista = new PriorityQueue<Peliruutu>();
        polku = new PriorityQueue<Peliruutu>();        

    }
    
    public Peliruutu aStar(Peliruutu lahto, Peliruutu maali, Pelialusta alusta) {
        alustus(alusta, maali);
        
        lahto.setEtaisyysAlkuun(0);
        solmulista.add(lahto);
        kaydyt.clear();
        
        while(!solmulista.isEmpty()) {
            Peliruutu ruutu = solmulista.poll();
            if(!kaydyt.contains(ruutu)) {
                kaydyt.add(ruutu);
                if(ruutu.equals(maali)) {
                    return polku.poll();
                }
                
            }
        }
        
        
        
        return null;
    }

    private void alustus(Pelialusta alusta, Peliruutu maali) {
        for (int y = 0; y < alusta.getKorkeus(); y++) {
            for (int x = 0; x < alusta.getLeveys(); x++) {
                alusta.getPeliruutu(x, y).setEtaisyysAlkuun(Integer.MAX_VALUE);
                alusta.getPeliruutu(x, y).setEtaisyysMaaliin(etaisyys(maali, alusta.getPeliruutu(x, y)));
//                kaymattomat.add(alusta.getPeliruutu(x, y));                
            }
        }        
    }
    
    public int etaisyys(Peliruutu maali, Peliruutu ruutu) {
        int etaisyysarvio = (ruutu.getX()-maali.getX())+(ruutu.getY()-maali.getY());
        etaisyysarvio = Math.abs(etaisyysarvio);
        
        return etaisyysarvio;
    }
    
}
