package suorituskyky;

import apuneuvot.MatriisienGeneroija;
import apuneuvot.MatriisienKopioija;
import logiikka.Determinantti;
import logiikka.Kaanteismatriisi;
import logiikka.Kertolasku;
import logiikka.Potenssi;
import logiikka.Strassen;
import logiikka.Vahennyslasku;
import logiikka.Yhteenlasku;

/**
 * SuorituskykyTestaus-luokka, jonka avulla voidaan testata eri lasku-
 * operaatioiden suorituskyky.
 * 
 * @author Eversor
 */
public class SuorituskykyTestaus {

    private MatriisienGeneroija generoija;
    private Determinantti determinantti;
    private Kaanteismatriisi kaanteismatriisi;
    private Strassen strassen;
    private Potenssi potenssi;
    private Kertolasku kertolasku;

    /**
     * Konstruktori, jossa luodaan tarvittavat ilmentymät laskuoperaatioista
     * ja MatriisienGeneroija-luokasta ja asetetaan ne niiden private-muuttujiin.
     * 
     */
    public SuorituskykyTestaus() {
        generoija = new MatriisienGeneroija();
        determinantti = new Determinantti(new MatriisienKopioija());
        kaanteismatriisi = new Kaanteismatriisi(new MatriisienKopioija(),
                                                generoija);
        kertolasku = new Kertolasku();
        strassen = new Strassen(new Yhteenlasku(), new Vahennyslasku(),
                                kertolasku);
        potenssi = new Potenssi(generoija, strassen);
    }

    /**
     * Metodi, joka testaa kertolaskuoperaation suorituskyvyn parametrina 
     * annetussa matriisikoossa. Palauttaa kellotettujen aikojen keskiarvon
     * millisekunneissa.
     * 
     * @param koko Matriisin koko
     * @return Palauttaa kellotettujen aikojen keskiarvon millisekunneissa
     */
    public double testaaKertolasku(int koko) {
        return ajanotto(koko, "Kertolasku", 1);
    }
    
    /**
     * Metodi, joka testaa kertolaskuoperaation suorituskyvyn parametrina 
     * annetussa matriisikoossa. Palauttaa kellotettujen aikojen keskiarvon
     * millisekunneissa.
     * 
     * @param koko Matriisin koko
     * @return Palauttaa kellotettujen aikojen keskiarvon millisekunneissa
     */
    public double testaaStrassen(int koko) {
        return ajanotto(koko, "Strassen", 1);
    }
    
    /**
     * Metodi, joka testaa kertolaskuoperaation suorituskyvyn parametrina 
     * annetussa matriisikoossa. Palauttaa kellotettujen aikojen keskiarvon
     * millisekunneissa.
     * 
     * @param koko Matriisin koko
     * @return Palauttaa kellotettujen aikojen keskiarvon millisekunneissa
     */
    public double testaaDeterminantti(int koko) {
        return ajanotto(koko, "Determinantti", 1);
    }
    
    /**
     * Metodi, joka testaa kertolaskuoperaation suorituskyvyn parametrina 
     * annetussa matriisikoossa. Palauttaa kellotettujen aikojen keskiarvon
     * millisekunneissa.
     * 
     * @param koko Matriisin koko
     * @return Palauttaa kellotettujen aikojen keskiarvon millisekunneissa
     */
    public double testaaKaanteismatriisi(int koko) {
        return ajanotto(koko, "Kaanteismatriisi", 1);
    }
    
    /**
     * Metodi, joka testaa kertolaskuoperaation suorituskyvyn parametrina 
     * annetussa matriisikoossa. Palauttaa kellotettujen aikojen keskiarvon
     * millisekunneissa.
     * 
     * @param koko Matriisin koko
     * @param potenssiin Haluttu potenssi
     * @return Palauttaa kellotettujen aikojen keskiarvon millisekunneissa
     */
    public double testaaPotenssi(int koko, int potenssiin) {
        return ajanotto(koko, "Potenssi", potenssiin);
    }
    
    /**
     * Metodi, joka ottaa kymmenen aikaa halutusta laskutoimituksesta ja
     * palauttaa niiden keskiarvon.
     * 
     * @param koko Matriisin koko
     * @param tunniste Laskutoimituksen tunniste
     * @param potenssiin Haluttu potenssi
     * @return 
     */
    private double ajanotto(int koko, String tunniste, int potenssiin) {
        long[] kestot = new long[11];
        for (int i = 1; i < 11; i++) {
            long aikaAlussa = System.currentTimeMillis();
            laskutoimitus(koko, tunniste, potenssiin);
            long aikaLopussa = System.currentTimeMillis();
            kestot[i] = (aikaLopussa - aikaAlussa);
        }
        return laskeKeskiarvo(kestot);
    }
    
    /**
     * Metodi, joka suorittaa parametrina annetun laskutoimituksen ja palauttaa
     * true, jos tulos oli haluttu.
     * 
     * @param koko Matriisin koko
     * @param tunniste Laskutoimituksen tunniste
     * @param potenssiin Haluttu potenssi
     * @return Palauttaa true, jos laskutoimitus onnistui ja tulos oli haluttu
     */
    protected boolean laskutoimitus(int koko, String tunniste, int potenssiin) {
        if(tunniste.equals("Kertolasku")) {
            double[][] tulomatriisi = kertolasku.kerro(
                             generoija.luoYkkosillaTaytettyMatriisi(koko, koko),
                             generoija.luoYkkosillaTaytettyMatriisi(koko, koko));
            return tulomatriisi[0][0] == koko;
        } else if(tunniste.equals("Strassen")) {
            double[][] tulomatriisi = strassen.kerro(
                           generoija.luoYkkosillaTaytettyMatriisi(koko, koko),
                           generoija.luoYkkosillaTaytettyMatriisi(koko, koko));
            return tulomatriisi[0][0] == koko;
        } else if(tunniste.equals("Determinantti")) {
            return determinantti.laskeDeterminantti(
                   generoija.luoYksikkomatriisi(koko)) == 1;
        } else if(tunniste.equals("Kaanteismatriisi")) {
            double[][] kaanteis = kaanteismatriisi.invertoi(
                           generoija.luoYksikkomatriisi(koko));
            return kaanteis[0][0] == 1;
        } else if(tunniste.equals("Potenssi")) {
            double[][] tulomatriisi = potenssi.neliomatriisiPotenssiin(
                            generoija.luoYksikkomatriisi(koko), potenssiin);
            return tulomatriisi[0][0] == 1;
        }
        return false;
    }

    /**
     * Metodi, joka laskee parametrina annetusta taulukosta keskiarvon. Aluksi
     * järjestää listan lisäysjärjestämisellä, jonka jälkeen kaksi pienintä ja
     * kaksi suurinta arvoa nollataan, ja jäljellejäävistä otetaan keskiarvo.
     * 
     * @param kestot Taulukko, josta halutaan laskea keskiarvo
     * @return Palauttaa lasketun keskiarvon
     */
    protected double laskeKeskiarvo(long[] kestot) {
        jarjesta(kestot);

        kestot[1] = 0;
        kestot[2] = 0;
        kestot[10] = 0;
        kestot[9] = 0;

        double keskiarvo = 0;
        for (int i = 3; i < 9; i++) {
            keskiarvo += (double)kestot[i];
        }
        return keskiarvo / 6;
    }

    /**
     * Metodi, joka järjestää parametrina annetun taulukon lisäysjärjestämisellä.
     * 
     * @param kestot Taulukko, joka halutaan järjestää
     */
    protected void jarjesta(long[] kestot) {
        kestot[0] = Long.MIN_VALUE;
        for (int i = 2; i < 11; i++) {
            long apu = kestot[i];
            int j = i;
            while (kestot[j - 1] > apu) {
                kestot[j] = kestot[j - 1];
                j--;
            }
            kestot[j] = apu;
        }
    }
}