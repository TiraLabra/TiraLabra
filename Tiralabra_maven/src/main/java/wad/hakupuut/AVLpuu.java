package wad.hakupuut;

import wad.solmu.Solmu;

/**
 *
 * AVL-puu on itseääntasapainottava binäärinen hakupuu. 
 * Tämä AVL on Binäärisen hakupuun laajennus.
 */
public class AVLpuu extends BinaarinenHakupuu {

    /**
     * AVL-puun lisäys eroaa binäärisen hakupuun lisäyksestä, sillä AVL-puu ei
     * voi mennä epätasapainoon.
     *
     * @param lisattava puuhun lisättävä data.
     */
    @Override
    public Solmu lisaa(Object lisattava) {
        Solmu vanhempi = null;
        Solmu uusiSolmu = super.lisaa(lisattava);
        uusiSolmu.setKorkeus(0); //kovakoodattu korkeus uudelle solmulle.

        if (uusiSolmu.getVanhempi() != null) {
            vanhempi = uusiSolmu.getVanhempi();
        }

        while (vanhempi != null) {
            if (epaTasapainossa(vanhempi)) {
                puunKorjaus(vanhempi, vanhempi.getVanhempi(), alipuunKierto(vanhempi));
            }
            vanhempi.setKorkeus(max(korkeus(vanhempi.getVasen()), korkeus(vanhempi.getOikea())) + 1);
            vanhempi = vanhempi.getVanhempi();
        }
        return uusiSolmu;
    }
    
    /**
     * AVL-poisto, hyödyntää binäärisen hakupuun poistoa.
     * Varmistaa, että tasapaino säilyy.
     * @param poistettava data joka halutaan poistaa puusta
     * @return palauttaa poistettavan solmun, paitsi jos poistettavalla solmulla on kaksilasta.
     * Silloin palautetaan poistettavan solmun oikean alipuun minimi.
     */
    @Override
    public Solmu poista(Object poistettava) {
        Solmu poistettavaSolmu = super.poista(poistettava);
        Solmu vanhempi = poistettavaSolmu.getVanhempi();
        
        while (vanhempi != null) {
            if (epaTasapainossa(vanhempi)) {
                Solmu v = vanhempi.getVanhempi();
                puunKorjaus(vanhempi, v, alipuunKierto(vanhempi));
                vanhempi = v;
            } else {
                vanhempi.setKorkeus(max(korkeus(vanhempi.getVasen()), korkeus(vanhempi.getOikea())) + 1);
                vanhempi = vanhempi.getVanhempi();
            }
        } return poistettavaSolmu;
    }

    /**
     * Tutkii onko Solmun alipuut epätasapainossa
     *
     * @param solmu tutkittavan solmun alipuut
     * @return palauttaa true jos puu on epätasapainossa
     */
    public boolean epaTasapainossa(Solmu solmu) {
        return (korkeus(solmu.getVasen()) == korkeus(solmu.getOikea()) + 2)
                || (korkeus(solmu.getOikea()) == korkeus(solmu.getVasen()) + 2);
    }

    /**
     * Puun sisäinen järjestyksen palautus.
     *
     * @param solmu korjattava solmu
     * @param vanhempi korjattavan solmun vanhempi
     * @param alipuu alipuun solmun juuri
     */
    private void puunKorjaus(Solmu solmu, Solmu vanhempi, Solmu alipuu) {
        if (vanhempi != null) {
            if (vanhempi.getVasen() == solmu) {
                vanhempi.setVasen(alipuu);
            } else {
                vanhempi.setOikea(alipuu);
            }
            vanhempi.setKorkeus(max(korkeus(vanhempi.getVasen()), korkeus(vanhempi.getOikea())) + 1);
        } else {
            juuri = alipuu;
        }
    }

    /**
     * Huolehtii puun kierto metodien valitsemisesta
     * @param solmu kierrettävä solmu
     * @return palauttaa kirretyn solmun.
     */
    private Solmu alipuunKierto(Solmu solmu) {
        if (korkeus(solmu.getOikea()) == korkeus(solmu.getVasen()) + 2) {
            if (korkeus(solmu.getOikea().getOikea()) > korkeus(solmu.getOikea().getVasen())) {
                return vasenKaanto(solmu);
            } else {
                return oikeaVasenKaanto(solmu);
            }
        }
        if (korkeus(solmu.getVasen()) == korkeus(solmu.getOikea()) + 2) {
            if (korkeus(solmu.getVasen().getVasen()) > korkeus(solmu.getVasen().getOikea())) {
                return oikeaKaanto(solmu);
            } else {
                return vasenOikeaKaanto(solmu);
            }
        }
        return null;
    }
    
    /**
     * Käännetään solmua vasemmalle puussa.
     * @param k1 käännettävä solmu
     * @return palautetaan k1 korvaava solmu, jos sellainen on.
     */
    private Solmu vasenKaanto(Solmu k1) {
        if (k1.getOikea() == null) {
            return null;
        }
        Solmu k2 = k1.getOikea();
        k2.setVanhempi(k1.getVanhempi());
        k1.setVanhempi(k2);
        k1.setOikea(k2.getVasen());
        k2.setVasen(k1);
        if (k1.getOikea() != null) {
            k1.getOikea().setVanhempi(k1);
        }
        k1.setKorkeus(max(korkeus(k1.getVasen()), korkeus(k1.getOikea())) + 1);
        k2.setKorkeus(max(korkeus(k2.getVasen()), korkeus(k2.getOikea())) + 1);
        return k2;
    }

    /**
     * Käännetään solmua oikealle puussa.
     * @param k1 käännettävä solmu
     * @return palautetaan k1 korvaava solmu, jos sellainen on.
     */
    private Solmu oikeaKaanto(Solmu k1) {
        if (k1.getVasen() == null) {
            return null;
        }
        Solmu k2 = k1.getVasen();
        k2.setVanhempi(k1.getVanhempi());
        k1.setVanhempi(k2);
        k1.setVasen(k2.getOikea());
        k2.setOikea(k1);
        if (k1.getVasen() != null) {
            k1.getVasen().setVanhempi(k1);
        }
        k1.setKorkeus(max(korkeus(k1.getVasen()), korkeus(k1.getOikea())) + 1);
        k2.setKorkeus(max(korkeus(k2.getVasen()), korkeus(k2.getOikea())) + 1);
        return k2;
    }
    
    /**
     * Käännetään solmua k1 oikealle ja sen vasenta lasta vasemmalle.
     * k1 vasemmaksi lapseksi korvataan k2 tilalle käännetty solmu.
     * @param k1 käännettävä solmu
     * @return tilalle käännetty solmu
     */
    private Solmu vasenOikeaKaanto(Solmu k1) {
        Solmu k2 = k1.getVasen();
        k1.setVasen(vasenKaanto(k2));
        return oikeaKaanto(k1);
    }

    /**
     * Käännetään solmua k1 vasemmalle ja sen oikeaa lasta oikealle.
     * k1 oikeaksi lapseksi korvataan k2 tilalle käännetty solmu.
     * @param k1 käännettävä solmu
     * @return tilalle käännetty solmu
     */
    private Solmu oikeaVasenKaanto(Solmu k1) {
        Solmu k2 = k1.getOikea();
        k1.setOikea(oikeaKaanto(k2));
        return vasenKaanto(k1);
    }

    /**
     * Palauttaa kahdesta annetusta arvosta suuremman.
     *
     * @param x int arvo
     * @param y int arvo
     * @return palauttaa suuremman arvoista x ja y.
     * 
     */
    public int max(int x, int y) {
        return (x > y) ? x : y;
    }

    /**
     * Metodi palauttaa solmun korkeuden. Jos solmu on null, niin palauttaa -1
     * @param solmu, jonka korkeus halutaan tietää
     * @return palauttaa solmun korkeuden. Jos solmu on null, niin palauttaa -1.
     */
    public int korkeus(Solmu solmu) {
        if (solmu == null) {
            return -1;
        } else {
            return solmu.getKorkeus();
        }
    }
}