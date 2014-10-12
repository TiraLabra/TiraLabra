package wad.hakupuut;

import wad.solmu.Solmu;

/**
 * Toteutettu
 * https://noppa.oulu.fi/noppa/kurssi/811312a/luennot/811312A_opiskelumateriaali_-_perustietorakenteet.pdf
 * lähteen pseudokoodin mukaisesti.
 *
 * Puna-mustapuu on itseääntasapainoittava binäärinenhakupuu. Puun solmuihin
 * liittyy tieto solmujen väristä.
 *
 * Puulle pätee: 1. Solmut ovat punaisia tai mustia. 2. Juurisolmu on musta. 3.
 * Jokainen lehti on musta ja null. 4. Punaisella solmulla on kaksi mustaa
 * lasta. 5. Kaikille solmuille pätee, jokainen polku solmusta sen
 * jälkeläislehtiin sisältää saman verran mustia solmuja.
 */
public class PunaMustaPuu extends BinaarinenHakupuu {
    /**
     * Lisaa metodi lisää puuhun solmun. Metodi hyödyntää BinaarinenHakupuu lisaa metodia.
     * Metodi päättelee värin lisätylle solmulle tai ohjaa puun korjattavaksi.
     * 
     * @param lisattava data, jota halutaan lisätä puuhun
     * @return palauttaa lisätyn solmun tai null.
     */
    @Override
    public Solmu lisaa(Object lisattava) {
        Solmu uusi = super.lisaa(lisattava);
        if(uusi.getVanhempi() == null) {
            uusi.setMusta();
        }
        else {
            uusi.setPunainen();
            if(uusi.getVanhempi().getVanhempi() != null) {
                lisaysKorjaus(uusi);
            }
        } return uusi;
    }
    
    /**
     * Poistaa puusta dataa ja selvitää onko solmujen värit oikein.
     * Mikäli värit eivät noudata puna-mustapuun ehtoja, ohjataan puu korjattavaksi.
     * 
     * @param poistettava data, joka halutaan poistaa puusta
     * @return poistettu solmu.
     */
    @Override
    public Solmu poista(Object poistettava) {
        Solmu poistettu = super.hae(poistettava);
        if( poistettu == null) return null;
        
        if(poistettu.getVasen() != null && poistettu.getOikea() != null) {
            Solmu apu = alipuunMin(poistettu.getOikea());
            Object tmp = poistettu.getArvo();
            poistettu.setArvo(apu.getArvo());
            apu.setArvo(tmp);
            poistettu = apu;
        }
        poistaYksiLapsi(poistettu);
        return poistettu;
    }
    
    private void poistaYksiLapsi(Solmu x) {
        Solmu lapsi = x.getOikea() == null ? x.getVasen() : x.getOikea();
        Solmu vanhempi = x.getVanhempi();
        korvaa(x, lapsi);
        if(x.onMusta()) {
            if(lapsi != null && !lapsi.onMusta()) {
                lapsi.setMusta();
            } else {
                poistoTapaus1(lapsi, vanhempi);
            }
        }
    }
    
    private void poistoTapaus1(Solmu s, Solmu vanhempi) {
        if(vanhempi != null) {
            poistoTapaus2(s, vanhempi);
        }
    }
    
    private void poistoTapaus2(Solmu s, Solmu vanhempi) {
        Solmu sisar = getSisar(s, vanhempi);
        if(!sisar.onMusta()) {
            vanhempi.setPunainen();
            sisar.setMusta();
            if(s == vanhempi.getVasen()) { 
                vasenKaanto(vanhempi);
            } else {
                oikeaKaanto(vanhempi);
            }  
        }
        poistoTapaus3(s, vanhempi);
    }
    
    private void poistoTapaus3(Solmu s, Solmu vanhempi) {
        Solmu sisar = getSisar(s, vanhempi);
        if((vanhempi.onMusta())
            && (sisar.onMusta())
            && ( sisar.getVasen() == null || sisar.getVasen().onMusta())
            && ( sisar.getOikea() == null || sisar.getOikea().onMusta())) {
            sisar.setPunainen();
            poistoTapaus1(vanhempi, vanhempi.getVanhempi());
        } else {
            poistoTapaus4(s, vanhempi);
        }
    }
    
    private void poistoTapaus4(Solmu s, Solmu vanhempi) {
        Solmu sisar = getSisar(s, vanhempi);
        if((!vanhempi.onMusta())
            && (sisar.onMusta())
            && ( sisar.getVasen() == null || sisar.getVasen().onMusta())
            && ( sisar.getOikea() == null || sisar.getOikea().onMusta())) {
            sisar.setPunainen();
            vanhempi.setMusta();
        } else {
            poistoTapaus5(s, vanhempi);
        }
    }
    
    private void poistoTapaus5(Solmu s, Solmu vanhempi) {
        Solmu sisar = getSisar(s, vanhempi);
        if(sisar != null && sisar.onMusta()) {
            if((s == vanhempi.getVasen())
                    && (sisar.getOikea().onMusta())
                    && (!sisar.getVasen().onMusta())) {
               sisar.setPunainen();
               sisar.getVasen().setMusta();
               oikeaKaanto(sisar);
            } else if((s == vanhempi.getOikea())
                    && (sisar.getVasen().onMusta())
                    && (!sisar.getOikea().onMusta())) {
               sisar.setPunainen();
               sisar.getOikea().setMusta();
               vasenKaanto(sisar); 
            }
        }
        poistoTapaus6(s, vanhempi);
    }
    
    private void poistoTapaus6(Solmu s, Solmu vanhempi) {
        Solmu sisar = getSisar(s, vanhempi);
        if(vanhempi.onMusta()) sisar.setMusta();
        else sisar.setPunainen();
        vanhempi.setMusta();
        
        if(s == vanhempi.getVasen()) {
            sisar.getOikea().setMusta();
            vasenKaanto(vanhempi);
        } else {
            sisar.getVasen().setMusta();
            oikeaKaanto(vanhempi);
        }
    }

    /**
     * Puuta kierretään vasemmalle solmun x suhteen.
     *
     * @param x kierrettävä solmu.
     */
    private void vasenKaanto(Solmu x) {
        Solmu y = x.getOikea();
        x.setOikea(y.getVasen());
        if (y.getVasen() != null) {
            y.getVasen().setVanhempi(x);
        }
        y.setVanhempi(x.getVanhempi());
        if (x.getVanhempi() == null) {
            juuri = y;
        } else if (x.getVanhempi().getVasen() == x) {
            x.getVanhempi().setVasen(y);
        } else {
            x.getVanhempi().setOikea(y);
        }
        y.setVasen(x);
        x.setVanhempi(y); 
    }

    /**
     * Puuta kierretään oikealle solmun x suhteen.
     *
     * @param x kierrettävä solmu.
     */
    private void oikeaKaanto(Solmu x) {
        Solmu y = x.getVasen();
        x.setVasen(y.getOikea());
        if (y.getOikea() != null) {
            y.getOikea().setVanhempi(x);
        }
        y.setVanhempi(x.getVanhempi());
        if (x.getVanhempi() == null) {
            juuri = y;
        } else if (x.getVanhempi().getVasen() == x) {
            x.getVanhempi().setVasen(y);
        } else {
            x.getVanhempi().setOikea(y);
        }
        y.setOikea(x);
        x.setVanhempi(y);
    }

    /**
     * Korvaa solmun u solmulla v
     * @param u korvattava solmu
     * @param v korvaava solmu
     */
    public void korvaa(Solmu u, Solmu v) {
        if(u.getVanhempi() == null) {
            juuri = v;
        } else if(u == u.getVanhempi().getVasen()) {
            u.getVanhempi().setVasen(v);
        } else {
            u.getVanhempi().setOikea(v);
        }
        if(v != null) {
            v.setVanhempi(u.getVanhempi());
        }
    }
    
    /**
     * Korjaa solmujen värit solmun x lisäyksen jälkeen.
     *
     * @param x lisätty solmu
     */
    private void lisaysKorjaus(Solmu x) {
        while (x.getVanhempi() != null && !x.getVanhempi().onMusta()) {
            if (x.getVanhempi() == x.getVanhempi().getVanhempi().getVasen()) {
                    Solmu y = x.getVanhempi().getVanhempi().getOikea();
                    if (y != null && !y.onMusta()) {
                        x.getVanhempi().setMusta();
                        y.setMusta();
                        x.getVanhempi().getVanhempi().setPunainen();
                        x = x.getVanhempi().getVanhempi();
                    } else if(x == x.getVanhempi().getOikea()) {
                        x = x.getVanhempi();
                        vasenKaanto(x);
                    } else {
                        x.getVanhempi().setMusta();
                        x.getVanhempi().getVanhempi().setPunainen();
                        oikeaKaanto(x.getVanhempi().getVanhempi());
                    }
                }
            
            else {
                    Solmu y = x.getVanhempi().getVanhempi().getVasen();
                    if (y != null && !y.onMusta()) {
                        x.getVanhempi().setMusta();
                        y.setMusta();
                        x.getVanhempi().getVanhempi().setPunainen();
                        x = x.getVanhempi().getVanhempi();
                    } else if(x == x.getVanhempi().getVasen()) {
                        x = x.getVanhempi();
                        oikeaKaanto(x);
                    } else {
                        x.getVanhempi().setMusta();
                        x.getVanhempi().getVanhempi().setPunainen();
                        vasenKaanto(x.getVanhempi().getVanhempi());
                    }
                
            }
            this.juuri.setMusta();
        }
    }
    
    /**
     * Palauttaa solmun s sisaren. Mikäli vanhempi ei ole s:n vanhempi, niin palautetaan null. 
     * @param s kysytty solmu
     * @param vanhempi solmun vanhempi
     * @return palautetaan s:n sisar tai null
     */
    public Solmu getSisar(Solmu s, Solmu vanhempi) {
        if(s == vanhempi.getVasen())
            return vanhempi.getOikea();
        else if(s == vanhempi.getOikea())
            return vanhempi.getVasen();
        else
            return null;
    }
}
