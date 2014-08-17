package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.Siirto;

/**
 * Luokka tarjoaa metodit uuden pelipuun muodostamiseen
 * @author noora
 */
public class PuunMuodostaja {
    
    private final int maksimiSyvyys;
    private Solmu juuriSolmu;
    private Solmu edellinenSolmu;
    private int syvyys;
    
    public PuunMuodostaja(int maksimiSyvyys){
        this.maksimiSyvyys = maksimiSyvyys;
    }
    
    /**
     * Metodi lisää pelipuuhun uuden solmun
     * @param onkoValkoisenVuoro Kertoo kumman pelaajan vuoro on
     * @param siirto Kertoo siirron, jolla päädyttiin kyseiseen tilanteeseen
     */
    public void lisaaSolmu(boolean onkoValkoisenVuoro, Siirto siirto){
        if (this.syvyys <= maksimiSyvyys){
            edellinenSolmu = new Solmu(edellinenSolmu, onkoValkoisenVuoro, siirto);
            if (edellinenSolmu.getVanhempi() == null){
                this.juuriSolmu = edellinenSolmu;
            }
        }
        syvyys++;
    }
    
    /**
     * Palauttaa pelipuun juuren
     * @return 
     */
    public Solmu getPuu(){
        return this.juuriSolmu;
    }
    
}
