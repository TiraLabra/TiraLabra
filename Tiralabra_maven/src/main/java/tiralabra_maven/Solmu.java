package tiralabra_maven;

/**
 * Puun solmu luokka
 *
 * @author esaaksvu
 */
public class Solmu {

    private Solmu vanhemSolmu;
    private Solmu vasenLapsi;
    private Solmu oikeaLapsi;
    private int korkeus;
    private int arvo;

    /**
     * Luo uuden solmu-olion, jotka muodostavat puun
     *
     * @param arvo on solmun sisältämä "data"
     */
    public Solmu(int arvo) {
        this.arvo = arvo;
    }

    /**
     * Asettaa solmulle vasemman lapsen
     *
     * @param s on vasemman lapsen solmu
     */
    public void setVasen(Solmu s) {
        vasenLapsi = s;
    }

    /**
     * Asettaa solmulle oikeanpuoleisen lapsen
     *
     * @param s on oikeanpuoleisen lapsen solmu
     */
    public void setOikea(Solmu s) {
        oikeaLapsi = s;
    }

    /**
     * Metodi joka tutkii onko solmulla jälkeläisiä
     *
     * @return true, jos solmulla ei ole lapsia
     */
    public boolean lapseton() {
        return (oikeaLapsi == null && vasenLapsi == null);
    }

    /**
     * Tulostaa solmun lapsineen muodossa juuri{vasen,oikea}
     *
     * @return tulostuksen solmusta ja lapsista
     */
    @Override
    public String toString() {
       return arvo+"{"+vasenLapsi+","+oikeaLapsi+"}";
    }

    public int getArvo() {
        return arvo;
    }

    public Solmu getVasen() {
        return vasenLapsi;
    }

    public Solmu getOikea() {
        return oikeaLapsi;
    }

    public void setVanhem(Solmu a) {
        vanhemSolmu = a;
    }

    public Solmu getVanhem() {
        return vanhemSolmu;
    }

    public void setArvo(int arvo) {
        this.arvo=arvo;
    }
    
    public void setKorkeus(int h){
        this.korkeus=h;
    }
    
    public int getKorkeus(){
        return korkeus;
    }
}
