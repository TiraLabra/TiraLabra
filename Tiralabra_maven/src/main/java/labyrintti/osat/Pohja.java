package labyrintti.osat;

import java.io.File;
import java.util.Scanner;

/**
 * Karttapohja, jossa on ruudut. Erityisesti pohja tietää lähtö ja maaliruudun.
 *
 * @author heidvill
 */
public class Pohja {

    /**
     * Taulukko kartan muistamiseen.
     */
    private Ruutu[][] kartta;
    /**
     * Kartan korkeus.
     */
    private int korkeus;
    /**
     * Kartan leveys.
     */
    private int leveys;
    /**
     * Lähtöruudun x-koordinaatti.
     */
    private int lahtoX;
    /**
     * Lähtöruudun y-koordinaatti.
     */
    private int lahtoY;
    /**
     * Maaliruudun x-koordinaatti.
     */
    private int maaliX;
    /**
     * Maaliruudun y-koordinaatti.
     */
    private int maaliY;

    public Pohja() {
    }
    
    public Pohja(int korkeus, int leveys, String syote){
        kartta = new Ruutu[korkeus][leveys];
        this.korkeus = korkeus;
        this.leveys = leveys;
        alustaPohja(syote);
    }
    
    private void alustaPohja(String syote){
        Scanner lukija = new Scanner(System.in);
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                char merkki = syote.charAt(i*leveys+j);
                int arvo = tarkistaRuudunArvo(merkki, i, j);
                kartta[i][j] = new Ruutu(arvo, i, j);
            }
        }
        tulostaPohja();
        
//        int x = 0;
//        while (lukija.hasNextLine()) {
//            String rivi = lukija.nextLine();
//            for (int j = 0; j < rivi.length(); j++) {
//                char merkki = rivi.charAt(j);
//                int arvo = tarkistaRuudunArvo(merkki, x, j);
//                kartta[x][j] = new Ruutu(arvo, x, j);
//            }
//            x++;
//        }
    }

    /**
     * Luo tekstitiedostosta pohjan taulukkoon.
     */
    public void alustaPohja1(String tiedostoPolku) {
        File tiedosto = new File(tiedostoPolku);
        Scanner lukija = liitaTiedostoLukijaan(tiedosto);

        leveys = lukija.nextLine().length();
        laskeKorkeus(lukija);
        kartta = new Ruutu[korkeus][leveys];
        lukija = liitaTiedostoLukijaan(tiedosto);
        tallennaPohjaTaulukkoon(lukija);
        lukija.close();
    }

    /**
     * Liittää annetun tekstitiedoston scanner-luokan oliolle.
     *
     * @param tiedosto annetaan scannerille
     * @return Scanner lukija
     */
    private Scanner liitaTiedostoLukijaan(File tiedosto) {
        Scanner lukija = null;
        try {
            lukija = new Scanner(tiedosto);
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen epäonnistui.");
            return null;
        }
        return lukija;
    }

    /**
     * Tallennetaan tiedoston kartta taulukkoon.
     *
     * @param lukija lukee tiedostoa
     */
    private void tallennaPohjaTaulukkoon(Scanner lukija) {
        int x = 0;
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            for (int j = 0; j < rivi.length(); j++) {
                char merkki = rivi.charAt(j);
                int arvo = tarkistaRuudunArvo(merkki, x, j);
                kartta[x][j] = new Ruutu(arvo, x, j);
            }
            x++;
        }
    }

    /**
     * Määrittää ruudulle painoarvon.
     *
     * @param merkki luettava merkki
     * @param x rivi
     * @param j sarake
     * @return ruudun arvo
     */
    private int tarkistaRuudunArvo(char merkki, int x, int j) {
        if (merkki == 'L') {
            lahtoX = x;
            lahtoY = j;
            return 0;
        } else if (merkki == 'M') {
            maaliX = x;
            maaliY = j;
            return 0;
        } else {
            return Integer.parseInt("" + merkki);
        }
    }

    /**
     * Laskee taulukolle korkeuden.
     *
     * @param lukija lukee tiedostoa
     */
    private void laskeKorkeus(Scanner lukija) {
        korkeus = 1;
        while (lukija.hasNextLine()) { //selvitetään, kuinka monta riviä pohjataulukkoon tulee
            lukija.nextLine();
            korkeus++;
        }
    }

    public Ruutu[][] getKartta() {
        return kartta;
    }

    public Ruutu getRuutu(int i, int j) {
        return kartta[i][j];
    }

    public int getKorkeus() {
        return korkeus;
    }

    public int getLahtoX() {
        return lahtoX;
    }

    public int getLahtoY() {
        return lahtoY;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getMaaliX() {
        return maaliX;
    }

    public int getMaaliY() {
        return maaliY;
    }

    public Ruutu getLahto() {
        return kartta[lahtoX][lahtoY];
    }

    public Ruutu getMaali() {
        return kartta[maaliX][maaliY];
    }

    /**
     * Tulostaa pohjakartan.
     */
    public void tulostaPohja() {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                System.out.print(kartta[i][j]);
            }
            System.out.println("");
        }
    }
}
