/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.domain;

import com.mycompany.tiralabra_maven.structures.List;


/**
 * Palkki, joka koostuu useammasta samantyyppisestä laatikosta.
 * @author szetk
 */
public class Palkki extends Sarmio {

    private Laatikkotyyppi tyyppi;
    private int nx;
    private int ny;
    private int nz;
    private Sijainti sijainti;
    private List<Laatikko> laatikot;    
/**
 * Konstuktori
 * @param tyyppi Laatikon tyyppi
 * @param nx Laatikoiden määrä x-akselilla
 * @param ny Laatikoiden määrä y-akselilla
 * @param nz Laatikoiden määrä z-akselilla
 */
    public Palkki(Laatikkotyyppi tyyppi, int nx, int ny, int nz) {
        super(nx * tyyppi.getX(), ny * tyyppi.getY(), nz * tyyppi.getZ());
        this.tyyppi = tyyppi;
        this.nx = nx;
        this.ny = ny;
        this.nz = nz;
        this.sijainti = new Sijainti(0, 0, 0);
    }

    public Palkki(int x, int y, int z) {
        super(x, y, z);
        this.tyyppi = null;
        this.sijainti = new Sijainti(0, 0, 0);
    }

    public int laatikoita() {
        return this.nx * this.ny * this.nz;
    }

    @Override
    public String toString() {
        return "Tyyppi: " + this.tyyppi + ", määrät: nx = " + this.nx + ", ny = " + this.ny + ", nz = " + this.nz;
    }

    public Laatikkotyyppi getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(Laatikkotyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public int getNx() {
        return nx;
    }

    public void setNx(int nx) {
        this.nx = nx;
    }

    public int getNy() {
        return ny;
    }

    public void setNy(int ny) {
        this.ny = ny;
    }

    public int getNz() {
        return nz;
    }

    public void setNz(int nz) {
        this.nz = nz;
    }

    public Sijainti getSijainti() {
        return sijainti;
    }

    public void setSijainti(Sijainti sijainti) {
        this.sijainti = sijainti;
    }

}
