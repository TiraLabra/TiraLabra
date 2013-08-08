package logiikka;

import osat.Laatikko;
import osat.Lava;
import tyokalut.KasvavaLista;

/**
 * Luokka, joka ohjailee muita luokkia ja kokoaa niiden ominaisuudet yhteen toimivan ohjelman
 * aikaansaamiseksi.
 *
 * @author albis
 */
public class Kontinpurkaja {
    private Laskuri laskuri;
    
    public Kontinpurkaja() {
        this.laskuri = new Laskuri();
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
    public void laskeParasAsettelu(int laatikonLeveys, int laatikonPituus, int laatikonKorkeus, String EAN,
            int lavanLeveys, int lavanPituus, int lavanKorkeus) {
        
        KasvavaLista asettelu = laskuri.laske(new Laatikko(laatikonLeveys, laatikonPituus, laatikonKorkeus,
                EAN), new Lava(lavanLeveys, lavanPituus, lavanKorkeus));
        
        
    }
}
