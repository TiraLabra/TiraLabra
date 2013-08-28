package logiikka;

import osat.Laatikko;
import osat.Nelikulmio;
import tyokalut.AVLkasittelija;
import tyokalut.AVLsolmu;
import tyokalut.TiedostonKasittelija;

/**
 * Luokka, joka ohjailee muita luokkia ja kokoaa niiden ominaisuudet yhteen toimivan ohjelman
 * aikaansaamiseksi.
 *
 * @author albis
 */
public class Kontinpurkaja {
    /**
     * Luokka, joka huolehtii tuotteen asettelutavan laskemisesta.
     */
    private Laskuri laskuri;
    
    /**
     * Historian sisältävän AVL-puun toiminnot kokoava olio.
     */
    private AVLkasittelija historia;
    
    private TiedostonKasittelija tiedostonKasittelija;
    
    public Kontinpurkaja() {
        laskuri = new Laskuri();
        historia = new AVLkasittelija();
        tiedostonKasittelija = new TiedostonKasittelija(historia);
        
        tiedostonKasittelija.avaa();
    }
    
    /**
     * Metodi, jota käytetään laskemaan annetun laatikon asettelutapa annetulle lavalle.
     * 
     * @param laatikonLeveys Asetettavan laatikon leveys kokonaislukuna.
     * @param laatikonPituus Asetettavan laatikon pituus kokonaislukuna.
     * @param laatikonKorkeus Asetettavan laatikon korkeus kokonaislukuna.
     * @param EAN Asetettavan laatikon yksilöivä kokonaisluku.
     * @param lavanLeveys Sen lavan leveys, jolle laatikot on asetettava.
     * @param lavanPituus Sen lavan pituus, jolle laatikot on asetettava.
     * @param lavanKorkeus Sen lavan korkeus, jolle laatikot on asetettava.
     */
    public void laskeParasAsettelu(int laatikonLeveys, int laatikonPituus, int laatikonKorkeus, long EAN,
            int lavanLeveys, int lavanPituus, int lavanKorkeus) {
        
        Laatikko laatikko = new Laatikko(laatikonLeveys, laatikonPituus, laatikonKorkeus, EAN);
        Nelikulmio lava = new Nelikulmio(lavanLeveys, lavanPituus, lavanKorkeus);
        
        int[][] asettelu = laskuri.laske(laatikko, lava);
        
        AVLsolmu solmu = haeTuote(EAN);
        
        if (solmu == null) {
            historia.AVLlisays(laatikko, asettelu, lava);
        } else {
            solmu.setLaatikko(laatikko);
            solmu.setLava(lava);
            solmu.setAsettelu(asettelu);
        }
    }
    
    /**
     * Hakee AVL-puusta annetun EAN-koodin mukaisen tuotteen asettelun, jos sellainen
     * on laskettu.
     * 
     * @param EAN Haluttu tuote.
     * @return Palauttaa halutun tuotteen tiedot historiasta.
     */
    public AVLsolmu haeTuote(long EAN) {
        return historia.etsi(historia.getJuuri(), EAN);
    }
    
    /**
     * Tallentaa AVL-puussa olevat asettelutiedot muistiin.
     */
    public void tallennaHaut() {
        tiedostonKasittelija.tallenna();
    }
}
