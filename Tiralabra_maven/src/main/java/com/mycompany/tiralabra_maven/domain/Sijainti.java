/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven.domain;

/**
 * Tämä on minkä tahansa asian sijainti
 * @author szetk
 */
public class Sijainti {
    private int x;
    private int y;
    private int z;
    
    public Sijainti(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Sijainti(int posX, int posY, int posZ) {
        this.x = posX;
        this.y = posY;
        this.z = posZ;
    }
    
    /**
     * Tämä lisää kahden sijainnin komponentit yhteen. Tätä hyödynnetään esimerkiksi palkkien referenssikulkmien käytössä.
     * @param sijainti Lisättävä sijainti
     */
    public void plus(Sijainti sijainti){
        this.x += sijainti.getX();
        this.y += sijainti.getY();
        this.z += sijainti.getZ();
        
    }

    public int getX() {
        return x;
    }

    public void setX(int posX) {
        this.x = posX;
    }

    public int getY() {
        return y;
    }

    public void setY(int posY) {
        this.y = posY;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int posZ) {
        this.z = posZ;
    }
    
    
    
}
