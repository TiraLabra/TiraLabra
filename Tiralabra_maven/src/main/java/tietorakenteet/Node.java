package tietorakenteet;

/**
 * Node-luokka yksittäisiä haettavan alueen nodeja varten.
 * Sisältää paikkatiedot, ja A*-algoritmin tarvitsemat tietokentät.
 */
public class Node implements Comparable<Node> {

    /**
     * Arvo, jolla määritellään mistä eteenpäin noden katsotaan olevan
     * kulkukelvotonta seinäaluetta.
     */
    private static int MAKSIMIARVO_KULJETTAVUUDELLE = 5;
    
    /**
     * Node tietää oman sijantinsa rivin.
     */
    private int rivi;
    
    /**
     * Node tietää oman sijaintinsa sarakkeen.
     */
    private int sarake;
    
    /**
     * Noden etäisyys alkupisteestä.
     * Alustetaan Integer.MAXVALUEksi luodessa.
     * Vastaa G-arvoa kaavoissa (F=G+H)
     */
    private int etaisyysAlusta;
    
    /**
     * Arvio noden etäisyydestä maaliin.
     * Lasketaan AStarin heuristiikan mukaisesti.
     * Vastaa H-arvoa kaavoissa. (F=G+H)
     */
    private int etaisyysMaaliin;
    
    /**
     * AStar-hakua varten tieto edellisestä nodesta, josta tähän tultu.
     */
    private Node edellinen;
    
    /**
     * Nodeen siirtymisen kustannus.
     */
    private int kustannus;
    
    /**
     * Tieto siitä, jos kyseinen node on toteutuneella reitillä.
     * Voi olla turha tieto, toistaiseksi kuitenkin kenttä sille.
     */
    private boolean onReitilla;
    
    /**
     * Tieto siitä, onko kyseistä nodea tarkasteltu AStar-haussa.
     * Debug-tarkoituksessa oleva tieto, jotta voi tutkia kuinka laajalle haku levinnyt.
     */
    private boolean lisattyNaapureihin;
    
    /**
     * Konstruktori, joka ottaa parametrinaan noden koordinaatit ja kustannuksen.
     * @param rivi
     * @param sarake 
     * @param kustannus
     * @param kustannus nodeen kulkemisen kustannus, oletus 0
     */
    public Node(int rivi, int sarake, int kustannus) {
        this.rivi = rivi;
        this.sarake = sarake;
        this.kustannus = kustannus;
        this.onReitilla = false;
        this.lisattyNaapureihin = false;
        this.etaisyysAlusta = Integer.MAX_VALUE;
    }
    
    /**
     * Konstruktori, joka ottaa paramatreinaan noden koordinaatit.
     * Kustannukseksi asetetaan
     * @param rivi
     * @param sarake 
     */
    public Node(int rivi, int sarake) {
        this(rivi, sarake, 0);
    }

    public Node getEdellinen() {
        return edellinen;
    }

    public int getEtaisyysAlusta() {
        return etaisyysAlusta;
    }

    public int getEtaisyysMaaliin() {
        return etaisyysMaaliin;
    }

    public int getRivi() {
        return rivi;
    }

    public int getSarake() {
        return sarake;
    }

    public int getKustannus() {
        return kustannus;
    }
    
    public boolean onReitilla() {
        return onReitilla;
    }

    public boolean onLisattyNaapureihin() {
        return lisattyNaapureihin;
    }

    public void setKustannus(int kustannus) {
        this.kustannus = kustannus;
    }
    
    public void setEtaisyysAlusta(int etaisyysAlusta) {
        this.etaisyysAlusta = etaisyysAlusta;
    }

    public void setEtaisyysMaaliin(int etaisyysMaaliin) {
        this.etaisyysMaaliin = etaisyysMaaliin;
    }
    
    public void setEdellinen(Node edellinen) {
        this.edellinen = edellinen;
    }
    
    public void setOnReitilla(boolean onko) {
        this.onReitilla = onko;
    }

    public void setLisattyNaapureihin(boolean onko) {
        this.lisattyNaapureihin = onko;
    }
    
    // -------------------------------------------------------------------------
    
    /**
     * Kertoo, onko kyseinen node kuljettavissa.
     * Tosin sanoen palauttaa false, kun kyseessä on seinä tms.
     * @return 
     */
    public boolean kuljettavissa() {
        if (kustannus > MAKSIMIARVO_KULJETTAVUUDELLE )
            return false;
        else
            return true;
    }
    
    /**
     * Nodejen välisen suuruusvertailut toteuttava metodi.
     * Jos etäisyys olisi sama, vertailuperusteena käytetään koordinaatteja,
     * painottaen vasemmasta yläkulmasta olevia arvoja pienemmäksi.
     * 
     * @param node vertailtava toinen node
     * @return
     */    
    public int compareTo(Node node) {
        int ero = (this.etaisyysAlusta+this.etaisyysMaaliin) - (node.etaisyysAlusta+node.etaisyysMaaliin);
        if (ero == 0) {     // Jos sama matka, vertaillaan koordinaatteja
            ero = ( (this.rivi+this.sarake) - (node.rivi+node.sarake) );
        }
        if (ero == 0) {     // Jos vieläkin sama, painotetaan pienintä rivinumeroa
            ero = ( this.rivi - node.rivi);
        }
        return ero;
    }

    @Override
    public String toString() {
        String tulos = "";
        tulos = this.rivi+", " + this.sarake + " G: " + this.etaisyysAlusta + " H: " + this.etaisyysMaaliin;
        if (edellinen != null)
            tulos += " edel: " + edellinen.rivi+"x"+edellinen.sarake;
        return tulos;
    }
    
}
