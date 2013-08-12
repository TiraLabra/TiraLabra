package kolmiopeli.logiikka.algoritmit;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Koordinaatti;

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

    public KomboEtsija(Kolmio[][] peliruudukko) {
        this.peliruudukko = peliruudukko;
        this.debugViestit = new KomboEtsijaDebugViestit();
    }

    /**
     * Kay lapi juuriarvottujen kolmioiden laheisia kolmioita niin pitkalle kun
     * loytyy sopivan varisia.
     *
     * @param Array kohdista joissa on uunituoreita uusia kolmioita arvottu.
     */
    public HashSet<Koordinaatti> etsiKombot(ArrayList<Koordinaatti> juuriArvotut) {
        debugViestit.tuplaviiva();
        Koordinaatti[] juuriArvotutTaulukko = juuriArvotut.toArray(new Koordinaatti[juuriArvotut.size()]);
        // BFS pohdintaa:
        // Miten merkkaan lapikaydyt kolmiot?
        // 1. Teen kolmioille muuttujan, mutta miten ja kuinka tehokkaasti "resettaan" kaikkien kolmioiden muuttujat uuden etsinnan alkaessa
        // 2. Teen kutsutilanteessa uuden taulukon (kopion verkosta) jossa pidan kirjaa
        // 3. Pidan listaa koordinaateista jotka on kayty lapi, tarkistus jonkinlaisella hashauksella

        // Mika naista olisi paras kompromissi aika ja tilavaativuuksien suhteen?
        // JA LISAKSI jos tuhoutuneiden tilalle arpoutuu kaksi vierekkaista samaa varia niin kuinka 

        // Itse toteutettavat rakenteet:
        // Lista, jono, hashset (nopeuden takia, ehka pelkka taulukko riittaa?)

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

            debugViestit.viiva();
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
        ArrayList<Koordinaatti> rootinVariset = keraaRootinVariset(root);
        loytykoTarpeeksiSamanvarisia(root, rootinVariset, kaikkiTuhoutuvat);
    }

    private ArrayList<Koordinaatti> keraaRootinVariset(Koordinaatti root) {

        // Merkkaus, jono ja lista johon kerataan rootin viereiset samanvariset
        boolean[][] onkoKaytyLapi = new boolean[peliruudukko.length][peliruudukko[0].length];
        LinkedList<Koordinaatti> tutkittavienJono = new LinkedList<Koordinaatti>();
        ArrayList<Koordinaatti> rootinVariset = new ArrayList<Koordinaatti>();

        tutkittavienJono.addLast(root);
        onkoKaytyLapi[root.getRivi()][root.getSarake()] = true;
        rootinVariset.add(root);

        debugViestit.valiTahtia();

        kayLapiKolmioita(rootinVariset, tutkittavienJono, onkoKaytyLapi);

        return rootinVariset;
    }

    private void loytykoTarpeeksiSamanvarisia(Koordinaatti root, ArrayList<Koordinaatti> rootinVariset, HashSet<Koordinaatti> kaikkiTuhoutuvat) {

        // Jos aloituskolmion kanssa samanvarisia vierekkaita loytyi vahintaan kolme niin lisataan ne tuhoutuvien joukkoon
        if (rootinVariset.size() >= 3) {
            debugViestit.juurenMukanaTuhoutuvat(root, rootinVariset);
            kaikkiTuhoutuvat.addAll(rootinVariset);
        } else {
            debugViestit.juurenVarisiaVain(root, rootinVariset);
        }
    }

    private void kayLapiKolmioita(ArrayList<Koordinaatti> rootinVariset, LinkedList<Koordinaatti> tutkittavienJono, boolean[][] onkoKaytyLapi) {
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
            tutkiOikeanpuolinen(viereinen, tutkittava, tRivi, tSarake, tVari, rootinVariset, tutkittavienJono, onkoKaytyLapi);
            tutkiVasemmanpuolinen(viereinen, tutkittava, tRivi, tSarake, tVari, rootinVariset, tutkittavienJono, onkoKaytyLapi);
            tutkiPystysuunta(viereinen, tutkittava, tRivi, tSarake, tVari, rootinVariset, tutkittavienJono, onkoKaytyLapi);

            debugViestit.valiTahtia();
        }

    }

    private void tutkiOikeanpuolinen(Kolmio viereinen, Koordinaatti tutkittava, int tRivi, int tSarake, Color tVari, ArrayList<Koordinaatti> rootinVariset, LinkedList<Koordinaatti> tutkittavienJono, boolean[][] onkoKaytyLapi) {
        // Oikeanpuolinen
        int vRivi = tRivi;
        int vSarake = tSarake + 1;
        int nollaJosEhdotTayttyy = 0;

//        if (viereinenEiOleReunassa(tutkittava, viereinen, vRivi, vSarake)) {
//            nollaJosEhdotTayttyy += viereinenOnSamanvarinen(viereinen.getKolmionVari(), tVari);
//            nollaJosEhdotTayttyy += viereistaEiOleKaytyLapi(vRivi, vSarake, onkoKaytyLapi);
//        }
//            
        


        if (tSarake + 1 < peliruudukko[0].length) {
            viereinen = peliruudukko[tRivi][tSarake + 1];

            if (viereinen.getKolmionVari() == tVari
                    && !onkoKaytyLapi[viereinen.getSijaintiRivi()][viereinen.getSijaintiSarake()]) {

                System.out.println(">>> " + tutkittava.toString() + ">>> - Vasemmalta loytyi samanvarinen, vari " + viereinen.getKolmionVari());

                tutkittavienJono.addLast(viereinen.getKoordinaatti());
                onkoKaytyLapi[viereinen.getSijaintiRivi()][viereinen.getSijaintiSarake()] = true;
                rootinVariset.add(viereinen.getKoordinaatti());
            }

        } else {
        }

    }

    private void tutkiVasemmanpuolinen(Kolmio viereinen, Koordinaatti tutkittava, int tRivi, int tSarake, Color tVari, ArrayList<Koordinaatti> rootinVariset, LinkedList<Koordinaatti> tutkittavienJono, boolean[][] onkoKaytyLapi) {
        // Vasemmanpuolinen
        if (tSarake - 1 >= 0) {
            viereinen = peliruudukko[tRivi][tSarake - 1];

            if (viereinen.getKolmionVari() == tVari
                    && !onkoKaytyLapi[viereinen.getSijaintiRivi()][viereinen.getSijaintiSarake()]) {

                System.out.println(">>> " + tutkittava.toString() + ">>> - Oikealta loytyi samanvarinen, vari " + viereinen.getKolmionVari());

                tutkittavienJono.addLast(viereinen.getKoordinaatti());
                onkoKaytyLapi[viereinen.getSijaintiRivi()][viereinen.getSijaintiSarake()] = true;
                rootinVariset.add(viereinen.getKoordinaatti());
            }

        } else {
            System.out.println(">>> " + tutkittava.toString() + ">>> - Tutkittava on vasemmassa reunassa");
        }

    }

    private void tutkiPystysuunta(Kolmio viereinen, Koordinaatti tutkittava, int tRivi, int tSarake, Color tVari, ArrayList<Koordinaatti> rootinVariset, LinkedList<Koordinaatti> tutkittavienJono, boolean[][] onkoKaytyLapi) {
        // Testataan tutkitaanko ylapuolella vai alapuolella olevaa kolmiota
        if (tutkittava.osoittaakoKoordinaatinRuutuYlospain()) {

            // Alapuolella
            if (tRivi + 1 < peliruudukko.length) {
                viereinen = peliruudukko[tRivi + 1][tSarake];

                if (viereinen.getKolmionVari() == tVari
                        && !onkoKaytyLapi[viereinen.getSijaintiRivi()][viereinen.getSijaintiSarake()]) {

                    System.out.println(">>> " + tutkittava.toString() + ">>> - Alapuolelta loytyi samanvarinen, vari " + viereinen.getKolmionVari());

                    tutkittavienJono.addLast(viereinen.getKoordinaatti());
                    onkoKaytyLapi[viereinen.getSijaintiRivi()][viereinen.getSijaintiSarake()] = true;
                    rootinVariset.add(viereinen.getKoordinaatti());
                }

            } else {
                System.out.println(">>> " + tutkittava.toString() + ">>> - Tutkittava on alareunassa");
            }

        } else {

            // Ylapuolella
            if (tRivi - 1 >= 0) {
                viereinen = peliruudukko[tRivi - 1][tSarake];

                if (viereinen.getKolmionVari() == tVari
                        && !onkoKaytyLapi[viereinen.getSijaintiRivi()][viereinen.getSijaintiSarake()]) {
                    System.out.println(">>> " + tutkittava.toString() + ">>> - Ylapuolelta loytyi samanvarinen, vari " + viereinen.getKolmionVari());


                    tutkittavienJono.addLast(viereinen.getKoordinaatti());
                    onkoKaytyLapi[viereinen.getSijaintiRivi()][viereinen.getSijaintiSarake()] = true;
                    rootinVariset.add(viereinen.getKoordinaatti());
                }

            } else {
            }

        }

    }

    private boolean viereinenEiOleReunassa(Koordinaatti tutkittava, Kolmio viereinen, int vRivi, int vSarake) {
        int reunaRivi = peliruudukko.length - 1;
        int reunaSarake = peliruudukko[0].length - 1;
        if (vRivi < 0) {
            
            return false;
        } else if (vRivi > reunaRivi) {
            System.out.println(">>> " + tutkittava.toString() + ">>> - Tutkittava on ylareunassa");
            
            return false;
        } else if (vSarake < 0) {
            
            return false;
        } else if (vSarake > reunaSarake) {
            System.out.println(">>> " + tutkittava.toString() + ">>> - Tutkittava on oikeassa reunassa");
            
            return false;
        } else {
            viereinen = peliruudukko[vRivi][vSarake];
            return true;
        }
    }

    private int viereinenOnSamanvarinen(Color vVari, Color tVari) {
        if (vVari != tVari) {
            return 1;
        } else {
            return 0;
        }
    }
    
    private int viereistaEiOleKaytyLapi(int vRivi, int vSarake, boolean[][] onkoKaytyLapi) {
        if (onkoKaytyLapi[vRivi][vSarake]) {
            return 1;
        } else {
            return 0;
        }
    }
    
}
