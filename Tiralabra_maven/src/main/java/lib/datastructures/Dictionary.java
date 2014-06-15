package lib.datastructures;

import java.util.HashMap;
import lib.utils.ArrayUtils;

/**
 * LZW-algoritmien käyttämä hakemisto. Hakemistossa on jokaista alkutavua kohtaan oma binäärihakupuu. Tavujonot tallennetaan taulukkoon, johon löytyy viitteet hakupuista.
 * @author Iiro
 */
public class Dictionary {
    private int maxSize;
    private int last;
    private BinaryTreeNode[] trees; //Sisältää puiden juurisolmut. Puissa on viitteet taulukkoon, ei tavujonoja itsessään. 
    private byte[][] table; //Taulukko, johon tavujonot tallennetaan.
    
    public Dictionary(int maxSize){
        this.maxSize = maxSize;
        last = 0;
        trees = new BinaryTreeNode[256];
        table = new byte[maxSize][];
        initialize();
    }
    /**
     * Lisää tavujonon hakemistoon. 
     * @param bytes tavujono.
     */
    public void add(byte[] bytes){
       if(last < maxSize){
           table[last] = bytes;           
           BinaryTreeNode parent = trees[bytes[0]+128];
           if(parent == null){
               trees[bytes[0]+128] = new BinaryTreeNode(last);
           } else{
                addToTree(bytes, parent);           
           }
           
           last++;
       }        
    }
    /**
     * Lisää parent-solmun määräämään puuhun annetun tavujonon.
     * @param bytes
     * @param parent 
     */
    private void addToTree(byte[] bytes, BinaryTreeNode parent) {
        while(true){            
            int bigger = compareArrays(bytes, table[parent.getKey()]);
            if(bigger == 1){
                if(parent.getRight() == null){
                    parent.setRight(new BinaryTreeNode(last));
                    break;
                } else{
                    parent = parent.getRight();
                }
            } else if(parent.getLeft() == null){
                parent.setLeft(new BinaryTreeNode(last));
                break;
            } else {
                parent = parent.getLeft();
            }
        }
    }
    /**
     * Alustaa hakemiston luoden puihin yksittäisiä tavuja vastaavat taulukot. 
     */
    private void initialize(){
        byte b = -128;
        for(int i = 0; i < 256; i++){
            add(new byte[]{b});
            b++;
        }
        
    }
    
    /**
     * Vertaa tavujonoja. Jonoja verrataan kuin desimaalilukuja. Siis verrataan ensimmäisiä tavuja, ja vain jos ne ovat samat verrataan seuraavia. Mikäli jonot ovat eripituiset, 
     * ja lyhyemmän matkalta samat, on pidempi aina suurempi. 
     * @param a
     * @param b
     * @return 1 jos a on suurempi, -1 jos b on suurempi, 0 jos yhtäsuuret. 
     */
    private int compareArrays(byte[] a, byte[] b){        
        for(int i = 0; i < Math.min(a.length, b.length); i++){
            if(a[i] > b[i]){
                return 1;
            } if(b[i] > a[i]){
                return -1;
            }                      
        }
        if(a.length > b.length){
            return 1;
        } else if(b.length > a.length){
            return -1;
        }
        return 0;       
    }
    /**Hakee tavujonon annetusta puusta.      
     * @param parent puun juurisolmu
     * @param bytes haettava tavujono
     * @return viitteen taulukkoon jos jono löytyy puusta, muuten -1.
     */
    private int searchFromTree(BinaryTreeNode parent,byte[] bytes){
        while(true){
            if(parent == null){
                return -1;
            }
            int bigger = compareArrays(bytes, table[parent.getKey()]);
            if(bigger == 0){
                return parent.getKey();                
            } if(bigger == 1){
                parent = parent.getRight();
            } else {
                parent = parent.getLeft();
            }                    
        }
    }
    
    
    public boolean contains(byte[] bytes){
        int index = searchFromTree(trees[bytes[0]+128] ,bytes);
        return index != -1;
    }
    /**
     * Palauttaa tavujonon taulukkoviitteen. 
     * @param bytes
     * @return -1, jos tavujono ei ole hakemistossa. 
     */
    public int get(byte[] bytes){
        int index = searchFromTree(trees[bytes[0]+128] ,bytes);
        return index;        
    }
    /**
     * Palauttaa tavujonon taulukon paikassa i. 
     * @param i
     * @return 
     */
    public byte[] get(int i){
        return table[i];
    }
    
    
}
