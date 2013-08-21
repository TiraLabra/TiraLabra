
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import rakenteet.*;


/**
 * 
 * @author maef
 */
public class SuunnistajaDFS{ //Vaatii vielä työstämistä...
     // Lähtöpiste
    private Solmu alku;
    //Maali
    private Solmu maali;
    //Missä suunnistetaan
    private Labyrintti laby;
    //Polku
    private Lista<Solmu> polku = new Lista();
    private Lista<Solmu> vierus = new Lista();
    private int matka = 0;

    public SuunnistajaDFS(Solmu alku, Solmu maali, Labyrintti laby) {
        this.alku = alku;
        this.maali = maali;
        this.laby = laby;
        
    }
    
    public Lista<Solmu> etsi(Graphics g) {
        g.setColor(Color.blue);
        DFSVisit(alku, null, laby, g);
        
        
        
       return getPolku();
    }
    
    private Lista<Solmu> getPolku() {
        Solmu s = maali;
        polku.add(s);
        while (s.getPolku() != null) {
            polku.add(s.getPolku());
            s = s.getPolku();
        }
        return polku;
    }

    private void DFSVisit(Solmu solmu, Solmu edellinen, Labyrintti laby, Graphics g) {
        try {
            Thread.sleep(70);
        } catch (InterruptedException ex) {
            Logger.getLogger(SuunnistajaDFS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        solmu.setAlkuarvo(matka);
//        solmu.setPolku(edellinen);
        
        if (edellinen!=null) {
        if (solmu.getAlkuarvo()>=edellinen.getAlkuarvo()+laby.etaisyys(edellinen)) {
            solmu.setAlkuarvo(edellinen.getAlkuarvo()+laby.etaisyys(edellinen));
            solmu.setPolku(edellinen);
        }
        }
        
        g.drawRect(solmu.getX()*15, solmu.getY()*15, 15, 15);
        kerroVierusX(solmu);
        
        if (solmu.getX()==maali.getX() && solmu.getY() == maali.getY()) {
            polku = getPolku();
        }
        
        for (int i = 0; i < vierus.size(); i++) {
            if (vierus.get(i).getAlkuarvo() == Integer.MAX_VALUE) {
                matka++;
                DFSVisit(vierus.get(i), solmu, laby, g);
            }
        }
    }

    private void kerroVierusX(Solmu solmu) {
        vierus.clear();
        
        for (int i = -1; i <=1; i+=2) {
            if (solmu.vierusX(i)!=null) {
            vierus.add(solmu.vierusX(i));
            }
            if (solmu.vierusY(i) != null) {
            vierus.add(solmu.vierusY(i));
            }
        }
    }

    
    
    
}
