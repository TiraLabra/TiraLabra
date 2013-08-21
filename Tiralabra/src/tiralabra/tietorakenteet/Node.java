/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tietorakenteet;

/**
 * Nodeista luodaan huffman puu
 * @author Joonas
 */
public class Node implements Comparable<Node> {
    
    private int toistot;
    private int merkki;
    private Node vasen;
    private Node oikea;
    
    /**
     * Luo uuden Noden parametreillä merkin koodi ja sen toistot tiedostossa
     * @param merkki
     * @param toistot 
     */

    public Node(int merkki, int toistot) {
        this.toistot = toistot;
        this.merkki = merkki;
        this.vasen = null;
        this.oikea = null;
    }
    
    /**
     * Toistojen getteri.
     * @return 
     */
    
    public int getToistot() {
        return this.toistot;
    }
    
    /**
     * Asettaa Nodelle vasemman lapsen.
     * @param vasen 
     */

    public void setVasen(Node vasen) {
        this.vasen = vasen;
    }
    
    /**
     * Asettaa Nodelle oikean lapsen.
     * @param oikea 
     */

    public void setOikea(Node oikea) {
        this.oikea = oikea;
    }
    
    /**
     * Getteri oikealle lapselle.
     * @return 
     */
    
    public Node getOikea() {
        return this.oikea;
    }
    
    /**
     * Getteri vasemmalle lapsele.
     * @return 
     */

    public Node getVasen() {
        return this.vasen;
    }

    /**
     * Nodejen vertailu joka määrittää sen sijainnin prioriteettijonossa
     * @param node
     * @return 
     */
    
    @Override
    public int compareTo(Node node) {
        return this.toistot - node.toistot;
    }
    
    /**
     * Setteri merkille.
     * @param merkki 
     */
    
    public void setMerkki(int merkki) {
        this.merkki = merkki;
    }
    
    /**
     * Getteri merkille.
     * @return 
     */
    
    public int getMerkki() {
        return this.merkki;
    }
    
    /**
     * toString overrideri Nodejen helpompaan tulostamiseen.
     * @return 
     */
    
    @Override
    public String toString(){
        return "(merkki: " + this.merkki + " toistot: " + this.toistot + " vasen: " + this.vasen + " oikea: " + this.oikea + " )";
    }
}
