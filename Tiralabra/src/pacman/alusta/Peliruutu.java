package pacman.alusta;

/**
 * Peliruutu tietää kaiken tärkeän tiedon mitä ruudussa tapahtuu ja ketä ja mitä
 * se sisältää.
 *
 * @author hhkopper
 */
public class Peliruutu {

    /**
     * Ruudun X koordinaatti.
     */
    private int x;
    /**
     * Ruudun Y koordinaatti.
     */
    private int y;
    /**
     * Kertoo ruudun tyypin, 0 jos seinä, 1 jos käytävä, 2 jos ekstrapallon paikka ja 3 jos vaikka jonne ei laiteta mitään.
     */
    private int ruudunTyyppi;
      /**
     * Kertoo onko Man ruudussa, true, jos on ja false, jos ei.
     */
    private boolean onkoMan;
      /**
     * Kertoo onko Haamu ruudussa, true, jos on ja false, jos ei.
     */
    private boolean onkoHaamu;
      /**
     * Kertoo onko pistepallo ruudussa vai ei, true, jos on ja false, jos ei.
     */
    private boolean onkoPallo;
      /**
     * Kertoo onko ekstra pistepallo ruudussa, true, jos on ja false, jos ei.
     */
    private boolean onkoExtraPallo;

    /**
     * Konstruktorissa annetaan arvot koordinaateille.
     *
     * @param x koordinaatti X
     * @param y koordinaatti Y
     */
    public Peliruutu(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setRuudunTyyppi(int uusiTyyppi) {
        this.ruudunTyyppi = uusiTyyppi;
    }

    public int getRuudunTyyppi() {
        return this.ruudunTyyppi;
    }

    public void setOnkoMan(boolean arvo) {
        this.onkoMan = arvo;
    }

    public boolean getOnkoMan() {
        return this.onkoMan;
    }

    public void setOnkoHaamu(boolean arvo) {
        this.onkoHaamu = arvo;
    }

    public boolean getOnkoHaamu() {
        return this.onkoHaamu;
    }

    public void setOnkoPallo(boolean arvo) {
        this.onkoPallo = arvo;
    }

    public boolean getOnkoPallo() {
        return this.onkoPallo;
    }

    public void setOnkoExtraPallo(boolean arvo) {
        this.onkoExtraPallo = arvo;
    }

    public boolean getOnkoExtraPallo() {
        return this.onkoExtraPallo;
    }
}
