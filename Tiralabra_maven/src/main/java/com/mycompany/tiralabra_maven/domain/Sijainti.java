/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.domain;

/**
 * Tämä on minkä tahansa asian sijainti
 *
 * @author szetk
 */
public class Sijainti {

    private long x;
    private long y;
    private long z;

    public Sijainti() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Sijainti(long posX, long posY, long posZ) {
        this.x = posX;
        this.y = posY;
        this.z = posZ;
    }

    /**
     * Tämä lisää kahden sijainnin komponentit yhteen. Tätä hyödynnetään
     * esimerkiksi palkkien referenssikulkmien käytössä.
     *
     * @param sijainti Lisättävä sijainti
     */
    public void plus(Sijainti sijainti) {
        this.x += sijainti.getX();
        this.y += sijainti.getY();
        this.z += sijainti.getZ();

    }

    public long getX() {
        return x;
    }

    public void setX(long posX) {
        this.x = posX;
    }

    public long getY() {
        return y;
    }

    public void setY(long posY) {
        this.y = posY;
    }

    public long getZ() {
        return z;
    }

    public void setZ(long posZ) {
        this.z = posZ;
    }

    @Override
    public String toString() {
        return "x = " + this.x + ", y = " + this.y + ", z = " + this.z;
    }

}
