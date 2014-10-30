/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko;

import java.util.List;
import verkko.esimerkki.LinjaJSON;

/**
 * Linja on kokoelma pysakkien välisiä kaaria.
 * 
 * @author E
 */
public class Linja {
    
    public static final String TYYPPI_RATIKKA = "raitiovaunu";
    
    private String koodi;
    private String lyhytKoodi;
    private String nimi;  
    private String tyyppi; // bussi, spåra, metro
    private List<Kaari> reitti;
    
    /*
    WIP: LÄHTÖAJAT
    WIP: KOMMENTOINTI
    */

    public Linja( LinjaJSON linja ) {
        this( linja.getKoodi(), linja.getKoodiLyhyt(), linja.getNimi());        
    }
    
    public Linja(String koodi, String lyhytKoodi, String nimi) {
        this.koodi = koodi;
        this.lyhytKoodi = lyhytKoodi;
        this.nimi = nimi;
    }

    
    public String getKoodi() {
        return koodi;
    }

    public void setKoodi(String koodi) {
        this.koodi = koodi;
    }

    public String getLyhytKoodi() {
        return lyhytKoodi;
    }

    public void setLyhytKoodi(String lyhytKoodi) {
        this.lyhytKoodi = lyhytKoodi;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public List<Kaari> getReitti() {
        return reitti;
    }

    public void setReitti(List<Kaari> reitti) {
        this.reitti = reitti;
    }

    public String getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    @Override
    public String toString() {
        return "Linja{" + "koodi=" + koodi + ", lyhytKoodi=" + lyhytKoodi + ", nimi=" + nimi + ", tyyppi=" + tyyppi + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.koodi != null ? this.koodi.hashCode() : 0);
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
        final Linja other = (Linja) obj;
        if ((this.koodi == null) ? (other.koodi != null) : !this.koodi.equals(other.koodi)) {
            return false;
        }
        return true;
    }
    
    
}
