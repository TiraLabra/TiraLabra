package labyrintti.osat;

/**
 * Ruutu on karttapohjan taulukon alkio.
 * @author heidvill
 */
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
    /**
     * Ruudun arvioitu etäisyys loppuun, |x-maaliX|+|y-maaliY|.
     */
    private int etaisyysLoppuun;
    /**
     * Ruudn etäisyys alkuun, polun pituus + ruudun arvo.
     */
    private int etaisyysAlkuun;
    /**
     * False, jos etsijä ei ole käynyt ruudussa, muuten true.
     */
    private boolean kayty;
    /**
     * Ruutu tuntee ruudun, josta siihen on tultu, jotta voi selvittää polun
     * reitin.
     */
    private Ruutu edellinen;

    public Ruutu(int arvo, int x, int y) {
        this.arvo = arvo;
        this.x = x;
        this.y = y;
        etaisyysAlkuun = 2000000000; //Integer.MAX_VALUE;
        etaisyysLoppuun = 2000000000; // Integer.MAX_VALUE;
        kayty = false;
        edellinen = null;
    }

    /**
     * Laskee etäisyyden maaliruutuun
     *
     * @param maaliX Maaliruudun x-koordinaatti
     * @param maaliY Maaliruudun y-koordinaatti
     */
    public void laskeEtaisyysMaaliin(int maaliX, int maaliY) {
        etaisyysLoppuun = Math.abs((x - maaliX)) + Math.abs((y - maaliY));
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

    public int getEtaisyysAlkuun() {
        return etaisyysAlkuun;
    }

    public int getEtaisyysLoppuun() {
        return etaisyysLoppuun;
    }

    /**
     * Laskee ruudun alun ja lopun etäisyyksien summan.
     *
     * @return etaisyys alkuun + etaisyys loppuun
     */
    public int getEtaisyyksienSumma() {
        return etaisyysAlkuun + etaisyysLoppuun;
    }

    /**
     *
     * @return true jos etsijä on jo käynyt ruudussa, muuten false
     */
    public boolean onkoKayty() {
        return kayty;
    }

    public Ruutu getEdellinen() {
        return edellinen;
    }

    public void setEdellinen(Ruutu edellinen) {
        this.edellinen = edellinen;
    }

    public void setEtaisyysAlkuun(int etaisyysAlku) {
        this.etaisyysAlkuun = etaisyysAlku;
    }

    public void setKayty(boolean kayty) {
        this.kayty = kayty;
    }

    /**
     * 
     * @return ruudun arvo
     */
    @Override
    public String toString() {
        return "" + arvo;
    }

    /**
     * 
     * @return ruudun koordinaatit (x,y)
     */
    public String koordinaatit() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public int compareTo(Ruutu r) {
        return getEtaisyyksienSumma() - r.getEtaisyyksienSumma();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Ruutu verrattava = (Ruutu) o;
        if (x == verrattava.getX() && y == verrattava.getY() && arvo==verrattava.getArvo()) {
            return true;
        }
        return false;
    }
}
