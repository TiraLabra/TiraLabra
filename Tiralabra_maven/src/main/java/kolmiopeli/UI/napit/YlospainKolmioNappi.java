
package kolmiopeli.UI.napit;

import java.awt.*;
import javax.swing.JButton;
import kolmiopeli.UI.Peliruudukko;
import kolmiopeli.domain.Kolmio;

/**
 *
 * @author Eemi
 */

// Source: http://harryjoy.com/2011/08/21/different-button-shapes-in-swing/
public class YlospainKolmioNappi extends JButton implements KolmioNappi {
    private boolean onkoValittuna;
    private Kolmio napinKolmio;
    private Peliruudukko peliruudukko;
    
    public YlospainKolmioNappi(Kolmio napinKolmio, Peliruudukko peliruudukko) {
        super();
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setBackground(napinKolmio.getKolmionVari());
        setPreferredSize(size);
        setContentAreaFilled(false);
        onkoValittuna = false;
        this.napinKolmio = napinKolmio;
        this.peliruudukko = peliruudukko;
    }


    public Kolmio getNapinKolmio() {
        return napinKolmio;
    }

    
    
    
    @Override
    public boolean isOnkoValittuna() {
        return onkoValittuna;
    }

    @Override
    public void setOnkoValittuna(boolean onkoValittuna) {
        this.onkoValittuna = onkoValittuna;
    }

    
    
    
    
    @Override
    public void paintComponent(Graphics g) {
        
        if (getModel().isPressed()) {
            g.setColor(getBackground());
            setOnkoValittuna(true);
        } else {
            g.setColor(getBackground());
            setOnkoValittuna(false);
        }
        int xPoints[] = {getSize().width / 2, 0, getSize().width};
        int yPoints[] = {0, kolmionKorkeus(getSize().width), kolmionKorkeus(getSize().width)};
        g.fillPolygon(xPoints, yPoints, xPoints.length);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        int xPoints[] = {getSize().width / 2, 0, getSize().width};
        int yPoints[] = {0, kolmionKorkeus(getSize().width), kolmionKorkeus(getSize().width)};
        g.drawPolygon(xPoints, yPoints, xPoints.length);
    }
    Polygon polygon;

    @Override
    public boolean contains(int x, int y) {
        if (polygon == null || !polygon.getBounds().equals(getBounds())) {
            int xPoints[] = {getSize().width / 2, 0, getSize().width};
            int yPoints[] = {0, getSize().height, getSize().height};
            polygon = new Polygon(xPoints, yPoints, xPoints.length);
        }
        return polygon.contains(x, y);
    }

    private int kolmionKorkeus(int a) {
        return (int) (Math.sqrt(3)/2 * a);
    }
    
    
}
