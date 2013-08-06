package com.mycompany.tiralabra_maven;

public class BinaariHakupuu implements PuuRajapinta {
    private int[] arvot;
    private int syvyys;
    
    public int[] getLapset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getJuuri() {
       return arvot[0];
    }

    public int getSyvyys() {
        return syvyys;
    }

    public int[] getSolmut() {
       return new int[0];
    }

    public String tulostaPuu() {
        return "";
    }

    public void lisaaSolmu(int i) {
        
    }

    public boolean poistaSolmu(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
