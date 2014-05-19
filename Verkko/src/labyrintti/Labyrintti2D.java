/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */

package labyrintti;

import verkko.KoordinoituSolmu;


public class Labyrintti2D {
    private KoordinoituSolmu[][] solmut;

    public Labyrintti2D(int i, int j) {
        this.solmut = new KoordinoituSolmu[i][j];
        for (int k = 0; k < solmut.length; k++) {
            for (int l = 0; l < solmut[0].length; l++) {
                Double[] koord = {k*1.0,l*1.0};
                solmut[k][l]=new KoordinoituSolmu(koord);
            }
        }
    }
    
    public KoordinoituSolmu[][] getSolmut(){
        return solmut;
    }
    
    
}
