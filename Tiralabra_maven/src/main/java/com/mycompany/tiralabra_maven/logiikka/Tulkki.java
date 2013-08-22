
package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.tietorakenteet.Hajautuskartta;
import com.mycompany.tiralabra_maven.tietorakenteet.Jono;
import com.mycompany.tiralabra_maven.tietorakenteet.Pino;
import java.util.EmptyStackException;

/**
 * Luokan vastuulla on tulkita merkkijonoina annetut matemaattiset kaavat
 * käänteiseen puolalaiseen notaatioon (RPN) käyttäen Shunting yard -algoritmia.
 *
 * @author John Lång
 * @see Laskin
 */
public final class Tulkki {

    private static final Hajautuskartta<Integer>    PRIORITEETIT
            = new Hajautuskartta<>();
    private static final Pino<Character>            PINO = new Pino<>();
    private static final Jono<String>               JONO = new Jono<>();
    private static final Jono<Character>            APUJONO = new Jono<>();
    private static String                           merkkijono;
    private static char[]                           syotteenMerkit;
    private static char                             merkki;
    private static int                              indeksi;
    
    static {
        PRIORITEETIT.lisaa('/', 1);
        PRIORITEETIT.lisaa('*', 1);
        PRIORITEETIT.lisaa('%', 1);
        PRIORITEETIT.lisaa('+', 2);
        PRIORITEETIT.lisaa('-', 2);
    }

    /**
     * Palauttaa luokan uuden instanssin.
     */
    public Tulkki() {
    }

    /**
     * Tulkitsee (mikäli mahdollista) syötteenä annetun matemaattisen kaavan
     * RPN kaavaksi. Tulkin tulee varmistaa että kaavan sulkumerkit täsmäävät
     * ja että kaikki kaavan (literaali-)luvut mahtuvat int-muuttujiin.
     * Tarkemmat laskemiseen ja operaattoreihin liittyvät tarkastukset kuuluvat
     * luokan <b>Laskin</b> vastuulle.
     *
     * @param MERKKIJONO Matemaattinen kaava.
     * @return Matemaattinen kaava käänteisellä puolalaisella notaatiolla.
     * @throws IllegalArgumentException Jos kaava ei sisällä yhtä paljon
     * vasemman- ja oikeanpuoleisia sulkumerkkejä tai jos se sisältää numeerisia
     * arvoja jotka ovat liian suuria int-muuttujaan.
     * @see Laskin
     */
    public Jono<String> tulkitseMerkkijono(final String MERKKIJONO)
            throws IllegalArgumentException {
        merkkijono = MERKKIJONO;
        
        // Koska tulkin kentät ovat staattiset, on jonoon voinut jäädä edellisen
        // kutsukerran paluuarvo.        
        JONO.tyhjenna();
        // Pinoon on voinut myös jäädä jotain.
        PINO.tyhjenna();
        
//        // Debuggaustulosteita:
//        System.out.println(PRIORITEETIT);
//        System.out.println(PRIORITEETIT.tayttosuhde());
//        PRIORITEETIT.uudelleenhajauta(5);
//        System.out.println(PRIORITEETIT);
//        System.out.println(PRIORITEETIT.tayttosuhde());
        
        // Pientä kapselointia
        iteroiMerkit();        
        tyhjennaPino();

        return JONO;
    }
    
    private void iteroiMerkit()
            throws EmptyStackException, IllegalArgumentException {
        syotteenMerkit = merkkijono.toCharArray();
        for (indeksi = 0; indeksi < syotteenMerkit.length; indeksi++) {
            merkki = syotteenMerkit[indeksi];
            if (merkki == ' ') {
                continue;
            } else if (merkkiOnNumero()) {
                kasitteleLuku();
                indeksi--;
            } else if (merkkiOnOperaattori()) {
                kasitteleOperaattori();
            } else if (merkki == '(') {
                PINO.lisaa(merkki);
            } else if (merkki == ')') {
                while (true) {
                    if (PINO.onTyhja()) {
                        // Vasen sulkumerkki puuttuu. (Code coverage bugittaa
                        // tämän rivin osalta jostain syystä, testimetodi
                        // testEpakelpoKaava3 kattaa tämän rivin.)
                        kaadu("Kaavan \"" + merkkijono + "\" sulkumerkit eivät "
                                + "täsmää!");
                    } else if (PINO.kurkista() == '(') {
                        PINO.poista();
                        break;
                    }
                    JONO.lisaa(PINO.poista() + "");
                }
            } else {
                // Code coverage bugittaa myös tässä, testEpakelpoKaava4 testaa
                // tämän rivin.
                kaadu("Kaava \"" + merkkijono + "\" sisältää tuntemattomia"
                        + "merkkejä!");
            }
        }
    }

    private boolean merkkiOnNumero() {
        switch (merkki) {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                return true;
            default:
                return false;
        }
    }
    
    private boolean merkkiOnOperaattori() {
        // Tämän tarkastuksen voisi myös suorittaa hakemalla merkkiä
        // hajautuskartasta ja vertaamalla sitä arvoon null (koska kaikki
        // tuetut operaattorit on lisätty hajautuskarttaan). Switch on nopeampi
        // sillä siinä tarvitsee suorittaa vain yksi vertailu.
        switch (merkki) {
            case '+': case '-': case '*': case '/': case '%':
                return true;
            default:
                return false;
        }
    }

    private void kasitteleLuku() throws IllegalArgumentException {
        if (merkkiOnNumero()) {
            APUJONO.lisaa(merkki);
            indeksi++;
            if (indeksi == syotteenMerkit.length) {
                kokoaLuku();
                return;
            }
            merkki = syotteenMerkit[indeksi];
            kasitteleLuku();
        } else {
            kokoaLuku();
        }
    }
    
    private void kokoaLuku() throws IllegalArgumentException {
        StringBuilder mjr = new StringBuilder();
        while (!APUJONO.onTyhja()) {
            mjr.append(APUJONO.poista());
        }
        // Varmisteteaan että luku mahtuu int-muuttujaan. (Lukuhan ei voi olla
        // negatiivinen paitsi epäsuorasti vähennyslaskun muodossa.)
        // Pitääkin miettiä jos toteuttaisi myös liukuluvut...
        if (Integer.parseInt(mjr.toString()) <= Integer.MAX_VALUE) {
            JONO.lisaa(mjr.toString());
        } else {
            kaadu("Kaava sisälsi liian suuria lukuja!");
        }
    }
    
    private void kasitteleOperaattori() {
        if (PINO.onTyhja()) {
            PINO.lisaa(merkki);
        } else {
            char pinonYlin = PINO.kurkista();
            if (pinonYlin != '(') {
                // Pienemmän prioriteetin laskutoimitukset suoritetaan ensin.
                // Siispä ne lisätään ulostuloon ensimmäisinä.
                if (PRIORITEETIT.haeEnsimmainen(merkki) >= PRIORITEETIT.haeEnsimmainen(pinonYlin)) {
                    JONO.lisaa(PINO.poista() + "");
                }
            }
            PINO.lisaa(merkki);
        }
    }    

    private void tyhjennaPino()
            throws IllegalArgumentException {
        while (!PINO.onTyhja()) {
            merkki = PINO.poista();
            // Pinossa ei saa olla enää sulkuja
            if (merkki == '(') {
                kaadu("Kaavan \"" + merkkijono + "\" sulkumerkit eivät täsmää!");
            }
            JONO.lisaa(merkki + "");
        }
    }
    
    private void kaadu(final String VIESTI) throws IllegalArgumentException {
        throw new IllegalArgumentException(VIESTI);
    }
}
