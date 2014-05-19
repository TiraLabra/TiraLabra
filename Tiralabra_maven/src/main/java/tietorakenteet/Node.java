package tietorakenteet;

/**
 * Node-luokka yksittäisiä haettavan alueen nodeja varten.
 * Sisältää paikkatiedot, ja A*-algoritmin tarvitsemat tietokentät.
 */
public class Node {

    private int x;
    private int y;
    
    private int etaisyysAlusta;
    private int etaisyysMaaliin;
    
    private Node edellinen;

    /**
     * Konstruktori, joka ottaa parametrainaan x- ja y-koordinaatit.
     * @param x
     * @param y 
     */
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        
        
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
    
    
    
    
}
