package verkko.esimerkki;


/**
 * Linja-JSON-datan kentät sisältävä luokka. Apuluokka linjadatan lukemiseksi
 * 
 * @author E
 */
public class LinjaJSON  {
    
    // JSON-datasta luettavat kentät: nimet samat kuin JSONissa
    private String koodi;
    private String koodiLyhyt;
    private String nimi;
    // linjan pysäkit:
    private int[] x;
    private int[] y;
    private String[] psKoodit;
    private int[] psAjat;   
    
    /////////////////////////////////////////////
    ///// automaattiset setterit & getterit /////
    /////////////////////////////////////////////  
    
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

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int[] getX() {
        return x;
    }

    public void setX(int[] x) {
        this.x = x;
    }

    public int[] getY() {
        return y;
    }

    public void setY(int[] y) {
        this.y = y;
    }

    public String[] getPsKoodit() {
        return psKoodit;
    }

    public void setPsKoodit(String[] psKoodit) {
        this.psKoodit = psKoodit;
    }

    public int[] getPsAjat() {
        return psAjat;
    }

    public void setPsAjat(int[] psAjat) {
        this.psAjat = psAjat;
    }
    
    
}
