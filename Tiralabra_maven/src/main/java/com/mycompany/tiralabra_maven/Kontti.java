/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

/**
 *
 * @author ilkkaporna
 */
public class Kontti {
    private final int koko;
    private final double tayttotavoiteaste;

    private int tayttotavoite;
    
    public Kontti(int koko, double tayttotavoiteaste){
        this.koko=koko;
        this.tayttotavoiteaste=tayttotavoiteaste;
        this.tayttotavoite = (int) (tayttotavoiteaste*koko);
    }
    public int annaKoko(){
        return this.koko;
    }
}
