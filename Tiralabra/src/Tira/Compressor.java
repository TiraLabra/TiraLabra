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
        
        for (byte b : wavAsByteArray) {

            if (n > 45) {
                if (b > 20) {
                    b = 25;
                } else if (b < -20) {
                    b = -25;
                }
            }
            
            n++;
        }

        //muista 44byte offset koska jee
        return null;
    }
}
