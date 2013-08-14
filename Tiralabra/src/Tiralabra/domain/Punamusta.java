package Tiralabra.domain;

/**
 * Toteuttaa punamustan puun. Punamustapuu on itsestään tasapainottuva
 * binäärihakupuu, joka käyttää hyväksen solmujen värjäämistä.
 *
 * @author Pia Pakarinen
 */
public class Punamusta implements Puu {

    /**
     * Puun juurisolmu.
     */
    private SolmuPunamusta juuri;

    /**
     * Luodaan uusi puu. Juurisolmuksi asetetaan musta, annetun arvon sisältävä
     * solmu.
     *
     * @param emo juurisolmun arvo
     */
    public Punamusta(int emo) {
        juuri = new SolmuPunamusta(emo, false);
    }

    @Override
    public String tulostaArvot() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insert(int key) {
        SolmuPunamusta uusi = new SolmuPunamusta(key, true);
        SolmuPunamusta iter = this.juuri;
        while (true) {
            if (key < iter.getArvo()) {
                if (iter.getVasen() == null) {
                    iter.setVasen(uusi);
                    uusi.setParent(iter);
                    break;
                }
                iter = iter.getVasen();
            } else {
                if (iter.getOikea() == null) {
                    iter.setOikea(uusi);
                    uusi.setParent(iter);
                    break;
                }
                iter = iter.getOikea();
            }
        }
        
        korjaapuu(uusi);
    }

    @Override
    public void delete(int key) {
        SolmuPunamusta s = etsiSolmu(key);
        if (s.getOikea() != null && s.getVasen() != null) {
            SolmuPunamusta seur = succ(s);
            s.setArvo(seur.getArvo());
            poista(seur);
        } else {
            poista(s);
        }
    }

    @Override
    public boolean search(int key) {
        if (etsiSolmu(key) == null) {
            return false;
        }
        return true;
    }

    /**
     * Palauttaa puun juurisolmun.
     *
     * @return puun juuri
     */
    public SolmuPunamusta getJuuri() {
        return juuri;
    }

    /** Tasapainottaa puun.
     * 
     * @param uusi solmu, johon on tullut muutos
     */
    private void korjaapuu(SolmuPunamusta uusi) {
        if (uusi.getParent() == null) {
            uusi.setVari(false);
            return;
        }
        if (!uusi.getParent().getVari()) {
            return;
        }
        
        if (uusi.getParent().getParent().getOikea() == uusi.getParent() && uusi.getParent().getParent().getVasen() != null && uusi.getParent().getParent().getVasen().getVari()) {
            uusi.getParent().setVari(false);
            uusi.getParent().getParent().setVari(true);
            uusi.getParent().getParent().getVasen().setVari(false);
            korjaapuu(uusi.getParent().getParent());
        } else if (uusi.getParent().getParent().getVasen() == uusi.getParent() && uusi.getParent().getParent().getOikea() != null && uusi.getParent().getParent().getOikea().getVari()) {
            uusi.getParent().setVari(false);
            uusi.getParent().getParent().setVari(true);
            uusi.getParent().getParent().getVasen().setVari(false);
            korjaapuu(uusi.getParent().getParent());
        }
        
        if (uusi == uusi.getParent().getOikea() && uusi.getParent() == uusi.getParent().getParent().getVasen()) {
            vasenkierto(uusi.getParent());
        } else if (uusi == uusi.getParent().getVasen() && uusi.getParent() == uusi.getParent().getParent().getOikea()) {
            oikeakierto(uusi.getParent());
        }
        
        uusi.getParent().setVari(false);
        uusi.getParent().getParent().setVari(true);
        if (uusi == uusi.getParent().getVasen()) {
            oikeakierto(uusi.getParent().getParent());
        } else{
            vasenkierto(uusi.getParent().getParent());
        }
        
    }

    /** Kiertää annettua solmua vasemmalle.
     * 
     * @param s kierrettävä solmu
     */
    private void vasenkierto(SolmuPunamusta s) {
        SolmuPunamusta apu = s.getOikea();
        s.setOikea(apu.getVasen());
        apu.setParent(s.getParent());
        s.setParent(apu);
        apu.setVasen(s);
    }
    
    /** Kiertää annettua solmua oikealle.
     * 
     * @param s kierrettävä solmu
     */
    private void oikeakierto(SolmuPunamusta s) {
        SolmuPunamusta apu = s.getVasen();
        s.setVasen(apu.getOikea());
        apu.setParent(s.getParent());
        s.setParent(apu);
        apu.setOikea(s);
    }

    /** Palauttaa etsityn arvon sisältävän solmun, null jos ei löydy.
     * 
     * @param key etsitty arvo
     * @return etsityn arvon sisältävä solmu tai null-viite
     */
    private SolmuPunamusta etsiSolmu(int key) {
        SolmuPunamusta kulkija = this.juuri;
        while (kulkija.getArvo() != key) {
            if (key < kulkija.getArvo()) {
                kulkija = kulkija.getVasen();
                if (kulkija == null) {
                    return null;
                }
            } else {
                kulkija = kulkija.getOikea();
                if (kulkija == null) {
                    return null;
                }
            }
        }
        return kulkija;
    }

    /** Palauttaa annetun solmun seuraajan.
     * 
     * @param s solmu, jolle seuraaja haetaan
     * @return solmun s seuraajasolmu
     */
    private SolmuPunamusta succ(SolmuPunamusta s) {
        SolmuPunamusta succ = s.getOikea();
        while (succ.getVasen() != null) {
            succ = succ.getVasen();
        }
        return succ;
    }

    /** Poistaa puusta solmun, jolla on korkeintaan yksi lapsisolmu.
     * 
     * @param pois poistettava solmu
     */
    private void poista(SolmuPunamusta pois) {
        //poistettava on punainen solmu, jolla ei lapsisolmuja
        if (pois.getVari()) {
            if (pois.getParent().getOikea() == pois) {
                pois.getParent().setOikea(null);
            } else {
                pois.getParent().setVasen(null);
            }
            return;
        }
        
        //poistettava on musta solmu, jolla yksi punainen lapsi
        if (!pois.getVari()) {
            if (pois.getOikea() != null && pois.getOikea().getVari()) {
                pois.getOikea().setVari(false);
            } else if (pois.getVasen() != null && pois.getVasen().getVari()) {
                pois.getVasen().setVari(false);
            }
        }
        
        //poistettava on musta solmu jolla mustat lapset
        poistoMaxYksiLapsi(pois);        
    }

    private void poistoMaxYksiLapsi(SolmuPunamusta s) {
        if (s == null) {
            return;
        }
        if (s.getParent() == null) {
            return;
        }
        //katsotaan, onko solmulla aitoa lasta vai pelkät null-viitteet
        SolmuPunamusta lapsi;
        if (s.getOikea() != null) {
            lapsi = s.getOikea();
        } else if (s.getVasen() != null) {
            lapsi = s.getVasen();
        } else {
            lapsi = null;
        }
        
        //laitetaan solmun lapsi solmun tilalle
        if (s.getParent().getOikea() == s) {
            s.getParent().setOikea(lapsi);
            if (lapsi != null) {
                lapsi.setParent(s.getParent());
            }
        } else {
            s.getParent().setVasen(lapsi);
            if (lapsi != null) {
                lapsi.setParent(s.getParent());
            }
        }
        
        
        
        
    }
    
    /** Palauttaa solmun sisarsolmun,
     * 
     * @param s solmu, jolle sisar haetaan
     * @return solmun s sisarsolmu
     */
    private SolmuPunamusta sisar(SolmuPunamusta s){
        SolmuPunamusta sisar;
        if (s.getParent().getOikea() == s) {
            sisar = s.getParent().getVasen();
        } else {
            sisar = s.getParent().getOikea();
        }
        return sisar;
    }
    
}
