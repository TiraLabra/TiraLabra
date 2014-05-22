/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tiralabra;

/**
 *
 * @author atte
 */
public class Utilities {
    
    public static byte[][] copy2dArray(byte[][] array) {
        byte[][] copy = new byte[array.length][array[0].length];
        
        for (byte y = 0; y < array.length; y++) {
            copy[y] = array[y].clone();
        }
        return copy;
    }
}
