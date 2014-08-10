/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pakkaaja;

/**
 * Solmu
 * @author joonaskylliainen
 */
public class Node implements Comparable<Node>{
    
    private char character;
    private int frequency;
    private Node parent;
    private Node left;
    private Node right;
    
    public Node(char c, int f) {
        this.character = c;
        this.frequency = f;
    }
    
    /**
     * palauttaa merkin
     * @return
     */
    public char getChar() {
        return this.character;
    }
    /**
     * palauttaa esiintymistaajuuden
     * @return
     */
    public int getFrequency() {
        return this.frequency;
    }
    /**
     * palauttaa vasemman lapsen
     * @return vasen lapsi
     */
    public Node getLeft() {
        return this.left;
    }
    /**
     * palauttaa oikean lapsen
     * @return oikea lapsi
     */
    public Node getRight() {
        return this.right;
    }
    /**
     * palauttaa vanhemman
     * @return vanhempi
     */
    public Node getParent() {
        return this.parent;
    }
    /**
     * asettaa esiintymistaajuuden
     * @param f taajuus
     */
    public void setFrequency(int f) {
        this.frequency = f;
    }
    /**
     * asettaa vanhemman
     * @param p vanhempi
     */
    public void setParent(Node p) {
        this.parent = p;
    }
    /**
     * asettaa vasemman lapsen
     * @param l vasen lapsi
     */
    public void setLeft(Node l) {
        this.left = l;
    }
    /**
     * asettaa oikean lapsen
     * @param r oikea lapsi
     */
    public void setRight(Node r) {
        this.right = r;
    }
    
    /**
     * kasvattaa taajuutta yhdellä
     */
    public void increaseFrequencyByOne() {
        this.frequency++;
    }
    
    /**
     * vertaa solmuja merkin perusteella
     * @param nod verrattava solmu
     * @return
     */
    public boolean equals(Node nod) {
        if (nod.getChar() == this.getChar()) {
            return true;
        }
        return false;
    }
    /**
     * vertaa solmuja taajuuden perusteella
     * @param nod verrattava solmu
     * @return
     */
    @Override
    public int compareTo(Node nod) {
        if (nod.frequency < this.frequency) {
            return 1;
        }
        else if (nod.frequency == this.frequency) {
            return 0;
        }
        else {
            return -1;
        }
    }

}
