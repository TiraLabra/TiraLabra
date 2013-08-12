package OhjelmaLogiikka;

import Tietorakenteet.Koodi;

/**
 * Utility-luokka bittishiftejä varten jotta koodin lukeminen olisi helpompaa
 */
public class BittiUtility {

    /**
     * Hakee annetun Koodi-objektin koodi-kentästä paikka-muuttujan määrittämän
     * bitin arvon ja palauttaa sen Huomaa että metodi ei tarkista että
     * paikka-muuttuja sisältää järkevän arvon
     *
     * @param koodi Koodi-objekti jonka koodikenttää tutkitaan
     * @param paikka Paikka jolta bitti halutaan
     * @return 0 tai 1 riippuen bitin arvosta
     */
    public static int haeBitinArvoPaikasta(Koodi koodi, int paikka) {
        long arvo = (koodi.koodi & (1 << paikka));
        arvo = arvo >> paikka;
        return (int)arvo;
    }

    /**
     * Hakee annetun byte-muuttujasta paikka-muuttujan määrittämän bitin arvon
     * ja palauttaa sen Huomaa että metodi ei tarkista että paikka-muuttuja
     * sisältää järkevän arvon
     *
     * @param koodi byte jonka arvoa tutkitaan
     * @param paikka Paikka jolta bitti halutaan
     * @return 0 tai 1 riippuen bitin arvosta
     */
    public static int haeBitinArvoPaikasta(byte koodi, int paikka) {
        int arvo = (koodi & (1 << paikka));
        arvo = arvo >> paikka;
        return arvo;
    }
    
    public static int haeBitinArvoPaikasta(long koodi, long paikka) {
        long arvo =  (koodi & (1L << paikka));
        arvo = arvo >> paikka;
        return (int)arvo;
    }

    /**
     * Tallentaa koodi-objektin koodi-kentän kopioon paikka-muuttujan
     * määrittämälle paikalle arvo-muuttujan arvon. Alkuperäistä koodi-kentän
     * arvoa ei muuteta. Huomaa että metodi ei nollaa bitin arvoa jos bitin arvo
     * on jo yksi. Lisäksi metodi ei tarkista että paikka-muuttujan arvo on
     * järkevä
     *
     * @param koodi Koodi-objekti jonka koodi-kenttää halutaan muokata
     * @param arvo Uusi bitin arvo
     * @param paikka Bitin paikka
     * @return koodi-kentän arvo jolle operaatio on toteutettu
     */
    public static long tallennaBitinArvoPaikalle(Koodi koodi, int arvo, int paikka) {
        return tallennaBitinArvoPaikalle(koodi.koodi, arvo, paikka);
    }

    /**
     * Tallentaa tavu-muuttujan kopioon paikka-muuttujan määrittämälle paikalle
     * arvo-muuttujan arvon. Alkuperäistä koodi-kentän arvoa ei muuteta. Huomaa
     * että metodi ei nollaa bitin arvoa jos bitin arvo on jo yksi. Lisäksi
     * metodi ei tarkista että paikka-muuttujan arvo on järkevä
     *
     * @param tavu Byte jonka bittejä halutaan muokata
     * @param arvo Uusi bitin arvo
     * @param paikka Bitin paikka
     * @return koodi-kentän arvo jolle operaatio on toteutettu
     */
    public static byte tallennaBitinArvoPaikalle(byte tavu, int arvo, int paikka) {
        return (byte) (tavu | (arvo << paikka));
    }

    /**
     * Tallentaa longArvo-muuttujan kopioon paikka-muuttujan määrittämälle paikalle
     * arvo-muuttujan arvon. Alkuperäistä koodi-kentän arvoa ei muuteta. Huomaa
     * että metodi ei nollaa bitin arvoa jos bitin arvo on jo yksi. Lisäksi
     * metodi ei tarkista että paikka-muuttujan arvo on järkevä
     *
     * @param longArvo Koodi-objekti jonka bittejä   halutaan muokata
     * @param arvo Uusi bitin arvo
     * @param paikka Bitin paikka
     * @return koodi-kentän arvo jolle operaatio on toteutettu
     */
    public static long tallennaBitinArvoPaikalle(long longArvo, long arvo, int paikka) {
        return (long) (longArvo | (arvo << paikka));
    }
}
