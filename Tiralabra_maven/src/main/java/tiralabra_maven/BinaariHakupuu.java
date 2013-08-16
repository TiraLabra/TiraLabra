package tiralabra_maven;

/**
 * Binäärihakupuu toteutus käyttäen PuuRajapintaa
 *
 * @author esaaksvu
 */
public class BinaariHakupuu implements PuuRajapinta {

    Solmu juuri;

    private Solmu min(Solmu s) {
        while (s.getVasen() != null) {
            s = s.getVasen();
        }
        return s;
    }

    /**
     * Lisää solmun puuhun
     * @param uusi viite solmu olioon joka lisätään
     * @return Solmun vanhempi
     */
    public Solmu lisaaSolmu(Solmu uusi) {
        if (juuri == null) {
            juuri = uusi;
            return juuri;
        }
        Solmu haku = juuri;
        Solmu vanhem = juuri;
        while (haku != null) {
            vanhem = haku;
            if (uusi.getArvo() == haku.getArvo()) {
                return null;
            }
            if (uusi.getArvo() < haku.getArvo()) {
                haku = haku.getVasen();
            } else {
                haku = haku.getOikea();
            }
        }
        uusi.setVanhem(vanhem);
        if (uusi.getArvo() < vanhem.getArvo()) {
            vanhem.setVasen(uusi);
        } else {
            vanhem.setOikea(uusi);
        }
        return vanhem;
    }

    /**
     * Poistaa solmun puusta
     * @param i on solmun arvo joka poistetaan
     * @return true jos poisto onnistui
     */
    public boolean poistaSolmu(int i) {
        Solmu poistet = hae(i);
        if (poistet == null) {
            return false;
        }
        Solmu pVanhem = poistet.getVanhem();
        if (poistet.lapseton()) {
            if (pVanhem == null) {
                juuri = null;
                return true;
            }
            if (poistet == pVanhem.getVasen()) {
                pVanhem.setVasen(null);
            } else {
                pVanhem.setOikea(null);
            }
            return true;
        }
        if (poistet.getOikea() == null || poistet.getVasen() == null) {
            Solmu lapsi = (poistet.getVasen() != null)
                    ? poistet.getVasen() : poistet.getOikea();
            lapsi.setVanhem(pVanhem);
            if (pVanhem == null) {
                juuri = lapsi;
                return true;
            }
            if (poistet == pVanhem.getVasen()) {
                pVanhem.setVasen(lapsi);
            } else {
                pVanhem.setOikea(lapsi);
            }
            return true;
        }

        Solmu seur = min(poistet.getOikea());
        seur.setArvo(poistet.getArvo());
        Solmu lapsi = seur.getOikea();
        pVanhem = seur.getVanhem();
        if (pVanhem.getVasen() == seur) {
            pVanhem.setVasen(lapsi);
        } else {
            pVanhem.setOikea(lapsi);
        }
        if (lapsi != null) {
            lapsi.setVanhem(pVanhem);
        }
        return true;

    }

    /**
     * Hakee puusta tiettyä arvoa ja palauttaa viitteen solmusta
     * @param i solmun arvo jota haetaan
     * @return viite solmuun, jos löytyy. Muussa tapauksessa null.
     */
    public Solmu hae(int i) {
        Solmu haku = juuri;

        while (haku != null && haku.getArvo() != i) {
            haku = (i < haku.getArvo()) ? haku.getVasen() : haku.getOikea();
        }
        if (haku == null || (haku == juuri && haku.getArvo() != i)) {
            return null;
        }
        return haku;
    }

    /**
     * Palauttaa tulostuksen juuri{vasen lapsi{vasen lapsi, oikea lapsi}, oikea
     * lapsi}
     *
     * @return tulostus koko puusta
     */
    @Override
    public String toString() {
        return juuri.toString();
    }
}
