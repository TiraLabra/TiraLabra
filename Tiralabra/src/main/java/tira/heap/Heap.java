package tira.heap;

import tira.common.Node;

/**
 *
 * @author joonaslaakkonen
 * Keko-tietorakenteen toteutus. Kekoa käytetään algoritmien suorituksessa. Keon taulukko pidetään minimikeon
 * mukaisessa järjestyksessä, eli paikassa 0 on aina solmu, jolla on pienin etäisyysarvo.
 */
public class Heap <T> {
    
    private Node[] table;
    private int size;
    
    public Heap (int size) {
        this.table = new Node[size];
        this.size = 0;
    }
    
    /**
     * Lisätään kekoon uusi alkio.
     * @param o lusättävä solmu.
     */
    public void insert (Node o) {
        if (this.size==0) {
            this.table[0] = o;
            this.size++;
        } else {
            this.heapInsert(o);
        }
    }
    
    /**
     * Otetaan keosta alkio, joka on taulukon paikassa 0.
     * @return pienin alkio.
     */
    public Node poll() {
        if(this.size != 0) {
            Node smallest = this.table[0];
            this.table[0] = this.table[this.size-1];
            this.size--;
            this.heapify(0);
            return smallest;
        }
        return null;     
    }
    
    /**
     * Tarkistetaan onko keko tyhjä.
     * @return 
     */
    public boolean empty() {
        return this.size == 0;
    }
    
    /**
     * Metodi kertoo keon koon.
     * @return keon koko.
     */
    public int size() {
        return this.size;
    }
    
    /**
     * Solmun lisäys kekoon. Metodi hakee taulukosta oikean paikan lisättävälle solmulle.
     * @param n lisättävä solmu.
     */
    private void heapInsert(Node n) {
        this.size++;
        int k = this.size-1;
        while (k > 0 && n.compareTo(this.table[(k-1)/2]) < 0) {
            Node parent = this.table[(k-1)/2];
            this.table[k] = parent;
            int p = (k-1)/2;
            k = p;
        }
        this.table[k] = n;
    }
    
    /**
     * Metodi tarkistaa, että kekoehto ei ole rikki muutosten jälkeen.
     * @param paikka kertoo mihin kohtaan kekoa muutos on tullus ja tarkistus alkaa tästä taulukon paikasta.
     */
    private void heapify(int paikka) {
        int l = 2*paikka + 1;
        int r = 2*paikka + 2;
        
        if (r < this.size) {
            Node smaller;
            int small;
            
            if (this.table[l].compareTo(this.table[r]) < 0) {
                smaller = this.table[l];
                small = l;
            } else {
                smaller = this.table[r];
                small = r;
            }
            if (this.table[paikka].compareTo(smaller) > 0) {
                Node oldRoot = this.table[paikka];
                Node root = this.table[small];
                this.table[paikka] = root;
                this.table[small] = oldRoot;
                this.heapify(small);
            }
        } else if(l==this.size-1 && this.table[paikka].compareTo(this.table[l]) > 0) {
            Node oldRoot = this.table[paikka];
            Node root = this.table[l];
            this.table[paikka] = root;
            this.table[l] = oldRoot;
        }
    }
}