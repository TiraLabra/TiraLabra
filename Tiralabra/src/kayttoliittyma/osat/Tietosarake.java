package kayttoliittyma.osat;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import osat.Laatikko;
import osat.Nelikulmio;
import tyokalut.AVLsolmu;

/**
 * Kertoo haetun tuotteen asettelun laskemiseen edellisen kerran käytetyt laatikon
 * ja lavan mitat.
 * 
 * @return Perii JPanel-luokan, johon merkitään halutut mittatiedot.
 *
 * @author albis
 */
public class Tietosarake extends JPanel {
    
    public Tietosarake(AVLsolmu solmu) {
        this.setLayout(new GridLayout(7, 1));
        
        Laatikko laatikko = solmu.getLaatikko();
        Nelikulmio lava = solmu.getLava();
        
        int laatikonLeveys = laatikko.getLeveys();
        int laatikonPituus = laatikko.getPituus();
        int laatikonKorkeus = laatikko.getKorkeus();
        
        int lavanLeveys = lava.getLeveys();
        int lavanPituus = lava.getPituus();
        int lavanKorkeus = lava.getKorkeus();
        
        lisaaTieto("Laatikon leveys", laatikonLeveys);
        lisaaTieto("Laatikon pituus", laatikonPituus);
        lisaaTieto("Laatikon korkeus", laatikonKorkeus);
        lisaaTieto("Lavan leveys", lavanLeveys);
        lisaaTieto("Lavan pituus", lavanPituus);
        lisaaTieto("Lavan korkeus", lavanKorkeus);
        lisaaTieto("Kerrosten määrä", solmu.getKerrokset());
    }
    
    /**
     * Lisää tähän komponenttiin annetun tekstin, joka sisältää parametrinä annetun
     * merkkijonon sekä kokonaisluvun.
     * 
     * @param teksti Lisättävä teksti.
     * @param koko Lisättävä numerotieto.
     */
    private void lisaaTieto(String teksti, int koko) {
        Font fontti = new Font("Arial", Font.PLAIN, 20);
        
        JLabel tieto = new JLabel(teksti + ": " + koko);
        tieto.setFont(fontti);
        
        this.add(tieto);
    }
}
