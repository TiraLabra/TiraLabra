package Tiralabra.domain;

import Tiralabra.util.ALista;

/**
 * Toteuttaa vasemmalle nojaavan punamustan puun. Punamustapuu on itsestään
 * tasapainottuva binäärihakupuu, joka käyttää hyväksen solmujen värjäämistä.
 * Tässä toteutuksessa insert/delete-operaatioit on mallinettu seuraavasta
 * osoitteesta löytyvää koodia mukaillen: https://gist.github.com/rkapsi/741080
 *
 * @author Pia Pakarinen
 */
public class Punamusta implements Puu {

    /**
     * Puun juurisolmu.
     */
    private SolmuPunamusta juuri;

    /**
     * Luodaan uusi puu. Juurisolmuksi asetetaan musta, annetun arvon sisältävä
     * solmu.
     *
     * @param emo juurisolmun arvo
     */
    public Punamusta(int emo) {
        juuri = new SolmuPunamusta(emo, false);
    }

    @Override
    public String tulostaArvot() {
        return sisa(new ALista(), juuri).toString();
    }

    @Override
    public void insert(int key) {
        juuri = insert(juuri, key);
        juuri.setVari(false);
    }

    /**
     * Toteuttaa solmun lisäämisen ja puun tasapainottamisen.
     *
     * @param nyk käsittelyssä oleva solmu
     * @param key uuden solmun sisältämä arvo
     * @return käsittelyyn siirtyvä solmu; lopussa puun uusi juuri
     */
    private SolmuPunamusta insert(SolmuPunamusta nyk, int key) {
        if (nyk == null) {
            return new SolmuPunamusta(key, true);
        }

        if (punainen(nyk.getVasen()) && punainen(nyk.getOikea())) {
            vaihdaVarit(nyk);
        }

        if (key < nyk.getArvo()) {
            nyk.setVasen(insert(nyk.getVasen(), key));
        } else if (key > nyk.getArvo()) {
            nyk.setOikea(insert(nyk.getOikea(), key));
        }

        //oikea lapsi on punainen, vasen lapsi joko musta tai null
        if (punainen(nyk.getOikea()) && !punainen(nyk.getVasen())) {
            nyk = vasenkierto(nyk);
        }

        //vasen lapsi on punainen ja vasemman vasen lapsi on punainen
        if (punainen(nyk.getVasen()) && punainen(nyk.getVasen().getVasen())) {
            nyk = oikeakierto(nyk);
        }

        return nyk;
    }

    @Override
    public void delete(int key) {
        if (search(key)) {
            juuri = delete(juuri, key);
            if (juuri == null) {
                return;
            }
            juuri.setVari(false);
        }
    }

    @Override
    public boolean search(int key) {
        return etsiSolmu(key) != null;
    }

    /**
     * Palauttaa puun juurisolmun.
     *
     * @return puun juuri
     */
    public SolmuPunamusta getJuuri() {
        return juuri;
    }

    /**
     * Kiertää annettua solmua vasemmalle.
     *
     * @param s kierrettävä solmu
     * @return s:n paikalle liikkunut solmu
     */
    private SolmuPunamusta vasenkierto(SolmuPunamusta s) {
        SolmuPunamusta x = s.getOikea();
        s.setOikea(x.getVasen());
        x.setVasen(s);
        x.setVari(s.getVari());
        s.setVari(true);
        return x;
    }

    /**
     * Kiertää annettua solmua oikealle.
     *
     * @param s kierrettävä solmu
     * @return s:n paikalle siirtynyt solmu
     */
    private SolmuPunamusta oikeakierto(SolmuPunamusta s) {
        SolmuPunamusta x = s.getVasen();
        s.setVasen(x.getOikea());
        x.setOikea(s);

        x.setVari(s.getVari());
        s.setVari(true);
        return x;
    }

    /**
     * Palauttaa etsityn arvon sisältävän solmun, null jos ei löydy.
     *
     * @param key etsitty arvo
     * @return etsityn arvon sisältävä solmu tai null-viite
     */
    private SolmuPunamusta etsiSolmu(int key) {
        SolmuPunamusta kulkija = this.juuri;
        while (kulkija.getArvo() != key) {
            if (key < kulkija.getArvo()) {
                kulkija = kulkija.getVasen();
                if (kulkija == null) {
                    return null;
                }
            } else {
                kulkija = kulkija.getOikea();
                if (kulkija == null) {
                    return null;
                }
            }
        }
        return kulkija;
    }

    /**
     * Palauttaa annetun solmun seuraajan.
     *
     * @param s solmu, jolle seuraaja haetaan
     * @return solmun s seuraajasolmu
     */
    private SolmuPunamusta succ(SolmuPunamusta s) {
        SolmuPunamusta succ = s.getOikea();
        while (succ.getVasen() != null) {
            succ = succ.getVasen();
        }
        return succ;
    }

    /**
     * Vaihtaa annetun solmun ja sen lasten värit nykyistä vastakkaisiksi,
     *
     * @param nyk solmu, jolle värinvaihto-operaatio suoritetaan
     */
    private void vaihdaVarit(SolmuPunamusta nyk) {
        nyk.setVari(!nyk.getVari());
        nyk.getVasen().setVari(!nyk.getVasen().getVari());
        nyk.getOikea().setVari(!nyk.getOikea().getVari());
    }

    /**
     * Poistaa puusta key-arvon sisältävän solmun ja tasapainottaa puun samalla.
     *
     * @param nyk käsittelyssä oleva solmu
     * @param key poistettavan solmun arvo
     * @return käsittelyyn siirtyvä solmu, lopussa puun uusi juuri
     */
    private SolmuPunamusta delete(SolmuPunamusta nyk, int key) {
        if (key < nyk.getArvo()) {
            if (!punainen(nyk.getVasen()) && !punainen(nyk.getVasen().getVasen())) {
                nyk = siirraPunVas(nyk);
            }
            nyk.setVasen(delete(nyk.getVasen(), key));
        } else {
            if (punainen(nyk.getVasen())) {
                nyk = oikeakierto(nyk);
            }

            if (key == nyk.getArvo() && nyk.getOikea() == null) {
                return null;
            }

            if (!punainen(nyk.getOikea()) && !punainen(nyk.getOikea().getVasen())) {
                nyk = siirraPunOik(nyk);
            }

            if (key == nyk.getArvo()) {
                nyk.setArvo(succ(nyk).getArvo());
                nyk.setOikea(deleteSeur(nyk));
            } else {
                nyk.setOikea(delete(nyk.getOikea(), key));
            }
        }

        return korjaa(nyk);
    }

    /**
     * Suorittaa kiertoja ja värinvaihdoksia, joita tarvitaan puun
     * tasoittamisessa solmuja poistettaessa.
     *
     * @param nyk solmu, jolle operaatiot suoritetaan
     * @return solmu, josta käsittely jatkuu muualla
     */
    private SolmuPunamusta siirraPunVas(SolmuPunamusta nyk) {
        vaihdaVarit(nyk);
        if (punainen(nyk.getOikea().getVasen())) {
            nyk.getOikea().setOikea(oikeakierto(nyk.getOikea()));
            nyk = vasenkierto(nyk);
            vaihdaVarit(nyk);
        }
        return nyk;
    }

    /**
     * Suorittaa kiertoja ja värinvaihdoksia, joita tarvitaan puun
     * tasoittamisessa solmuja poistettaessa.
     *
     * @param nyk solmu, jolle operaatiot suoritetaan
     * @return solmu, josta käsittely jatkuu muualla
     */
    private SolmuPunamusta siirraPunOik(SolmuPunamusta nyk) {
        vaihdaVarit(nyk);
        if (punainen(nyk.getVasen().getVasen())) {
            nyk = oikeakierto(nyk);
            vaihdaVarit(nyk);
        }
        return nyk;
    }

    /**
     * Poistaa puusta annetun solmun seuraajan ja tasapainottaa.
     *
     * @param nyk käsiteltävissä oleva solmu
     * @return lopussa alkuperäisen solmun uusi oikea lapsi
     */
    private SolmuPunamusta deleteSeur(SolmuPunamusta nyk) {
        if (nyk.getVasen() == null) {
            return null;
        }

        if (!punainen(nyk.getVasen()) && !punainen(nyk.getVasen().getVasen())) {
            nyk = siirraPunVas(nyk);
        }

        nyk.setVasen(deleteSeur(nyk.getVasen()));
        return korjaa(nyk);
    }

    /**
     * Suorittaa tarvittavia kiertoja ja värivaihdoksia puun ominaisuuksien
     * säilyttämiseksi.
     *
     * @param nyk käsittelyssä oleva solmu
     * @return solmu käsittelyn jälkeen
     */
    private SolmuPunamusta korjaa(SolmuPunamusta nyk) {
        if (punainen(nyk.getOikea())) {
            nyk = vasenkierto(nyk);
        }

        if (nyk.getVasen() != null && punainen(nyk.getVasen()) && punainen(nyk.getVasen().getVasen())) {
            nyk = oikeakierto(nyk);
        }

        if (punainen(nyk.getVasen()) && punainen(nyk.getOikea())) {
            vaihdaVarit(nyk);
        }
        return nyk;
    }

    /**
     * Tarkistaa solmun värin; null solmut ovat mustia.
     *
     * @param s tarkistettava solmu
     * @return true jos solmu on punainen, false jos musta tai null
     */
    private boolean punainen(SolmuPunamusta s) {
        return s != null && s.getVari();
    }
    /**
     * Käy puun läpi sisäjärjestyksessä.
     *
     * @return automaattisesti järjestetty linkitetty-lista esitys puun solmuista
     */
    private ALista sisa(ALista l, SolmuPunamusta s) {
        if (s == null) {
            return l;
        }
        l.lisaa(s.getArvo());
        sisa(l, s.getVasen());
        sisa(l, s.getOikea());
        return l;
      }
}
