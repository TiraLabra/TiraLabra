package tietorakenteet;

/**
 * Node-luokka yksittäisiä haettavan alueen nodeja varten.
 * Sisältää paikkatiedot, ja A*-algoritmin tarvitsemat tietokentät.
 */
public class Node implements Comparable<Node> {

    private static int MAKSIMIARVO_KULJETTAVUUDELLE = 5;
    
    private int x;
    private int y;
    
    private int etaisyysAlusta;
    private int etaisyysMaaliin;
    
    private Node edellinen;
    
    //tarvitaankohan...
    private int syvyys;
    
    private int kustannus;
    
    
    /**
     * Konstruktori, joka ottaa parametrainaan x- ja y-koordinaatit.
     * @param x
     * @param y 
     * @param kustannus nodeen kulkemisen kustannus, oletus 0
     */
    public Node(int x, int y, int kustannus) {
        this.x = x;
        this.y = y;
        this.kustannus = kustannus;
    }
    
    public Node(int x, int y) {
        this(x, y, 0);
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
        return this.x+", " + this.y + " alusta: " + this.etaisyysAlusta;
    }
    
    
    
}
