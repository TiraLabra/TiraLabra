package verkko;


/**
 *
 * @author maef
 */
public class Solmu implements Comparable<Solmu>{
    private int x;
    private int y;
    private int heuristiikka;
    private int alkuarvo = Integer.MAX_VALUE;
    private Solmu[][] laby;
    public boolean seina;
    private Solmu polku;

    public void setOnkoSeina(boolean seina) {
        this.seina = seina;
    }

    public void setHeuristiikka(int heuristiikka) {
        this.heuristiikka = heuristiikka;
    }

    public Solmu(int x, int y, Solmu[][] laby) {
        this.x = x;
        this.y = y;
        this.laby = laby;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setPolku(Solmu solmu) {
        this.polku = solmu;
    }
    
    public Solmu getPolku(){
        return this.polku;
    }
    
    /**
     * 
     * @param n
     * @return 
     * Kertoo solmun viereiset solmut pystyakselilla.
     * n:n arvon pitää olla joko 1 tai -1.
     */
    public Solmu vierusX(int n) {
        if (x+n<0 || x+n >= laby[0].length) {

            return null;
        }
        return laby[y][x+n];
    }

    public int getAlkuarvo() {
        return alkuarvo;
    }

    public void setAlkuarvo(int alkuarvo) {
        this.alkuarvo = alkuarvo;
    }
    
    /**
     * 
     * @param n
     * @return
     * Kertoo solmun viereiset solmut vaaka-akselilla.
     * n:n arvon on oltava joko 1 tai -1.
     */
    public Solmu vierusY(int n) {
        if (y+n<0 || y+n >= laby.length) {
            return null;
        }
        return laby[y+n][x];
    }
    
    public int getHeuristiikka() {
        return heuristiikka;
    }

    @Override
    public int compareTo(Solmu o) {
        return ((this.alkuarvo+this.heuristiikka)-(o.alkuarvo+o.heuristiikka));
    }
    
    @Override
    public String toString(){
        return "( x: "+ x+ " y: " + y + " h: " + heuristiikka + " )";
    }
}
