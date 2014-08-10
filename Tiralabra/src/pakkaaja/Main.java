/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pakkaaja;

/**
 *
 * @author joonaskylliainen
 */
import java.util.Scanner;

public class Main {
    
     public static void main( String[] args ) {
         
        Scanner lukija = new Scanner(System.in);
        System.out.println("Anna pakattava: ");
        String lause = lukija.nextLine();
        Pakkaaja packer = new Pakkaaja();
        String paketti = packer.pack(lause);
        
     }
    
}
