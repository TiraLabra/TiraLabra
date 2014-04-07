package blokus.AI;

import blokus.conf.GlobaalitMuuttujat;
import blokus.logiikka.Laatta;

/**
 * Kuvaa laudalle mahdollisesti asetettavaa siirtoa ja sen hyvyyttä
 * @author Ilkimys
 */
public class Siirto {

    private int kohdeI;
    private int kohdeJ;
    private int ympari;
    private int asento;
    private Laatta laatta;
    private int[][] tlauta;
    private int hyvyys;

    /**
     * Luo halutun siirron
     * @param kohdeI
     * @param kohdeJ
     * @param laatta
     */
    public Siirto(int kohdeI, int kohdeJ, Laatta laatta, int[][] tlauta, int alkuperäisetSiirrot) {
        this.kohdeI = kohdeI;
        this.kohdeJ = kohdeJ;
        this.laatta = laatta;
        ympari = laatta.getYmpari();
        asento = laatta.getAsento();
        this.tlauta = tlauta;

        pisteytaSiirto(alkuperäisetSiirrot);
    }

    
    /*
     * Pisteyttää halutun siirron
     */
    private void pisteytaSiirto(int aS) {
        hyvyys = laatta.getKoko() + laskeuudetPaikat(aS);
    }

    /*
     * Laskee siirron tuottamat uudet asettamis paikat
     */

    private int laskeuudetPaikat(int aS) {
        int[][] testiTLauta = kopioiLauta(tlauta);
        testaaLaatta(testiTLauta, kohdeI, kohdeJ);
        return laskeMaara(testiTLauta, 1) - aS;
       
       
    }

    private void testaaLaatta(int[][] l, int y, int x) {
        for (int i = 0; i < GlobaalitMuuttujat.RUUDUKON_KOKO; i++) {
            for (int j = 0; j < GlobaalitMuuttujat.RUUDUKON_KOKO; j++) {

                if (laatta.getMuoto()[i][j] == GlobaalitMuuttujat.LAATTA) {
                    l[y + i - 3][x + j - 3] = 3;

                } else if (laatta.getMuoto()[i][j] == GlobaalitMuuttujat.KULMA && onkoLaudalla(y, x, i, j)) {
                    l[y + i - 3][x + j - 3] = 1;

                } else if (laatta.getMuoto()[i][j] == GlobaalitMuuttujat.KIELLETTY_ALUE && onkoLaudalla(y, x, i, j)) {

                    l[y + i - 3][x + j - 3] = 2;
                }
            }
        }
    }

    private boolean onkoLaudalla(int y, int x, int i, int j) {
        return y + i - 3 >= 0 && y + i - 3 < GlobaalitMuuttujat.LAUDAN_KOKO && x + j - 3 >= 0 && x + j - 3 < GlobaalitMuuttujat.LAUDAN_KOKO;
    }

    private int laskeMaara(int[][] testiTLauta, int luku) {
        int paikat = 0;
        for (int i = 0; i < testiTLauta.length; i++) {
            for (int j = 0; j < testiTLauta.length; j++) {
                if ( testiTLauta[i][j] == luku) {
                    paikat++;
                }
                
            }
            
        }
        return paikat;
    }
    
    private int[][] kopioiLauta(int[][] l) {
        int[][] kopio = new int[l.length][l[0].length];
        for (int i = 0; i < l.length; i++) {
            for (int j = 0; j < l.length; j++) {
                kopio[i][j] = l[i][j];
                
            }
            
        }
        return kopio;
    }
    
        public int getAsento() {
        return asento;
    }

    public int getYmpari() {
        return ympari;
    }
    public int getHyvyys() {
        return hyvyys;
    }

    public int getKohdeI() {
        return kohdeI;
    }

    public int getKohdeJ() {
        return kohdeJ;
    }

    public Laatta getLaatta() {
        return laatta;
    }
}
