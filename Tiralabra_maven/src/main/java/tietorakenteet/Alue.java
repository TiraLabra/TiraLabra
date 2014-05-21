package tietorakenteet;

/**
 * Koko hakualuetta kuvaava luokka
 */
public class Alue {
    
    private int[][] intit;
    
    private Node[][] nodet;

    public Alue(int koko) {
        intit = new int[koko][koko];
        nodet = new Node[koko][koko];
    }
    
    
    /**
     * Metodi, joka luo esimerkkitaulukon
     */
    public void luoEsimerkkiTaulukko() {
        
        int[][] uusi2 = {
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} ,
            { 0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0} ,
            { 0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0} ,
            { 0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0} ,
            { 0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0} ,
            { 0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0} ,
            { 0,9,9,9,9,9,9,9,9,9,0,0,0,0,0,0} ,
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} ,
            { 0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,0} ,
            { 0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,0} ,
            { 0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,0} ,
            { 0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,0} ,
            { 0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,0} ,
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} ,
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} ,
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };
        
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {

                Node n = new Node(i, j, uusi2[i][j]);
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
    
}
