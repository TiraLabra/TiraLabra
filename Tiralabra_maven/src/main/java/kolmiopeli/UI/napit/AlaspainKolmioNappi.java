
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
public class AlaspainKolmioNappi extends JButton implements KolmioNappi {

    private boolean onkoValittuna;
    private Kolmio napinKolmio;
        private Peliruudukko peliruudukko;

    public AlaspainKolmioNappi(Kolmio napinKolmio, Peliruudukko peliruudukko) {
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

    @Override
    public void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(getBackground());
        } else {
            g.setColor(getBackground());
        }
        int xPoints[] = {getSize().width / 2, 0, getSize().width};
        int yPoints[] = {kolmionKorkeus(getSize().width), 0, 0};
        g.fillPolygon(xPoints, yPoints, xPoints.length);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        int xPoints[] = {getSize().width / 2, 0, getSize().width};
        int yPoints[] = {kolmionKorkeus(getSize().width), 0, 0};
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
        return (int) (Math.sqrt(3) / 2 * a);
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
    public Kolmio getNapinKolmio() {
        return napinKolmio;
    }
}
