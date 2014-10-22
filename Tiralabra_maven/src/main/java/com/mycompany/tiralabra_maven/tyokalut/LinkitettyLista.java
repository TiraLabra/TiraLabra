package com.mycompany.tiralabra_maven.tyokalut;

import java.util.Iterator;

/**
 * Tietojoukkojen käsittelyä helpottava linkitetty lista.
 *
 * @see Iterable
 * @see Solmu
 * @author Markus
 */
public class LinkitettyLista implements Iterable<Solmu> {

    /**
     * Listan pää
     */
    private Solmu viimeinen;

    /**
     * Palauttaa listan läpikäyntiin tarkoitetun iteraattorin.
     *
     * @return Iteraattori jonka avulla lista voidaan käydä helposti läpi.
     */
    public Iterator<Solmu> iterator() {
        /**
         * Luodaan uusi iteraattori. Koska sitä ei käytetä missään muualla
         * tuntui liialliselta määritellä sitä omassa tiedostossaan.
         *
         */
        return new Iterator<Solmu>() {
            Solmu solmu = viimeinen;

            /**
             * Kertoo onko listalla seuraava solmu mikä voidaan hakea.
             *
             * @return True jos listalla on vielä alkioita, muulloin false.
             */
            public boolean hasNext() {
                return solmu != null;
            }

            /**
             * *
             * Palauttaa seuraavan solmun listalta.
             *
             * @return Listan seuraava solmu.
             */
            public Solmu next() {
                Solmu temp = solmu;
                solmu = solmu.getSeuraava();
                return temp;
            }

            /**
             * Ei tuettu toiminto tässä implementaatiossa.
             */
            public void remove() {
            }
        };

    }

    /**
     * Lisää listalle uuden taulukon.
     *
     * @param arvot Taulukko mikä lisätään listalle.
     */
    public void lisaa(int[] arvot) {
        Solmu uusi = new Solmu(arvot);
        uusi.setSeuraava(viimeinen);
        viimeinen = uusi;
    }
}
