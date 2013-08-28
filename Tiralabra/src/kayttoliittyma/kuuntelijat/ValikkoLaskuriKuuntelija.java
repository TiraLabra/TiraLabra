package kayttoliittyma.kuuntelijat;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import logiikka.Kontinpurkaja;

/**
 * Luokka, joka luo graafisen käyttöliittymän ja tarjoaa syötemahdollisuuden
 * uusien tuotteiden asettelutapojen laskemiseen.
 *
 * @author albis
 */
public class ValikkoLaskuriKuuntelija implements ActionListener {
    /**
     * Graafisen käyttöliittymän osa, jolle tämä luokka kokoaa kenttänsä.
     */
    private JPanel panel;
    
    /**
     * Nappi, joka käynnistää laskutoiminnon.
     */
    private JButton nappi;
    
    
    private Kontinpurkaja kontinpurkaja;
    
    public ValikkoLaskuriKuuntelija(JPanel panel, JButton nappi, Kontinpurkaja kontinpurkaja) {
        this.panel = panel;
        this.nappi = nappi;
        
        this.kontinpurkaja = kontinpurkaja;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        panel.removeAll();
        panel.repaint();
        
        JTextArea laatikonLeveys = new JTextArea();
        JTextArea laatikonPituus = new JTextArea();
        JTextArea laatikonKorkeus = new JTextArea();
        JTextArea tuotteenEAN = new JTextArea();
        
        JTextArea lavanLeveys = new JTextArea();
        JTextArea lavanPituus = new JTextArea();
        JTextArea lavanKorkeus = new JTextArea();
        
        GridLayout layout = new GridLayout(7, 2);
        layout.setHgap(1);
        layout.setVgap(1);
        panel.setLayout(layout);
        
        
        panel.add(new JLabel("Laatikon leveys:"));
        panel.add(laatikonLeveys);
        panel.add(new JLabel("Laatikon pituus:"));
        panel.add(laatikonPituus);
        panel.add(new JLabel("Laatikon korkeus:"));
        panel.add(laatikonKorkeus);
        panel.add(new JLabel("Tuotteen EAN-koodi:"));
        panel.add(tuotteenEAN);
        panel.add(new JLabel("Lavan leveys:"));
        panel.add(lavanLeveys);
        panel.add(new JLabel("Lavan pituus:"));
        panel.add(lavanPituus);
        panel.add(new JLabel("Lavan korkeus:"));
        panel.add(lavanKorkeus);
        
        nappi.setText("Laske");
        nappi.addActionListener(new LaskentaKuuntelija(kontinpurkaja, panel, nappi));
                
        nappi.setVisible(true);
    }
    
}
