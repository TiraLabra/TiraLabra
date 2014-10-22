/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Luokan tehtävänä on kuunnella Menun kaynnista-JButtonin komentoja.
 * @author Mikael Parvamo
 */
public class MenuKomentojenKuuntelija implements ActionListener {

    private JTextField syotekentta;
    private JLabel vastauskentta;
    private JButton kaynnista;

    public MenuKomentojenKuuntelija(JTextField syotekentta, JLabel vastauskentta, JButton kaynnista) {
        this.syotekentta = syotekentta;
        this.vastauskentta = vastauskentta;
        this.kaynnista = kaynnista;
    }
    
    /**
     * Metodi tarkistaa komennon lähteen, minkä jälkeen
     * tarkastetaan, että syöte on halutun mukainen.
     * Metodi luo labyrintin syötteen (koon) perusteella.
     * 
     * @param ActionEvent ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == kaynnista) {
            try {
                int koko = Integer.parseInt(syotekentta.getText());
            } catch (Exception e) {
                syotekentta.setText("");
                return;
            }
            int koko = Integer.parseInt(syotekentta.getText());
            if(koko >= 2 && koko <= 40){
                LabyrinthGUI kayttis = new LabyrinthGUI(koko);
                kayttis.run();
            }
            else{
                syotekentta.setText("");
            }
        }
    }
}
