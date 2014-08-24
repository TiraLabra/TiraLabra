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
         System.out.println("----------------");
        Packer packer = new Packer();
        Paketti paketti = packer.pack(lause);
        
         System.out.println("-----------------");

        System.out.println(paketti.getLause());
        
        System.out.println("----------------");
        
        Decompressor decompressor = new Decompressor(paketti.getTree(), paketti.getLause());
        System.out.println(decompressor.unzip());
         

    }
    
}
