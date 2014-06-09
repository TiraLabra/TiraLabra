
import Reitti.Reitti;
import Verkko.Verkko;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eniirane
 */
public class Main {
   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Verkko gi = new Verkko();
        Reitti r = new Reitti(gi);
        r.getReitti(gi.getNoodi(0,0),gi.getNoodi(49,49));

        System.out.println(gi.toString());
        System.out.println();
        System.out.println(r.toString());
        
        
    }
    
}
