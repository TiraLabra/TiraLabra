package kayttoliittyma.kuuntelijat;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import kayttoliittyma.osat.Piirtoalusta;
import kayttoliittyma.osat.Tietosarake;
import logiikka.Kontinpurkaja;
import osat.Laatikko;
import osat.Nelikulmio;
import tyokalut.AVLsolmu;

/**
 * Luokka, joka kokoaa kyseessä olevan asettelun tiedot sekä graafisen esityksen
 * käyttäjän tarkasteltavaksi.
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
    private JTextArea EAN;
    
    public TulostenEsittaja(JPanel panel, Kontinpurkaja kontinpurkaja, JTextArea EAN) {
        this.kontinpurkaja = kontinpurkaja;
        this.panel = panel;
        this.EAN = EAN;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        long EANluku = 0;
        
        try {
            if (EAN.getText().length() != 12) {
                throw new NumberFormatException();
            }
            
            EANluku = Long.parseLong(EAN.getText());
        } catch (NumberFormatException e) {
            panel.add(new JLabel("Virheellinen syöte!"));
        }
        
        panel.removeAll();
        panel.repaint();
        
        AVLsolmu solmu = kontinpurkaja.haeTuote(EANluku);
        Laatikko laatikko = solmu.getLaatikko();
        Nelikulmio lava = solmu.getLava();
        
        panel.setLayout(new GridLayout(1, 2));
        
        panel.add(new Tietosarake(solmu));
        panel.add(new Piirtoalusta(solmu));
        
        JFrame frame = (JFrame) panel.getTopLevelAncestor();
        frame.pack();
        frame.repaint();
    }
}
