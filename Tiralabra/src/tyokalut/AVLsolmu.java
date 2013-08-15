package tyokalut;

import java.io.FileWriter;
import java.io.IOException;
import osat.Laatikko;
import osat.Lava;

/**
 * AVL-puun solmua kuvaava peruskomponentti, joihin kerätään tiedot aiemmin laskettujen
 * tuotteiden asettelusta.
 *
 * @author albis
 */
public class AVLsolmu {
    /**
     * Laatikko-olio, jonka kautta päästään käsiksi mittoihin, joita kyseisen tuotteen
     * asettelun laskemiseen käytettiin
     */
    private Laatikko laatikko;
    
    /**
     * Lista, joka sisältää tiedon kuinka laatikot on aseteltu.
     */
    private KasvavaLista asettelu;
    
    /**
     * Sisältää tiedon minkä kokoiselle lavalle laatikoiden asettelu on laskettu.
     */
    private Lava lava;
    
    /**
     * Solmun vasen lapsi.
     */
    private AVLsolmu vasen;
    
    /**
     * Solmun oikea lapsi.
     */
    private AVLsolmu oikea;
    
    /**
     * Solmun vanhempi.
     */
    private AVLsolmu vanhempi;
    
    /**
     * Kertoo solmun etäisyyden puun lehteen.
     */
    private int korkeus;
    
    public AVLsolmu(Laatikko laatikko, KasvavaLista asettelu, Lava lava) {
        vasen = null;
        oikea = null;
        vanhempi = null;
        
        korkeus = 0;
        
        this.laatikko = laatikko;
        this.asettelu = asettelu;
        this.lava = lava;
    }
    
    /**
     * Tallentaa solmun tiedot tekstitiedostoon seuraavaa käyttökertaa varten.
     * 
     * @param kirjoittaja Tiedostoon kirjoittamisesta huolehtiva olio.
     * @throws IOException 
     */
    public void tallennaSolmu(FileWriter kirjoittaja) throws IOException {
        if (vasen != null) {
            vasen.tallennaSolmu(kirjoittaja);
        }
        
        kirjoittaja.write(laatikko.getLeveys() + "-" + laatikko.getPituus() + "-" + laatikko.getKorkeus() +
                "-" + laatikko.getEAN() + ":");
        kirjoittaja.write(lava.getLeveys() + "-" + lava.getPituus() + "-" + lava.getKorkeus() + "=");
        
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
    
    public Laatikko getLaatikko() {
        return laatikko;
    }
    
    public Lava getLava() {
        return lava;
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
    
    public void setLaatikko(Laatikko laatikko) {
        this.laatikko = laatikko;
    }
    
    public void setAsettelu(KasvavaLista asettelu) {
        this.asettelu = asettelu;
    }
}
