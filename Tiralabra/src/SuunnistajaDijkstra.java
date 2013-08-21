
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import rakenteet.*;


/**
 *
 * @author maef
 */
public class SuunnistajaDijkstra {
     // Lähtöpiste
    private Solmu alku;
    //Maali
    private Solmu maali;
    //Missä suunnistetaan
    private Labyrintti laby;
    //Mihin tallennetaan
    private Lista<Solmu> kasitelty = new Lista();
    private Jarjestysjono<Solmu> nykyiset = new Jarjestysjono();
    private Lista<Solmu> polku = new Lista();

    public SuunnistajaDijkstra(Solmu alku, Solmu maali, Labyrintti laby) {
        this.alku = alku;
        this.maali = maali;
        this.laby = laby;
    }
    
    public Lista<Solmu> etsi(Graphics g){
        Solmu kasiteltava;
        alku.setAlkuarvo(0);
        if (alku.seina || maali.seina) {
            return null;
        }
        
        g.setColor(Color.blue);
        g.drawRect(maali.getX()*15, maali.getY()*15, 15, 15);
        
        nykyiset.add(alku);
        
        while (!kasitelty.contains(maali)) {
            
            
        }
        return muodostaPolku();
    }
    
    private void relax(Solmu kasiteltava, Jarjestysjono<Solmu> naapurit, Labyrintti laby){
        for (int i = 0; i < naapurit.size(); i++) {
                if (naapurit.get(i).getAlkuarvo() >= kasiteltava.getAlkuarvo() + laby.etaisyys(naapurit.get(i))) {
                    naapurit.get(i).setAlkuarvo(kasiteltava.getAlkuarvo() + laby.etaisyys(naapurit.get(i)));
                    naapurit.get(i).setPolku(kasiteltava);
                }
            } 
    }
    
    private Jarjestysjono<Solmu> maaritaNaapurit(Solmu kasiteltava) {
        Jarjestysjono<Solmu> naapurit = new Jarjestysjono();
        for (int i = -1; i <= 1; i += 2) {
            if (kasiteltava.vierusX(i) != null) {

                if (kasiteltava.vierusX(i).getAlkuarvo() == Integer.MAX_VALUE) {
                    kasiteltava.vierusX(i).setAlkuarvo(kasiteltava.getAlkuarvo() + 1);

                }
            }

            if (!kasitelty.contains(kasiteltava.vierusX(i)) && kasiteltava.vierusX(i) !=null) {
//                nykyiset.add(kasiteltava.vierusX(i));
                naapurit.add(kasiteltava.vierusX(i));
            }
            if (kasiteltava.vierusY(i) != null) {

                if (kasiteltava.vierusY(i).getAlkuarvo() == Integer.MAX_VALUE) {
                    kasiteltava.vierusY(i).setAlkuarvo(kasiteltava.getAlkuarvo() + 1);

                }

                if (!kasitelty.contains(kasiteltava.vierusY(i)) && kasiteltava.vierusY(i) != null) {
//                    nykyiset.add(kasiteltava.vierusY(i));
                    naapurit.add(kasiteltava.vierusY(i));
                }
            }
        }
        return naapurit;
    }

    private Lista<Solmu> muodostaPolku() {
        Solmu s = maali;
        polku.add(s);
        while (s.getPolku() != null) {
            polku.add(s.getPolku());
            s = s.getPolku();
        }
        return polku;
    }
}
