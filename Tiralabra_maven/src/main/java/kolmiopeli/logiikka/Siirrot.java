package kolmiopeli.logiikka;

import java.util.ArrayList;
import java.util.Collections;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Koordinaatti;
import kolmiopeli.domain.Ruudukko;

// Alkeelliset versiot kolmioiden siirroista (kaikki kolmioiden paikkojen vaihdot mahdollisia ilman rajoituksia)
// Myohemmin pitaa lisata rajoituksia siirtoihin, lisaamalla rajoitukset tahan luokkaan tai johonkin muuhun
// Luokassa hieman copypastea, voi antaa tiivistysehdotuksia (refactorointi tulee 
// kun MahdollisetPistesiirrot valmistuu eli sitten kun peli tietaa mista siirroista tulisi pisteita)
// YLLAOLEVAT TIEDOT VANHENTUNEITA
// Siirrot luokkaa siistitty ja lisatty pistesiirto ehto.
/**
 * Kolmioiden vaihtaminen ruudukossa viereisen kolmion kanssa jos ehdot tayttyy
 * (esim ei reunalla ja tuottaa pisteita).
 *
 * @author Eemi
 */
public class Siirrot {

    private Kolmio[][] peliruudukko;
    private PistesiirtojenEtsija etsija;
    private Pisteenlaskija pisteenlaskija;

    /**
     * Luo Siirrot olion ja liittaa siihen peliruudukon. Lisaa
     * PistesiirtojenEtsija erikseen.
     *
     * @param peliruudukko Peliruudukko jota muokataan siirroilla
     */
    public Siirrot(Ruudukko peliruudukko) {
        this.peliruudukko = peliruudukko.getRuudukko();
        this.pisteenlaskija = new Pisteenlaskija(peliruudukko);
    }

    /**
     * Luo Siirrot olio ilman mitaan pelilautaa. Lisaa peliruudukko ja
     * PistesiirtojenEtsija erikseen.
     */

    public Pisteenlaskija getPisteenlaskija() {
        return pisteenlaskija;
    }    
    
    public PistesiirtojenEtsija getEtsija() {
        return etsija;
    }

    public void setEtsija(PistesiirtojenEtsija etsija) {
        this.etsija = etsija;
    }


    // Aloittaessa uuden pelin ei tarvitse kuin luoda uusi ruudukko
    public void setPeliruudukko(Ruudukko ruudukko) {
        this.peliruudukko = ruudukko.getRuudukko();
    }

    /**
     * Yrittaa siirtaa kolmiota annetussa koordinaatissa vasemmalle.
     *
     * @param siirrettavanRivi Rivi, jossa siirrettava kolmio sijaitsee.
     * @param siirrettavanSarake Sarake, jossa siirrettava kolmio sijaitsee.
     * @return True, jos siirto onnistui.
     */
    public boolean siirraKolmioVasemmalle(int siirrettavanRivi, int siirrettavanSarake) {

        // Vasemman reunan kolmioita ei voi siirtaa vasemmalle
        if (siirrettavanSarake == 0) {
            return false;
        }

        // Tuottaako siirto pisteita
        if (!this.etsija.tuottaakoPisteitaJosVaihtaa(siirrettavanRivi, siirrettavanSarake, siirrettavanRivi, siirrettavanSarake - 1)) {
            return false;
        }

        return siirraKolmio(siirrettavanRivi, siirrettavanSarake, siirrettavanRivi, siirrettavanSarake - 1);
    }

    /**
     * Yrittaa siirtaa kolmiota annetussa koordinaatissa oikealle.
     *
     * @param siirrettavanRivi Rivi, jossa siirrettava kolmio sijaitsee.
     * @param siirrettavanSarake Sarake, jossa siirrettava kolmio sijaitsee.
     * @return True, jos siirto onnistui.
     */
    public boolean siirraKolmioOikealle(int siirrettavanRivi, int siirrettavanSarake) {

        // Oikean reunan kolmioita ei voi siirtaa oikealle
        if (siirrettavanSarake == this.peliruudukko[0].length - 1) {
            return false;
        }

        // Tuottaako siirto pisteita
        if (!this.etsija.tuottaakoPisteitaJosVaihtaa(siirrettavanRivi, siirrettavanSarake, siirrettavanRivi, siirrettavanSarake + 1)) {
            return false;
        }

        return siirraKolmio(siirrettavanRivi, siirrettavanSarake, siirrettavanRivi, siirrettavanSarake + 1);
    }

    /**
     * Yrittaa siirtaa kolmiota annetussa koordinaatissa alas.
     *
     * @param siirrettavanRivi Rivi, jossa siirrettava kolmio sijaitsee.
     * @param siirrettavanSarake Sarake, jossa siirrettava kolmio sijaitsee.
     * @return True, jos siirto onnistui.
     */
    public boolean siirraKolmioYlos(int siirrettavanRivi, int siirrettavanSarake) {

        // Ylareunan kolmioita ei voi siirtaa ylos
        if (siirrettavanRivi == 0) {
            return false;
        }

        // Jos kolmio osoittaa ylospain, sita ei voi siirtaa ylos
        if (this.peliruudukko[siirrettavanRivi][siirrettavanSarake].osoittaakoKolmioYlospain()) {
            return false;
        }

        // Tuottaako siirto pisteita
        if (!this.etsija.tuottaakoPisteitaJosVaihtaa(siirrettavanRivi, siirrettavanSarake, siirrettavanRivi - 1, siirrettavanSarake)) {
            return false;
        }

        return siirraKolmio(siirrettavanRivi, siirrettavanSarake, siirrettavanRivi - 1, siirrettavanSarake);
    }

    /**
     * Yrittaa siirtaa kolmiota annetussa koordinaatissa ylos.
     *
     * @param siirrettavanRivi Rivi, jossa siirrettava kolmio sijaitsee.
     * @param siirrettavanSarake Sarake, jossa siirrettava kolmio sijaitsee.
     * @return True, jos siirto onnistui.
     */
    public boolean siirraKolmioAlas(int siirrettavanRivi, int siirrettavanSarake) {

        // Alareunan kolmioita ei voi siirtaa alas
        if (siirrettavanRivi == this.peliruudukko.length - 1) {
            return false;
        }

        // Jos kolmio osoittaa alaspain, sita ei voi siirtaa alas
        if (!this.peliruudukko[siirrettavanRivi][siirrettavanSarake].osoittaakoKolmioYlospain()) {
            return false;
        }

        // Tuottaako siirto pisteita
        if (!this.etsija.tuottaakoPisteitaJosVaihtaa(siirrettavanRivi, siirrettavanSarake, siirrettavanRivi + 1, siirrettavanSarake)) {
            return false;
        }

        return siirraKolmio(siirrettavanRivi, siirrettavanSarake, siirrettavanRivi + 1, siirrettavanSarake);
    }

    private boolean siirraKolmio(int siirrettavanRivi, int siirrettavanSarake, int viereisenRivi, int viereisenSarake) {
        // Toisen apumuuttujista voi poistaa, lisatty selkeyden takia
        Kolmio siirrettava = this.peliruudukko[siirrettavanRivi][siirrettavanSarake];
        Kolmio viereinen = this.peliruudukko[viereisenRivi][viereisenSarake];


        // Vaihdetaan kolmioiden paikat ja muutetaan koordinaatit
        this.peliruudukko[viereisenRivi][viereisenSarake] = siirrettava;
        siirrettava.setSijainti(viereisenRivi, viereisenSarake);
        this.peliruudukko[siirrettavanRivi][siirrettavanSarake] = viereinen;
        viereinen.setSijainti(siirrettavanRivi, siirrettavanSarake);

        lahetaTuhoutuvatPisteenlaskijalle(siirrettava, viereinen);


        return true;
    }

    private void lahetaTuhoutuvatPisteenlaskijalle(Kolmio siirrettava, Kolmio viereinen) {
        ArrayList<Koordinaatti> tuhoutuvat = new ArrayList();
        tuhoutuvat.addAll(this.etsija.getTamanKolmionKanssaTuhoutuvat(siirrettava));
        tuhoutuvat.addAll(this.etsija.getTamanKolmionKanssaTuhoutuvat(viereinen));
        Collections.sort(tuhoutuvat);
        Collections.reverse(tuhoutuvat);
        // Piirra tassa valissa peliruudukkoon mitka ovat tuhoutuneet
        this.pisteenlaskija.tuhoaKolmiot(tuhoutuvat);
        System.out.println(tuhoutuvat);
        
    }
}
