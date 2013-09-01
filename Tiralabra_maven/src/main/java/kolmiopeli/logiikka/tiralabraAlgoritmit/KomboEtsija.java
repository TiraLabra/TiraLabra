package kolmiopeli.logiikka.tiralabraAlgoritmit;

import java.awt.Color;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Koordinaatti;
import kolmiopeli.logiikka.tiralabraAlgoritmit.omatTietorakenteet.OmaLinkedList;

/**
 * Luokka lahtee kaymaan pelilautaa lapi uusista arvotuista kolmioista ja etsii
 * kolmen tai useamman samanvarisen vierekkaisen kolmion muodostamia polkuja
 * kolmioiden muodostamasta verkosta.
 *
 * @author Eemi
 */
public class KomboEtsija {

    private Kolmio[][] peliruudukko;
    private final KomboEtsijaDebugViestit debugViestit;

    /**
     * KomboEtsija algoritmiluokan konstruktori, liittaa etsijalle ruudukon ja 
     * laittaa debug viestit paalle tai pois.
     * @param peliruudukko
     * @param debugPaalla 
     */
    public KomboEtsija(Kolmio[][] peliruudukko, boolean debugPaalla) {
        this.peliruudukko = peliruudukko;
        this.debugViestit = new KomboEtsijaDebugViestit(debugPaalla);
    }

    /**
     * Kay lapi juuriarvottujen kolmioiden laheisia kolmioita niin pitkalle kun
     * loytyy sopivan varisia.
     * 
     * Algoritmin kulku:
     * 1. Alustaa joukon, johon kerataan kaikki algoritmin lapikaynnin aikana keratyt
     *    tuhoutuvat kolmiot (esim 3 punaista ja 4 vihreaa, jos tallaiset on loydetty).
     * 
     * 2. Aloitetaan for-loop, jossa kaydaan lapi parametrina annetut koordinaatit.
     * 
     *    3. Vuorossa oleva koordinaatti on BFS lapikaynnin juuri.
     * 
     *       4. Jos juuri on jo todettu osaksi jotain tuhoutuvaa kolmioyhdistelmaa
     *          (kuuluu jo palautettavaan joukkoon), niin siirrytaan for-loopissa
     *          seuraavaan parametrijoukon alkioon.
     * 
     *    5. Aloitetaan juuresta varsinainen BFS, alustetaan peliruudukon kokoinen
     *       boolean-taulukko joka toimii BFS:n solmujen lapikayntimerkkauksena,
     *       BFS:n jono ja lista johon kerataan tutkittavan juuren kanssa samanvariset
     *       kolmiot.
     * 
     *    6. Jatketaan BFS:saa niin pitkalle juuresta kuin vain loytyy samanvarisia
     *       vierekkaisia. Lisataan aina viereinen samanvarinen kolmio listaan joka
     *       pitaa niista kirjaa. Kolmiot ovat "verkon" solmuja ja jokaisesta lahtee 
     *       kolme kaarta viereisiin kolmioihin (reunoissa vahemman).
     * 
     *    7. BFS:n loputtua tarkistetaan onko juuren kanssa samanvarisia kolmioita
     *       loytynyt tarpeeksi (kolme vierekkaista).
     * 
     *       8. Jos on, lisataan listan kolmiot alussa maariteltyyn joukkoon. Muuten
     *          siirry seuraavaan parametrijoukon alkioon ja tutki sita (kohta 3.)
     *          lisaamatta listaa.
     * 
     * 9. Kun kaikki parametrijoukon alkiot on kayty lapi for-loopissa, niin palauta
     *    joukko kaikista loydetyista tuhoutuvista kolmioista.
     * 
     * @param juuriArvotut Array kohdista joissa on uunituoreita uusia kolmioita tutkittavaksi.
     * @return Palauttaa joukon koordinaatteja joissa on loydetyt kombokolmio koordinaatit.
     */
    public Collection etsiKombot(Collection juuriArvotut) {
        debugViestit.tuplaviiva();
        debugViestit.kayLapiJoukko(juuriArvotut);
        Koordinaatti[] juuriArvotutTaulukko = (Koordinaatti[]) juuriArvotut.toArray(new Koordinaatti[juuriArvotut.size()]);

        // Joukko joka keraa kaikki mahdolliset tuhoutuvat ja palauttaa ne.
        HashSet<Koordinaatti> kaikkiTuhoutuvat = lahdeTutkimaanArvottuja(juuriArvotutTaulukko);

        // Palautetaan lopuksi kaikki mahdolliset tuhoutuvat
        debugViestit.loydetytKombot(kaikkiTuhoutuvat);
        return kaikkiTuhoutuvat;
    }

    private HashSet<Koordinaatti> lahdeTutkimaanArvottuja(Koordinaatti[] juuriArvotutTaulukko) {
        HashSet<Koordinaatti> kaikkiTuhoutuvat = new HashSet<Koordinaatti>();

        // Lahdetaan kaymaan peliruudukkoa lapi jokaisesta uudesta kolmiosta vuorotellen
        for (int i = 0; i < juuriArvotutTaulukko.length; i++) {
            Koordinaatti root = juuriArvotutTaulukko[i];

            debugViestit.tahtia();
            debugViestit.tutkitaanJuurta(root);

            etsiSamanvarisetJuuresta(root, kaikkiTuhoutuvat);

        }
        return kaikkiTuhoutuvat;
    }

    private void etsiSamanvarisetJuuresta(Koordinaatti root, HashSet<Koordinaatti> kaikkiTuhoutuvat) {

        // Jos jokin aiempi kolmio on jo loytanyt jonkin toisen uusista kolmioista niin jatketaan seuraavaan uuteen kolmioon
        // esim jos uusissa kolmioissa on vierekkain samanvarisia
        if (kaikkiTuhoutuvat.contains(root)) {
            debugViestit.juuriOnJoTuhoutumassa(root);
            return;
        }
        OmaLinkedList<Koordinaatti> rootinVariset = keraaRootinVariset(root);
        loytykoTarpeeksiSamanvarisia(root, rootinVariset, kaikkiTuhoutuvat);
    }

    private OmaLinkedList<Koordinaatti> keraaRootinVariset(Koordinaatti root) {

        // Merkkaus, jono ja lista johon kerataan rootin viereiset samanvariset
        boolean[][] onkoKaytyLapi = new boolean[peliruudukko.length][peliruudukko[0].length];
        LinkedList<Koordinaatti> tutkittavienJono = new LinkedList<Koordinaatti>();
        OmaLinkedList<Koordinaatti> rootinVariset = new OmaLinkedList<Koordinaatti>();

        tutkittavienJono.addLast(root);
        onkoKaytyLapi[root.getRivi()][root.getSarake()] = true;
        rootinVariset.addLast(root);

        debugViestit.valiviiva();

        kayLapiKolmioita(rootinVariset, tutkittavienJono, onkoKaytyLapi);

        return rootinVariset;
    }

    private void kayLapiKolmioita(OmaLinkedList<Koordinaatti> rootinVariset, LinkedList<Koordinaatti> tutkittavienJono, boolean[][] onkoKaytyLapi) {
        while (!tutkittavienJono.isEmpty()) {
            Koordinaatti tutkittava = tutkittavienJono.removeFirst();
            int tRivi = tutkittava.getRivi();
            int tSarake = tutkittava.getSarake();
            Color tVari = peliruudukko[tRivi][tSarake].getKolmionVari();
            debugViestit.tutkitaanKoordinaattia(tutkittava, tVari);

            // Tutkitaan kolmion viereisia kolmiota ehdoilla:
            // 1. Viereinen ei ole indexOutOfBounds
            // 2. Viereinen on samanvarinen kuin tutkittava
            // 3. Viereista ei ole kayty lapi
            Kolmio viereinen = null;
            int vRivi;
            int vSarake;

            // Oikeanpuolinen
            vRivi = tRivi;
            vSarake = tSarake + 1;
            tutkiViereinen(viereinen, vRivi, vSarake, tutkittava, tVari, rootinVariset, tutkittavienJono, onkoKaytyLapi);

            // Vasemmanpuolinen
            vRivi = tRivi;
            vSarake = tSarake - 1;
            tutkiViereinen(viereinen, vRivi, vSarake, tutkittava, tVari, rootinVariset, tutkittavienJono, onkoKaytyLapi);
            
            // Pystysuunnat
            tutkiPystysuunta(viereinen, tutkittava, tRivi, tSarake, tVari, rootinVariset, tutkittavienJono, onkoKaytyLapi);

            debugViestit.valiviiva();
        }
    }

    private void tutkiViereinen(Kolmio viereinen, int vRivi, int vSarake, Koordinaatti tutkittava, Color tVari, OmaLinkedList<Koordinaatti> rootinVariset, LinkedList<Koordinaatti> tutkittavienJono, boolean[][] onkoKaytyLapi) {


        if (viereinenEiOleYliReunan(tutkittava, vRivi, vSarake)) {
            viereinen = peliruudukko[vRivi][vSarake];
            if (viereinenOnSamanvarinen(viereinen, tutkittava, tVari)) {
                if (viereistaEiOleKaytyLapi(viereinen, tutkittava, vRivi, vSarake, onkoKaytyLapi)) {
                    debugViestit.loytyiSamanvarinenTutkimaton(tutkittava, viereinen);
                    tutkittavienJono.addLast(viereinen.getKoordinaatti());
                    onkoKaytyLapi[viereinen.getSijaintiRivi()][viereinen.getSijaintiSarake()] = true;
                    rootinVariset.addLast(viereinen.getKoordinaatti());
                    
                }
            }
        }
    }

    private void tutkiPystysuunta(Kolmio viereinen, Koordinaatti tutkittava, int tRivi, int tSarake, Color tVari, OmaLinkedList<Koordinaatti> rootinVariset, LinkedList<Koordinaatti> tutkittavienJono, boolean[][] onkoKaytyLapi) {
        // Testataan tutkitaanko ylapuolella vai alapuolella olevaa kolmiota
        int vRivi;
        int vSarake;
        if (tutkittava.osoittaakoKoordinaatinRuutuYlospain()) {
            // Alapuolella
            vRivi = tRivi + 1;
            vSarake = tSarake;
            tutkiViereinen(viereinen, vRivi, vSarake, tutkittava, tVari, rootinVariset, tutkittavienJono, onkoKaytyLapi);

        } else {
            // Ylapuolella
            vRivi = tRivi - 1;
            vSarake = tSarake;
            tutkiViereinen(viereinen, vRivi, vSarake, tutkittava, tVari, rootinVariset, tutkittavienJono, onkoKaytyLapi);

        }

    }
    
    private void loytykoTarpeeksiSamanvarisia(Koordinaatti root, OmaLinkedList<Koordinaatti> rootinVariset, HashSet<Koordinaatti> kaikkiTuhoutuvat) {
        // Jos aloituskolmion kanssa samanvarisia vierekkaita loytyi vahintaan kolme niin lisataan ne tuhoutuvien joukkoon
        if (rootinVariset.size() >= 3) {
            debugViestit.juurenMukanaTuhoutuvat(root, rootinVariset);
            lisaaOmaLinkedListinAlkiotHashSettiin(kaikkiTuhoutuvat, rootinVariset);
        } else {
            debugViestit.juurenVarisiaVain(root, rootinVariset);
        }
    }

    private boolean viereinenEiOleYliReunan(Koordinaatti tutkittava, int vRivi, int vSarake) {
        int reunaRivi = peliruudukko.length - 1;
        int reunaSarake = peliruudukko[0].length - 1;
        if (vRivi < 0) {
            debugViestit.tutkittavaYlareunassa(tutkittava);
            return false;
        } else if (vRivi > reunaRivi) {
            debugViestit.tutkittavaAlareunassa(tutkittava);
            return false;
        } else if (vSarake < 0) {
            debugViestit.tutkittavaVasemmassaReunassa(tutkittava);
            return false;
        } else if (vSarake > reunaSarake) {
            debugViestit.tutkittavaOikeassaReunassa(tutkittava);
            return false;
        } else {
            return true;
        }
    }

    private boolean viereinenOnSamanvarinen(Kolmio viereinen, Koordinaatti tutkittava, Color tVari) {
        Color vVari = viereinen.getKolmionVari();
        if (vVari != tVari) {
            debugViestit.viereinenErivarinen(tutkittava, viereinen);
            return false;
        } else {
            return true;
        }
    }

    private boolean viereistaEiOleKaytyLapi(Kolmio viereinen, Koordinaatti tutkittava, int vRivi, int vSarake, boolean[][] onkoKaytyLapi) {
        if (onkoKaytyLapi[vRivi][vSarake]) {
            debugViestit.viereinenKaytyLapi(tutkittava, viereinen);
            return false;
        } else {
            return true;
        }
    }
    
    public boolean testViereistaEiOleKaytyLapi(Kolmio viereinen, Koordinaatti tutkittava, int vRivi, int vSarake, boolean[][] onkoKaytyLapi) {
        return this.viereistaEiOleKaytyLapi(viereinen, tutkittava, vRivi, vSarake, onkoKaytyLapi);
    }
    public boolean testViereinenOnSamanvarinen(Kolmio viereinen, Koordinaatti tutkittava, Color tVari) {
        return this.viereinenOnSamanvarinen(viereinen, tutkittava, tVari);
    }
    public boolean testViereinenEiOleYliReunan(Koordinaatti tutkittava, int vRivi, int vSarake) {
        return this.viereinenEiOleYliReunan(tutkittava, vRivi, vSarake);
    }

    private void lisaaOmaLinkedListinAlkiotHashSettiin(HashSet<Koordinaatti> kaikkiTuhoutuvat, OmaLinkedList<Koordinaatti> rootinVariset) {
        while (rootinVariset.size() > 0) {
            kaikkiTuhoutuvat.add(rootinVariset.removeFirst());
        }
    }
    
    
}
