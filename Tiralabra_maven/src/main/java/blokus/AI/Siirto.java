package blokus.AI;

import blokus.conf.GlobaalitMuuttujat;
import blokus.logiikka.Laatta;
import blokus.logiikka.PeliLauta;
import blokus.logiikka.TarkastusLauta;
import hashMap.OmaHashMap;

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
    private PeliLauta p;

    /**
     * Luo halutun siirron
     * @param kohdeI
     * @param kohdeJ
     * @param laatta
     * @param tlauta 
     * @param alkuperäisetSiirrot
     * @param p  
     */
    public Siirto(int kohdeI, int kohdeJ, Laatta laatta, int[][] tlauta, int alkuperäisetSiirrot, PeliLauta p) {
        this.kohdeI = kohdeI;
        this.kohdeJ = kohdeJ;
        this.laatta = laatta;
        ympari = laatta.getYmpari();
        asento = laatta.getAsento();
        this.tlauta = tlauta;
        this.p = p;

        pisteytaSiirto(alkuperäisetSiirrot);
    }

    
    /*
     * Pisteyttää halutun siirron
     */
    private void pisteytaSiirto(int aS) {
        hyvyys = laatta.getKoko() + laskeuudetPaikat(aS) *3 + laskeTorppaukset()*2;
    }

    /*
     * Laskee siirron tuottamat uudet asettamis paikat
     */

    private int laskeuudetPaikat(int aS) {
        int[][] testiTLauta = kopioiLauta(tlauta);
        testaaLaatta(testiTLauta, kohdeI, kohdeJ);
        return laskeMaara(testiTLauta, 1) - aS;
       
       
    }

    private int[][] testaaLaatta(int[][] l, int y, int x) {
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
        return l;
    }
    
        private int[][] testaaVainLaattaa(int[][] l, int y, int x) {
        for (int i = 0; i < GlobaalitMuuttujat.RUUDUKON_KOKO; i++) {
            for (int j = 0; j < GlobaalitMuuttujat.RUUDUKON_KOKO; j++) {

                if (laatta.getMuoto()[i][j] == GlobaalitMuuttujat.LAATTA) {
                    l[y + i - 3][x + j - 3] = 3;
                }
            }
        }
        return l;
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
    
    private int laskeTorppaukset(){
        OmaHashMap<Integer,TarkastusLauta> tarLaudat = p.getTarkastusLaudat();
        int torppaus = 0;
        for (TarkastusLauta l : tarLaudat.getDatas()) {
           int apu = laskeMaara(l.getLauta(), 1);
           int [][] kopio = kopioiLauta(l.getLauta());
           int[][] mahdol = testaaVainLaattaa(kopio, kohdeI, kohdeJ);
           int apu2 = laskeMaara(mahdol, 1);
           torppaus += apu -apu2;
        }
      return torppaus;
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
