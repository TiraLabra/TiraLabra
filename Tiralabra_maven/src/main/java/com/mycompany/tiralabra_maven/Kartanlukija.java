package com.mycompany.tiralabra_maven;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Kartanlukija luo verkon tekstitiedostosta. Kartanlukija ottaa muistiin
 * lähtö- ja kohdesolmut.
 * 
 * Tekstitiedosto muodostetaan seuraavista merkeistä:
 *  aloituspiste A
 *  lopetuspiste B
 *  este #
 *  kulkemiskelpoinen maasto 0-9, missä 0 on helpointa ja 9 vaikeinta
 */
public class Kartanlukija {
    
    final int SEINAPAINO = -1;
    private Solmu lahtosolmu;
    private Solmu kohdesolmu;

    public Solmu getLahtosolmu() {
        return lahtosolmu;
    }

    public void setLahtosolmu(Solmu lahtosolmu) {
        this.lahtosolmu = lahtosolmu;
    }

    public Solmu getKohdesolmu() {
        return kohdesolmu;
    }

    public void setKohdesolmu(Solmu kohdesolmu) {
        this.kohdesolmu = kohdesolmu;
    }
    
    /**
     * Luo verkon parametrina annetusta tiedostosta
     * 
     * Aikavaativuus: lineaarinen merkkien lukumäärän suhteen. Jokaisesta
     * merkistä tehdään yksi solmu V ja lopuksi vieruslistojen teossa käydään
     * kaikki solmut V läpi, eli O(|V| + |V|) = O(|V|)
     * 
     * @param    kartta  kartta File-oliona
     * @return valmis verkko
     * @throws java.io.FileNotFoundException jos karttatiedostoa ei löydy
    */  
    public Verkko luoVerkko(File kartta) throws FileNotFoundException {
        Scanner lukija = new Scanner(kartta);
        Verkko verkko = new Verkko();
        int rivinumero = 0;
        
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            int sarakenumero = 0;
            
            while (sarakenumero < rivi.length()) { 
                char merkki = rivi.charAt(sarakenumero);
                int paino = 0;
                
                if(merkki == '#') {
                    paino = SEINAPAINO;
                } else if (merkki == 'A' || merkki == 'B') {
                    paino = 0;
                } else if (Integer.parseInt("" + merkki) >= '0' || Integer.parseInt("" + merkki) <= '9') {
                    paino = Integer.parseInt("" + merkki);
                } 
                
                Solmu solmu = new Solmu(sarakenumero, rivinumero, paino);
                verkko.lisaaSolmu(solmu);
                
                if(merkki == 'A') {
                    this.lahtosolmu = solmu;
                } else if(merkki == 'B') {
                    this.kohdesolmu = solmu;
                }
                
                sarakenumero++;
            }
            rivinumero++;
        }

        lukija.close();
        verkko.luoVieruslistat();
        return verkko;
    }
}
