
import wad.hakupuut.*;
import java.util.Scanner;

public class SuorituskyvynTestaus {
    private HakupuuRajapinta bst, avl, rbt, splay;
    
    public SuorituskyvynTestaus() {
        bst = new BinaarinenHakupuu();
        avl = new AVLpuu();
        rbt = new PunaMustaPuu();
        splay = new SplayPuu();
    }
    
    /**
     * Luokan ainoa näkyvä metodi. Metodia kutsumalla on tarkoitus hoitaa ulkopuolinen
     * suorituskykytestaus.
     */
    public void testaaPuita() {
        liirumlaarum();
        int maara = kysyMaara("Kuinka monella syötteellä testataan?");
        valitaanTestattavatPuut(maara);
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
     * Kysytään käyttäjältä int arvoa, jonka metodi palauttaa. Vain luonnolliset luvut sallitaan syötteinä.
     * @param s kysymys string
     * @return käyttäjän syöttämä int arvo.
     */
    private int kysyMaara(String s) {
        System.out.println(s);
        try {
            Scanner sc = new Scanner(System.in);
            Integer i = new Integer(sc.nextLine().replaceAll("\\s+", ""));
            if(i >= 0) return i;
            else throw new Exception();
        } catch (Exception e) {
            return kysyMaara(s);
        }
    }
    
    /**
     * Palauttaa totuusarvon. String s sisältää kysymyksen, johon odotetaan
     * vastausta k. Jos saadaan jokin muu syöte kuin k, palautetaan false.
     * @param s Kysymys merkkijono.
     * @return palauttaa true, syötteelle k ja kaikille muille syötteille false.
     */
    private boolean testataanko(String s) {
        System.out.println(s);
        try {
            Scanner sc = new Scanner(System.in);
            if(sc.nextLine().equals("k")) return true;
            else return false;
        } catch (Exception e) {
            return testataanko(s);
        }
    }
    
    
    /**
     * Erottelee testattavat puut. Käyttäjän päätettäväksi jää, mitkä puista testataan. Metodi luotiin sitä varten, 
     * että voidaan testata monimutkaisempia puita jouhevammin. Tämä mahdollistaa etsimään yksittäiselle puulle
     * rajoja syötteen suhteen ilman, että muut tietorakenteet hidastavat testaamista.
     * @param maara Kaikkia puita testataan samalla syötteellä, jotta puiden suoritusta voidaan vertailla
     * keskenään.
     */
    private void valitaanTestattavatPuut(int maara) {
        System.out.println("\nSyötä k seuraaviin kysymyksiin testataksesi puita. Muut syötteet ohittavat puun.");
        if(testataanko("Testataanko BST?"))testataanBst(maara);
        if(testataanko("Testataanko AVL?"))testataanAVL(maara);
        if(testataanko("Testataanko RBT?"))testataanRbt(maara);
        if(testataanko("Testataanko SPLAY?"))testataanSplay(maara);
    }
    
    /**
     * Testataan Binääristä hakupuuta
     * @param maara puun alkioiden määrä
     */
    private void testataanBst(int maara) {
        System.out.println("Testataan binääristä hakupuuta...");
        bst = puuLisays(maara, bst);
        puuHaku(maara,bst);
        bst = puuPoisto(maara,bst);
    }
    
    /**
     * Testataan AVL-puuta
     * @param maara puun alkioiden määrä
     */
    private void testataanAVL(int maara) {
        System.out.println("Testataan AVL-puuta...");
        avl = puuLisays(maara, avl);
        puuHaku(maara,avl);
        avl = puuPoisto(maara,avl);
    }
    
    /**
     * Testataan Punamustaa puuta
     * @param maara puun alkioiden määrä
     */
    private void testataanRbt(int maara) {
        System.out.println("Testataan Punamustaa puuta...");
        rbt = puuLisays(maara, rbt);
        puuHaku(maara,rbt);
        rbt = puuPoisto(maara,rbt);
    }
    
    /**
     * Testataan Splay-puuta
     * @param maara puun alkioiden määrä
     */
    private void testataanSplay(int maara) {
        System.out.println("Testataan Splay-puuta...");
        splay = puuLisays(maara, splay);
        splay = puuHaku(maara,splay);
        splay = puuPoisto(maara,splay);
    }
    /**
     * Lisätään alkioita puuhun.
     * @param maara montako lisätään
     * @param puu mihin puuhun
     * @return palautetaan operoitu puu
     */
    private HakupuuRajapinta puuLisays(int maara, HakupuuRajapinta puu) {
        long aikaAlussa = System.currentTimeMillis(); 
        for(int i = maara; i>0; i--) puu.lisaa(i);
        long aikaLopussa = System.currentTimeMillis();  
        System.out.println("Lisäyksessä kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");  
        return puu;
    }
    
    /**
     * Haetaan kaikki alkiot puusta.
     * @param maara Montako alkiota on puussa
     * @param puu Mikä puu
     * @return palautetaan operoitu puu. Tätä tarvitaan erityisesti splaypuussa, missä haku muuttaa puun rakennetta.
     */
    private HakupuuRajapinta puuHaku(int maara, HakupuuRajapinta puu) {
        long aikaAlussa = System.currentTimeMillis(); 
        for(int i = maara; i>0; i--) puu.hae(i);
        long aikaLopussa = System.currentTimeMillis();  
        System.out.println("Haussa kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");  
        return puu;
    }
    
    /**
     * Poistetaan kaikki puun alkiot.
     * @param maara poistettavien määrä
     * @param puu, josta poistetaan
     * @return palautetaan tyhjä puu
     */
    private HakupuuRajapinta puuPoisto(int maara, HakupuuRajapinta puu) {
        long aikaAlussa = System.currentTimeMillis(); 
        for(int i = 1; i<maara+1; i++) puu.poista(i);
        long aikaLopussa = System.currentTimeMillis();  
        System.out.println("Poistossa kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");  
        return puu;
    }
}
