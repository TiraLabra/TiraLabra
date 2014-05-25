
import java.util.PriorityQueue;

/**
 *Minimikekoa käytetään solmujen järjestämiseen.
 */
public class Minimikeko {
    /**
     * Alustavasti käytetään Javan valmista prioriteettijonoa.
     */
    private PriorityQueue<HuffmanSolmu> keko;

    /**
     *
     */
    public Minimikeko() {
        keko = new PriorityQueue();
    }
    
    /**
     *Palauttaa keon koon.
     * @return Keossa olevien solmujen lukumäärä.
     */
    public int koko() {
        return keko.size();
    }
    
    /**
     *Palauttaa pienimmän solmun keon päältä. Pienin solmu on se, jonka esintyymiskerrat-arvo on pienin.
     * @return Keon pienin solmu.
     */
    public HuffmanSolmu pienin() {
        return keko.poll();
    }
    
    /**
     *Lisää kekoon solmun.
     * @param solmu Lisättävä solmu.
     */
    public void lisaa(HuffmanSolmu solmu) {
        keko.offer(solmu);
    }
    
    
}
