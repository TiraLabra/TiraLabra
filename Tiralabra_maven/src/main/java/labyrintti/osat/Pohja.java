package labyrintti.osat;

import java.io.File;
import java.util.Scanner;

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

    public Pohja() {

    }

    /**
     * Luo tekstitiedostosta pohjan.
     */
    public void luoPohja() {
        File tiedosto = new File("src/main/java/labyrintti/osat/kartta.txt");
        Scanner lukija = null;
        try {
            lukija = new Scanner(tiedosto);
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen epäonnistui.");
            return;
        }

        
        leveys = lukija.nextLine().length();
        korkeus = 1;
        while(lukija.hasNextLine()){
            lukija.nextLine();
            korkeus++;
        }
        kartta = new Ruutu[korkeus][leveys];
        try {
            lukija = new Scanner(tiedosto);
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen epäonnistui.");
            return;
        }
        int x = 0;
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            for (int j = 0; j < rivi.length(); j++) {
                int arvo = Integer.parseInt(""+rivi.charAt(j));
                Ruutu ruutu = new Ruutu(arvo, x, j);
                kartta[x][j]=ruutu;
            }
            x++;
        }
        lukija.close();
    }
    
    /**
     * Tulostaa pohjakartan.
     */
    public void tulostaPohja(){
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                System.out.print(kartta[i][j]);
            }
            System.out.println("");
        }
    }
}
