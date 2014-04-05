package pacman.hahmot;

import java.util.Random;
import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;
import pacman.tietorakenteet.Lista;

public class Magenta extends Haamu {

    public Magenta(int x, int y, Suunta suunta, String nimi, Pelialusta alusta) {
        this.x = x;
        this.y = y;
        this.suunta = suunta;
        this.nimi = nimi;
        this.alusta = alusta;
        this.tyyppi = "vahva";
        kielletytRuudut = new Lista();
        this.lisaaKielletytRuudut();
    }

    /**
     * Selvitetaan Magenta haamulle ruutu, joka toimii sen maalina.
     *
     * @param arpoja
     * @return palauttaa peliruudun, joka on maali
     */
    public Peliruutu selvitaMaaliMagenta(Random arpoja) {
        int arpaX = arvoXMagenta(arpoja);
        int arpaY = arvoYMagenta(arpoja);

        Peliruutu maali = alusta.getPeliruutu(arpaX, arpaY);
        maali = sopivanRuudunEtsiminen(maali, arpoja);
        return maali;
    }

    /**
     * Arvotaan y-koordinaatti
     *
     * @param arpoja
     * @return
     */
    private int arvoYMagenta(Random arpoja) {
        int arpaY = arpoja.nextInt(18) + 1;
        return arpaY;
    }

    /**
     * Arvotaan x-koordinaatti
     *
     * @param arpoja
     * @return
     */
    private int arvoXMagenta(Random arpoja) {
        int arpaX = arpoja.nextInt(16) + 1;
        return arpaX;
    }
    
    /**
     * Whilen sisälle mennään, jos ensimmäinen arvottu ruutu on seinä tai sisältyy listaan kielletyistä ruuduista.
     * Whilen sisällä ollaan kunnes on löydetty sopiva ruutu maaliksi.
     * @param maali
     * @param arpoja
     * @return 
     */
    private Peliruutu sopivanRuudunEtsiminen(Peliruutu maali, Random arpoja) {
        int arpaX;
        int arpaY;
        while (maali.getRuudunTyyppi() == 0 || kielletytRuudut.sisaltaa(maali)) {
            arpaX = arpoja.nextInt(16) + 1;
            arpaY = arpoja.nextInt(18) + 1;

            maali = alusta.getPeliruutu(arpaX, arpaY);
        }
        return maali;
    }
}
