package labyrintti;

import java.util.Scanner;
import javax.swing.SwingUtilities;
import labyrintti.gui.Kayttoliittyma;
import labyrintti.osat.Pohja;
import labyrintti.sovellus.Etsija;

/**
 * Alustaa tarvittavat luokat ja käynnistää ohjelman.
 *
 * @author heidvill
 */
public class Kaynnistys {

    /**
     * Etsii kartasta lyhimmän reitin.
     */
    private Etsija etsija;
    /**
     * Sovelluksen käyttöliittymä.
     */
    private Kayttoliittyma kali;
    /**
     * Pohjakartta, josta reitti lasketaan.
     */
    private Pohja pohja;

    public Kaynnistys() {
        pohja = new Pohja();
        pohja.alustaPohja1("src/main/java/labyrintti/osat/kartta2.txt");
        etsija = new Etsija(pohja);
        kali = new Kayttoliittyma(this, 40);
//        etsija.setPiirtoalusta(kali.getPiirtoalusta());
    }

    /**
     * Käynnistää käyttöliittymän.
     */
    public void kaynnista() {
//        int korkeus = kysyKayttajalta("Anna kartan korkeus: ");
//        int leveys = kysyKayttajalta("Anna kartan leveys: ");
//        String syote = "";
//        Scanner lukija = new Scanner(System.in);
//        String rivi = "";
//        int mones = 0;
//        System.out.println("Syötä kartta: ");
//        System.out.println("Merkitse lähtö kirjaimella L ja maali kirjaimella M");
//        while (true) {
//            rivi = lukija.nextLine();
//            mones++;
//            if (rivi.length()!=leveys) {
//                mones--;
//                System.out.println("Rivi on liian lyhyt/pitkä, olet syöttänyt tähän mennessä " + mones + " riviä, jotka ovat");
//                System.out.println("(Jatka rivien syöttöä tästä)");
//                for (int i = 0; i < mones; i++) {
//                    String mj = syote.substring(i * leveys, i * leveys + leveys);
//                    System.out.println(mj);
//                }
//            } else {
//                syote += rivi;
//            }
//            if (syote.length() == korkeus * leveys) {
//                if (syote.contains("L") && syote.contains("M")) {
//                    break;
//                } else {
//                    System.out.println("Et antanut maalia ja/tai lähtöä, syötä kartta uudelleen");
//                    syote = "";
//                }
//            }
//        }
//        pohja = new Pohja(korkeus, leveys, syote);
//
//        etsija = new Etsija(pohja);
//        kali = new Kayttoliittyma(this, 40);

        SwingUtilities.invokeLater(kali);

        while (kali.getPiirtoalusta() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }
    }

    private int kysyKayttajalta(String kysymys) {
        int luku = 0;
        Scanner lukija = new Scanner(System.in);
        System.out.print(kysymys);
        while (true) {
            luku = lukija.nextInt();
            if (luku > 0 && luku < 51) {
                break;
            } else {
                System.out.println("Luvun pitää olla välillä 1 - 50");
            }
        }
        return luku;
    }

    public Pohja getPohja() {
        return pohja;
    }

    public Etsija getEtsija() {
        return etsija;
    }
//    public void maaritaAlkuarvot() {
//        int korkeus = 0;
//        int leveys = 0;
//    }
//
//    private void kysyAlkuarvot(String kysymys) {
//    }
}
