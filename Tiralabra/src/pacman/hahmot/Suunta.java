package pacman.hahmot;

/**
 * Määrittelee haamujen ja manin liikkumissuunnat.
 * 
 * @author hhkopper
 */
public enum Suunta {

    /**
     * Suunta ylös, jolloin vähennetään y-koordinaattia ja x-koordinaatti pysyy samana.
     */
    YLOS(0, -1), 
    /**
     * Suunta oikea, jolloin x-koordinaattia kasvatetaan yhdellä ja y-koordinaatti pysyy samana.
     */
    OIKEA(1, 0), 
    /**
     * Suunta alas, jolloin x-koordinaatti pysyy samana ja y-koordinaattia kasvatetaan yhdellä.
     */
    ALAS(0, 1), 
    /**
     *Suunta vasen, jolloin x-koordinaattia vähennetään yhdellä ja y-koordinaatti pysyy samana.
     */
    VASEN(-1, 0);
    
      /**
     * Kertoo X koordinaatin muutoksen.
     */
    private int x;
      /**
     * Kertoo Y koordinaatin muutoksen.
     */
    private int y;

    private Suunta(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
