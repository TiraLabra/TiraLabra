package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import logiikka.Kontinpurkaja;
import osat.Laatikko;

/**
 * Luokka, joka luo piirroksen yhdestä laatikkokerroksesta, jotta käyttäjä
 * helposti näkee kuinka laatikot on asetettava.
 * 
 * @author albis
 */
public class KerroksenPiirtaja implements ActionListener {
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
    
    public KerroksenPiirtaja(JPanel panel, Kontinpurkaja kontinpurkaja, long EAN) {
        this.kontinpurkaja = kontinpurkaja;
        this.panel = panel;
        this.EAN = EAN;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Laatikko laatikko = kontinpurkaja.haeTuote(EAN).getLaatikko();
        
        int leveys = laatikko.getLeveys();
        int pituus = laatikko.getPituus();
        int korkeus = laatikko.getKorkeus();
    }
}
