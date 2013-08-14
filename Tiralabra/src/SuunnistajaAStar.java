
import java.util.*;
import rakenteet.Jarjestysjono;


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
    private Jarjestysjono<Solmu> nykyiset = new Jarjestysjono();
    private ArrayList<Solmu> polku = new ArrayList();


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
    public ArrayList<Solmu> etsi() { //Ei toimi oikein.
        Solmu kasiteltava = alku;
        int matka = 1;
        
        if(alku.seina || maali.seina) {
            return null;
        }

        while ((kasiteltava.getX() != maali.getX()) || (kasiteltava.getY() != maali.getY())) {

            
            
            kasiteltava = nykyiset.peek();
            maaritaNaapurit(kasiteltava, matka);

            if (nykyiset.isEmpty() ) {
            // Lopetetahan! Implementoi.
            }
                Solmu solmu = nykyiset.poll();
                if (!kasitelty.contains(solmu)) {
                    kasitelty.add(solmu);
                }
//                System.out.println("nykyiset: " + nykyiset);
//                System.out.println("Solmun a X-arvo on " + kasiteltava.getX() + " ja sen Y-arvo on " + kasiteltava.getY());
            }
            matka++;
            nykyiset.clear();
            nykyiset.add(kasiteltava);
            return muodostaPolku();
        }

        
    

    /*
     * Heuristiikka riippuu siitä, edustaako solu sokkelon seinää vaiko ei.
     */
    private int heuristiikka(Solmu x) {
        if (x.seina) {
            return 1000000+Math.abs((x.getX() - maali.getX()) + (x.getY() - maali.getY()));
        }
        return Math.abs((x.getX() - maali.getX()) + (x.getY() - maali.getY()));
    }
    
    private void maaritaNaapurit(Solmu kasiteltava, int matka) {
        System.out.println("Käsiteltävä: "+kasiteltava);
        for (int i = -1; i <= 1; i += 2) {
                if (kasiteltava.vierusX(i) != null) {

                    if (kasiteltava.vierusX(i).getAlkuarvo() == Integer.MAX_VALUE){
                        kasiteltava.vierusX(i).setAlkuarvo(matka);
                        
                    }
                }       
                
                if (!kasitelty.contains(kasiteltava.vierusX(i))) {
                    nykyiset.add(kasiteltava.vierusX(i));
                }
                System.out.println("X-naapuri: "+kasiteltava.vierusX(i));
                if (kasiteltava.vierusY(i) != null) {
                    
                    if (kasiteltava.vierusY(i).getAlkuarvo() == Integer.MAX_VALUE){
                        kasiteltava.vierusY(i).setAlkuarvo(matka);
                        
                    }
                    
                    if (!kasitelty.contains(kasiteltava.vierusY(i))) {
                    nykyiset.add(kasiteltava.vierusY(i));
                }
                    System.out.println("Y-naapuri: "+kasiteltava.vierusY(i));
                    //System.out.println("Jono: "+nykyiset.toString());
                }
            }
    }

    private ArrayList<Solmu> muodostaPolku() { //Tämä ei todellakaan ole oikein, mutta sillä ei ole väliä ennen kuin ohjelma löytää sinne!
        for (Solmu solmu : kasitelty) {
            if (!polku.contains(solmu)) {
                polku.add(solmu);
            }
        }

        return polku;
    }
}
