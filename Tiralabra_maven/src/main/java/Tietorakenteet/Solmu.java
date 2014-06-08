package Tietorakenteet;

/**
 * Luokka kuvaa keon solmua, jolla on "vanhempi" sek‰ oikea- ett‰ vasen lapsi.
 * Jokaisella solmulla on merkin mittainen avain, joka talletaan viel‰ String -formaatissa, sill‰
 * tarvitsen sit‰ javan valmiissa hajautustaulussa t‰ss‰ muodossa.
 * 
 * En ole kirjoittanut t‰t‰ luokkaa varten testej‰ laisinkaan, sill‰ sen metodit ovat t‰ysin triviaaleja.
 * Piti vaan pit‰‰ huolta ett‰ niiss‰ ei ole turhia bugeja (esim. "getVanh() olisikin palauttanut vasemman lapsen
 * vanhemman sijaan).
 */

public class Solmu {
    private String avain;
    private int esiintymiskerrat;
    private Solmu vanh;
    private Solmu vasen;
    private Solmu oikea;
    
    public Solmu(String avain, int esiintymiskerrat) {
        this.avain = avain;
        this.esiintymiskerrat = esiintymiskerrat;
    }
    
    /**
     * Luo uuden solmun, jonka avaimeksi tulee ascii -merkki "00" (vastaa null-avainta, mutta ei ole "null").
     * K‰ytet‰‰n Huffman -puun keossa solmuille, jotka yhdist‰v‰t kaksi lasta.
     * @param esiintymiskerrat 
     */
    
    public Solmu(int esiintymiskerrat) {
        this.avain = "" + (char) 0;
        this.esiintymiskerrat = esiintymiskerrat;
    }
    
    public String getAvain() {
        return this.avain;
    }
    
    public int getEsiintymat() {
        return this.esiintymiskerrat;
    }
    
    public void setEsiintymat(int esiintymat) {
        this.esiintymiskerrat = esiintymat;
    }
    
    public void setVanh(Solmu solmu) {
        this.vanh = solmu;
    }
    
    public void setVasen(Solmu solmu) {
        this.vasen = solmu;
    }
    
    public void setOikea(Solmu solmu) {
        this.oikea = solmu;
    }
    
    public Solmu getVanh() {
        return this.vanh;
    }
    
    public Solmu getVasen() {
        return this.vasen;
    }
                    
    public Solmu getOikea() {
        return this.oikea;
    }
}
