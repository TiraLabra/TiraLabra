package com.mycompany.tiralabra_maven;

/**
 * Binäärihakupuu toteutus käyttäen PuuRajapintaa
 * @author esaaksvu
 */
public class BinaariHakupuu implements PuuRajapinta {

    private int syvyys;
    private Solmu juuri;

    /**
     * Konstruktori joka luo uuden hakupuu olion ja 
     * alustaa muuttujan syvyys nollaksi
     */
    public BinaariHakupuu() {
        syvyys = 0;
    }

    /**
     * Metodi joka palauttaa puun päälimmäisen solmun
     * @return päälimmäinen solmu
     */
    public Solmu getJuuri() {
        return juuri;
    }

    /**
     * palauttaa puun syvyyden
     * @return puun syvyys
     */
    public int getSyvyys() {
        return syvyys;
    }

    /**
     * Lisää uuden solmun puuhun, tasapainoalgoritmi puuttuu vielä
     * @param uusi solmu joka lisätään puuhun
     */
    public void lisaaSolmu(Solmu uusi) {
        if (uusi == null) {
            return;
        }
        if (syvyys == 0) {
            juuri = uusi;
        } else {
            Solmu haku = juuri;
            while (true) {
                if (uusi.getArvo() == haku.getArvo()) {
                    return;
                }
                if (uusi.getArvo() < haku.getArvo()) {
                    if (haku.getVasen() == null) {
                        haku.setVasen(uusi);
                        break;
                    } else {
                        haku = haku.getVasen();
                    }
                } else if (uusi.getArvo() > haku.getArvo()) {
                    if (haku.getOikea() == null) {
                        haku.setOikea(uusi);
                        break;
                    } else {
                        haku = haku.getOikea();
                    }
                }
            }
        }
        syvyys++;
    }

    /**
     * Poistaa solmun arvon perusteella
     * @param i arvo joka poistetaan puusta
     * @return true jos poisto onnistui
     */
    public boolean poistaSolmu(int i) {
        if (syvyys == 0) {
            return false;
        } else {
            syvyys--;
            return true;
        }
    }

    /**
     * Palauttaa tulostuksen puusta muodossa 
     *   1
     *  /\
     * 2  3 mutta keskeneräinen
     * @return tulostus koko puusta
     */
    public String tulostaPuu() {
      return juuri.toString();
    }
    
    
}
