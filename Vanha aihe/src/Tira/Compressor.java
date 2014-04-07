/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tira;

/**
 *
 * @author samsalon
 */
public class Compressor {

    public byte[] compress(byte[] wavAsByteArray) {

        int max = 127;
        int min = -127;

        int n = 1;
/*
        for (byte b : wavAsByteArray) {
            System.out.println("tavu numero " + n + " == " + b);
            n++;
            if (n == 45) {
                break;
            }
        }

        n = 1;
*/
        int arrayLength = wavAsByteArray.length;
        
        while(n < (arrayLength-(45)) ) {
            
            int leftSample = (int) wavAsByteArray[44+n];
            int rightSample = (int) wavAsByteArray[44+n+1];

            int peak = Math.max(leftSample, rightSample);
            
            if (leftSample > 60) {
                
            }
            
            System.out.println("left: " + leftSample);
            System.out.println("right: " + rightSample);
            
            
            
            n+=2;
        }

        //muista 44byte offset koska jee
        System.out.println("compression done!");
        return wavAsByteArray;
    }
}
