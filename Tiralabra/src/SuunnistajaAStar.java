
import java.util.ArrayList;


/*
 * Tänne ohjelmoidaan verkon läpikäyvä A*-algoritmi.
 */

/**
 *
 * @author maef
 */
public class SuunnistajaAStar {

    // Lähtöpiste
    private Solmu a;
    //Maali
    private Solmu b;
    //Missä suunnistetaan
    private Labyrintti laby;

    public SuunnistajaAStar(Solmu a, Solmu b, Labyrintti laby) {
        this.a = a;
        this.b = b;
        this.laby = laby;
    }
    /**
     * 
     * @return 
     * Etsii ja palauttaa lyhimmän polun.
     */
    public ArrayList<Solmu> etsi() {  //Ei toimi. Juuttuu silmukkaan.
        ArrayList<Solmu> kasitelty = new ArrayList();
        ArrayList<Solmu> nykyiset = new ArrayList();
        ArrayList<Solmu> polku = new ArrayList();
        nykyiset.add(a);
        polku.add(a);
        int matka = 0;
        
        
        while (true) {
            
            if (laby.etaisyys(a) == 1000000){
            return null;
        }
            
            if (a == b) {
                break;
            }
            
            for (int i = -1; i < 1; i+=2) {
                if (a.vierusX(i)!=null) {
                nykyiset.add(a.vierusX(i));
                }
                if (a.vierusY(i)!=null) {
                nykyiset.add(a.vierusY(i));
                }
            }
            
            for (int i = 0; i < nykyiset.size(); i++) {
                if (laby.etaisyys(nykyiset.get(i))+heuristiikka(nykyiset.get(i)) < laby.etaisyys(a)+heuristiikka(a)) {
                    a = nykyiset.get(i);
                    polku.add(a);
                }
                if (!kasitelty.contains(nykyiset.get(i))) {
                    kasitelty.add(nykyiset.get(i));
                }
            }
            nykyiset.clear();
            nykyiset.add(a);
        
        }
        return polku;
    }
    
    private int heuristiikka(Solmu x){
        return Math.abs((x.getA()-b.getA()) + (x.getB()-b.getB()));
    }
    
}
