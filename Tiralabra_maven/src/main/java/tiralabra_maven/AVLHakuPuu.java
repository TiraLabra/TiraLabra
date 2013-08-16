package tiralabra_maven;

/**
 * AVLPuu toteutus käyttäen perinnöllisyyttä ja rajapintaa
 * @author esaaksvu
 */
public class AVLHakuPuu extends BinaariHakupuu implements PuuRajapinta {

    private int tutkiTasapaino(Solmu p) {
        if (korkeus(p.getOikea()) == korkeus(p.getVasen()) + 2) {
            if (korkeus(p.getOikea().getOikea()) > korkeus(p.getOikea().getVasen())) {
                return 1;
            } else {
                return 2;
            }
        }
        if (korkeus(p.getVasen()) == korkeus(p.getOikea()) + 2) {
            if (korkeus(p.getVasen().getVasen()) > korkeus(p.getVasen().getOikea())) {
                return 3;
            } else {
                return 4;
            }
        }
        return -1;
    }

    /**
     * Lisaa solmun puuhun
     * @param uusi viite solmu olioon joka lisätään
     * @return palauttaa viitteen lisätyn solmun vanhempaan
     */
    @Override
    public Solmu lisaaSolmu(Solmu uusi) {
       uusi.setKorkeus(1);
       uusi = super.lisaaSolmu(uusi);
        Solmu p = null;
        if (uusi != null) {
            p = uusi.getVanhem();
        }
        while (p != null) {
            if (tutkiTasapaino(p) != -1) {
                Solmu alipuu = null;
                Solmu vanhem = p.getVanhem();
                switch (tutkiTasapaino(p)) {
                    case 1: {
                        alipuu = kaannaVasen(p);
                        break;
                    }
                    case 2: {
                        alipuu = kaannaOikeaVasen(p);
                        break;
                    }
                    case 3: {
                        alipuu = kaannaOikea(p);
                        break;
                    }
                    case 4: {
                        alipuu = kaannaVasenOikea(p);
                        break;
                    }
                }
                if (vanhem == null) {
                    juuri = alipuu;
                } else if (vanhem.getVasen() == p) {
                    vanhem.setVasen(alipuu);
                } else {
                    vanhem.setOikea(alipuu);
                }
                if (vanhem != null) {
                    vanhem.setKorkeus(max(korkeus(vanhem.getVasen()),
                            korkeus(vanhem.getOikea())) + 1);
                }
                return vanhem;
            } else {
                p = p.getVanhem();
            }
        }
        return null;
    }

    private Solmu kaannaVasen(Solmu p) {
        Solmu k2 = p.getOikea();
        k2.setVanhem(p.getVanhem());
        p.setVanhem(k2);
        p.setOikea(k2.getVasen());
        k2.setVasen(p);
        if (p.getOikea() != null) {
            p.getOikea().setVanhem(p);
        }
        p.setKorkeus(max(korkeus(p.getVasen()), korkeus(p.getOikea())) + 1);
        k2.setKorkeus(max(korkeus(k2.getVasen()), korkeus(k2.getOikea())) + 1);
        return k2;
    }

    private Solmu kaannaOikea(Solmu p) {
        Solmu k2 = p.getVasen();
        k2.setVanhem(p.getVanhem());
        p.setVanhem(k2);
        p.setVasen(k2.getOikea());
        k2.setOikea(p);
        if (p.getVasen() != null) {
            p.getVasen().setVanhem(p);
        }
        p.setKorkeus(max(korkeus(p.getVasen()), korkeus(p.getOikea())) + 1);
        k2.setKorkeus(max(korkeus(k2.getVasen()), korkeus(k2.getOikea())) + 1);
        return k2;
    }

    private Solmu kaannaOikeaVasen(Solmu p) {
        Solmu k2 = p.getOikea();
        p.setOikea(kaannaOikea(k2));
        return kaannaVasen(p);
    }

    private Solmu kaannaVasenOikea(Solmu p) {
        Solmu k2 = p.getVasen();
        p.setVasen(kaannaVasen(k2));
        return kaannaOikea(p);
    }

    /**
     * Poistaa solmun puusta
     * @param arvo poistettavan solmun arvo
     * @return true jos poisto onnistuu
     */
    @Override
    public boolean poistaSolmu(int arvo) {
        return false;
    }

    /**
     * Palauttaa suuremman kahdesta luvusta
     * @param i ensimmäinen luku
     * @param j toinen luku
     * @return suurempi kahdesta
     */
    public int max(int i, int j) {
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
