package blokus.logiikka;

import blokus.conf.GlobaalitMuuttujat;

/**
 * Pelaaja asettaa laattoja pelilaudalle. Laatalla on muoto, josta ilmenee sen
 * vuorovaikutus muiden pelilaudalla olevien laattojen suhteen. Laatan koko
 * vaikuttaa myös siitä saataviin pisteisiin.
 *
 * @author Simo Auvinen
 */
public class Laatta {

    private int koko;
    private int[][] muoto;
    private int[][] alkupainenMuoto;
    private int laatanID;
    private int pelaajanID;
    private int asento;
    private int ympari;

    /**
     *
     * @param koko Laatan koko ruutuina
     * @param muoto ennalta määrätty muoto laatalle
     * @param laatanID 
     * @param pelaajanID laatan omistavan pelaajan ID
     */
    public Laatta(int koko, int[][] muoto, int laatanID, int pelaajanID) {
        this.koko = koko;
        this.muoto = muoto;
        this.alkupainenMuoto = muoto;
        this.laatanID = laatanID;
        this.pelaajanID = pelaajanID;
        asento = 1;
        ympari = 0;

    }

    /**
     * Kääntää laattaa 90 astetta vasempaan
     */
    public void kaannaVasempaan() {
        int[][] kaannos = new int[GlobaalitMuuttujat.RUUDUKON_KOKO][GlobaalitMuuttujat.RUUDUKON_KOKO];

        for (int i = 0; i < GlobaalitMuuttujat.RUUDUKON_KOKO; i++) {
            for (int j = 0; j < GlobaalitMuuttujat.RUUDUKON_KOKO; j++) {
                kaannos[GlobaalitMuuttujat.RUUDUKON_KOKO - j - 1][i] = muoto[i][j];
            }
        }
        muoto = kaannos;
        if (asento > 1) {
            asento--;
        } else {
            asento = 4;
        }
    }

    /**
     * Kääntää laattaa 90 astetta oikeaan
     */
    public void kaannaOikeaan() {
        int[][] kaannos = new int[GlobaalitMuuttujat.RUUDUKON_KOKO][GlobaalitMuuttujat.RUUDUKON_KOKO];

        for (int i = 0; i < GlobaalitMuuttujat.RUUDUKON_KOKO; i++) {
            for (int j = 0; j < GlobaalitMuuttujat.RUUDUKON_KOKO; j++) {
                kaannos[j][GlobaalitMuuttujat.RUUDUKON_KOKO - i - 1] = muoto[i][j];
            }
        }
        muoto = kaannos;
        if (asento < 4) {
            asento++;
        } else {
            asento = 1;
        }

    }

    /**
     * Kääntää laatan Y akselinsa ympäri
     */
    public void kaannaYmpari() {
        int[][] kaannos = new int[GlobaalitMuuttujat.RUUDUKON_KOKO][GlobaalitMuuttujat.RUUDUKON_KOKO];

        for (int i = 0; i < GlobaalitMuuttujat.RUUDUKON_KOKO; i++) {
            for (int j = 0; j < GlobaalitMuuttujat.RUUDUKON_KOKO; j++) {
                kaannos[i][GlobaalitMuuttujat.RUUDUKON_KOKO - j - 1] = muoto[i][j];
            }
        }
        muoto = kaannos;
        if (ympari == 0) {
            ympari = 1;
        } else {
            ympari = 0;
        }
    }

    /**
     *
     */
    public void palautaAlkuperainenAsento() {
        muoto = alkupainenMuoto;
        ympari = 0;
        asento = 1;
    }

    /**
     *
     * @param as
     * @param ym
     */
    public void kaannaTiettyynAsentoon(int as, int ym) {
        palautaAlkuperainenAsento();
        if (ympari != ym) {
            kaannaYmpari();
        }
        while (asento != as) {
            kaannaOikeaan();
        }

    }

    /**
     *
     * @param i
     * @param j
     * @return palauttaa parametrien määrittämän kohdan laatan muodosta
     */
    public int getTaulukonArvo(int i, int j) {
        return muoto[i][j];
    }

    public int getAsento() {
        return asento;
    }

    public int getYmpari() {
        return ympari;
    }

    public int getKoko() {
        return koko;
    }

    public int[][] getMuoto() {
        return muoto;
    }

    public int getPelaajanID() {
        return pelaajanID;
    }

    public int getId() {
        return laatanID;
    }
}
