/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tiralabra.utilities;

/**
 *
 * @author atte
 */
public class Utilities {
    
    public static int[][] copy2dArray(int[][] array) {
        int[][] copy = new int[array.length][array[0].length];
        
        for (int y = 0; y < array.length; y++) {
            copy[y] = array[y].clone();
        }
        return copy;
    }
}
