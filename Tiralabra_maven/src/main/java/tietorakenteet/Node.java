package tietorakenteet;

/**
 * Node-luokka yksittäisiä haettavan alueen nodeja varten.
 * Sisältää paikkatiedot, ja A*-algoritmin tarvitsemat tietokentät.
 */
public class Node implements Comparable<Node> {

    private static int MAKSIMIARVO_KULJETTAVUUDELLE = 5;
    
    private int rivi;
    private int sarake;
    
    private int etaisyysAlusta;
    
    /**
     * 
     */
    private int etaisyysMaaliin;
    
    /**
     * AStar-hakua varten tieto edellisestä nodesta, josta tähän tultu.
     */
    private Node edellinen;
    
    //tarvitaankohan...
    private int syvyys;
    
    /**
     * Nodeen siirtymisen kustannus.
     */
    private int kustannus;
    
    
    /**
     * Konstruktori, joka ottaa parametrinaan noden koordinaatit.
     * @param rivi
     * @param sarake 
     * @param kustannus nodeen kulkemisen kustannus, oletus 0
     */
    public Node(int rivi, int sarake, int kustannus) {
        this.rivi = rivi;
        this.sarake = sarake;
        this.kustannus = kustannus;
    }
    
    public Node(int rivi, int sarake) {
        this(rivi, sarake, 0);
    }

    public Node getEdellinen() {
        return edellinen;
    }

    public int getEtaisyysAlusta() {
        return etaisyysAlusta;
    }

    public int getEtaisyysMaaliin() {
        return etaisyysMaaliin;
    }

    public int getRivi() {
        return rivi;
    }

    public int getSarake() {
        return sarake;
    }

    public int getKustannus() {
        return kustannus;
    }

    public void setKustannus(int kustannus) {
        this.kustannus = kustannus;
    }
    
    public void setEtaisyysAlusta(int etaisyysAlusta) {
        this.etaisyysAlusta = etaisyysAlusta;
    }

    public void setEtaisyysMaaliin(int etaisyysMaaliin) {
        this.etaisyysMaaliin = etaisyysMaaliin;
    }
    
    public void setEdellinen(Node edellinen) {
        this.edellinen = edellinen;
    }
    
    /**
     * Kertoo, onko kyseinen node kuljettavissa.
     * Tosin sanoen palauttaa false, kun kyseessä on seinä tms.
     * @return 
     */
    public boolean kuljettavissa() {
        if (kustannus > MAKSIMIARVO_KULJETTAVUUDELLE )
            return false;
        else
            return true;
    }
    /**
     * Nodejen välisen suuruusvertailut toteuttava metodi
     * @param node vertailtava toinen node
     * @return
     */    
    public int compareTo(Node node) {
        
        return (this.etaisyysAlusta+this.etaisyysMaaliin) - (node.etaisyysAlusta+node.etaisyysMaaliin);
    }

    @Override
    public String toString() {
        return this.rivi+", " + this.sarake + " alusta: " + this.etaisyysAlusta + " maaliin: " + this.etaisyysMaaliin;
    }
    
    
    
}
