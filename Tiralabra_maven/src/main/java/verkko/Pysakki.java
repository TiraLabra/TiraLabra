/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko;

import verkko.esimerkki.PysakkiJSON;

/**
 * Verkon solmu.
 * 
 * @author E
 */
public class Pysakki {

    private String koodi;
    private String osoite;
    private String nimi;
    private int x;
    private int y;

    public Pysakki( PysakkiJSON pysakki ) {
        this( pysakki.getKoodi(), pysakki.getOsoite(), pysakki.getNimi(), pysakki.getX(), pysakki.getY() );
    }
    
    public Pysakki(String koodi, String osoite, String nimi, int x, int y) {
        this.koodi = koodi;
        this.osoite = osoite;
        this.nimi = nimi;
        this.x = x;
        this.y = y;
    }

    
    
    public String getKoodi() {
        return koodi;
    }

    public void setKoodi(String koodi) {
        this.koodi = koodi;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
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

    @Override
    public String toString() {
        return "Pysakki{" + "koodi=" + koodi + ", osoite=" + osoite + ", nimi=" + nimi + ", x=" + x + ", y=" + y + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.koodi != null ? this.koodi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pysakki other = (Pysakki) obj;
        if ((this.koodi == null) ? (other.koodi != null) : !this.koodi.equals(other.koodi)) {
            return false;
        }
        return true;
    }
    
    
    
}
