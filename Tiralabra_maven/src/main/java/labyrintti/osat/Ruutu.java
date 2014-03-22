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
     * Ruutu tuntee ruudun, josta siihen on tultu, jotta voi selvittää polun reitin.
     */
    private Ruutu edellinen;

    public Ruutu(int arvo, int x, int y) {
        this.arvo = arvo;
        this.x = x;
        this.y = y;
        etaisyysAlkuun = Integer.MAX_VALUE;
        etaisyysLoppuun = Integer.MAX_VALUE;
        kayty = false;
        edellinen = null;
    }

    /**
     * Laskee etäisyyden maaliruutuun
     * @param maaliX, maaliruudun x-koordinaatti
     * @param maaliY, maaliruudun y-koordinaatti
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

    public int getEtaisyyksiensumma() {
        return etaisyysAlkuun + etaisyysLoppuun;
    }

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

    @Override
    public String toString() {
        return "" + arvo;
    }

    @Override
    public int compareTo(Ruutu r) {
        return getEtaisyyksiensumma() - r.getEtaisyyksiensumma();
    }

    @Override
    public boolean equals(Object o) {
        if(o==null){
            return false;
        }
        if(getClass() != o.getClass()){
            return false;
        }
        Ruutu verrattava = (Ruutu) o;
        if(x==verrattava.getX() && y==verrattava.getY()){
            return true;
        }
        return false;
    }
    
    

}
