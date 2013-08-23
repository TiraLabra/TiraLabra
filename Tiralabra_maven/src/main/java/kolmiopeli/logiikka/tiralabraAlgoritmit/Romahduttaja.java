package kolmiopeli.logiikka.tiralabraAlgoritmit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Koordinaatti;
import kolmiopeli.domain.Variarpoja;

/**
 * Romahduttaja liikuttelee peliruudukon kolmioiden sijainteja ylhaalta alaspain
 * eraanlaisissa "S" muodoissa (katso Docs/RomahtamisenIdea.png) siten, etta
 * tyhjat ruudut tayttyy ylemmilla kolmioilla. Ylarivilla romahduttaja arpoo
 * tyhjaan ruutuun uuden kolmion.
 */
public class Romahduttaja {

    private Kolmio[][] peliruudukko;
    private final Variarpoja variarpoja;
    private final RomahduttajaDebugViestit debugViestit;
    private List<Koordinaatti> tyhjienRuutujenLista;
    private Collection muutettujenJoukko;

    /**
     * Konstruktori, joka saa parametrina peliruudukon ja tiedon laitetaanko
     * debug-viestit paalle.
     *
     * @param peliruudukko Romautettava peliruudukko
     * @param debugViestitPaalla True, jos viestit halutaan paalle.
     */
    public Romahduttaja(Kolmio[][] peliruudukko, boolean debugViestitPaalla) {
        this.peliruudukko = peliruudukko;
        this.variarpoja = new Variarpoja();
        this.debugViestit = new RomahduttajaDebugViestit(debugViestitPaalla);

    }

    /**
     * Metodi romahduttaa parametrina saamiinsa tyhjiin ruutuihin kolmioita
     * tyhjien ruutujen ylapuolelta. Tekee parametrista oliolle kentan ja luo
     * joukon johon kerataan ruudukossa liikkuneet koordinaatit (jotta komboetsija
     * osaa etsia oikeista paikoista) jotka palautetaan.
     *
     * @param tyhjatRuudut Joukko juuri tyhjentyneita ruutuja, joihin romahtaa 
     * yllaolevat kolmiot
     * @return Joukko koordinaatteja, joissa on eri kolmioita kuin alkutilanteessa
     */
    public Collection romahduta(Collection tyhjatRuudut) {
        this.debugViestit.romahdusAlkaa();
        tyhjienRuutujenLista = (ArrayList<Koordinaatti>) tyhjatRuudut;
        muutettujenJoukko = new HashSet<Koordinaatti>();
        kayLapiTyhjatRuudut();
        this.debugViestit.romautettujenJoukkoOn(muutettujenJoukko);
        return muutettujenJoukko;
    }

    /**
     * Kay lapi romahduta-metodiin parametrina annetut koordinaatit.
     */
    private void kayLapiTyhjatRuudut() {
        for (int i = 0; i < tyhjienRuutujenLista.size(); i++) {
            Koordinaatti taytettavaTyhja = tyhjienRuutujenLista.get(i);
            romahdutaYlapuolellaOlevat(taytettavaTyhja);
        }
    }

    /**
     * Liikuttelee kolmioita aina yhden kerrallaan alaspain while-loopissa
     * kunnes tiputaYksiKolmio palauttaa koordinaatin sijasta nullin.
     * @param taytettavaTyhja Yksi tyhjienRuutujenLista -kentan jasenista
     */
    private void romahdutaYlapuolellaOlevat(Koordinaatti taytettavaTyhja) {
        this.debugViestit.tutkitaanJuurta(taytettavaTyhja);
        while (taytettavaTyhja != null) {
            taytettavaTyhja = tiputaYksiKolmio(taytettavaTyhja);
        }
        this.debugViestit.viivoja();

    }
    
    /**
     * Tarkistaa onko tyhja ruutu ylarivilla vai jossain muualla.
     * 
     * @param tyhja Taytettava tyhja koordinaatti
     * @return Koordinaatti joka tiputettiin tyhjan tilalle, null jos ei ruudukossa
     * ei ollut enaa mitaan tiputettavaa
     */
    private Koordinaatti tiputaYksiKolmio(Koordinaatti tyhja) {
        if (tyhja.getRivi() == 0) {
            return tyhjaOnYlarivilla(tyhja);
        } else {
            return tyhjaOnMuullaRivilla(tyhja);
        }
    }

    /**
     * Jos tyhja ylarivin ruutu osoittaa ylospain, vieritetaan siihen kolmio vieresta,
     * muuten arvotaan ruutuun uusi Kolmio.
     * 
     * @param tyhja Taytettava tyhja koordinaatti
     * @return Koordinaatti joka tiputettiin tyhjan tilalle, null jos ei ruudukossa
     * ei ollut enaa mitaan tiputettavaa 
     */
    private Koordinaatti tyhjaOnYlarivilla(Koordinaatti tyhja) {
        if (tyhja.osoittaakoKoordinaatinRuutuYlospain()) {
            return siirraViereista(tyhja);
        } else {
            peliruudukko[tyhja.getRivi()][tyhja.getSarake()] = new Kolmio(variarpoja.arvoVari(), tyhja.getRivi(), tyhja.getSarake());
            muutettujenJoukko.add(tyhja);
            return null;
        }
    }

    /**
     * Jos epaylarivin tyhja ruutu osoittaa alaaspain, tiputetaan ylapuolella
     * oleva kolmio ja palautetaan sen koordinaatti, muuten vieritetaan ruutuun 
     * kolmio vieresta.
     * 
     * @param tyhja Taytettava tyhja koordinaatti
     * @return Koordinaatti joka tiputettiin tyhjan tilalle, null jos ei ruudukossa
     * ei ollut enaa mitaan tiputettavaa 
     */
    private Koordinaatti tyhjaOnMuullaRivilla(Koordinaatti tyhja) {
        if (!tyhja.osoittaakoKoordinaatinRuutuYlospain()) {
            int romahtavanRivi = tyhja.getRivi() - 1;
            int romahtavanSarake = tyhja.getSarake();
            return siirraKolmionPaikkaa(tyhja.getRivi(), tyhja.getSarake(), romahtavanRivi, romahtavanSarake);
        } else {
            return siirraViereista(tyhja);
        }
    }

    /**
     * Vierittaa tyhjaan ruutuun kolmion joko oikealta tai vasemmalta, perustuen
     * suunniteltuihin tippumisreitteihin (katso Docs/RomahtamisenIdea.png).
     * 
     * @param tyhja Taytettava tyhja koordinaatti
     * @return Koordinaatti joka tiputettiin tyhjan tilalle, null jos ei ruudukossa
     * ei ollut enaa mitaan tiputettavaa 
     */
    private Koordinaatti siirraViereista(Koordinaatti tyhja) {
        int romahtavanRivi;
        int romahtavanSarake;
        if (vieriikoTyhjaanKolmioOikealta(tyhja)) {
            romahtavanRivi = tyhja.getRivi();
            romahtavanSarake = tyhja.getSarake() + 1;
        } else {
            romahtavanRivi = tyhja.getRivi();
            romahtavanSarake = tyhja.getSarake() - 1;
        }
        return siirraKolmionPaikkaa(tyhja.getRivi(), tyhja.getSarake(), romahtavanRivi, romahtavanSarake);
    }

    /**
     * Tekee varsinaisen kolmioiden vaihdon, jos romahtava ruutu on tyhja, 
     * metodi palauttaa null. Muuten metodi muuttaa romahtavan kolmion sijainnin,
     * lisaa romahtavan koordinaatin muuttuneidenJoukkoon, siirtaa romahtavan 
     * tyhjaan ruutuun ja muuttaa romahtaneen ruudun nulliksi.
     * 
     * @param tyhjaRivi Tyhjan ruudun rivi
     * @param tyhjaSarake Tyhjan ruudun sarake
     * @param romahtavanRivi Romahtavan ruudun rivi
     * @param romahtavanSarake Romahtavan ruudun sarake
     * @return Romahtaneen, juuri tyhjentyneen ruudun koordinaatin
     */
    private Koordinaatti siirraKolmionPaikkaa(int tyhjaRivi, int tyhjaSarake, int romahtavanRivi, int romahtavanSarake) {
        if (romahtavaOnTyhja(tyhjaRivi, tyhjaSarake, romahtavanRivi, romahtavanSarake)) {
            return null;
        }

        this.debugViestit.kolmioRomahtaa(tyhjaRivi, tyhjaSarake, romahtavanRivi, romahtavanSarake);
        Kolmio romahtava = peliruudukko[romahtavanRivi][romahtavanSarake];
        romahtava.setSijainti(tyhjaRivi, tyhjaSarake);
        muutettujenJoukko.add(new Koordinaatti(tyhjaRivi, tyhjaSarake));
        peliruudukko[tyhjaRivi][tyhjaSarake] = romahtava;
        peliruudukko[romahtavanRivi][romahtavanSarake] = null;
        return new Koordinaatti(romahtavanRivi, romahtavanSarake);
    }

    /**
     * Testaa vieriiko tutkittavaan ruutuun kolmio oikealta vai vasemmalta
     * 
     * @param tyhja Koordinaatti johon vierii kolmio vieresta
     * @return True, jos tyhjaan vierii kolmio oikealta, false jos vasemmalta
     */
    private boolean vieriikoTyhjaanKolmioOikealta(Koordinaatti tyhja) {
        if (tyhja.getRivi() % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tarkistaa onko romahtamis vuorossa oleva ruutu tyhja. Jos on, niin metodi
     * lisaa romahtavan ruudun alapuolella olevan toisen tyhjan ruudun koordinaatin
     * tyhjien ruutujen listan peralle, jotta se voidaan kayda lapi myohemmin kun 
     * tama romahtava ruutu on tayttynyt.
     * 
     * @param tyhjaRivi Tyhjan ruudun rivi
     * @param tyhjaSarake Tyhjan ruudun sarake
     * @param romahtavanRivi Romahtavan ruudun rivi
     * @param romahtavanSarake Romahtavan ruudun sarake
     * @return True, jos romahtava ruutu oli myos tyhja, false jos ei ollut
     */
    private boolean romahtavaOnTyhja(int tyhjaRivi, int tyhjaSarake, int romahtavanRivi, int romahtavanSarake) {
        if (peliruudukko[romahtavanRivi][romahtavanSarake] == null) {
            Koordinaatti siirraViimeiseksi = new Koordinaatti(tyhjaRivi, tyhjaSarake);
            this.tyhjienRuutujenLista.add(siirraViimeiseksi);
            this.debugViestit.siirrettiinListanPohjalle(siirraViimeiseksi);
            return true;
        }
        return false;
    }
}
