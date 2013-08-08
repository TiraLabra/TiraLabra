
package com.mycompany.tiralabra_maven;

/**
 * Puun solmu luokka
 * @author esaaksvu
 */
public class Solmu {
    private Solmu vasenLapsi;
    private Solmu oikeaLapsi;
    private int arvo;
    
    /**
     * Luo uuden solmu-olion, jotka muodostavat puun
     * @param arvo on solmun sisältämä "data"
     */
    public Solmu(int arvo){
        this.arvo=arvo;
    }
    
    /**
     * Asettaa solmulle vasemman lapsen
     * @param s on vasemman lapsen solmu
     */
    public void setVasen(Solmu s){
        vasenLapsi=s;
    }
    
    /**
     *Asettaa solmulle oikeanpuoleisen lapsen
     * @param s on oikeanpuoleisen lapsen solmu
     */
    public void setOikea(Solmu s){
        oikeaLapsi=s;
    }
    /**
     * Metodi joka tutkii onko solmulla jälkeläisiä
     * @return true, jos solmulla ei ole lapsia
     */
    public boolean lapseton(){
        return (oikeaLapsi==null && vasenLapsi==null);
    }
    
    /**
     * Tulostaa solmun lapsineen muodossa juuri{vasen,oikea}
     * @return tulostuksen solmusta ja lapsista
     */
    @Override
    public String toString(){
        return arvo+"{"+getVasen()+","+getOikea()+"}";
    }

    int getArvo(){
        return arvo;
    }
    
    Solmu getVasen() {
        return vasenLapsi;
    }

    Solmu getOikea() {
       return oikeaLapsi;
    }
}
