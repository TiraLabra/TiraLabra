/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.testing;

/**
 * Tämä sisältää parametreja testisettien tekemistä helpottamisen vuoksi.
 *
 * @author szetk
 */
public class SetinOminaisuudet {

    private String nimi;
    private long haluttuTilavuus;
    private int tyyppienMaara;

    private int minX;
    private int maxX;

    private int minY;
    private int maxY;

    private int minZ;
    private int maxZ;

    public SetinOminaisuudet(String nimi, long haluttuTilavuus, int tyyppienMaara, int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
        this.nimi = nimi;
        this.haluttuTilavuus = haluttuTilavuus;
        this.tyyppienMaara = tyyppienMaara;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.minZ = minZ;
        this.maxZ = maxZ;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public long getHaluttuTilavuus() {
        return haluttuTilavuus;
    }

    public void setHaluttuTilavuus(long haluttuTilavuus) {
        this.haluttuTilavuus = haluttuTilavuus;
    }

    public int getTyyppienMaara() {
        return tyyppienMaara;
    }

    public void setTyyppienMaara(int tyyppienMaara) {
        this.tyyppienMaara = tyyppienMaara;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMinZ() {
        return minZ;
    }

    public void setMinZ(int minZ) {
        this.minZ = minZ;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public void setMaxZ(int maxZ) {
        this.maxZ = maxZ;
    }

}
