package com.mycompany.tiralabra_maven.tyokalut;

import java.util.Iterator;

/**
 * Tietojoukkojen käsittelyä helpottava lista.
 *
 * @author Markus
 */
public class LinkitettyLista implements Iterable<Solmu> {

    private String kuvaus;
    private Solmu viimeinen;

    public LinkitettyLista() {
    }

    public Iterator<Solmu> iterator() {
        return new Iterator<Solmu>() {
            Solmu solmu = viimeinen;

            public boolean hasNext() {
                return solmu != null;
            }

            public Solmu next() {
                Solmu temp = solmu;
                solmu = solmu.getSeuraava();
                return temp;
            }

            public void remove() {
            }
        };

    }

    public void lisaa(int[] arvot) {
        Solmu uusi = new Solmu(arvot);
        uusi.setSeuraava(viimeinen);
        viimeinen = uusi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

}
