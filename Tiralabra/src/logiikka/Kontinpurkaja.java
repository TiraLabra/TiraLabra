/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import osat.Laatikko;
import osat.Lava;
import tyokalut.KasvavaLista;

/**
 *
 * @author albis
 */
public class Kontinpurkaja {
    private Laskuri laskuri;
    
    public Kontinpurkaja() {
        this.laskuri = new Laskuri();
    }
    
    public void laskeParasAsettelu(int laatikonLeveys, int laatikonPituus, int laatikonKorkeus, int EAN,
            int lavanLeveys, int lavanPituus, int lavanKorkeus) {
        
        KasvavaLista asettelu = laskuri.laske(new Laatikko(laatikonLeveys, laatikonPituus, laatikonKorkeus,
                EAN), new Lava(lavanLeveys, lavanPituus, lavanKorkeus));
        
        
    }
}
