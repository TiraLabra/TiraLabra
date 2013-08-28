package kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import logiikka.Kontinpurkaja;

/**
 * Luokka huolehtii laatikon ja lavan tietojen keräämisestä, sekä käynnistää
 * näiden pohjalta asettelun laskentaominaisuuden. Tuloksen valmistuttua esitetään
 * käyttäjälle saatu asettelu.
 *
 * @author albis
 */
public class LaskentaKuuntelija implements ActionListener {
    private Kontinpurkaja kontinpurkaja;
    private JPanel panel;
    private JButton nappi;
    
    public LaskentaKuuntelija(Kontinpurkaja kontinpurkaja, JPanel panel, JButton nappi) {
        this.kontinpurkaja = kontinpurkaja;
        this.panel = panel;
        this.nappi = nappi;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        nappi.setVisible(false);
        
        JTextArea laatikonLeveys = (JTextArea) panel.getComponent(1);
        JTextArea laatikonPituus = (JTextArea) panel.getComponent(3);
        JTextArea laatikonKorkeus = (JTextArea) panel.getComponent(5);
        JTextArea tuotteenEAN = (JTextArea) panel.getComponent(7);
        JTextArea lavanLeveys = (JTextArea) panel.getComponent(9);
        JTextArea lavanPituus = (JTextArea) panel.getComponent(11);
        JTextArea lavanKorkeus = (JTextArea) panel.getComponent(13);
        
        try {
            int laatikonLeveysLuku = Integer.parseInt(laatikonLeveys.getText());
            int laatikonPituusLuku = Integer.parseInt(laatikonPituus.getText());
            int laatikonKorkeusLuku = Integer.parseInt(laatikonKorkeus.getText());
            long EAN = Long.parseLong(tuotteenEAN.getText());
            int lavanLeveysLuku = Integer.parseInt(lavanLeveys.getText());
            int lavanPituusLuku = Integer.parseInt(lavanPituus.getText());
            int lavanKorkeusLuku = Integer.parseInt(lavanKorkeus.getText());
            
            if (laatikonLeveysLuku > lavanLeveysLuku || laatikonPituusLuku > lavanPituusLuku ||
                    laatikonKorkeusLuku > lavanKorkeusLuku || lavanLeveysLuku > 100 ||
                    lavanPituusLuku > 120 || tuotteenEAN.getText().length() != 12 ||
                    laatikonLeveysLuku <= 0 || laatikonPituusLuku <= 0 || laatikonKorkeusLuku <= 0 ||
                    EAN <= 0 || lavanLeveysLuku <= 0 || lavanPituusLuku <= 0 || lavanKorkeusLuku <= 0) {
                throw new NumberFormatException();
            }
            
            kontinpurkaja.laskeParasAsettelu(laatikonLeveysLuku, laatikonPituusLuku, laatikonKorkeusLuku,
                EAN, lavanLeveysLuku, lavanPituusLuku, lavanKorkeusLuku);
            
            nappi.removeActionListener(nappi.getActionListeners()[0]);
            nappi.addActionListener(new TulostenEsittaja(panel, kontinpurkaja, tuotteenEAN));
            nappi.doClick();
        } catch (NumberFormatException e) {
            panel.removeAll();
            panel.repaint();
            panel.add(new JLabel("Virheellinen syöte!"));
        }       
    }
}