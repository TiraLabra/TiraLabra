package pacman.hahmot;

import pacman.alusta.Pelialusta;

/**
 *
 * @author Hanna
 */
public abstract class Hahmo {

    /**
     * Suunta kertoo hahmon suunnan.
     */
    protected Suunta suunta;

    /**
     * Pelialusta kertoo alustan, jolla hahmo liikkuu.
     */
    protected Pelialusta alusta;

    /**
     * Hahmon X-koordinaatti.
     */
    protected int x;

    /**
     * Hahmon Y-koordinaatti.
     */
    protected int y;
    
    public Hahmo(int x, int y, Suunta suunta, Pelialusta alusta) {
        this.x = x;
        this.y = y;
        this.suunta = suunta;
        this.alusta = alusta;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    public void setSuunta(Suunta suunta) {
        this.suunta = suunta;
    }
    
    public Suunta getSuunta() {
        return this.suunta;
    }
    
    /**
     * Metodi tutkii voiko sivuille liikkua.
     * @return palauttaa totuusarvon.
     */
    public boolean katsoVoikoLiikkuaSivuille() {
        if (suunta == Suunta.VASEN || suunta == Suunta.OIKEA) {
            if (alusta.getPeliruutu(x, y - 1).getRuudunTyyppi() == 1 || alusta.getPeliruutu(x, y + 1).getRuudunTyyppi() == 1
                    || alusta.getPeliruutu(x, y - 1).getRuudunTyyppi() == 3 || alusta.getPeliruutu(x, y + 1).getRuudunTyyppi() == 3) {
                return true;
            }
        } else if (suunta == Suunta.ALAS || suunta == Suunta.YLOS) {
            if (alusta.getPeliruutu(x - 1, y).getRuudunTyyppi() == 1 || alusta.getPeliruutu(x + 1, y).getRuudunTyyppi() == 1
                    || alusta.getPeliruutu(x - 1, y).getRuudunTyyppi() == 3 || alusta.getPeliruutu(x + 1, y).getRuudunTyyppi() == 3) {
                return true;
            }
        }
        return false;
    }
    
}
