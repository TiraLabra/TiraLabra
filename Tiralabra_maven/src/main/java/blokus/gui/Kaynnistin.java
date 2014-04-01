package blokus.gui;

import blokus.logiikka.Blokus;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ilkimys
 */
public class Kaynnistin extends JFrame {

    JPanel pohja;
    JCheckBox pelaaja1;
    JCheckBox pelaaja2;
    JCheckBox pelaaja3;
    JCheckBox pelaaja4;

    /**
     *
     */
    public Kaynnistin() {
        super("Blokus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        valmisteleValikko();

    }

    private void valmisteleValikko() {
        pohja = new JPanel();
        JLabel teksti1 = new JLabel("Valitkaa tietokonepelaajat:");
        pelaaja1 = new JCheckBox("Pelaaja 1");

        JLabel teksti2 = new JLabel("Pelaaja 2:");
        pelaaja2 = new JCheckBox("Pelaaja 2");

        JLabel teksti3 = new JLabel("Pelaaja 3:");
        pelaaja3 = new JCheckBox("Pelaaja 3");

        JLabel teksti4 = new JLabel("Pelaaja 4:");
        pelaaja4 = new JCheckBox("Pelaaja 4");
        
        JButton aloita = new JButton("Aloita peli");
        aloita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Blokus peli = new Blokus(pelaaja1.isSelected(), pelaaja2.isSelected(),pelaaja3.isSelected(),pelaaja4.isSelected());
                 peli.aloitaVuoro();
                Kayttoliittyma g = new Kayttoliittyma(peli);
                
            }         
        });

        pohja.add(teksti1);
        pohja.add(pelaaja1);
        pohja.add(pelaaja2);
        pohja.add(pelaaja3);
        pohja.add(pelaaja4);
        pohja.add(aloita);
        getContentPane().add(pohja);
        repaint();
        pack();
        setVisible(true);

    }
}
