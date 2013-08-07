/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.io.File;
import java.io.FileInputStream;

/**
 * Lukee tiedoston ja siinä esiintyvät merkit ja niiden toistot.
 * @author joonaslongi
 */
public class Lukija {
    
    FileInputStream fileinput; 
    
    /**
     * Alustaa uuden tiedostonlukijan.
     * @param tiedosto 
     */
    
    public Lukija(String tiedosto){
        File file = new File(tiedosto);
        try {
            fileinput = new FileInputStream(file);
        } catch (Exception e){
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
        } catch (Exception e){
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
        } catch ( Exception e){
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
        } catch (Exception e){
            System.out.println("Sulkeminen ei onnistu" + e);
        }
    }
    
    
}
