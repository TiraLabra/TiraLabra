/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko;

import java.util.List;
import tira.Lista;
import verkko.esimerkki.LinjaJSON;

/**
 * Linja on kokoelma pysakkien välisiä kaaria.
 * 
 * @author E
 */
public class Linja {
    /**
     * Oletustyyppi linjalle
     */
    public static final String TYYPPI_RATIKKA = "raitiovaunu";
    
    /**
     * Linjan yksilöivä koodi
     */
    private String koodi;
    /**
     * Lyhyt versio koodista
     */
    private String lyhytKoodi;
    /**
     * Linjan nimi (1,3T,...)
     */
    private String nimi;  
    /**
     * Linjan tyyppi: bussi, raitiovaunu, metro yms
     */
    private String tyyppi;
    /**
     * Linjan reitti järjestettynä listana kaarista: ensimmäinen on lähtöpysäkki, viimeinen päätepysäkki
     */
    private Lista<Kaari> reitti;
    

    /**
     * Konstruktori JSON-muotoista dataa varten
     * 
     * @param linja 
     */
    public Linja( LinjaJSON linja ) {
        this( linja.getKoodi(), linja.getKoodiLyhyt(), linja.getNimi());        
    }
    /**
     * Yleinen konstruktori.
     * 
     * @param koodi
     * @param lyhytKoodi
     * @param nimi 
     */
    public Linja(String koodi, String lyhytKoodi, String nimi) {
        this.koodi = koodi;
        this.lyhytKoodi = lyhytKoodi;
        this.nimi = nimi;
    }

    /////////////////////////////////////////////
    ///// automaattiset setterit & getterit /////
    /////////////////////////////////////////////    
    
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

    public Lista<Kaari> getReitti() {
        return reitti;
    }

    public void setReitti(Lista<Kaari> reitti) {
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
    /////////////////////////////////////////////
    ///// automaattinen hashcode&equals     /////
    /////////////////////////////////////////////
    /**
     * Hashcode luodaan linjan yksikäsitteisen koodin perusteella.
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.koodi != null ? this.koodi.hashCode() : 0);
        return hash;
    }
    /**
     * Kaksi linjaa ovat samat jos niiden yksikäsitteinen koodi on sama.
     * 
     * @param obj Toinen linja
     * @return 
     */
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
