package labyrintti.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import labyrintti.Kaynnistys;
import labyrintti.osat.Pohja;
import labyrintti.osat.Ruutu;

/**
 *
 * @author heidvill@cs
 */
public class Piirtoalusta extends JPanel {

    private Kaynnistys kaynnistys;
    private int sivu;
    private Pohja pohja;

    public Piirtoalusta(Kaynnistys kaynnistys, int sivu) {
        this.kaynnistys = kaynnistys;
        this.sivu = sivu;
        pohja = kaynnistys.getPohja();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < pohja.getKorkeus(); i++) {
            for (int j = 0; j < pohja.getLeveys(); j++) {
                piirraRuutu(g, pohja.getRuutu(i, j));
            }
        }
    }

    private void piirraRuutu(Graphics g, Ruutu r) {
        if (r.getArvo() == 9) {
            g.setColor(Color.BLUE);
        } else if(lahtoTaiMaali(r.getX(), r.getY())){
            g.setColor(Color.RED);
        }else {
            Color vari = new Color(0, 255-r.getArvo() * 20, 0);
            g.setColor(vari);
        }
        g.fill3DRect(r.getY() * sivu, r.getX() * sivu, sivu, sivu, true);
    }
    
    private boolean lahtoTaiMaali(int i, int j){
        if(i==pohja.getLahtoX() && j == pohja.getLahtoY()){
            return true;
        }
        return false;
    }

    /**
     * Päivittää piirretyn kuvan.
     */
    public void paivita() {
        repaint();
    }
}
