package tiralabra_maven;

//Toteutettu käyttäen lähteessä olevaa pseudokoodia: 
//http://www.pp.htv.fi/uvaisane/yo/tira/
/**
 * Punamustapuun totetutus
 *
 * @author esaaksvu
 */
public class PunMusPuu implements PuuRajapinta {

    Solmu juuri;
    Solmu nil;

    /**
     * Konstruktori luo uuden ilmentymän punamustapuusta ja asettaa nil solmulle
     * arvot
     */
    public PunMusPuu() {
        nil = new Solmu(-99);
        nil.setVari(true);
        nil.setVanhem(nil);
        nil.setOikea(nil);
        nil.setVasen(nil);
        juuri = nil;
    }

    /**
     * Lisää solmun punamusta puuhun
     *
     * @param uusi viite solmu olioon joka lisätään
     */
    @Override
    public void lisaaSolmu(Solmu uusi) {
        uusi.setOikea(nil);
        uusi.setVasen(nil);
        Solmu x = juuri;
        Solmu y = nil;

        while (x != nil) {
            y = x;
            x = (uusi.getArvo() < x.getArvo()) ? x.getVasen() : x.getOikea();
        }
        uusi.setVanhem(y);
        if (y == nil) {
            juuri = uusi;
        } else if (uusi.getArvo() < y.getArvo()) {
            y.setVasen(uusi);
        } else {
            y.setOikea(uusi);
        }
        uusi.setVari(false);
        korjaaPuuLisa(uusi);
    }

    /**
     * Poistaa solmun puusta
     *
     * @param pois poistettavan solmun viite
     */
    @Override
    public void poistaSolmu(Solmu pois) {
        if (pois == nil || pois == null) {
            return;
        }
        Solmu x;
        if (pois.getVasen() == nil || pois.getOikea() == nil) {
            x = (pois.getVasen() != nil) ? pois.getVasen() : pois.getOikea();
            Solmu w = pois.getVanhem();
            if (w == nil) {
                juuri = x;
            } else if (pois == w.getVasen()) {
                w.setVasen(x);
            } else {
                w.setOikea(x);
            }
            x.setVanhem(w);
            if (pois.getVari()) {
                korjaaPuuPois(x);
            }
            return;
        }
        Solmu y = succ(pois);
        x = y.getOikea();
        Solmu w = y.getVanhem();
        if (y == w.getVasen()) {
            w.setVasen(x);
        } else {
            w.setOikea(x);
        }
        x.setVanhem(w);
        pois.setArvo(y.getArvo());
        if (y.getVari()) {
            korjaaPuuPois(x);
        }
    }

    /**
     * hakee solmun puusta
     *
     * @param i haettavan solmun arvo
     * @return viite solmuun, null jos ei löydy
     */
    @Override
    public Solmu hae(int i) {
        Solmu haku = juuri;

        while (haku != nil && haku.getArvo() != i) {
            haku = (i < haku.getArvo()) ? haku.getVasen() : haku.getOikea();
        }
        if (haku == nil || (haku == juuri && haku.getArvo() != i)) {
            return nil;
        }
        return haku;
    }

    private void korjaaPuuLisa(Solmu uusi) {
        while (!uusi.getVanhem().getVari()) {
            if (uusi.getVanhem() == getIsoisa(uusi).getVasen()) {
                Solmu y = getIsoisa(uusi).getOikea();
                if (!y.getVari()) {
                    uusi.getVanhem().setVari(true);
                    y.setVari(true);
                    getIsoisa(uusi).setVari(false);
                    uusi = getIsoisa(uusi);
                } else if (uusi == uusi.getVanhem().getOikea()) {
                    uusi = uusi.getVanhem();
                    kaannaVasen(uusi);
                } else {
                    uusi.getVanhem().setVari(true);
                    getIsoisa(uusi).setVari(false);
                    kaannaOikea(getIsoisa(uusi));
                }
            } else {
                Solmu y = getIsoisa(uusi).getVasen();
                if (!y.getVari()) {
                    uusi.getVanhem().setVari(true);
                    y.setVari(true);
                    getIsoisa(uusi).setVari(false);
                    uusi = getIsoisa(uusi);
                } else if (uusi == uusi.getVanhem().getVasen()) {
                    uusi = uusi.getVanhem();
                    kaannaOikea(uusi);
                } else {
                    uusi.getVanhem().setVari(true);
                    getIsoisa(uusi).setVari(false);
                    kaannaVasen(getIsoisa(uusi));
                }
            }
        }
        juuri.setVari(true);
    }

    private void korjaaPuuPois(Solmu pois) {
        while (pois != juuri && pois.getVari()) {
            if (pois == pois.getVanhem().getVasen()) {
                Solmu sis = pois.getVanhem().getOikea(); //sisar
                if (!sis.getVari()) {
                    sis.setVari(true);
                    pois.getVanhem().setVari(false);
                    kaannaVasen(pois.getVanhem());
                    sis = pois.getVanhem().getOikea();
                }
                if (sis.getVasen().getVari() && sis.getOikea().getVari()) {
                    sis.setVari(false);
                    pois = pois.getVanhem();
                    continue;
                } else if (sis.getOikea().getVari()) {
                    sis.getVasen().setVari(true);
                    sis.setVari(false);
                    kaannaOikea(sis);
                    sis = pois.getVanhem().getOikea();
                }
                sis.setVari(pois.getVanhem().getVari());
                pois.getVanhem().setVari(true);
                sis.getOikea().setVari(true);
                kaannaVasen(pois.getVanhem());
                pois = juuri;
            } else if (pois == pois.getVanhem().getOikea()) {
                Solmu sis = pois.getVanhem().getVasen();
                if (!sis.getVari()) {
                    sis.setVari(true);
                    pois.getVanhem().setVari(false);
                    kaannaOikea(pois.getVanhem());
                    sis = pois.getVanhem().getVasen();
                }
                if (sis.getOikea().getVari() && sis.getVasen().getVari()) {
                    sis.setVari(false);
                    pois = pois.getVanhem();
                    continue;
                } else if (sis.getVasen().getVari()) {
                    sis.getOikea().setVari(true);
                    sis.setVari(false);
                    kaannaVasen(sis);
                    sis = pois.getVanhem().getVasen();
                }
                sis.setVari(pois.getVanhem().getVari());
                pois.getVanhem().setVari(true);
                sis.getVasen().setVari(true);
                kaannaOikea(pois.getVanhem());
                pois = juuri;
            }
        }
        pois.setVari(true);
    }

    private void kaannaVasen(Solmu x) {
        Solmu y = x.getOikea();
        x.setOikea(y.getVasen());
        if (y.getVasen() != nil) {
            y.getVasen().setVanhem(x);
        }
        Solmu w = x.getVanhem();
        y.setVanhem(w);
        if (w == nil) {
            juuri = y;
        } else if (w.getVasen() == x) {
            w.setVasen(y);
        } else {
            w.setOikea(y);
        }
        y.setVasen(x);
        x.setVanhem(y);
    }

    private void kaannaOikea(Solmu x) {
        Solmu y = x.getVasen();
        x.setVasen(y.getOikea());
        if (y.getOikea() != nil) {
            y.getOikea().setVanhem(x);
        }
        Solmu w = x.getVanhem();
        y.setVanhem(w);
        if (w == nil) {
            juuri = y;
        } else if (w.getVasen() == x) {
            w.setVasen(y);
        } else {
            w.setOikea(y);
        }
        y.setOikea(x);
        x.setVanhem(y);
    }

    private String tulosta(Solmu s) {
        if (s == nil) {
            return "";
        }
        String st = "";
        if (s != nil) {
            st += s.getArvo() + "(";
            if (s.getVari()) {
                st += "M";
            } else {
                st += "P";
            }
            st += "){";
            st += tulosta(s.getVasen()) + ",";
            st += tulosta(s.getOikea()) + "}";
        }
        return st;
    }

    private Solmu succ(Solmu s) {
        if (s.getOikea() != nil) {
            return min(s.getOikea());
        }
        Solmu y = s.getVanhem();
        while (y != nil && s == y.getOikea()) {
            s = y;
            y = s.getVanhem();
        }
        return y;
    }

    private Solmu min(Solmu s) {
        while (s.getVasen() != nil) {
            s = s.getVasen();
        }
        return s;
    }

    private Solmu getIsoisa(Solmu s) {
        return s.getVanhem().getVanhem();

    }

    /**
     * Palauttaa tulostuksen puusta
     * @return juuri(Väri){Vasen(väri),Oikea(väri)}
     */
    @Override
    public String toString() {
        return tulosta(juuri);
    }
}
