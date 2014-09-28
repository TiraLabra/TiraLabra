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
     * Toteuttaa BinaarinenHakupuu-luokan hae-metodin, mutta "splayjaa" solmun.
     * @param haettava data
     * @return palauttaa haettua dataa vastaavan solmun.
     */
    @Override
    public Solmu hae(Object haettava) {
        Solmu haettu = super.hae(haettava);
        splay(haettu);
        return haettu;
    }
    
    /**
     * Toteuttaa BinaarinenHakupuu-luokan lisaa-metodin, mutta "splayjaa" solmun.
     * @param lisattava data
     * @return palauttaa lisätyn solmun.
     */
    @Override
    public Solmu lisaa(Object lisattava) {
        Solmu lisatty;
        if( (lisatty = super.lisaa(lisattava)) != null ) splay(lisatty);
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
        Solmu poistettu;
        if( (poistettu = super.poista(poistettava)) == null) return null;
        
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
            if(solmu.getVanhempi().getVanhempi() == null) {
                if( solmu.getVanhempi().getVasen() == solmu) oikeaKaanto(solmu.getVanhempi());
                else vasenKaanto(solmu.getVanhempi());
            } else if( solmu.getVanhempi().getVasen() == solmu && solmu.getVanhempi().getVanhempi().getVasen() == solmu.getVanhempi()) {
               oikeaKaanto(solmu.getVanhempi().getVanhempi());
               oikeaKaanto(solmu.getVanhempi());
            } else if( solmu.getVanhempi().getOikea() == solmu && solmu.getVanhempi().getVanhempi().getOikea() == solmu.getVanhempi()) {
                vasenKaanto(solmu.getVanhempi().getVanhempi());
                vasenKaanto(solmu.getVanhempi());
            } else if( solmu.getVanhempi().getVasen() == solmu && solmu.getVanhempi().getVanhempi().getOikea() == solmu.getVanhempi()) {
                oikeaKaanto(solmu.getVanhempi());
                vasenKaanto(solmu.getVanhempi());   
            } else {
                vasenKaanto(solmu.getVanhempi());
                oikeaKaanto(solmu.getVanhempi());
            }
        }
    }
    
    private void vasenKaanto(Solmu solmu) {
        Solmu apu = solmu.getOikea();
        if(apu != null) {
            solmu.setOikea(apu.getVasen());
            if(apu.getVasen() != null) apu.getVasen().setVanhempi(solmu);
            apu.setVanhempi(solmu.getVanhempi());
        }
        
        apuKaanto(solmu,apu);
        if(apu != null) apu.setVasen(solmu);
        solmu.setVanhempi(apu);
    }
    
    private void oikeaKaanto(Solmu solmu) {
        Solmu apu = solmu.getVasen();
        if(apu != null) {
            solmu.setVasen(apu.getOikea());
            if(solmu.getOikea() != null) apu.getOikea().setVanhempi(solmu);
            apu.setVanhempi(solmu.getVanhempi());
        }
        
        apuKaanto(solmu,apu);
        solmu.setVanhempi(apu);
    }
    
    private void apuKaanto(Solmu solmu, Solmu apu) {
        if(solmu.getVanhempi() == null) juuri = apu;
        else if(solmu == solmu.getVanhempi().getVasen()) solmu.getVanhempi().setVasen(apu);
        else solmu.getVanhempi().setOikea(apu);
    }
    
    /**
     * Korvaa solmun u solmulla v
     * @param u korvattava solmu
     * @param v korvaava solmu
     */
    public void korvaa(Solmu u, Solmu v) {
        if(u.getVanhempi() == null) juuri = v;
        else if(u == u.getVanhempi().getVasen()) u.getVanhempi().setVasen(v);
        else u.getVanhempi().setOikea(v);
        if(v != null) v.setVanhempi(u.getVanhempi());
    }
}
