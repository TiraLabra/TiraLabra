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
    private int korkeus; // AVL-puuta varten
    private boolean onMusta; //Puna-mustapuuta varten

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
    
    public void setKorkeus(int uusiKorkeus) {
        this.korkeus = uusiKorkeus;
    }
    
    public int getKorkeus() {
        return this.korkeus;
    }
    
    //Asettaa solmun väriarvoksi musta(onMusta totuusarvo true)
    public void setMusta() {
        this.onMusta = true;
    }
    
    //Asettaa solmun väriarvoksi punainen(onMusta totuusarvo false)
    public void setPunainen() {
        this.onMusta = false;
    }
    
    public boolean onMusta() {
        return this.onMusta;
    }

    /**
     * Metodi tutkii onko solmulla lapsia.
     *
     * @return palauttaa true jos solmulla ei ole lapsia
     */
    public boolean lapseton() {
        return (oikea == null && vasen == null);
    }
}
