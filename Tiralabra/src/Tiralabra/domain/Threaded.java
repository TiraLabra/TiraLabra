package Tiralabra.domain;

/**
 * Toteuttaa threaded-mallisen binääripuun. Puun lehtien normaalisti
 * null-arvoiset lapsi-pointterit osoittavat solmun seuraajaan (oikea) tai
 * edeltäjään (vasen) sisäjärjestyksessä.
 *
 * @author Pia Pakarinen
 */
public class Threaded implements Puu {

    /**
     * Puun juurisolmu.
     *
     */
    private SolmuThreaded juuri;

    /**
     * Luo uuden puun ja sen juurisolmun annetulla arvolla.
     *
     * @param emo juurisolmun arvo.
     */
    public Threaded(int emo) {
        juuri = new SolmuThreaded(emo);
    }

    /**
     * Luo uuden tyhjän puun.
     */
    public Threaded() {
    }

    @Override
    public String tulostaArvot() {
        String arvot = "";
        SolmuThreaded pointteri = juuri;
        if (pointteri == null) {
            return "";
        }
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
        if(juuri == null){
            juuri = new SolmuThreaded(key);
            return;
        }
        
        SolmuThreaded parent = juuri;
        SolmuThreaded uusi = new SolmuThreaded(key);

        while (true) {
            if (key == parent.getKey()) {
                break;
            }
            if (key < parent.getKey()) {
                //nykyisen solmun vasemmalla on paikka
                if (!parent.vasenStatusGet()) {
                    parent.vasemmanLapsenStatusSet(true);
                    //uuden solmun seuraaja on sen uusi vanhempi
                    uusi.setOikea(parent);
                    uusi.oikeanLapsenStatusSet(false);
                    //uuden solmun edeltäjä on vanhemman entinen edeltäjä
                    uusi.setVasen(parent.getVasen());
                    uusi.vasemmanLapsenStatusSet(false);
                    parent.setVasen(uusi);
                    uusi.setParent(parent);
                    break;
                } else {
                    parent = parent.getVasen();
                }
            } else {
                if (!parent.oikeaStatusGet()) {
                    //nykyisen solmun oikealla on paikka
                    parent.oikeanLapsenStatusSet(true);
                    //uuden solmun edeltäjä on sen uusi vanhempi
                    uusi.setVasen(parent);
                    uusi.vasemmanLapsenStatusSet(false);
                    //uuden solmun seuraaja on vanhemman entinen seuraaja
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
        
        if (pois == null) {
            return;
        }

        //solmu on juuri jolla yksi lapsi 
        if (pois.getParent() == null && (!pois.oikeaStatusGet() || !pois.vasenStatusGet())) {
            if (pois.oikeaStatusGet()) {
                pois.getOikea().setParent(null);
                this.juuri = pois.getOikea();
            } else {
                pois.getVasen().setParent(null);
                this.juuri = pois.getVasen();
            }
        }

        //solmulla on kaksi lasta: korvataan solmun arvo seuraajan arvolla, poistetaan seuraaja
        if (pois.oikeaStatusGet() && pois.vasenStatusGet()) {
            SolmuThreaded succ = succ(pois);
            pois.setKey(succ.getKey());

            //seuraaja on juuri, aloitetaan alusta!
            if (succ.getParent() == null) {
                this.delete(succ.getKey());

            } else {
                poista(succ);
            }
            //solmulla on yksi lapsi, eikä solmu ole juuri
        } else if (pois.getParent() != null) {
            poista(pois);
        }
    }

    @Override
    public boolean search(int key) {
        if (searchThreaded(key) == null) {
            return false;
        }
        return true;
    }

    /**
     * Antaa annetun solmun seuraajan, null jos seuraajaa ei ole.
     *
     * @param s solmu, jonka seuraaja haetaan
     * @return solmun s seuraaja tai null-arvo
     */
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

    /**
     * Poistaa solmun 'pois' puusta. Solmun 'pois' parent-solmu saa uudeksi
     * lapsekseen poistettavan solmun asianmukaisen lapsen, viitteet seuraajiin/edeltäjiin korjautuvat.
     *
     * @param pois poistettava solmu
     */
    private void poista(SolmuThreaded pois) {

        //poistettava solmu on vanhempansa vasen lapsi
        if (pois.getParent().getVasen() != null && pois.getParent().getVasen().getKey() == pois.getKey()) {

            //poistettava on lapseton
            if (!pois.oikeaStatusGet() && !pois.vasenStatusGet()) {
                pois.getParent().setVasen(pois.getVasen());
                pois.getParent().vasemmanLapsenStatusSet(false);
            } 
            
            //poistettavalla on yksi lapsi
            else {
                if (pois.oikeaStatusGet()) {
                    pois.getOikea().setParent(pois.getParent());
                    //poistettavan lapsi osoittaa takaisin poistettavaan
                    if (pois.getOikea().getVasen() == pois) {
                        pois.getOikea().setVasen(pois.getParent());
                    }
                    pois.getParent().setVasen(pois.getOikea());
                } else {
                    pois.getVasen().setParent(pois.getParent());
                    if (pois.getVasen().getOikea() == pois) {
                        pois.getVasen().setOikea(pois.getParent());
                    }
                    pois.getParent().setVasen(pois.getVasen());
                }
            }


        } 
        
        //poistettava on vanhempansa oikea lapsi, edellisen kanssa symmetrinen
        else if (pois.getParent().getOikea() != null && pois.getParent().getOikea().getKey() == pois.getKey()) {
            if (!pois.oikeaStatusGet() && !pois.vasenStatusGet()) {
                pois.getParent().setOikea(pois.getOikea());
                pois.getParent().oikeanLapsenStatusSet(false);
            } else {
                if (pois.vasenStatusGet()) {
                    pois.getVasen().setParent(pois.getParent());
                    if (pois.getVasen().getOikea() == pois) {
                        pois.getVasen().setOikea(pois.getParent());
                    }
                    pois.getParent().setOikea(pois.getVasen());
                } else {
                    pois.getOikea().setParent(pois.getParent());
                    if (pois.getOikea().getVasen() == pois) {
                        pois.getOikea().setVasen(pois.getParent());
                    }
                    pois.getParent().setOikea(pois.getOikea());
                }
            }


        }
    }

    /**
     * Hake puusta arvoa vastaavan solmun.
     *
     * @param key haettava arvo
     * @return null jos arvoa ei löydy, muuten arvon sisältävä solmu
     */
    private SolmuThreaded searchThreaded(int key) {
        SolmuThreaded kulkija = this.juuri;
        if (kulkija == null) {
            return null;
        }
        while (kulkija.getKey() != key) {
            if (key < kulkija.getKey() && kulkija.vasenStatusGet()) {
                kulkija = kulkija.getVasen();
            } else if (key > kulkija.getKey() && kulkija.oikeaStatusGet()) {
                kulkija = kulkija.getOikea();
            } else {
                return null;
            }
            if (kulkija == null) {
                return null;
            }
        }

        return kulkija;
    }

    /**
     * Palauttaa puun juurisolmun.
     * Metodia käytetään puun testauksessa.
     * @return juurisolmu
     */
    public SolmuThreaded getJuuri() {
        return juuri;
    }
}