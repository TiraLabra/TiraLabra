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
        
        while(n < (arrayLength-44) ) {
            //System.out.println((byte) 25);
            if ((int) wavAsByteArray[44+n] > 25) {
                
                wavAsByteArray[44+n+0] = (byte) 25;
                wavAsByteArray[44+n+1] = (byte) 25;
                wavAsByteArray[44+n+2] = (byte) 25;
                wavAsByteArray[44+n+3] = (byte) 25;
                
                wavAsByteArray[44+n+4] = (byte) -25;
                wavAsByteArray[44+n+5] = (byte) -25;
                wavAsByteArray[44+n+6] = (byte) -25;
                wavAsByteArray[44+n+7] = (byte) -25;
                
                n+=8;
            }
            
            //wavAsByteArray[44+n] = (byte) 100;
            
            n++;
        }

        //muista 44byte offset koska jee
        System.out.println("compression done!");
        return wavAsByteArray;
    }
}
