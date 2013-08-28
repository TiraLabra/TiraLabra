package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.*;
import javax.swing.*;
import rakenteet.*;
import suunnistajat.SuunnistajaAStar;
import suunnistajat.SuunnistajaDFS;
import verkko.Labyrintti;
import verkko.Solmu;

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
            try {
                aStar = new SuunnistajaAStar(laby.verkko[1][1], laby.verkko[17][19], laby);
                dfs = new SuunnistajaDFS(laby.verkko[1][1], laby.verkko[17][19], laby);
            } catch (Exception ex) {
                kuva.setText("Asettamasi alku- tai maalipiste on labyrintin ulkopuolella.");
            }
        } else if ("AStar".equals(e.getActionCommand())) {
            suunnistajaAStar = true;
        } else if ("DFS".equals(e.getActionCommand())) {
            suunnistajaAStar = false;
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