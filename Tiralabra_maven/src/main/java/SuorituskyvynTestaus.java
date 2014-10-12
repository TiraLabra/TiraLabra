
import wad.hakupuut.*;
import java.util.Scanner;

public class SuorituskyvynTestaus {
    
    public SuorituskyvynTestaus() {
        BinaarinenHakupuu bst = new BinaarinenHakupuu();
        AVLpuu avl = new AVLpuu();
        PunaMustaPuu rbt = new PunaMustaPuu();
        SplayPuu splay = new SplayPuu();
    }
    
    /**
     * Luokan ainoa näkyvä metodi. Metodia kutsumalla on tarkoitus hoitaa ulkopuolinen
     * suorituskykytestaus.
     */
    public void testaaPuita() {
        liirumlaarum();
        int maara = kysyMaara("Kuinka monella syötteellä testataan?");
    }
    
    /**
     * Alku höpinöitä. 
     */
    private void liirumlaarum() {
        System.out.println("Testataan binäärisiä hakupuita:\n"
                + "1. Perinteinen binäärinen hakupuu\n"
                + "2. AVL-puu\n"
                + "3. Punamusta puu\n"
                + "4. Splay-puu\n");
    }
    
    /**
     * Kysytään käyttäjältä int arvoa, jonka metodi palauttaa
     * @param s kysymys string
     * @return käyttäjän syöttämä int arvo.
     */
    private int kysyMaara(String s) {
        System.out.println(s);
        try {
            Scanner sc = new Scanner(System.in);
            return sc.nextInt();
        } catch (Exception e) {
            return kysyMaara(s);
        }
    }
}
