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
    private int x;
    private int y;
    private int z;
    
    public Sarmio(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    @Override
    public String toString(){
        return "x = " + this.x + ", y = " + this.y + ", z = " + this.z;
    }
    
    public int getTilavuus(){
        return this.x * this.y * this.z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
    
    
    
}
