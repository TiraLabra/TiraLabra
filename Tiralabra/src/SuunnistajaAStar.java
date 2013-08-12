
import java.util.*;


/*
 * Tänne ohjelmoidaan verkon läpikäyvä A*-algoritmi.
 */
/**
 *
 * @author maef
 */
public class SuunnistajaAStar {

    // Lähtöpiste
    private Solmu alku;
    //Maali
    private Solmu maali;
    //Missä suunnistetaan
    private Labyrintti laby;
    //Mihin tallennetaan
    private ArrayList<Solmu> kasitelty = new ArrayList();
    private PriorityQueue<Solmu> nykyiset = new PriorityQueue();
    private PriorityQueue<Solmu> polku = new PriorityQueue();


    public SuunnistajaAStar(Solmu a, Solmu b, Labyrintti laby) {
        
        this.alku = a;
        this.maali = b;
        this.laby = laby;

        a.setHeuristiikka(heuristiikka(a));
        nykyiset.add(a);
        polku.add(a);
        
        for (int i = 0; i < laby.getHeight(); i++) {
            for (int j = 0; j < laby.getWidth(); j++) {
                Solmu s = laby.verkko[i][j];
                s.setHeuristiikka(heuristiikka(s));
            }
        }
    }

    /**
     *
     * @return Etsii ja palauttaa lyhimmän polun.
     */
    public PriorityQueue<Solmu> etsi() {  //Ei toimi. Päätyy kiertämään kehää.
        Solmu kasiteltava = alku;

        while ((kasiteltava.getA() != maali.getA()) || (kasiteltava.getB() != maali.getB())) {

            if (laby.etaisyys(kasiteltava) == 1000000) {
                return null;
            }
            for (int i = -1; i <= 1; i += 2) {
                if (kasiteltava.vierusX(i) != null) {
                    kasiteltava.vierusX(i).setHeuristiikka(heuristiikka(kasiteltava.vierusX(i)));
                    nykyiset.add(kasiteltava.vierusX(i));
                }
                if (kasiteltava.vierusY(i) != null) {
                    kasiteltava.vierusY(i).setHeuristiikka(heuristiikka(kasiteltava.vierusY(i)));
                    nykyiset.add(kasiteltava.vierusY(i));
                }
            }
            
            kasiteltava = nykyiset.peek();

            while ( nykyiset.size() > 0) { //Ongelma on yhteydessä tähän lauseeseen, muttei välttämättä johdu siitä.
                Solmu solmu = nykyiset.poll();
//                if (laby.etaisyys(solmu) + heuristiikka(solmu) <= laby.etaisyys(kasiteltava) + heuristiikka(kasiteltava)) {
//                    kasiteltava = solmu;
//                }
//                kasiteltava = solmu;
                if (!kasitelty.contains(solmu)) {
                    kasitelty.add(solmu);
                }
                System.out.println("nykyiset: " + nykyiset);
                System.out.println("Solmun a X-arvo on " + kasiteltava.getA() + " ja sen Y-arvo on " + kasiteltava.getB());
            }
            nykyiset.clear();
            nykyiset.add(kasiteltava);
        }

        return muodostaPolku();
    }

    private int heuristiikka(Solmu x) {
        return Math.abs((x.getA() - alku.getA()) + (x.getB() - alku.getB())) + Math.abs((x.getA() - maali.getA()) + (x.getB() - maali.getB()));
    }

    private PriorityQueue<Solmu> muodostaPolku() { //Tämä ei todellakaan ole oikein, mutta sillä ei ole väliä ennen kuin ohjelma löytää sinne!
        for (Solmu solmu : kasitelty) {
            if (!polku.contains(solmu)) {
                polku.add(solmu);
            }
        }

        return polku;
    }
}
