/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.domain;

import com.mycompany.tiralabra_maven.structures.List;

/**
 * Palkki, joka koostuu useammasta samantyyppisestä laatikosta.
 *
 * @author szetk
 */
public class Palkki extends Sarmio {

    private Laatikkotyyppi tyyppi;
    private long nx;
    private long ny;
    private long nz;
    private Sijainti sijainti;
    private List<Laatikko> laatikot;
    private int orientaatio;

    /**
     * Konstuktori
     *
     * @param sijainti Palkin sijainti
     * @param laatikko Esimerkki laatikosta, joita palkkiin asetetaan
     * @param nx Laatikoiden määrä x-akselilla
     * @param ny Laatikoiden määrä y-akselilla
     * @param nz Laatikoiden määrä z-akselilla
     */
    public Palkki(Sijainti sijainti, Laatikko laatikko, long nx, long ny, long nz) {
        super(nx * laatikko.getX(), ny * laatikko.getY(), nz * laatikko.getZ());
        this.tyyppi = laatikko.getTyyppi();
        this.laatikot = new List<Laatikko>();
        this.nx = nx;
        this.ny = ny;
        this.nz = nz;
        this.sijainti = sijainti;
        this.orientaatio = laatikko.getOrientaatio();
//        lisaaLaatikot();

    }

    public void lisaaLaatikot() {
        for (long i = 0; i < this.nx; i++) {
            for (long j = 0; j < this.ny; j++) {
                for (long k = 0; k < this.nz; k++) {
                    Laatikko laatikko = new Laatikko(this.tyyppi, new Sijainti(), this.orientaatio);
                    Sijainti sij = new Sijainti(this.sijainti.getX() + i*laatikko.getX(), this.sijainti.getY() + j*laatikko.getY(), this.sijainti.getZ() + k*laatikko.getZ());
                    laatikko.setSijainti(sij);
                    this.laatikot.add(laatikko);
                }

            }
        }
    }

//    
    @Override
    public String toString() {
        return "Tyyppi: " + this.laatikot.get(0) + ", määrät: nx = " + this.nx + ", ny = " + this.ny + ", nz = " + this.nz;
    }

    public Laatikkotyyppi getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(Laatikkotyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public long getNx() {
        return nx;
    }

    public void setNx(long nx) {
        this.nx = nx;
    }

    public long getNy() {
        return ny;
    }

    public void setNy(long ny) {
        this.ny = ny;
    }

    public long getNz() {
        return nz;
    }

    public void setNz(long nz) {
        this.nz = nz;
    }

    public Sijainti getSijainti() {
        return sijainti;
    }

    public void setSijainti(Sijainti sijainti) {
        this.sijainti = sijainti;
    }

    public List<Laatikko> getLaatikot() {
        return laatikot;
    }

    public int getOrientaatio() {
        return orientaatio;
    }

    
}
