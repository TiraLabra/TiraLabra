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

    /**
     * Konstruktori, jolle annetaan parametrina Yhteenlasku- ja Vahennyslasku-
     * luokan ilmentymät. Ilmentymät asetetaan niiden private muuttujiin. 
     * 
     * @param yhteenlasku Yhteenlasku-luokan ilmentymä, joka pitää sisällään
     *                    samankokoisten matriisien yhteenlaskuoperaatiot
     * @param vahennyslasku Vahennyslasku-luokan ilmentymä, joka pitää
     *                      sisällään samankokoisten matriisien vähennyslasku-
     *                      operaatiot
     */
    public Strassen(Yhteenlasku yhteenlasku, Vahennyslasku vahennyslasku) {
        this.yhteenlasku = yhteenlasku;
        this.vahennyslasku = vahennyslasku;
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
            double[][] tulomatriisi = new double[1][1];
            tulomatriisi[0][0] += eka[0][0] * toka[0][0];
            return tulomatriisi;
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
     * avulla saadaan lopuksi koottua haluttu tulomatriisi, joka palautetaan. 
     * 
     * Puolittaa aluksi neliömatriisin koon (ei neliömatriisia), jonka jälkeen 
     * tarkastaa mennäänkö alle yhden, jolloin palauttaa yhden alkion 
     * tulomatriisin. Tämän jälkeen alustaa neliömatriisien lohkomatriisit 
     * puolitetulla koolla ja sopivilla alkioilla, jotta muodostuu ositus 
     * neliömatriisin vasemmalle ja oikealle ylä- ja alanurkalle.
     * 
     * Tämän jälkeen laskee rekursiivisesti sopivien lohkomatriisien summien
     * ja erotusten avulla 7 eri tulomatriisia, joita summaamalla ja
     * vähentämällä saadaan muodostettua halutun tulomatriisin lohkomatriisi-
     * esitys. Lopuksi kasataan lohkomatriiseista haluttu tulomatriisi ja
     * palautetaan se.
     * 
     * @param eka Vasemmalta luettuna ensimmäinen neliömatriisi muotoa n x n
     * @param toka Vasemmalta luettuna toinen neliömatriisi muotoa n x n
     * @return Palauttaa halutun tulomatriisin muotoa n x n
     */
    private double[][] rekursioAlgo(double[][] eka, double[][] toka) {
        int puolet = eka.length / 2;

        if (puolet == 0) {
            double[][] C = new double[1][1];
            C[0][0] += eka[0][0] * toka[0][0];
            return C;
        }

        double[][] eka11 = new double[puolet][puolet];
        double[][] eka12 = new double[puolet][puolet];
        double[][] eka21 = new double[puolet][puolet];
        double[][] eka22 = new double[puolet][puolet];

        double[][] toka11 = new double[puolet][puolet];
        double[][] toka12 = new double[puolet][puolet];
        double[][] toka21 = new double[puolet][puolet];
        double[][] toka22 = new double[puolet][puolet];

        for (int rivi = 0; rivi < puolet; rivi++) {
            for (int sarake = 0; sarake < puolet; sarake++) {
                eka11[rivi][sarake] = eka[rivi][sarake];
                eka12[rivi][sarake] = eka[rivi][sarake + puolet];
                eka21[rivi][sarake] = eka[rivi + puolet][sarake];
                eka22[rivi][sarake] = eka[rivi + puolet][sarake + puolet];

                toka11[rivi][sarake] = toka[rivi][sarake];
                toka12[rivi][sarake] = toka[rivi][sarake + puolet];
                toka21[rivi][sarake] = toka[rivi + puolet][sarake];
                toka22[rivi][sarake] = toka[rivi + puolet][sarake + puolet];
            }
        }

        double[][] P1 = rekursioAlgo(this.yhteenlasku.summaa(eka11, eka22),
                                     this.yhteenlasku.summaa(toka11, toka22));
        double[][] P2 = rekursioAlgo(this.yhteenlasku.summaa(eka21, eka22),
                                     toka11);
        double[][] P3 = rekursioAlgo(eka11,
                                     this.vahennyslasku.vahenna(toka12, toka22));
        double[][] P4 = rekursioAlgo(eka22,
                                     this.vahennyslasku.vahenna(toka21, toka11));
        double[][] P5 = rekursioAlgo(this.yhteenlasku.summaa(eka11, eka12),
                                     toka22);
        double[][] P6 = rekursioAlgo(this.vahennyslasku.vahenna(eka21, eka11),
                                     this.yhteenlasku.summaa(toka11, toka12));
        double[][] P7 = rekursioAlgo(this.vahennyslasku.vahenna(eka12, eka22),
                                     this.yhteenlasku.summaa(toka21, toka22));

        double[][] C11 = this.vahennyslasku.vahenna(this.yhteenlasku.summaa(
                         this.yhteenlasku.summaa(P1, P4), P7), P5);
        double[][] C12 = this.yhteenlasku.summaa(P3, P5);
        double[][] C21 = this.yhteenlasku.summaa(P2, P4);
        double[][] C22 = this.vahennyslasku.vahenna(this.yhteenlasku.summaa(
                         this.yhteenlasku.summaa(P1, P3), P6), P2);

        double[][] C = new double[eka.length][eka.length];
        for (int rivi = 0; rivi < puolet; rivi++) {
            for (int sarake = 0; sarake < puolet; sarake++) {
                C[rivi][sarake] = C11[rivi][sarake];
                C[rivi][sarake + puolet] = C12[rivi][sarake];
                C[rivi + puolet][sarake] = C21[rivi][sarake];
                C[rivi + puolet][sarake + puolet] = C22[rivi][sarake];
            }
        }
        return C;
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