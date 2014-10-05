package labyrintti.logiikka;

/**
 * Maapalarekisteri on luokka, joka luo labyrintin ja pitää kirjaa kaikista
 * labyrinttiin kuuluvista maapaloista. Luokalla on tieto lähdöstä ja maalista,
 * sekä kaikista muista labyrintin maapaloista.
 *
 * @author Mikael Parvamo
 */
public class Maapalarekisteri {

    private int koko;
    private Maapala[][] labyrintti;
    private int alkuX;
    private int alkuY;
    private int loppuX;
    private int loppuY;

    public Maapalarekisteri(int koko, int alkuX, int alkuY, int loppuX, int loppuY) {
        this.koko = koko;
        this.alkuX = alkuX;
        this.alkuY = alkuY;
        this.loppuX = loppuX;
        this.loppuY = loppuY;
        this.labyrintti = new Maapala[koko][koko];
    }

    /**
     * Metodi laskee jokaisen maapalan kohdalla tämän heuristisen arvon ja
     * asettaa sen maapalalle.
     */
    public void alustaMaapalat() {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {

                int xMatka = Math.abs(j - this.loppuX);
                int yMatka = Math.abs(i - this.loppuY);

                int hValue = xMatka + yMatka;

                labyrintti[j][i].alustaArvot(hValue);
            }
        }
    }
    
    /**
     * Metodi luo kaikki maapalat ja asettaa ne 2D- taulukkoon omille paikoilleen.
     */

    public void luoMaapalat() {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                labyrintti[j][i] = new Maapala(j, i);
            }
        }
    }

    /**
     * Tämä metodi tulostaa kaikki labyrintin maapalat. Metodi käy läpi kaikki
     * 2D- taulukon alkiot(maapalat) ja tulostaa ne.
     */
    public void tulostaMaapalat() {
        for (int i = 0; i < this.koko; i++) {
            for (int j = 0; j < this.koko; j++) {
                System.out.print(labyrintti[j][i]);
            }
            System.out.println("");
        }
    }

    /**
     * Tämä metodi palauttaa aloitusmaapalan X-koordinaatin, joka kertoo missä
     * maapala on labyrintin X-akselilla.
     *
     * @return this.alkuX, joka on aloitusmaapalan X-koordinaatti
     */
    public int getAlkuX() {
        return this.alkuX;
    }

    /**
     * Tämä metodi palauttaa aloitusmaapalan Y-koordinaatin, joka kertoo missä
     * maapala on labyrintin Y-akselilla.
     *
     * @return this.alkuY, joka on aloitusmaapalan Y-koordinaatti
     */
    public int getAlkuY() {
        return this.alkuY;
    }

    /**
     * Tämä metodi palauttaa lopetusmaapalan X-koordinaatin, joka kertoo missä
     * maapala on labyrintin X-akselilla.
     *
     * @return this.loppuX, joka on lopetusmaapalan X-koordinaatti
     */
    public int getLoppuX() {
        return this.loppuX;
    }

    /**
     * Tämä metodi palauttaa lopetusmaapalan Y-koordinaatin, joka kertoo missä
     * maapala on labyrintin Y-akselilla.
     *
     * @return this.loppuY, joka on lopetusmaapalan Y-koordinaatti
     */
    public int getLoppuY() {
        return this.loppuY;
    }

    /**
     * Tämä metodi palauttaa labyrintin aloitusmaapalan.
     *
     * @return labyrintti[this.alkuX][this.alkuY], eli palauttaa maapalan, joka
     * on aloitusmaapalan koordinaateissa.
     */
    public Maapala getAlku() {
        return labyrintti[this.alkuX][this.alkuY];
    }

    /**
     * Tämä metodi palauttaa lopetusmaapalan.
     *
     * @return labyrintti[this.loppuX][this.loppuY], eli metodi palauttaa
     * maapalan, joka on lopetusmaapalan (uloskäynnin) koordinaateissa.
     */
    public Maapala getLoppu() {
        return labyrintti[this.loppuX][this.loppuY];
    }

    /**
     * Tämän metodin tehtävänä on palauttaa koordinaattien mukainen maapala.
     *
     * @param x, haetun maapalan X-koordinaatti
     * @param y, haetun maapalan Y-koordinaatti
     * @return annettujen koordinaattien perusteella haettu maapala.
     */
    public Maapala getMaapala(int x, int y) {
        return this.labyrintti[x][y];
    }

    /**
     * Tämän metodin tarkoituksena on palauttaa koko labyrintti eli 2D-taulukko
     *
     * @return this.labyrintti, eli 2D- taulukko, jossa on kaikki labyrintin
     * maapalat.
     */
    public Maapala[][] getLabyrintti() {
        return this.labyrintti;
    }

    /**
     * @return this.koko, eli labyrintin sivun pituus.
     */
    public int getKoko() {
        return this.koko;
    }
    
    /**
     * @param int alkuX
     */
    
    public void setAlkuX(int alkuX) {
        this.alkuX = alkuX;
    }
    
     /**
     * @param int alkuY
     */

    public void setAlkuY(int alkuY) {
        this.alkuY = alkuY;
    }
    
     /**
     * @param int loppuX
     */
    
    public void setLoppuX(int loppuX){
        this.loppuX = loppuX;     
    }
    
     /**
     * @param int loppuY
     */
    
    public void setLoppuY(int loppuY){
        this.loppuY = loppuY;
    }
    
}
