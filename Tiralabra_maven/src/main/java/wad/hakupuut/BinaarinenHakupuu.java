package wad.hakupuut;

import wad.solmu.Solmu;

/**
 *
 * Perinteinen binäärinen hakupuu, joka käyttää HakupuuRajapintaa.
 */
public class BinaarinenHakupuu implements HakupuuRajapinta {

    /**
     * Puun juurisolmu, joka määritellään ensimmäisen lisäyksen yhteydessä
     */
    Solmu juuri;

    /**
     * Puusta haettava data
     *
     * @param haettava data joka halutaan puusta
     * @return palauttaa solmu tietorakenteen, jonka arvo on sidottu haettavaan
     * dataan. Jos solua ei löydy niin palauttaa null.
     */
    public Solmu hae(Object haettava) {
        int avain = haettava.hashCode();
        Solmu tulos = juuri;
        while (tulos != null && tulos.getAvain() != avain) {
            if (avain < tulos.getAvain()) {
                tulos = tulos.getVasen();
            } else {
                tulos = tulos.getOikea();
            }
        }
        return tulos;
    }

    /**
     * Puun lisäysoperaatio
     *
     * @param lisattava puuhun lisättävä data.
     * @return palauttaa lisätyn solmun.
     */
    public Solmu lisaa(Object lisattava) {
        Solmu uusiSolmu = new Solmu(lisattava);
        if (juuri == null) {
            juuri = uusiSolmu;
            return uusiSolmu;
        }
        Solmu x = juuri;
        Solmu p = juuri;
        while (x != null) {
            p = x;
            if (uusiSolmu.getAvain() < x.getAvain()) {
                x = x.getVasen();
            } else {
                x = x.getOikea();
            }
        }
        uusiSolmu.setVanhempi(p);
        if (uusiSolmu.getAvain() < p.getAvain()) {
            p.setVasen(uusiSolmu);
        } else {
            p.setOikea(uusiSolmu);
        }
        return uusiSolmu;
    }

    /**
     * Puun poisto-operaatio.
     *
     * @param poistettava Poistettavan solmun arvo
     * @return palauttaa poistetun solmun. Jos solmua ei ole, palauttaa null.
     */
    public Solmu poista(Object poistettava) {
        Solmu poistettavaSolmu;
        if ((poistettavaSolmu = hae(poistettava)) == null) {
            return null;
        }

        //1. Poistettavalla solmulla ei ole lapsia
        if (poistettavaSolmu.lapseton()) {
            poistettavallaEiLapsia(poistettavaSolmu);
            return poistettavaSolmu;
        } //2. Poistettavalla solmulla on yksi lapsi
        else if (poistettavaSolmu.getOikea() == null || poistettavaSolmu.getVasen() == null) {
            poistettavallaOnYksiLapsi(poistettavaSolmu);
            return poistettavaSolmu;
        } //3. Poistettavalla solmulla on kaksi lasta
        else {
            return poistettavallaKaksiLasta(poistettavaSolmu);
        }
    }

    /**
     * 1. Metodi lapsettoman solmun poistolle
     *
     * @param poistettavaSolmu on solmu joka poistetaan
     * @return palauttaa true jos poisto onnistuu.
     */
    private void poistettavallaEiLapsia(Solmu poistettavaSolmu) {
        if (poistettavaSolmu.getVanhempi() == null) {
            this.juuri = null;
            return;
        }

        Solmu vanhempi = poistettavaSolmu.getVanhempi();

        if (vanhempi.getVasen() == poistettavaSolmu) {
            vanhempi.setVasen(null);
        } else {
            vanhempi.setOikea(null);
        }
    }

    /**
     * 2. Metodi yksilapsisen solmun poistolle
     *
     * @param poistettavaSolmu on solmu joka poistetaan
     * @return palauttaa true jos poisto onnistuu.
     */
    private void poistettavallaOnYksiLapsi(Solmu poistettavaSolmu) {
        Solmu lapsi = null;
        if (poistettavaSolmu.getOikea() != null) {
            lapsi = poistettavaSolmu.getOikea();
        } else {
            lapsi = poistettavaSolmu.getVasen();
        }
        Solmu vanhempi = poistettavaSolmu.getVanhempi();
        lapsi.setVanhempi(vanhempi);

        if (vanhempi == null) {
            juuri = lapsi;
            return;
        }

        if (vanhempi.getVasen() == poistettavaSolmu) {
            vanhempi.setVasen(lapsi);
        } else {
            vanhempi.setOikea(lapsi);
        }
    }

    /**
     * 3. Metodi kaksilapsisen solmun poistolle
     *
     * @param poistettavaSolmu on solmu joka poistetaan
     * @return palauttaa true jos poisto onnistuu.
     */
    private Solmu poistettavallaKaksiLasta(Solmu poistettavaSolmu) {
        Solmu vanhempi, lapsi;
        Solmu seuraaja = min(poistettavaSolmu.getOikea());
        poistettavaSolmu.setArvo(seuraaja.getArvo());
        lapsi = seuraaja.getOikea();
        vanhempi = seuraaja.getVanhempi();

        if (vanhempi.getVasen() == seuraaja) {
            vanhempi.setVasen(lapsi);
        } else {
            vanhempi.setOikea(lapsi);
        }

        if (lapsi != null) {
            lapsi.setVanhempi(vanhempi);
        }
        return seuraaja;
    }

    /**
     * Hakee pyydetyn solmun alipuiden minimi arvon.
     *
     * @param solmu, kohta puuta, josta minimi halutaan selvittää
     * @return palauttaa pienimmän alkion puusta.
     */
    public Solmu min(Solmu solmu) {
        Solmu min = solmu;
        while (min.getVasen() != null) {
            min = min.getVasen();
        }
        return min;
    }

    /**
     * Palauttaa puun merkkijono esityksen esijärjestyksessä.
     *
     * @return merkkijono
     */
    public String toString() {
        return juuri.toString();
    }
}
