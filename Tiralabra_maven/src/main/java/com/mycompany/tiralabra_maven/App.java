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
        int[] koot = {25,23,21,19,17,9,6,15,13,27,29,31,25,23,21,19,17,9,6,15,13,27,29,31,25,23,21,19,17,9,6,15,13,27,29,31,25,23,21,19,17,9,6,15,13,27,29,31};
        int KONTINVETOISUUS = 500;
        double KONTINTAYTTOASTETAVOITE = 0.98;

        //Luo Paketit ja Kontin
        /*Paketti[] paketit = new Paketti[koot.length];
        int summa = 0;
        for (int i=0; i<koot.length; i++) {
            paketit[i]=new Paketti(koot[i]);
            summa+=koot[i];
        }
        */
        Kontti kontti = new Kontti(KONTINVETOISUUS, KONTINTAYTTOASTETAVOITE);
        
        Testi testi = new Testi();
        testi.ajaTesti(koot, kontti, 1000);
        
        //Aloittaa Tayton valmistelun
        
        /*
        Taytto taytto = new Taytto(paketit, summa, kontti);

        taytto.etsiMahtuvaSummaKombinaatioistaRek(new ArrayList(), 0, 0, summa);
        taytto.tulostaRatkaisut();
        */
        
        //Etsii kombinaatiot ja niiden tuottamat summat

        //taytto.etsiSummatKombinaatioistaRek(new ArrayList(), 0, 0);
        //Tulostaa kombinaatiot ja niiden summat
        //taytto.muodostaKombinaatioRek(jono, 0);

        //taytto.tulostaSummatJaJonot();

        //System.out.println(taytto.annaSummatJaJonotMerkkijonona());
        
        
        
        
        
    }
}
