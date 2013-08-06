package com.mycompany.tiralabra_maven;

public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        PuuRajapinta p = new BinaariHakupuu();
        Kontrolliluokka k = new Kontrolliluokka(p);
        k.LisaaArvottuja(10000);
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println(estimatedTime);
        
    }
}
