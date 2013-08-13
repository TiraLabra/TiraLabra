
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
        a.setAlkuarvo(0);
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
        int matka = 1;
        
        if(alku.seina) {
            return null;
        }

        while ((kasiteltava.getX() != maali.getX()) || (kasiteltava.getY() != maali.getY())) {

            for (int i = -1; i <= 1; i += 2) {
                if (kasiteltava.vierusX(i) != null) {

                    if (kasiteltava.vierusX(i).getAlkuarvo() == Integer.MAX_VALUE){
                        kasiteltava.vierusX(i).setAlkuarvo(matka);
                    } else {
                        kasiteltava.vierusX(i).setAlkuarvo(matka+kasiteltava.vierusX(i).getAlkuarvo());
                    }
                    
                    kasiteltava.vierusX(i).setHeuristiikka(heuristiikka(kasiteltava.vierusX(i)));
                    nykyiset.add(kasiteltava.vierusX(i));
                }
                if (kasiteltava.vierusY(i) != null) {
                    
                    if (kasiteltava.vierusY(i).getAlkuarvo() == Integer.MAX_VALUE){
                        kasiteltava.vierusY(i).setAlkuarvo(matka);
                    } else {
                        kasiteltava.vierusY(i).setAlkuarvo(matka+kasiteltava.vierusY(i).getAlkuarvo());
                    }
                    
                    kasiteltava.vierusY(i).setHeuristiikka(heuristiikka(kasiteltava.vierusY(i)));
                    nykyiset.add(kasiteltava.vierusY(i));
                }
            }
            
            kasiteltava = nykyiset.peek();

            while ( !nykyiset.isEmpty() ) {
                Solmu solmu = nykyiset.poll();
                if (!kasitelty.contains(solmu)) {
                    kasitelty.add(solmu);
                }
                System.out.println("nykyiset: " + nykyiset);
                System.out.println("Solmun a X-arvo on " + kasiteltava.getX() + " ja sen Y-arvo on " + kasiteltava.getY());
            }
            matka++;
            nykyiset.clear();
            nykyiset.add(kasiteltava);
        }

        return muodostaPolku();
    }

    /*
     * Heuristiikka riippuu siitä, edustaako solu sokkelon seinää vaiko ei.
     */
    private int heuristiikka(Solmu x) {
        if (x.seina) {
            return 1000000+Math.abs((x.getX() - alku.getX()) + (x.getY() - alku.getY())) + Math.abs((x.getX() - maali.getX()) + (x.getY() - maali.getY()));
        }
        return Math.abs((x.getX() - alku.getX()) + (x.getY() - alku.getY())) + Math.abs((x.getX() - maali.getX()) + (x.getY() - maali.getY()));
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
