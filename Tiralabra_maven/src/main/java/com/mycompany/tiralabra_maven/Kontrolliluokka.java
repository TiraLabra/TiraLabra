package com.mycompany.tiralabra_maven;

import java.util.Random;

public class Kontrolliluokka {

    PuuRajapinta puu;

    public Kontrolliluokka(PuuRajapinta p) {
        puu = p;
    }

    public void LisaaArvottuja(int maara) {
        for (int i = 0; i < maara; i++) {
           Random r = new Random();
            puu.lisaaSolmu(r.nextInt());
        }
    }
}
