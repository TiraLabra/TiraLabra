/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logiikka;

/**
 *
 * @author Serafim
 */
public class Asetukset {
    
    private int kulmienmaksimiklkm;
    private boolean overridemode;
    
    public Asetukset()
    {
    
    }
    
    public void asetaKulmamaksimi(int i)
    {
    this.kulmienmaksimiklkm = i;
    }
    public int palautaKulmamaksimi()
    {
    return this.kulmienmaksimiklkm;
    }
    
}
