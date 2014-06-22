package tietorakenteet;

import java.awt.image.BufferedImage;

/**
 * Koko hakualuetta kuvaava luokka
 */
public class Alue {
    
    /**
     * Alueen nodet int-arvoina tallennettuna.
     * Ei välttämättä tarvita, voitaisiin ehkä käyttää tulostukseen tms.
     */
    private int[][] intit;
    
    /**
     * Alueen korkeus.
     */
    private int korkeus;
    
    /**
     * alueen leveys.
     */
    private int leveys;
    
    /**
     * Alueen muodostavat alkiot kaksiulotteisena taulukkona.
     */
    private Node[][] nodet;
    
    /**
     * Jos alue on muodostettu kuvatiedostosta, tähän voi tallentaa kyseisen kuvan.
     * Tallessa mahdollista reitinmuotoilua ja tulostamista varten.
     */
    private BufferedImage alueenKuva;
    
    /**
     * Oletuskonstruktori, joka saa parametrinaan alueen koon.
     * @param koko 
     */
    public Alue(int koko) {
        intit = new int[koko][koko];
        nodet = new Node[koko][koko];
        korkeus = leveys = koko;
    }
    
    /**
     * Luo alueen annetun Node-taulukon perusteella.
     * Tämä on pääasiallisin tapa luoda uusi alue, esim. kuvan tietojen perusteella.
     * @param nodetaulukko 
     */
    public Alue(Node[][] nodetaulukko, int korkeus, int leveys) {
        this.nodet = nodetaulukko;
        this.korkeus = korkeus;
        this.leveys = leveys;
    }
    
    /**
     * Metodi, joka luo esimerkkitaulukon
     */
    public void luoEsimerkkiTaulukko() {
        
        int[][] numeroina = {
            //0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} , // 0
            { 0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0} , // 1
            { 5,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0} , // 2
            { 0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0} , // 3
            { 0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0} , // 4
            { 0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0} , // 5
            { 0,5,9,3,9,9,9,9,9,9,9,9,9,9,9,0} , // 6
            { 0,0,0,0,0,0,0,0,0,0,9,0,9,0,0,0} , // 7
            { 0,0,0,0,0,9,0,0,0,0,9,9,9,0,0,0} , // 8
            { 0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,0} , // 9
            { 0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,0} , // 0
            { 0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,0} , // 1
            { 0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,0} , // 2
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} , // 3
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} , // 4
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}   // 5
        };
        
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {

                Node n = new Node(i, j, numeroina[i][j]);
                nodet[i][j] = n;
            }
            
        }
        
    }
    
    /**
     * Pieni testitaulukko jolla helpompi tutkia algoritmin perustoimintaa.
     * 
     */
    public void luoPieniTestitaulukko() {
        int[][] numeroina = {
            //0 1 2 3 4 5 6 7
            { 0,0,0,0,0,0,0,0} , // 0
            { 0,0,0,9,9,9,9,0} , // 1
            { 0,9,9,9,0,0,0,0} , // 2
            { 0,0,0,0,0,0,0,0} , // 3
            { 0,0,0,9,0,0,0,0} , // 4
            { 0,0,0,9,0,9,0,0} , // 5
            { 0,0,0,9,0,0,0,0} , // 6
            { 0,0,0,0,0,0,0,0}   // 7
        };
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Node n = new Node(i, j, numeroina[i][j]);
                nodet[i][j] = n;
            }
            
        }
        
    }
    
    /**
     * Palauttaa halutuista koordinaateista noden tiedot
     * @param x
     * @param y
     * @return 
     */
    public Node getnode(int x, int y) {
        return this.nodet[x][y];
    }
    
    /**
     * Tulostaa alueen merkistönä.
     * estemerkki-muuttujassa määritellään miten ei-kuljettava alue tulostuu.
     * @return 
     */
    @Override
    public String toString() {
        String tuloste = "";
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                String kustannus = "";
                // char estemerkki = '\u275A';
                char estemerkki = '\u2588';
                if (nodet[i][j].onReitilla())
                    kustannus += "-";
                //else if (nodet[i][j].onLisattyNaapureihin())
                //    kustannus += "x";
                else if (nodet[i][j].getKustannus() < 9)
                    kustannus += nodet[i][j].getKustannus();
                else
                    kustannus += estemerkki;
                tuloste += kustannus;
            }
            tuloste += "\n";
        }
        
        return tuloste;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public int getLeveys() {
        return leveys;
    }

    public void setAlueenKuva(BufferedImage alueenKuva) {
        this.alueenKuva = alueenKuva;
    }

    public BufferedImage getAlueenKuva() {
        return alueenKuva;
    }
    
}
