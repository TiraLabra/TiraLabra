/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package labyrintti;

import verkko.KoordinoituSolmu;

/**
 *
 * @author Arvoitusmies
 */
public class Labyrintti2D {

    /**
     * Merkki käytävälle
     */
    private static final char CORRIDOR = ' ';
    /**
     * Merkki seinälle
     */
    private static final char WALL = '#';

    /**
     * 2D taulukko KoordinoituSolmu olioille jotka tässä labyrintissä on.
     */
    private KoordinoituSolmu[][] solmut;

    /**
     *
     */
    private Labyrintitin labyrintitin;

    /**
     * Initialisoi solmut kentän tyhjillä, naapurittomilla,
     * KoordinoiduillaSolmuilla.
     *
     * @param korkeus
     * @param leveys
     */
    public Labyrintti2D(int korkeus, int leveys) {
        this.solmut = new KoordinoituSolmu[korkeus][leveys];
        for (int k = 0; k < solmut.length; k++) {
            for (int l = 0; l < solmut[0].length; l++) {
                Double[] koord = {k * 1.0, l * 1.0};
                solmut[k][l] = new KoordinoituSolmu(koord);
            }
        }
    }

    /**
     * Asettaa käytettävän labyrintingeneroimisalgoritmin.
     *
     * @param l
     */
    public void setLabyrintitin(Labyrintitin l) {
        labyrintitin = l;
    }

    /**
     * GetSolmut
     *
     * @return solmut
     */
    public KoordinoituSolmu[][] getSolmut() {
        return solmut;
    }

    /**
     *
     */
    public void labyrintitaLabyrintti() {
        if (labyrintitin == null) {
            throw new IllegalStateException("Aseta labyrintitin ensin (setLabyrintitin)");
        }
        solmut = labyrintitin.labyrintitaLabyrintti();
    }

    /**
     * True jos (x,y) kohdan solmulla on käytävä oikealle.
     *
     * @param x
     * @param y
     * @return
     */
    private boolean onkoOikeaNaapuri(int x, int y) {
        if (y == solmut.length - 1) {
            return false;
        }
        KoordinoituSolmu solmu = solmut[x][y];
        KoordinoituSolmu oikeaSolmu = solmut[x][y + 1];
        if (solmu.onkoNaapuri(oikeaSolmu)) {
            return true;
        }
        return false;
    }

    /**
     * True jos (x,y) kohdan solmulla on käytävä alas.
     *
     * @param x
     * @param y
     * @return
     */
    private boolean onkoAlaNaapuri(int x, int y) {
        if (x == solmut[0].length - 1) {
            return false;
        }
        KoordinoituSolmu solmu = solmut[x][y];
        KoordinoituSolmu alaSolmu = solmut[x + 1][y];
        if (solmu.onkoNaapuri(alaSolmu)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        char[][] merkkiTauluEsitys = new char[solmut.length * 2 + 1][solmut[0].length * 2 + 1];

        //ylä ja alareuna
        for (int i = 0; i < merkkiTauluEsitys[0].length; i++) {
            merkkiTauluEsitys[0][i] = WALL;
            merkkiTauluEsitys[merkkiTauluEsitys.length - 1][i] = WALL;
        }

        //oikea ja vasen reuna
        for (int i = 0; i < merkkiTauluEsitys.length; i++) {
            merkkiTauluEsitys[i][0] = WALL;
            merkkiTauluEsitys[i][merkkiTauluEsitys[0].length - 1] = WALL;
        }

        for (int i = 1; i < merkkiTauluEsitys.length - 1; i++) {
            for (int j = 1; j < merkkiTauluEsitys[0].length - 1; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    merkkiTauluEsitys[i][j] = CORRIDOR;
                } else if (j % 2 == 0 && i % 2 == 0) {
                    merkkiTauluEsitys[i][j] = WALL;
                } else if (j % 2 == 0 && i % 2 == 1) {
                    if (onkoOikeaNaapuri(i >> 1, (j >> 1) - 1)) {
                        merkkiTauluEsitys[i][j] = CORRIDOR;
                    } else {
                        merkkiTauluEsitys[i][j] = WALL;
                    }
                } else if (j % 2 == 1 && i % 2 == 0) {
                    if (onkoAlaNaapuri((i >> 1) - 1, j >> 1)) {
                        merkkiTauluEsitys[i][j] = CORRIDOR;
                    } else {
                        merkkiTauluEsitys[i][j] = WALL;
                    }
                } else {
                    //should never happen
                    throw new Error("jotain pahasti pielessä");
                }
            }
        }
        String paluu = "";
        for (int i = 0; i < merkkiTauluEsitys.length; i++) {
            paluu += (charArrayToString(merkkiTauluEsitys[i]) + "\n");
        }
        return paluu;
    }

    /**
     * Tällänen apumetodi tuohon tulostukseen.
     *
     * @param ca
     * @return
     */
    private String charArrayToString(char[] ca) {
        String paluu = "";
        for (char c : ca) {
            paluu += c;
        }
        return paluu;
    }

}
