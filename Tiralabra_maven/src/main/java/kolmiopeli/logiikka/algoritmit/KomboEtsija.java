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

    public KomboEtsija(Kolmio[][] peliruudukko) {
        this.peliruudukko = peliruudukko;
    }

    /**
     * Kay lapi juuriarvottujen kolmioiden laheisia kolmioita niin pitkalle kun
     * loytyy sopivan varisia.
     *
     * @param Array kohdista joissa on uunituoreita uusia kolmioita arvottu.
     */
    public HashSet<Koordinaatti> etsiKombot(ArrayList<Koordinaatti> juuriArvotut) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
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
        HashSet<Koordinaatti> kaikkiTuhoutuvat = new HashSet<Koordinaatti>();


        // Lahdetaan kaymaan peliruudukkoa lapi jokaisesta uudesta kolmiosta vuorotellen
        for (int i = 0; i < juuriArvotutTaulukko.length; i++) {
            Koordinaatti root = juuriArvotutTaulukko[i];
            System.out.println("---------------------");
            System.out.println(root.toString() + " - Lahdetaan tutkimaan juurta.");

            // Jos jokin aiempi kolmio on jo loytanyt jonkin toisen uusista kolmioista niin jatketaan seuraavaan uuteen kolmioon
            // esim jos uusissa kolmioissa on vierekkain samanvarisia
            if (kaikkiTuhoutuvat.contains(root)) {
                System.out.println(root.toString() + " - Juuri oli jo palautettavassa koordinaattijoukossa");
                continue;
            }

            // Merkkaus, jono ja lista johon kerataan rootin viereiset samanvariset
            boolean[][] onkoKaytyLapi = new boolean[peliruudukko.length][peliruudukko[0].length];
            LinkedList<Koordinaatti> tutkittavienJono = new LinkedList<Koordinaatti>();
            ArrayList<Koordinaatti> rootinVariset = new ArrayList<Koordinaatti>();

            tutkittavienJono.addLast(root);
            onkoKaytyLapi[root.getRivi()][root.getSarake()] = true;
            rootinVariset.add(root);

            System.out.println("******");
            while (!tutkittavienJono.isEmpty()) {
                Koordinaatti tutkittava = tutkittavienJono.removeFirst();
                int tRivi = tutkittava.getRivi();
                int tSarake = tutkittava.getSarake();
                Color tVari = peliruudukko[tRivi][tSarake].getKolmionVari();
                System.out.println(">>> " + tutkittava.toString() + ">>> - Tutkitaan while-loopissa, vari " + tVari);


                // Tutkitaan kolmion viereisia kolmiota ehdoilla:
                // 1. Viereinen ei ole indexOutOfBounds
                // 2. Viereinen on samanvarinen kuin tutkittava
                // 3. Viereista ei ole kayty lapi
                Kolmio viereinen = null;
                
                // Oikeanpuolinen
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
                    System.out.println(">>> " + tutkittava.toString() + ">>> - Tutkittava on oikeassa reunassa");
                }

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
                        System.out.println(">>> " + tutkittava.toString() + ">>> - Tutkittava on ylareunassa");
                    }
                    
                }
                System.out.println("******");
            }
            
            
            // Jos aloituskolmion kanssa samanvarisia vierekkaita loytyi vahintaan kolme niin lisataan ne tuhoutuvien joukkoon
            if (rootinVariset.size() >= 3) {
                System.out.println(root.toString() + " - Juuren ymparilta loytyi samanvarisia: " + rootinVariset.size());
                kaikkiTuhoutuvat.addAll(rootinVariset);
            } else {
                System.out.println(root.toString() + " - Samanvarisia loytyi vain: " + rootinVariset.size());
            }
        }
        
        
        // Palautetaan lopuksi kaikki mahdolliset tuhoutuvat
        System.out.println("Loydetyt kombot: ");
        System.out.println(kaikkiTuhoutuvat);
        return kaikkiTuhoutuvat;
    }
}
