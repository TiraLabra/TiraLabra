/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 8.8.2014 
 */

package TestSuite.Engine;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class ArrayFactor {
    
    public ArrayFactor() {
    }
    
    public static int[] randomArrayNoDuplicatesMaxIsSize(int size) {
        int[] a = new int[size];
        
        for(int j = 0 ; j<a.length; j++){
            a[j] = j;
        }
         
        Random r = new Random();
        
        for(int i = 0; i<a.length; i ++){
            
            int rndNmb = r.nextInt(size);
            int temp = a[i];
            a[i] = a[rndNmb];
            a[rndNmb] = temp;

        }
        
        return a;
    }
}
