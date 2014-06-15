package lib.io;

import java.io.IOException;
import lib.datastructures.LinkedQueue;
import lib.utils.ByteAsBits;

/**
 * Sisääntulopuskuri. Lukee tiedostoa ja lisää tavut bittivirtaan. Palauttaa halutun kokoisia bittijonoja.
 * @author Iiro
 */
public class InputBuffer {
    private final LinkedQueue<Boolean> list;
    private final IO io;
    
    
    public InputBuffer(IO io){
        list = new LinkedQueue<Boolean>();
        this.io = io;
    }
    /**
     * Palauttaa n-pituisen bittijonon.
     * @param n Jonon pituus.
     * @return Bittijono boolean-taulukkona.
     */
    public boolean[] nextBits(int n) {
        if(list.size() >= n){
            boolean[] bits = new boolean[n];
            for(int i = 0; i < n; i++){
                bits[i] = list.dequeue();
            }
            return bits;
        } else return null;
    }
    /**
     * Lukee tiedostosta kunnes bittivirrassan on tarpeeksi bittejä. 
     * @throws IOException 
     */
    public void read() throws IOException{
        while(list.size() < 17){
            ByteAsBits bits = io.read();
            if(bits == null){ return; } 
            for(boolean b : bits.getAllBits()){
                list.enqueue(b);
            }
        }
    }
}
