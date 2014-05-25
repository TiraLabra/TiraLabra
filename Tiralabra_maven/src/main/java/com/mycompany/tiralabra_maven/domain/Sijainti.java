/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven.domain;

/**
 *
 * @author szetk
 */
public class Sijainti {
    private int posX;
    private int posY;
    private int posZ;

    public Sijainti(int posX, int posY, int posZ) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }
    
    public void plus(Sijainti sijainti){
        this.posX += sijainti.getPosX();
        this.posY += sijainti.getPosY();
        this.posZ += sijainti.getPosZ();
        
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosZ() {
        return posZ;
    }

    public void setPosZ(int posZ) {
        this.posZ = posZ;
    }
    
    
    
}
