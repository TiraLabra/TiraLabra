/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tiedostonkasittely;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Lukee tiedoston ja siinä esiintyvät merkit ja niiden toistot.
 * @author joonaslongi
 */
public class TiedostonLukija {
    
    FileInputStream fileinput; 
    
    /**
     * Alustaa uuden tiedostonlukijan.
     * @param tiedosto 
     */
    
    public TiedostonLukija(String tiedosto){
        File file = new File(tiedosto);
        try {
            fileinput = new FileInputStream(file);
        } catch (IOException e){
            System.out.println("Tiedostoa " + e + " ei löydy");
        }
        
    }
    
    /**
     * Yritää lukea yhden bitin tiedostosta ja palauttaa sen.
     * @return 
     */
    public int lue(){
        try{
            return fileinput.read();
        } catch (IOException e){
            System.out.println("Ei pysty lukemaan " + e);
        }
        return -1;
    }
    
    /**
     * Kertoo montako bittia on lukematta.
     * @return 
     */
    
    public int vapaana(){
        try{
            return fileinput.available();
        } catch ( IOException e){
            System.out.println("Ei toimi" + e);
        }
        return -1;
    }
    
    /**
     * Sulkee lukijan.
     */
    
    public void sulje(){
        try{
            fileinput.close();   
        } catch (IOException e){
            System.out.println("Sulkeminen ei onnistu" + e);
        }
    }
    
    
}
