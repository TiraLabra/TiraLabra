package tyokalut;

import osat.Laatikko;
import osat.Nelikulmio;

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
     * Kaksiulotteinen taulukko, joka sisältää tiedon kuinka laatikot on aseteltu.
     */
    private int[][] asettelu;
    
    /**
     * Kokonaisluku, joka kertoo kuinka monta kerrosta lavalle voidaan tehdä.
     */
    private int kerrostenMaara;
    
    /**
     * Sisältää tiedon minkä kokoiselle lavalle laatikoiden asettelu on laskettu.
     */
    private Nelikulmio lava;
    
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
    
    public AVLsolmu(Laatikko laatikko, int[][] asettelu, Nelikulmio lava) {
        vasen = null;
        oikea = null;
        vanhempi = null;
        
        korkeus = 0;
        
        this.laatikko = laatikko;
        this.asettelu = asettelu;
        this.lava = lava;
        
        kerrostenMaara = lava.getKorkeus() / laatikko.getKorkeus();
    }
    
    public long getKey() {
        return laatikko.getEAN();
    }
    
    public int[][] getAsettelu() {
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
    
    public int getKerrokset() {
        return kerrostenMaara;
    }
    
    public Laatikko getLaatikko() {
        return laatikko;
    }
    
    public Nelikulmio getLava() {
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
    
    public void setLava(Nelikulmio lava) {
        this.lava = lava;
    }
    
    public void setAsettelu(int[][] asettelu) {
        this.asettelu = asettelu;
    }
}
