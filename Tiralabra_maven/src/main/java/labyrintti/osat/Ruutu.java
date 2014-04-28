package labyrintti.osat;

/**
 * Ruutu on karttapohjan taulukon alkio.
 *
 * @author heidvill
 */
public class Ruutu {

    /**
     * Ruudun arvo.
     */
    private int arvo;
    /**
     * Ruudun x-koordinaatti, kertoo millä rivillä ruutu on.
     */
    private int x;
    /**
     * Ruudun y-koordinaatti, kertoo missä sarakkeessa ruutu on.
     */
    private int y;
    /**
     * Ruudun arvioitu etäisyys loppuun, |x-maaliX|+|y-maaliY|.
     */
    private int etaisyysLoppuun;
    /**
     * Ruudn etäisyys alkuun = polun pituus + ruudun arvo.
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
    /**
     * Ruudun indeksi minimikeossa.
     */
    private int indeksi;

    /**
     *
     * @param arvo ruudun arvo
     * @param x x-koordinaatti, rivi
     * @param y y-koordinaatti, sarake
     */
    public Ruutu(int arvo, int x, int y) {
        this.arvo = arvo;
        this.x = x;
        this.y = y;
        etaisyysAlkuun = 2000000000;
        etaisyysLoppuun = 2000000000;
        kayty = false;
        edellinen = null;
    }

    /**
     * Laskee etäisyyden maaliruutuun.
     *
     * @param maaliX Maaliruudun x-koordinaatti
     * @param maaliY Maaliruudun y-koordinaatti
     */
    public void laskeEtaisyysMaaliin(int maaliX, int maaliY) {
        etaisyysLoppuun = Math.abs(x - maaliX) + Math.abs(y - maaliY);
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

    /**
     *
     * @return ruutu, josta tähän ruutuun on tultu.
     */
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

    public int getIndeksi() {
        return indeksi;
    }

    public void setIndeksi(int indeksi) {
        this.indeksi = indeksi;
    }

    /**
     *
     * @return ruudun koordinaatit (x,y)
     */
    public String koordinaatit() {
        return "(" + x + "," + y + ")";
    }

    /**
     * Ruudut ovat samat, jos niillä on samat koordinaatit.
     *
     * @param o verrattava objekti
     * @return true, jos ruuduilla on samat koordinaatit, muuten false
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Ruutu verrattava = (Ruutu) o;
        if (x == verrattava.getX() && y == verrattava.getY()) {
            return true;
        }
        return false;
    }
}
