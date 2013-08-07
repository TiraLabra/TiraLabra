package Tiralabra.kayttoliittyma;

import java.util.Scanner;

/** Tekstikäyttöliittymä testausta ja perustoimivuutta varten.
 *
 * @author Pia Pakarinen
 */

public class Teksti_UI {
    
    /** Lukee käyttäjän antamat komennot.
     * 
     */
    private Scanner luk;
    
    /** Käynnistää tekstikäyttöliittymän.
     * 
     */
    public void kaynnista(){
        luk = new Scanner(System.in);
        System.out.println("Valitse vertailtavien tietorakenteiden numerot: \n"
                + "1: Punamustapuu\n"
                + "2: B-puu\n"
                + "3: Treap\n"
                + "4: Threaded-puu\n");
    }   
}