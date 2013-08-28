package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import kayttoliittyma.kuuntelijat.ValikkoHistoriaKuuntelija;
import kayttoliittyma.kuuntelijat.ValikkoLaskuriKuuntelija;
import logiikka.Kontinpurkaja;

/**
 * Graafisen käyttöliittymän kokoava ja alkuvalikon luova luokka.
 *
 * @author albis
 */
public class Kayttoliittyma implements Runnable {
    /**
     * Pohja jolle graafista käyttöliittymää rakennetaan.
     */
    private JFrame frame;
    
    /**
     * Olio, joka tarjoaa kaikki laatikoiden asettelun laskemiseen ja historian tarkasteluun
     * tarvittavat toiminnot.
     */
    private Kontinpurkaja kontinpurkaja;
    
    public Kayttoliittyma(Kontinpurkaja kontinpurkaja) {
        this.kontinpurkaja = kontinpurkaja;
    }
    
    @Override
    public void run() {
        frame = new JFrame("Kontinpurkaja 2000");
        frame.setPreferredSize(null);
        
        Dimension koko = Toolkit.getDefaultToolkit().getScreenSize();
        
        frame.setPreferredSize(new Dimension((int) koko.getWidth() / 2, (int) koko.getHeight() / 2));
        
        lisaaSulkuoperaatio();
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Luo alkuvalikkoon kuuluvat osat ja lisää ne näkyville.
     * 
     * @param container Graafiset osat sisältävä olio.
     */
    private void luoKomponentit(Container container) {
        JPanel valikko = new JPanel(new GridLayout(1,2));
        JPanel panel = new JPanel();
        JButton nappi = new JButton();
        
        JButton laskuriNappi = new JButton("Laskuri");
        laskuriNappi.addActionListener(new ValikkoLaskuriKuuntelija(panel, nappi, kontinpurkaja));
        JButton historiaNappi = new JButton("Historia");
        historiaNappi.addActionListener(new ValikkoHistoriaKuuntelija(panel, nappi, kontinpurkaja));
        
        valikko.add(laskuriNappi);
        valikko.add(historiaNappi);

        container.add(valikko, BorderLayout.NORTH);
        container.add(panel);
        container.add(nappi, BorderLayout.SOUTH);
        
        nappi.setVisible(false);
    }
    
    /**
     * Käyttöliittymän tavallisen sulkemisen korvaava metodi, joka aina ennen sulkemista
     * tallentaa asettelutiedot sisältävän AVL-puun muistiin tekstitiedostoon, josta
     * nämä tiedot saadaan ohjelman seuraavalla käyttökerralla.
     */
    private void lisaaSulkuoperaatio() {
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                kontinpurkaja.tallennaHaut();
                System.exit(0);
            }
        });
    }
}
