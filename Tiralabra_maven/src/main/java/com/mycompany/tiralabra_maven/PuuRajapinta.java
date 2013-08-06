package com.mycompany.tiralabra_maven;

public interface PuuRajapinta {
    
    public int[] getLapset();

    public int getJuuri();

    public int getSyvyys();

    public void lisaaSolmu(int i);

    public boolean poistaSolmu(int i);
    
    public int[] getSolmut();

    public String tulostaPuu();
}
