package suunnistajat;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import rakenteet.*;
import verkko.Labyrintti;
import verkko.Solmu;

/**
 * Tänne ohjelmoidaan verkon läpikäyvä A*-algoritmi.
 *
 * @author maef
 */
public class SuunnistajaAStar implements Suunnistaja {

    // Lähtöpiste
    private Solmu alku;
    //Maali
    private Solmu maali;
    //Missä suunnistetaan
    private Labyrintti laby;
    //Mihin tallennetaan
    private Jarjestysjono<Solmu> kasitelty = new Jarjestysjono();
    private Jarjestysjono<Solmu> nykyiset = new Jarjestysjono();
    private Lista<Solmu> polku = new Lista();
    
    //Suorituskykytestauksen välimuuttujat. Näillä vältetään piirtämiseen kuluvan ajan huomioonotto.
    private long aikaValissa;
    private long aikaValissa2;
    private long valiaika = 0;

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
    @Override
    public void etsi(Graphics g) {
        long aikaAlussa = System.currentTimeMillis(); 
        
        Solmu kasiteltava;
        alku.setAlkuarvo(0);

        if (alku.seina || maali.seina) {
            return;
        }

        g.setColor(Color.blue);
        g.drawRect(maali.getX() * (300 / laby.getWidth()), maali.getY() * (300 / laby.getHeight()), 300 / laby.getWidth(), 300 / laby.getHeight());

        nykyiset.add(alku);
        while (!kasitelty.contains(maali)) {
            kasiteltava = nykyiset.poll();

            if (kasiteltava == null) {
                return;
            }
            
            aikaValissa = System.currentTimeMillis();
            g.drawRect(kasiteltava.getX() * 300 / laby.getWidth(), kasiteltava.getY() * 300 / laby.getHeight(), 300 / laby.getWidth(), 300 / laby.getHeight());
            try {
                Thread.sleep(70);
            } catch (InterruptedException ex) {
                Logger.getLogger(SuunnistajaAStar.class.getName()).log(Level.SEVERE, null, ex);
            }
            aikaValissa2 = System.currentTimeMillis();
            valiaika = valiaika + (aikaValissa2-aikaValissa);

            Jarjestysjono<Solmu> naapurit = maaritaNaapurit(kasiteltava);
            /*
             * 1) Otetaan lupaavin
             * 2) Lisätään sen naapurit nykyisiin
             * 3) Katsotaan lupaavin reitti nykyisestä naapurisolmuun ja lisätään sille solmulle poluksi nykyinen
             * 4) Pollataan seuraava nykyisistä.
             */

            kasitelty.add(kasiteltava);

            for (int i = 0; i < naapurit.size(); i++) {
                if (naapurit.get(i).getAlkuarvo() >= kasiteltava.getAlkuarvo() + laby.etaisyys(naapurit.get(i))) {
                    naapurit.get(i).setAlkuarvo(kasiteltava.getAlkuarvo() + laby.etaisyys(naapurit.get(i)));
                    naapurit.get(i).setPolku(kasiteltava);
                }
            }
            
        }

        polku = muodostaPolku();
        long aikaLopussa = System.currentTimeMillis();
        piirraPolku(laby, polku, g);

        System.out.println("Algoritmiin kului aikaa: "+((aikaLopussa-aikaAlussa)-valiaika)+"ms.");

    }

    /**
     * @return Heuristiikka riippuu siitä, edustaako solu sokkelon seinää vaiko
     * ei. Etäisyydet ovat kaikki Manhattan-etäisyyksiä.
     */
    private int heuristiikka(Solmu x) {
        return Math.abs((x.getX() - maali.getX())) + Math.abs(x.getY() - maali.getY());
    }

    /**
     * Kertoo, mitkä taulukon solut edustavat jonkin solun naapureita.
     * Sokkelossa voi liikkua vain pysty- ja vaakasuuntaan
     */
    private Jarjestysjono<Solmu> maaritaNaapurit(Solmu kasiteltava) {
        Jarjestysjono<Solmu> naapurit = new Jarjestysjono();
        for (int i = -1; i <= 1; i += 2) {
            if (kasiteltava.vierusX(i) != null) {

                if (kasiteltava.vierusX(i).getAlkuarvo() == Integer.MAX_VALUE) {
                    kasiteltava.vierusX(i).setAlkuarvo(kasiteltava.getAlkuarvo() + 1);

                }
            }

            if (!kasitelty.contains(kasiteltava.vierusX(i)) && kasiteltava.vierusX(i) != null && !kasiteltava.vierusX(i).seina) {
                nykyiset.add(kasiteltava.vierusX(i));
                naapurit.add(kasiteltava.vierusX(i));
            }
            if (kasiteltava.vierusY(i) != null) {

                if (kasiteltava.vierusY(i).getAlkuarvo() == Integer.MAX_VALUE) {
                    kasiteltava.vierusY(i).setAlkuarvo(kasiteltava.getAlkuarvo() + 1);

                }

                if (!kasitelty.contains(kasiteltava.vierusY(i)) && kasiteltava.vierusY(i) != null && !kasiteltava.vierusY(i).seina) {
                    nykyiset.add(kasiteltava.vierusY(i));
                    naapurit.add(kasiteltava.vierusY(i));
                }
            }
        }
        long aikaLopussa = System.currentTimeMillis();
        return naapurit;
    }

    private Lista<Solmu> muodostaPolku() {
        long aikaAlussa = System.currentTimeMillis();
        Solmu s = maali;
        polku.add(s);
        Keko<Solmu> keko = new Keko();

        while (s.getPolku() != null) {
            keko.put(s.getPolku());
            s = s.getPolku();
        }

        while ((s = keko.poll()) != null) {
            polku.add(s);
        }
        long aikaLopussa = System.currentTimeMillis();
        System.out.println("Polun muodostamiseen kului aikaa: "+(aikaLopussa-aikaAlussa)+"ms.");
        return polku;
    }

    private void piirraPolku(Labyrintti laby, Lista<Solmu> polku, Graphics g) {
        g.setColor(Color.RED);
        if (polku == null) {
            return;
        }
        for (int i = 0; i < polku.size(); i++) {
            g.fillRect(polku.get(i).getX() * 300 / laby.getWidth(), polku.get(i).getY() * 300 / laby.getHeight(), 300 / laby.getWidth(), 300 / laby.getHeight());
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                    Logger.getLogger(SuunnistajaAStar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
