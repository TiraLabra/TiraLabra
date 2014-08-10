package com.mycompany.tiralabra_maven;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //anna pakettien koot ja kontin tiedot
        int[] koot = {17,19,21,23,25,27,29,31,33,35,17};
        int KONTINVETOISUUS = 99;
        double KONTINTAYTTOASTETAVOITE = 0.95;

        //Luo Paketit ja Kontin
        Paketti[] paketit = new Paketti[koot.length];
        for (int i=0; i<koot.length; i++) {
            paketit[i]=new Paketti(koot[i]);
        }
        Kontti kontti = new Kontti(KONTINVETOISUUS, KONTINTAYTTOASTETAVOITE);
        
        //Aloittaa Tayton valmistelun
        Taytto taytto = new Taytto(paketit, kontti);
        //Etsii kombinaatiot ja niiden tuottamat summat
        taytto.etsiSummatKombinaatioistaRek(new ArrayList(), 0, 0);

        //Tulostaa kombinaatiot ja niiden summat
//        taytto.muodostaKombinaatioRek(jono, 0);
        taytto.tulostaSummatJaJonot();

        
        
    }
}
