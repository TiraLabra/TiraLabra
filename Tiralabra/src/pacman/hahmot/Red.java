package pacman.hahmot;

import pacman.alusta.Pelialusta;
import pacman.tietorakenteet.Lista;

public class Red extends Haamu{
    
    public Red(int x, int y,  Suunta suunta, String nimi, Pelialusta alusta) {
        this.x = x;
        this.y = y;
        this.suunta = suunta;
        this.nimi = nimi;
        this.alusta = alusta;
        this.tyyppi = "vahva";
        this.kielletytRuudut = new Lista();
        this.lisaaKielletytRuudut();
    }

    public void liiku() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
