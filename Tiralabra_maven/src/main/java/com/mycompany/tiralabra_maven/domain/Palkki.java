/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.domain;

import java.util.ArrayList;

/**
 *
 * @author szetk
 */
public class Palkki extends Sarmio {

    private ArrayList<Laatikko> laatikot;
    private Sijainti sijainti;

    public Palkki(int x, int y, int z, ArrayList<Laatikko> laatikot) {
        super(x, y, z);
        this.laatikot = laatikot;
        this.sijainti = new Sijainti(0, 0, 0);
    }

    public Palkki(int x, int y, int z) {
        super(x, y, z);
        this.laatikot = new ArrayList<Laatikko>();
        this.sijainti = new Sijainti(0, 0, 0);
    }

    /**
     * Lisää yhden tyyppisiä laatikoita palkkiin kolmelle akseleille parametrien mukaan.
     * @param tyyppi Lisättävän laatikon tyyppi
     * @param nx Määrittää kuinka monta laatikkoa laitetaan x-akselin suuntaan
     * @param ny Määrittää kuinka monta laatikkoa laitetaan y-akselin suuntaan
     * @param nz Määrittää kuinka monta laatikkoa laitetaan z-akselin suuntaan
     */
    public void lisaaLaatikot(Laatikkotyyppi tyyppi, int nx, int ny, int nz) {
        for (int x = 0; x < nx; x++) {
            for (int y = 0; y < ny; y++) {
                for (int z = 0; z < nz; z++) {
                    Sijainti sij = new Sijainti(x * tyyppi.getX(), y * tyyppi.getY(), z * tyyppi.getZ());
                    Laatikko laatikko = new Laatikko(tyyppi, sij, 0);
                    this.laatikot.add(laatikko);
                }
            }
        }
    }

    public ArrayList<Laatikko> getLaatikot() {
        return laatikot;
    }

    public void setLaatikot(ArrayList<Laatikko> laatikot) {
        this.laatikot = laatikot;
    }

    public Sijainti getSijainti() {
        return sijainti;
    }

    public void setSijainti(Sijainti sijainti) {
        this.sijainti = sijainti;
    }

}
