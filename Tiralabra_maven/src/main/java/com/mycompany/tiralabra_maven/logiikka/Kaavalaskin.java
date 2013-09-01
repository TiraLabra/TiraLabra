package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.kayttoliittymat.Tekstikayttoliittyma;
import com.mycompany.tiralabra_maven.rajapinnat.Kayttoliittyma;
import com.mycompany.tiralabra_maven.suorituskykytestit.HajautuskartanSuorituskyky;
import com.mycompany.tiralabra_maven.suorituskykytestit.JononSuorituskyky;
import com.mycompany.tiralabra_maven.suorituskykytestit.PinonSuorituskyky;
import com.mycompany.tiralabra_maven.tietorakenteet.Jono;

/**
 * Ohjelman pääluokka, joka vastaa kolmen pääkomponentin, <b>Kayttoliittyma</b>,
 * <b>Tulkki</b> ja <b>Laskin</b>, käynnistämisestä sekä ohjelman suorituksen
 * päättämisestä. Lisäksi luokka käsittelee mahdolliset käyttäjän antamat
 * käynnistysparametrit.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public final class Kaavalaskin {

    public static final Kayttoliittyma KAYTTOLIITTYMA = new Tekstikayttoliittyma();

    /**
     * Ohjelman suoritus alkaa ja päättyy tässä staattisessa metodissa.
     *
     * @param args Mahdolliset käynnistysparametrit.
     */
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                tulostaOhje();
            } else if (args[0].equals("al")) {
                aritmetiikkaproseduuri();
            } else if (args[0].toLowerCase().trim().equals("sl")) {
                regexproseduuri();
            } else if (args[0].toLowerCase().trim().equals("kal")) {
                rpnAritmetiikkaproseduuri();
            } else if (args[0].toLowerCase().trim().equals("ksl")) {
                rpnRegexproseduuri();
            } else if (args[0].toLowerCase().trim().equals("hkp")) {
                HajautuskartanSuorituskyky.aloita();
            } else if (args[0].toLowerCase().trim().equals("jp")) {
                JononSuorituskyky.aloita();
            } else if (args[0].toLowerCase().trim().equals("pp")) {
                PinonSuorituskyky.aloita();
            } else {
                KAYTTOLIITTYMA.tulosta("Tuntematon argumentti \"" + args[0] + "\".\n");
            }
        }
        catch (Exception e) {
            KAYTTOLIITTYMA.tulosta("Tapahtui virhe!\n");
            KAYTTOLIITTYMA.tulosta(e.getMessage());
        }
    }
    
    private static void tulostaOhje() {
        KAYTTOLIITTYMA.tulosta("\nKäynnistysargumentit (joista yksi tulee valita"
                + "):\n\n" +
        "al	Käynnistää aritmeettisten kaavojen tulkin ja laskimen.\n" +
        "sl	Käynnistää säännöllisten lausekkeiden tulkin ja käsittelijän.\n"
                + "kal	Käynnistää aritmeettisten kaavojen laskimen.\n" +
        "ksl	Käynnistää säännöllisten lausekkeiden käsittelijän.\n" +
        "hkp	Käynnistää luokan Hajautuskartta suorituskykyä toiseen luokkaan"
                + "\n        vertaavan aliohjelman.\n" +
        "jp	Käynnistää luokan Jono suorituskykyä toiseen luokkaan"
                + "\n        vertaavan aliohjelman.\n" +
        "pp	Käynnistää luokan Pino suorituskykyä toiseen luokkaan"
                + "\n        vertaavan aliohjelman.\n");
    }

    private static double testaaAjanotonViive() {
        // En tiedä voiko tämän perusteella oikeasti tehdä mitään johtopäätöksiä
        long a, b, c = 0;
        for (int i = 0; i < 10; i++) {
            a = System.nanoTime();
            b = System.nanoTime() - a;
            c += b;
        }
        return c / 10.0;
    }

    private static void aritmetiikkaproseduuri()
            throws ArithmeticException, IllegalArgumentException {
        final Tulkki T = new Aritmetiikkatulkki();
        final Laskin L = new Laskin();
        String syote;
        int kaavanArvo;
        long aloitusaika, tulkinAika, laskimenAika;

        while (true) {
            KAYTTOLIITTYMA.tulosta("");
            syote = KAYTTOLIITTYMA.pyydaSyote("Kaava:              ");
            aloitusaika = System.nanoTime();
            if (syote.equals("lopeta")) {
                break;
            }
            Jono<String> kaava = T.tulkitseMerkkijono(syote);
            tulkinAika = System.nanoTime() - aloitusaika;
            KAYTTOLIITTYMA.tulosta("RPN-kaava:          " + kaava.tuloste() + '\n');
            aloitusaika = System.nanoTime();
            kaavanArvo = L.laske(kaava);
            laskimenAika = System.nanoTime() - aloitusaika;
            KAYTTOLIITTYMA.tulosta("Kaavan lukuarvo:    " + kaavanArvo + '\n');
            KAYTTOLIITTYMA.tulosta("Tulkkauksen kesto:  " + tulkinAika + " ns.\n");
            KAYTTOLIITTYMA.tulosta("Laskennan kesto:    " + laskimenAika + " ns.\n");
            System.out.printf("JVM:n viive:        %-1.1f ns.\n",
                    testaaAjanotonViive());
        }
    }
    
    private static void rpnAritmetiikkaproseduuri() {
        final Laskin L = new Laskin();
        String syote;
        int kaavanArvo;
        long aloitusaika, laskimenAika;
        
        while (true) {
            KAYTTOLIITTYMA.tulosta("");
            syote = KAYTTOLIITTYMA.pyydaSyote("RPN-kaava:          ");
            if (syote.equals("lopeta")) {
                break;
            }
            Jono<String> kaava = new Jono<>();
            for (String merkkijono : syote.split(" ")) {
                kaava.lisaa(merkkijono);
            }
            aloitusaika = System.nanoTime();
            kaavanArvo = L.laske(kaava);
            laskimenAika = System.nanoTime() - aloitusaika;
            KAYTTOLIITTYMA.tulosta("Kaavan lukuarvo:    " + kaavanArvo + '\n');
            KAYTTOLIITTYMA.tulosta("Laskennan kesto:    " + laskimenAika + " ns.\n");
            System.out.printf("JVM:n viive:        %-1.1f ns.\n",
                    testaaAjanotonViive());
        }
    }

    private static void regexproseduuri() throws IllegalArgumentException {
        
//        K.tulosta("Tämä ominaisuus on otettu pois käytöstä! (Katso \"ksl\")");
        
        final Tulkki T = new Regextulkki();
        final Regexkasittelija R = new Regexkasittelija();
        String syote;
        long aloitusaika, tulkinAika, rakennusaika;

        while (true) {
            KAYTTOLIITTYMA.tulosta("");
            syote = KAYTTOLIITTYMA.pyydaSyote("Regex:              ");
            if (syote.equals("lopeta")) {
                break;
            }
            aloitusaika = System.nanoTime();
            Jono<String> kaava = T.tulkitseMerkkijono(syote);
            tulkinAika = System.nanoTime() - aloitusaika;
            KAYTTOLIITTYMA.tulosta("RPN-regex:          " + kaava.tuloste() + '\n');
            aloitusaika = System.nanoTime();
            R.asetaSaannollinenLauseke(kaava);
            rakennusaika = System.nanoTime() - aloitusaika;
//            k.tulosta("Kaavan lukuarvo:    " + kaavanArvo + '\n');
            KAYTTOLIITTYMA.tulosta("Tulkkauksen kesto:  " + tulkinAika + " ns.\n");
            KAYTTOLIITTYMA.tulosta("NFA:n rakentaminen: " + rakennusaika + " ns.\n");
            System.out.printf("JVM:n viive:        %-1.1f ns.\n",
                    testaaAjanotonViive());
            tutkiRegex(R);
        }
    }
    
    private static void rpnRegexproseduuri() {
        final Regexkasittelija R = new Regexkasittelija();
        String syote;
        long aloitusaika, rakennusaika;
        
        while (true) {
            KAYTTOLIITTYMA.tulosta("");
            syote = KAYTTOLIITTYMA.pyydaSyote("RPN-regex:          ");
            if (syote.equals("lopeta")) {
                break;
            }
            Jono<String> kaava = new Jono<>();
            for (String merkkijono : syote.split(" ")) {
                kaava.lisaa(merkkijono);
            }
            aloitusaika = System.nanoTime();
            R.asetaSaannollinenLauseke(kaava);
            rakennusaika = System.nanoTime() - aloitusaika;
            KAYTTOLIITTYMA.tulosta("NFA:n rakentaminen: " + rakennusaika + " ns.\n");
            System.out.printf("JVM:n viive:        %-1.1f ns.\n",
                    testaaAjanotonViive());
            tutkiRegex(R);
        }
    }

    private static void tutkiRegex(final Regexkasittelija R) {
        String syote;
        long aloitusaika;
        boolean tasmaa;
        long etsintaAika;
        while (true) {
            syote = KAYTTOLIITTYMA.pyydaSyote("Merkkijono:         ");
            if (syote.equals("lopeta")) {
                break;
            }
            aloitusaika = System.nanoTime();
            tasmaa = R.tasmaa(syote);
            etsintaAika = System.nanoTime() - aloitusaika;
            if (tasmaa) {
                KAYTTOLIITTYMA.tulosta("Annettu merkkijono täsmää säännöllisen "
                        + "lausekkeen kanssa!\n");
            }
            KAYTTOLIITTYMA.tulosta("NFA:n läpikäynti:   " + etsintaAika + " ns.\n");
        }
    }
}
