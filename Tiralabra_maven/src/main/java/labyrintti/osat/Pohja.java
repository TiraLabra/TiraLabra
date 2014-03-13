package labyrintti.osat;

import java.io.File;
import java.util.Scanner;

public class Pohja {

    private Ruutu[][] kartta;
    private int korkeus;
    private int leveys;

    public Pohja() {

    }

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
        int y = 0;
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            for (int i = 0; i < rivi.length(); i++) {
                int arvo = Integer.parseInt(""+rivi.charAt(i));
                Ruutu ruutu = new Ruutu(arvo, i, y);
                kartta[i][y]=ruutu;
            }
            y++;
        }
        lukija.close();
    }
    
    public void tulostaPohja(){
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                System.out.print(kartta[i][j]);
            }
            System.out.println("");
        }
    }
}
