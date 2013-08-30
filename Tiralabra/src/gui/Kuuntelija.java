package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import suunnistajat.SuunnistajaAStar;
import suunnistajat.SuunnistajaDFS;
import verkko.Labyrintti;

/**
 *
 * @author maef
 */
public class Kuuntelija implements ActionListener {

    private JButton nappi;
    private JButton valitse;
    private JTextField labyrintti;
    private JLabel kuva;
    private Labyrintti laby;
    private SuunnistajaAStar aStar;
    private SuunnistajaDFS dfs;
    private boolean suunnistajaAStar = true;
    private JTextField alkuX;
    private JTextField alkuY;
    private JTextField maaliX;
    private JTextField maaliY;

    /**
     *
     * @param nappi
     */
    public Kuuntelija(JButton nappi, JButton valitse, JTextField alkuX, JTextField alkuY, JTextField maaliX, JTextField maaliY, JTextField labyrintti, JLabel kuva, Labyrintti laby) {
        this.nappi = nappi;
        this.kuva = kuva;
        this.laby = laby;
        this.valitse = valitse;
        this.labyrintti = labyrintti;
        this.alkuX = alkuX;
        this.alkuY = alkuY;
        this.maaliX = maaliX;
        this.maaliY = maaliY;
        
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        kuva.setText("");
        if (e.getSource().equals(valitse)) {
            laby = new Labyrintti(labyrintti.getText());
            Image sokkelo = laby.haeLaby();
            kuva.setIcon(new ImageIcon(sokkelo.getScaledInstance(300, 300, 0)));

        } else if ("AStar".equals(e.getActionCommand())) {
            try {
            aStar = new SuunnistajaAStar(laby.verkko[Integer.parseInt(alkuY.getText())][Integer.parseInt(alkuX.getText())], laby.verkko[Integer.parseInt(maaliY.getText())][Integer.parseInt(maaliX.getText())], laby);
            suunnistajaAStar = true;
             } catch (Exception ex) {
                kuva.setText("Antamasi syöte ei ole kokonaisluku tai asettamasi alku- tai maalipiste on labyrintin ulkopuolella.");
            }
            
        } else if ("DFS".equals(e.getActionCommand())) {
            try {
            dfs = new SuunnistajaDFS(laby.verkko[Integer.parseInt(alkuY.getText())][Integer.parseInt(alkuX.getText())], laby.verkko[Integer.parseInt(maaliY.getText())][Integer.parseInt(maaliX.getText())], laby);
            suunnistajaAStar = false;
            } catch (Exception ex) {
                kuva.setText("Antamasi syöte ei ole kokonaisluku tai asettamasi alku- tai maalipiste on labyrintin ulkopuolella.");
            }
            
        } else {
            Graphics g = kuva.getGraphics();
            if (suunnistajaAStar) {
                aStar.etsi(g);
            } else {
                dfs.etsi(g);
            }
        }
    }
}