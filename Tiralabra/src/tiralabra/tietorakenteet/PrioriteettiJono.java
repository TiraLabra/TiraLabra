/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tietorakenteet;

/**
 *
 * @author joonaslongi
 */
public class PrioriteettiJono {
    
    /**
     * Eri merkkejä voi olla vain 256
     */
    private final static int MAKSIMIKOKO = 256;
    
    private Node[] keko;
    
    private int koko;
    
    /**
     * Alustaa prioriteettijonon, joka toteutetaan kekona.
     */
    public PrioriteettiJono(){
        this.koko = 0;
        this.keko = new Node[MAKSIMIKOKO];
    }
    
    /**
     * Palauttaa true jos keko on tyhjä, muuten false.
     * @return 
     */
    
    public boolean tyhja(){
        return koko == 0;
    }
    
    /**
     * Palauttaa keon koon.
     * @return 
     */
    
    public int koko(){
        return this.koko;
    }
    
    /**
     * Lisää kekoon noden oikealle paikalleen.
     * @param node 
     */
    
    public void lisaa(Node node){
        if(koko < MAKSIMIKOKO){
            koko++;
            int indeksi = koko -1;
            while( indeksi > 0 && keko[indeksi / 2].compareTo(node) > 0){
                keko[indeksi] = keko[indeksi / 2];
                indeksi = indeksi/2;
            }
            keko[indeksi] = node;
        }
    }
    
    
    /**
     * Ottaa ja poistaa keosta pienimmän noden.
     * @return 
     */
    
    public Node ota(){
        Node pienin = keko[0];
        keko[0] = keko[koko -1];
        koko--;
        korjaaYlos(0);
        return pienin;
    }
    
    /**
     * Vaihtaa kahden noden paikkaa keskenään.
     * @param indeksi1
     * @param indeksi2 
     */
    
    public void vaihda(int indeksi1, int indeksi2){
        Node node = keko[indeksi1];
        keko[indeksi1] = keko[indeksi2];
        keko[indeksi2] = node;
    }
    
    /**
     * Korjaa keon annetusta indeksistä alaspäin
     * @param indeksi 
     */
    
    public void korjaaYlos(int indeksi){
        int vasen = 2 * indeksi;
        int oikea = 2 * indeksi + 1;
        int pienin = indeksi;
        if(koko > vasen && keko[vasen].compareTo(keko[indeksi]) <0 ){
            pienin = vasen;
        }
        if(koko > oikea && keko[oikea].compareTo(keko[pienin]) < 0){
            pienin = oikea;
        }
        if(pienin != indeksi){
            vaihda(indeksi, pienin);
            korjaaYlos(pienin);
        }
    }
    
    /**
     * palauttaa keon.
     * @return 
     */
    
    public Node[] getKeko(){
        return this.keko;
    }

    
}
