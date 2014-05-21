package tietorakenteet;

/**
 * Node-luokka yksittäisiä haettavan alueen nodeja varten.
 * Sisältää paikkatiedot, ja A*-algoritmin tarvitsemat tietokentät.
 */
public class Node implements Comparable<Node> {

    private int x;
    private int y;
    
    private int etaisyysAlusta;
    private int etaisyysMaaliin;
    
    private Node edellinen;
    
    private int syvyys;
    
    private int kustannus;
    
    
    /**
     * Konstruktori, joka ottaa parametrainaan x- ja y-koordinaatit.
     * @param x
     * @param y 
     */
    public Node(int x, int y, int kustannus) {
        this.x = x;
        this.y = y;
        this.kustannus = kustannus;
        
        
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
     * Nodejen välisen suuruusvertailut toteuttava metodi
     * @param node vertailtava toinen node
     * @return
     */    
    public int compareTo(Node node) {
        
        return (this.etaisyysAlusta+this.etaisyysMaaliin) - (n.etaisyysAlusta+n.etaisyysMaaliin);
    }
    
    
    
    
}
