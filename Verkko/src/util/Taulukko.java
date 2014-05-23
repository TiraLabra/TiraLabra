/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package util;

import java.util.Random;

/**
 * Luokka taulukoiden käsittelyyn.
 *
 * @author Arvoitusmies
 */
public class Taulukko {

    private static Random r = new Random();

    /**
     * Poistaa kaikki null arvoiset solut ja lyhentää taulukon koon niin, että
     * ei ole luppotilaa.
     *
     * @param ks
     * @return
     */
    public static Object[] poistaNullit(Object[] ks) {
        int nulleja = 0;
        for (int i = 0; i < ks.length; i++) {
            if (ks[i] == null) {
                nulleja++;
            }
        }
        Object[] uusi = new Object[ks.length - nulleja];
        int i = 0; //indeksoi uusi -taulukkoa
        for (Object o : ks) {
            if (o != null) {
                uusi[i] = o;
                i++;
            }
        }
        return uusi;
    }

    /**
     * Vaihtaa taulukossa i1 ja i2 indeksien omaavien olioiden paikkaa.
     *
     * @param taulukko
     * @param i1
     * @param i2
     */
    public static void swap(Object[] taulukko, int i1, int i2) {
        Object tmp = taulukko[i1];
        taulukko[i1] = taulukko[i2];
        taulukko[i2] = tmp;
    }

    /**
     * Sekoittaa fisher-yatesilla
     *
     * @param taulukko
     */
    public static void sekoita(Object[] taulukko) {
        for (int i = 0; i < taulukko.length - 1; i++) {
            int swapattavaIndeksi = r.nextInt(taulukko.length);
            if (i != swapattavaIndeksi) {
                Taulukko.swap(taulukko, i, swapattavaIndeksi);
            }
        }
    }

    private Taulukko() {
    }
}
