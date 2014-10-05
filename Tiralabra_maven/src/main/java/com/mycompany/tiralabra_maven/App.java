package com.mycompany.tiralabra_maven;

import java.io.File;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        long aikaAlussa = System.currentTimeMillis();
        
        File kartta = new File("suorituskykykartta5.txt");
        Kartanlukija kartanlukija = new Kartanlukija();
        Verkko verkko = kartanlukija.luoVerkko(kartta);
        
        Astar astar = new Astar(verkko, kartanlukija.getLahtosolmu(), kartanlukija.getKohdesolmu());
        
        //tulostaPolku(astar.haeLyhinPolku());
        
        Solmu kohdesolmu = astar.haeLyhinPolku();

        tulostaVerkko(verkko, kohdesolmu);
        
        long aikaLopussa = System.currentTimeMillis();  
        System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
        
    }
    
    /**
     * Tulostaa polun koordinaatit tekstimuotoisena esityksenä 
     * 
     * Aikavaativuus: lineaarinen polun solmujen lukumäärän suhteen
     * 
     * @param solmu tulostettavan polun pää
    */
    public static void tulostaPolku(Solmu solmu) {
        while(solmu != null) {
            System.out.println("X" + solmu.getX() + ", Y" + solmu.getY());
            solmu = solmu.getPolku();
        }
    }
    
    /**
     * Tulostaa verkon ja polun graafisen esityksen. Algoritmi olettaa, että
     * verkko on tehty Kartanlukijalla tai vastaavassa järjestyksessä.
     * 
     * Aikavaativuus: n^2 - jokaiselle solmulle O(n) tarkistetaan, kuuluuko
     * solmu kohdesolmuun vievälle polulle O(n)
     * 
     * @param verkko     tulostettava verkko
     * @param kohdesolmu tulostettavan polun pää
    */
    public static void tulostaVerkko(Verkko verkko, Solmu kohdesolmu) {
        //kuljetaan polun päähän, josta löytyy lähtösolmu
        Solmu lahtosolmu = kohdesolmu;
        while(lahtosolmu.getPolku() != null) {
            lahtosolmu = lahtosolmu.getPolku();
        }
        
        //Käännetään pinon järjestys, jotta tuloste näytetään oikein päin
        LinkitettyLista lista2 = new LinkitettyLista();
        Listasolmu pinosolmu = verkko.getSolmut().getYlin();
  
        while (pinosolmu != null) {
            lista2.lisaa(pinosolmu.getSisalto());
            pinosolmu = pinosolmu.getSeuraava();
        }
        
        tulostaSeinaa(verkko.getSolmut().getYlin().getSisalto().getX() + 3);
        System.out.print("\n#");
        
        //käydään kaikki solmut läpi
        pinosolmu = lista2.getYlin();
        int rivinumero = 0;
        
        while (pinosolmu != null) {
            Solmu solmu = pinosolmu.getSisalto();
            
            //tehdään rivinvaihdot
            if(solmu.getY() > rivinumero) {
                System.out.println("#");
                System.out.print("#");
                rivinumero++;
            }
            
            if(solmu.getPaino() == -1) {
                System.out.print("#");
            } else if(solmu == lahtosolmu) {
                System.out.print("A");
            } else if(solmu == kohdesolmu) {
                System.out.print("B");
            } else if(onkoPolulla(kohdesolmu, solmu)) {
                System.out.print(".");
            } else if(solmu.getPaino() == 0) {
                System.out.print(" ");
            } else {
                System.out.print(solmu.getPaino());
            }

            pinosolmu = pinosolmu.getSeuraava();
        }
        
        System.out.println("#");
        tulostaSeinaa(verkko.getSolmut().getYlin().getSisalto().getX() + 3);
        System.out.println("");
    }
    
    /**
     * Tarkistaa, onko etsittävä solmu kohdesolmuun johtavalla polulla
     * 
     * Pahin tapaus: etsittävä solmu ei ole polulla
     * Aikavaativuus: lineaarinen polun solmujen lukumäärän suhteen
     * 
     * @param kohdesolmu kohdesolmu
     * @param etsittava  polulta etsittävä solmu
     * @return onko solmu polulla (true) vaiko eikö (false)
    */
    public static boolean onkoPolulla(Solmu kohdesolmu, Solmu etsittava) {
        while(kohdesolmu != null) {
            if(kohdesolmu == etsittava) {
                return true;
            }
            kohdesolmu = kohdesolmu.getPolku();
        }
        return false;
    }
    
    /**
     * Tulostaa vaakasuoraa seinää graafiseen esitykseen
     * 
     * Aikavaativuus: lineaarinen seinän pituuden suhteen
     * 
     * @param pituus kuinka pitkä seinä tulostetaan
    */
    public static void tulostaSeinaa(int pituus) {
        for(int i = 0; i < pituus; i++) {
            System.out.print("#");
        }
    }
}
