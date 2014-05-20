
import java.util.PriorityQueue;


public class Minimikeko {
    private PriorityQueue<HuffmanSolmu> keko;

    public Minimikeko() {
        keko = new PriorityQueue();
    }
    
    public int koko() {
        return keko.size();
    }
    
    public HuffmanSolmu pienin() {
        return keko.poll();
    }
    
    public void lisaa(HuffmanSolmu solmu) {
        keko.offer(solmu);
    }
    
    
}
