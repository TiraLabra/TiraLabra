package viidensuora.gui;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Peliruudun laidoilla näytettävä scrollattava infopaneeli johon tulostetaan
 * tekoälyn etsimän siirron tiedot.
 *
 * @author juha
 */
public class InfoPaneeli extends JPanel {

    private final JTextArea hakutulosData;

    public InfoPaneeli(String alkuteksti, Color tekstinVari) {
        hakutulosData = new JTextArea(alkuteksti, 20, 30);
        hakutulosData.setForeground(tekstinVari);
        hakutulosData.setEditable(false);
        JScrollPane scroll = new JScrollPane(hakutulosData,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scroll);
    }

    /**
     * Lisää parametrina saadun tekstin kentän loppuun.
     *
     * @param teksti Tulostettava teksti.
     */
    public void lisaaTekstia(String teksti) {
        hakutulosData.append(teksti);
        hakutulosData.selectAll();
    }

}
