package blokus.logiikka;

import blokus.conf.GlobaalitMuuttujat;

/**
 * Jokaisella pelaajalla on oma näkökulmansa pelilaudalle, siis mihin hän voi
 * laattansa asettaa. Tarkastus laudalta tarkastetaan voiko pelaaja asettaa
 * laattansa tiettyyn kohtaa lautaa.
 *
 * @author Simo Auvinen
 */
public class TarkastusLauta {

    int[][] tLauta;

    /**
     *
     * @param pelaajanId
     */
    public TarkastusLauta(int pelaajanId) {
        tLauta = new int[GlobaalitMuuttujat.LAUDAN_KOKO][GlobaalitMuuttujat.LAUDAN_KOKO];
        alusta(pelaajanId);
    }

    /**
     * Metodi tarkistaa tarkistus laudasta onnistuuko laatan lisääminen
     * kyseiseen kohtaan. Siis että laatta on kulmittain edelliseen nähden ja
     * muutenkin sääntöjen mukaan asetettu.
     *
     * @param laatta
     * @param y
     * @param x
     * @return Virheteksin jos lisääminen ei onnistu tai tyhjän tekstin jos
     * lisääminen onnistuu
     */
    public String tarkistaVoikoLisata(Laatta laatta, int y, int x) {
        String laattaSalKohtaan = " Laatan tulee olla kulmittain jo asetettuun nähden!";
        for (int i = 0; i < GlobaalitMuuttujat.RUUDUKON_KOKO; i++) {
            for (int j = 0; j < GlobaalitMuuttujat.RUUDUKON_KOKO; j++) {
                if (!onkoLaudalla(y, x, i, j) && laatta.getMuoto()[i][j] == GlobaalitMuuttujat.LAATTA) {
                    return " Laatan tulee olla kokonaan laudalla!";
                } else if (onkoLaudalla(y, x, i, j)) {
                    if (laatta.getMuoto()[i][j] == GlobaalitMuuttujat.LAATTA && tLauta[y + i - 3][x + j - 3] == GlobaalitMuuttujat.KIELLETTY_ALUE) {
                        return " Laattan sivu ei saa koskea jo asetettuun laattaan!";
                    } else if (laatta.getMuoto()[i][j] == GlobaalitMuuttujat.LAATTA && tLauta[y + i - 3][x + j - 3] == GlobaalitMuuttujat.LAATTA) {
                        return " Laattaa ei voi asettaa jo asetetun laatan päälle!";
                    }
                    if (laatta.getMuoto()[i][j] == GlobaalitMuuttujat.LAATTA && tLauta[y + i - 3][x + j - 3] == GlobaalitMuuttujat.KULMA) {
                        laattaSalKohtaan = "";
                    }
                }
            }
        }
        return laattaSalKohtaan;
    }

    /**
     * Muuttaa halutun koodin tarkastus laudalle.
     *
     * @param koodi
     * @param y
     * @param x
     */
    public void lisaaLaatta(int koodi, int y, int x) {
        if (!(y < 0 || x < 0 || y >= GlobaalitMuuttujat.LAUDAN_KOKO || x >= GlobaalitMuuttujat.LAUDAN_KOKO)) {
            if (tLauta[y][x] != GlobaalitMuuttujat.LAATTA) {
                if (!(koodi == GlobaalitMuuttujat.KULMA &&  tLauta[y][x] == GlobaalitMuuttujat.KIELLETTY_ALUE)) {
                    tLauta[y][x] = koodi;
                }
                
            }

        }

    }

    /**
     *
     * @param y
     * @param x
     * @param i
     * @param j
     * @return Palauttaa true jos kohta on tarkastus laudan sisäpuolella.
     */
    public boolean onkoLaudalla(int y, int x, int i, int j) {
        return y + i - 3 >= 0 && y + i - 3 < GlobaalitMuuttujat.LAUDAN_KOKO && x + j - 3 >= 0 && x + j - 3 < GlobaalitMuuttujat.LAUDAN_KOKO;
    }

    private void alusta(int id) {
        for (int i = 0; i < tLauta.length; i++) {
            for (int j = 0; j < tLauta.length; j++) {
                tLauta[i][j] = 0;
            }
        }
        if (id == 1) {
            tLauta[0][0] = 1;
        } else if (id == 2) {
            tLauta[0][tLauta.length - 1] = 1;
        } else if (id == 4) {
            tLauta[tLauta.length - 1][0] = 1;
        } else if (id == 3) {
            tLauta[tLauta.length - 1][tLauta.length - 1] = 1;
        }
    }

    public int[][] gettLauta() {
        return tLauta;
    }
}
