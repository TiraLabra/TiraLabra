
/**
 *
 * @author maef
 */
public class Solmu {
    private int a;
    private int b;
    private Labyrintti laby;

    public Solmu(int a, int b, Labyrintti laby) {
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
        if (a == 0 || a == laby.getWidth()-1) {
            return null;
        }
        
        Solmu vierus = new Solmu((a+n), b, laby);
        return vierus;
    }
    
    /**
     * 
     * @param n
     * @return
     * Kertoo solmun viereiset solmut pystyakselilla.
     * n:n arvon on oltava joko 1 tai -1.
     */
    public Solmu vierusY(int n) {
        if (b == 0 || b == laby.getHeight()-1) {
            return null;
        }
        Solmu vierus = new Solmu(a, (b+n), laby);
        return vierus;
    }
}
