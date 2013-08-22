package tiralabra_maven;

//Toteutettu käyttäen lähteessä olevaa pseudokoodia: 
//http://www.pp.htv.fi/uvaisane/yo/tira/
/**
 * Punamustapuun totetutus 
 * @author esaaksvu
 */
public class PunMusPuu extends BinaariHakupuu implements PuuRajapinta {

    Solmu nil;

    /**
     * Konstruktori luo uuden ilmentymän punamustapuusta ja asettaa nil solmulle
     * arvot
     */
    public PunMusPuu() {
        nil = new Solmu(0);
        nil.setVari(true);
        nil.setVanhem(nil);
        nil.setOikea(nil);
        nil.setVasen(nil);
        juuri = nil;
    }

     /**
     * Lisää solmun puuhun (Kaipaa refaktorointia)
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
            if (uusi.getArvo() == x.getArvo()) {
                return;
            }
            if (uusi.getArvo() < x.getArvo()) {
                x = x.getVasen();
            } else {
                x = x.getOikea();
            }
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
     * Poistaa solmun puusta (keskeneräinen/sisältää virheitä)
     *
     * @param i on solmun arvo joka poistetaan
     * @return true jos poisto onnistui
     */
    @Override
    public boolean poistaSolmu(int i) {
        Solmu pois = super.hae(i);
        if (pois == null) {
            return false;
        }
        Solmu x;
        if (pois.getVasen() == nil || pois.getOikea() == nil) {
            if (pois.getVasen() != nil) {
                x = pois.getVasen();
            } else {
                x = pois.getOikea();
            }
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
            return true;
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
        return true;
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

    private void korjaaPuuPois(Solmu x) {
        while (x != juuri && x.getVari()) {
            if (x == x.getVanhem().getVasen()) {
                Solmu w = x.getVanhem().getOikea();
                if (!w.getVari()) {
                    w.setVari(true);
                    x.getVanhem().setVari(false);
                    kaannaVasen(x.getVanhem());
                    w = x.getVanhem().getOikea();
                }
                if (w.getVasen().getVari() && w.getOikea().getVari()) {
                    w.setVari(false);
                    x = x.getVanhem();
                } else if (w.getOikea().getVari()) {
                    w.getVasen().setVari(true);
                    w.setVari(false);
                    kaannaOikea(w);
                    w = x.getVanhem().getOikea();
                }
                w.setVari(x.getVanhem().getVari());
                x.getVanhem().setVari(true);
                w.getOikea().setVari(true);
                kaannaVasen(x.getVanhem());
                x = juuri;
            } else if (x == x.getVanhem().getOikea()) {
                Solmu w = x.getVanhem().getVasen();
                if (!w.getVari()) {
                    w.setVari(true);
                    x.getVanhem().setVari(false);
                    kaannaOikea(x.getVanhem());
                    w = x.getVanhem().getVasen();
                }
                if (w.getOikea().getVari() && w.getVasen().getVari()) {
                    w.setVari(false);
                    x = x.getVanhem();
                } else if (w.getVasen().getVari()) {
                    w.getOikea().setVari(true);
                    w.setVari(false);
                    kaannaVasen(w);
                    w = x.getVanhem().getVasen();
                }
                w.setVari(x.getVanhem().getVari());
                x.getVanhem().setVari(true);
                w.getVasen().setVari(true);
                kaannaOikea(x.getVanhem());
                x = juuri;
            }
        }
        x.setVari(true);
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
     */
    @Override
    public String toString() {
        return tulosta(juuri);
    }
}
