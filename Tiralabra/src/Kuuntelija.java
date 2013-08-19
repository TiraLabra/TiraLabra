


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import rakenteet.*;

/**
 *
 * @author maef
 */
public class Kuuntelija implements ActionListener{
    
    private JButton nappi;
    private JLabel kuva;
    private Labyrintti laby;
    private SuunnistajaAStar aStar;
    private SuunnistajaDFS dfs;
    

    /**
     *
     * @param nappi
     */
    public Kuuntelija(JButton nappi, JLabel kuva, Labyrintti laby) {
        this.nappi = nappi;
        this.kuva = kuva;
        this.laby = laby;
        aStar = new SuunnistajaAStar(laby.verkko[1][1], laby.verkko[17][19], laby);
        dfs = new SuunnistajaDFS(laby.verkko[1][1], laby.verkko[17][19], laby);
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
       Graphics g = kuva.getGraphics();
//       Jarjestysjono<Solmu> polku = aStar.etsi(g);
       Lista<Solmu> polku = dfs.etsi(g);
       g.setColor(Color.red);
        for (int i=0; i<polku.size(); i++) {
            g.fillRect(polku.get(i).getX()*15, polku.get(i).getY()*15, 15, 15);
        }
    }
}