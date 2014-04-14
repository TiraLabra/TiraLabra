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
     * Testisyöte.
     */
    private String esim1 = "99992222L2"
            + "9999200002"
            + "9992202222"
            + "9992202222"
            + "9292202222"
            + "9292252222"
            + "2292555222"
            + "M299909999"
            + "0000555222";
    /**
     * Testisyöte.
     */
    private String esim2 = "L0000"
            + "99990"
            + "00000"
            + "09999"
            + "0000M";
    /**
     * Testisyöte.
     */
    private String esim3 = "9999999999999999M9"
            //            + "000000000000000900"
            //            + "000000000000000900"
            //            + "000000000000000000"
            //            + "000000999990000000"
            //            + "000000000090000000"
            //            + "000000000090000000"
            //            + "000000000090000000"
            //            + "000000000000000000"
            //            + "9L9999999999999999";
            + "111111111111111911"
            + "111111111111111911"
            + "111111111111111111"
            + "111111999991111111"
            + "111111111191111111"
            + "111111111191111111"
            + "111111111191111111"
            + "111111111111111111"
            + "9L9999999999999999";
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
    /**
     * Käyttäjän syötteen lukija
     */
    private Scanner lukija;

    public Kaynnistys() {
        pohja = new Pohja();
//        pohja.alustaPohja1("src/main/java/labyrintti/osat/kartta1.txt");
//        etsija = new Etsija(pohja);
//        kali = new Kayttoliittyma(this, 40);
        lukija = new Scanner(System.in);
    }

    /**
     * Kysyy käyttäjältä kartan ja käynnistää käyttöliittymän.
     */
    public void kaynnista() {
        System.out.println("Paina pelkkä Enter käyttääksesi valmista karttaa, muuten anna korkeus.");
        int korkeus = kysyKayttajalta("Anna kartan korkeus: ");
        if (korkeus == 0) {
            valmisPohja();
        } else {
            int leveys = kysyKayttajalta("Anna kartan leveys: ");
            String syote = kartanSyottaminen(leveys, korkeus, "", 0);
            pohja = new Pohja(korkeus, leveys, syote);
        }
        etsija = new Etsija(pohja);
//        testipohjat();
        kaynnistaGui();
    }

//    /**
//     * Metodi A*:n suorituskyvyn testaamiseen.
//     */
//    private void testipohjat() {
//        String syote = "";
//        for (int i = 2; i < 130; i++) {
//            for (int j = 0; j < i * i; j++) {
//                syote += "1";
//            }
//            syote = syote.substring(2);
//            syote = "L" + syote + "M";
//            pohja = new Pohja(i, i, syote);
//            etsija = new Etsija(pohja);
//            long aikaAlussa = System.currentTimeMillis();
//            etsija.aStar();
//            long aikaLopussa = System.currentTimeMillis();
//            System.out.println("i = " + i + " Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
//            syote = "";          
//        }
//    }
    
    /**
     * Käynnistää käyttöliittymän.
     */
    private void kaynnistaGui() {
        kali = new Kayttoliittyma(this, 25);

        SwingUtilities.invokeLater(kali);

        while (kali.getPiirtoalusta() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }
    }

    /**
     * Kysyy käyttäjältä kartan korkeuden ja leveyden.
     *
     * @param kysymys
     * @return 0 jos käyttäjä haluaa käyttää valmista karttaa, muuten luku
     * väliltä 1-30
     */
    private int kysyKayttajalta(String kysymys) {
        System.out.print(kysymys);
        while (true) {
            String sana = lukija.nextLine();
            if (sana.equals("")) {
                return 0;
            } else if (tarkistaLuku(sana)) {
                return Integer.parseInt(sana);
            }
        }
    }

    /**
     * Tarkista että luku on sallituissa rajoissa.
     *
     * @param sana muutetaan luvuksi
     * @return true, jos luku on sallitulla välillä, muuten false.
     */
    private boolean tarkistaLuku(String sana) {
        int luku = Integer.parseInt(sana);
        if (luku > 0 && luku < 31) {
            return true;
        } else {
            System.out.print("Luvun pitää olla välillä 1 - 30, anna uusi luku: ");
            return false;
        }
    }

    /**
     * Asettaa valmiin kartan Pohja-oliolle.
     */
    private void valmisPohja() {
//        String syote = esim3;
//        pohja = new Pohja(10, 18, syote);
//        String syote = esim2;
//        pohja = new Pohja(5, 5, syote);
        String syote = esim1;
        pohja = new Pohja(9, 10, syote);
    }

    public Pohja getPohja() {
        return pohja;
    }

    public Etsija getEtsija() {
        return etsija;
    }

    /**
     * Lukee käyttäjän syöttämän kartan, ilmoittaa virhetilanteissa.
     *
     * @param leveys kartan leveys
     * @param korkeus kartan korkeus
     * @param syote kartta merkkijonona
     * @param mones monta riviä käyttäjä on syöttänyt
     * @return kartta merkkijonona
     */
    private String kartanSyottaminen(int leveys, int korkeus, String syote, int mones) {
        // metodi liian pitkä, mistä lyhentää..?
        System.out.println("Syötä kartta: \nMerkitse lähtö kirjaimella L ja maali kirjaimella M");
        while (true) {
            String rivi = lukija.nextLine();
            mones++;
            if (rivi.length() != leveys) {
                mones = riviEiOikeanMittainen(mones, syote, leveys);
            } else {
                syote += rivi;
            }
            if (syote.length() == korkeus * leveys) {
                if (tarkistaOnkoLahtoJaMaali(syote)) {
                    break;
                } else {
                    syote = "";
                    mones = 0;
                }
            }
        }
        return syote;
    }

    /**
     * Ohjeistaa käyttäjää, jos syötetty rivi ei ole oikean mittainen.
     *
     * @param mones monta riviä käyttäjä on syöttänyt.
     * @param syote tähän asti annettu syöte
     * @param leveys kartan leveys
     * @return mones-1 eli sallittujen rivien määrä
     */
    private int riviEiOikeanMittainen(int mones, String syote, int leveys) {
        mones--;
        System.out.println("Rivi on liian lyhyt/pitkä, olet syöttänyt tähän mennessä " + mones + " riviä, jotka ovat");
        System.out.println("(Jatka rivien syöttöä tästä)");
        for (int i = 0; i < mones; i++) {
            String mj = syote.substring(i * leveys, i * leveys + leveys);
            System.out.println(mj);
        }
        return mones;
    }

    /**
     * Tarkistaa on käyttäjän syöttämässä kartassa lähtö ja maali.
     *
     * @param syote kartta merkkijonona
     * @param korkeus kartan korkeus
     * @param leveys kartan leveys
     * @return true, jos kartassa on lähtö ja maali, muuten false.
     */
    private boolean tarkistaOnkoLahtoJaMaali(String syote) {
        if (syote.contains("L") && syote.contains("M")) {
            return true;
        } else {
            System.out.println("Et antanut maalia ja/tai lähtöä, syötä kartta uudelleen");
            return false;
        }
    }
}
