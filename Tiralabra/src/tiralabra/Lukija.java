/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.io.File;
import java.io.FileInputStream;

/**
 *
 * @author joonaslongi
 */
public class Lukija {
    FileInputStream fileinput; 

    public Lukija(String tiedosto){
        File file = new File(tiedosto);
        try {
            fileinput = new FileInputStream(file);
        } catch (Exception e){
            System.out.println("Tiedostoa " + e + " ei löydy");
        }
        
    }
    
    public int lue(){
        try{
            return fileinput.read();
        } catch (Exception e){
            System.out.println("Ei pysty lukemaan " + e);
        }
        return -1;
    }
    
    public int vapaana(){
        try{
            return fileinput.available();
        } catch ( Exception e){
            System.out.println("Ei toimi" + e);
        }
        return -1;
    }
    
    public void sulje(){
        try{
            fileinput.close();   
        } catch (Exception e){
            System.out.println("Sulkeminen ei onnistu" + e);
        }
    }
    
    
}
