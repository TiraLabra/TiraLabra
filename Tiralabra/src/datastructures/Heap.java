/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 *
 * @author joonaskylliainen
 */
public class Heap {
    
    private Node[] keko;
    private int heapSize;
    private int length;
    
    public Heap(int size) {
        heapSize = size;
        keko = new Node[size];
        length = 0;
    }
    
    /**
     * palauttaa ndeksiä vastaavan olion vanhemman
     * @param i indeksi
     * @return vanhempi
     */
    public int parent(int i) {
        return i/2;
    }
    /**
     * palauttaa ndeksiä vastaavan olion vasemman lapsen
     * @param i indeksi
     * @return vasen lapsi
     */
    public int left(int i) {
        return 2*i;
    }
    /**
     * palauttaa ndeksiä vastaavan olion oikean lapsen
     * @param i indeksi
     * @return oikea lapsi
     */
    public int right(int i) {
        return 2*i + 1;
    }
    /**
     * järjestää keon uudelleen keoksi
     * @param i indeksi josta lähdetään järjestämään
     */
    public void heapify(int i) {
        int l = left(i);
        int r = right(i);
        if (r <= heapSize) {
            if (keko[i].compareTo(keko[left(i)]) > 0 || keko[i].compareTo(keko[right(i)]) > 0) {
            
                if (keko[left(i)].compareTo(keko[right(i)]) < 0) {
                
                    vaihda(i, left(i));
                    heapify(left(i));
                }else {
                
                    vaihda(i, right(i));
                    heapify(right(i));
                }
            }
        }
    }
    
    /**
     * poistaa pienimmän solmun eli ensimmäisen alkion
     * @return poistettu solmu
     */
    public Node delMin() {
        Node min = keko[0];
        keko[0] = keko[this.length];
        this.length = this.length - 1;
        heapify(0);
        return min;
    }
    
    /**
     * lisää uuden solmun kekoon
     * @param n uusi olmu
     */
    public void insert(Node n) {
        this.length = this.length + 1;
        int i = this.length;
        while (keko[i].compareTo(keko[parent(i)]) < 0) {
            vaihda(i,parent(i));
            i = parent(i);        
        }
    }
    
    /**
     * vaihtaa kahden solmun paikkaa päittäin
     * @param i toinen solmu
     * @param j toinen solmu
     */
    public void vaihda(int i, int j) {
        Node temp = keko[i];
        keko[i] = keko[j];
        keko[j] = temp;
    }
}
