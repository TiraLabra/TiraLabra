package verkko.esimerkki;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 *
 * @author E
 */
public class PysakkiJSON  {

     //JSON-data
    private String koodi;
    private String koodiLyhyt;
    private String osoite;
    private String nimi;
    private int x;
    private int y;
    private HashMap<String, String[]> naapurit;   // pysäkki-[millä linjoilla pääsee]
    
    
    public String getKoodi() {
        return koodi;
    }

    public void setKoodi(String koodi) {
        this.koodi = koodi;
    }

    public String getKoodiLyhyt() {
        return koodiLyhyt;
    }

    public void setKoodiLyhyt(String koodiLyhyt) {
        this.koodiLyhyt = koodiLyhyt;
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

    
    public HashMap<String, String[]> getNaapurit() {
        return naapurit;
    }

    public void setNaapurit(HashMap<String, String[]> naapurit) {
        this.naapurit = naapurit;
    }
    
}
