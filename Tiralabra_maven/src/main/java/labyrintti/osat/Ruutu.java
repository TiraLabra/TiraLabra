package labyrintti.osat;

public class Ruutu {
    /**
     * Ruudun arvo.
     */
    private int arvo;
    /**
     * Ruudun x-koordinaatti.
     */
    private int x; //rivi
    /**
     * Ruudun y-koordinaatti.
     */
    private int y; //sarake

    public Ruutu(int arvo, int x, int y) {
        this.arvo = arvo;
        this.x = x;
        this.y = y;
    }

    public int getArvo() {
        return arvo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return ""+arvo;
    }
    
    
}
