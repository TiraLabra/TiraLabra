
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
     *
     * @param k
     */
    public Hirvio(Koordinaatit k) {
        this.sijainti = k;
    }

    /**
     *
     * @return
     */
    @Override
    public Koordinaatit getKoordinaatit() {
        return this.sijainti;
    }

    /**
     *
     * @param x
     * @param y
     */
    @Override
    public void setKoordinaatit(Koordinaatit k) {
        this.sijainti = k;
    }

    void setPolku(Koordinaatit[] polku) {
        this.polku = polku;
    }
}
