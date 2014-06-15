package com.mycompany.tiralabra_maven.domain;

/**
 * Tämä implementoi laatikkoa
 * @author Sami
 */
public class Laatikko {

    private Laatikkotyyppi tyyppi;
    private Sijainti sijainti;
    private int orientaatio;
    // Nämä luvut ovat laatikon mitat ovat samat kuin tyypin, mutta niiden järjestys riippuu orientaatiosta
    private int x;
    private int y;
    private int z;
    private final int[] orientX;
    private final int[] orientY;
    private final int[] orientZ;

    /**
     * Konstruktori
     * @param tyyppi Laatikon tyyppi
     * @param sijainti Laatikon sijainti, oletusarvoisesti tämä voisi olla origo
     * @param orientaatio Laatikon orientaatio, käytetään luku välillä 0-5
     */
    public Laatikko(Laatikkotyyppi tyyppi, Sijainti sijainti, int orientaatio) {
        this.tyyppi = tyyppi;
        this.sijainti = sijainti;
        this.orientaatio = orientaatio;

        this.orientX = new int[]{this.tyyppi.getX(), this.tyyppi.getX(), this.tyyppi.getY(), this.tyyppi.getY(), this.tyyppi.getZ(), this.tyyppi.getZ()};
        this.orientY = new int[]{this.tyyppi.getY(), this.tyyppi.getZ(), this.tyyppi.getX(), this.tyyppi.getZ(), this.tyyppi.getY(), this.tyyppi.getX()};
        this.orientZ = new int[]{this.tyyppi.getZ(), this.tyyppi.getY(), this.tyyppi.getZ(), this.tyyppi.getX(), this.tyyppi.getX(), this.tyyppi.getY()};

        this.x = orientX[orientaatio];
        this.y = orientY[orientaatio];
        this.z = orientZ[orientaatio];
    }

    @Override
    public String toString() {
        return "Laatikko: x = " + this.x + ", y = " + this.y + ", z = " + this.z + " , orientaatio: " + this.orientaatio;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Laatikkotyyppi getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(Laatikkotyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public Sijainti getSijainti() {
        return sijainti;
    }

    public void setSijainti(Sijainti sijainti) {
        this.sijainti = sijainti;
    }

    public int getOrientaatio() {
        return orientaatio;
    }
/**
 * Päivitetään laatikon sisäiset mitat orientaation mukaan
 * @param orientaatio Luku välillä 0-5
 */
    public void setOrientaatio(int orientaatio) {
        this.orientaatio = orientaatio;
        this.x = orientX[orientaatio];
        this.y = orientY[orientaatio];
        this.z = orientZ[orientaatio];
    }

}
