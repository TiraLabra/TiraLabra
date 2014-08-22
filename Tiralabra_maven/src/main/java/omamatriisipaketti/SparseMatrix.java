
package omamatriisipaketti;

/**
 * Sparse matrix. Luokka tarjoaa keinot käsitellä matriiseja, joissa on vain vähän muita kuin
 * nolla-arvoja.
 * @author risto
 */
public class SparseMatrix {
    private int m;
    private int n;
    private Solmu[] lista;
    
    public SparseMatrix(double[][] matriisi) {
        int m = matriisi.length;
        int n = matriisi[0].length;
        this.lista = new Solmu[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matriisi[i][j] != 0) {
                    this.insert(i, j, matriisi[i][j]);
                }
            }
        }
    }
    
    
    public void insert(int rivi,int sarake,double arvo) {

        Solmu tallennettava = new Solmu();
        tallennettava.setSarake(sarake);
        tallennettava.setKey(arvo);

        if (lista[rivi]==null) {
            lista[rivi] = tallennettava;
            return;
        }
        
        Solmu x = new Solmu();
        x = lista[rivi];        
        
        if (x==null) {
            lista[rivi] = tallennettava;
            return;
        }
        if (x.next==null) {
            x.next=tallennettava;
            return;
        }
        while (x.next != null) {
            x = x.next;
        }
        x.next=tallennettava;
    }
    
    
    public void kerro(double[][] kertoja) {
        int m = kertoja.length;
        int n = kertoja[0].length;
        
        
    }
    
    
    private class Solmu {
        public Solmu next;
        public int sarake;
        public double key;
        
        public Solmu() {
            this.next=null;            
        }
        public void setSarake(int sarake) {
            this.sarake=sarake;
        }
        public void setKey(double key) {
            this.key = key;
        }
    }
}