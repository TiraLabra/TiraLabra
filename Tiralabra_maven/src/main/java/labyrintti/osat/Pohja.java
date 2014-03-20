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
    private int lahtoX;
    private int lahtoY;
    private int maaliX;
    private int maaliY;

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
        while (lukija.hasNextLine()) {
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
        int arvo = 0;
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            for (int j = 0; j < rivi.length(); j++) {
                //int arvo = Integer.parseInt(""+rivi.charAt(j));
                char merkki = rivi.charAt(j);
                if (merkki == 'L') {
                    lahtoX=x;
                    lahtoY=j;
                    arvo = 0;
                } else if (merkki == 'M') {
                    maaliX=x;
                    maaliY=j;
                    arvo = 0;
                } else {
                    arvo = Integer.parseInt(""+merkki);
                }
                Ruutu ruutu = new Ruutu(arvo, x, j);
                    kartta[x][j] = ruutu;
            }
            x++;
        }
        lukija.close();
    }
    
    
    public Ruutu[][] getKartta() {
        return kartta;
    }
    
    public Ruutu getRuutu(int i, int j){
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
    
    public Ruutu getLahto(){
        return kartta[lahtoX][lahtoY];
    }
    
    public Ruutu getMaali(){
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
