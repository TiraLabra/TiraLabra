
package Domain;

/**
 * Luokka mallintaa pelin hirviöitä pitäen kirjaa
 * niiden sijainnista ja muista ominaisuuksista.
 * 
 * @author Emleri
 */
public class Hirvio implements Peliobjekti {
    private Koordinaatit sijainti;
    private Koordinaatit[] polku;

    /**
     * Konstruktori, luo hirviön jonka sijainti on parametreina saaduissa 
     * koordinaateissa.
     * @param k sijainnin koordinaatit
     */
    public Hirvio(Koordinaatit k) {
        this.sijainti = k;
    }

    /**
     * Getteri sijainnille.
     * @return hirviön sijainti koordinaatteina
     */
    @Override
    public Koordinaatit getKoordinaatit() {
        return this.sijainti;
    }

    /**
     * Setteri sijainnille.
     * @param k sijainti koordinaatteina
     */
    @Override
    public void setKoordinaatit(Koordinaatit k) {
        this.sijainti = k;
    }

    /**
     * Asettaa hirviölle polun kuljettavaksi. Placeholder, tulee ottamaan
     * todennäköisesti polun myöhemmin vastaan hieman eri tavalla.
     * @param polku polku koordinaattitauluna kulkujärjestyksessä
     */
    public void setPolku(Koordinaatit[] polku) {
        this.polku = polku;
    }
}
