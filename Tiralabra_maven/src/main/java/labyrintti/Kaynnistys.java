package labyrintti;

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
        pohja.alustaPohja("src/main/java/labyrintti/osat/kartta1.txt");
        etsija = new Etsija(pohja);
        kali = new Kayttoliittyma(this, 40);
    }

    /**
     * Käynnistää käyttöliittymän.
     */
    public void kaynnista() {
        SwingUtilities.invokeLater(kali);

        while (kali.getPiirtoalusta() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }
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
