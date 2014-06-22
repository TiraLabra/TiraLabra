package suorituskyky;

import apurakenteet.*;
import hakualgoritmit.AStar;
import heuristiikat.*;
import tietorakenteet.*;

/**
 * Apuluokka, jonka avulla voi suorittaa suorituskykytestasuksia.
 * Tarkoitettu muokattavaksi kooditasolla, ja ajettavaksi toistuvasti Netbeansista.
 */
public class AStarSuorituskyky {

    private static String kuvanimi  = "100x100.bmp";
    private static int alkurivi     = 0;
    private static int alkusarake   = 0;
    private static int loppurivi    = 88;
    private static int loppusarake  = 88;
    
    private static Heuristiikka h   = new Dijkstra();
    //private static Heuristiikka h   = new Manhattan();
    //private static Heuristiikka h   = new Euklidinen();
    
    private AStar as;
    
    public AStarSuorituskyky() {
        as = new AStar(h);
    }
    
    public void suorita() {
        
        Kuvalukija kl = new Kuvalukija(kuvanimi);
        
        Alue alue = new Alue(kl.muodostaAlue(), kl.getKorkeus(), kl.getLeveys());
        alue.setAlueenKuva(kl.getKuva());
        
        System.out.println(alue.toString());

        if (as.AStarHaku(alue, alue.getnode(alkurivi, alkusarake), alue.getnode(loppurivi,loppusarake))) {

            ArrayListOma reitti = as.kerroKuljettuReitti();
            System.out.println("\nKuljettu reitti: ("+ reitti.koko()+ " kpl)");
            for (int i = 0; i < reitti.koko(); i++) {
                Node n = (Node)reitti.palautaKohdasta(i);
                System.out.println(n.toString());
                n.toString();
            }
            System.out.println(alue.toString());
            System.out.println(as.yhteenveto());

            Kuvanayttaja kn = new Kuvanayttaja(kl.getKuva());
            kn.muodostaKuvaanPolku(reitti);
            kn.naytaKuva();
            
        } else {
            System.out.println("Hakua ei voitu suorittaa, tarkasta parametrit!");
            
            
        }
        
    }
    
}
