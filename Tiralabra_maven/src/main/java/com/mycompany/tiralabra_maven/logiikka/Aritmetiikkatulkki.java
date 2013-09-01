
package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.tietorakenteet.Jono;

/**
 * Luokan vastuulla on tulkita merkkijonoina annetut aritmeettiset kaavat
 * käänteiseen puolalaiseen notaatioon (RPN).
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 * @see Tulkki
 * @see Laskin
 */
public final class Aritmetiikkatulkki extends Tulkki {

    /**
     * Palauttaa luokan uuden instanssin.
     */
    public Aritmetiikkatulkki() {
//        PRIORITEETIT.lisaa('^', 1);
        PRIORITEETIT.lisaa('/', 2);
        PRIORITEETIT.lisaa('*', 2);
        PRIORITEETIT.lisaa('%', 2);
        PRIORITEETIT.lisaa('+', 3);
        PRIORITEETIT.lisaa('-', 3);
    }

    @Override
    protected boolean merkkiOnLyhenne() {
        // Potenssilasku käsitellään (ainoana) lyhenteenä:
        return merkki == '^';
    }
    
    @Override
    protected boolean merkkiOnOperaattori() {
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
    
    @Override
    protected boolean merkkiOnOperandi() {
        switch (merkki) {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                return true;
            default:
                return false;
        }
    }
    
    @Override
    protected void kasitteleLyhenne() {
        // Potenssilaskun voisi tottakai toteuttaa fiksumminkin jotenkin siihen
        // tapaan että käsiteltäisiin eksponenttia kuten sulkujen sisässä olevaa
        // lauseketta. Päädyin nyt kuitenkin näissä olosuhteissa paljon
        // alkeellisempaan ratkaisuun jossa tulkki korvaa potenssit
        // kertolaskuilla. Tämä ratkaisu ei kuulu Shunting yard -algoritmiin.
        if (JONO.onTyhja()) {
            kaadu("Potenssilaskun kantaluku puuttuu!");
        }
        // Siirretään kaikki pääjonon alkiot tilapäisjonoon ja tyhjennetään
        // pääjono, jotta sieltä saataisiin napattua eksponentti:
        Jono<String> tilapaisjono = new Jono<>();
        tilapaisjono.yhdista(JONO);
        JONO.tyhjenna();
        String kantaluku = tilapaisjono.viimeinen();
        indeksi++;
        merkki = syotteenMerkit[indeksi];
        if (!merkkiOnOperandi()) {
            kaadu("Eksponentin tulee olla positiivinen kokonaisluku");
        }
        kasitteleOperandi();
        int eksponentti = Integer.parseInt(JONO.poista());
        if (eksponentti > 1) {
            for (int i = 1; i < eksponentti; i++) {
                tilapaisjono.lisaa(kantaluku);
                tilapaisjono.lisaa("*");
            }
        }
//        PINO.lisaa('*');
//        tilapaisjono.lisaa("*");
        JONO.yhdista(tilapaisjono);
        indeksi--;
    }
    
    @Override
    protected void kasitteleOperandi() throws IllegalArgumentException {
        if (merkkiOnOperandi()) {
            APUJONO.lisaa(merkki);
            indeksi++;
            if (indeksi == syotteenMerkit.length) {
                kokoaData();
                return;
            }
            merkki = syotteenMerkit[indeksi];
            kasitteleOperandi();
        } else {
            kokoaData();
        }
    }
    
    protected void kokoaData() throws IllegalArgumentException {
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
}
