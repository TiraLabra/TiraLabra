/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Suorituskyky;

/**
 *
 * Tämä on apuluokka suorituskykytestaukselle
 */
public class Verkkoluokittelu {
    
    private int mustienlkm;
    private int max;
    private int alkioita;
    private long summa;
    private int onnistumiset;
    
    public Verkkoluokittelu(int mustienlkm, int max)
    {
    this.mustienlkm = mustienlkm;
    this.max = max;
    this.alkioita = 0;
    this.summa = 0;
    this.onnistumiset = 0;
    
    }
    
     /**
     *
     * lisää havainnon
     */
    public void lisaahavainto(long aika, int onnistuiko)
    {
    summa = summa + aika;
    alkioita = alkioita + 1;
    onnistumiset = onnistumiset + onnistuiko;
         
     /**
     *
     * Palauttaa aikakeskiarvon
     */
    }
    public double palautaaikakeskiarvo()
    {
    double sum = summa;
    double alk = alkioita;
    return sum/alk;
    
    }
    
      /**
     *
     * Palauttaa onnistumistodennkäöisyyden
     */
    public double palautaonnistumistodennakoisyys()
    {
    double sum = onnistumiset;
    double alk = alkioita;
    return sum/alk;
        
    }
    
}
