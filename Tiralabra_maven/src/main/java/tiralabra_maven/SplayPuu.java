package tiralabra_maven;

/**
 * Splaypuun toteutus
 *
 * @author esaaksvu
 */
public class SplayPuu extends BinaariHakupuu {

    /**
     * Hakee puista solmun arvon perusteella
     *
     * @param i on solmun arvo jolla haetaan
     * @return viite solmuun jos löytyy, null jos ei
     */
    @Override
    public void lisaaSolmu(Solmu uusi) {
        super.lisaaSolmu(uusi);
        splay(uusi);
    }

    /**
     * Poistaa solmun viitteen perusteella
     *
     * @param pois on solmun viite
     * @return true jos poisto onnistuu
     */
    @Override
    public boolean poistaSolmu(Solmu pois) {
        if (pois == null) {
            return false;
        }
        splay(pois);
        if (pois.getVasen() == null) {
            vaihda(pois, pois.getOikea());
        } else if (pois.getOikea() == null) {
            vaihda(pois, pois.getVasen());
        } else {
            Solmu y = min(pois.getOikea());
            if (y.getVanhem() != pois) {
                vaihda(y, y.getOikea());
                y.setOikea(pois.getOikea());
                y.getOikea().setVanhem(y);
            }
            vaihda(pois, y);
            y.setVasen(pois.getVasen());
            y.getVasen().setVanhem(y);
        }
        return true;
    }

    /**
     * Hakee puista solmun arvon perusteella
     *
     * @param i on solmun arvo jolla haetaan
     * @return viite solmuun jos löytyy, null jos ei
     */
    @Override
    public Solmu hae(int i) {
        Solmu haku = super.hae(i);
        if (haku != null) {
            splay(haku);
        }
        return haku;
    }

    private void splay(Solmu s) {
        while (s.getVanhem() != null) {
            Solmu p = s.getVanhem();
            Solmu gp = s.getVanhem().getVanhem();
            if (gp == null) {
                if (s == p.getVasen()) {
                    kaannaOikea(p);
                } else {
                    kaannaVasen(p);
                }
            } else if (p.getVasen() == s && gp.getVasen() == p) {
                kaannaOikea(gp);
                kaannaOikea(p);
            } else if (p.getOikea() == s && gp.getOikea() == p) {
                kaannaVasen(gp);
                kaannaVasen(p);
            } else if (p.getVasen() == s && gp.getOikea() == p) {
                kaannaOikea(p);
                kaannaVasen(p);
            } else {
                kaannaVasen(p);
                kaannaOikea(p);
            }

        }
    }

    private void kaannaOikea(Solmu x) {
        if (x.getVasen() == null) {
            return;
        }
        Solmu y = x.getVasen();
        x.setVasen(y.getOikea());
        if (y.getOikea() != null) {
            y.getOikea().setVanhem(x);
        }
        y.setVanhem(x.getVanhem());
        if (x.getVanhem() == null) {
            juuri = y;
        } else if (x == x.getVanhem().getVasen()) {
            x.getVanhem().setVasen(y);
        } else {
            x.getVanhem().setOikea(y);
        }
        y.setOikea(x);
        x.setVanhem(y);
    }

    private void kaannaVasen(Solmu x) {
        if (x.getOikea() == null) {
            return;
        }
        Solmu y = x.getOikea();
        if (y == null) {
            System.out.println(x);
        }
        x.setOikea(y.getVasen());
        if (y.getVasen() != null) {
            y.getVasen().setVanhem(x);
        }
        y.setVanhem(x.getVanhem());
        if (x.getVanhem() == null) {
            juuri = y;
        } else if (x == x.getVanhem().getVasen()) {
            x.getVanhem().setVasen(y);
        } else {
            x.getVanhem().setOikea(y);
        }
        y.setVasen(x);
        x.setVanhem(y);
    }

    private void vaihda(Solmu x, Solmu y) {
        if (x.getVanhem() == null) {
            juuri = y;
        } else if (x == x.getVanhem().getVasen()) {
            x.getVanhem().setVasen(y);
        } else {
            x.getOikea().setOikea(y);
        }
        if (y != null) {
            y.setVanhem(x.getVanhem());
        }
    }
}
