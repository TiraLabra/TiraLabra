package Tiralabra.domain;

/**
 * Toteuttaa threaded-mallisen binääripuun. Puun lehtien normaalisti
 * null-arvoiset lapsi-pointterit osoittavat solmun seuraajaan (oikea) tai
 * edeltäjään (vasen) sisäjärjestyksessä.
 *
 * @author Pia Pakarinen
 */
public class Threaded implements Puu {

    private SolmuThreaded juuri;

    public Threaded(int emo) {
        juuri = new SolmuThreaded(emo);
    }

    @Override
    public String tulostaArvot() {
        String arvot = "";
        SolmuThreaded pointteri = juuri;
        while (pointteri.getVasen() != null) {
            pointteri = pointteri.getVasen();
        }
        
        while (pointteri != null) {
        arvot = arvot + pointteri.getKey() + "\n";
        pointteri = succ(pointteri);
        }
        
        return arvot;
    }

    @Override
    public void insert(int key) {
        SolmuThreaded parent = juuri;
        SolmuThreaded uusi = new SolmuThreaded(key);

        while (true) {
            if (key < parent.getKey()) {
                if (parent.getVasen() == null || !parent.vasenStatusGet()) {
                    parent.vasemmanLapsenStatusSet(true);
                    uusi.setOikea(parent);
                    uusi.oikeanLapsenStatusSet(false);
                    uusi.setVasen(parent.getVasen());
                    uusi.vasemmanLapsenStatusSet(false);
                    parent.setVasen(uusi);
                    uusi.setParent(parent);
                    break;
                } else {
                    parent = parent.getVasen();
                }
            } else {
                if (parent.getOikea() == null || !parent.oikeaStatusGet()) {
                    parent.oikeanLapsenStatusSet(true);
                    uusi.setVasen(parent);
                    uusi.vasemmanLapsenStatusSet(false);
                    uusi.setOikea(parent.getOikea());
                    uusi.oikeanLapsenStatusSet(false);
                    parent.setOikea(uusi);
                    uusi.setParent(parent);
                    break;
                } else {
                    parent = parent.getOikea();
                }
            }
        }
    }

    @Override
    public void delete(int key) {
        SolmuThreaded pois = searchThreaded(key);
        if (pois.oikeaStatusGet() && pois.vasenStatusGet()) {
            SolmuThreaded succ = succ(pois);
            pois.setKey(succ.getKey());
            poista(succ);
        } else if (pois.getParent() != null){
            poista(pois);
        }
    }

    @Override
    public Solmu search(int key) {
        return (Solmu) searchThreaded(key);
    }

    private SolmuThreaded succ(SolmuThreaded s) {
        if (!s.oikeaStatusGet()) {
            return s.getOikea();
        }
        s = s.getOikea();
        while (s.getVasen() != null && s.vasenStatusGet()) {
            s = s.getVasen();
        }
        return s;
    }

    private void poista(SolmuThreaded pois) {
        if (pois.getParent().getVasen().getKey() == pois.getKey()) {
            pois.getParent().setVasen(pois.getOikea());
        } else if (pois.getParent().getOikea().getKey() == pois.getKey()) {
            pois.getParent().setOikea(pois.getVasen());
        }
    }

    private SolmuThreaded searchThreaded(int key) {
        SolmuThreaded kulkija = this.juuri;
        while (kulkija.getKey() != key || kulkija != null) {
            if (key < kulkija.getKey() && kulkija.vasenStatusGet()) {
                kulkija = kulkija.getVasen();
            } else if (key > kulkija.getKey() && kulkija.oikeaStatusGet()) {
                kulkija = kulkija.getOikea();
            } else {
                return null;
            }
        }
        return kulkija;
    }
}
