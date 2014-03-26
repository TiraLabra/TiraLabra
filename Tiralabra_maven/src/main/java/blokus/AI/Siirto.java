package blokus.AI;

import blokus.logiikka.Laatta;

/**
 *
 * @author Ilkimys
 */
public class Siirto {
    
    private int kohdeI;
    private int kohdeJ;
    private int ympari;
    private int asento;
    
    private Laatta laatta;
    
    private int hyvyys;

    /**
     *
     * @param kohdeI
     * @param kohdeJ
     * @param laatta
     */
    public Siirto(int kohdeI, int kohdeJ, Laatta laatta) {
        this.kohdeI = kohdeI;
        this.kohdeJ = kohdeJ;
        this.laatta = laatta;
        ympari = laatta.getYmpari();
        asento = laatta.getAsento();
        pisteytaSiirto();
    }

    private void pisteytaSiirto() {
        hyvyys = laatta.getKoko() + laskeuudetPaikat();
    }

    public int getAsento() {
        return asento;
    }

    public int getYmpari() {
        return ympari;
    }
    

    /**
     *
     * @return
     */
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
    

    private int laskeuudetPaikat() {
        int[][] malli = laatta.getMuoto();
        int paikat = 0;
        for (int i = 0; i < malli.length; i++) {
            for (int j = 0; j < malli.length; j++) {
                if (malli[i][j] == 1)  {
                    paikat++;
                }
                
            }
            
        }
        return paikat;
    }
    
    
    
    
    
            
    
}
