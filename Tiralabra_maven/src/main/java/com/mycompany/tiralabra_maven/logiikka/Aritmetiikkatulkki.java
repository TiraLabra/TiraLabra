
package com.mycompany.tiralabra_maven.logiikka;

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
        PRIORITEETIT.lisaa('/', 1);
        PRIORITEETIT.lisaa('*', 1);
        PRIORITEETIT.lisaa('%', 1);
        PRIORITEETIT.lisaa('+', 2);
        PRIORITEETIT.lisaa('-', 2);
    }

    @Override
    protected boolean merkkiOnLyhenne() {
        // Aritmetiikkatulkille ei ole lyhenteitä.
        return false;
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
    protected boolean merkkiOnDataa() {
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
        // Lyhenteet eivät ole käytössä aritmetiikkatulkilla. Siispä ei tehdä
        // mitään.
    }
    
    @Override
    protected void kasitteleData() throws IllegalArgumentException {
        if (merkkiOnDataa()) {
            APUJONO.lisaa(merkki);
            indeksi++;
            if (indeksi == syotteenMerkit.length) {
                kokoaData();
                return;
            }
            merkki = syotteenMerkit[indeksi];
            kasitteleData();
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
