


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import rakenteet.Jarjestysjono;

/**
 *
 * @author maef
 */
public class Kuuntelija implements ActionListener{
    
    private JButton nappi;
    private JLabel kuva;
    private Labyrintti laby;
    private SuunnistajaAStar suunnistaja;
    

    /**
     *
     * @param nappi
     */
    public Kuuntelija(JButton nappi, JLabel kuva, Labyrintti laby) {
        this.nappi = nappi;
        this.kuva = kuva;
        this.laby = laby;
        suunnistaja = new SuunnistajaAStar(laby.verkko[3][3], laby.verkko[90][68], laby); 
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
       Graphics g = kuva.getGraphics();
       g.setColor(Color.red);
       Jarjestysjono<Solmu> polku = suunnistaja.etsi(g);
        for (int i=0; i<polku.size(); i++) {
            g.drawRect(polku.get(i).getX(), polku.get(i).getY(), 1, 1);
        }
    }
}