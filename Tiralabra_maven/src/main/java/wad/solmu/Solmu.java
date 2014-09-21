package wad.solmu;

/**
 *
 * Solmu on tietue, joka on olennainen osa hakupuita. Solmuun säilötään tieto,
 * joka halutaan tallentaa tietorakenteeseen. Solmun avaimella erotetaan solmut
 * toisistaan ja arvo muuttujaan säilötään haluttu tieto. Solmuun kirjataan myös
 * tieto sen oikean ja vasemman alipuun juuri solmuista.
 *
 */
public class Solmu {

    private int avain;
    private Object arvo;
    private Solmu oikea;
    private Solmu vasen;
    private Solmu vanhempi;

    public Solmu(Object uusiArvo) {
        this.avain = uusiArvo.hashCode();
        this.arvo = uusiArvo;
    }

    public int getAvain() {
        return this.avain;
    }
    
    public void setArvo(Object uusiarvo) {
        this.avain = uusiarvo.hashCode();
        this.arvo = uusiarvo;
    }
    
    public Object getArvo() {
        return this.arvo;
    }

    public void setOikea(Solmu solmu) {
        this.oikea = solmu;
    }

    public Solmu getOikea() {
        return this.oikea;
    }

    public void setVasen(Solmu solmu) {
        this.vasen = solmu;
    }

    public Solmu getVasen() {
        return this.vasen;
    }

    public void setVanhempi(Solmu solmu) {
        this.vanhempi = solmu;
    }

    public Solmu getVanhempi() {
        return this.vanhempi;
    }

    /**
     * Metodi tutkii onko solmulla lapsia.
     *
     * @return palauttaa true jos solmulla ei ole lapsia
     */
    public boolean lapseton() {
        return (oikea == null && vasen == null);
    }

    /**
     * Palauttaa merkkijonona tietoa solmun rakenteesta.
     *
     * @return merkkijono
     */
    public String toString() {
        if (lapseton()) {
            return "" + arvo;
        }
        else if (vasen != null && oikea != null) {
            return arvo + "{" + vasen + "," + oikea + "}";
        }
        else if (vasen != null) {
            return arvo + "{" + vasen + ",[]}";
        }
        else {
            return arvo + "{[]," + oikea + "}";
        }
    }
}
