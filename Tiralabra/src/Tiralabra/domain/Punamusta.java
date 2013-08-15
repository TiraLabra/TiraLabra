package Tiralabra.domain;

/**
 * Toteuttaa vasemmalle nojaavan punamustan puun. Punamustapuu on itsestään tasapainottuva
 * binäärihakupuu, joka käyttää hyväksen solmujen värjäämistä. Tässä toteutuksessa insert/delete-operaatioit on mallinettu
 * seuraavasta osoitteesta löytyvää koodia mukaillen: https://gist.github.com/rkapsi/741080
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
        juuri = insert(juuri, key);
        juuri.setVari(false);
    }
    
    /** Toteuttaa solmun lisäämisen ja puun tasapainottamisen.
     * 
     * @param nyk puun vanha juuri
     * @param key uuden solmun sisältämä arvo
     * @return puun uusi juuri
     */
    private SolmuPunamusta insert(SolmuPunamusta nyk, int key) {
        if (nyk == null) {
            return new SolmuPunamusta(key, true);
        }
        
        if ((nyk.getOikea() != null && nyk.getVasen() != null) && (nyk.getOikea().getVari() && nyk.getVasen().getVari())) {
            vaihdaVarit(nyk);
        }
        
        if (key == nyk.getArvo()) {
            return nyk;
        }
        if (key < nyk.getArvo()) {
            nyk.getVasen().setVasen(insert(nyk.getVasen(), key));
        } else {
            nyk.getOikea().setOikea(insert(nyk.getOikea(), key));
        }
        
        //oikea lapsi on punainen, vasen lapsi joko musta tai null
        if ((nyk.getOikea() != null && nyk.getOikea().getVari() && nyk.getVasen() == null) || (nyk.getOikea() != null && nyk.getOikea().getVari() && nyk.getVasen() != null && !nyk.getVasen().getVari())) {
            nyk = vasenkierto(nyk);
        }
        
        //vasen lapsi on punainen ja vasemman vasen lapsi on punainen
        if (nyk.getVasen() != null && nyk.getVasen() != null && nyk.getVasen().getVari() && nyk.getVasen().getVasen().getVari()) {
            nyk = oikeakierto(nyk);
        }
        
        return nyk;
    }

    @Override
    public void delete(int key) {
        
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

    /**
     * Kiertää annettua solmua vasemmalle.
     *
     * @param s kierrettävä solmu
     * @return s:n paikalle liikkunut solmu
     */
    private SolmuPunamusta vasenkierto(SolmuPunamusta s) {
        SolmuPunamusta x = s.getOikea();
        s.setOikea(x.getVasen());
        x.setVasen(s);
        
        x.setParent(s.getParent());
        if (s.getParent() != null) {
            if (s.getParent().getOikea() == s) {
                s.getParent().setOikea(x);
            } else {
                s.getParent().setVasen(x);
            }
        } 
        s.setParent(x);
        
        x.setVari(s.getVari());
        s.setVari(true);
        return x;
    }

    /**
     * Kiertää annettua solmua oikealle.
     *
     * @param s kierrettävä solmu
     * @return s:n paikalle siirtynyt solmu
     */
    private SolmuPunamusta oikeakierto(SolmuPunamusta s) {
        SolmuPunamusta x = s.getVasen();
        s.setVasen(x.getOikea());
        x.setOikea(s);
        
        x.setParent(s.getParent());
        if (s.getParent() != null) {
            if (s.getParent().getOikea() == s) {
                s.getParent().setOikea(x);
            } else {
                s.getParent().setVasen(x);
            }
        } 
        s.setParent(x);
        
        x.setVari(s.getVari());
        s.setVari(true);
        return x;
    }

    /**
     * Palauttaa etsityn arvon sisältävän solmun, null jos ei löydy.
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

    /**
     * Palauttaa annetun solmun seuraajan.
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

    /**
     * Palauttaa solmun sisarsolmun,
     *
     * @param s solmu, jolle sisar haetaan
     * @return solmun s sisarsolmu
     */
    private SolmuPunamusta sisar(SolmuPunamusta s) {
        SolmuPunamusta sisar;
        if (s.getParent().getOikea() == s) {
            sisar = s.getParent().getVasen();
        } else {
            sisar = s.getParent().getOikea();
        }
        return sisar;
    }

    /** Vaihtaa annetun solmun ja sen lasten värit nykyistä vastakkaisiksi,
     * 
     * @param nyk solmu, jolle värinvaihto-operaatio suoritetaan
     */
    private void vaihdaVarit(SolmuPunamusta nyk) {
        nyk.setVari(!nyk.getVari());
        nyk.getVasen().setVari(!nyk.getVasen().getVari());
        nyk.getOikea().setVari(!nyk.getOikea().getVari());
    }

}
