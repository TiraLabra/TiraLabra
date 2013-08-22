package kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tyokalut.AVLsolmu;

/**
 *
 * @author albis
 */
public class Piirtoalusta extends JPanel {
    AVLsolmu solmu;
    int[][] asettelu;
    
    public Piirtoalusta(AVLsolmu solmu, int korkeus) {
        super.setBackground(new Color(156, 93, 82));
        
        this.solmu = solmu;
        asettelu = solmu.getAsettelu();
        
        int leveys = korkeus / (solmu.getLava().getKorkeus() / solmu.getLava().getLeveys());
        super.setSize(leveys, korkeus);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        int monesko = 1;
        g.setColor(Color.BLACK);
        
        while (true) {
            int alkuX = etsiAlkuX(monesko);
            int alkuY = etsiAlkuY(monesko);
            
            if (alkuX == -1) {
                break;
            }
            
            int leveys = haeLeveys(alkuX, alkuY, monesko);
            int korkeus;
            if (leveys == solmu.getLaatikko().getLeveys()) {
                korkeus = solmu.getLaatikko().getKorkeus();
            } else {
                korkeus = solmu.getLaatikko().getLeveys();
            }
            
            g.fill3DRect(alkuX, alkuY, leveys, korkeus, true);
        }
    }
    
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
