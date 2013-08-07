package kolmiopeli.logiikka;

import java.util.ArrayList;
import kolmiopeli.domain.Koordinaatti;
import kolmiopeli.domain.Ruudukko;

/**
 * Luokka pitaa kirjaa pelin pisteista.
 *
 * @author Eemi
 */
public class Pisteenlaskija {

    private int pisteet;
    private Ruudukko peliruudukko;
    private KolmioTayttaja tuhoutuneidenKorvaaja;

    /**
     * Alustaa pisteenlaskijalle ruudukon ja pisteet nollaksi.
     *
     * @param peliruudukko
     */
    public Pisteenlaskija(Ruudukko peliruudukko) {
        this.pisteet = 0;
        this.peliruudukko = peliruudukko;
        this.tuhoutuneidenKorvaaja = new KolmioTayttaja(peliruudukko);
    }

    public int getPisteet() {
        return pisteet;
    }

    /**
     * Nollaa pistelaskijan pisteet.
     */
    public void nollaaPisteet() {
        this.pisteet = 0;
    }

    /**
     * Kirjaa tuhoutuneiden kolmioiden pisteet ja lahettaa ne eteenpain
     * tuhoutumaan.
     *
     * @param tuhoutuvat Lista tuhottavien ruutujen koordinaateista
     */
    public void tuhoaKolmiot(ArrayList<Koordinaatti> tuhoutuvat) {
        this.pisteet += 10 * tuhoutuvat.size() * (tuhoutuvat.size() - 2);
        this.peliruudukko.poistaKolmiotKohdista(tuhoutuvat);
        this.tuhoutuneidenKorvaaja.taytaTietytRuudut(tuhoutuvat);
    }
}
