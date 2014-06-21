package logiikka;

import apuneuvot.MatriisienGeneroija;
import apuneuvot.MatriisienKopioija;

/**
 * Kaanteismatriisi-luokka, jossa Gaussin eliminointi-menetelmän ja takaisin-
 * sijoituksen avulla saadaan approksimoitua kääntyvän neliömatriisin
 * käänteismatriisi.
 *
 * @author Eversor
 */
public class Kaanteismatriisi {

    private MatriisienKopioija kopioija;
    private MatriisienGeneroija generoija;

    /**
     * Konstruktori, joka luo uuden ilmentymän MatriisienKopioija-luokasta, joka
     * asetetaan sen private muuttujaan.
     *
     */
    public Kaanteismatriisi(MatriisienKopioija kopioija, 
                            MatriisienGeneroija generoija) {
        this.kopioija = kopioija;
        this.generoija = generoija;
    }

    /**
     * Metodi, joka laskee parametrina annetun matriisin käänteismatriisin.
     * Matriisit A ja I muodostavat laajennetun matriisin, johon sovelletaan
     * Gaussin eliminointi-menetelmää ja lopuksi takaisinsijoituksen avulla
     * saadaan muodostettua haluttu käänteismatriisi, joka palautetaan.
     * Tarkempi selostus toteutusdokumentissa.
     *
     * @param matriisi Matriisi, jonka käänteismatriisi halutaan laskea, 
     *                 muotoa m x n
     * @return Palauttaa parametrina annetun matriisin käänteismatriisin
     */
    public double[][] invertoi(double[][] matriisi) {
        tarkasta(matriisi);

        double[][] A = kopioija.kopioiNeliomatriisi(matriisi);
        double[][] I = generoija.luoYksikkomatriisi(matriisi.length);

        muodostaLU(A, I);
        suhteuta(A, I);        
        return takaisinsijoita(A, I);
    }

    /**
     * Metodi, joka tarkastaa onko parametrina annettu matriisi neliömatriisi.
     * Heittää poikkeuksen, jos ehto ei täyty.
     * 
     * @param matriisi Matriisi, josta halutaan selvittää onko neliömatriisi,
     *                 muotoa m x n
     */
    private void tarkasta(double[][] matriisi) {
        if (matriisi.length != matriisi[0].length) {
            throw new IllegalArgumentException("Matriisi ei ole neliömatriisi,"
                    + "joten sen käänteismatriisia ei voida muodostaa");
        }
    }

    /**
     * Metodi, joka muodostaa matriisiin A sen LU-hajotelman skaalatun osittais-
     * tuennan avulla. Lopussa siis matriisiin A muodostuu sen ylä- ja
     * alakolmiomatriisi, jonka päälävistäjän alkiot ovat yläkolmiomatriisin
     * päälävistäjän alkiot. Tarkempi selostus toteutusdokumentissa.
     *
     * @param A Matriisi, johon muodostuu sen ylä- ja alakolmiomatriisi, jonka
     *          päälävistäjän alkiot ovat yläkolmiomatriisin päälävistäjän 
     *          alkiot, muotoa n x n
     * @param I Yksikkömatriisi, joka laajentaa matriisia A, muotoa n x n
     */
    private void muodostaLU(double[][] A, double[][] I) {
        double[] tekijat = new double[A.length];
        etsiSkaalaustekijat(A, tekijat);
        
        for (int lavistaja = 0; lavistaja < A.length; lavistaja++) {
            vaihdaRivit(A, I, lavistaja, etsiPivotRivi(A, tekijat, lavistaja));
            hajota(A, lavistaja);
        }
    }

    /**
     * Metodi, joka etsii parametrina annetun matriisin A skaalaustekijät ja
     * tallettaa ne tekijat-taulukkoon. Skaalaustekijä on matriisin tutkittavana 
     * olevan rivin itseisarvoltaan suurin alkio.
     * 
     * @param A Matriisi, jonka skaalaustekijät halutaan selvittää, muotoa n x n
     * @param tekijat Taulukko, johon talletetaan jokaisen rivin skaalaustekijä
     */
    private void etsiSkaalaustekijat(double[][] A, double[] tekijat) {
        for (int rivi = 0; rivi < A.length; rivi++) {
            double suurin = 0;
            for (int sarake = 0; sarake < A.length; sarake++) {
                if (Math.abs(A[rivi][sarake]) > suurin) {
                    suurin = Math.abs(A[rivi][sarake]);
                }
            }
            tekijat[rivi] = suurin;
        }
    }

    /**
     * Metodi, joka etsii parametrina annetun matriisin A sarakkeelle pivot-
     * rivin eli indeksin miltä riviltä löytyy sarakkeen pivot-alkio. Pivot-
     * alkio on sarakeindeksin ja päälävistäjän leikkauksesta lähtien sarakkeen 
     * itseisarvoltaan suurin alkio suhteutettuna rivin skaalaustekijään. 
     * Palauttaa lopussa rivin indeksin, josta löytyi sarakkeen pivot-alkio.
     * 
     * @param A Matriisi, jonka pivot-rivi halutaan selvittää, muotoa n x n
     * @param tekijat Taulukko, joka sisältää matriisin A skaalaustekijät
     * @param sarake Lävistäjäalkion sarake, josta etsitää pivot-alkiota
     * @return Palauttaa rivin indeksin, josta pivot-alkio löytyi
     */
    private int etsiPivotRivi(double[][] A, double[] tekijat, int sarake) {
        double pivotArvo = 0;
        int pivotRivi = 0;
        for (int rivi = sarake; rivi < A.length; rivi++) {
            if (Math.abs(A[rivi][sarake]) / tekijat[rivi] > pivotArvo) {
                pivotArvo = Math.abs(A[rivi][sarake]) / tekijat[rivi];
                pivotRivi = rivi;
            }
        }
        return pivotRivi;
    }

    /**
     * Metodi, joka vaihtaa parametrina annettujen matriisien rivien paikat
     * annetun vaihtorivin kanssa.
     * 
     * @param A Matriisi, jonka rivit vaihdetaan keskenään, muotoa n x n
     * @param I Matriisi, jonka rivit vaihdetaan keskenään, muotoa n x n
     * @param rivi Rivin indeksi, joka vaihdetaan vaihtorivi-indeksin kanssa
     * @param vaihtorivi Rivin indeksi, joka vaihdetaan rivi-indeksin kanssa
     */
    private void vaihdaRivit(double[][] A, double[][] I, int rivi, 
                                                         int vaihtorivi) {
        double[] apu = A[rivi];
        A[rivi] = A[vaihtorivi];
        A[vaihtorivi] = apu;

        apu = I[rivi];
        I[rivi] = I[vaihtorivi];
        I[vaihtorivi] = apu;
    }

    /**
     * Metodi, joka toteuttaa LU-hajotelman algoritmin parametrina annetulle 
     * matriisille lävistäjäalkion 'k' osalta. Aluksi jakaa sarakkeen alkiot
     * lävistäjäalkiolla poislukien lävistäjäalkio ja sitä edeltävät alkiot, 
     * jonka jälkeen laskee Schurin komplementin arvot. Schurin komplementti on
     * siis lohkomatriisi, joka jää lävistäjäalkion sarakkeen ja rivin
     * rajaamaksi alueeksi alaoikealle.
     * 
     * @param A Matriisi, jolle ylä- ja alakolmiomatriisi saadaan hajotettua
     *          lävistäjäalkion 'k' osalta, muotoa n x n
     * @param k Lävistäjäalkio, jonka osalta LU-hajotelma toteutetaan
     */
    private void hajota(double[][] A, int k) {
        for (int rivi = k + 1; rivi < A.length; rivi++) {
            A[rivi][k] = A[rivi][k] / A[k][k];
            for (int sarake = k + 1; sarake < A.length; sarake++) {
                A[rivi][sarake] = A[rivi][sarake] - A[rivi][k] * A[k][sarake];
            }
        }
    }

    /**
     * Metodi, joka tekee parametrina annettuun (mahdollisesti) rivimuunneltuun 
     * yksikkömatriisiin I suhteessa ne muutokset, jotka ovat tehty matriisiin A 
     * sen LU-hajotelman aikana, käyttäen hyväksi A:n alakolmiomatriisin arvoja
     * ja eteenpäin sijoitusta.
     * 
     * @param A Matriisi, joka on muutettu LU-muotoon, muotoa n x n
     * @param I Matriisi, joka halutaan suhteuttaa A:n muutoksiin nähden, 
     *          muotoa n x n
     */
    private void suhteuta(double[][] A, double[][] I) {
        for (int lavistaja = 0; lavistaja < A.length; lavistaja++) {
            for (int rivi = lavistaja + 1; rivi < A.length; rivi++) {
                for (int sarake = 0; sarake < A.length; sarake++) {
                    I[rivi][sarake] -= A[rivi][lavistaja] * I[lavistaja][sarake];
                }
            }
        }
    }

    /**
     * Metodi, joka laskee parametreina annettujen matriisien A ja I avulla 
     * halutun käänteismatriisin approksimaation käyttäen takaisinsijoitusta A:n 
     * yläkolmiomatriisille. Takaisinsijoituksessa peruskaavan mukaisesti I:n
     * sarakkeet ajatellaan käänteismatriisin ratkaisusarakkeiksi ja nämä
     * ratkaistaan A:n yläkolmiomatriisin avulla alhaalta ylöspäin.
     * 
     * @param A Matriisi, jonka yläkolmiomatriisia käytetään takaisinsijoituksen
     *          apuna, muotoa n x n
     * @param I Matriisi, jonka sarakkeiden avulla muodostetaan takaisin-
     *          sijoituksessa käänteismatriisin sarakkeet, muotoa n x n
     * @return Palauttaa halutun käänteismatriisin approksimaation, muotoa n x n
     */
    private double[][] takaisinsijoita(double[][] A, double[][] I) {
        int koko = A.length;
        double[][] X = new double[koko][koko];
        
        for (int sarake = 0; sarake < koko; sarake++) {
            X[koko - 1][sarake] += I[koko - 1][sarake] / A[koko - 1][koko - 1];
            for (int rivi = koko - 2; rivi >= 0; rivi--) {
                X[rivi][sarake] = I[rivi][sarake];
                for (int k = rivi + 1; k < koko; k++) {
                    X[rivi][sarake] -= A[rivi][k] * X[k][sarake];
                }
                X[rivi][sarake] /= A[rivi][rivi];
            }
        }
        return X;
    }
}