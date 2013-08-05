/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

/**
 *
 * @author Joonas
 */
public class Node implements Comparable<Node> {
    
    private int toistot;
    private int merkki;
    private Node vasen;
    private Node oikea;

    public Node(int merkki, int toistot) {
        this.toistot = toistot;
        this.merkki = merkki;
        this.vasen = null;
        this.oikea = null;
    }

    public int getToistot() {
        return this.toistot;
    }

    public void setVasen(Node vasen) {
        this.vasen = vasen;
    }

    public void setOikea(Node oikea) {
        this.oikea = oikea;
    }

    public Node getOikea() {
        return this.oikea;
    }

    public Node getVasen() {
        return this.vasen;
    }


    @Override
    public int compareTo(Node node) {
        return this.toistot - node.toistot;
    }

    public void setMerkki(int merkki) {
        this.merkki = merkki;
    }

    public int getMerkki() {
        return this.merkki;
    }
    @Override
    public String toString(){
        return "(merkki: " + this.merkki + " toistot: " + this.toistot + " vasen: " + this.vasen + " oikea: " + this.oikea + " )";
    }
}
