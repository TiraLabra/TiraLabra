/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package yleismetodeja;

/**
 *
 * @author risto
 */
public class Taulukko {

    public static String toString(double[][] matriisi){
        int m = matriisi.length;
        int n = matriisi[0].length;
        String tulos = "";
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tulos = tulos + matriisi[i][j] + " ";
            }
            tulos = tulos + "\n";
        }
        return tulos;
        
    }
    
}
