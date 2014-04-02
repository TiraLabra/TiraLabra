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
        
        while(n < (arrayLength-(100)) ) {
            //ton satasen vois vaihtaa johonki tarkempaa, ei kovakoodattuun
            //System.out.println((byte) 25);
            
            int sampleValue = (int) wavAsByteArray[44+n];
            
            if ((int) sampleValue > 80) {
                
                wavAsByteArray[44+n+0] = (byte) (10);
                wavAsByteArray[44+n+1] = (byte) (-10);
                
                //wavAsByteArray[44+n+2] = (byte) (10);
                //wavAsByteArray[44+n+3] = (byte) (-10);
                
                n+=2;
            }
            
            //wavAsByteArray[44+n] = (byte) 100;
            
            n++;
        }

        //muista 44byte offset koska jee
        System.out.println("compression done!");
        return wavAsByteArray;
    }
}
