package labyrintti.osat;

public class Ruutu implements Comparable<Ruutu> {

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
    private int etaisyysLoppuun;
    private int etaisyysAlkuun;
    private boolean kayty;

    public Ruutu(int arvo, int x, int y) {
        this.arvo = arvo;
        this.x = x;
        this.y = y;
        etaisyysAlkuun = Integer.MAX_VALUE;
        etaisyysLoppuun = Integer.MAX_VALUE;
        kayty = false;
    }

    public void laskeEtaisyysMaaliin(int maaliX, int maaliY) {
        etaisyysLoppuun = Math.abs((x - maaliX) + (y - maaliY));
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

    public int getEtaisyysAlku() {
        return etaisyysAlkuun;
    }

    public int getEtaisyysLoppu() {
        return etaisyysLoppuun;
    }

    public int getEtaisyyksiensumma() {
        return etaisyysAlkuun + etaisyysLoppuun;
    }

    public boolean onkoKayty() {
        return kayty;
    }

    public void setEtaisyysAlku(int etaisyysAlku) {
        this.etaisyysAlkuun = etaisyysAlku;
    }

    public void setKayty(boolean kayty) {
        this.kayty = kayty;
    }

    @Override
    public String toString() {
        return "" + arvo;
    }

    @Override
    public int compareTo(Ruutu r) {
        return getEtaisyyksiensumma() - r.getEtaisyyksiensumma();
    }

}
