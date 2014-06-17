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
        lisaaLaatikot(nx, ny, nz);
        this.sijainti = sijainti;
    }

//    public Palkki(long x, long y, long z) {
//        super(x, y, z);
//        this.tyyppi = null;
//        this.sijainti = new Sijainti(0, 0, 0);
//    }
    private void lisaaLaatikot(long nx, long ny, long nz) {
        for (long i = 0; i < nx; i++) {
            for (long j = 0; j < ny; j++) {
                for (long k = 0; k < nz; k++) {
                    Laatikko laatikko = new Laatikko(tyyppi, new Sijainti(), this.orientaatio);
                    laatikot.add(laatikko);
                }

            }
        }
    }

//    
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

}
