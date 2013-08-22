package tiralabra_maven;

/**
 * Puun solmu luokka Sisältää AVL ja punamustaan tarvittavat muuttujat
 *
 * @author esaaksvu
 */
public class Solmu {

    private Solmu vanhemSolmu;
    private Solmu vasenLapsi;
    private Solmu oikeaLapsi;
    private int korkeus; //AVL puutaa varten
    private boolean vari; //Punamusta puuta varten, t=musta, f=punainen
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
        if (lapseton()) {
            return "" + arvo;
        }
        if (vasenLapsi != null && oikeaLapsi != null) {
            return arvo + "{" + vasenLapsi + "," + oikeaLapsi + "}";
        }
        if (vasenLapsi != null) {
            return arvo + "{" + vasenLapsi + ",[]}";
        }
        if (oikeaLapsi != null) {
            return arvo + "{[]," + oikeaLapsi + "}";
        }
        return "";

    }

    public void setVasen(Solmu s) {
        vasenLapsi = s;
    }

    public void setOikea(Solmu s) {
        oikeaLapsi = s;
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
        this.arvo = arvo;
    }

    /**
     * korkeus AVL-puuta varten
     *
     * @param Solmun korkeus puussa, 0 on juuri
     */
    public void setKorkeus(int h) {
        this.korkeus = h;
    }

    public int getKorkeus() {
        return korkeus;
    }

    /**
     * Väri punamustapuuta varten
     *
     * @param true jos on musta
     */
    public void setVari(Boolean b) {
        vari = b;
    }

    public boolean getVari() {
        return vari;
    }
}
