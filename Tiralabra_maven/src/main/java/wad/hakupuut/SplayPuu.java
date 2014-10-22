package wad.hakupuut;

import wad.solmu.Solmu;

/**
 * Splay-puu on mukautuva binäärinen hakupuu. Splay-puu pyrkii kohottamaan
 * viimeisimmin haetut solmut lähemmäs juurta. Tästä seuraa, että useimmiten
 * haetut solmut löytyvät läheltä juurta. Tämä Splay täydentää BinaarinenHakupuu
 * -luokan vastaamaan splay-puu toteutusta.
 */
public class SplayPuu extends BinaarinenHakupuu {
    /**
     * Haku metodi splay-puulle. Toimii muuten kuten binäärisen hakupuun haku, mutta
     * splayaa puun.
     * @param haettava data, jota haetaan puusta
     * @return palauttaa haetun solmun. Null jos solmua ei löydy.
     */
    @Override
    public Solmu hae(Object haettava) {
        Solmu haettu = super.hae(haettava);
        if(haettu != null) splay(haettu);
        return haettu;
    }
    /**
     * Toteuttaa BinaarinenHakupuu-luokan lisaa-metodin, mutta "splayjaa" solmun.
     * @param lisattava data
     * @return palauttaa lisätyn solmun.
     */
    @Override
    public Solmu lisaa(Object lisattava) {
        Solmu lisatty = super.lisaa(lisattava);
        splay(lisatty);
        return lisatty;
    }
    
    /**
     * Toteuttaa BinaarinenHakupuu-luokan poista-metodin ja "splayjaa" solmun.
     * Poistuu metodista, jos poistettavaa solmua ei ole. Lopussa "kikkailua"
     * @param poistettava data
     * @return palauttaa poistetun solmun.
     */
    @Override
    public Solmu poista(Object poistettava) {
        Solmu poistettu = super.hae(poistettava);
        if( poistettu == null) return null;
        
        splay(poistettu);
        
        if( poistettu.getVasen() == null ) korvaa( poistettu, poistettu.getOikea() );
        else if( poistettu.getOikea() == null ) korvaa( poistettu, poistettu.getVasen() );
        else {
            Solmu apu = alipuunMin(poistettu.getOikea());
            if( apu.getVanhempi() != poistettu) {
                korvaa(apu, apu.getOikea());
                apu.setOikea(poistettu.getOikea());
                apu.getOikea().setVanhempi(apu);
            }
            korvaa(poistettu, apu);
            apu.setVasen(poistettu.getVasen());
            apu.getVasen().setVanhempi(apu);
        }
        return poistettu;
    }
    
    /**
     * Maaginen splay:aus metodi. Splay-puun ydinosa.
     * @param solmu splayattava solmu.
     */
    public void splay(Solmu solmu) {
        while(solmu.getVanhempi() != null) {
            Solmu vanhempi = solmu.getVanhempi();
            Solmu isovanhempi = solmu.getVanhempi().getVanhempi();   
            if( isovanhempi == null ) {
                if( solmu == vanhempi.getVasen() ){
                    oikeaKaanto(vanhempi);
                } else {
                    vasenKaanto(vanhempi);
                }
            } else if( vanhempi.getVasen() == solmu && isovanhempi.getVasen() == vanhempi ) {
               oikeaKaanto(isovanhempi);
               oikeaKaanto(vanhempi);
            } else if( vanhempi.getOikea() == solmu && isovanhempi.getOikea() == vanhempi ) {
                vasenKaanto(isovanhempi);
                vasenKaanto(vanhempi);
            } else if( vanhempi.getVasen() == solmu && isovanhempi.getOikea() == vanhempi ) {
                oikeaKaanto(vanhempi);
                vasenKaanto(vanhempi);   
            } else {
                vasenKaanto(vanhempi);
                oikeaKaanto(vanhempi);
            }
        }
    }
    
    /**
     * Kääntää solmua puussa vasemmalle.
     * @param x käännettävä solmu
     */
    private void vasenKaanto(Solmu x) {
        if (x.getOikea() == null) {
            return;
        }
        Solmu y = x.getOikea();
        x.setOikea(y.getVasen());
        if (y.getVasen() != null) {
            y.getVasen().setVanhempi(x);
        }
        y.setVanhempi(x.getVanhempi());
        if (x.getVanhempi() == null) {
            juuri = y;
        } else if (x == x.getVanhempi().getVasen()) {
            x.getVanhempi().setVasen(y);
        } else {
            x.getVanhempi().setOikea(y);
        }
        y.setVasen(x);
        x.setVanhempi(y);
    }
    
    /**
     * Kääntää solmua puussa oikealle.
     * @param x käännettävä solmu
     */
    private void oikeaKaanto(Solmu x) {
        if (x.getVasen() == null) {
            return;
        }
        Solmu y = x.getVasen();
        x.setVasen(y.getOikea());
        if (y.getOikea() != null) {
            y.getOikea().setVanhempi(x);
        }
        y.setVanhempi(x.getVanhempi());
        if (x.getVanhempi() == null) {
            juuri = y;
        } else if (x == x.getVanhempi().getVasen()) {
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
}