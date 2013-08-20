package Tiralabra.domain;

/**
 * Toteuttaa 2-3 hakupuun. 2-3 hakupuusa solmulla voi olla 1-2 arvoa, ja 0-3
 * lasta.
 *
 * @author Pia Pakarinen
 */
public class B implements Puu {

    /**
     * Puun juurisolmu.
     */
    private SolmuB juuri;

    /**
     * Luo uuden 2-3 B-puun ja tälle juurisolmun annetulla arvolla.
     *
     * @param emo juurisolmun arvo
     */
    public B(int emo) {
        juuri = new SolmuB(emo, null);
    }
    
    @Override
    public String tulostaArvot() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void insert(int key) {
        insert2(key, this.juuri);
    }
    
    @Override
    public void delete(int key) {
        delete2(key, this.juuri);
    }
    
    @Override
    public boolean search(int key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Etsii rekursiivisesti solmulle paikan puusta. Uusi solmu lisätään solmuun
     * jos siinä on tilaa, muuten se muodostaa uuden lehden.
     *
     * @param key Uuden solmun arvo.
     * @param s Uuden solmun vanhempi tai paikka.
     */
    private void insert2(int key, SolmuB s) {
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
                    s.setKeski(new SolmuB(key, s));
                    return;
                } else {
                    insert2(key, s.getKeski());
                }
            } else if (key > s.getToinenArvo()) {
                //solmusta tulee uusi lehti oikealle
                if (s.getOikea() == null) {
                    s.setOikea(new SolmuB(key, s));
                    return;
                } else {
                    insert2(key, s.getOikea());
                }
            } else {
                if (s.getVasen() == null) {
                    s.setVasen(new SolmuB(key, s));
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
    private SolmuB succSolmu1(SolmuB s) {
        //seuraaja ei tarpeen
        if (s.getKeski() == null) {
            return null;
        }
        
        SolmuB it = s.getKeski();
        
        while (it.getVasen() != null) {
            it = it.getVasen();
        }
        
        return it;
    }

    /**
     * Palauttaa seuraajasolmun annetulle solmulle, null jos seuraaja ei ole
     * tarpeen (oikea alipuu ei olemassa).
     *
     * @param s solmu, jolle seuraaja haetaan
     * @return solmun s seuraaja
     */
    private SolmuB succSolmu2(SolmuB s) {
        //seuraaja ei tarpeen
        if (s.getOikea() == null) {
            return null;
        }
        
        SolmuB it = s.getOikea();
        
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
    private void delete2(int key, SolmuB s) {
        SolmuB seur;
        while (s != null) {
            if (s.getEnsimmainenArvo() == key) {
                seur = succSolmu1(s);
                if (seur == null) {
                    if (s.solmunKoko() == 2) {
                        s.setVasen(s.getKeski());
                        s.setKeski(null);
                        s.poistaArvo(key);
                        break;
                    } else {
                        poistaHelppo(key, s);
                        break;
                    }
                } else {
                    s.lisaaArvo(seur.getEnsimmainenArvo());
                    poistaHelppo(seur.getEnsimmainenArvo(), seur);
                    break;
                }
            } else if (s.solmunKoko() == 2) {
                if (s.getToinenArvo() == key) {
                    seur = succSolmu2(s);
                    //tilanne, jossa kaksiarvoisella solmulla ei oikeaa lasta
                    if (seur == null) {
                        s.poistaArvo(key);
                        s.setOikea(s.getKeski());
                        s.setKeski(null);
                        s.poistaArvo(key);
                        break;
                    } //muuten korvataan seuraajalla ja poistetaan tästä arvo
                    else {
                        s.poistaArvo(key);
                        s.lisaaArvo(seur.getEnsimmainenArvo());
                        poistaHelppo(seur.getEnsimmainenArvo(), seur);
                        break;
                    }
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
    private void jaaLapsetVasemmalta(SolmuB s) {
        SolmuB uusivasen = null;
        SolmuB uusikeski = null;
        int v;
        while (s.getVasen() != null) {
            v = s.getVasen().getEnsimmainenArvo();
            if (v > s.getEnsimmainenArvo()) {
                if (uusikeski == null) {
                    uusikeski = new SolmuB(v, s);
                } else {
                    insert2(v, uusikeski);
                }
                delete2(v, s.getVasen());
            } else if (v < s.getEnsimmainenArvo()) {
                if (uusivasen == null) {
                    uusivasen = new SolmuB(v, s);
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
    private void jaaLapsetOikealta(SolmuB s) {
        SolmuB uusioikea = null;
        SolmuB uusikeski = null;
        int v;
        while (s.getOikea() != null) {
            v = s.getOikea().getEnsimmainenArvo();
            if (v < s.getToinenArvo()) {
                if (uusikeski == null) {
                    uusikeski = new SolmuB(v, s);
                } else {
                    insert2(v, uusikeski);
                }
                delete2(v, s.getOikea());
            } else if (v < s.getToinenArvo()) {
                if (uusioikea == null) {
                    uusioikea = new SolmuB(v, s);
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
     * Poistaa puusta arvon, jonka solmulla ei ole vasenta lasta
     *
     * @param arvo poistettava arvo
     * @param s solmu, josta arvo poistuu
     */
    private void poistaHelppo(int arvo, SolmuB s) {

        //lehtisolmu
        if (s.getKeski() == null && s.getOikea() == null && s.getVasen() == null) {
            if (s.solmunKoko() == 2) {
                s.poistaArvo(arvo);
            } else {
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
            s.getParent().setVasen(s.getOikea());
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
    public SolmuB getJuuri() {
        return juuri;
    }
}
