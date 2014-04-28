/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nkostiai
 */
public class Kayttoliittyma {

    public Kayttoliittyma() {
    }

    public void kaynnista() {
        SorterReporter reportteri = new SorterReporter("raportti.html");
        System.out.println("Järjestetään taulukoita...");
        reportteri.testSorters();
        try {
            System.out.println("Generoidaan raporttia...");
            reportteri.exportReport();
            System.out.println("Raportti generoitu!");

        } catch (IOException ex) {
            System.out.println("Tiedostoon kirjoittaessa tapahtui virhe");
            Logger.getLogger(Kayttoliittyma.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
