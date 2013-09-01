package tiralabra_maven;

/**
 * AVLPuu toteutus käyttäen perinnöllisyyttä
 *
 * @author esaaksvu
 */
public class AVLHakuPuu extends BinaariHakupuu {

    /**
     * Lisää solmun puuhun
     *
     * @param uusi viite solmu olioon joka lisätään
     */
    @Override
    public void lisaaSolmu(Solmu uusi) {
        uusi.setKorkeus(0);
        super.lisaaSolmu(uusi);
        Solmu p = null;
        if (uusi.getVanhem() != null) {
            p = uusi.getVanhem();
        }
        while (p != null) {
            if (epaTasapainossa(p)) {
                korjaaPuu(p, p.getVanhem(), kierraAlipuu(p));
            }
            p.setKorkeus(max(korkeus(p.getVasen()), korkeus(p.getOikea())) + 1);
            p = p.getVanhem();
        }
    }

    /**
     * Poistaa solmun puusta
     *
     * @param pois poistettavan solmun viite
     */
    @Override
    public void poistaSolmu(Solmu pois) {
        if (pois == null) {
            return;
        }
        Solmu v = pois.getVanhem();
        super.poistaSolmu(pois);
        pois = v;
        Solmu p = null;
        if (pois != null) {
            p = pois.getVanhem();
        }
        while (p != null) {
            if (epaTasapainossa(p)) {
                Solmu vanhem = p.getVanhem();
                korjaaPuu(p, vanhem, kierraAlipuu(p));
                p = vanhem;
            } else {
                p.setKorkeus(max(korkeus(p.getVasen()), korkeus(p.getOikea())) + 1);
                p = p.getVanhem();
            }
        }
    }

    private boolean epaTasapainossa(Solmu p) {
        return (korkeus(p.getOikea()) == korkeus(p.getVasen()) + 2)
                || (korkeus(p.getVasen()) == korkeus(p.getOikea()) + 2);

    }

    private Solmu kaannaVasen(Solmu k1) {
        if (k1.getOikea()==null) return null;
        Solmu k2 = k1.getOikea();
        k2.setVanhem(k1.getVanhem());
        k1.setVanhem(k2);
        k1.setOikea(k2.getVasen());
        k2.setVasen(k1);
        if (k1.getOikea() != null) {
            k1.getOikea().setVanhem(k1);
        }
        k1.setKorkeus(max(korkeus(k1.getVasen()), korkeus(k1.getOikea())) + 1);
        k2.setKorkeus(max(korkeus(k2.getVasen()), korkeus(k2.getOikea())) + 1);
        return k2;
    }

    private Solmu kaannaOikea(Solmu k1) {
        if (k1.getVasen()==null) return null;
        Solmu k2 = k1.getVasen();
        k2.setVanhem(k1.getVanhem());
        k1.setVanhem(k2);
        k1.setVasen(k2.getOikea());
        k2.setOikea(k1);
        if (k1.getVasen() != null) {
            k1.getVasen().setVanhem(k1);
        }
        k1.setKorkeus(max(korkeus(k1.getVasen()), korkeus(k1.getOikea())) + 1);
        k2.setKorkeus(max(korkeus(k2.getVasen()), korkeus(k2.getOikea())) + 1);
        return k2;
    }

    private Solmu kaannaVasenOikea(Solmu k1) {
        Solmu k2 = k1.getVasen();
        k1.setVasen(kaannaVasen(k2));
        return kaannaOikea(k1);
    }

    private Solmu kaannaOikeaVasen(Solmu k1) {
        Solmu k2 = k1.getOikea();
        k1.setOikea(kaannaOikea(k2));
        return kaannaVasen(k1);
    }

    private Solmu kierraAlipuu(Solmu p) {
        if (korkeus(p.getOikea()) == korkeus(p.getVasen()) + 2) {
            if (korkeus(p.getOikea().getOikea()) > korkeus(p.getOikea().getVasen())) {
                return kaannaVasen(p);
            } else {
                return kaannaOikeaVasen(p);
            }
        }
        if (korkeus(p.getVasen()) == korkeus(p.getOikea()) + 2) {
            if (korkeus(p.getVasen().getVasen()) > korkeus(p.getVasen().getOikea())) {
                return kaannaOikea(p);
            } else {
                return kaannaVasenOikea(p);
            }
        }
        return null;
    }

    private void korjaaPuu(Solmu p, Solmu vanhem, Solmu alipuu) {
        if (vanhem == null) {
            juuri = alipuu;
        } else if (vanhem.getVasen() == p) {
            vanhem.setVasen(alipuu);
        } else {
            vanhem.setOikea(alipuu);
        }
        if (vanhem != null) {
            vanhem.setKorkeus(max(korkeus(vanhem.getVasen()), korkeus(vanhem.getOikea())) + 1);
        }
    }

    private int max(int i, int j) {
        int z = (i > j) ? i : j;
        return z;
    }

    private int korkeus(Solmu s) {
        if (s == null) {
            return -1;
        } else {
            return s.getKorkeus();
        }

    }
}