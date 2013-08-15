
import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
import rakenteet.Jarjestysjono;
import rakenteet.Jarjestysjono;

/**
 * Tänne ohjelmoidaan verkon läpikäyvä A*-algoritmi.
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
    private Jarjestysjono<Solmu> kasitelty = new Jarjestysjono();
    private Jarjestysjono<Solmu> nykyiset = new Jarjestysjono();
    private Jarjestysjono<Solmu> polku = new Jarjestysjono();

    public SuunnistajaAStar(Solmu a, Solmu b, Labyrintti laby) {

        this.alku = a;
        a.setAlkuarvo(0);
        this.maali = b;
        this.laby = laby;
        a.setHeuristiikka(heuristiikka(a));
        

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
    public Jarjestysjono<Solmu> etsi(Graphics g) {
        Solmu kasiteltava;
        alku.setAlkuarvo(0);

        if (alku.seina || maali.seina) {
            return null;
        }
        
        g.setColor(Color.blue);
        g.drawRect(maali.getX(), maali.getY(), 1, 1);
        g.setColor(Color.red);
       
        nykyiset.add(alku);
        while (!kasitelty.contains(maali)) {
            kasiteltava = nykyiset.poll();
            
            g.drawRect(kasiteltava.getX(), kasiteltava.getY(), 1, 1);
            
            Jarjestysjono<Solmu> naapurit = maaritaNaapurit(kasiteltava);
            /*
             * 1) Otetaan lupaavin
             * 2) Lisätään sen naapurit nykyisiin
             * 3) Katsotaan lupaavin reitti nykyisestä naapurisolmuun ja lisätään sille solmulle poluksi nykyinen
             * 4) Pollataan seuraava nykyisistä.
             */
//            while(kasitelty.contains(nykyiset.peek())) {
//                nykyiset.poll();
//            }

            kasitelty.add(kasiteltava); //Kutsu huonoksi ajatukseksi kaiksessa rauhassa. Ilman tätä algoritmi jää jumiin.
//            for (int i = 0; i < nykyiset.size(); i++) {
//                kasitelty.add(nykyiset.poll());
//            }
//            maaritaNaapurit(kasiteltava);
//            kasiteltava = nykyiset.peek();
            for (int i = 0; i < naapurit.size(); i++) {
                if (naapurit.get(i).getAlkuarvo() > kasiteltava.getAlkuarvo() + laby.etaisyys(naapurit.get(i))) {
                    naapurit.get(i).setAlkuarvo(kasiteltava.getAlkuarvo() + laby.etaisyys(naapurit.get(i)));
                    naapurit.get(i).setPolku(kasiteltava);
                }
            }
        }
        return muodostaPolku();
    }

    /**
     * @return Heuristiikka riippuu siitä, edustaako solu sokkelon seinää vaiko
     * ei. Etäisyydet ovat kaikki Manhattan-etäisyyksiä.
     */
    private int heuristiikka(Solmu x) {
        if (x.seina) {
            return 1000000 + Math.abs((x.getX() - maali.getX())) + Math.abs(x.getY() - maali.getY());
        }
        return Math.abs((x.getX() - maali.getX())) + Math.abs(x.getY() - maali.getY());
    }

    /**
     * Kertoo, mitkä taulukon solut edustavat jonkin solun naapureita.
     * Sokkelossa voi liikkua vain pysty- ja vaakasuuntaan
     */
    private Jarjestysjono<Solmu> maaritaNaapurit(Solmu kasiteltava) {
        Jarjestysjono<Solmu> naapurit = new Jarjestysjono();
//        System.out.println("Käsiteltävä: " + kasiteltava);
        for (int i = -1; i <= 1; i += 2) {
            if (kasiteltava.vierusX(i) != null) {

                if (kasiteltava.vierusX(i).getAlkuarvo() == Integer.MAX_VALUE) {
                    kasiteltava.vierusX(i).setAlkuarvo(kasiteltava.getAlkuarvo() + 1);

                }
            }

            if (!kasitelty.contains(kasiteltava.vierusX(i))) {
                nykyiset.add(kasiteltava.vierusX(i));
                naapurit.add(kasiteltava.vierusX(i));
            }
            if (kasiteltava.vierusY(i) != null) {

                if (kasiteltava.vierusY(i).getAlkuarvo() == Integer.MAX_VALUE) {
                    kasiteltava.vierusY(i).setAlkuarvo(kasiteltava.getAlkuarvo() + 1);

                }

                if (!kasitelty.contains(kasiteltava.vierusY(i))) {
                    nykyiset.add(kasiteltava.vierusY(i));
                    naapurit.add(kasiteltava.vierusY(i));
                }
            }
        }
        return naapurit;
    }

    private Jarjestysjono<Solmu> muodostaPolku() {
        Solmu s = maali;
        polku.add(s);
        while (s.getPolku() != null) {
            polku.add(s.getPolku());
        }
        return polku;
    }
}
