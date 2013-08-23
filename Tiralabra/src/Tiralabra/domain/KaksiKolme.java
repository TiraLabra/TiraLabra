package Tiralabra.domain;

import Tiralabra.util.ALista;

/**
 * Toteuttaa 2-3 hakupuun. 2-3 hakupuusa solmulla voi olla 1-2 arvoa, ja 0-3
 * lasta. Pahin tapaus haulle, lisäämiselle, tulostamiselle ja poistolle O(n),
 * koska puu ei ole itsestään tasapainottuva.
 *
 * @author Pia Pakarinen
 */
public class KaksiKolme implements Puu {

    /**
     * Puun juurisolmu.
     */
    private SolmuKaksiKolme juuri;

    /**
     * Luo uuden 2-3 KaksiKolme-puun ja tälle juurisolmun annetulla arvolla.
     *
     * @param emo juurisolmun arvo
     */
    public KaksiKolme(int emo) {
        juuri = new SolmuKaksiKolme(emo, null);
    }

    /**
     * Luo uuden tyhjän puun.
     */
    public KaksiKolme() {
    }

    @Override
    public String tulostaArvot() {
        return sisa(new ALista(), juuri).toString();
    }

    @Override
    public void insert(int key) {
        if (juuri == null) {
            this.juuri = new SolmuKaksiKolme(key, null);
        } else {
            insert2(key, this.juuri);
        }
    }

    @Override
    public void delete(int key) {
        if (this.juuri != null) {
            delete2(key, this.juuri);;
        }
    }

    @Override
    public boolean search(int key) {
        SolmuKaksiKolme i = juuri;
        while (i != null) {
            if (key == i.getEnsimmainenArvo() || (i.solmunKoko() == 2 && key == i.getToinenArvo())) {
                break;
            } else if (key < i.getEnsimmainenArvo()) {
                i = i.getVasen();
            } else if (key > i.getEnsimmainenArvo()) {
                if (i.solmunKoko() == 2) {
                    if (key < i.getToinenArvo()) {
                        i = i.getKeski();
                    } else {
                        i = i.getOikea();
                    }
                } else {
                    i = i.getOikea();
                }
            }
        }

        return i != null;
    }

    /**
     * Etsii rekursiivisesti solmulle paikan puusta. Uusi solmu lisätään solmuun
     * jos siinä on tilaa, muuten se muodostaa uuden lehden.
     *
     * @param key Uuden solmun arvo.
     * @param s Uuden solmun vanhempi tai paikka.
     */
    private void insert2(int key, SolmuKaksiKolme s) {
        if (s.getEnsimmainenArvo() == key) {
            return;
        }
        //solmu on jo täynnä
        if (s.solmunKoko() == 2) {
            if (s.getToinenArvo() == key) {
                return;
            }
            if (key < s.getToinenArvo() && key > s.getEnsimmainenArvo()) {
                //solmulle luodaan uusi lehti s:n keskimmäisenä lapsena
                if (s.getKeski() == null) {
                    s.setKeski(new SolmuKaksiKolme(key, s));
                    return;
                } else {
                    insert2(key, s.getKeski());
                }
            } else if (key > s.getToinenArvo()) {
                //solmusta tulee uusi lehti oikealle
                if (s.getOikea() == null) {
                    s.setOikea(new SolmuKaksiKolme(key, s));
                    return;
                } else {
                    insert2(key, s.getOikea());
                }
            } else {
                if (s.getVasen() == null) {
                    s.setVasen(new SolmuKaksiKolme(key, s));
                    return;
                } else {
                    insert2(key, s.getVasen());
                }
            }
        }

        //solmuun mahtuu
        if (s.solmunKoko() < 2) {
            s.lisaaArvo(key);
            //kaksoisarvoiselle solmulle täytyy lapsi-puut jakaa uudelleen
            if (key == s.getEnsimmainenArvo() && s.getVasen() != null) {
                jaaLapsetVasemmalta(s);
            } else if (s.getOikea() != null) {
                jaaLapsetOikealta(s);
            }
        }
    }

    /**
     * Palauttaa ensimmäisen arvon seuraajasolmun, null jos ei ole tarpeen
     * (keskimmäistä lasta ei ole).
     *
     * @param s solmu, jolle seuraaja haetaan
     * @return solmun s seuraaja
     */
    private SolmuKaksiKolme succSolmu1(SolmuKaksiKolme s) {
        //seuraaja ei tarpeen
        if (s.getKeski() == null) {
            return null;
        }

        SolmuKaksiKolme it = s.getKeski();

        while (it.getVasen() != null) {
            it = it.getVasen();
        }

        return it;
    }

    /**
     * Palauttaa seuraajan arvon annetulle arvo (suurempi arvo solmussa), null
     * jos seuraaja ei ole tarpeen (oikea alipuu ei olemassa).
     *
     * @param s solmu, jolle seuraaja haetaan
     * @return solmun s seuraaja
     */
    private SolmuKaksiKolme succSolmu2(SolmuKaksiKolme s) {
        //seuraaja ei tarpeen
        if (s.getOikea() == null) {
            return null;
        }

        SolmuKaksiKolme it = s.getOikea();

        while (it.getVasen() != null) {
            it = it.getVasen();
        }
        return it;
    }

    /**
     * Poistaa annetun arvon lähtien annetusta solmusta.
     *
     * @param key poistettava arvo
     * @param s käsiteltävissä oleva solmu
     */
    private void delete2(int key, SolmuKaksiKolme s) {
        SolmuKaksiKolme seur;
        while (s != null) {
            if (s.getEnsimmainenArvo() == key) {
                seur = succSolmu1(s);
                if (seur == null) {
                    poistaHelppo(key, s);
                    break;
                } else {
                    s.poistaArvo(key);
                    s.lisaaArvo(seur.getEnsimmainenArvo());
                    poistaHelppo(seur.getEnsimmainenArvo(), seur);
                    break;
                }
            } else if (s.solmunKoko() == 2 && s.getToinenArvo() == key) {
                seur = succSolmu2(s);
                if (seur == null) {
                    poistaHelppo(key, s);
                    break;
                } //korvataan seuraajalla ja poistetaan tästä arvo
                else {
                    s.poistaArvo(key);
                    s.lisaaArvo(seur.getEnsimmainenArvo());
                    poistaHelppo(seur.getEnsimmainenArvo(), seur);
                    break;

                }
            }
            //arvoa ei vielä löytynyt, jatketaan matkaa
            if (key > s.getEnsimmainenArvo()) {
                if (s.solmunKoko() == 2 && key < s.getToinenArvo()) {
                    s = s.getKeski();
                } else {
                    s = s.getOikea();
                }
            } else {
                s = s.getVasen();
            }
        }
    }

    /**
     * Luo uudet alipuut (vasen ja keskimmäinen) uudelle kaksiarvoiselle
     * solmulle kun uusi lisätty solmu on entistä pienempi.
     *
     * @param s solmu, jolle uudet alipuut luodaan
     */
    private void jaaLapsetVasemmalta(SolmuKaksiKolme s) {
        SolmuKaksiKolme uusivasen = null;
        SolmuKaksiKolme uusikeski = null;
        int v;
        while (s.getVasen() != null) {
            v = s.getVasen().getEnsimmainenArvo();
            if (v > s.getEnsimmainenArvo()) {
                if (uusikeski == null) {
                    uusikeski = new SolmuKaksiKolme(v, s);
                } else {
                    insert2(v, uusikeski);
                }
                delete2(v, s.getVasen());
            } else if (v < s.getEnsimmainenArvo()) {
                if (uusivasen == null) {
                    uusivasen = new SolmuKaksiKolme(v, s);
                } else {
                    insert2(v, uusivasen);
                }
                delete2(v, s.getVasen());
            }
        }
        s.setKeski(uusikeski);
        s.setVasen(uusivasen);
    }

    /**
     * Luo uudet alipuut (oikea ja keskimmäinen) uudelle kaksiarvoiselle
     * solmulle kun uusi lisätty solmu on entistä suurempi.
     *
     * @param s solmu, jolle uudet alipuut luodaan
     */
    private void jaaLapsetOikealta(SolmuKaksiKolme s) {
        SolmuKaksiKolme uusioikea = null;
        SolmuKaksiKolme uusikeski = null;
        int v;
        while (s.getOikea() != null) {
            v = s.getOikea().getEnsimmainenArvo();
            if (v < s.getToinenArvo()) {
                if (uusikeski == null) {
                    uusikeski = new SolmuKaksiKolme(v, s);
                } else {
                    insert2(v, uusikeski);
                }
                delete2(v, s.getOikea());
            } else if (v < s.getToinenArvo()) {
                if (uusioikea == null) {
                    uusioikea = new SolmuKaksiKolme(v, s);
                } else {
                    insert2(v, uusioikea);
                }
                delete2(v, s.getOikea());
            }
        }
        s.setKeski(uusikeski);
        s.setOikea(uusioikea);
    }

    /**
     * Poistaa puusta arvon, jolla ei ole enää
     *
     * @param arvo poistettava arvo
     * @param s solmu, josta arvo poistuu
     */
    private void poistaHelppo(int arvo, SolmuKaksiKolme s) {

        //lehtisolmu
        if (s.getKeski() == null && s.getOikea() == null && s.getVasen() == null) {
            if (s.solmunKoko() == 2) {
                s.poistaArvo(arvo);
            } else {
                if (s.getParent() == null) {
                    this.juuri = null;
                    return;
                }
                if (s.getParent().getKeski() == s) {
                    s.getParent().setKeski(null);
                } else if (s.getParent().getOikea() == s) {
                    s.getParent().setOikea(null);
                } else {
                    s.getParent().setVasen(null);
                }
            }
            return;
        }



        //solmussa on vain yksi arvo; koska vasenta lasta ei voi enää löytyä,
        //eikä yksiarvoisella voi olla keskimmäistä lasta, siirretään mahdollinen
        //oikea lapsi pykälä ylöspäin
        if (s.solmunKoko() == 1) {
            if (s.getParent() == null) {
                s.getOikea().setParent(null);
                this.juuri = s.getOikea();
                return;
            }
            if (s == s.getParent().getVasen()) {
                s.getParent().setVasen(s.getOikea());
            } else if (s == s.getParent().getOikea()) {
                s.getParent().setOikea(s.getOikea());
            } else {
                s.getParent().setKeski(s.getOikea());
            }
        } //solmussa on kaksi arvoa, ei vasenta lasta; riippuen poistettavan arvon paikasta, keskimmäisestä
        //lapsesta tulee joko uusi oikea tai vasen lapsi
        else {
            if (arvo == s.getEnsimmainenArvo()) {
                s.setVasen(s.getKeski());
                s.setKeski(null);
            } else {
                s.setOikea(s.getKeski());
                s.setKeski(null);
            }
        }
        s.poistaArvo(arvo);
    }

    /**
     * Palauttaa juurisolmun.
     *
     * @return puun juurisolmu
     */
    public SolmuKaksiKolme getJuuri() {
        return juuri;
    }

    /**
     * Käy puun läpi sisäjärjestyksessä.
     *
     * @return automaattisesti järjestetty linkitetty-lista esitys puun
     * solmuista
     */
    private ALista sisa(ALista l, SolmuKaksiKolme s) {
        if (s == null) {
            return l;
        }
        l.lisaa(s.getEnsimmainenArvo());
        if (s.solmunKoko() == 2) {
            l.lisaa(s.getToinenArvo());
        }
        sisa(l, s.getKeski());
        sisa(l, s.getVasen());
        sisa(l, s.getOikea());
        return l;
    }
}