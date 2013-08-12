
/**
 *
 * @author maef
 */
public class Solmu implements Comparable<Solmu>{
    private int a;
    private int b;
    private int heuristiikka;
    private int alkuarvo = Integer.MAX_VALUE;
    private Solmu[][] laby;
    boolean seina;

    public void setOnkoSeina(boolean seina) {
        this.seina = seina;
    }

    public void setHeuristiikka(int heuristiikka) {
        this.heuristiikka = heuristiikka;
    }

    public Solmu(int a, int b, Solmu[][] laby) {
        this.a = a;
        this.b = b;
        this.laby = laby;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }
    
    /**
     * 
     * @param n
     * @return 
     * Kertoo solmun viereiset solmut vaaka-akselilla.
     * n:n arvon pitää olla joko 1 tai -1.
     */
    public Solmu vierusX(int n) {
        if (a+n<0 || a+n > laby[0].length) {
            return null;
        }
        return laby[b][a+n];
    }
    
    /**
     * 
     * @param n
     * @return
     * Kertoo solmun viereiset solmut pystyakselilla.
     * n:n arvon on oltava joko 1 tai -1.
     */
    public Solmu vierusY(int n) {
        if (b+n<0 || b+n > laby.length) {
            return null;
        }
        return laby[b+n][a];
    }
    
    public int getHeuristiikka() {
        return heuristiikka;
    }

    @Override
    public int compareTo(Solmu o) {
        return 0;
    }
    
    @Override
    public String toString(){
        return "( a: "+ a+ " b: " + b + " h: " + heuristiikka + " )";
    }
}
