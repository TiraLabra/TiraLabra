
package kolmiopeli.UI;


import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Pelin loputtua esiin tuleva nakyma
 * @author Eemi
 */
public class GameOverPanel extends JPanel implements ActionListener {
    private PeliFrame peliFrame;
    private int pisteet;



    GameOverPanel(PeliFrame peliFrame) {
        this.peliFrame = peliFrame;
        this.pisteet = 0;
    }

    void paivita() {
        this.pisteet = this.peliFrame.getInfoPaneeli().getPisteet();
        this.add(new JLabel("PELI OHI! Pisteesi: " + this.pisteet));
        JButton uudestaan = new JButton("Uudestaan?");
        uudestaan.addActionListener(this);
        this.add(uudestaan);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.peliFrame.uusiPeli();
        CardLayout nakymat = (CardLayout) this.peliFrame.getNakymat().getLayout();
        nakymat.first(this.peliFrame.getNakymat());
    }


    
    
    
    
}
