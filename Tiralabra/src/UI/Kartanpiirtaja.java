/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Domain.Hirvio;
import Domain.Luola;
import Domain.Peliobjekti;

/**
 *
 * @author Emleri
 */
public class Kartanpiirtaja {
    private Luola luola;

    public Kartanpiirtaja(Luola luola) {
        this.luola = luola;
    }
    
    public void piirra() {
        Peliobjekti[][] alue = luola.getLuola();
        for(int i = 0; i < luola.getKorkeus(); i++) {
            for(int j = 0; j < luola.getLeveys(); j++) {
                if(alue[j][i] == null) {
                    System.out.print("_");
                } else if (alue[j][i].getClass() == Hirvio.class) {
                    System.out.print("h");
                }
            }
            System.out.println("");
        }
    }
}
