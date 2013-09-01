package tiralabra_maven;

/**
 * Binäärihakupuu toteutus käyttäen PuuRajapintaa
 *
 * @author esaaksvu
 */
public class BinaariHakupuu implements PuuRajapinta {

    Solmu juuri;

    /**
     * Lisää solmun puuhun
     *
     * @param uusi viite solmu olioon joka lisätään
     */
    @Override
    public void lisaaSolmu(Solmu uusi) {
        if (juuri == null) {
            juuri = uusi;
            return;
        }
        Solmu haku = juuri;
        Solmu vanhem = juuri;
        while (haku != null) {
            vanhem = haku;
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
    }

    /**
     * Poistaa solmun puusta
     *
     * @param poistet on viite solmuun joka halutaan poistaa
     */
    @Override
    public void poistaSolmu(Solmu poistet) {
        if (poistet == null) {
            return;
        }
        Solmu pVanhem = poistet.getVanhem();
        if (poistet.lapseton()) {
            if (pVanhem == null) {
                juuri = null;
                return;
            }
            if (poistet == pVanhem.getVasen()) {
                pVanhem.setVasen(null);
            } else {
                pVanhem.setOikea(null);
            }
            return;
        }
        if (poistet.getOikea() == null || poistet.getVasen() == null) {
            Solmu lapsi = (poistet.getVasen() != null)
                    ? poistet.getVasen() : poistet.getOikea();
            lapsi.setVanhem(pVanhem);
            if (pVanhem == null) {
                juuri = lapsi;
                return;
            }
            if (poistet == pVanhem.getVasen()) {
                pVanhem.setVasen(lapsi);
            } else {
                pVanhem.setOikea(lapsi);
            }
        }

        Solmu seur = min(poistet.getOikea());
        poistet.setArvo(seur.getArvo());
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

    }

    /**
     * Hakee puusta tiettyä arvoa ja palauttaa viitteen solmusta
     *
     * @param i solmun arvo jota haetaan
     * @return viite solmuun, jos löytyy. Muussa tapauksessa null.
     */
    @Override
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
        if (juuri == null) {
            return "null";
        }
        return juuri.toString();
    }

    /**
     * Hakee solmusta pienemmän solmun 
     * @param s Solmu josta aletaan etsimään pienintä
     * @return pienin solmu
     */
    public Solmu min(Solmu s) {
        while (s.getVasen() != null) {
            s = s.getVasen();
        }
        return s;
    }
}
