package com.mycompany.tiralabra_maven;

import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ilkkaporna
 */
public class Testi {
  
    
    public Testi() {
        
      
    }
    
    public void ajaTesti(int[] koot, Kontti kontti, int toistokoeLkm) {
        Taytto taytto;
        Paketti[] paketit;
        long tulosSumma=0;
        
        for (int i=0;i<toistokoeLkm;i++) {
            shuffleArray(koot);
            paketit = new Paketti[koot.length];
            int summa = 0;
            for (int j=0; j<koot.length; j++) {
                paketit[j]=new Paketti(koot[j]);
                summa+=koot[j];
            }
            taytto = new Taytto(paketit, summa, kontti);
            
            long aikaAlussa = System.currentTimeMillis();
            taytto.etsiMahtuvaSummaKombinaatioistaRek(new ArrayList(), 0, 0, summa);            
            long aikaLopussa = System.currentTimeMillis();  
            long aika=aikaLopussa-aikaAlussa;
            tulosSumma+=aika;
            System.out.println("Suoritusaika: " + aika + " " + taytto.annaRatkaisutMerkkijonona());
            
        }
        long keskiarvo = tulosSumma/toistokoeLkm;
        System.out.println(toistokoeLkm + ":n satunnaisjärjestyksessä käydyn haun keskiarvo(ms): " + keskiarvo + ", yhteenlaskettu aika: " + tulosSumma);
        
    
    }
    
    private void shuffleArray(int[] ar)
    {
      Random rnd = new Random();
      for (int i = ar.length - 1; i > 0; i--)
      {
        int index = rnd.nextInt(i + 1);
        // Simple swap
        int a = ar[index];
        ar[index] = ar[i];
        ar[i] = a;
      }
    }    

}
