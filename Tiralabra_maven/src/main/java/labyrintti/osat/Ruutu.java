package labyrintti.osat;

public class Ruutu {
    private int arvo;
    private int x; //sarake
    private int y; //rivi

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
