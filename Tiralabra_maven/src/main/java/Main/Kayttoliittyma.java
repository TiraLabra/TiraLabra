/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Sorters.*;
import java.io.IOException;
import java.util.Random;
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
        reportteri.testSorters();
        try {
            reportteri.exportReport();
        } catch (IOException ex) {
            System.out.println("Tiedostoon kirjoittaessa tapahtui virhe");
            Logger.getLogger(Kayttoliittyma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
