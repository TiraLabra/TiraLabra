package logiikka;

/**
 * Determinantti-luokka, jossa osittaistuetun LU-hajotelman avulla saadaan 
 * laskettua neliömatriisin determinantti.
 * 
 * @author Eversor
 */
public class Determinantti {

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
     * Metodi, joka laskee parametrina annetun neliömatriisin determinantin.
     * Tarkastaa aluksi onko annettu matriisi neliömatriisi ja alustaa rivin-
     * vaihtojen määrän nollaksi. Tämän jälkeen LU-matriisiin kopioidaan
     * annetun matriisin sisältö. Seuraavaksi osittaistuetun LU-hajotelman
     * avulla muodostetaan LU-matriisiin sen ylä- sekä alakolmiomatriisi, missä
     * päälävistäjän alkiot ovat yläkolmiomatriisin päälävistäjän alkiot. 
     * 
     * Jos LU-hajotelma epäonnistuu, olemme joutuneet tilanteeseen jossa jaetaan
     * nollalla, joka on seuraus matriisin singulaarisuudesta eli epäsäännölli-
     * syydestä. Tässä tilanteessa boolean-muuttuja 'onnistui' saa arvon false 
     * ja metodi palauttaa determinantin arvoksi nollan. LU-hajotelman
     * onnistuttua determinantti lasketaan LU-matriisin lävistäjäalkioiden
     * tulona, johon lopuksi lisätään etumerkki rivinvaihtojen parillisuudesta
     * riippuen. Tämän jälkeen etumerkkitty determinantti palautetaan.
     * 
     * @param matriisi Matriisi, jonka determinantti halutaan laskea, 
     *                 muotoa m x n
     * @return Palauttaa parametrina annetun matriisin determinantin
     */
    public double laskeDeterminantti(double[][] matriisi) {
        tarkasta(matriisi);
        rivinvaihdot = 0;
        
        double[][] LU = kopioi(matriisi);
        boolean onnistui = muodostaLU(LU);

        if (!onnistui) {
            return 0;
        }

        double det = laskeLavistajatulo(LU);

        return etumerkitse(det);
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
     * Metodi, joka kopioi annetun matriisin sisällön LU-matriisin sisällöksi ja
     * lopuksi palauttaa vastakopioidun matriisin.
     * 
     * @param matriisi Matriisi, jonka sisältö kopioidaan LU-matriisin
     *                 sisällöksi, muotoa n x n
     * @return Palauttaa parametrina annetun matriisin kopion muotoa n x n
     */
    private double[][] kopioi(double[][] matriisi) {
        double[][] LU = new double[matriisi.length][matriisi.length];
        for (int rivi = 0; rivi < matriisi.length; rivi++) {
            for (int sarake = 0; sarake < matriisi[0].length; sarake++) {
                LU[rivi][sarake] = matriisi[rivi][sarake];
            }
        }
        return LU;
    }

    /**
     * Metodi, joka muodostaa LU-matriisin osittaistuetun LU-hajotelman avulla.
     * Lopussa LU-matriisiin muodostuu ylä- ja alakolmiomatriisi, jonka pää-
     * lävistäjän alkiot ovat yläkolmiomatriisin päälävistäjän alkiot. Palauttaa
     * true, jos LU-matriisin muodostaminen onnistui, ja false, jos jouduttiin
     * tilanteeseen missä pivot-alkion arvo on nolla eli olisimme joutuneet
     * jakamaan nollalla.
     * 
     * Algoritmi käy järjestyksessä matriisin lävistäjäalkiot läpi etsien aluksi
     * lävistäjäalkion sarakkeelle pivot-alkion. Pivot-alkio on lävistäjä-
     * alkiosta lähtien kyseisen sarakkeen itseisarvoltaan suurin alkio. Pivot-
     * alkion etsintä estää nollalla jakamisen algoritmin myöhemmässä vaiheessa 
     * ja algoritmi palauttaakin falsen, jos pivot-alkion arvoksi jää nolla.
     * 
     * Kun löytyy nollasta eroava pivot-alkio, sen rivi ja lävistäjäalkion rivi
     * vaihtavat paikkaa keskenään ja rivinvaihto-laskurin arvo kasvaa yhdellä,
     * joka mahdollisesti vaikuttaa LU-matriisista laskettavan determinantin
     * arvoon.
     * 
     * Viimeisessä vaiheessa hajotetaan matriisi kyseessä olevan lävistäjäalkion
     * osalta LU-hajotelmalla. Tämä jakaa lävistäjäalkion sarakkeen alapuoliset
     * alkiot lävistäjäalkiolla ja laskee Schurin komplementin arvot. Tähän
     * mennessä päälävistäjän alapuolelle lävistäjäalkioon asti on löytynyt
     * alakolmiomatriisin alkiot ja yläpuolelle päälävistäjän ja lävistäjä-
     * alkion rivin rajaamalle yläosalle yläkolmiomatriisin alkiot.
     * 
     * Uusilla iteraatioilla algoritmi toimii edellisen iteraation lopussa
     * lasketussa Schurin komplementissa eli lohkomatriisissa, joka on 
     * lävistäjäalkion sarakkeen ja rivin rajaama alue alaoikealla.
     * 
     * @param LU Matriisi, johon muodostuu sen ylä- ja alakolmiomatriisi, jonka
     *           päälävistäjän alkiot ovat yläkolmiomatriisin päälävistäjän
     *           alkiot, muotoa n x n
     * @return Palauttaa true, jos LU-hajotelma onnistui, ja false, jos pivot-
     *         alkion arvoksi jäi jossain vaiheessa nolla
     */
    private boolean muodostaLU(double[][] LU) {
        for (int lavistaja = 0; lavistaja < LU.length; lavistaja++) {
            if(etsiPivot(LU, lavistaja) == 0) {
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