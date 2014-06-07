package logiikka;

/**
 * Strassen-luokka, jonka avulla saadaan kerrottua kaksi samankokoista neliö-
 * matriisia Strassenin algoritmilla.
 *
 * @author Eversor
 */
public class Strassen {
         
    private Yhteenlasku yhteenlasku;
    private Vahennyslasku vahennyslasku;
    private Kertolasku kertolasku;

    /**
     * Konstruktori, jolle annetaan parametrina Yhteenlasku-, Vahennyslasku-
     * ja Kertolasku-luokan ilmentymät. Ilmentymät asetetaan niiden private 
     * muuttujiin. 

     * @param yhteenlasku Yhteenlasku-luokan ilmentymä, joka pitää sisällään
     *                    samankokoisten matriisien yhteenlaskuoperaatiot
     * @param vahennyslasku Vahennyslasku-luokan ilmentymä, joka pitää
     *                      sisällään samankokoisten matriisien vähennyslasku-
     *                      operaatiot
     * @param kertolasku Kertolasku-luokan ilmentymä, joka pitää sisällään
     *                   matriisin kertolaskun tavallisella tavalla
     */
    public Strassen(Yhteenlasku yhteenlasku, Vahennyslasku vahennyslasku,
                    Kertolasku kertolasku) {
        this.yhteenlasku = yhteenlasku;
        this.vahennyslasku = vahennyslasku;
        this.kertolasku = kertolasku;
    }

    /**
     * Metodi, joka kertoo kaksi matriisia keskenään Strassenin algoritmilla
     * ja palauttaa niiden tulomatriisin. Tarkastaa ensin ovatko matriisit
     * samankokoisia neliömatriiseja. Jos matriisit sisältävät ainoastaan yhden
     * alkion, tulomatriisi on niiden tulo. Tämän jälkeen jos matriisien rivit
     * ja sarakkeet eivät valmiiksi ole kooltaan kahden potensseja, ne täytetään
     * nollilla seuraavaan kahden potenssiin. Seuraavaksi ajetaan Strassenin
     * rekursiivinen algoritmi, jonka jälkeen tarvittaessa poistetaan kootusta 
     * matriisista täytteenä olleet nollat, jolloin matriisin koko palautuu 
     * alkuperäiseksi. Lopuksi palauttaa tulomatriisin.
     * 
     * @param eka Vasemmalta luettuna ensimmäinen matriisi muotoa m x n
     * @param toka Vasemmalta luettuna toinen matriisi muotoa p x q
     * @return Palauttaa tulomatriisin muotoa n x n, jos matriisit ovat 
     *         samankokoisia neliömatriiseja
     */
    public double[][] kerro(double[][] eka, double[][] toka) {
        tarkasta(eka, toka);
        if (eka.length == 1) {
            return kertolasku.kerro(eka, toka);
        }

        double[][] ekaFull = tayta(eka);
        double[][] tokaFull = tayta(toka);
        double[][] koottu = rekursioAlgo(ekaFull, tokaFull);

        if (koottu.length != eka.length) {
            koottu = tayteNollatPois(koottu, eka.length);
        }
       
        return koottu;
    }

    /**
     * Metodi, joka tarkastaa toteuttavatko parametreina annetut matriisit
     * Strassenin algoritmin ehdon, eli ovatko matriisit samankokoisia neliö-
     * matriiseja. Heittää poikkeuksen, jos ehto ei täyty.
     * 
     * @param eka Vasemmalta luettuna ensimmäinen matriisi muotoa m x n
     * @param toka Vasemmalta luettuna toinen matriisi muotoa p x q
     */
    private void tarkasta(double[][] eka, double[][] toka) {
        if (eka.length != eka[0].length || toka.length != toka[0].length
                                        || eka.length != toka.length) {
            throw new IllegalArgumentException("Matriisit eivät ole "
                    + "samankokoisia neliömatriiseja, joten Strassenia "
                    + "ei voida käyttää");
        }
    }

    /**
     * Metodi, joka täyttää parametrina annetun neliömatriisin rivien ja 
     * sarakkeiden määrät seuraavaan kahden potenssiin. Matriisin täytettyjen 
     * alkioiden arvoksi tulee 0. Lopuksi palauttaa nolla-arvoilla täytetyn
     * matriisin.
     * 
     * @param matriisi Neliömatriisi, jonka koko halutaan kasvattaa seuraavaan
     *                 kahden potenssiin
     * @return Palauttaa täytetyn neliömatriisin, jonka koko on kahden potenssi
     *         ja jonka täytettyjen alkioiden arvot ovat 0
     */
    private double[][] tayta(double[][] matriisi) {
        int seuraavaPotenssi = (int) Math.ceil(
                                     Math.log(matriisi.length) / Math.log(2));
        int koko = (int) Math.pow(2, seuraavaPotenssi);
        double[][] taytetty = new double[koko][koko];
        for (int rivi = 0; rivi < matriisi.length; rivi++) {
            for (int sarake = 0; sarake < matriisi.length; sarake++) {
                taytetty[rivi][sarake] = matriisi[rivi][sarake];
            }
        }
        return taytetty;
    }

    /**
     * Metodi, joka pilkkoo annetut neliömatriisit neljäksi yhtäsuureksi lohko-
     * matriisiksi, laskee niiden avulla rekursiivisesti 7 tulomatriisia joiden
     * avulla saadaan lopuksi koottua vastauksena annettava tulomatriisi, joka 
     * palautetaan. 
     * 
     * Puolittaa aluksi neliömatriisin koon (ei neliömatriisia), jonka jälkeen 
     * tarkastaa mennäänkö 32:een tai sen alle, jolloin palauttaa tavallisella
     * matriisikertolaskulla lasketun tulomatriisin. Tämä optimointi johtuu
     * siitä, että pienillä matriiseilla Strassen paljon hitaampi kuin
     * tavallinen matriisikertolasku. Tämän jälkeen alustaa neliömatriisien 
     * lohkomatriisit puolitetulla koolla ja sopivilla alkioilla, jotta 
     * muodostuu ositus neliömatriisin vasemmalle ja oikealle ylä- ja ala-
     * nurkalle.
     * 
     * Tämän jälkeen laskee rekursiivisesti sopivien lohkomatriisien summien
     * ja erotusten avulla 7 eri tulomatriisia, joita summaamalla ja
     * vähentämällä saadaan muodostettua vastauksena annettavan tulomatriisin 
     * lohkomatriisiesitys. Lopuksi kootaan lohkomatriiseista vastauksena
     * annettava tulomatriisi ja palautetaan se.
     * 
     * @param eka Vasemmalta luettuna ensimmäinen neliömatriisi muotoa n x n
     * @param toka Vasemmalta luettuna toinen neliömatriisi muotoa n x n
     * @return Palauttaa halutun tulomatriisin muotoa n x n
     */
    private double[][] rekursioAlgo(double[][] eka, double[][] toka) {
        int puolet = eka.length / 2;

        if (puolet <= 32) {
            return kertolasku.kerro(eka, toka);
        }
        
        double[] lohkot [][] = new double[8][puolet][puolet];
        double[] tulomatriisit [][] = new double[7][][];
        double[] vastauslohkot [][] = new double[4][][];

        ositaLohkoihin(eka, toka, lohkot, puolet);       
        laskeTulomatriisit(lohkot, tulomatriisit);       
        laskeVastauslohkot(tulomatriisit, vastauslohkot);      
        return kokoaVastaus(vastauslohkot, puolet, eka.length);
    }
    
    /**
     * Metodi, joka osittaa kerrottavat neliömatriisit keskenään samankokoisiksi 
     * lohkomatriiseiksi. Lohkot[0] - [3] pitävät sisällään vasemmalta luettuna 
     * ensimmäisen kerrottavan matriisin osituksen ja lohkot[4] - [7] toisen 
     * kerrottavan matriisin osituksen.
     * 
     * @param eka Vasemmalta luettuna ensimmäinen neliömatriisi muotoa n x n
     * @param toka Vasemmalta luettuna toinen neliömatriisi muotoa n x n
     * @param lohkot Matriisi, joka pitää sisällään kerrottavien neliömatriisien
     *               lohkomatriisit
     * @param puolet Reaaliluku, joka pitää kirjaa siitä missä vaiheessa ositus
     *               vaihtuu
     */
    private void ositaLohkoihin(double[][] eka, double[][] toka, 
                                double[][][] lohkot, int puolet){
        for (int rivi = 0; rivi < puolet; rivi++) {
            for (int sarake = 0; sarake < puolet; sarake++) {
                lohkot[0][rivi][sarake] = eka[rivi][sarake];
                lohkot[1][rivi][sarake] = eka[rivi][sarake + puolet];
                lohkot[2][rivi][sarake] = eka[rivi + puolet][sarake];
                lohkot[3][rivi][sarake] = eka[rivi + puolet][sarake + puolet];

                lohkot[4][rivi][sarake] = toka[rivi][sarake];
                lohkot[5][rivi][sarake] = toka[rivi][sarake + puolet];
                lohkot[6][rivi][sarake] = toka[rivi + puolet][sarake];
                lohkot[7][rivi][sarake] = toka[rivi + puolet][sarake + puolet];
            }
        }
    }
    
    /**
     * Metodi, joka laskee tulomatriisit rekursiivisesti lohkomatriisien summien 
     * ja erotusten avulla.
     * 
     * @param lohkot Matriisi, joka pitää sisällään kerrottavien neliömatriisien
     *               lohkomatriisit
     * @param tulomatriisit Matriisi, joka pitää sisällään rekursiivisesti
     *                      muodostetut tulomatriisit
     */
    private void laskeTulomatriisit(double[][][] lohkot, 
                                    double[][][] tulomatriisit){
        tulomatriisit[0] = rekursioAlgo(yhteenlasku.summaa(lohkot[0], lohkot[3]),
                                        yhteenlasku.summaa(lohkot[4], lohkot[7]));
        tulomatriisit[1] = rekursioAlgo(yhteenlasku.summaa(lohkot[2], lohkot[3]), 
                                        lohkot[4]);
        tulomatriisit[2] = rekursioAlgo(lohkot[0], vahennyslasku.vahenna(
                                        lohkot[5], lohkot[7]));
        tulomatriisit[3] = rekursioAlgo(lohkot[3], vahennyslasku.vahenna(
                                        lohkot[6], lohkot[4]));
        tulomatriisit[4] = rekursioAlgo(yhteenlasku.summaa(lohkot[0], lohkot[1]), 
                                        lohkot[7]);
        tulomatriisit[5] = rekursioAlgo(vahennyslasku.vahenna(lohkot[2], lohkot[0]),
                                        yhteenlasku.summaa(lohkot[4], lohkot[5]));
        tulomatriisit[6] = rekursioAlgo(vahennyslasku.vahenna(lohkot[1], lohkot[3]),
                                        yhteenlasku.summaa(lohkot[6], lohkot[7]));
    }

    /**
     * Metodi, joka laskee tulomatriisien summien ja erotusten avulla 
     * vastauksena annettavan tulomatriisin lohkomatriisit, jotka muodostavat
     * tulomatriisin osituksen.
     * 
     * @param tulomatriisit Matriisi, joka pitää sisällään rekursiivisesti
     *                      muodostetut tulomatriisit
     * @param vastauslohkot Matriisi, joka pitää sisällään vastauksena
     *                      annettavan tulomatriisin lohkomatriisit
     */
    private void laskeVastauslohkot(double[][][] tulomatriisit, 
                                    double[][][] vastauslohkot){
        vastauslohkot[0] = vahennyslasku.vahenna(yhteenlasku.summaa(
                           yhteenlasku.summaa(tulomatriisit[0], tulomatriisit[3]), 
                           tulomatriisit[6]), tulomatriisit[4]);
        vastauslohkot[1] = yhteenlasku.summaa(tulomatriisit[2], tulomatriisit[4]);
        vastauslohkot[2] = yhteenlasku.summaa(tulomatriisit[1], tulomatriisit[3]);
        vastauslohkot[3] = vahennyslasku.vahenna(yhteenlasku.summaa(
                           yhteenlasku.summaa(tulomatriisit[0], tulomatriisit[2]), 
                           tulomatriisit[5]), tulomatriisit[1]);
    }
    
    /**
     * Metodi, joka kokoaa vastauksena annettavan tulomatriisin sen osituksen
     * muodostavista lohkomatriiseista. Palauttaa vastauksena annettavan
     * tulomatriisin.
     * 
     * @param vastauslohkot Matriisi, joka pitää sisällään vastauksena
     *                      annettavan tulomatriisin lohkomatriisit
     * @param puolet Reaaliluku, joka pitää kirjaa siitä missä vaiheessa ositus
     *               vaihtuu
     * @param koko Vastauksena annettavan tulomatriisin alkuperäinen koko
     * @return Palauttaa vastauksena annettavan tulomatriisin muotoa n x n
     */
    private double[][] kokoaVastaus(double[][][] vastauslohkot, 
                                    int puolet, int koko){
        double[][] vastaus = new double[koko][koko];
        for (int rivi = 0; rivi < puolet; rivi++) {
            for (int sarake = 0; sarake < puolet; sarake++) {
                vastaus[rivi][sarake] = vastauslohkot[0][rivi][sarake];
                vastaus[rivi][sarake + puolet] = vastauslohkot[1][rivi][sarake];
                vastaus[rivi + puolet][sarake] = vastauslohkot[2][rivi][sarake];
                vastaus[rivi + puolet][sarake + puolet] = vastauslohkot[3][rivi]
                                                                       [sarake];
            }
        }
        return vastaus;
    }
    
    /**
     * Metodi, joka poistaa täytetyn neliömatriisin ylimääräiset nollarivit ja
     * -sarakkeet ja palauttaa täytetyn matriisin alkuperäisessä koossaan.
     * 
     * @param taytetty Neliömatriisi, joka on täytetty nollariveillä ja 
     *                 -sarakkeilla, jonka koko halutaan palauttaa takaisin 
     *                 alkuperäiseksi neliömatriisiksi
     * @param koko Neliömatriisin alkuperäinen koko
     * @return Palauttaa täytetyn matriisin alkuperäisessä koossaan
     */
    private double[][] tayteNollatPois(double[][] taytetty, int koko) {
        double[][] trimmattu = new double[koko][koko];
        for (int rivi = 0; rivi < koko; rivi++) {
            for (int sarake = 0; sarake < koko; sarake++) {
                trimmattu[rivi][sarake] = taytetty[rivi][sarake];
            }
        }
        return trimmattu;
    }
}