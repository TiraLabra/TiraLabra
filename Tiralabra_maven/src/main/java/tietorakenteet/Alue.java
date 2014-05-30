package tietorakenteet;

/**
 * Koko hakualuetta kuvaava luokka
 */
public class Alue {
    
    private int[][] intit;
    
    private int x;
    private int y;
    
    private Node[][] nodet;
    
    public Alue(int koko) {
        intit = new int[koko][koko];
        nodet = new Node[koko][koko];
        x = y = koko;
    }
    
    
    /**
     * Metodi, joka luo esimerkkitaulukon
     */
    public void luoEsimerkkiTaulukko() {
        
        int[][] numeroina = {
            //0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} , // 0
            { 0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0} , // 1
            { 0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0} , // 2
            { 0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0} , // 3
            { 0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0} , // 4
            { 0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0} , // 5
            { 0,9,9,9,9,9,9,9,9,9,9,9,9,9,9,0} , // 6
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} , // 7
            { 0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,0} , // 8
            { 0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,0} , // 9
            { 0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,0} , // 0
            { 0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,0} , // 1
            { 0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,0} , // 2
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} , // 3
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} , // 4
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}   // 5
        };
        
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {

                Node n = new Node(i, j, numeroina[i][j]);
                nodet[i][j] = n;
            }
            
        }
        
    }
    
    /**
     * Pieni testitaulukko jolla helpompi tutkia algoritmin perustoimintaa.
     * 
     */
    public void luoPieniTestitaulukko() {
        int[][] numeroina = {
            //0 1 2 3 4 5 6 7
            { 0,0,0,0,0,0,0,0} , // 0
            { 0,0,0,9,9,9,9,0} , // 1
            { 0,9,9,9,0,0,0,0} , // 2
            { 0,0,0,0,0,0,0,0} , // 3
            { 0,0,0,9,0,0,0,0} , // 4
            { 0,0,0,9,0,9,0,0} , // 5
            { 0,0,0,9,0,0,0,0} , // 6
            { 0,0,0,0,0,0,0,0}   // 7
        };
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Node n = new Node(i, j, numeroina[i][j]);
                nodet[i][j] = n;
            }
            
        }
        
    }
    
    /**
     * Palauttaa halutuista koordinaateista noden tiedot
     * @param x
     * @param y
     * @return 
     */
    public Node getnode(int x, int y) {
        return this.nodet[x][y];
    }
    
    @Override
    public String toString() {
        String tuloste = "";
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                tuloste = tuloste + nodet[i][j].getKustannus();
            }
            tuloste = tuloste + "\n";
        }
        
        return tuloste;
    }

    public int getKorkeus() {
        return x;
    }

    public int getLeveys() {
        return y;
    }
    
}
