package pacman.hahmot;

import java.util.Random;
import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;
import pacman.tietorakenteet.Lista;

public class Green extends Haamu {

    public Green(int x, int y, Suunta suunta, String nimi, Pelialusta alusta) {
        super(x, y, suunta, nimi, alusta);
    }

    /**
     * Haamua liikuttava metodi. Jos haamun seuraava ruutu olisi seinä arvotaan
     * uusi suunta, minkä jälkeen liikutaan tähän suuntaa. Liiku metodi myös
     * tarkistaa, pääseekö ruudusta, jossa haamu on myös muualle kuin samaan
     * suuntaan jatkamalla tai peruuttamalla. Jos muuallekin pääsee arvotaan
     * uusi suunta ja liikutaan sinne. Muissa tapauksissa liikutaan suuntaan,
     * joka haamulla on suuntana.
     */
    public void liiku() {
        if (alusta.getPeliruutu(x + this.suunta.getX(), y + this.suunta.getY()).getRuudunTyyppi() == 0) {
            arvoUusiSuunta();
            liikuSuunta();
        } else if (katsoVoikoLiikkuaSivuille()) {
            arvoUusiSuunta();
            liikuSuunta();
        } else {
            liikuSuunta();
        }
    }

    /**
     * Liikutaan suuntaan, joka haamulla on suuntana. Muutetaan haamun
     * koordinaatteja ja kerrotaan pelialustalle mistä ruudusta mihin ruutuun
     * haamu siirtyy.
     */
    private void liikuSuunta() {

        this.y = this.y + suunta.getY();
        this.x = this.x + suunta.getX();
        alusta.getPeliruutu(x, y).setOnkoHaamu(true);
        alusta.getPeliruutu(x - suunta.getX(), y - suunta.getY()).setOnkoHaamu(false);

    }

    /**
     * Kerätään listalle kaikki mahdolliset suunnat, johon haamu pystyy
     * liikkumaan. Arvotaan listan perusteella numero, joka kertoo mistä
     * indeksistä valitaan uusi suunta. Arvottu suunta asetaan haamulle uudeksi
     * suunnaksi.
     */
    private void arvoUusiSuunta() {

        selvitaMahdollisetSuunnat();

        Random arpoja = new Random();
        int arpaluku = arpoja.nextInt(mahdollisetSuunnat.koko());
        this.suunta = (Suunta) mahdollisetSuunnat.getAlkio(arpaluku);
    }
    
    /**
     * Metodi selvittää mihin suuntii haamu voisi liikahtaa, siitä ruudussa
     * missä se metodin kutsuma hetkellä on. Tallentaa sopivat suunnat listaan.
     */
    private void selvitaMahdollisetSuunnat() {

        mahdollisetSuunnat = new Lista();

        for (Suunta s : Suunta.values()) {
            Peliruutu tarkasteltava = alusta.getPeliruutu(x + s.getX(), y + s.getY());

            if (tarkasteltava.getRuudunTyyppi() == 1 || tarkasteltava.getRuudunTyyppi() == 3) {
                mahdollisetSuunnat.lisaa(s);
            }
        }
    }
}
