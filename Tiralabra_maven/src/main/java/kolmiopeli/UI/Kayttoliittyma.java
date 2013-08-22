
package kolmiopeli.UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import kolmiopeli.domain.Ruudukko;

/**
 * JFrame johon tulee kaikki graafinen sisalto
 * @author Eemi
 */
public class Kayttoliittyma implements Runnable, ActionListener {
    private JFrame frame;
    
    
    
    
    @Override
    public void run() {
        frame = new JFrame("Kolmio-peli");

        frame.setPreferredSize(new Dimension(640, 558));
        frame.setResizable(false);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.getContentPane().removeAll();
        new PeliFrame(8, 14, this.frame.getContentPane()); // TAALLA RUUDUKON KOON YKSI HARDCODED KOHTA
        this.frame.revalidate();

        
        
    }

    private void luoKomponentit(Container container) {
        JPanel panel = new JPanel();
        container.add(panel);
        
        JLabel ohjeet = new JLabel("Vaihtele vierekkaisia kolmioita ja keraa pisteita samanvarisista kolmioista kunnes siirrot loppuvat!");
        
        container.add(ohjeet, BorderLayout.NORTH);
        
        JButton pelaa = new JButton("Pelaa");
        pelaa.addActionListener(this);
        
        panel.add(pelaa, BorderLayout.CENTER);
        
    }
    
}
