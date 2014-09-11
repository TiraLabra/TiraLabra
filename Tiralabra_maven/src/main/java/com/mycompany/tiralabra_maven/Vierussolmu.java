/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

/**
 *
 * @author iivo
 */
public class Vierussolmu {
    private Solmu solmu;
    private int kaaripaino;

    public Vierussolmu(Solmu solmu, int paino) {
        this.solmu = solmu;
        this.kaaripaino = paino;
    }

    public Solmu getSolmu() {
        return solmu;
    }

    public void setSolmu(Solmu solmu) {
        this.solmu = solmu;
    }

    public int getPaino() {
        return kaaripaino;
    }

    public void setPaino(int paino) {
        this.kaaripaino = paino;
    }
    
}
