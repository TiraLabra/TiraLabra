package tyokalut;

import java.io.FileWriter;
import java.io.IOException;
import osat.Laatikko;

/**
 *
 * @author albis
 */
public class AVLsolmu {
    private Laatikko laatikko;
    private KasvavaLista asettelu;
    
    private AVLsolmu vasen;
    private AVLsolmu oikea;
    private AVLsolmu vanhempi;
    
    private int korkeus;
    
    public AVLsolmu(Laatikko laatikko, KasvavaLista asettelu) {
        vasen = null;
        oikea = null;
        vanhempi = null;
        
        korkeus = 0;
        
        this.laatikko = laatikko;
        this.asettelu = asettelu;
    }
    
    public void tallennaSolmu(FileWriter kirjoittaja) throws IOException {
        if (vasen != null) {
            vasen.tallennaSolmu(kirjoittaja);
        }
        
        kirjoittaja.write(laatikko.getLeveys() + "-" + laatikko.getPituus() + "-" + laatikko.getKorkeus() +
                "-" + laatikko.getEAN() + ":");
        
        kirjoittaja.write(asettelu.getAsento(0));
        for (int i = 1; i < asettelu.length(); i++) {
            kirjoittaja.write("," + asettelu.getAsento(i));
        }
        kirjoittaja.write("\n");
        
        if (oikea != null) {
            oikea.tallennaSolmu(kirjoittaja);
        }
    }
    
    public long getKey() {
        return laatikko.getEAN();
    }
    
    public KasvavaLista getAsettelu() {
        return asettelu;
    }
    
    public AVLsolmu getVasen() {
        return vasen;
    }
    
    public AVLsolmu getOikea() {
        return oikea;
    }
    
    public AVLsolmu getVanhempi() {
        return vanhempi;
    }
    
    public int getKorkeus() {
        return korkeus;
    }
    
    public void setVasen(AVLsolmu solmu) {
        vasen = solmu;
    }
    
    public void setOikea(AVLsolmu solmu) {
        oikea = solmu;
    }
    
    public void setVanhempi(AVLsolmu solmu) {
        vanhempi = solmu;
    }
    
    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
    }
}
