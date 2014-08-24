package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.Pelilauta;
import com.mycompany.tiralabra_maven.Siirto;

/**
 * Luokka tarjoaa metodit uuden pelipuun muodostamiseen
 * Ei ole vielä käytössä
 * @author noora
 */
public class PuunMuodostaja {
    
    private final int maksimiSyvyys;
    private Solmu juuriSolmu;
    private Solmu edellinenSolmu;
    private int syvyys;
    private Pelilauta lauta;
    
    public PuunMuodostaja(int maksimiSyvyys, Pelilauta lauta){
        this.maksimiSyvyys = maksimiSyvyys;
        this.lauta = lauta;
    }
    
//    /**
//     * Metodi lisää pelipuuhun uuden solmun
//     * @param onkoValkoisenVuoro Kertoo kumman pelaajan vuoro on
//     * @param siirto Kertoo siirron, jolla päädyttiin kyseiseen tilanteeseen
//     */
//    public void lisaaSolmu(boolean onkoValkoisenVuoro, Siirto siirto){
//        if (this.syvyys <= maksimiSyvyys){
//            edellinenSolmu = new Solmu(onkoValkoisenVuoro, siirto);
//            if (edellinenSolmu.getVanhempi() == null){
//                this.juuriSolmu = edellinenSolmu;
//            }
//        }
//        syvyys++;
//    }
    
    /**
     * Palauttaa pelipuun juuren
     * @return 
     */
    public Solmu getPuu(){
        return this.juuriSolmu;
    }
    
}
