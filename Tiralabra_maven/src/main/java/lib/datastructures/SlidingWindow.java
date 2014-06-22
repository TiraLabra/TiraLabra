package lib.datastructures;

import lib.utils.ByteAsBits;

/**
 * LZ77 -pakkaajan käyttämä liukuikkuna. 
 * @author Iiro
 */
public class SlidingWindow{
    private final SlidingTable window;
    private final LinkedQueue<Integer>[] table;
    
    public SlidingWindow(int size){
        window = new SlidingTable(size);
        table = new LinkedQueue[256]; // taulukkoon tallennetaan jono jokaista tavun arvoa kohtaan. Arvojen ilmentymien indeksit lisätään jonoon. 
    }
    /**
     * Lisää alkion ikkunaan. Alkio lisätään SlidingTable-taulukkoon ja taulukon indeksi oikeaan jonoon. Taulukosta pois putoava alkio poistetaan ja poistetaan. Myös indeksi putoaa 
     * jonosta.
     * @param b lisättävä tavu.
     * @return null, jos taulukko ei ole täynnä.
     */
    public ByteAsBits add(byte b){
        ByteAsBits ret = window.add(b);
        int lastIndex = window.getLastIndex();
        
        if(table[(int)b +128] == null){
            table[(int)b +128] = new LinkedQueue<Integer>();
        }        
        table[(int) b+128].enqueue(lastIndex);
        
        if(ret != null){
            table[ret.getInt()].dequeue();
        }
        
        return ret;
    }
    /**
     * Etsii ikkunasta pisimmän annetun tavujonon alkuosaa vastaavan osajonon. Palauttaa int-parin. Ensimmäinen luku ilmaisee osuman pituuden, toinen indeksin alkaen SlidingTablen 
     * liukuvasta nollaindeksistä.
     * @param bytes 
     * @return null, jos yhtään yhtä merkkiä pidempää osumaa ei löydy.
     */
    public int[] findBestMatch(LinkedQueue<Byte> bytes){
        LinkedQueue<Integer> list = table[(int)bytes.getFirst()+128];
        if(list == null) return null;
 
        int bestLength = 0;
        int offset = 0;
        
        for(int k: list){
            int i = window.relativeIndex(k);
            int l = matchLength(bytes, i);
            if(l > bestLength){
                bestLength = l;
                offset = i;
            }
        }
        if(bestLength < 2) return null;

        return new int[]{offset, bestLength};
    }
    /**
     * Palauttaa pisimmän bytes-jonoa vastaavan osajonon pituuden alkaen ikkunan indeksistä i. 
     * @param bytes
     * @param index
     * @return osajonon pituus.
     */
    private int matchLength(LinkedQueue<Byte> bytes, int index) {
        int l = 0;
        int j = 0;
        for(byte m: bytes){
            if(m != window.get(index+j)){
                break;
            } else {
                l++;
                j++;
            }
        }
        return l;
    }
    
    
}
