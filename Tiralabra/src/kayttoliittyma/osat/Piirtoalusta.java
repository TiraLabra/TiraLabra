package kayttoliittyma.osat;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tyokalut.AVLsolmu;

/**
 * Lavan mallinnuksen piirtämisestä huolehtiva luokka.
 * 
 * @author albis
 */
public class Piirtoalusta extends JPanel {
    AVLsolmu solmu;
    int[][] asettelu;
    
    public Piirtoalusta(AVLsolmu solmu) {
        super.setBackground(new Color(156, 93, 82));
        
        this.solmu = solmu;
        asettelu = solmu.getAsettelu();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int monesko = 1;
        
        int lavanPituus = solmu.getLava().getPituus();
        int lavanLeveys = solmu.getLava().getLeveys();
        
        this.setSize(lavanLeveys, lavanPituus);
        
        while (true) {
            int alkuX = etsiAlkuX(monesko);
            int alkuY = etsiAlkuY(monesko);
            
            if (alkuX == -1) {
                break;
            }
            
            int laatikonLeveys = haeLeveys(alkuX, alkuY, monesko);
            int laatikonPituus;
            if (laatikonLeveys == solmu.getLaatikko().getLeveys()) {
                laatikonPituus = solmu.getLaatikko().getPituus();
            } else {
                laatikonPituus = solmu.getLaatikko().getLeveys();
            }
            
            g.setColor(Color.BLACK);
            g.fill3DRect(alkuX, alkuY, laatikonLeveys, laatikonPituus, true);
            g.setColor(Color.BLUE);
            g.draw3DRect(alkuX, alkuY, laatikonLeveys, laatikonPituus, true);
            
            monesko++;
        }
    }
    
    /**
     * Mittaa annetun numeroisen laatikon lavalta leveyssuunnassa vievän tilan.
     * 
     * @param alkuX Laatikon vasemman yläkulman sijainti x-akselilla.
     * @param alkuY Laatikon vasemman yläkulman sijainti y-akselilla.
     * @param monesko Mitattavan laatikon numero.
     * @return Palauttaa kokonaisluvun, joka kertoo laatikon vievän tilan.
     */
    private int haeLeveys(int alkuX, int alkuY, int monesko) {
        int leveys = 0;
        
        for (int i = alkuX; i < asettelu[0].length; i++) {
            if (asettelu[alkuY][i] != monesko) {
                break;
            }
            leveys++;
        }
        
        return leveys;
    }
    
    /**
     * Etsii annetun numeroisen laatikon vasemman ylänurkan sijainnin x-akselilla.
     * 
     * @param monesko Etsittävän laatikon numero
     * @return Palauttaa laatikon vasemman yläkulman sijainnin x-akselilla.
     */
    private int etsiAlkuX(int monesko) {
        for (int i = 0; i < asettelu.length; i++) {
            for (int j = 0; j < asettelu[0].length; j++) {
                if (asettelu[i][j] == monesko) {
                    return j;
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Etsii annetun numeroisen laatikon vasemman ylänurkan sijainnin y-akselilla.
     * 
     * @param monesko Etsittävän laatikon numero
     * @return Palauttaa laatikon vasemman yläkulman sijainnin y-akselilla.
     */
    private int etsiAlkuY(int monesko) {
        for (int i = 0; i < asettelu.length; i++) {
            for (int j = 0; j < asettelu[0].length; j++) {
                if (asettelu[i][j] == monesko) {
                    return i;
                }
            }
        }
        
        return -1;
    }
}
