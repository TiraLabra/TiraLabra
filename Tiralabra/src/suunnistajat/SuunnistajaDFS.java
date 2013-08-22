package suunnistajat;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import rakenteet.*;
import verkko.Labyrintti;
import verkko.Solmu;

/**
 *
 * @author maef Jos tätä ei saada toimimaan ajoissa, se heitetään romukoppaan.
 */
public class SuunnistajaDFS implements Suunnistaja { //Vaatii vielä työstämistä...
    // Lähtöpiste

    private Solmu alku;
    //Maali
    private Solmu maali;
    //Missä suunnistetaan
    private Labyrintti laby;
    //Polku
    private Lista<Solmu> polku = new Lista();
    private Keko<Solmu> keko = new Keko();

    public SuunnistajaDFS(Solmu alku, Solmu maali, Labyrintti laby) {
        this.alku = alku;
        this.maali = maali;
        this.laby = laby;

    }

    @Override
    public Lista<Solmu> etsi(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect(maali.getX() * (300 / laby.getWidth()), maali.getY() * (300 / laby.getHeight()), 300 / laby.getWidth(), 300 / laby.getHeight());
        DFSVisit(alku, null, laby, g);
        tulostaKeko();

        return muodostaPolku();
    }

    private Lista<Solmu> muodostaPolku() {
        Keko<Solmu> kaannetty = new Keko<Solmu>();
        Solmu s;
        while ((s = keko.poll()) != null) {
            kaannetty.put(s);
        }
        while ((s = kaannetty.poll()) != null) {
            polku.add(s);
        }
        return polku;
    }

    private boolean DFSVisit(Solmu solmu, Solmu edellinen, Labyrintti laby, Graphics g) {

        try {
            Thread.sleep(70);
        } catch (InterruptedException ex) {
            Logger.getLogger(SuunnistajaDFS.class.getName()).log(Level.SEVERE, null, ex);
        }
        keko.put(solmu);

        if (edellinen == null) {
            solmu.setAlkuarvo(0);
        } else {
            solmu.setAlkuarvo(edellinen.getAlkuarvo() + 1);
        }

        g.setColor(Color.RED);
        g.fillRect(solmu.getX() * 300 / laby.getWidth(), solmu.getY() * 300 / laby.getHeight(), 300 / laby.getWidth(), 300 / laby.getHeight());
        Lista<Solmu> vierus = kerroVierus(solmu);

        if (solmu.getX() == maali.getX() && solmu.getY() == maali.getY()) {
            return true;
        }
        boolean tulos = false;
        for (int i = 0; i < vierus.size(); i++) {
            if (vierus.get(i).getAlkuarvo() == Integer.MAX_VALUE) {
                tulos = DFSVisit(vierus.get(i), solmu, laby, g);
            }
            if (tulos) {
                return true;
            }
        }
        keko.poll();
        g.setColor(Color.WHITE);
        g.fillRect(solmu.getX() * 300 / laby.getWidth(), solmu.getY() * 300 / laby.getHeight(), 300 / laby.getWidth(), 300 / laby.getHeight());
        g.setColor(Color.CYAN);

        g.drawRect(solmu.getX() * 300 / laby.getWidth(), solmu.getY() * 300 / laby.getHeight(), 300 / laby.getWidth(), 300 / laby.getHeight());
        return false;
    }

    private Lista<Solmu> kerroVierus(Solmu solmu) {
        Lista<Solmu> vierus = new Lista<Solmu>();
        for (int i = -1; i <= 1; i += 2) {
            if (solmu.vierusX(i) != null && !solmu.vierusX(i).seina) {
                vierus.add(solmu.vierusX(i));
            }
            if (solmu.vierusY(i) != null && !solmu.vierusY(i).seina) {
                vierus.add(solmu.vierusY(i));
            }
        }
        return vierus;
    }

    private void tulostaKeko() {
        Keko<Solmu> kaannetty = new Keko<Solmu>();
        Solmu s;
        while ((s = keko.poll()) != null) {
            kaannetty.put(s);
        }
        while ((s = kaannetty.poll()) != null) {
            System.out.println(s);
        }

    }
}
