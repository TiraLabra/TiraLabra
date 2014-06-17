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
public class Sarmio {
    private long x;
    private long y;
    private long z;
    
    public Sarmio(long x, long y, long z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    @Override
    public String toString(){
        return "x = " + this.x + ", y = " + this.y + ", z = " + this.z;
    }
    
    public long getTilavuus(){
        return this.x * this.y * this.z;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public long getZ() {
        return z;
    }

    public void setZ(long z) {
        this.z = z;
    }
    
    
    
}
