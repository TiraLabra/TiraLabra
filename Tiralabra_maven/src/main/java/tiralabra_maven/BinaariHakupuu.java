package tiralabra_maven;

/**
 * Binäärihakupuu toteutus käyttäen PuuRajapintaa
 *
 * @author esaaksvu
 */
public class BinaariHakupuu implements PuuRajapinta {

    private Solmu juuri;

    /**
     * Konstruktori joka luo uuden hakupuu olion
     */
    public BinaariHakupuu() {
    }

    /**
     * Metodi joka palauttaa puun päälimmäisen solmun
     *
     * @return päälimmäinen solmu
     */
    public Solmu getJuuri() {
        return juuri;
    }

    /**
     * Lisää uuden solmun puuhun, tasapainoalgoritmi puuttuu vielä
     *
     * @param uusi solmu joka lisätään puuhun
     */
    public void lisaaSolmu(Solmu uusi) {
        if (juuri == null) {
            juuri = uusi;
            return;
        }
        Solmu haku = juuri;
        Solmu isa = juuri;
        while (haku != null) {
            isa = haku;
            if (uusi.getArvo() == haku.getArvo()) {
                return;
            }
            if (uusi.getArvo() < haku.getArvo()) {
                haku = haku.getVasen();
            } else {
                haku = haku.getOikea();
            }
        }
        uusi.setIsa(isa);
        if (uusi.getArvo() < isa.getArvo()) {
            isa.setVasen(uusi);
        } else {
            isa.setOikea(uusi);
        }
  //      tasapainotaPuu(juuri);
    }

    /**
     * Poistaa solmun arvon perusteella
     *
     * @param i arvo joka poistetaan puusta
     * @return true jos poisto onnistui
     */
    public boolean poistaSolmu(int i) {
        return false;
    }

    /**
     * Palauttaa tulostuksen puusta muodossa 1 \ 1 3 / 2 mutta keskeneräinen
     *
     * @return tulostus koko puusta
     */
    public String tulostaPuu(Solmu s) {
        String t = "";
        if (s != null) {
            t += tulostaPuu(s.getVasen());
            t += s.getArvo() + ", ";
            t += tulostaPuu(s.getOikea());
        }
        return t;
    }


    public Solmu tasapainotaPuu(Solmu s) {
        if (s.getOikea() != null) {
            return min(s.getOikea());
        }
        Solmu y = s.getIsaSolmu();
        while (y != null && s == y.getOikea()) {
            s = y;
        }
        y = s.getIsaSolmu();
                
        return y;
    }

    private Solmu min(Solmu s) {
        while (s.getVasen() != null) {
            s = s.getVasen();
        }
        return s;
    }
    
}
