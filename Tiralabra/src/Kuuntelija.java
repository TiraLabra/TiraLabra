


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
    private JButton valitse;
    private JTextField labyrintti;
    private JLabel kuva;
    private Labyrintti laby;
    private SuunnistajaAStar aStar;
    private SuunnistajaDFS dfs;
    private SuunnistajaDijkstra dijkstra;
    

    /**
     *
     * @param nappi
     */
    public Kuuntelija(JButton nappi, JButton valitse, JTextField labyrintti, JLabel kuva, Labyrintti laby) {
        this.nappi = nappi;
        this.kuva = kuva;
        this.laby = laby;
        this.valitse = valitse;
        this.labyrintti = labyrintti;
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(valitse)) {
            laby = new Labyrintti(labyrintti.getText());
            Image sokkelo = laby.haeLaby();
            kuva.setIcon(new ImageIcon(sokkelo.getScaledInstance(300, 300, 0)));
            aStar = new SuunnistajaAStar(laby.verkko[1][1], laby.verkko[17][19], laby);
            dfs = new SuunnistajaDFS(laby.verkko[1][1], laby.verkko[17][19], laby);
            dijkstra = new SuunnistajaDijkstra(laby.verkko[1][1], laby.verkko[17][19], laby);
        }
        
       else {
       Graphics g = kuva.getGraphics();
       Jarjestysjono<Solmu> polku = aStar.etsi(g);
//       Lista<Solmu> polku = dijkstra.etsi(g);
       g.setColor(Color.red);
        for (int i=0; i<polku.size(); i++) {
            g.fillRect(polku.get(i).getX()*15, polku.get(i).getY()*15, 15, 15);
        }
        }
    }
}