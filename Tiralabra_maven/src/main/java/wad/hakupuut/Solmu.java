package wad.hakupuut;

/**
 * 
 * Solmu on tietue, joka on olennainen osa hakupuita.
 * Solmuun säilötään tieto, joka halutaan tallentaa tietorakenteeseen.
 * Solmun avaimella erotetaan solmut toisistaan ja arvo muuttujaan säilötään
 * haluttu tieto. Solmuun kirjataan myös tieto sen oikean ja vasemman alipuun
 * juuri solmuista.
 * 
 */
public class Solmu {
    private int avain;
    private Object arvo;
    private Solmu oikea;
    private Solmu vasen;

    public Solmu(int uusiAvain, Object uusiArvo) {
        this.avain = uusiAvain;
        this.arvo = uusiArvo;
    }
    
    public int getAvain() {
        return this.avain;
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
}
