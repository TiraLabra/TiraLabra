package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Luokka, joka huolehtii historian hakemisen graafisesta esittämisestä ja syötteiden
 * lukemisesta.
 * 
 * @author albis
 */
public class ValikkoHistoriaKuuntelija implements ActionListener {
    /**
     * Graafisen käyttöliittymän osa, jolle tämä luokka kokoaa tarvittavat osansa.
     */
    private JPanel panel;
    
    /**
     * Hakunappi, joka käynnistää toiminnon, jolla haetaan historiasta asettelutietoja.
     */
    private JButton nappi;
    
    public ValikkoHistoriaKuuntelija(JPanel panel, JButton nappi) {
        this.panel = panel;
        this.nappi = nappi;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.removeAll();
        panel.repaint();
        
        JPanel hakukentta = new JPanel();
        hakukentta.setLayout(new BorderLayout());
        
        JTextArea syote = new JTextArea();
        hakukentta.add(new JLabel("Syötä halutun tuotteen EAN-koodi:"), BorderLayout.WEST);
        hakukentta.add(syote);
        
        panel.setLayout(new BorderLayout());
        panel.add(hakukentta, BorderLayout.SOUTH);
        
        nappi.setText("Hae");
        nappi.setVisible(true);
    }
    
}
