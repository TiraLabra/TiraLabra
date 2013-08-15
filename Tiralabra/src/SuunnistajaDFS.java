
import rakenteet.Jarjestysjono;


/**
 *
 * @author maef
 */
public class SuunnistajaDFS {
     // Lähtöpiste
    private Solmu alku;
    //Maali
    private Solmu maali;
    //Missä suunnistetaan
    private Labyrintti laby;
    //Polku
    private Jarjestysjono<Solmu> polku = new Jarjestysjono();

    public SuunnistajaDFS(Solmu alku, Solmu maali, Labyrintti laby) {
        this.alku = alku;
        this.maali = maali;
        this.laby = laby;
        etsi(alku);
    }
    
    private Jarjestysjono<Solmu> etsi(Solmu solmu) {
        if(alku.seina || maali.seina) {
            return null;
        }
        if (solmu.getX()==maali.getX() && solmu.getY()==maali.getY()) {
            return polku;
        }
        if (solmu.getAlkuarvo()==Integer.MAX_VALUE) {
            solmu.setAlkuarvo(1);
            if (solmu.vierusX(-1)!=null && solmu.vierusX(1)!=null) {
            etsi(solmu.vierusX(-1));
            etsi(solmu.vierusX(1));
            }
            if (solmu.vierusY(-1)!=null && solmu.vierusY(1)!=null) {
            etsi(solmu.vierusY(-1));
            etsi(solmu.vierusY(1));
            }
        }
        return polku;
    }
    
    public Jarjestysjono<Solmu> getPolku() {
        return polku;
    }
    
    
}
