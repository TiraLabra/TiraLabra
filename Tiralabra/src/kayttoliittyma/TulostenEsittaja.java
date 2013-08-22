package kayttoliittyma;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logiikka.Kontinpurkaja;
import osat.Laatikko;
import osat.Nelikulmio;
import tyokalut.AVLsolmu;

/**
 * Luokka, joka luo piirroksen yhdestä laatikkokerroksesta, jotta käyttäjä
 * helposti näkee kuinka laatikot on asetettava ja kertoo minkä kokoisilla
 * laatikoilla ja mitä lavoja käyttäen asettelu on laskettu.
 * 
 * @author albis
 */
public class TulostenEsittaja implements ActionListener {
    /**
     * Olio, jolta saadaan tieto kuinka laatikot on asetettava.
     */
    private Kontinpurkaja kontinpurkaja;
    
    /**
     * Graafisen käyttöliittymän osa, johon tämä luokka piirtää.
     */
    private JPanel panel;
    
    /**
     * Piirrettävän tuotteen tunniste.
     */
    private long EAN;
    
    public TulostenEsittaja(JPanel panel, Kontinpurkaja kontinpurkaja, long EAN) {
        this.kontinpurkaja = kontinpurkaja;
        this.panel = panel;
        this.EAN = EAN;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AVLsolmu solmu = kontinpurkaja.haeTuote(EAN);
        Laatikko laatikko = solmu.getLaatikko();
        Nelikulmio lava = solmu.getLava();
        
        panel.setLayout(new GridLayout(2, 1));
        
        panel.add(luoTietosarake(solmu));
        panel.add(new Piirtoalusta(solmu, panel.getHeight()));
    }
    
    /**
     * Kertoo haetun tuotteen asettelun laskemiseen edellisen kerran käytetyt laatikon
     * ja lavan mitat.
     * 
     * @return Palauttaa JPanel-olion, johon merkittynä halutut mittatiedot.
     */
    private JPanel luoTietosarake(AVLsolmu solmu) {
        Laatikko laatikko = solmu.getLaatikko();
        Nelikulmio lava = solmu.getLava();
        
        int laatikonLeveys = laatikko.getLeveys();
        int laatikonPituus = laatikko.getPituus();
        int laatikonKorkeus = laatikko.getKorkeus();
        
        int lavanLeveys = lava.getLeveys();
        int lavanPituus = lava.getPituus();
        int lavanKorkeus = lava.getKorkeus();
        
        JPanel tiedot = new JPanel();
        tiedot.add(new JLabel("Laatikon leveys: " + laatikonLeveys + "\nLaatikon pituus: " +
                laatikonPituus + "\nLaatikon korkeus: " + laatikonKorkeus + "\n\nLavan leveys" +
                lavanLeveys + "\nLavan pituus: " + lavanPituus + "\nLavan korkeus: " + lavanKorkeus +
                "\n\n Kerroksia mahtuu: " + solmu.getKerrokset()));
        
        return tiedot;
    }
}
