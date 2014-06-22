package logiikka;

import apuneuvot.MatriisienKopioija;
import java.math.BigDecimal;

/**
 * Determinantti-luokka, jossa osittaistuetun LU-hajotelman avulla saadaan 
 * laskettua neliömatriisin determinantti.
 * 
 * @author Eversor
 */
public class Determinantti {

    private MatriisienKopioija kopioija;
    /**
     * Muuttuja, joka pitää kirjaa vaihdettavan rivin indeksistä.
     */
    private int vaihtorivi;
    /**
     * Muuttuja, joka pitää kirjaa osittaistuennasta johtuvista rivinvaihtojen
     * määrästä.
     */
    private int rivinvaihdot;
    
    /**
     * Konstruktori, joka luo uuden ilmentymän MatriisienKopioija-luokasta, joka
     * asetetaan sen private muuttujaan.
     *
     */
    public Determinantti(MatriisienKopioija kopioija) {
        this.kopioija = kopioija;
    }

    /**
     * Metodi, joka laskee parametrina annetun neliömatriisin determinantin.
     * Osittaistuetun LU-hajotelman avulla muodostetaan LU-matriisiin sen ylä- 
     * sekä alakolmiomatriisi, missä päälävistäjän alkiot ovat yläkolmio-
     * matriisin päälävistäjän alkiot. Jos LU-hajotelma epäonnistuu, olemme 
     * joutuneet tilanteeseen jossa jaetaan nollalla, joka on seuraus matriisin 
     * singulaarisuudesta, jolloin matriisin determinantti on nolla. Muulloin 
     * determinantti lasketaan LU-matriisin lävistäjäalkioiden tulona, jonka 
     * jälkeen se pyöristetään kahdeksan desimaalin tarkkuuteen. Lopuksi 
     * lisätään etumerkki rivinvaihtojen parillisuudesta riippuen.
     * 
     * @param matriisi Matriisi, jonka determinantti halutaan laskea, 
     *                 muotoa m x n
     * @return Palauttaa parametrina annetun matriisin determinantin
     */
    public double laskeDeterminantti(double[][] matriisi) {
        tarkasta(matriisi);
        rivinvaihdot = 0;
        
        double[][] LU = kopioija.kopioiNeliomatriisi(matriisi);
        boolean onnistui = muodostaLU(LU);

        if (!onnistui) {
            return 0;
        }
        
        return determinoi(LU);
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
                    + "joten sen determinanttia ei voida laskea");
        }
    }

    /**
     * Metodi, joka muodostaa LU-matriisin osittaistuetun LU-hajotelman avulla.
     * Lopussa LU-matriisiin muodostuu ylä- ja alakolmiomatriisi, jonka pää-
     * lävistäjän alkiot ovat yläkolmiomatriisin päälävistäjän alkiot. Palauttaa
     * true, jos LU-matriisin muodostaminen onnistui, ja false, jos jouduttiin
     * tilanteeseen missä pivot-alkion arvo on nolla eli olisimme joutuneet
     * jakamaan nollalla. Tarkempi selostus toteutusdokumentissa.
     * 
     * @param LU Matriisi, johon muodostuu sen ylä- ja alakolmiomatriisi, jonka
     *           päälävistäjän alkiot ovat yläkolmiomatriisin päälävistäjän
     *           alkiot, muotoa n x n
     * @return Palauttaa true, jos LU-hajotelma onnistui, ja false, jos pivot-
     *         alkion arvoksi jäi jossain vaiheessa nolla
     */
    private boolean muodostaLU(double[][] LU) {
        for (int lavistaja = 0; lavistaja < LU.length; lavistaja++) {
            double pivot = etsiPivot(LU, lavistaja);
            if(Math.abs(pivot) <= 0.001) {
                return false;
            } else if (vaihtorivi != -1 && vaihtorivi !=  lavistaja) {
                vaihdaRivit(LU, lavistaja);
            }
            hajota(LU, lavistaja);
        }
        return true;
    }
    
    /**
     * Metodi, joka etsii parametrina annetulle matriisille ja sen sarakkeelle 
     * pivot-alkion, joka on sarakeindeksin ja päälävistäjän leikkauksesta
     * lähtien sarakkeen itseisarvoltaan suurin alkio. Kirjaa ylös vaihtorivi-
     * muuttujaan pivot-alkion rivi-indeksin ja palauttaa lopuksi pivot-alkion 
     * arvon.
     * 
     * @param LU Matriisi, jonka sarakkeelle pivot-alkiota etsitään, muotoa n x n
     * @param sarake Sarakeindeksi, jolle pivot-alkiota etsitään
     * @return Palauttaa löydetyn pivot-alkion arvon
     */
    private double etsiPivot(double[][] LU, int sarake) {
        double pivotArvo = 0;
        vaihtorivi = -1;
        for (int rivi = sarake; rivi < LU.length; rivi++) {
            if (Math.abs(LU[rivi][sarake]) > pivotArvo) {
                pivotArvo = Math.abs(LU[rivi][sarake]);
                vaihtorivi = rivi;
            }
        }
        return pivotArvo;
    }

    /**
     * Metodi, joka vaihtaa parametrina annetun LU-matriisin kahden rivin 
     * paikkaa keskenään ja kasvattaa rivinvaihtojen määrää yhdellä.
     * 
     * @param LU Matriisi, jonka rivit vaihdetaan keskenään, muotoa n x n
     * @param rivi Rivin indeksi, joka vaihdetaan vaihtorivi-indeksin kanssa
     */
    private void vaihdaRivit(double[][] LU, int rivi) {
        double[] apu = LU[rivi];
        LU[rivi] = LU[vaihtorivi];
        LU[vaihtorivi] = apu;
        rivinvaihdot++;
    }
    
    /**
     * Metodi, joka toteuttaa LU-hajotelman algoritmin parametrina annetulle 
     * matriisille lävistäjäalkion 'k' osalta. Aluksi jakaa sarakkeen alkiot
     * lävistäjäalkiolla poislukien lävistäjäalkio ja sitä edeltävät alkiot, 
     * jonka jälkeen laskee Schurin komplementin arvot. Schurin komplementti on
     * siis lohkomatriisi, joka jää lävistäjäalkion sarakkeen ja rivin
     * rajaamaksi alueeksi alaoikealle.
     * 
     * @param LU Matriisi, jolle ylä- ja alakolmiomatriisi saadaan hajotettua
     *           lävistäjäalkion 'k' osalta, muotoa n x n
     * @param k Lävistäjäalkio, jonka osalta LU-hajotelma toteutetaan
     */
    private void hajota(double[][] LU, int k) {
        for (int rivi = k + 1; rivi < LU.length; rivi++) {
            LU[rivi][k] = LU[rivi][k] / LU[k][k];
            for (int sarake = k + 1; sarake < LU.length; sarake++) {
                LU[rivi][sarake] = LU[rivi][sarake] - LU[rivi][k] * LU[k][sarake];
            }
        }
    }

    /**
     * Metodi, joka laskee parametrina annetun LU-matriisin päälävistäjän tulon
     * ja lopuksi palauttaa sen.
     * 
     * @param LU LU-matriisi, jonka päälävistäjän tulo halutaan laskea,
     *           muotoa n x n
     * @return Palauttaa LU-matriisin päälävistäjän tulon
     */
    private double laskeLavistajatulo(double[][] LU) {
        double tulo = 1;
        for (int rivi = 0; rivi < LU.length; rivi++) {
            for (int sarake = 0; sarake < LU.length; sarake++) {
                if (rivi == sarake) {
                    tulo *= LU[rivi][sarake];
                }
            }
        }
        return tulo;
    }
    
    /**
     * Metodi, joka laskee determinantin LU-matriisin yläkolmiomatriisin
     * lävistäjäalkioiden tulona ja etumerkitsee sen rivinvaihtojen perusteella.
     * Lopuksi pyöristää determinantin kahdeksan desimaalin tarkkuuteen, mikäli
     * se ei ole ääretön.
     * 
     * @param LU Matriisi, josta determinantti lasketaan, muotoa n x n
     * @return Palauttaa etumerkityn ja mahdollisesti pyöristetyn matriisin
     */
    private double determinoi(double[][] LU) {
        double det = laskeLavistajatulo(LU);
        
        if(det == Double.POSITIVE_INFINITY || 
           det == Double.NEGATIVE_INFINITY){
            return etumerkitse(det);
        }
        return etumerkitse(pyorista(det));
    }
    
    /**
     * Metodi, joka pyöristää annetun determinantin kahdeksan desimaalin
     * tarkkuuteen ja lopuksi palauttaa sen.
     * 
     * @param det Determinantti, joka pyöristetään kahdesaan desimaaliin
     * @return Palauttaa pyöristetyn determinantin
     */
    private double pyorista(double det) {
        BigDecimal bd = new BigDecimal(Double.toString(det));
        bd = bd.setScale(8,BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    
    /**
     * Metodi, joka etumerkitsee parametrina annetun determinantin. Jos
     * osittaistuetussa LU-hajotelmassa syntyneiden rivinvaihtojen määrä on
     * parillinen, ei tee mitään. Parittomien tapauksessa vaihtaa etumerkin.
     * Palauttaa lopuksi etumerkityn determinantin.
     * 
     * @param det Determinantti, joka halutaan etumerkitä
     * @return Palauttaa etumerkityn determinantin
     */
    private double etumerkitse(double det){
        if (rivinvaihdot % 2 == 0) {
            return det;
        } else {
            return det * -1;
        }
    }
}