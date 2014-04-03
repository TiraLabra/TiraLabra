/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package labyrintti;

import javax.swing.SwingUtilities;
import labyrintti.gui.Kayttoliittyma;
import labyrintti.gui.Piirtoalusta;
import labyrintti.osat.Pohja;
import labyrintti.sovellus.Etsija;

/**
 *
 * @author heidvill@cs
 */
public class Kaynnistys {
    
    private Piirtoalusta alusta;
    private Etsija etsija;
    private Kayttoliittyma kali;
    private Pohja pohja;

    public Kaynnistys() {
        pohja = new Pohja();
        pohja.alustaPohja("src/main/java/labyrintti/osat/kartta1.txt");        
        etsija = new Etsija(pohja);
        alusta = new Piirtoalusta(this, 40);
        kali = new Kayttoliittyma(alusta, this, 40);  
    }
    
    public void kaynnista(){
        SwingUtilities.invokeLater(kali);

        while (kali.getPiirtoalusta() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }
        
        alusta.paivita();
    }

    public Pohja getPohja() {
        return pohja;
    }
    
    public void maaritaAlkuarvot(){
        int korkeus = 0;
        int leveys = 0;
    }
    
    private void kysyAlkuarvot(String kysymys){
        
    }

    public Piirtoalusta getPiirtoalusta() {
        return alusta;
    }

    public Etsija getEtsija() {
        return etsija;
    }
}
