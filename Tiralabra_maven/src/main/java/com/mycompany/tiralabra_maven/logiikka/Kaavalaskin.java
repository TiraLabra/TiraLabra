package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.kayttoliittymat.Tekstikayttoliittyma;
import com.mycompany.tiralabra_maven.rajapinnat.Kayttoliittyma;
import com.mycompany.tiralabra_maven.suorituskykytestit.HajautuskartanSuorituskyky;
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
    
    static final Kayttoliittyma K = new Tekstikayttoliittyma();

    /**
     * Ohjelman suoritus alkaa ja päättyy tässä staattisessa metodissa.
     *
     * @param args Mahdolliset käynnistysparametrit.
     */
    public static void main(String[] args) {        
        if (args.length == 0 || args[0].equals("al")) {
            try {
                aritmetiikkaproseduuri();
            }
            catch (Exception e) {
                System.out.println("Tapahtui virhe!");
                System.out.println(e.getMessage());
            }
        } else if (args[0].toLowerCase().trim().equals("sl")) {
            try {
                regexproseduuri();
            }
            catch (Exception e) {
                System.out.println("Tapahtui virhe!");
                System.out.println(e.getMessage());
            }
        } else if (args[0].toLowerCase().trim().equals("hkp")) {
            HajautuskartanSuorituskyky.aloita();
        } else {
            System.out.println("Tuntematon argumentti \"" + args[0] + "\".");
        }
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

    private static void aritmetiikkaproseduuri() throws ArithmeticException, IllegalArgumentException {
        final Tulkki T = new Aritmetiikkatulkki();
        final Laskin L = new Laskin();
        String syote;
        int kaavanArvo;
        long aloitusaika, tulkinAika, laskimenAika;
        
        while (true) {
            syote = K.pyydaSyote("Kaava:              ");
            aloitusaika = System.nanoTime();
            if (syote.equals("lopeta")) {
                break;
            }
            Jono<String> kaava = T.tulkitseMerkkijono(syote);
            tulkinAika = System.nanoTime() - aloitusaika;
            K.tulosta("RPN-kaava:          " + kaava.tuloste() + '\n');
            aloitusaika = System.nanoTime();
            kaavanArvo = L.laske(kaava);
            laskimenAika = System.nanoTime() - aloitusaika;
            K.tulosta("Kaavan lukuarvo:    " + kaavanArvo + '\n');
            K.tulosta("Tulkkauksen kesto:  " + tulkinAika + " ns.\n");
            K.tulosta("Laskennan kesto:    " + laskimenAika + " ns.\n");
            System.out.printf("JVM:n viive:        %-1.1f ns.\n",
                    testaaAjanotonViive());
        }
    }

    private static void regexproseduuri() throws IllegalArgumentException {
        final Tulkki T = new Regextulkki();
        final Regexkasittelija R = new Regexkasittelija();
        String syote;
        long aloitusaika, tulkinAika, rakennusAika, etsintaAika;
        boolean tasmaa;
        
        while (true) {                
            syote = K.pyydaSyote("Regex:              ");
            if (syote.equals("lopeta")) {
                break;
            }
            aloitusaika = System.nanoTime();
            Jono<String> kaava = T.tulkitseMerkkijono(syote);
            tulkinAika = System.nanoTime() - aloitusaika;
            K.tulosta("RPN-regex:          " + kaava.tuloste() + '\n');
            aloitusaika = System.nanoTime();
            R.asetaSaannollinenLauseke(kaava);
            rakennusAika = System.nanoTime() - aloitusaika;
//            k.tulosta("Kaavan lukuarvo:    " + kaavanArvo + '\n');
            K.tulosta("Tulkkauksen kesto:  " + tulkinAika + " ns.\n");
            K.tulosta("NFA:n rakentaminen: " + rakennusAika + " ns.\n");
            System.out.printf("JVM:n viive:        %-1.1f ns.\n",
                    testaaAjanotonViive());
            while (true) {
                syote = K.pyydaSyote("Merkkijono:         ");
                if (syote.equals("lopeta")) {
                    break;
                }
                aloitusaika = System.nanoTime();
                tasmaa = R.tasmaa(syote);
                etsintaAika = System.nanoTime() - aloitusaika;
                if (tasmaa) {
                    K.tulosta("Annettu merkkijono täsmää säännöllisen "
                            + "lausekkeen kanssa!\n");
                }
                K.tulosta("NFA:n läpikäynti:   " + etsintaAika + " ns.\n");
            }
        }
    }
}
